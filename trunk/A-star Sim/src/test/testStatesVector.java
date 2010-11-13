package test;

import java.util.PriorityQueue;
import java.util.Vector;

import algorithms.Point;
import algorithms.State;

public class testStatesVector {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Point p1 = new Point(1,2);
		Point p2 =new Point (1,2);
		Point p3 = new Point (2,1);
		Point p4 = new Point (2,1);
		Vector<Point> v1 = new Vector<Point>();
		v1.add(p1);
		v1.add(p3);
		Vector<Point> v2 = new Vector<Point>();
		v2.add(p2);
		v2.add(p4);
		State s1 = new State(v1);
		State s2 = new State(v2);
		System.out.println(s1);
		System.out.println(s2);
		System.out.println("\n\n\n");
		System.out.println(s2.equals(s1));
		PriorityQueue<State> states1 = new PriorityQueue<State>();
		states1.add(s1);
		
		System.out.println(states1.contains(s1));
		System.out.println(states1.contains(s2));
		
	}

}
