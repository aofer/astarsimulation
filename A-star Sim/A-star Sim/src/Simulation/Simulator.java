package Simulation;

import heuristics.HeuristicInterface;
import heuristics.ManhattanHeuristic;
import maps.TileBasedMap;
import maps.TiledMapImpl;
import algorithms.AStarPathFinder;
import algorithms.PathFinderInterface;

public class Simulator {
	
	private PathFinderInterface _pathFinder;
	private TileBasedMap _map;
	private HeuristicInterface _heuristic;
	
	public Simulator(){
		this._map = new TiledMapImpl(20, 20, false);
		this._heuristic = new ManhattanHeuristic();
		this._pathFinder = new AStarPathFinder(this._map, this._heuristic);
		
	}
}
