package view;

import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

import controller.Game;
import controller.GameInterface;
import core.Square;
import view.View;

public class MockView implements View{
	@Override
	public void finish(boolean guanyat) {
		// TODO Auto-generated method stub
		
	}
	
	public void reset() {
		this.game.resetGame();
	}
	
	private Game game;
	
	public MockView(Game game){
		this.game = game;
	}
	
	@Override
	public int getRows() {
		// TODO Auto-generated method stub
		return 12;
	}

	@Override
	public int getCols() {
		// TODO Auto-generated method stub
		return 10;
	}

	@Override
	public JButtonExtend[][] getButtons() {
		// TODO Auto-generated method stub
		return null;
	}

	public void leftClick() {
		JButtonExtend buttonToSimulateClicking = new JButtonExtend(2,2, game);		
		MouseEvent me = new MouseEvent(buttonToSimulateClicking, 0, 0, 0, 100, 100, 1, false, 1); //es necessari ficar 3 per a right click i 1 per a left click.
		
		game.mouseClicked(me);
	}
	
	public void rightClick(){
		JButtonExtend buttonToSimulateClicking = new JButtonExtend(2,2, game);		
		MouseEvent me = new MouseEvent(buttonToSimulateClicking, 0, 0, 0, 100, 100, 1, false, 3); //es necessari ficar 3 per a right click i 1 per a left click.
				
		game.mouseClicked(me);
	}

	@Override
	public void printaTauler(Square[][] tauler) {
		// TODO Auto-generated method stub
		
	}

}
