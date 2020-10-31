package core;

import java.util.Random;

public class BoardR implements Board{
	private int rows;
	private int cols;
	private int nBombs;
	private boolean initialized = false;
	private Square[][] board;

	
	BoardR(int rows, int cols, int nBombs){
		this.rows = rows;
		this.cols = cols;
		this.nBombs = nBombs;
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
	
	public void initialize(int i, int j) {
		Random rand = new Random();
		for (int it = 0; it < nBombs; it++) {
			int row = rand.nextInt(rows);
			int col = rand.nextInt(cols);
			if(!board[row][col].setBomb()) {
				it--;
			}
		}
		
	}
	
	public void openSquare(int i, int j) {//TODO:
		initialized = true;
		initialize(i,j);
	}
	
	
	
	public Square getSquare(int i, int j) { //TODO: getSquareisOpen
		Square s = new Square();
		return s;
	}
}
