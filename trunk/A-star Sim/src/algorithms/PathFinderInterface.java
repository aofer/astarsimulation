package algorithms;

import java.util.Vector;

import maps.Mover;

public interface PathFinderInterface {
	
	public Vector<State> findPath(Vector<Mover> movers,Vector<Point> starts,Vector<Point> ends);
	
	
}
