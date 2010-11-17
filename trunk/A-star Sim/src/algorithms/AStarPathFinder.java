package algorithms;

import heuristics.HeuristicInterface;

import java.util.Observable;
import java.util.Observer;
import java.util.PriorityQueue;
import java.util.Vector;

import Events.ClosedListChangeEvent;
import Events.OpenListChangeEvent;

import maps.Mover;
import maps.TileBasedMap;

/**
 * implements the path finder interface uses a multi-agent A* to calculate the
 * paths for all moving objects in the simulation
 * 
 * @author amit
 * 
 */
public class AStarPathFinder extends Observable implements PathFinderInterface{

	private TileBasedMap _map;
	private Vector<State> _closedList;
	private PriorityQueue<State> _openList;
	private HeuristicInterface _heuristic;

	/**
	 * constructor
	 * 
	 * @param map
	 * @param heuristic
	 */
	public AStarPathFinder(TileBasedMap map, HeuristicInterface heuristic) {
		this._map = map;
		this._heuristic = heuristic;
		this._closedList = new Vector<State>();
		this._openList = new PriorityQueue<State>();

	}

	@Override
	/**
	 * calculates the paths for all moving objects in the search according
	 * to the A* algorithm
	 */
	public Vector<Vector<myPoint>> findPath(Vector<Mover> movers,
			Vector<myPoint> starts, Vector<myPoint> ends) {
		State initialState = new State(starts);
		initialState.set_heuristic(this._heuristic.getCost(_map, movers,
				starts, ends));
		State finalState = new State(ends);
		State current = null;
		this._openList.add(initialState);
		NotifyOpenListChange(initialState);
		while (this._openList.size() != 0) {
			current = this._openList.poll();
			System.out.println(current.toString());
			if (current.equals(finalState)) {
				break;
			}
			this._closedList.add(current);
			NotifyClosedListChange(current);
			Vector<State> neighbours = getNeighbours(current, movers);
			for (State neighbour : neighbours) {
				float nextStepCost = current.get_cost()
						+ getMovementCost(movers, current, neighbour);
				if (nextStepCost < neighbour.get_cost()) {
					if (this._openList.contains(neighbour)) { // might not work
						this._openList.remove(neighbour); // might not work
					}
					if (this._closedList.contains(neighbour)) {
						this._closedList.remove(neighbour);
					}
				}
				if (!this._openList.contains(neighbour)
						&& !this._closedList.contains(neighbour)) { // might not
																	// work
					neighbour.set_cost(nextStepCost);
					neighbour.set_heuristic(getHeuristicCost(movers,
							neighbour.get_Coordinates(), ends));
					neighbour.set_parent(current);
					this._openList.add(neighbour);
					NotifyOpenListChange(neighbour);
				}

			}
		}
		// TODO pathfinding and reconsturcting after the algorithm finished
		Vector<Vector<myPoint>> path = new Vector<Vector<myPoint>>();
		if (current != null && current.equals(finalState)) {
			while (!current.equals(initialState)) {
				path.add(current.get_Coordinates());
				current = current.get_parent();
			}

		}
		return path;
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
	private float getHeuristicCost(Vector<Mover> movers,
			Vector<myPoint> starts, Vector<myPoint> ends) {
		return this._heuristic.getCost(this._map, movers, starts, ends);
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
			// Point tPoint =
			// state.get_Coordinates().remove(state.get_Coordinates().size() -
			// 1);
			State tState = new State(state);
			myPoint tPoint = tState.get_Coordinates().remove(
					tState.get_Coordinates().size() - 1);
			// Mover mover = movers.remove(movers.size() - 1);
			Mover mover = movers.get(movers.size() - 1);
			Vector<Mover> tMovers = (Vector<Mover>) movers.clone(); // clone method is not type safe
			Vector<myPoint> moves = this._map.getAllMoves(mover, tPoint);
			Vector<State> tStates = getNeighbours(tState, tMovers);
			for (myPoint p : moves) {
				for (State s : tStates) {
					if (checkIfLegal(state, p, s)) {
						Vector<myPoint> tCoordinates = new Vector<myPoint>(
								s.get_Coordinates());
						tCoordinates.add(p);
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
		Vector<myPoint> tCoordinates = s.get_Coordinates();
		for (myPoint tPoint : tCoordinates) {
			if (!checkIfLegal(p, tPoint)) {
				ans = false;
				break;
			}
		}
		return ans;
	}

	private boolean checkIfLegal(myPoint p1, myPoint p2) {
		boolean ans = true;
		// check if the points are equal - meaning the two agents are on the
		// same tile
		if (p1.equals(p2))
			ans = false;
		// else if ()
		return ans;
	}

	/**
	 * returns the path for a specific agent
	 */
	@Override
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
	
	private void NotifyOpenListChange(State s){
		OpenListChangeEvent event = new OpenListChangeEvent(s.get_Coordinates());
		notifyObservers(event);
	}
	private void NotifyClosedListChange(State s){
		ClosedListChangeEvent event = new ClosedListChangeEvent(s.get_Coordinates());
		notifyObservers(event);
	}
}
