public class Board {

	// Board tiles setup
	// Board tiles
	// Goes from left to right, top to bottom.
	private Tile[][] tiles;

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
}
