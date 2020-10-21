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

	@Override
	public int getRows() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getCols() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNBombs() {
		// TODO Auto-generated method stub
		return 0;
	}
}
