/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPopupMenu;

/**
 *
 * @author Owner
 */
class PopupListener  extends MouseAdapter{

private JPopupMenu _popup;

        PopupListener(JPopupMenu popupMenu) {
            _popup = popupMenu;
        }

    @Override
        public void mousePressed(MouseEvent e) {
            maybeShowPopup(e);
        }

    @Override
        public void mouseReleased(MouseEvent e) {
            maybeShowPopup(e);
        }

        private void maybeShowPopup(MouseEvent e) {
            if (e.isPopupTrigger()) {
                _popup.show(e.getComponent(),
                           e.getX(), e.getY());
            }
    }

}
