package Controller;

import heuristics.HeuristicInterface;
import heuristics.ManhattanHeuristic;

import java.util.Vector;

import test.moverStub;

import maps.Mover;
import maps.TileBasedMap;
import maps.TileStatus;
import maps.TiledMapImpl;
import Simulation.mainFrame;
import algorithms.myPoint;

public class Controller implements ControllerInterFace {
	//private AStarPathFinder _pathFinder;
	private TileBasedMap _map;
	private HeuristicInterface _heuristic;
	private mainFrame _mainFrame;
	private AStarPathFinderWorker _findPathWorker ;
	
	public Controller(mainFrame main){
		this._map = new TiledMapImpl(10, 10, false);
		this._heuristic = new ManhattanHeuristic();
		this._mainFrame = main;
		//this._pathFinder = new AStarPathFinder(this._map, this._heuristic);
		
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
		
		this.set_findPathWorker(new AStarPathFinderWorker(this._mainFrame,starts, ends, movers, _map, _heuristic));
		try {
			this.get_findPathWorker().execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;	
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

	public void set_findPathWorker(AStarPathFinderWorker _findPathWorker) {
		this._findPathWorker = _findPathWorker;
	}

	public AStarPathFinderWorker get_findPathWorker() {
		return _findPathWorker;
	}

	
}
