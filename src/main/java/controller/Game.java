package controller;

import java.awt.event.MouseEvent;

import core.Board;
import core.BoardR;
import view.JButtonExtend;
import view.View;

public class Game implements GameInterface {
	private View view;
	private BoardR board;
	
	Game() {
		//View v = new View(12, 10, this);	//el tauler per defecte serà 12 X 10.
		board = new BoardR(12, 10, 20);
	}
	
	public BoardR getBoard() {
		return board;
	}
	
	public void openBoard(int i, int j) {
		board.openSquare(i, j);
	}
	
	public void flagBoard(int i, int j) {
		board.changeFlag(i, j);
	}
	
	public void mouseClicked(MouseEvent m) {
		JButtonExtend aux = (JButtonExtend) m.getSource();
		int i = aux.getRow();
		int j = aux.getCol();
		this.openBoard(i, j);
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
