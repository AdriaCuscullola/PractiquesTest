package controller;


import static org.junit.Assert.*;

import org.junit.Test;

import core.Board;
import core.BoardR;
import core.Square;
import core.SquareStatus;
import view.MockView;
import view.View;

public class testGame {


	@Test
	public void testInitializeBoard() { //
		Game g = new Game();
		/*View view = new MockView();
		g.setView(view);
		View v = g.getView();*/
		BoardR b = g.getBoard();
		Square[][] R = b.getBoard();
		int bombs = 0;
		for(int i = 0; i < b.getRows(); i++) {
			for (int j = 0; j < b.getCols(); j++) {
				bombs += R[i][j].getIsBomb()? 1 : 0;
			}
		}
		assertEquals(12, b.getRows());
		assertEquals(10, b.getCols());
		assertEquals(0, bombs);
	}
	
	@Test
	public void testOpenBoard() {
		Game g = new Game();
		Board b = g.getBoard();
		g.openBoard(3,3);
		Square[][] R = b.getBoard();
		int bombs = 0;
		for(int i = 0; i < b.getRows(); i++) {
			for (int j = 0; j < b.getCols(); j++) {
				bombs += R[i][j].getIsBomb()? 1 : 0;
			}
		}
		assertEquals(20, bombs);
	}
	
	@Test
	public void testFlagBoard() {
		Game g = new Game();
		Board b = g.getBoard();
		g.flagBoard(4,4);
		SquareStatus real = b.getStatus(4, 4);
		SquareStatus aux = SquareStatus.FLAGGED;
		assertEquals(aux, real);		
	}
	
	@Test
	public void testLeftClick() {
		Game g = new Game();
		MockView v = new MockView(g);
		v.leftClick();
		Board b = g.getBoard();
		SquareStatus real = b.getStatus(2, 2);
		SquareStatus aux = SquareStatus.VALUE;
		assertEquals(aux, real);
	}
	
	@Test
	public void testRightClick() {
		Game g = new Game();
		MockView v = new MockView(g);
		v.rightClick();
		Board b = g.getBoard();
		SquareStatus real = b.getStatus(2, 2);
		SquareStatus aux = SquareStatus.FLAGGED;
		assertEquals(aux, real);
	}
	//TODO: MIRAR SI EL JOC ESTA ACABAT O NO, funcio que cridarà mouseclicked();
	
	/*Tauler amb seed: = 55555
	oxoooo |0 
	oooooo |1
	oxoooo |2
	ooxooo |3
	ooooox |4
	------
	012345
	*/
	@Test
	public void testJocAcabat() {
		Game g = new Game();
		Board b = g.getBoard();
		b = new BoardR(12,10,119);
		g.openBoard(3, 3);
		boolean boo = g.isFinished();
		assertEquals(true, boo);
		
		Game h = new Game();
		Board j = h.getBoard();
		j = new BoardR(5,6,4);
		j.initialize(4, 3, 55555);
		h.openBoard(3, 4);
		boo = h.isFinished();
		assertEquals(false, boo);
		h.openBoard(0, 1); //bomba
		boo = h.isFinished();
		assertEquals(true, boo);
	}
}
