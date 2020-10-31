package core;

public class Square {
	private boolean isOpen = false;
	private boolean isBomb = false;
	private int value = 0;
	private int row;
	private int column;
	private Board board;
	private boolean isFlagged;
	
	public void open() {
		if(!isOpen) {
			isOpen = true;
			if(!isBomb && board != null) {
				value = calcNearBombs();
				if(value == 0) {
					openAdjacents();
				}
			}
		}
	}
	
	private void openAdjacents() {
		int minRowPosicion = row-1>=0 ? row-1 : 0;
		int maxRowPosicion = row+1<board.getRows() ? row+1 : board.getRows()-1;
		int minColPosicion = column-1>=0 ? column-1 : 0;
		int maxColPosicion = column+1<board.getCols() ? column+1 : board.getCols()-1;
		for(int i = minRowPosicion; i<=maxRowPosicion; i++) {
			for(int j = minColPosicion; j<=maxColPosicion; j++) {
				board.openSquare(i, j);
			}
		}
	}
	
	private int calcNearBombs() {
		int newValue = 0;
		int minRowPosicion = row-1>=0 ? row-1 : 0;
		int maxRowPosicion = row+1<board.getRows() ? row+1 : board.getRows()-1;
		int minColPosicion = column-1>=0 ? column-1 : 0;
		int maxColPosicion = column+1<board.getCols() ? column+1 : board.getCols()-1;
		for(int i = minRowPosicion; i<=maxRowPosicion; i++) {
			for(int j = minColPosicion; j<=maxColPosicion; j++) {
				if(board.getSquare(i, j).isBomb) {
					newValue++;
				}
			}
		}
		return newValue;
	}
	
	
	public boolean getIsOpen() {
		return isOpen;
	}
	
	public boolean setBomb() {
		if(isBomb == false) {
			isBomb = true;
			value = -1;
			return true;
		}
		else
			return false;
	}
	
	public boolean getIsBomb() {
		return isBomb;
	}
	
	public void setBoard(Board newBoard) {
		board = newBoard;
	}
	
	public void addBoardPosition(int row, int column) {
		this.row = row;
		this.column = column;
	}
	
	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}
	
	public int getValue() {
		return this.value;
	}
	
	public boolean isFlagged() {
		return isFlagged;
	}
	
}
