package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public interface GameInterface extends MouseListener{

	
	void mouseClicked(MouseEvent arg0);

	void mouseEntered(MouseEvent arg0);

	void mouseExited(MouseEvent arg0);

	void mousePressed(MouseEvent arg0);

	void mouseReleased(MouseEvent arg0);

}
