import controller.Game;
import view.RView;

public class Main {
	public static void main(String[] args) {
		Game game = new Game(12, 10, 15);
		RView view = new RView(12, 10, game);
		game.setView(view);
	}
}
