package GUI;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPopupMenu;

/**
 * 
 * @author Owner
 */
class MouseOnCell implements MouseListener {

	MouseOnCell () {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		handleMouseEvent(e);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		handleMouseEvent(e);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		handleMouseEvent(e);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		handleMouseEvent(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		handleMouseEvent(e);
	}
	
	private void handleMouseEvent(MouseEvent e) {
		if (e.isPopupTrigger()) {
			System.out.println("action is: "  + e.toString());
		}
		
	}

}
