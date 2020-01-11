import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Board {
	public static final int NUM_TILES_SIDE = 7;
	public static final int BOARD_SIDE_LENGTH = 600;
	public static final int TILES_SIDE_LENGTH = BOARD_SIDE_LENGTH/NUM_TILES_SIDE;

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
			{'0',' ','2',' ','2',' ','2'},
			{' ',' ',' ',' ',' ',' ',' '},
			{'1',' ','1',' ','2',' ','3'},
			{' ',' ',' ',' ',' ',' ',' '},
			{'1',' ','0',' ','3',' ','3'},
			{' ',' ',' ',' ',' ',' ',' '},
			{'1',' ','0',' ','0',' ','3'},
	};

	// Board tiles
	// Goes from left to right, top to bottom.
	private Tile[][] tiles = new Tile[7][7];

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
	}

	public void setupTiles() {

		final int TOTAL_TILE_AMOUNT = TTile.TILE_AMOUNT + LTile.TILE_AMOUNT + ITile.TILE_AMOUNT;
		final int MAX_SHIFTABLE_TREASURE = 6;
		final int STATIONARY_TILE_AMOUNT = 12;
		Random rand = new Random();

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
					if(tileType == 'T') {
						tiles[row][col] = new TTile(row, col, (orientationOfStationaryTiles[row][col] - '0'));
					} else if (tileType == 'L') {
						tiles[row][col] = new LTile(row, col, (orientationOfStationaryTiles[row][col] - '0'));
					} else {
						tiles[row][col] = new ITile(row, col, (orientationOfStationaryTiles[row][col] - '0'));
					}

					// add treasure
					tiles[row][col].setTreasure(treasures[stationaryTreasureCounter]);

					stationaryTreasureCounter++;
				}
				// setup movable tiles
				else {
					TypeAndTreasureNum currentTileData = shiftableTilesData.get(dataCounter);
					int currentTreasureNum = currentTileData.getTreasureNum();
					tileType = currentTileData.getType();

					// create tile object
					if(tileType == 'T') {
						tiles[row][col] = new TTile(row, col, (rand.nextInt(4)));
					} else if (tileType == 'L') {
						tiles[row][col] = new LTile(row, col, (rand.nextInt(4)));
					} else {
						tiles[row][col] = new ITile(row, col, (rand.nextInt(4)));
					}

					dataCounter++;

					//add treasure
					if(currentTreasureNum != -1) {
						tiles[row][col].setTreasure(treasures[currentTreasureNum]);
					}
				}
			}
		}
		printBoard();
	}

	public void printBoard(){
        for(int r = 0; r < tiles.length; r++) {
            for (int c = 0; c < tiles[r].length; c++) {
                System.out.print(tiles[r][c].getType() + " ");
            }
            System.out.println();
        }
    }

    /**
     * Updates which tiles are next to and accessible from which tile
     */
	public void updateAdj(){
        for(int r = 0; r < tiles.length; r++){
            for(int c = 0; c < tiles[r].length; c++){
                for(int dir = 0; dir < 4; dir++){
                    if(!(r ==  0 && dir == 0) && !(r == 6 && dir == 2) && !(c == 0 && dir == 3) && !(c == 6 && dir == 1)){
                        addAdj(r, c, dir);
                    }
                }
            }
        }
    }

    /**
     * checks and adds adjacent tiles
     *
     * @param r row of the tile
     * @param c column of the tile
     * @param dir direction to be checked
     */
    public void addAdj(int r, int c, int dir){
	    if(dir == 0){
	        if(tiles[r][c].getOpening(0) && tiles[r - 1][c].getOpening(2)){
	            tiles[r][c].addAdjTile(tiles[r - 1][c]);
            }
        }
	    else if(dir == 1){
            if(tiles[r][c].getOpening(1) && tiles[r][c + 1].getOpening(3)){
                tiles[r][c].addAdjTile(tiles[r][c + 1]);
            }
        }
        else if(dir == 2){
            if(tiles[r][c].getOpening(2) && tiles[r + 1][c].getOpening(0)){
                tiles[r][c].addAdjTile(tiles[r + 1][c]);
            }
        }
        else{
            if(tiles[r][c].getOpening(3) && tiles[r][c - 1].getOpening(1)){
                tiles[r][c].addAdjTile(tiles[r][c + 1]);
            }
        }
    }

	public void shiftRowLeft(int row, Tile extraTile) {
		Tile newExtraTile = tiles[row][0];
		for (int i = 0; i < tiles[row].length - 2; i++) {
			tiles[row][i] = tiles[row][i + 1];
		}
		tiles[row][tiles.length - 1] = extraTile;
		extraTile = newExtraTile;
	}

	public void shiftRowRight(int row, Tile extraTile) {
		Tile newExtraTile = tiles[row][tiles.length - 1];
		for (int i = tiles[row].length - 1; i > 0; i--) {
			tiles[row][i] = tiles[row][i - 1];
		}
		tiles[row][0] = extraTile;
		extraTile = newExtraTile;
	}

	public void shiftColUp(int col, Tile extraTile) {
		Tile newExtraTile = tiles[0][col];
		for (int i = 0; i < tiles.length - 1; i++) {
			tiles[i][col] = tiles[i + 1][col];
		}
		tiles[tiles.length - 1][col] = extraTile;
		extraTile = newExtraTile;
	}

	public void shiftRowDown(int col, Tile extraTile) {
		Tile newExtraTile = tiles[tiles.length - 1][col];
		for (int i = tiles.length - 1; i > 0; i--) {
			tiles[i][col] = tiles[i - 1][col];
		}
		tiles[0][col] = extraTile;
		extraTile = newExtraTile;
	}

	public Tile[][] getTiles() {
		return tiles;
	}
}

