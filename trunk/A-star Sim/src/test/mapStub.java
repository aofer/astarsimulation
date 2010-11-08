package test;

import java.util.Vector;

import maps.Mover;
import maps.TileBasedMap;

import algorithms.Point;

public class mapStub implements TileBasedMap {

	@Override
	public int getWidthInTiles() {
		// TODO Auto-generated method stub
		return 5;
	}

	@Override
	public int getHeightInTiles() {
		// TODO Auto-generated method stub
		return 5;
	}

	@Override
	public void pathFinderVisited(int x, int y) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean blocked(Mover mover, int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public float getCost(Mover mover, int sx, int sy, int tx, int ty) {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public Vector<Point> getAllMoves(Mover mover, Point p) {
		Point p1 = new Point(p.getX() - 1,p.getY());
		Point p2 = new Point(p.getX() + 1,p.getY());
		Vector<Point> res = new Vector<Point>();
		res.add(p1);
		res.add(p2);
		
		return res;
	}

}
