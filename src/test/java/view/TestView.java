package view;

import static org.junit.Assert.*;

import org.junit.Test;

import controller.GameInterface;
import controller.MockController;

public class TestView {

	@Test
	public void testConstructor() {
		GameInterface mock = new MockController();
		View view = new RView(12, 10, mock);
		assertEquals(12, view.getRows());
		assertEquals(10, view.getCols());
		
		JButtonExtend[][] buttons = view.getButtons();
		for(int i = 0; i < 12; i++) {
			for(int j = 0; j < 10; j++) {
				assertEquals(true, buttons[i][j].isEnabled());
				assertEquals("", buttons[i][j].getText());
				assertEquals(i, buttons[i][j].getRow());
				assertEquals(j, buttons[i][j].getCol());
			}
		}
		assertEquals(12, buttons.length);
		assertEquals(10, buttons[0].length);
	}
	
	
	@Test
	public void testButtonConstructor() {
		int row = 1;
		int col = 2;
		GameInterface mock = new MockController();
		JButtonExtend button = new JButtonExtend(row, col, mock);
		assertEquals(1, button.getRow());
		assertEquals(2, button.getCol());
		assertEquals(mock, button.getController());
		
		assertEquals(mock, button.getMouseListeners()[1]);
	}

}
