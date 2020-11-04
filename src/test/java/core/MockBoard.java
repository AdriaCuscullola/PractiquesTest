package core;

public class MockBoard implements Board {
	private Square[][] board;
	public int rows;
	public int col;
	
	@Override
	public int getPendingSquares() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public SquareStatus getStatus(int i, int j) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void changeFlag(int i, int j) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Square[][] getBoard() {
		// TODO Auto-generated method stub
		return null;
	}

	public void addBoard(Square[][] board) {
		this.board = board;
	}

	@Override
	public void openSquare(int i, int j) {
		board[i][j].open();
	}

	@Override
	public Square getSquare(int i, int j) {
		return board[i][j];
	}

	@Override
	public int getRows() {
		// TODO Auto-generated method stub
		return rows;
	}

	@Override
	public int getNBombs() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean getInitialized() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getCols() {
		return col;
	}
}
