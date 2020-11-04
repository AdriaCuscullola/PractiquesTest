package core;

public interface Board {
	public void openSquare(int i, int j);
	public Square getSquare(int i, int j);
	public int getRows();
	public int getCols();
	public int getNBombs();
	public boolean getInitialized();
	public Square[][] getBoard();
	public int getPendingSquares();
	public SquareStatus getStatus(int i, int j);
	public void changeFlag(int i, int j);
	public void initialize(int i, int j, long seed);
	public void minusPendingSquares();
	public boolean getSquareIsOpen(int i, int j);
}
