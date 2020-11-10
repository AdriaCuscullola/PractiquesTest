package controller;

import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;

import core.Board;
import core.BoardR;
import core.SquareStatus;
import view.JButtonExtend;
import view.RView;
import view.View;

public class Game implements GameInterface {
	private View view;
	private BoardR board;
	
	public Game() {
		View v = new RView(12, 10, this);	//el tauler per defecte serà 12 X 10.
		board = new BoardR(12, 10, 20);
	}
	public Game(int i, int j, int bombs) {
		board = new BoardR(i, j, bombs);
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
		boolean boo = false;
		if(aux == SquareStatus.BOMB) {
			view.finish(false);
			boo = true;
		}
		else {
			if (0 == board.getPendingSquares()) {
				view.finish(true);
				boo = true;
			}
		}
		return boo;
	}
	
	public void resetGame() {
		this.board = new BoardR(12, 10, 20);
	}
	
	@Override
	public void mouseClicked(MouseEvent m) {
		JButtonExtend aux = (JButtonExtend) m.getSource();
		int i = aux.getRow();
		int j = aux.getCol();
		if(SwingUtilities.isLeftMouseButton(m)) {
			this.openBoard(i, j);
			isFinished(i, j);
		}
		else {
			if(SwingUtilities.isRightMouseButton(m)) {
				this.flagBoard(i, j);
			}
		}
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
