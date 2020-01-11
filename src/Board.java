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
		// -------------------------------------------------------------

		// debug
		printBoard();
	}

	public Tile generateTile(int row, int col, char tileType, int treasureNum, boolean isStationary) {
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
		System.arraycopy(tiles[row], 1, tiles[row], 0, tiles[row].length - 1);
		tiles[row][tiles.length - 1] = extraTile;
		extraTile = newExtraTile;
	}

	public void shiftRowRight(int row, Tile extraTile) {
		Tile newExtraTile = tiles[row][tiles.length - 1];
		System.arraycopy(tiles[row], 0, tiles[row], 1, tiles[row].length - 1);
		tiles[row][0] = extraTile;
		extraTile = newExtraTile;
	}

	public void shiftColUp(int col, Tile extraTile) {
		Tile newExtraTile = tiles[0][col];
		System.arraycopy(tiles, 1, tiles, 0,  tiles.length - 1);
		tiles[tiles.length - 1][col] = extraTile;
		extraTile = newExtraTile;
	}

	public void shiftRowDown(int col, Tile extraTile) {
		Tile newExtraTile = tiles[tiles.length - 1][col];
		System.arraycopy(tiles, 0, tiles, 1,  tiles.length - 1);
		tiles[0][col] = extraTile;
		extraTile = newExtraTile;
	}

	public Tile[][] getTiles() {
		return tiles;
	}
}

