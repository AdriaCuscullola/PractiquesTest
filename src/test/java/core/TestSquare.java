package core;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestSquare {
	
	/*
	 * Tests de getters y setters basicos
	 */
	
	/*
	 * TDD. Caja Negra. Test que comprueba si el método open marca la casilla como abierta
	 */
	@Test
	public void testOpen() {
		Square s = new Square();
		s.open();
		boolean isOpen = s.getIsOpen();
		assertEquals(true, isOpen);
	}
	
	/*
	 * TDD. Caja Negra. Test que comprueba si el método setBomb marca la casilla como bomba
	 */
	@Test
	public void testBomba() {
		Square s = new Square();
		s.setBomb();
		boolean isBomb = s.getIsBomb();
		assertEquals(true, isBomb);
		assertEquals(-1, s.getValue());
	}
	
	/*
	 * TDD. Caja Negra. Test que comprueba si el método setBomb devuelve si la bomba se ha marcado o
	 * ya lo estaba
	 */
	@Test
	public void testSetBomb() {
		Square s = new Square();
		boolean set = s.setBomb();
		assertEquals(true, set);
		set = s.setBomb();
		assertEquals(false,set);
	}	
	
	/*
	 * TDD. Caja Negra. Test que comprueba si el método addBoardPosition añade correctamente la posicion
	 * en el square.
	 */
	@Test
	public void testGetSquarePosition() {
		Square square = new Square();
		square.addBoardPosition(1, 2);
		assertEquals(1, square.getRow());
		assertEquals(2, square.getColumn());
	}
	
	/*
	 * Tests de Board de 1x1
	 */
	
	/*
	 * TDD. Caja Negra. Se utiliza un Mock de board. Test que comprueba si el valor al abrir una casilla
	 * vacia en un tablero unico es igual a 0.
	 */
	@Test
	public void testOpenSquareEnUnBoardDeUnaSquare() {
		MockBoard board = new MockBoard();
		board.col = 1;
		board.rows = 1;
		Square[][] squares = createBoard(board, 1, 1);
		board.addBoard(squares);
		board.openSquare(0, 0);
		assertEquals(0, board.getSquare(0, 0).getValue());
		assertTrue(board.getSquare(0, 0).getIsOpen());
	}
	
	/*
	 * TDD. Caja Negra. Se utiliza un Mock de board. Test que comprueba si el valor al abrir una casilla
	 * bomba en un tablero unico es igual a los valores de bomba.
	 */
	@Test
	public void testOpenBombEnUnBoardDeUnaSquare() {
		MockBoard board = new MockBoard();
		board.col = 1;
		board.rows = 1;
		Square[][] squares = createBoard(board, 1, 1);
		squares[0][0].setBomb();
		board.addBoard(squares);
		board.openSquare(0, 0);
		assertEquals(-1, board.getSquare(0, 0).getValue());
		assertTrue(board.getSquare(0, 0).getIsOpen());
	}
	
	/*
	 * Testos que comproven que el valor que te una casella (num de mines aprop) és el correcte
	 */

	/*
	 * TDD. Caja negra. Se utiliza un Mock de board. Se crea un mock de board con un tablero 
	 * 3x3 con 3 bombas y abrimos la casilla del medio para obtener su valor. Este test está 
	 * dentro de la partición equivalente de los datos de salida: de 0 a 8
	 */
	@Test
	public void testValorCasellaNum() {
		
		MockBoard board = new MockBoard();
		board.col = 3;
		board.rows = 3;
		Square testedSquare = new Square();
		testedSquare.setBoard(board);
		testedSquare.addBoardPosition(1, 1);
		Square[][] squares;
		Square bomb = new Square();
		bomb.setBomb();
		Square normalSquare = new Square();
		squares = new Square[][] {{bomb, normalSquare, normalSquare}, {bomb, testedSquare, bomb}, {normalSquare, normalSquare, normalSquare}};
		board.addBoard(squares);
		testedSquare.open();
		assertEquals(3, testedSquare.getValue());
	}
	
	/*
	 * TDD. Caja negra. Se utiliza un Mock de board. Se crea un mock de board con un tablero 
	 * 3x3 con 0 bombas y abrimos la casilla del medio para obtener su valor. Este test comprueba
	 * la frontera inferior (valor 0) de la particion equivalente de los datos de salida: de 0 a 8.
	 */
	@Test
	public void testValorCasellaNumSenseBombes() {
		MockBoard board = new MockBoard();
		board.col = 3;
		board.rows = 3;
		Square testedSquare = new Square();
		testedSquare.setBoard(board);
		testedSquare.addBoardPosition(1, 1);
		Square[][] squares;
		Square normalSquare = new Square();
		squares = new Square[][] {{normalSquare, normalSquare, normalSquare}, {normalSquare, testedSquare, normalSquare}, {normalSquare, normalSquare, normalSquare}};
		board.addBoard(squares);
		testedSquare.open();
		assertEquals(0, testedSquare.getValue());
	}
	
	/*
	 * TDD. Caja negra. Se utiliza un Mock de board. Se crea un mock de board con un tablero 
	 * 3x3 con 8 bombas y abrimos la casilla del medio para obtener su valor. Este test comprueba
	 * la frontera superior (valor 8) de la particion equivalente de los datos de salida: de 0 a 8.
	 */
	@Test
	public void testValorCasellaNumNomesBombes() {
		MockBoard board = new MockBoard();
		board.col = 3;
		board.rows = 3;
		Square testedSquare = new Square();
		testedSquare.setBoard(board);
		testedSquare.addBoardPosition(1, 1);
		Square[][] squares;
		Square bomb = new Square();
		bomb.setBomb();
		squares = new Square[][] {{bomb, bomb, bomb}, {bomb, testedSquare, bomb}, {bomb, bomb, bomb}};
		board.addBoard(squares);
		testedSquare.open();
		assertEquals(8, testedSquare.getValue());
	}
	
	/*
	 * TDD. Caja negra. Se utiliza un Mock de board. Se crea un mock de board con un tablero 
	 * 3x3 con 1 bomba y abrimos la casilla del medio para obtener su valor. Este test comprueba
	 * el límite inferior interno (valor 1) de la particion equivalente de los datos de salida: de 0 a 8.
	 */
	@Test
	public void testValorCasellaNumUnaBombes() {
		/*
		 * Generem un Board 3x3 i posem la casella de test al mig, aquesta te un total de 1 mines al costat. Cas limit
		 */
		MockBoard board = new MockBoard();
		board.col = 3;
		board.rows = 3;
		Square testedSquare = new Square();
		testedSquare.setBoard(board);
		testedSquare.addBoardPosition(1, 1);
		Square[][] squares;
		Square bomb = new Square();
		bomb.setBomb();
		Square normalSquare = new Square();
		squares = new Square[][] {{bomb, normalSquare, normalSquare}, {normalSquare, testedSquare, normalSquare}, {normalSquare, normalSquare, normalSquare}};
		board.addBoard(squares);
		testedSquare.open();
		assertEquals(1, testedSquare.getValue());
	}
	
	/*
	 * TDD. Caja negra. Se utiliza un Mock de board. Se crea un mock de board con un tablero 
	 * 3x3 con 7 bombas y abrimos la casilla del medio para obtener su valor. Este test comprueba
	 * el límite superior interno (valor 7) de la particion equivalente de los datos de salida: de 0 a 8.
	 */
	@Test
	public void testValorCasellaNumSetBombes() {
		MockBoard board = new MockBoard();
		board.col = 3;
		board.rows = 3;
		Square testedSquare = new Square();
		testedSquare.setBoard(board);
		testedSquare.addBoardPosition(1, 1);
		Square[][] squares;
		Square bomb = new Square();
		bomb.setBomb();
		Square normalSquare = new Square();
		squares = new Square[][] {{bomb, bomb, normalSquare}, {bomb, testedSquare, bomb}, {bomb, bomb, bomb}};
		board.addBoard(squares);
		testedSquare.open();
		assertEquals(7, testedSquare.getValue());
	}
	
	/*
	 * TDD. Caja blanca. Se utiliza un Mock de board. Se crea un mock de board con un tablero 
	 * 3x3 con 3 bombas y abrimos la casilla superior central para obtener su valor. Este test comprueba,
	 * sabiendo la implementación interna, que el método calcula el valor correcto sin dar excepciones.
	 */
	@Test
	public void testValorCasellaNumEnUnCostat() {
		MockBoard board = new MockBoard();
		board.col = 3;
		board.rows = 3;
		Square testedSquare = new Square();
		testedSquare.setBoard(board);
		testedSquare.addBoardPosition(0, 1);
		Square[][] squares;
		Square bomb = new Square();
		bomb.setBomb();
		Square normalSquare = new Square();
		squares = new Square[][] {{bomb, testedSquare, normalSquare}, {bomb, normalSquare, normalSquare}, {normalSquare, normalSquare, normalSquare}};
		board.addBoard(squares);
		testedSquare.open();
		assertEquals(2, testedSquare.getValue());
	}
	
	/*
	 * TDD. Caja blanca. Se utiliza un Mock de board. Se crea un mock de board con un tablero 
	 * 3x3 con 3 bombas y abrimos la casilla superior izquierda para obtener su valor. Este test comprueba,
	 * sabiendo la implementación interna, que el método calcula el valor correcto sin dar excepciones.
	 */
	@Test
	public void testValorCasellaNumEnUnaCantonada() {
		MockBoard board = new MockBoard();
		board.col = 3;
		board.rows = 3;
		Square testedSquare = new Square();
		testedSquare.setBoard(board);
		testedSquare.addBoardPosition(0, 0);
		Square[][] squares;
		Square bomb = new Square();
		bomb.setBomb();
		Square normalSquare = new Square();
		squares = new Square[][] {{testedSquare, normalSquare, normalSquare}, {normalSquare, bomb, normalSquare}, {bomb, bomb, normalSquare}};
		board.addBoard(squares);
		testedSquare.open();
		assertEquals(1, testedSquare.getValue());
	}
	
	/*
	 * TDD. Caja blanca. Se utiliza un Mock de board. Se crea un mock de board con un tablero 
	 * 3x3 con 4 bombas y abrimos la casilla inbferior derecha para obtener su valor. Este test comprueba,
	 * sabiendo la implementación interna, que el método calcula el valor correcto sin dar excepciones.
	 */
	@Test
	public void testValorCasellaNumEnUnaCantonadaInferior() {
		MockBoard board = new MockBoard();
		board.col = 3;
		board.rows = 3;
		Square testedSquare = new Square();
		testedSquare.setBoard(board);
		testedSquare.addBoardPosition(2, 2);
		Square[][] squares;
		Square bomb = new Square();
		bomb.setBomb();
		Square normalSquare = new Square();
		squares = new Square[][] {{bomb, normalSquare, normalSquare}, {normalSquare, bomb, normalSquare}, {bomb, bomb, testedSquare}};
		board.addBoard(squares);
		testedSquare.open();
		assertEquals(2, testedSquare.getValue());
	}
	
	/*
	 * Conjunto de tests que comprueban la abertura en cascada de las casillas vacias.
	 */
	
	private Square[][] createBoard(Board board, int rows, int columns) {
		Square[][] squares = new Square[rows][columns];
		for(int i = 0; i<rows; i++) {
			for(int j = 0; j<columns; j++) {
				Square newSquare = new Square();
				newSquare.addBoardPosition(i, j);
				squares[i][j] = newSquare;
				newSquare.setBoard(board);
			}
		}
		return squares;
	}
	
	/**
	 * TDD. Caja Negra, Se utiliza un mock de board. Crea un board 3x3 con una bomba en la esquina
	 * inferior derecha. Abre al square de la esquina superior izquierda lo que hace que todas se abren
	 * exceptuando la bomba. Podriamos decir que la particion equivalente en este test y los dos siguientes
	 * son los datos de salida (se abren distintas casillas o ninguna otra). En este se comprueba el primer tipo
	 */
	@Test
	public void testCasellesBuidesAdjacentsObertes() {
		MockBoard board = new MockBoard();
		board.col = 3;
		board.rows = 3;
		Square[][] squares = createBoard(board, 3, 3);
		squares[2][2].setBomb();
		board.addBoard(squares);
		board.openSquare(0, 0);
		for(int i = 0; i<3; i++) {
			for(int j = 0; j<3; j++) {
				Square square = board.getSquare(i, j);
				if(i != 2 && j != 2) {
					assertTrue(square.getIsOpen());
				} else if (i == 2 && j == 2){
					assertFalse(square.getIsOpen());
				}
			}
		}
	}
	
	/**
	 * TDD. Caja Negra, Se utiliza un mock de board. Crea un board 3x3 con una bomba en la esquina
	 * inferior derecha. Abre la square central lo que hace que ninguna otra square tenga que abrirse.
	 * Respecto a la particion comentada anteriormente, en este se comprueba el segundo tipo
	 */
	@Test
	public void testCasellesBuidesAdjacentsObertesClicantNum() {
		MockBoard board = new MockBoard();
		board.col = 3;
		board.rows = 3;
		Square[][] squares = createBoard(board, 3, 3);
		squares[2][2].setBomb();
		board.addBoard(squares);
		board.openSquare(1, 1);
		for(int i = 0; i<3; i++) {
			for(int j = 0; j<3; j++) {
				Square square = board.getSquare(i, j);
				if(i == 1 && j == 1) {
					assertTrue(square.getIsOpen());
				} else {
					assertFalse(square.getIsOpen());
				}
			}
		}
	}
	
	/**
	 * TDD. Caja Negra, Se utiliza un mock de board. Crea un board 3x3 con una bomba en la esquina
	 * inferior derecha. Abre la square bomba lo que hace que ninguna otra square tenga que abrirse.
	 * Respecto a la particion comentada anteriormente, en este se comprueba el segundo tipo
	 */
	@Test
	public void testCasellesBuidesAdjacentsObertesClicantBomba() {
		MockBoard board = new MockBoard();
		board.col = 3;
		board.rows = 3;
		Square[][] squares = createBoard(board, 3, 3);
		squares[2][2].setBomb();
		board.addBoard(squares);
		board.openSquare(2, 2);
		for(int i = 0; i<3; i++) {
			for(int j = 0; j<3; j++) {
				Square square = board.getSquare(i, j);
				if(i == 2 && j == 2) {
					assertTrue(square.getIsOpen());
				} else {
					assertFalse(square.getIsOpen());
				}
			}
		}
	}
	
	/*
	 * Testos que comprueban el método de añadir banderas
	 */
	
	/**
	 * TDD. Caja Negra. Comprueba que al llamar el metodo de cambiar bandera (changeIsFlagged), este
	 * haga el canvio de estado.
	 */
	@Test
	public void testSetBandera() {
		Square square = new Square();
		assertFalse(square.isFlagged());
		square.changeIsFlagged();
		assertTrue(square.isFlagged());
		square.changeIsFlagged();
		assertFalse(square.isFlagged());
	}
	
	/**
	 * TDD. Caja Negra. Comprueba que al llamar el metodo de cambiar bandera (changeIsFlagged), en una 
	 * Square abierta, esta no cambie de estado. Podriamos decir que forma parte de la particion equivalente
	 * donde los datos de entrada son Squares abiertas y cerradas.
	 */
	@Test
	public void testFlagAnOpenSquare() {
		Square square = new Square();
		assertFalse(square.isFlagged());
		square.open();
		square.changeIsFlagged();
		assertFalse(square.isFlagged());
	}
	
	/**
	 * TDD. Caja Negra. Comprueba que al llamar el metodo de abrir en una Square con bandera, esta
	 * no se abra.
	 */
	@Test
	public void testOpenFlaggedSquare() {
		Square square = new Square();
		square.changeIsFlagged();
		square.open();
		assertFalse(square.getIsOpen());
	}
	
	/**
	 * TDD. Caja Negra. Comprueba que al llamar el metodo de abrir en una Square bomba con bandera, esta
	 * no se abra.
	 */
	@Test
	public void testOpenFlaggedBomb() {
		Square square = new Square();
		square.setBomb();
		square.changeIsFlagged();
		square.open();
		assertFalse(square.getIsOpen());
	}
	
	/**
	 * TDD. Caja Negra. Se utiliza un mock de board. Crea un board 3x3 con una bomba en la esquina
	 * inferior derecha. Añadimos una bandera en la casilla central. Abre la square de la esquina superior izquierda
	 * lo que hace que se abran todas menos la bomba y la casilla con bandera
	 */
	@Test
	public void testOpenEmptySquareInBoardWithFlag() {
		MockBoard board = new MockBoard();
		board.col = 3;
		board.rows = 3;
		Square[][] squares = createBoard(board, 3, 3);
		squares[2][2].setBomb();
		squares[1][1].changeIsFlagged();
		board.addBoard(squares);
		board.openSquare(0, 0);
		for(int i = 0; i<3; i++) {
			for(int j = 0; j<3; j++) {
				Square square = board.getSquare(i, j);
				if(i == 2 && j == 2) {
					assertFalse(square.getIsOpen());
				} else if (i == 1 && j == 1) {
					assertFalse(square.getIsOpen());
				} else {
					assertTrue(square.getIsOpen());
				}
			} 
		}
	}
	
	/**
	 * TDD. Caja Negra. Se utiliza un mock de board. Crea un board 3x3 con una bomba en la esquina
	 * inferior derecha. Añadimos una bandera en la casilla izquierda superior. Abre la square de la esquina superior izquierda
	 * lo que hace que ninguna casilla se abra
	 */
	@Test
	public void testOpenEmptySquareFlaggedInBoard() {
		MockBoard board = new MockBoard();
		board.col = 3;
		board.rows = 3;
		Square[][] squares = createBoard(board, 3, 3);
		squares[2][2].setBomb();
		squares[0][0].changeIsFlagged();
		board.addBoard(squares);
		board.openSquare(0, 0);
		for(int i = 0; i<3; i++) {
			for(int j = 0; j<3; j++) {
				Square square = board.getSquare(i, j);
				assertFalse(square.getIsOpen());
			} 
		}
	}
	
	/*
	 * Conjunto de tests que comprueban el estado de las Squares.
	 * En estos tests tenemos como particion equivalente los distintos tipos de squares:
	 * Sin abrir, abierto bomba, abierto valor y bandera como tipo de entrada. De salida
	 * deben generarse los mismos tipos 
	 */
	
	/**
	 * TDD. Caja Negra. Crea una Square bomba y se comprueba que esta devuelva la enumeracion
	 * correcta.
	 */
	@Test
	public void testGetStatusBomb() {
		Square s = new Square();
		s.setBomb();
		SquareStatus real = s.getStatus();
		SquareStatus aux = SquareStatus.NOT_OPEN;
		assertEquals(aux, real);
		s.open();
		real = s.getStatus();
		aux = SquareStatus.BOMB;
		assertEquals(aux,real);
	}
	
	
	/**
	 * TDD. Caja Negra. Crea una Square normal y se le añade una bomba, se comprueba que esta devuelva la enumeracion
	 * correcta.
	 */
	@Test
	public void testGetStatusFlagged() {
		Square s = new Square();
		s.setBomb();
		SquareStatus real = s.getStatus();
		SquareStatus aux = SquareStatus.NOT_OPEN;
		assertEquals(aux, real);
		s.changeIsFlagged();
		real = s.getStatus();
		aux = SquareStatus.FLAGGED;
		assertEquals(aux,real);
	}
	
	/**
	 * TDD. Caja Negra. Crea una Square normal y se abre, se comprueba que esta devuelva la enumeracion
	 * correcta.
	 */
	@Test
	public void testGetStatusOpen() {
		Square s = new Square();
		s.open();
		SquareStatus real = s.getStatus();
		SquareStatus aux = SquareStatus.VALUE;
		assertEquals(aux, real);
	}
	
	/**
	 * Caja Blanca. Se utiliza un mock de board. Tipo decicision coverage. Se comprueba que se pasen
	 * por todas las decisiones en la funcionalidad de devolver su estado.
	 */
	@Test
	public void testGetStatusDecisionCoverage() {
		MockBoard board = new MockBoard();
		board.col = 3;
		board.rows = 3;
		Square[][] squares = createBoard(board, 3, 3);
		squares[2][2].setBomb();
		squares[0][0].changeIsFlagged();
		board.addBoard(squares);
		board.openSquare(1, 0);
		assertEquals(SquareStatus.FLAGGED, squares[0][0].getStatus());
		assertEquals(SquareStatus.VALUE, squares[1][0].getStatus());
		assertEquals(SquareStatus.NOT_OPEN, squares[2][2].getStatus());
		board.openSquare(2, 2);
		assertEquals(SquareStatus.BOMB, squares[2][2].getStatus());
	}
	
	/**
	 * Caja Blanca. Se utiliza un mock de board. Tipo decicision + condition coverage. Se comprueba
	 * que se pasen por todas las condiciones posibles en la funcionalidad de abrir una casilla.
	 * Para eso creamos un tablero 3x3 añadiendo una bomba en la esquina inferior derecha y abrimos
	 * la casilla superior izquierda. Esto hace que se pasen por todas las coniciones
	 */
	@Test
	public void testOpenDecisionCoverage() {
		Square s = new Square();
		s.open();
		MockBoard board = new MockBoard();
		boolean[][] results = {{false, true, true}, {true, true, true}, {true, true, true}};
		int[][] values = {{0, 0, 0}, {0, 1, 1}, {0, 1, -1}};
		board.col = 3;
		board.rows = 3;
		Square[][] squares = createBoard(board, 3, 3);
		squares[2][2].setBomb();
		squares[0][0].changeIsFlagged();
		board.addBoard(squares);
		board.openSquare(1, 0);
		board.openSquare(2, 2);
		for(int i = 0; i<3; i++) {
			for(int j = 0; j<3; j++) {
				Square square = board.getSquare(i, j);
				assertEquals(results[i][j], square.getIsOpen());
				assertEquals(values[i][j], square.getValue());
			} 
		}
	}
	
	
}
