package core;

public class MockBoard implements Board {
	private Square[][] board;
	
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
}
