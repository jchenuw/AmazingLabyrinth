public class GameController {

	private GameDisplay gameDisplay;
	private Game game;

	public GameController(GameDisplay gameDisplay) {
		this.gameDisplay = gameDisplay;
		this.game = gameDisplay.getGame();
	}
}
