package core;

import java.util.Random;

public class BoardR implements Board{
	private int rows;
	private int cols;
	private int nBombs;
	private boolean initialized = false;
	private Square[][] board;
	private int pendingSquares;
	
	BoardR(int rows, int cols, int nBombs){
		this.rows = rows;
		this.cols = cols;
		this.nBombs = nBombs;
		this.board = new Square[rows][cols];
		for(int it = 0; it < rows; it++) {
			for(int jt = 0; jt < cols; jt++) {
				board[it][jt] = new Square();
			}
		}
		int maxBombs = (rows*cols)-1;
		if(this.nBombs > maxBombs)
			this.nBombs = maxBombs;
		this.pendingSquares = (rows*cols)-nBombs;
	}
	
	public int getRows() {
		return rows;
	}
	
	public int getCols() {
		return cols;
	}
	
	public int getNBombs() {
		return nBombs;
	}
	
	public boolean getInitialized() {
		return initialized;
	}
	
	public void initialize(int i, int j, long seed) {
		initialized = true;
		Random rand;
		if(seed != 0) {
			rand = new Random(seed);
		}
		else {
			rand = new Random();
		}
		/*int maxBombs = (rows*cols)-1;
		if(nBombs > maxBombs)
			nBombs = maxBombs;*/
		for (int it = 0; it < nBombs; it++) {
			int row = rand.nextInt(rows);
			int col = rand.nextInt(cols);
			if((row == i) && (col == j)) {
				it--;
			}
			else{
				if(!board[row][col].setBomb()) {
					it--;
				}
			}
		}
		
	}
	
	public void openSquare(int i, int j) {//TODO:
		if(!getInitialized()) {
			initialize(i,j, 0);
		}
		if(board[i][j].getBoard() == null)
			board[i][j].setBoard(this);
		board[i][j].open();
		/*SquareStatus aux = SquareStatus.VALUE;
		if(getStatus(i,j) == aux)
			pendingSquares--;*/
	}
	
	public SquareStatus getStatus(int i, int j) {
		SquareStatus aux = board[i][j].getStatus();
		return aux;
	}
	
	public Square getSquare(int i, int j) { 
		return board[i][j];
	}
	public boolean getSquareIsOpen(int i, int j) {
		return board[i][j].getIsOpen();
	}
	
	public Square[][] getBoard(){
		return board;
	}
	
	public int getPendingSquares() {
		return pendingSquares;
	}
	public void changeFlag(int i, int j) {
		board[i][j].changeIsFlagged();
	}
	
	public void minusPendingSquares() {
		pendingSquares--;
	}
}
