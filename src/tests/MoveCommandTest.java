package tests;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.MoveCommand;

import static org.junit.jupiter.api.Assertions.*;

class MoveCommandTest {

	private Player[] players = new Player[4];
	private Treasure[] treasures = new Treasure[24];
	private Tile[][] tiles = new Tile[7][7];

	@BeforeEach
	void setup() {
		String[] colours =  {"Red", "Blue", "Green", "Purple"};
		int[][] startingPoints = {{0,0}, {6, 0}, {0, 6}, {6, 6}};

		for(int i = 0; i < players.length; i++) {
			players[i] = new HumanPlayer(startingPoints[i][0], startingPoints[i][1], colours[i]);
		}

		for(int i = 0; i < treasures.length; i++) {
			treasures[i] = new Treasure(i);
		}

		for(int i = 0; i < tiles.length; i++) {
			for(int j = 0; j < tiles.length; j++) {
				tiles[i][j] = new TTile(i, j, 0);
			}
		}
	}
	@Test
	void isLegalOneTest() {
		Board board = new Board(players, treasures);
		board.setTiles(tiles);

		tiles[0][0].addAdjTile(tiles[0][1]);
		tiles[0][1].addAdjTile(tiles[1][1]);
		tiles[1][1].addAdjTile(tiles[1][2]);
		tiles[1][2].addAdjTile(tiles[1][3]);

		tiles[0][0].addAdjTile(tiles[6][6]);
		tiles[6][6].addAdjTile(tiles[5][2]);

		MoveCommand move0 = new MoveCommand(board, players[0], 1, 3);
		MoveCommand move1 = new MoveCommand(board, players[0], 6, 6);
		MoveCommand move2 = new MoveCommand(board, players[3], 5, 2);
		MoveCommand move3 = new MoveCommand(board, players[3], 0, 0);

		assertTrue(move0.isLegal());
		assertTrue(move1.isLegal());
		assertTrue(move2.isLegal());
		assertFalse(move3.isLegal());

	}
}