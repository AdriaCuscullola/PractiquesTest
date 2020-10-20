package core;

public class Square {
	private boolean isOpen = false;
	private boolean isBomb = false;
	
	
	public void open() {
		isOpen = true;
	}
	
	public boolean getIsOpen() {
		return isOpen;
	}
	
	public void setBomb() {
		isBomb = true;
	}
	
	public boolean getIsBomb() {
		return isBomb;
	}
	
}
