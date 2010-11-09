package test;

import heuristics.ManhattanHeuristic;

import java.util.Vector;

import algorithms.AStarPathFinder;
import algorithms.PathFinderInterface;
import algorithms.Point;
import algorithms.State;
import maps.Mover;
import maps.TileBasedMap;

public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TileBasedMap map = new mapStub();
		Mover mover = new moverStub();
		Mover mover2 = new moverStub();
		Mover mover3 = new moverStub();
		AStarPathFinder pathfinder = new AStarPathFinder(map, new ManhattanHeuristic());
		Point p = new Point(4,4);
		Point p2 = new Point(5,5);
		Point p3 = new Point(6,6);
	//	System.out.println("Start point is: " + p.toString());
	//	Vector<Point> moves = map.getAllMoves(mover, p);
	//	System.out.println("All possible moves are:");
	//	for (Point tPoint : moves){
	//		System.out.println(tPoint.toString());
	//	}
		Vector<Point> coordinates = new Vector<Point>();
		coordinates.add(p);
		coordinates.add(p2);
		//coordinates.add(p3);
		State s = new State(coordinates);
		Vector<Mover> movers = new Vector<Mover>();
		movers.add(mover);
		movers.add(mover2);
		//movers.add(mover3);
	//	Vector<State> res = pathfinder.getNeighbours(s,movers);
	//	System.out.println("\nNext states are: (" + res.size() + ")\n");
		//for (State st : res){
	//		System.out.println(st.toString());
	//	}
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
