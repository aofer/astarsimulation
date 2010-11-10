package maps;

import java.util.Vector;

import algorithms.Point;

/**
 * an implementation of the tiled based map interface
 * @author amit
 *
 */
public class TiledMapImpl implements TileBasedMap {
	
	private int _width;
	private int _height;
	private Tile[][] _tiles;
	private boolean _diagonal;
	public TiledMapImpl(int width,int height,boolean diagonal){
		this._width =  width;
		this._height = height;
		this._tiles = new Tile[_width][_height];
		this._diagonal = diagonal;
	}
	@Override
	public int getWidthInTiles() {
		return this._width;
	}

	@Override
	public int getHeightInTiles() {
		return this._height;
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
		return 0;
	}

	@Override
	public Vector<Point> getAllMoves(Mover mover, Point p) {
		Vector<Point> res = new Vector<Point>();
		for (int i = p.getX() - 1; i <= p.getX() + 1;i++){
			for (int j = p.getY()- 1; i <= p.getY() + 1;j++){
				if (i == p.getX() || j == p.getY() || this._diagonal == true){
					if (inMap(i,j) && !blocked(mover,i,j)){
						Point tPoint = new Point(i,j);
						res.add(tPoint);
					}
				}
			}
		}
		return res;
	}
	private boolean inMap(int i, int j) {
		return  i >= 0 && i <this._width && j >=0 && j < this._height;
	}

}
