import javax.swing.*;

public class Card extends JLabel {

	// Treasure to collect for this card
	private Treasure cardTreasure;

	// If the treasure on the card has been collected
	private boolean isCollected = false;

	// Owner of this card
	private Player owner = null;

	// Constructor
	public Card(Treasure cardTreasure) {
		this.cardTreasure = cardTreasure;
	}

	public boolean isMatch(Treasure treasure) {
		if(treasure == cardTreasure) {
			return true;
		}

		return false;
	}

	public void complete() {
		isCollected = true;
	}

	// Setters and getters
	public void setOwner(Player player) {
		this.owner = player;
	}
	public Player getOwner() {
		return owner;
	}

}
