import java.util.ArrayList;

public class Game {

    // Game board
    private Board board;

    // Game cards to distribute to players
	private Card[] cards;

    // Players of the game
	private Player[] players;
	private final int numPlayers;

	// Treasures
	private Treasure[] treasures;

	// Extra insertable tile
	private Tile extraTile;

	/**
	 * Creates a new game that initializes a new board
	 *
	 * @param numPlayers numbers of players in the game
	 */
	public Game(int numPlayers) {
		this.numPlayers = numPlayers;

		init();
	}

	/**
	 * Initializes the game by setting up players and game pieces
	 */
	public void init() {

		// setup pre-game components
		setupPlayers();
		setupCardsAndTreasures();

		// initialize new board
		board = new Board(this.players, this.treasures);
	}

	/**
	 * Initializes players for the game
	 */
	public void setupPlayers() {
		// preset player data
		String[] colours =  {"Red", "Blue", "Green", "Purple"};
		int[][] startingPoints = {{0,0}, {6, 0}, {0, 6}, {6, 6}};

		// generate new players
		players = new Player[numPlayers];

		for(int i = 0; i < numPlayers; i++) {
			players[i] = new HumanPlayer(startingPoints[i][0], startingPoints[i][1], colours[i]);
		}
	}

	/**
	 * Initializes treasures and treasure cards
	 */
	public void setupCardsAndTreasures() {
		// create treasures and treasure cards
		treasures = new Treasure[Treasure.TREASURE_AMOUNT];
		cards = new Card[Treasure.TREASURE_AMOUNT];

		for(int i = 0; i < Treasure.TREASURE_AMOUNT; i++) {
			treasures[i] = new Treasure(i);
			cards[i] = new Card(treasures[i]);
		}
	}

	// Getters
	public Board getBoard() {
		return this.board;
	}

	public Treasure[] getTreasure() {
		return this.treasures;
	}

	public Card[] getCards() {
		return this.cards;
	}

	public Tile getExtraTile() {
		return this.extraTile;
	}

}
