/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import algorithms.myPoint;


/**
 *
 * @author Liron Katav
 */
public class Cell extends Component implements ActionListener {

	public static enum Agents{
		Agent1,Agent2;
	}
	
	public static enum Status{
		Empty,Start,Finish,Blocked,inOpenList,inClosedList;
	}

	
    //fields
    private myPoint _position;
    private Status _status ;
    private double _distFromStart;
    private double _distFromFinish ;
    private Agents _Agent_num ;

    //Constructor
    public Cell(myPoint point) {
    	this._position = point;
        init();
    }

    //Constructor
    public Cell(myPoint p, boolean block) {
    	this._position = p;       
        init();
        this.set_status(Status.Blocked);
    }

    private void init() {
    	this.set_status(Status.Empty);
        this._distFromStart =  -1;
        this._distFromFinish = -1;
        this._Agent_num = Agents.Agent1; //default

        // Action and mouse listener support
        enableEvents(AWTEvent.MOUSE_EVENT_MASK);
        
        MouseListener mouseOnCell = new MouseOnCell();
        this.addMouseListener(mouseOnCell);

    }

    /**
     * @return the _position
     */
    public myPoint getPosition() {
        return _position;
    }

    /**
     * @param position the _position to set
     */
    public void setPosition(myPoint position) {
        this._position = position;
    }

    /**
     * @return the _distFromStart
     */
    public double getDistFromStart() {
        return _distFromStart;
    }

    /**
     * @param distFromStart the _distFromStart to set
     */
    public void setDistFromStart(double distFromStart) {
        this._distFromStart = distFromStart;
    }

    
    public void set_Agent_num(Agents _Agent_num) {
		this._Agent_num = _Agent_num;
	}

	public Agents get_Agent_num() {
		return _Agent_num;
	}
    /**
     * @return the _distFromFinish
     */
    public double getDistFromFinish() {
        double ans;
        if (this._status == Status.Start) {
            ans = 0;
        }
        if (this._status == Status.Blocked) {
            ans = -1;
        } else {
            ans = this._distFromStart;
        }
        return ans;
    }

    /**
     * @param distFromFinish the _distFromFinish to set
     */
    public void setDistFromFinish(double distFromFinish) {
        this._distFromFinish = distFromFinish;
    }

    public void set_status(Status _status) {
		this._status = _status;
	}

	public Status get_status() {
		return _status;
	}

    @Override
    public void paint(Graphics g) {
        Dimension size = getSize();
        if (this._status == Status.Empty) {
            g.setColor(Color.white);
        }
        if (this._status == Status.Start) {
            g.setColor(Color.green);
        }
        if (this._status == Status.Finish) {
            g.setColor(Color.red);
        }
        if (this._status == Status.Blocked) {
            g.setColor(Color.black);
        }
        if (this._status == Status.inOpenList) {
            g.setColor(Color.gray);
        }
        if (this._status == Status.inClosedList) {
            g.setColor(Color.darkGray);
        }
        g.fillRect(0, 0, size.width, size.height);
        g.setColor(Color.black);
        g.drawRect(0, 0, size.width - 1, size.height - 1);
    }

    @Override
    public void processMouseEvent(MouseEvent event) {
        super.processMouseEvent(event);
        if (event.getID() == MouseEvent.MOUSE_CLICKED) {
            System.out.println( "mouse was clicked on " +  event.getSource());
        }
    }

    public void actionPerformed(ActionEvent event) {
    	repaint();
    }
    
}//end of class Cell

