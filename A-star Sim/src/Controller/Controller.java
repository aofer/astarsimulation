package Controller;

import heuristics.HeuristicInterface;
import heuristics.ManhattanHeuristic;

import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import test.moverStub;

import maps.Mover;
import maps.TileBasedMap;
import maps.TileStatus;
import maps.TiledMapImpl;
import Simulation.Simulator;
import Simulation.mainFrame;
import algorithms.AStarPathFinder;
import algorithms.PathFinderInterface;
import algorithms.myPoint;

public class Controller implements ControllerInterFace {
	private AStarPathFinder _pathFinder;
	private TileBasedMap _map;
	private HeuristicInterface _heuristic;
	
	public Controller(){
		this._map = new TiledMapImpl(10, 10, false);
		this._heuristic = new ManhattanHeuristic();
		this._pathFinder = new AStarPathFinder(this._map, this._heuristic);
		
	}
	
	public Vector<Vector<myPoint>> findPath(myPoint[] startPoints, myPoint[] endPoints,
			Vector<myPoint> _blocked) {
	//	AStarPathFinder pathfinder = new AStarPathFinder(this._map, new ManhattanHeuristic());
		Vector<myPoint> starts = arrayToVector(startPoints);
		Vector<myPoint> ends = arrayToVector(endPoints);
		Vector<Mover> movers = new Vector<Mover>();
		for (int i = 0; i < startPoints.length;i++){
			Mover m = new moverStub();
			movers.add(m);
		}
		return this._pathFinder.findPath(movers, starts, ends);
	}

	
	public void setTile(Vector<myPoint> blockedTiles){
		for (myPoint p: blockedTiles){
			this._map.setTile(p.getX(), p.getY(), TileStatus.blocked);
		}
	}
	public Vector<myPoint> arrayToVector(myPoint[] arr){
		Vector<myPoint> res = new Vector<myPoint>();
		for (int i = 0; i < arr.length;i++){
			res.add(arr[i]);
		}
		return res;
	}

	
}
