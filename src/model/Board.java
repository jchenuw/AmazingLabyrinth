package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Board {
	public static final int TILES_PER_SIDE = 7;

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

	// model.Board tiles
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
		connectTiles(0, tiles.length - 1, 0, tiles.length - 1);
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

					// Stationary L-tiles have no treasures
					if(tileType == 'L') {
						tiles[row][col] = generateTile(row, col, tileType, -1, true);
					}
					else {
						tiles[row][col] = generateTile(row, col, tileType, stationaryTreasureCounter, true);
						stationaryTreasureCounter++;
					}
				}
				// setup movable tiles
				else {
					TypeAndTreasureNum currentTileData = shiftableTilesData.get(dataCounter);

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
		System.out.println();
		printOrientation();
		System.out.println();
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
	private void connectTiles(int rowStart, int rowEnd, int colStart, int colEnd) {
		// Limit the bounds
		rowStart = Math.max(rowStart, 0);
		rowEnd = Math.min(rowEnd, tiles.length - 1);
		colStart = Math.max(colStart, 0);
		colEnd = Math.min(colEnd, tiles.length - 1);

		for(int row = rowStart; row <= rowEnd; row++) {
			for(int col = colStart; col <= colEnd; col++) {

				// Connects to the tile above
				if(row != 0 && tiles[row][col].getOpening(0) && tiles[row - 1][col].getOpening(2)) {
					tiles[row][col].addAdjTile(tiles[row - 1][col]);
				}

				// Connects to the tile to the right
				if(col != 6 && tiles[row][col].getOpening(1) && tiles[row][col + 1].getOpening(3)) {
					tiles[row][col].addAdjTile(tiles[row][col + 1]);
				}

				// Connects to the tile below
				if(row != 6 && tiles[row][col].getOpening(2) && tiles[row + 1][col].getOpening(0)) {
					tiles[row][col].addAdjTile(tiles[row + 1][col]);
				}

				// Connects to the tile to the left
				if(col != 0 && tiles[row][col].getOpening(3) && tiles[row][col - 1].getOpening(1)) {
					tiles[row][col].addAdjTile(tiles[row][col - 1]);
				}
			}
		}
	}

	/**
	 * Adds players to the tiles they're on
	 */
	private void connectPlayersToTiles() {
		for(int i = 0; i < players.length; i++) {
			Tile homeTile = tiles[players[i].getRow()][players[i].getCol()];

			players[i].setHomeTile(homeTile);
			homeTile.addPlayerOnTile(players[i]);
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

	private void printOrientation(){
		for(int r = 0; r < tiles.length; r++) {
			for (int c = 0; c < tiles[r].length; c++) {
				System.out.print(tiles[r][c].getOrientation() + " ");
			}
			System.out.println();
		}
	}

	/**
	 * Removes all adjacent tiles for each tile within certain rows and columns
	 *
	 * @param rowStart beginning of row constraint
	 * @param rowEnd end of row constraint
	 * @param colStart beginning of column constraint
	 * @param colEnd end of column constraint
	 */
	private void disconnectTiles(int rowStart, int rowEnd, int colStart, int colEnd) {
		// Limit the bounds
		rowStart = Math.max(rowStart, 0);
		rowEnd = Math.min(rowEnd, tiles.length - 1);
		colStart = Math.max(colStart, 0);
		colEnd = Math.min(colEnd, tiles.length - 1);

		for(int row = rowStart; row <= rowEnd; row++) {
			for(int col = colStart; col <= colEnd; col++) {
				tiles[row][col].removeAdjTiles();
			}
		}
	}

	private void reAddPlayers(int row, int col) {
		for(int i = 0; i < players.length; i++) {
			if(players[i].getRow() == -1 && players[i].getCol() == -1) {
				players[i].setRow(row);
				players[i].setCol(col);
				tiles[row][col].addPlayerOnTile(players[i]);
			}
		}
	}

	public void shiftRowLeft(int row) {
		// Hold new extra tile
		Tile newExtraTile = tiles[row][0];
		becomeExtraTile(newExtraTile);

		// Shift row
		for (int col = 0; col < tiles[row].length - 1; col++) {
			tiles[row][col] = tiles[row][col + 1];
			tiles[row][col].setCol(col);
		}

		// Insert previous extra tile to the end of row
		becomeBoardTile(extraTile, row, tiles.length - 1);

		// Reconnect tile nodes
		disconnectTiles(row - 1, row + 1, 0, tiles.length - 1);
		connectTiles(row - 1, row + 1, 0, tiles.length - 1);

		reAddPlayers(row, tiles.length - 1);
		extraTile = newExtraTile;
	}

	public void shiftRowRight(int row) {
		// Hold new extra tile
		Tile newExtraTile = tiles[row][tiles.length - 1];
		becomeExtraTile(newExtraTile);

		// Shift row
		for (int col = tiles[row].length - 1; col > 0; col--) {
			tiles[row][col] = tiles[row][col - 1];
			tiles[row][col].setCol(col);
		}

		// Insert previous extra tile to the start of row
		becomeBoardTile(extraTile, row, 0);

		// Reconnect tile nodes
		disconnectTiles(row - 1, row + 1, 0, tiles.length - 1);
		connectTiles(row - 1, row + 1, 0, tiles.length - 1);

		reAddPlayers(row, 0);
		extraTile = newExtraTile;
	}

	public void shiftColUp(int col) {
		// Hold new extra tile
		Tile newExtraTile = tiles[0][col];
		becomeExtraTile(newExtraTile);

		// Shift column
		for (int row = 0; row < tiles.length - 1; row++) {
			tiles[row][col] = tiles[row + 1][col];
			tiles[row][col].setRow(row);
		}

		// Insert previous extra tile to the end of column
		becomeBoardTile(extraTile, tiles.length - 1, col);

		// Reconnect tile nodes
		disconnectTiles(0, tiles.length - 1, col - 1, col + 1);
		connectTiles(0, tiles.length - 1, col - 1, col + 1);

		reAddPlayers(tiles.length - 1, col);
		extraTile = newExtraTile;
	}

	public void shiftColDown(int col) {
		// Hold new extra tile
		Tile newExtraTile = tiles[tiles.length - 1][col];
		becomeExtraTile(newExtraTile);

		// Shift column
		for (int row = tiles.length - 1; row > 0; row--) {
			tiles[row][col] = tiles[row - 1][col];
			tiles[row][col].setRow(row);
		}

		// Insert previous extra tile to the start of column
		becomeBoardTile(extraTile, 0, col);

		// Reconnect tile nodes
		disconnectTiles(0, tiles.length - 1, col - 1, col + 1);
		connectTiles(0, tiles.length - 1, col - 1, col + 1);

		reAddPlayers(0, col);
		extraTile = newExtraTile;
	}

	private void becomeExtraTile(Tile tile) {
		tile.setRow(-1);
		tile.setCol(-1);
		tile.setExtra(true);
		tile.removeAllPlayersOnTile();
		tile.removeAdjTiles();
	}

	private void becomeBoardTile(Tile extraTile, int row, int col) {
		tiles[row][col] = extraTile;
		tiles[row][col].setRow(row);
		tiles[row][col].setCol(col);
		tiles[row][col].setExtra(false);
	}

	public void movePlayer(Player player, int row, int col) {
		// Remove player from its current tile
		tiles[player.getRow()][player.getCol()].removePlayerOnTile(player);

		// Move to new tile
		player.setRow(row);
		player.setCol(col);
		tiles[row][col].addPlayerOnTile(player);

		// Check if player returned home with all treasures
		if(player.hasCollectedAll() && tiles[player.getRow()][player.getCol()] == player.getHomeTile()) {
			player.setReturnedHome(true);
		}
	}

	// Setters and getters
	public Tile[][] getTiles() {
		return tiles;
	}

	public Tile getExtraTile() {
		return this.extraTile;
	}

	public Player[] getPlayers() {
		return this.players;
	}

	public Treasure[] getTreasures() {
		return this.treasures;
	}
}
