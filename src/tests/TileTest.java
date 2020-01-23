package tests;

import model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TileTest {

	private Player[] players = new Player[4];
	private Tile lTile;
	private Tile tTile;
	private Tile iTile;

	@BeforeEach
	void setUp() {
		lTile = new LTile(0, 0, 0);
		tTile = new TTile(4,4,0);
		iTile = new ITile(5, 7, 0);

		String[] colours =  {"Red", "Blue", "Green", "Purple"};
		int[][] startingPoints = {{0,0}, {6, 0}, {0, 6}, {6, 6}};

		for(int i = 0; i < players.length; i++) {
			players[i] = new HumanPlayer(startingPoints[i][0], startingPoints[i][1], colours[i]);
		}
	}

	@AfterEach
	void cleanup() {
		lTile.removeAdjTiles();;
		lTile.removeAllPlayersOnTile();
		lTile.removeTreasure();

		tTile.removeAdjTiles();;
		tTile.removeAllPlayersOnTile();
		tTile.removeTreasure();

		iTile.removeAdjTiles();;
		iTile.removeAllPlayersOnTile();
		iTile.removeTreasure();

		for(int i = 0; i < players.length; i++) {
			players[i].setReturnedHome(false);
			players[i].setCurrentCard(null);
		}
	}

	@Test
	void adjTilesTest() {
		tTile.addAdjTile(lTile);
		tTile.addAdjTile(iTile);

		assertEquals(tTile.getAdjTiles().size(), 2);

		tTile.removeAdjTiles();

		assertEquals(tTile.getAdjTiles().size(), 0);
	}

	@Test
	void playersOnTileTest() {
		Treasure treasure0 = new Treasure(0);
		Treasure treasure1 = new Treasure(1);

		Card card0 = new Card(treasure0);
		Card card1 = new Card(treasure1);

		players[0].addToHand(card0);
		players[1].addToHand(card1);

		lTile.setTreasure(treasure0);

		// Add player who cannot collect lTile's treasure
		lTile.addPlayerOnTile(players[1]);
		assertEquals(lTile.getPlayersOnTile().size(), 1);
		assertTrue(lTile.hasTreasure());
		assertSame(players[0].getCurrentCard(), card0);

		// Add player who can collect lTile's treasure
		lTile.addPlayerOnTile(players[0]);
		lTile.addPlayerOnTile(players[1]);
		assertEquals(lTile.getPlayersOnTile().size(), 2);
		assertFalse(lTile.hasTreasure());
		assertNull(players[0].getCurrentCard());

		lTile.removePlayerOnTile(players[3]);
		lTile.removePlayerOnTile(players[0]);
		assertEquals(lTile.getPlayersOnTile().size(), 1);

		lTile.removeAllPlayersOnTile();
		assertEquals(lTile.getPlayersOnTile().size(), 0);
	}

	@Test
	void rotationAndOpeningsTest() {
		assertArrayEquals(lTile.getOpenings(), LTile.OPENINGS_BY_ORIENTATION[lTile.getOrientation()],
				"L-Tile orientation 0 openings are incorrect");
		assertArrayEquals(tTile.getOpenings(), TTile.OPENINGS_BY_ORIENTATION[tTile.getOrientation()],
				"T-tile orientation 0 openings are incorrect");
		assertArrayEquals(iTile.getOpenings(), ITile.OPENINGS_BY_ORIENTATION[iTile.getOrientation()],
				"I-tile orientation 0 openings are incorrect");

		// L-tile rotate to 270 degrees
		lTile.rotate();
		lTile.rotate();
		lTile.rotate();

		assertEquals(lTile.getOrientation(), 3);
		assertArrayEquals(lTile.getOpenings(), LTile.OPENINGS_BY_ORIENTATION[lTile.getOrientation()],
				"L-Tile orientation 3 openings are incorrect");

		// T-tile rotate to 180 degrees
		tTile.rotate();
		tTile.rotate();

		assertEquals(tTile.getOrientation(), 2);
		assertArrayEquals(tTile.getOpenings(), TTile.OPENINGS_BY_ORIENTATION[tTile.getOrientation()],
				"T-tile orientation 2 openings are incorrect");

		// I-tile rotate to 90 degrees
		iTile.rotate();

		assertEquals(iTile.getOrientation(), 1);
		assertArrayEquals(iTile.getOpenings(), ITile.OPENINGS_BY_ORIENTATION[iTile.getOrientation()],
				"I-tile orientation 1 openings are incorrect");
	}
}