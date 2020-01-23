package tests;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.SlideCommand;

import static org.junit.jupiter.api.Assertions.*;

class SlideCommandTest {

	private Player[] players = new Player[4];
	private Treasure[] treasures = new Treasure[24];

	@BeforeEach
	void setUp() {
		String[] colours =  {"Red", "Blue", "Green", "Purple"};
		int[][] startingPoints = {{0,0}, {6, 0}, {0, 6}, {6, 6}};

		for(int i = 0; i < players.length; i++) {
			players[i] = new HumanPlayer(startingPoints[i][0], startingPoints[i][1], colours[i]);
		}

		for(int i = 0; i < treasures.length; i++) {
			treasures[i] = new Treasure(i);
		}
	}

	@Test
	void commandLegalAndExecuteTest() {
		// Board
		Board board = new Board(players, treasures);
		board.init();
		Tile extraTile = board.getExtraTile();

		SlideCommand rowZeroSlider = new SlideCommand(board, 1, 0);
		SlideCommand rowOneSlider = new SlideCommand(board, 1, 1);
		SlideCommand columnThreeSlider = new SlideCommand(board, 0, 3);

		assertFalse(rowZeroSlider.isLegal());
		assertTrue(rowOneSlider.isLegal());
		assertTrue(columnThreeSlider.isLegal());

		// shift row one right
		rowOneSlider.execute();

		assertNotSame(board.getExtraTile(), extraTile);
		assertSame(board.getTiles()[1][0], extraTile);

		extraTile = board.getExtraTile();

		// shift column three up
		columnThreeSlider.execute();

		assertNotSame(board.getExtraTile(), extraTile);
		assertSame(board.getTiles()[6][3], extraTile);
	}

}