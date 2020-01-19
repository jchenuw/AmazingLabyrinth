package tests;

import model.*;
import org.junit.jupiter.api.*;

import java.util.Stack;

public class PlayerTest {

	Treasure[] treasures = new Treasure[5];
	Card[] cards = new Card[5];
	Stack<Card> hand = new Stack<Card>();

	private void setupDeck() {
		for(int i = 0; i < 5; i++) {
			treasures[i] = new Treasure(i);
			cards[i] = new Card(treasures[i]);

			hand.push(cards[i]);
		}
	}

	@Test
	void goToNextCardTest() {
		setupDeck();

		Player player = new HumanPlayer(1, 1, "Green");

		player.setHand(hand);

		Assertions.assertEquals(player.getCurrentCard().getTreasure(), treasures[4],
				"Objective treasure does not match actual treasure 5");

		player.goToNextCard();
		player.goToNextCard();

		Assertions.assertEquals(player.getCurrentCard().getTreasure(), treasures[2],
				"Objective treasure does not match actual treasure 3");
	}

	@Test
	void hasCollectedAllTest() {
		setupDeck();

		Player player = new HumanPlayer(1, 1, "Green");

		Assertions.assertEquals(player.hasCollectedAll(), true);

		player.setHand(hand);
		player.goToNextCard();

		Assertions.assertEquals(player.hasCollectedAll(), false);

		player.goToNextCard();
		player.goToNextCard();
		player.goToNextCard();
		player.goToNextCard();

		Assertions.assertEquals(player.hasCollectedAll(), true);
	}

	@Test
	void hasWonTest() {
		setupDeck();

		Player player = new HumanPlayer(1, 1, "Green");

		Assertions.assertEquals(player.hasWon(), false);

		player.setReturnedHome(true);

		Assertions.assertEquals(player.hasWon(), true);

		player.setReturnedHome(false);
		player.setHand(hand);

		for(int i = 0; i < 5; i++) {
			player.goToNextCard();
		}

		Assertions.assertEquals(player.hasWon(), false);

		player.setReturnedHome(true);

		Assertions.assertEquals(player.hasWon(), true);
	}

}