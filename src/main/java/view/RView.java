package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import controller.GameInterface;
import core.Square;
import core.SquareStatus;

public class RView implements View {
	private int rows;
	private int cols;
	private GameInterface controller;
	private JButtonExtend[][] buttons;
	private JFrame frame;
	private Container grid;
	private static final int[] ROWS_NUM = {8, 14, 20};
	private static final int[] COLS_NUM = {10, 18, 24};
	private int dificulty = 1;
	private static final int SIZE = 50;
	
	
	public void proxyPrintaBoto(Square square, int row, int col) { printaBoto(square, row, col); }
	protected void setController(GameInterface game) {
		this.controller = game;
	}
	
	
	public RView(GameInterface controller) {
		this.controller = controller;
		frame = new JFrame("Minesweeper");
        frame.setSize(100, 100);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void start() {
		int dificulty = showMessage("Selecciona una dificultat", "MineSweeper", JOptionPane.QUESTION_MESSAGE);
        controller.resetGame(dificulty);
        rows = ROWS_NUM[dificulty];
        cols = COLS_NUM[dificulty];
        frame.setSize(SIZE*cols, SIZE*rows);
        createButtons();
        frame.setVisible(true);
        this.dificulty = dificulty;
	}
	
	public RView(int rows, int cols, GameInterface controller) {
		this.rows = rows;
		this.cols = cols;
		this.controller = controller;
		frame = new JFrame("Minesweeper");
        frame.setSize(SIZE*cols, SIZE*rows);
        frame.setLayout(new BorderLayout());
		createButtons();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
	}
	
	protected int showMessage(String message, String title, int option) {
		int seleccio = JOptionPane.showOptionDialog(
				   frame,
				   message, 
				   title,
				   JOptionPane.YES_NO_CANCEL_OPTION,
				   option,
				   null,  
				   new Object[] { "facil", "nomal", "dificil" },
				   "normal");
		return seleccio<0 || seleccio>2 ? this.dificulty : seleccio;
	}
	
	private void createButtons() {
		grid = new Container();
        grid.setLayout(new GridLayout(rows, cols));
		buttons = new JButtonExtend[rows][cols];
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols; j++) {
				buttons[i][j] = new JButtonExtend(i, j, controller);
				grid.add(buttons[i][j]);
			}
		}
		frame.add(grid, BorderLayout.CENTER);
	}

	public int getRows() {
		return rows;
	}

	public int getCols() {
		return cols;
	}

	@Override
	public void finish(boolean guanyat) {
		int dificulty;
		if(guanyat) {
			dificulty = showMessage("Has guanyat", "Fi", JOptionPane.INFORMATION_MESSAGE);
		} else {
			dificulty = showMessage("Has perdut", "Fi", JOptionPane.ERROR_MESSAGE);
		}
		controller.resetGame(dificulty);
		rows = ROWS_NUM[dificulty];
        cols = COLS_NUM[dificulty];
        if(this.dificulty != dificulty && this.frame!=null) {
	        frame.setSize(SIZE*cols, SIZE*rows);
	        frame.remove(grid);
	        createButtons();
	        this.dificulty = dificulty;
        }
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
