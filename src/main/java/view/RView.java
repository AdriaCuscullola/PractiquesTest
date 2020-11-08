package view;

import controller.GameInterface;
import core.Square;

public class RView implements View {
	private int rows;
	private int cols;
	private GameInterface controller;
	private JButtonExtend[][] buttons;
	
	public RView(int rows, int cols, GameInterface controller) {
		this.rows = rows;
		this.cols = cols;
		this.controller = controller;
		createButtons();
	}
	
	private void createButtons() {
		buttons = new JButtonExtend[rows][cols];
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols; j++) {
				buttons[i][j] = new JButtonExtend(i, j, controller);
			}
		}
	}

	public int getRows() {
		return rows;
	}

	public int getCols() {
		return cols;
	}

	public JButtonExtend[][] getButtons() {
		return buttons;
	}

	@Override
	public void printaTauler(Square[][] tauler) {
		// TODO Auto-generated method stub
		
	}
	
	
}
