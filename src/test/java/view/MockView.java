package view;

import java.awt.event.MouseEvent;

import javax.swing.JButton;

import controller.Game;
import controller.GameInterface;
import view.View;

public class MockView implements View{
	private GameInterface game;
	
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

	public void click() {
		JButtonExtend buttonToSimulateClicking = new JButtonExtend(2,2, game);
		
		MouseEvent me = new MouseEvent(buttonToSimulateClicking, 0, 0, 0, 100, 100, 1, false);
		//ara toca cridar al click de Game amb el Mousevent.
		game.mouseClicked(me);
		
	}

}
