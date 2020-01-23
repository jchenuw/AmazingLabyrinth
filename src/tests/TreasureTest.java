package tests;

import model.LTile;
import model.TTile;
import model.Tile;
import model.Treasure;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TreasureTest {

	@Test
	void getTileOwnerTest() {
		Tile firstTile = new LTile(1, 3, 0);
		Tile differentTile = new TTile(1, 5, 3);

		Treasure treasure0 = new Treasure(0);
		Treasure treasure1 = new Treasure(1);

		firstTile.setTreasure(treasure0);
		differentTile.setTreasure(treasure0);
		firstTile.setTreasure(treasure1);

		Assertions.assertSame(treasure0.getTileOwner(), differentTile,
				"treasure0 owner should be differentTile");

		Assertions.assertSame(treasure1.getTileOwner(), firstTile,
				"treasure1 owner should be firstTile");
	}
}