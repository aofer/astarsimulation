package Controller;

import java.util.Vector;

import algorithms.myPoint;

public class Controller implements ControllerInterFace {

	@Override
	public Vector<myPoint> findPath(myPoint[] startPoints, myPoint[] endPoints,
			Vector<myPoint> _blocked) {
		// TODO Auto-generated method stub
		Vector<myPoint> stub= new Vector<myPoint>();
		stub.add(new myPoint(4,5));
		stub.add(new myPoint(5,5));
		stub.add(new myPoint(6,5));
		return stub;
	}

	@Override
	public Vector<myPoint> getClosedList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector<myPoint> getOpenList() {
		// TODO Auto-generated method stub
		return null;
	}

}
