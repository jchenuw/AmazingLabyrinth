import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Board {
	public static final int NUM_TILES_SIDE = 7;

	/**
	 * Helper class to encapsulate tile {@code type} and {@code treasureNum} pair
	 */
	private static class TypeAndTreasureNum {

		private char type;
		private int treasureNum;

		public TypeAndTreasureNum (char type, int treasureNum) {
			this.type = type;
			this.treasureNum = treasureNum;
		}

		// getters
		public char getType() {
			return this.type;
		}

		public int getTreasureNum() {
			return this.treasureNum;
		}

		public String toString(){
			return String.valueOf(type);
		}
	}

	// Stationary board tiles information
	private static final char[][] typeOfStationaryTiles = new char[][] {
			{'L',' ','T',' ','T',' ','L'},
			{' ',' ',' ',' ',' ',' ',' '},
			{'T',' ','T',' ','T',' ','T'},
			{' ',' ',' ',' ',' ',' ',' '},
			{'T',' ','T',' ','T',' ','T'},
			{' ',' ',' ',' ',' ',' ',' '},
			{'L',' ','T',' ','T',' ','L'}
	};
	private static final int[][] orientationOfStationaryTiles = new int[][] {
			{'1',' ','2',' ','2',' ','2'},
			{' ',' ',' ',' ',' ',' ',' '},
			{'1',' ','1',' ','2',' ','3'},
			{' ',' ',' ',' ',' ',' ',' '},
			{'1',' ','0',' ','3',' ','3'},
			{' ',' ',' ',' ',' ',' ',' '},
			{'0',' ','0',' ','0',' ','3'},
	};

	// Board tiles
	// Goes from left to right, top to bottom.
	private Tile[][] tiles = new Tile[7][7];
	private Tile extraTile;


	// Players pieces
	private Player[] players;

	// Treasures
	private Treasure[] treasures;

	public Board(Player[] players, Treasure[] treasures) {
		this.players = players;
		this.treasures = treasures;
		init();
	}

	public void init() {
		setupTiles();
		connectTiles(0, tiles.length, 0, tiles.length);
		connectPlayersToTiles();
	}

	public void setupTiles() {

		final int TOTAL_TILE_AMOUNT = TTile.TILE_AMOUNT + LTile.TILE_AMOUNT + ITile.TILE_AMOUNT;
		final int MAX_SHIFTABLE_TREASURE = 6;
		final int STATIONARY_TILE_AMOUNT = 12;

		// Initial tile data generation
		// -------------------------------------------------------------
		ArrayList<TypeAndTreasureNum> shiftableTilesData = new ArrayList<TypeAndTreasureNum>();
		int shiftableTreasureCounter = STATIONARY_TILE_AMOUNT;

		for(int i = 0; i < TTile.TILE_AMOUNT; i++) {
			shiftableTilesData.add(new TypeAndTreasureNum('T', (i < MAX_SHIFTABLE_TREASURE ? shiftableTreasureCounter : -1)));
			shiftableTreasureCounter++;
		}

		for(int i = 0; i < LTile.TILE_AMOUNT; i++) {
			shiftableTilesData.add(new TypeAndTreasureNum('L', (i < MAX_SHIFTABLE_TREASURE ? shiftableTreasureCounter : -1)));
			shiftableTreasureCounter++;
		}

		for(int i = 0; i < ITile.TILE_AMOUNT; i++) {
			shiftableTilesData.add(new TypeAndTreasureNum('I', -1));
		}
		// -------------------------------------------------------------

		// Randomize shiftable tiles data
		Collections.shuffle(shiftableTilesData, new Random());
		System.out.println(shiftableTilesData);

		// Setup board tiles
		// -------------------------------------------------------------
		int stationaryTreasureCounter = 0;
		int dataCounter = 0;

		for(int row = 0; row < tiles.length; row++) {
			for(int col = 0; col < tiles[row].length; col++) {

				char tileType = typeOfStationaryTiles[row][col];

				// set-up preset/stationary tiles
				if(tileType != ' ') {

					// create tile object
					tiles[row][col] = generateTile(row, col, tileType, stationaryTreasureCounter, true);

					stationaryTreasureCounter++;
				}
				// setup movable tiles
				else {
					TypeAndTreasureNum currentTileData = shiftableTilesData.get(dataCounter);

					// create tile object
					tiles[row][col] = generateTile(row, col, currentTileData.getType(), currentTileData.getTreasureNum(),false);

					dataCounter++;
				}
			}
		}
		// -------------------------------------------------------------

		// Setup extraTile
		// -------------------------------------------------------------
		TypeAndTreasureNum extraTileData = shiftableTilesData.get(dataCounter);

		// create extra tile object
		extraTile = generateTile(-1, -1, extraTileData.getType(), extraTileData.getTreasureNum(), false);
		extraTile.setExtra(true);
		// -------------------------------------------------------------

		// debug
		printBoard();
	}

	private Tile generateTile(int row, int col, char tileType, int treasureNum, boolean isStationary) {
		Tile newTile;
		int orientation;
		Random rand = new Random();

		// generate orientation
		if(isStationary) {
			orientation = (orientationOfStationaryTiles[row][col] - '0');
		} else {
			orientation = (rand.nextInt(4));
		}

		// generate new tile object
		if(tileType == 'T') {
			newTile = new TTile(row, col,  orientation);
		} else if (tileType == 'L') {
			newTile = new LTile(row, col,  orientation);
		} else {
			newTile = new ITile(row, col, orientation);
		}

		//add treasure
		if(treasureNum != -1) {
			newTile.setTreasure(treasures[treasureNum]);
		}

		return newTile;
	}


	/**
	 * Connects each tile, within certain rows and columns, to its neighbouring tiles
	 *
	 * @param rowStart beginning of row constraint
	 * @param rowEnd end of row constraint
	 * @param colStart beginning of column constraint
	 * @param colEnd end of column constraint
	 */
	public void connectTiles(int rowStart, int rowEnd, int colStart, int colEnd) {
		for(; rowStart < rowEnd; rowStart++) {
			for(;colStart < colEnd; colStart++) {

				if(rowStart != 0 && tiles[rowStart][colStart].getOpening(0) && tiles[rowStart - 1][colStart].getOpening(2)) {
					tiles[rowStart][colStart].addAdjTile(tiles[rowStart - 1][colStart]);
				}

				if(colStart != 7 && tiles[rowStart][colStart].getOpening(1) && tiles[rowStart][colStart + 1].getOpening(3)) {
					tiles[rowStart][colStart].addAdjTile(tiles[rowStart][colStart + 1]);
				}

				if(rowStart != 7 && tiles[rowStart][colStart].getOpening(2) && tiles[rowStart + 1][colStart].getOpening(0)) {
					tiles[rowStart][colStart].addAdjTile(tiles[rowStart + 1][colStart]);
				}

				if(colStart != 0 && tiles[rowStart][colStart].getOpening(3) && tiles[rowStart][colStart - 1].getOpening(1)) {
					tiles[rowStart][colStart].addAdjTile(tiles[rowStart][colStart - 1]);
				}
			}
		}
	}

	public void connectPlayersToTiles() {
		for(int i = 0; i < players.length; i++) {
			tiles[players[i].getRow()][players[i].getCol()].addPlayerOnTile(players[i]);
		}
	}

	private void printBoard(){
        for(int r = 0; r < tiles.length; r++) {
            for (int c = 0; c < tiles[r].length; c++) {
                System.out.print(tiles[r][c].getType() + " ");
            }
            System.out.println();
        }
    }

	public void shiftRowLeft(int row, Tile extraTile) {
		Tile newExtraTile = tiles[row][0];
		newExtraTile.setCol(-1);
		newExtraTile.setRow(-1);
		newExtraTile.setExtra(true);

		for (int i = 0; i < tiles[row].length - 1; i++) {
			tiles[row][i] = tiles[row][i + 1];
		}
		tiles[row][tiles.length - 1] = extraTile;
		tiles[row][tiles.length - 1].setRow(row);
		tiles[row][tiles.length - 1].setCol(tiles.length - 1);
		tiles[row][tiles.length - 1].setExtra(false);

		extraTile = newExtraTile;
	}

	public void shiftRowRight(int row, Tile extraTile) {
		Tile newExtraTile = tiles[row][tiles.length - 1];
		newExtraTile.setCol(-1);
		newExtraTile.setRow(-1);
		newExtraTile.setExtra(true);

		for (int i = tiles[row].length - 1; i > 0; i--) {
			tiles[row][i] = tiles[row][i - 1];
		}
		tiles[row][0] = extraTile;
		tiles[row][0].setRow(row);
		tiles[row][0].setCol(tiles.length - 1);
		tiles[row][0].setExtra(false);

		extraTile = newExtraTile;
	}

	public void shiftColUp(int col, Tile extraTile) {
		Tile newExtraTile = tiles[0][col];
		newExtraTile.setCol(-1);
		newExtraTile.setRow(-1);
		newExtraTile.setExtra(true);

		for (int i = 0; i < tiles.length - 1; i++) {
			tiles[i][col] = tiles[i + 1][col];
		}
		tiles[tiles.length - 1][col] = extraTile;
		tiles[tiles.length - 1][col].setRow(tiles.length - 1);
		tiles[tiles.length - 1][col].setCol(col);
		tiles[tiles.length - 1][col].setExtra(false);

		extraTile = newExtraTile;
	}

	public void shiftRowDown(int col, Tile extraTile) {
		Tile newExtraTile = tiles[tiles.length - 1][col];
		newExtraTile.setCol(-1);
		newExtraTile.setRow(-1);
		newExtraTile.setExtra(true);

		for (int i = tiles.length - 1; i > 0; i--) {
			tiles[i][col] = tiles[i - 1][col];
		}
		tiles[0][col] = extraTile;
		tiles[0][col].setRow(tiles.length - 1);
		tiles[0][col].setCol(col);
		tiles[0][col].setExtra(false);

		extraTile = newExtraTile;
	}

	// Setters and getters
	public Tile[][] getTiles() {
		return tiles;
	}

	public Tile getExtraTile() {
    	return this.extraTile;
	}
}

