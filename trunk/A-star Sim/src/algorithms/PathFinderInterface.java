package algorithms;

import java.util.Vector;

import maps.Mover;
/**
 * interface for all multi-agent search algorithms
 * @author amit ofer & liron katav
 *
 */
public interface PathFinderInterface {
	
	public Vector<State> findPath(Vector<Mover> movers,Vector<Point> starts,Vector<Point> ends);
	
	
}
