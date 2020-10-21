package core;

public interface Board {
	public void openSquare(int i, int j);
	public Square getSquare(int i, int j);
	
	public int getRows();
	public int getCols();
	public int getNBombs();
	
	
	
	
}
