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
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 *
 * @author Liron Katav
 */
public class Cell extends Component implements ActionListener {

    public static final int AGENT_1 = 1, AGENT_2 = 2;
    //fields
    private Point _position;
    private boolean _isStart = false;
    private boolean _isFinish = false;
    private boolean _isBlocked = false;
    private double _distFromStart = -1;
    private double _distFromFinish = -1;
    private static int _Agent = AGENT_1;
    private JPopupMenu _popup;
    private JMenuItem _mSetStart, _mSetEnd, _mSetBlock;

    ;

    //constractor
    public Cell() {
        init();
    }

    //constractor
    public Cell(boolean block) {
        init();
        this._isBlocked = block;
    }

    private void init() {

        // Create some menu items for the popup
        _mSetStart = new JMenuItem("Set Start");
        _mSetEnd = new JMenuItem("Set end");
        _mSetBlock = new JMenuItem("Set Block");
        // Create a popup menu
        _popup = new JPopupMenu();
        _popup.add(_mSetStart);
        _popup.add(_mSetEnd);
        _popup.add(_mSetBlock);

        // Action and mouse listener support
        enableEvents(AWTEvent.MOUSE_EVENT_MASK);
        _mSetStart.addActionListener(this);
        _mSetEnd.addActionListener(this);
        _mSetBlock.addActionListener(this);

        MouseListener popupListener = new PopupListener(_popup);
        this.addMouseListener(popupListener);

    }

    /**
     * @return the _position
     */
    public Point getPosition() {
        return _position;
    }

    /**
     * @param position the _position to set
     */
    public void setPosition(Point position) {
        this._position = position;
    }

    /**
     * @return the _isStart
     */
    public boolean getIsStart() {
        return _isStart;
    }

    /**
     * @param isStart the _isStart to set
     */
    public void setIsStart(boolean isStart) {
        this._isStart = isStart;
    }

    /**
     * @return the _isFinish
     */
    public boolean getIsFinish() {
        return _isFinish;
    }

    /**
     * @param isFinish the _isFinish to set
     */
    public void setIsFinish(boolean isFinish) {
        this._isFinish = isFinish;
    }

    /**
     * @return the _isBlocked
     */
    public boolean getIsBlocked() {
        return _isBlocked;
    }

    /**
     * @param isBlocked the _isBlocked to set
     */
    public void setIsBlocked(boolean isBlocked) {
        this._isBlocked = isBlocked;
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

    /**
     * @return the _distFromFinish
     */
    public double getDistFromFinish() {
        double ans;
        if (this._isStart) {
            ans = 0;
        }
        if (this._isBlocked) {
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

    /**
     * @return the _Agent
     */
    public static int getAgent() {
        return _Agent;
    }

    /**
     * @param aAgent the _Agent to set
     */
    public static void setAgent(int aAgent) {
        _Agent = aAgent;
    }

    @Override
    public void paint(Graphics g) {
        Dimension size = getSize();
        g.setColor(Color.white);
        if (this._isStart) {
            g.setColor(Color.green);
        }
        if (this._isFinish) {
            g.setColor(Color.red);
        }
        if (this._isBlocked) {
            g.setColor(Color.black);
        }
        g.fillRect(0, 0, size.width, size.height);
        g.setColor(Color.black);
        g.drawRect(0, 0, size.width - 1, size.height - 1);
    }

    @Override
    public void processMouseEvent(MouseEvent event) {
        super.processMouseEvent(event);
        if (event.getID() == MouseEvent.MOUSE_CLICKED) {
            System.out.println(event.getSource());
        }
    }

    public void actionPerformed(ActionEvent event) {
        System.out.println(event.getSource());
    }
}//end of class Cell

