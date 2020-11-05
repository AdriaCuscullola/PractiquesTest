package controller;

import core.BoardR;

public class Game {
	
	Game() {
		//View v = new View(12, 10, this);	//el tauler per defecte serà 12 X 10.
		BoardR b = new BoardR(12, 10, 20);
	}
}
