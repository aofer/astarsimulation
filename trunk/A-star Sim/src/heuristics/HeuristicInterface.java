package heuristics;

import java.util.Vector;
import algorithms.Point;
import maps.Mover;
import maps.TileBasedMap;
/**
 * the interface for the multi-agent heuristics functions
 * @author amit ofer & liron katav
 *
 */
public interface HeuristicInterface {
	
	public float getCost(TileBasedMap map, Vector<Mover> movers,Vector<Point> start,Vector<Point> ends);
}
