/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Button;
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

    //fields
    private int _width = 20;
    private int _height = 20;
    private Cell _grid[][] = new Cell[_width][_height];
     transient Image buffer;

    //constractor
    public Grid() {
        super();
        setLayout(new GridLayout(_width, _height));
        for (int i = 0; i < _height; i++) {
            for (int j = 0; j < _width; j++) {
                _grid[i][j] = new Cell();
                _grid[i][j].setPosition(new Point(i, j));
               add (_grid[i][j]);
            }
        }
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
