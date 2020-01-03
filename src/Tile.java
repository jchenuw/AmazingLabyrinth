import java.util.ArrayList;

public abstract class Tile extends Piece {

	// Treasure on the tile
	private Treasure treasure;

	// Neighbouring tiles
	private ArrayList<Tile> adjTiles;

	// Type of tile
	private char type;

	// Openings of the tile
	// 0 - Up
	// 1 - Right
	// 2 - Down
	// 3 - Left
	private boolean openings[];

	// Orientation of the tile
	// 0 - 0 degree
	// 1 - 90 degrees
	// 2 - 180 degrees
	// 3 - 270 degrees
	private int orientation;

	// Determines if tile is the extra
	private boolean extra;

	// Constructor
	public Tile(int row, int col) {
		super(row, col);
	}

	// Removes current treasure on tile
	public void removeTreasure() {
		this.treasure = null;
	}

	// Determines if this tile contains a treasure
	public boolean hasTreasure() {
		return this.treasure != null;
	}

	// Add and remove neighbours
	public void addAdjTile(Tile tile) {
		adjTiles.add(tile);
	}
	public void removeAdjTiles() {
		adjTiles.clear();
	}

	/* Setters and getters */
	public void setType(char type) {
		this.type = type;
	}
	public char getType(int type) {
		return this.type;
	}

	public void setOrientation(int orientation) {
		this.orientation = orientation;
	}
	public int getOrientation() {
		return this.orientation;
	}

	public void setExtra(boolean extra) {
		this.extra = extra;
	}
	public boolean isExtra() {
		return this.extra;
	}
}