import java.util.*;

public abstract class Player extends Piece {

	private String colour;
	private Stack<Card> hand;
	private Card currentCard;

	// Constructor
	public Player(int row, int col, String colour) {
		super(row, col);

		this.colour = colour;
	}

	/**
	 * Moves the player to a location
	 *
	 * @param row
	 * @param col
	 */
	public void moveTo(int row, int col) {
		setRow(row);
		setCol(col);
	}

	/**
	 * Draws the next card for player
	 */
	public void nextCard() {
		// remove obtained treasure
		hand.pop();

		// go to next treasure to collect
		currentCard = hand.peek();
	}

	/**
	 * Checks if the player has won by checking if player still has cards left
	 * @return If the player has won
	 */
	public boolean isWon() {
		return(hand.isEmpty());
	}
}
