package core;

public class Square {
	private boolean isOpen = false;
	private boolean isBomb = false;
	private int value = 0;
	private int xPosition;
	private int yPosition;
	private Board board;
	
	public void open() {
		isOpen = true;
		if(!isBomb && board != null) {
			for(int i = -1; i<2; i++) {
				for(int j = -1; j<2; j++) {
					if(board.getSquare(xPosition-i, yPosition-j).isBomb) {
						value++;
					}
				}
			}
		}
	}
	
	public boolean getIsOpen() {
		return isOpen;
	}
	
	public void setBomb() {
		isBomb = true;
		value = -1;
	}
	
	public boolean getIsBomb() {
		return isBomb;
	}
	
	public void setBoard(Board newBoard) {
		board = newBoard;
	}
	
	public void addBoardPosition(int x, int y) {
		this.xPosition = x;
		this.yPosition = y;
	}
	
	public int getXPosition() {
		return xPosition;
	}

	public int getYPosition() {
		return yPosition;
	}
	
	public int getValue() {
		return this.value;
	}
	
	
}
