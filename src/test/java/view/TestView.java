package view;

import static org.junit.Assert.*;

import org.junit.Test;

import controller.Controller;
import controller.MockController;

public class TestView {

	@Test
	public void testConstructor() {
		Controller mock = new MockController();
		View view = new RView(12, 10, mock);
		assertEquals(12, view.getRows());
		assertEquals(10, view.getCols());
	}

}
