package tests;

import model.Card;
import model.HumanPlayer;
import model.Player;
import model.Treasure;
import org.junit.jupiter.api.*;

import java.util.Stack;

class PlayerTest {

	Player player;
	Treasure[] treasures = new Treasure[5];
	Card[] cards = new Card[5];
	Stack<Card> hand = new Stack<Card>();

	@BeforeEach
	private void setupDeck() {
		player = new HumanPlayer(1, 1, "Green");
		for(int i = 0; i < 5; i++) {
			treasures[i] = new Treasure(i);
			cards[i] = new Card(treasures[i]);

			hand.push(cards[i]);
		}
	}

	@Test
	void goToNextCardTest() {
		player.setHand(hand);

		Assertions.assertSame(player.getCurrentCard().getTreasure(), treasures[4],
				"Objective treasure does not match actual treasure 5");

		// Skip 2 treasures
		player.goToNextCard();
		player.goToNextCard();

		Assertions.assertSame(player.getCurrentCard().getTreasure(), treasures[2],
				"Objective treasure does not match actual treasure 3");
	}

	@Test
	void hasCollectedAllTest() {
		Assertions.assertTrue(player.hasCollectedAll());

		player.setHand(hand);
		player.goToNextCard();

		Assertions.assertFalse(player.hasCollectedAll());

		// Empty player's hand
		player.goToNextCard();
		player.goToNextCard();
		player.goToNextCard();
		player.goToNextCard();

		Assertions.assertTrue(player.hasCollectedAll());
	}

	@Test
	void hasWonTest() {
		Assertions.assertFalse(player.hasWon());

		player.setReturnedHome(true);

		Assertions.assertTrue(player.hasWon());

		player.setReturnedHome(false);
		player.setHand(hand);

		for(int i = 0; i < 5; i++) {
			player.goToNextCard();
		}

		Assertions.assertFalse(player.hasWon());

		player.setReturnedHome(true);

		Assertions.assertTrue(player.hasWon());

	}

}