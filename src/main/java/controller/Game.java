package controller;

import core.Board;
import core.BoardR;
import view.View;

public class Game {
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
}
