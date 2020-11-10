package view;

import controller.GameInterface;
import core.Square;
import core.SquareStatus;

public class RView implements View {
	private int rows;
	private int cols;
	private GameInterface controller;
	private JButtonExtend[][] buttons;
	
	public void proxyPrintaBoto(Square square, int row, int col) { printaBoto(square, row, col); }
	
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

	@Override
	public void finish(boolean guanyat) {
		// TODO Auto-generated method stub
		
	}

	public JButtonExtend[][] getButtons() {
		return buttons;
	}

	@Override
	public void printaTauler(Square[][] tauler) {
		for(int i = 0; i < tauler.length; i++) {
			for(int j = 0; j < tauler[0].length; j++) {
				printaBoto(tauler[i][j], i, j);
			}
		}
		
	}
	
	private void printaBoto(Square square, int row, int col) {
		switch(square.getStatus()) {
		case NOT_OPEN:
			buttons[row][col].setText("");
			buttons[row][col].setEnabled(true);
			break;
		case BOMB:
			buttons[row][col].setText("X");
			buttons[row][col].setEnabled(false);
			break;
		case FLAGGED:
			buttons[row][col].setText("F");
			buttons[row][col].setEnabled(true);
			break;
		default:
			String text = square.getValue() == 0 ? "" : square.getValue()+"";
			buttons[row][col].setText(text);
			buttons[row][col].setEnabled(false);
		}
	}
	
	
}
