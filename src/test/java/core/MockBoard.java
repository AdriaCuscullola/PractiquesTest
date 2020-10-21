package core;

public class MockBoard implements Board {
	private Square[][] board;
	public int rows;
	public int col;
	
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
		return rows;
	}

	@Override
	public int getCol() {
		return col;
	}
}
