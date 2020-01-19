package model;

import java.util.Random;
import java.util.Stack;

public class Game {

	/**
	 * Enscapulation class to hold current turn data to enforce rules
	 */
	public class TurnState {
		private int playerTurn;
		private boolean insertedTile;
		private boolean moved;
		private boolean gameEnd = false;

		private TurnState(int playerTurn, boolean insertedTile, boolean moved) {
			this.playerTurn = playerTurn;
			this.insertedTile = insertedTile;
			this.moved = moved;
		}
		// Getters
		public int getPlayerTurn() {
			return this.playerTurn;
		}
		public void setPlayerTurn(int playerTurn) {
			this.playerTurn = playerTurn;
		}

		public boolean hasInsertedTile() {
			return this.insertedTile;
		}
		public void setInsertedTile(boolean insertedTile) {
			this.insertedTile = insertedTile;
		}

		public boolean hasMoved() {
			return moved;
		}
		public void setMoved(boolean moved) {
			this.moved = moved;
		}

		public boolean hasGameEnd() {
			return this.gameEnd;
		}
	}

    // model.Game board
    private Board board;

    // model.Game cards to distribute to players
	private Card[] cards;

    // Players of the game
	private Player[] players;
	private final int numPlayers;

	// Treasures
	private Treasure[] treasures;

	// Extra insertable tile
	private Tile extraTile;

	private TurnState turnState;

	/**
	 * Creates a new game that initializes a new board
	 *
	 * @param numPlayers numbers of players in the game
	 */
	public Game(int numPlayers) {
		this.numPlayers = numPlayers;

		init();

		turnState = new TurnState(1, false, false);
	}

	public void endGame() {
		turnState.gameEnd = true;
	}

	public void nextTurn() {
		turnState.playerTurn++;

		if(turnState.playerTurn > players.length) {
			turnState.playerTurn = 0;
		}

		turnState.insertedTile = false;
		turnState.moved = false;
	}

	/**
	 * Initializes the game by setting up players and game pieces
	 */
	public void init() {

		// setup pre-game components
		setupPlayers();
		setupCardsAndTreasures();
		distributeCards();

		// initialize new board
		board = new Board(this.players, this.treasures);
		extraTile = board.getExtraTile();

	}

	/**
	 * Initializes players for the game
	 */
	private void setupPlayers() {
		players = new Player[numPlayers];

		// preset player data
		String[] colours =  {"Red", "Blue", "Green", "Purple"};
		int[][] startingPoints = {{0,0}, {6, 0}, {0, 6}, {6, 6}};

		for(int i = 0; i < numPlayers; i++) {
			players[i] = new HumanPlayer(startingPoints[i][0], startingPoints[i][1], colours[i]);
		}
	}

	/**
	 * Initializes treasures and treasure cards
	 */
	private void setupCardsAndTreasures() {
		// create treasures and treasure cards
		treasures = new Treasure[Treasure.TREASURE_AMOUNT];
		cards = new Card[Treasure.TREASURE_AMOUNT];

		for(int i = 0; i < Treasure.TREASURE_AMOUNT; i++) {
			treasures[i] = new Treasure(i);
			cards[i] = new Card(treasures[i]);
		}
	}

	private void distributeCards() {
		Card[] shuffledCards = new Card[cards.length];

		Random rand = new Random();

		// Fisher-Yates shuffle
		for(int i = shuffledCards.length - 1; i > 0; i--) {
			int index = rand.nextInt(i + 1);

			// swap
			Card temp = shuffledCards[index];
			shuffledCards[index] = shuffledCards[i];
			shuffledCards[i] = temp;
		}

		int cardsPerPlayer = 5;
		int cardCounter = 0;

		for(int i = 0; i < players.length; i++) {
			Stack<Card> newHand = new Stack<Card>();

			for(int j = 0; j < cardsPerPlayer; j++) {
				newHand.push(shuffledCards[cardCounter]);
				cardCounter++;
			}

			players[i].setHand(newHand);
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

	public TurnState getTurnState() {
		return this.turnState;
	}

}
