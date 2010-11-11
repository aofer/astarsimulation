/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Point;
import java.io.Serializable;

/**
 *
 * @author Liron Katav
 */
public class Grid extends Panel implements Serializable{

	public final static int LENGHT = 20;
    //fields
    private int _width = LENGHT;
    private int _height = LENGHT;
    private Cell _grid[][] = new Cell[get_width()][get_height()];
    transient Image buffer;

    //constractor
    public Grid() {
        super();
        setLayout(new GridLayout(get_width(), get_height()));
        for (int i = 0; i < get_height(); i++) {
            for (int j = 0; j < get_width(); j++) {
                get_grid()[i][j] = new Cell(new Point (i,j));
               add (get_grid()[i][j]);
            }
        }
    }

    
    public void set_grid(Cell _grid[][]) {
		this._grid = _grid;
	}


	public Cell[][] get_grid() {
		return _grid;
	}

	
	public void set_width(int _width) {
		this._width = _width;
	}


	public int get_width() {
		return _width;
	}


	public void set_height(int _height) {
		this._height = _height;
	}


	public int get_height() {
		return _height;
	}
	
    @Override
    public void paint(Graphics g){
        if(buffer == null){buffer = createImage(getBounds().width,getBounds().height);}
        Graphics bg = buffer.getGraphics();
        super.paint(bg);
        bg.setColor(Color.black);
        g.drawImage(buffer,0,0,null);
    }

    @Override
    public void update(Graphics g){
        paint(g);
    }
    


	


	




}//end of class
