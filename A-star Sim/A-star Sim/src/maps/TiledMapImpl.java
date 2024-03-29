package maps;

import java.util.Vector;

import algorithms.myPoint;

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
		for (int i = 0;i < this._tiles.length;i++){
			for (int j = 0; j < this._tiles[i].length;j++){
				this._tiles[i][j] = new Tile();
			}
		}
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
		return !(this._tiles[x][y].get_status() == TileStatus.free);
	}

	@Override
	public float getCost(Mover mover, int sx, int sy, int tx, int ty) {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public Vector<myPoint> getAllMoves(Mover mover, myPoint p) {
		Vector<myPoint> res = new Vector<myPoint>();
		for (int i = p.getX() - 1; i <= p.getX() + 1;i++){
			for (int j = p.getY()- 1; j <= p.getY() + 1;j++){
				if (i == p.getX() || j == p.getY() || this._diagonal == true){
					if (inMap(i,j) && !blocked(mover,i,j)){
						myPoint tPoint = new myPoint(i,j);
						//for debugging canceled the stay in one place
				//		if (!(i == p.getX() && j == p.getY()))
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
	public void setTile(int x,int y,TileStatus status,float cost){
		Tile tTile = this._tiles[x][y];
		tTile.set_status(status);
		tTile.set_cost(cost);
	}
	public void setTile(int x, int y,TileStatus status){
		Tile tTile = this._tiles[x][y];
		tTile.set_status(status);
	}
}
