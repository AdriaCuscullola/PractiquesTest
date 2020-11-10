package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public interface GameInterface extends MouseListener{

	public void resetGame();
	
	@Override
	default void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	default void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	default void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	default void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	default void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
