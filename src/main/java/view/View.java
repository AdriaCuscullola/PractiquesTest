package view;

import core.Square;

public interface View {
	public int getRows();

	public int getCols();

	public JButtonExtend[][] getButtons();
	
	public void printaTauler(Square[][] tauler);
	
	public void finish(boolean guanyat);
	
}
