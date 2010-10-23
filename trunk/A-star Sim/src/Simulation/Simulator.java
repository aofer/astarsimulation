/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Simulation;

import GUI.Grid;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Choice;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

/**
 *
 * @author Liron Katav
 */
public class Simulator extends JFrame {

    Grid _grid;
    Panel _eastPanel, _mainPanel, _southPanel;
    Choice _cAgentNum;
    Button _bStart, _bClear;

    //Constractor
    public Simulator(String title) {
        super(title);
        init();
    }

    private void init() {
        _grid = new Grid();
        _eastPanel = new Panel();
         _mainPanel = new Panel();
         _southPanel = new Panel();

        _cAgentNum = new Choice();
        _bStart = new Button("Start");
        _bClear = new Button("Clear");

        //setLayout(new BorderLayout(0, 0));
        _mainPanel.setLayout(new BorderLayout(0, 0));
        _mainPanel.setSize(612, 482);

        _cAgentNum.add("Agent 1");
        _cAgentNum.add("Agent 2");

        _eastPanel.add(new Label("Agent:"));
        _eastPanel.add(_cAgentNum);

        //_southPanel.setLayout(new GridLayout(1,2));
        _southPanel.add(_bStart);
        _southPanel.add(_bClear);

         _mainPanel.add(_eastPanel, "East");
        _mainPanel.add(_grid, "Center");
        _mainPanel.add(_southPanel, "South");
    }

    public static void main(String args[]) {
        Simulator sim = new Simulator("A* Simulator");
        sim.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        sim.getContentPane().add(sim._mainPanel,BorderLayout.CENTER);
        sim.setSize(700,550);
        sim.setResizable(false);
        //sim.pack();
        sim.setVisible(true);
    }
}//end of class

