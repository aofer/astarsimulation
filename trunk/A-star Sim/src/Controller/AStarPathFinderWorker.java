package Controller;

import heuristics.HeuristicInterface;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Vector;

import javax.swing.SwingWorker;

import maps.Mover;
import maps.TileBasedMap;

import Simulation.mainFrame;
import algorithms.State;
import algorithms.myPoint;

public class AStarPathFinderWorker extends
		SwingWorker<Vector<Vector<myPoint>>, Vector<myPoint>> {
	private Vector<myPoint> _starts;
	private Vector<myPoint> _ends;
	private Vector<Mover> _movers;
	private TileBasedMap _map;
	private Vector<State> _closedList;
	private PriorityQueue<State> _openList;
	private HeuristicInterface _heuristic;
	private mainFrame _frame;

	public AStarPathFinderWorker(mainFrame frame, Vector<myPoint> starts,
			Vector<myPoint> ends, Vector<Mover> movers, TileBasedMap map,
			HeuristicInterface heuristic) {
		this._frame = frame;
		this._starts = starts;
		this._ends = ends;
		this._movers = movers;
		this._map = map;
		this._heuristic = heuristic;
		this._closedList = new Vector<State>();
		this._openList = new PriorityQueue<State>();
	}

	/**
	 * calculates the paths for all moving objects in the search according to
	 * the A* algorithm
	 */
	@SuppressWarnings("unchecked")
	protected Vector<Vector<myPoint>> doInBackground() throws Exception {
		Vector<Vector<myPoint>> path = new Vector<Vector<myPoint>>();
		if (!isCancelled()) {
			State initialState = new State(this._starts);
			initialState.set_heuristic(this._heuristic.getCost(_map,
					this._movers, this._starts, this._ends));
			State finalState = new State(this._ends);
			State current = null;
			this._openList.add(initialState);
			Vector<myPoint> tOpenPoints = new Vector<myPoint>();
			//Vector<myPoint> tClosedPoints = new Vector<myPoint>();
			//tOpenPoints = getPoints(this._openList);
			//publish(tOpenPoints);
			while (this._openList.size() != 0) {
				current = this._openList.poll();
				System.out.println(current.toString());
				if (current.equals(finalState)) {
					break;
				}
				this._closedList.add(current);
				//tClosedPoints = getPoints(this._closedList);
				//publish(tClosedPoints);
				Vector<State> neighbours = getNeighbours(current, this._movers);
				for (State neighbour : neighbours) {
					float nextStepCost = current.get_cost()
							+ getMovementCost(this._movers, current, neighbour);
					if (nextStepCost < neighbour.get_cost()) {
						if (this._openList.contains(neighbour)) { // might not															// work
							this._openList.remove(neighbour); // might not work
						}
						if (this._closedList.contains(neighbour)) {
							this._closedList.remove(neighbour);
						}
					}
					if (!this._openList.contains(neighbour)
							&& !this._closedList.contains(neighbour)) { // might not work
						neighbour.set_cost(nextStepCost);
						neighbour.set_heuristic(getHeuristicCost(this._movers,
								neighbour.get_Coordinates(), this._ends));
						neighbour.set_parent(current);
						this._openList.add(neighbour);
						tOpenPoints = getPoints(this._openList);
						publish(tOpenPoints);
					}

				}
			}
			// TODO pathfinding and reconsturcting after the algorithm finished

			if (current != null && current.equals(finalState)) {
				while (!current.equals(initialState)) {
					path.add(current.get_Coordinates());
					current = current.get_parent();
				}

			}
		}
		return path;
	}

	

	protected void done() {
		System.out.println("doInBackground is complete");
		try {
			Vector<Vector<myPoint>> paths = get();
			this._frame.get_grid().drawFinalPaths(paths);
		} catch (Exception e) {
			System.out.println("Caught an exception: " + e);
		}
	}
	
	
	protected void process(List<Vector<myPoint>> chunks ) {
		for(int i=0; i<chunks.size();i++){
			Vector<myPoint> tVector = chunks.get(i);
			this._frame.get_grid().setOpenListCell(tVector);
			//this._frame.get_grid().setClosedListCell(tVector);	
		}
	}

	/**
	 * returns the heuristic cost from the state with start positions start to
	 * the end points in ends
	 * 
	 * @param movers
	 *            - the type of moving object
	 * @param starts
	 *            - the vector containing the current positions of all agents
	 * @param ends
	 *            - the vector containing the goal positions of all agents
	 * @return
	 */
	private float getHeuristicCost(Vector<Mover> _movers2,
			Vector<myPoint> get_Coordinates, Vector<myPoint> _ends2) {
		return this._heuristic.getCost(this._map, this._movers, this._starts,
				this._ends);
	}

	/**
	 * calculate the cost of the start positions of all agents to the positions
	 * saved in state
	 * 
	 * @param movers
	 *            - the moving objects
	 * @param current
	 *            - the current positions of all agents in the search
	 * @param st
	 *            - the goal positions of all agents in the search
	 * @return the sum of the costs for all agents
	 */
	private float getMovementCost(Vector<Mover> movers, State current, State st) {
		float ans = 0;
		for (int i = 0; i < current.get_Coordinates().size(); i++) {
			int sx = current.get_Coordinates().elementAt(i).getX();
			int sy = current.get_Coordinates().elementAt(i).getY();
			int tx = st.get_Coordinates().elementAt(i).getX();
			int ty = st.get_Coordinates().elementAt(i).getY();
			ans += this._map.getCost(movers.elementAt(i), sx, sy, tx, ty);
		}
		return ans;
	}

	/**
	 * finds all valid neighbours of state. calculates all the permutations of
	 * each of the agents' possible moves
	 * 
	 * @param state
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Vector<State> getNeighbours(State state, Vector<Mover> movers) {
		if (state.get_Coordinates().size() == 1) {
			Vector<myPoint> moves = this._map.getAllMoves(movers.elementAt(0),
					state.get_Coordinates().elementAt(0));
			Vector<State> res = new Vector<State>();
			for (myPoint p : moves) {
				Vector<myPoint> tCoordinates = new Vector<myPoint>();
				tCoordinates.add(p);
				res.add(new State(tCoordinates));
			}
			return res;
		} else {
			Vector<State> res = new Vector<State>();
			State tState = new State(state);
			// myPoint tPoint =
			// tState.get_Coordinates().remove(tState.get_Coordinates().size() -
			// 1);
			myPoint tPoint = tState.get_Coordinates().remove(0);
			// Mover mover = movers.remove(movers.size() - 1);
			// Mover mover = movers.get(movers.size() - 1);
			Mover mover = movers.get(0);
			Vector<Mover> tMovers = (Vector<Mover>) movers.clone(); // clone
																	// method is
																	// not type
																	// safe
			Vector<myPoint> moves = this._map.getAllMoves(mover, tPoint);
			Vector<State> tStates = getNeighbours(tState, tMovers);
			for (myPoint p : moves) {
				for (State s : tStates) {
					if (checkIfLegal(state, p, s)) {
						Vector<myPoint> tCoordinates = new Vector<myPoint>(s
								.get_Coordinates());
						tCoordinates.add(0, p);
						State resState = new State(tCoordinates);
						res.add(resState);
					}
				}
			}
			return res;
		}
	}

	private boolean checkIfLegal(State current, myPoint p, State s) {
		boolean ans = true;
		Vector<myPoint> tCurrentPoints = current.get_Coordinates();
		Vector<myPoint> tCoordinates = s.get_Coordinates();
		int seifaIndex = tCurrentPoints.size() - tCoordinates.size();
		for (int i = 0; i < tCoordinates.size(); i++) {
			if (p.equals(tCoordinates.elementAt(i))
					|| (p.equals(tCurrentPoints.elementAt(seifaIndex + i)))
					&& tCoordinates.elementAt(i).equals(
							tCurrentPoints.elementAt(seifaIndex - 1))) {
				ans = false;
				break;
			}
		}
		return ans;
	}

	/**
	 * returns the path for a specific agent
	 */
	public Vector<myPoint> getPathForAgent(
			Vector<Vector<myPoint>> allAgentsPath, int index) {
		Vector<myPoint> path = new Vector<myPoint>();
		for (int i = 0; i < allAgentsPath.size(); i++) {
			Vector<myPoint> tVector = allAgentsPath.elementAt(i);
			myPoint tPoint = tVector.elementAt(index);
			path.add(tPoint);
		}
		return path;
	}
	
	/**
	 * returns vector of points from the open list
	 * @param list
	 * @return
	 */
	private Vector<myPoint> getPoints(PriorityQueue<State> queue) {
		Vector<myPoint> res = new Vector<myPoint>();
		PriorityQueue<State> Q = clonePriorityQueue(queue);
		int size = Q.size();
			for (int i=0; i<size ; i++){
				State st = Q.poll();
				Vector<myPoint> tVector = st.get_Coordinates();
					for (myPoint p: tVector){
						res.add(p);
					}
		}		
		return res;
	}
	
	
	private PriorityQueue<State> clonePriorityQueue(PriorityQueue<State> queue) {
		 PriorityQueue<State> res = new PriorityQueue<State>();
		 Object[] tArr = queue.toArray();
		 for(int i=0; i<tArr.length ; i++){
			 System.out.println(tArr[i].toString());
			 res.add((State) tArr[i]);
		 }
          return res;
	}

	/**
	 * returns vector of points from the open list
	 * @param list
	 * @return
	 */
	private Vector<myPoint> getPoints(Vector<State> list) {
		Vector<myPoint> res = new Vector<myPoint>();
		for (int i=0; i<list.size() ; i++){
			State st = list.elementAt(i);
			Vector<myPoint> tVector = st.get_Coordinates();
			for (myPoint p: tVector){
				res.add(p);
			}
		}		
		return res;
	}

}
