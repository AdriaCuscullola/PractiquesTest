package client;

import controller.Game;
import controller.GeneradorRandom;
import controller.MockTestCase;

public class Main {
	public static void main(String[] args) {
		Game g = new Game();
		GeneradorRandom random = new MockTestCase();
		g.setRandom(random);
		g.start();
	}
}
