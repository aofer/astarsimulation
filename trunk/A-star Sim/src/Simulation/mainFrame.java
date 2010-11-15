/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Simulation;
import Controller.Controller;
import GUI.Grid;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import algorithms.myPoint;

/**
 * 
 * @author Liron Katav
 */
public class mainFrame extends JFrame {
	String[] agentsStrings = { "Agent1", "Agent2"};
	private Grid _grid;
	private JPanel _selectionPanel;
	public JPanel __mainPanel;
	private JPanel _contorlPanel;
	private JRadioButton _rBlock,_rStart,_rEnd;
	private ButtonGroup _selectionGroup;
	private JComboBox _AgentBox;
	private JButton _bStart, _bStop, _bStep, _bClear;
	private Controller _controller;
	private String _command;
	private int _agentNum;

	// Constructor
	public mainFrame(String title) {
		super(title);
		initComponenets();
	}

	private void initComponenets() {
		set_grid(new Grid(this));
		set_controller(new Controller());
		_selectionPanel = new JPanel();
		__mainPanel = new JPanel();
		_contorlPanel = new JPanel();
		this._command = "start";
		this._agentNum = 1;

		_selectionGroup = new ButtonGroup();
		_rBlock = new JRadioButton("Block");
		_rStart = new JRadioButton("Start Point");
		_rStart.setSelected(true);
		_rEnd = new JRadioButton("End Point");		

		_AgentBox = new JComboBox (agentsStrings);	
		
		_bStart = new JButton ("Start");
		_bStop = new JButton ("stop");
		_bStep = new JButton ("step");
		_bClear = new JButton ("Clear");
		
		_selectionGroup.add(_rBlock);
		_selectionGroup.add(_rStart);
		_selectionGroup.add(_rEnd);
				
		
		GroupLayout layout = new GroupLayout(_selectionPanel);
		_selectionPanel.setLayout(layout);

		layout.setHorizontalGroup(
				   layout.createSequentialGroup()
				   .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				           .addComponent(_AgentBox)
				           .addComponent(_rStart)
				           .addComponent(_rEnd)
				           .addComponent(_rBlock))
				);

		layout.setVerticalGroup(
				   layout.createSequentialGroup()
				   			.addGap(50)
				           .addComponent(_AgentBox)
				           .addComponent(_rStart)
				           .addComponent(_rEnd)
				           .addComponent(_rBlock)
				           .addGap(350)
				);

		
		_contorlPanel.add(_bStart);
		_contorlPanel.add(_bStop);
		_contorlPanel.add(_bStep);
		_contorlPanel.add(_bClear);

		__mainPanel.setLayout(new BorderLayout(10,10));
		__mainPanel.setSize(612, 482);
		
		__mainPanel.add(_selectionPanel,BorderLayout.WEST);
		__mainPanel.add(get_grid(), BorderLayout.CENTER);
		__mainPanel.add(_contorlPanel, BorderLayout.SOUTH);
		
				
		this._rStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                startPointActionPerformed(evt);
            }
        });
		
		this._rEnd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                endPointActionPerformed(evt);
            }		
        });
		
		this._rBlock.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                blockedActionPerformed(evt);
            }				
        });
		
		this._AgentBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                agentBoxActionPerformed(evt);
            }				
        });
		
		this._bStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                bStartActionPerformed(evt);
            }					
        });
		
		this._bStop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                bStopActionPerformed(evt);
            }				
        });
		
		this._bStep.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                bStepActionPerformed(evt);
            }				
        });
		
		this._bClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                bClearActionPerformed(evt);
            }				
        });
		
		
	}

	public void set_grid(Grid _grid) {
		this._grid = _grid;
	}

	public Grid get_grid() {
		return _grid;
	}
	
	private void startPointActionPerformed(ActionEvent evt) {	
		this._command = "start";	
	}
	
	private void endPointActionPerformed(ActionEvent evt) {
		this._command = "end";
		
	}
	
	private void blockedActionPerformed(ActionEvent evt) {
		this._command = "block";	
	}	
	
	private void agentBoxActionPerformed(ActionEvent evt) {
		this._agentNum = this._AgentBox.getSelectedIndex() + 1 ;	
	}
	
	private void bStartActionPerformed(ActionEvent evt) {
		if(this._grid.checkArguments()){
			this._controller.setTile(this._grid.get_blockList());
			Vector<Vector<myPoint>> finalPath = this.get_controller().findPath(this.get_grid().get_starts(),this.get_grid().get_ends(),this.get_grid().get_blockList());
			System.out.println("correct");
			this.get_grid().drawFinalPath(finalPath);
		}
		else{
			JOptionPane.showMessageDialog(this,
				    "You havn't entered all the parameters, please try again", 
				    "Missing Argumets", JOptionPane.ERROR_MESSAGE);
		}		
	}	
	
	private void bStopActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub		
	}	
	
	private void bStepActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		
	}	
	private void bClearActionPerformed(ActionEvent evt) {
		this.get_grid().clear();		
	}

	public String getCommand() {
		return this._command;
		
	}

	public int getAgentNumber() {
		return this._agentNum;
		
	}

	public void set_controller(Controller _controller) {
		this._controller = _controller;
	}

	public Controller get_controller() {
		return _controller;
	}	
	
	
	
}// end of class

