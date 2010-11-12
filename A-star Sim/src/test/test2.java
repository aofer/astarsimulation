package test;

import heuristics.ManhattanHeuristic;

import java.util.Vector;

import algorithms.AStarPathFinder;
import algorithms.PathFinderInterface;
import algorithms.Point;
import algorithms.State;
import maps.Mover;
import maps.TileBasedMap;
import maps.TileStatus;
import maps.TiledMapImpl;

public class test2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TiledMapImpl map = new TiledMapImpl(5,5,false);
		map.setTile(2, 4, TileStatus.blocked);
		map.setTile(2, 2, TileStatus.blocked);
		map.setTile(2, 1, TileStatus.blocked);
		map.setTile(0, 3, TileStatus.blocked);
		map.setTile(1, 2, TileStatus.blocked);
		map.setTile(3, 2, TileStatus.blocked);
		map.setTile(4, 0, TileStatus.blocked);

		Mover mover = new moverStub();
		AStarPathFinder pathfinder = new AStarPathFinder(map, new ManhattanHeuristic());
		Point p = new Point(4,4);
		Vector<Point> coordinates = new Vector<Point>();
		coordinates.add(p);
		System.out.println("Start point is: " + p.toString());
		Vector<Point> moves = map.getAllMoves(mover, p);
		System.out.println("All possible moves are:");
		for (Point tPoint : moves){
			System.out.println(tPoint.toString());
		}
		Vector<Mover> movers = new Vector<Mover>();
		movers.add(mover);
		Point p4 = new Point(0,0);
		Vector<Point> ends = new Vector<Point>();
		ends.add(p4);
		System.out.println("trying the A star:\n");
		Vector<State> path = pathfinder.findPath(movers, coordinates, ends);
		
		System.out.println("Path size is: " + path.size() +"\n");
		for (State state : path){
			System.out.println(state.get_Coordinates().toString());
		}

	}

}
