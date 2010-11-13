package tests;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import GUI.Cell;
import GUI.Cell.Status;
import Simulation.mainFrame;


public class GridTest {
	
	public static void main(String args[]) {
		mainFrame main = new mainFrame("A* Simulator");
		Cell[][] grid = main.get_grid().get_grid();
		grid[1][2].set_status(Status.Blocked);
		grid[2][2].set_status(Status.Blocked);
		grid[3][7].set_status(Status.Start);
		grid[3][8].set_status(Status.inOpenList);
		grid[0][8].set_status(Status.inClosedList);
		grid[10][10].set_status(Status.Finish);
		
		
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main.getContentPane().add(main.__mainPanel, BorderLayout.CENTER);
		main.setSize(700, 550);
		main.setResizable(false);
		main.setVisible(true);
	}

}
