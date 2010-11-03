package heuristics;

import java.util.Vector;

import maps.Mover;
import maps.TileBasedMap;
import algorithms.Point;

public class ManhattanHeuristic implements HeuristicInterface {
	/**
	 * the sum of all Manhattan values for all agents in the search
	 */
	@Override
	public float getCost(TileBasedMap map, Vector<Mover> movers,
			Vector<Point> start, Vector<Point> ends) {
		float res = 0;
		for (int i = 0;i < start.size();i++){
			Point s = start.elementAt(i);
			Point e = ends.elementAt(i);
			res += (Math.abs(s.getX()-e.getX()) + Math.abs(s.getY()-e.getY()));
		}
		return res;
	}

}
