package core;

public class BoardR implements Board{
	private int rows;
	private int cols;
	private int nBombs;
	private boolean initialized = false;
	
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
	
	public void openSquare(int i, int j) {//TODO:
		initialized = true;
		
	}
	
	
	
	public Square getSquare(int i, int j) { //TODO: getSquareisOpen
		Square s = new Square();
		return s;
	}
}
