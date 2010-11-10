package tests;

import java.util.Vector;

import junit.framework.TestCase;

import heuristics.ManhattanHeuristic;
import maps.Mover;
import maps.TileStatus;
import maps.TiledMapImpl;

import org.junit.Before;
import org.junit.Test;

import test.moverStub;

import algorithms.AStarPathFinder;
import algorithms.Point;


public class TileMapTest extends TestCase {
	
	private TiledMapImpl _map;
	@Before
	public void setUp() throws Exception {
		this._map = new TiledMapImpl(5, 5, false);
		this._map.setTile(1, 2, TileStatus.blocked);
		this._map.setTile(2, 1, TileStatus.blocked);
		this._map.setTile(2, 2, TileStatus.blocked);
		this._map.setTile(2, 4, TileStatus.blocked);
		this._map.setTile(4, 0, TileStatus.blocked);
		
	}
	
	public void testMoves(){
		Mover mover1 = new moverStub();
		Point p1 = new Point(4,1);
		Point res1 = new Point(3,1);
		Point res2 = new Point(4,2);
		Vector<Point> moves = this._map.getAllMoves(mover1, p1);
		Vector<Point> result = new Vector<Point>();
		result.add(res1);
		result.add(p1);
		result.add(res2);
		AssertEquals(result,moves);
		Point p2 = new Point (1,1);
		Point res3 = new Point(1,0);
		Point res4 = new Point(0,1);
		Vector<Point> result2 = new Vector<Point>();
		result2.add(res3);
		result2.add(p2);
		result2.add(res4);
		AssertEquals(result2, this._map.getAllMoves(mover1, p2));
		
	}
	public boolean AssertEquals(Vector<Point> expected,Vector<Point> actual){
		boolean ans = true;
		for (int i = 0; i < expected.size();i++){
			if (!expected.elementAt(i).equals(actual.elementAt(i))){
				ans = false;
				break;
			}
		}
		return ans;
	}
}
