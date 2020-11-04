package view;

import controller.GameInterface;

public class RView implements View {
	private int rows;
	private int cols;
	private GameInterface controller;
	
	public RView(int rows, int cols, GameInterface controller) {
		this.rows = rows;
		this.cols = cols;
		this.controller = controller;
	}

	public int getRows() {
		return rows;
	}

	public int getCols() {
		return cols;
	}
	
}
