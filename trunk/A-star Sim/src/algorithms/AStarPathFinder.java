package algorithms;

import heuristics.HeuristicInterface;

import java.util.PriorityQueue;
import java.util.Vector;

import maps.Mover;
import maps.TileBasedMap;

public class AStarPathFinder implements PathFinderInterface {
	
	private TileBasedMap _map;
	private Vector<State> _closedList;
	private PriorityQueue<State> _openList;
	private HeuristicInterface _heuristic;
	
	public AStarPathFinder(TileBasedMap map,HeuristicInterface heuristic){
		this._map = map;
		this._heuristic = heuristic;
		this._closedList = new Vector<State>();
		this._openList = new PriorityQueue<State>();
		
	}
	@Override
	public Vector<State> findPath(Vector<Mover> movers, Vector<Point> starts,
			Vector<Point> ends) {
		State initialState = new State(starts);
		State finalState = new State(ends);
		while (this._openList.size() != 0){
			State current = this._openList.poll();
			if (current.equals(finalState)){
				break;
			}
			this._closedList.add(current);
			Vector<State> neighbours = getNeighbours(current);
			for (State neighbour: neighbours){
				float nextStepCost = current.get_cost() + getMovementCost(movers, current, neighbour);
				if (nextStepCost < neighbour.get_cost()){
					if (this._openList.contains(neighbour)){	//might not work
						this._openList.remove(neighbour);	//might not work
					}
					if (this._closedList.contains(neighbour)){
						this._closedList.remove(neighbour);
					}
					if (!this._openList.contains(neighbour) && !this._closedList.contains(neighbour)){ //might not work
						neighbour.set_cost(nextStepCost);
						neighbour.set_heuristic(getHeuristicCost(movers, neighbour.get_Coordinates(),ends));
						neighbour.set_parent(current);
						this._openList.add(neighbour);
					}
				}
				
			}
		}
		//TODO pathfinding and reconsturcting after the algorithm finished
		return null;
	}
	private float getHeuristicCost(Vector<Mover> movers,
			Vector<Point> starts, Vector<Point> ends) {
		return this._heuristic.getCost(this._map,movers,starts,ends);
	}
	private float getMovementCost(Vector<Mover> movers, State current, State st) {
		float ans = 0;
		for (int i = 0;i < current.get_Coordinates().size();i++){
			int sx = current.get_Coordinates().elementAt(i).getX();
			int sy = current.get_Coordinates().elementAt(i).getY();
			int tx = st.get_Coordinates().elementAt(i).getX();
			int ty = st.get_Coordinates().elementAt(i).getY();
			ans += this._map.getCost(movers.elementAt(i),sx , sy, tx, ty);
		}
		return ans;
	}
	/**
	 * finds all valid neighbours of state.
	 * @param state
	 * @return
	 */
	public Vector<State> getNeighbours(State state){
		//TODO later
		return null;
	}
}
