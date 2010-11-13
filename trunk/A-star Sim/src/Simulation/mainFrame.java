/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Simulation;

import GUI.Grid;
import java.awt.BorderLayout;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

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
	private JRadioButton _block,_start,_end;
	private ButtonGroup _selectionGroup;
	private JComboBox _AgentBox;
	private JButton _bStart, _bStop, _bStep, _bClear;

	// Constructor
	public mainFrame(String title) {
		super(title);
		initComponenets();
	}

	private void initComponenets() {
		set_grid(new Grid());
		_selectionPanel = new JPanel();
		__mainPanel = new JPanel();
		_contorlPanel = new JPanel();

		_selectionGroup = new ButtonGroup();
		_block = new JRadioButton("Block");
		_start = new JRadioButton("Start Point");
		_end = new JRadioButton("End Point");		

		_AgentBox = new JComboBox (agentsStrings);	
		
		_bStart = new JButton ("Start");
		_bStop = new JButton ("stop");
		_bStep = new JButton ("step");
		_bClear = new JButton ("Clear Path");
		
		_selectionGroup.add(_block);
		_selectionGroup.add(_start);
		_selectionGroup.add(_end);
				
		
		GroupLayout layout = new GroupLayout(_selectionPanel);
		_selectionPanel.setLayout(layout);

		layout.setHorizontalGroup(
				   layout.createSequentialGroup()
				   .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				           .addComponent(_AgentBox)
				           .addComponent(_start)
				           .addComponent(_end)
				           .addComponent(_block))
				);

		layout.setVerticalGroup(
				   layout.createSequentialGroup()
				   			.addGap(50)
				           .addComponent(_AgentBox)
				           .addComponent(_start)
				           .addComponent(_end)
				           .addComponent(_block)
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
		
		
	}

	public void set_grid(Grid _grid) {
		this._grid = _grid;
	}

	public Grid get_grid() {
		return _grid;
	}
	
}// end of class

