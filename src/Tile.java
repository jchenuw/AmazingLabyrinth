import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public abstract class Tile extends Piece {

	private Treasure treasure = null;
	private ArrayList<Tile> adjTiles;
	private char type;

	// Openings of the tile
	// 0 - Up
	// 1 - Right
	// 2 - Down
	// 3 - Left
	protected boolean[] openings = new boolean[4];

	// Orientation of the tile
	// 0 - 0 degree
	// 1 - 90 degrees
	// 2 - 180 degrees
	// 3 - 270 degrees
	private int orientation;

	// Determines if tile is the extra
	private boolean extra;

	/**
	 * Creates new tile based on row position, column position, and orientation
	 *
	 * @param row row position on the board
	 * @param col column position on the board
	 * @param orientation orientation of the tile
	 */
	public Tile(int row, int col, int orientation) {
		super(row, col);
		this.orientation = orientation;
	}

	/**
	 * Removes current treasure on tile
 	 */
	public void removeTreasure() {
		this.treasure = null;
	}

	/**
	 * Determines if this tile contains a treasure
	 *
	 * @return if the treasure is not null
	 */
	public boolean hasTreasure() {
		return this.treasure != null;
	}

	/**
	 * Connects a tile to {@code adjTiles}
	 *
	 * @param tile Tile to be connected
	 */
	public void addAdjTile(Tile tile) {
		adjTiles.add(tile);
	}

	/**
	 * Removes all connected tiles in {@code adjTiles}
	 */
	public void removeAdjTiles() {
		adjTiles.clear();
	}

	/**
	 * Rotates current tile by 90 degrees (increment {@code orientation} by 1)
	 * and returns {@code orientation} to 0 when rotating at 270 degrees
	 */
	public void rotate() {

		// rotate 90 degrees
		this.orientation++;

		if(this.orientation > 3) {
			// start back at 'normal' rotation
			this.orientation = 0;
		}
	}

	/**
	 * Sets tile openings based on tile type and orientation
	 */
	public abstract void updateOpenings();

	// Setters and getters
	public boolean getOpening(int dir){
		return(openings[dir]);
	}

	public void setTreasure(Treasure treasure) {
		this.treasure = treasure;
	}

	public void setType(char type) {
		this.type = type;
	}
	public char getType() {
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

	// Object class toString
	@Override
	public String toString() {
		return "Tile: row = " + getRow() + ", col = " + getCol() + ", type = " + type + ", orientation = " + (orientation * 90) + " degrees, " + "has treasure = " + hasTreasure()
				+ ", openings: " + openings[0] + " " + openings[1] + " " + openings[2] + " " + openings[3];
	}
}