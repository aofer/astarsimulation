package Controller;

import java.util.Vector;

import algorithms.myPoint;

public interface ControllerInterFace {
	
	/**
	 * returns the final Path
	 * @param _startPoints
	 * @param _endPoints
	 * @param _blocked
	 * @return
	 */
	public Vector<Vector<myPoint>> findPath(myPoint[] startPoints,
									myPoint[] endPoints, 
									Vector<myPoint> _blocked);
	/**
	 * @return the openList
	 */
	public Vector<myPoint> getOpenList();
	
	/**
	 * @return the closed list 
	 */
	public Vector<myPoint> getClosedList();

}
