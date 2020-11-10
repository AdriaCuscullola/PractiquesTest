import controller.Game;
import view.RView;

public class Main {
	public static void main(String[] args) {
		Game game = new Game(30, 30, 99);
		RView view = new RView(30, 30, game);
		game.setView(view);
	}
}
