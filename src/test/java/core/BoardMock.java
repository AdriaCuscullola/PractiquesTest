package core;

public class BoardMock implements Board {
	private Square[][] board;
	
	public void addBoard(Square[][] board) {
		this.board = board;
	}
}
