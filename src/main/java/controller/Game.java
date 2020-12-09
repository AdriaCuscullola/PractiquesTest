package controller;

import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;

import core.Board;
import core.BoardR;
import core.Square;
import core.SquareStatus;
import view.JButtonExtend;
import view.RView;
import view.View;

public class Game implements GameInterface {
	private View view;
	private BoardR board;
	private int row;
	private int col;
	private int bombs;
	private static final int[] ROWS_NUM = {8, 14, 20};
	private static final int[] COLS_NUM = {10, 18, 24};
	private static final int[] BOMBS_NUM = {10, 40, 99};
	
	private GeneradorRandom g;
	
	public void setRandom(GeneradorRandom i) {
		this.g = i;
	}
	
	public Game() {
		view = new RView(this);
	}
	
	public void start() {
		view.start();
	}
	
	public Game(int i, int j, int bombs) {
		board = new BoardR(i, j, bombs);
		row = i;
		col = j;
		this.bombs = bombs;
	}
	
	public void setView(View view) {
		this.view = view;
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
	
	public boolean isFinished(int i, int j) {
		SquareStatus aux = board.getStatus(i, j);
		
		boolean finished = false;
		if(aux == SquareStatus.BOMB) {
			view.printaTauler(board.getBoard());
			view.finish(false);
			finished = true;
		}
		else {
			if (0 == board.getPendingSquares()) {
				view.printaTauler(board.getBoard());
				view.finish(true);
				finished = true;
			}
		}
		return finished;
	}
	
	public void resetGame(int dificulty) {
		row = ROWS_NUM[dificulty];
		col = COLS_NUM[dificulty];
		bombs = BOMBS_NUM[dificulty];
		this.board = new BoardR(row, col, bombs);
		this.board.setGenerador(g);
	}
	
	@Override
	public void mouseClicked(MouseEvent m) { 
		JButtonExtend aux = (JButtonExtend) m.getSource();
		int i = aux.getRow();
		int j = aux.getCol();
		if(SwingUtilities.isLeftMouseButton(m)) {
			this.openBoard(i, j);
			isFinished(i, j);
		} else if(SwingUtilities.isRightMouseButton(m)) {
			this.flagBoard(i, j);
		}
		Square[][] auxSquare = board.getBoard();
		view.printaTauler(auxSquare);
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
	
	public View getView() {
		return this.view;
	}
}
