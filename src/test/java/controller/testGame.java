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
	public void testInitializeBoard() { //TDD. Caja Negra. Comprueba que el tablero se inicialize correctamente.
		Game g = new Game(12, 10, 20);
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
	public void testOpenBoard() { //TDD. Caja Negra. Comprueba que la función open funcione correctamente.
		Game g = new Game(12, 10, 20);
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
	public void testFlagBoard() { //TDD. Caja Negra. Comprueba que la bandera se ponga correctamente.
		Game g = new Game(12, 10, 20);
		Board b = g.getBoard();
		g.flagBoard(4,4);
		SquareStatus real = b.getStatus(4, 4);
		SquareStatus aux = SquareStatus.FLAGGED;
		assertEquals(aux, real);		
	}
	
	@Test
	public void testLeftClick() { //TDD. Caja Negra. Se utiliza un mock de vista. Compruba que el click izquierdo se haga correctamente. (abra una casilla).
		Game g = new Game(12, 10, 20);
		MockView v = new MockView(g);
		g.setView(v);
		v.leftClick();
		Board b = g.getBoard();
		SquareStatus real = b.getStatus(2, 2);
		SquareStatus aux = SquareStatus.VALUE;
		assertEquals(aux, real);
	}
	
	@Test
	public void testRightClick() { //TDD. Caja Negra. //TDD. Caja Negra. Se utiliza un mock de vista. Compruba que el click derecho se haga correctamente. (ponga una bandera).
		Game g = new Game(12, 10, 20);
		MockView v = new MockView(g);
		g.setView(v);
		v.rightClick();
		Board b = g.getBoard();
		SquareStatus real = b.getStatus(2, 2);
		SquareStatus aux = SquareStatus.FLAGGED;
		assertEquals(aux, real);
	}
	
	@Test
	public void testMiddleClick() { //TDD. Caja Negra. //TDD. Caja Negra. Se utiliza un mock de vista.Compruba que el click del medio no haga nada.
		Game g = new Game(12, 10, 20);
		MockView v = new MockView(g);
		g.setView(v);
		v.middleClick();
		Board b = g.getBoard();
		SquareStatus real = b.getStatus(2, 2);
		SquareStatus aux = SquareStatus.NOT_OPEN;
		assertEquals(aux, real);
	}
	
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
	public void testJocAcabat() { //TDD. Caja Negra y Caja Blanca. Se utiliza un mock de vista. Comoprueba que el tablero finalize correctamente cuando la vista así se lo indica.
		Game g = new Game(12,10,119);
		MockView v = new MockView(g);
		g.setView(v);
		g.openBoard(3, 3);
		boolean boo = g.isFinished(3, 3);
		assertEquals(true, boo);
		
		Game h = new Game(5,6,4);
		MockView d = new MockView(h);
		h.setView(d);
		Board j = h.getBoard();
		j.initialize(4, 3, 55555);
		h.openBoard(3, 4);
		boo = h.isFinished(3, 4);
		assertEquals(false, boo);
		h.openBoard(0, 1); //bomba
		boo = h.isFinished(0, 1);
		assertEquals(true, boo);
	}
	
	@Test
	public void testGameInici() { //TDD. Caja Negra. Se usa un mock de la vista. Comprueba que el controlador  se inicie correctamente junto con la vista.
		Game g = new Game();
		MockView v = new MockView(g);
		v.addDif(0);
		g.setView(v);
		g.start();
		Board b = g.getBoard();
		assertEquals(10, b.getCols());
		assertEquals(8, b.getRows());
		
		g = new Game();
		v = new MockView(g);
		v.addDif(1);
		g.setView(v);
		g.start();
		b = g.getBoard();
		assertEquals(18, b.getCols());
		assertEquals(14, b.getRows());
		
		g = new Game();
		v = new MockView(g);
		v.addDif(2);
		g.setView(v);
		g.start();
		b = g.getBoard();
		assertEquals(24, b.getCols());
		assertEquals(20, b.getRows());
	}
	
	@Test
	public void testResetGame() {//TDD. Caja Negra. Se utiliza un mock de vista. Comprueba que el juego se resetee correctamente cuando la vista así se lo indique.
		Game g = new Game(12,10,119);
		g.openBoard(3, 3);
		Board b = g.getBoard();
		int pendings = b.getPendingSquares();
		assertEquals(0, pendings);
		
		MockView v = new MockView(g);
		v.reset(0);
		b = g.getBoard();
		pendings = b.getPendingSquares();
		assertEquals(70, pendings);
		assertEquals(8, b.getRows());
		assertEquals(10, b.getCols());
		
		v.reset(1);
		b = g.getBoard();
		pendings = b.getPendingSquares();
		assertEquals(212, pendings);
		assertEquals(14, b.getRows());
		assertEquals(18, b.getCols());
		
		v.reset(2);
		b = g.getBoard();
		pendings = b.getPendingSquares();
		assertEquals(381, pendings);
		assertEquals(20, b.getRows());
		assertEquals(24, b.getCols());
		
	}	
	
}
