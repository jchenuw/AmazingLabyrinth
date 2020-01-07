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

	public void moveTo(int row, int col) {
		setRow(row);
		setCol(col);
	}

	public void nextCard() {
		// remove obtained treasure
		hand.pop();

		// go to next treasure to collect
		currentCard = hand.peek();
	}
}
