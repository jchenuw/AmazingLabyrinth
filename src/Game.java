import java.util.ArrayList;

public class Game {

    // Game board
    private Board board;

    // Players of the game
	private ArrayList<Player> players;
	private int numPlayers;

	// Constructor
	public Game() {
		// create new board
		board = new Board();
	}
}
