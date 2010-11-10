package test;

import heuristics.ManhattanHeuristic;

import java.util.Vector;

import algorithms.AStarPathFinder;
import algorithms.PathFinderInterface;
import algorithms.Point;
import algorithms.State;
import maps.Mover;
import maps.TileBasedMap;
import maps.TiledMapImpl;

public class test2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TileBasedMap map = new TiledMapImpl(10,10,false);
		Mover mover = new moverStub();
		Mover mover2 = new moverStub();
		Mover mover3 = new moverStub();
		AStarPathFinder pathfinder = new AStarPathFinder(map, new ManhattanHeuristic());
		Point p = new Point(4,4);
		Point p2 = new Point(5,5);
		Vector<Point> coordinates = new Vector<Point>();
		coordinates.add(p);
		coordinates.add(p2);
		State s = new State(coordinates);
		System.out.println("Start point is: " + p.toString());
		Vector<Point> moves = map.getAllMoves(mover, p);
		System.out.println("All possible moves are:");
		for (Point tPoint : moves){
			System.out.println(tPoint.toString());
		}
		Vector<Mover> movers = new Vector<Mover>();
		movers.add(mover);
		movers.add(mover2);

		Point p4 = new Point(2,4);
		Point p5 = new Point(3,5);
		Vector<Point> ends = new Vector<Point>();
		ends.add(p4);
		ends.add(p5);
		System.out.println("trying the A star:\n");
		Vector<State> path = pathfinder.findPath(movers, coordinates, ends);
		
		System.out.println("Path size is: " + path.size() +"\n");
		for (State state : path){
			System.out.println(state.toString());
		}

	}

}
