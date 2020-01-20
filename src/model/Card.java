package model;

public class Card {

	// model.Treasure to collect for this card
	private Treasure treasure;

	// Constructor
	public Card(Treasure treasure) {
		this.treasure = treasure;
	}

	// Setters and getters
	public Treasure getTreasure() {
		return this.treasure;
	}

}
