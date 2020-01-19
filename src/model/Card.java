package model;

public class Card {

	// model.Treasure to collect for this card
	private Treasure treasure;

	// If the treasure on the card has been collected
	private boolean isCollected = false;


	// Constructor
	public Card(Treasure treasure) {
		this.treasure = treasure;
	}

	public void complete() {
		isCollected = true;
	}

	// Setters and getters
	public Treasure getTreasure() {
		return this.treasure;
	}

}
