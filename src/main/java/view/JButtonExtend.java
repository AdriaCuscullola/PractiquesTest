package view;

import javax.swing.JButton;

import controller.GameInterface;

public class JButtonExtend extends JButton{
	private int row;
	private int col;
	private GameInterface controller;
	
	public JButtonExtend(int row, int col, GameInterface controller) {
		super();
		this.row = row;
		this.col = col;
		this.controller = controller;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public GameInterface getController() {
		return controller;
	}
	
	
}
