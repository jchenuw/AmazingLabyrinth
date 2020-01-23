package model;

import java.util.ArrayList;

public abstract class Tile extends Piece {

	private Treasure treasure = null;
	private ArrayList<Tile> adjTiles = new ArrayList<Tile>();
	private ArrayList<Player> playersOnTile = new ArrayList<Player>();
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
	private boolean extra = false;

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
	 * @param tile model.Tile to be connected
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
	 * Adds a player to {@code playersOnTile}
	 *
	 * @param player model.Player to be added
	 */
	public void addPlayerOnTile(Player player) {
		playersOnTile.add(player);

		if(player.getCurrentCard() != null && player.getCurrentCard().getTreasure() == this.treasure) {
			player.goToNextCard();

			this.treasure.setCollected(true);
			this.treasure = null;
		}
	}

	/**
	 * Removes a player from {@code playersOnTile}
	 *
	 * @param player model.Player to be removed
	 */
	public void removePlayerOnTile(Player player) {
		playersOnTile.remove(player);
	}

	/**
	 * Clears all players from {@code playersOnTile}
	 */
	public void removeAllPlayersOnTile() {
		playersOnTile.clear();
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
		updateOpenings();
	}

	/**
	 * Sets tile openings based on tile type and orientation
	 */
	public abstract void updateOpenings();

	// Setters and getters
	public ArrayList<Tile> getAdjTiles() {
		return adjTiles;
	}

	public ArrayList<Player> getPlayersOnTile() {
		return this.playersOnTile;
	}

	@Override
	public void setRow(int row) {
		super.setRow(row);

		// move players on this tile to new row position
		for(int i = 0; i < playersOnTile.size(); i++) {
			playersOnTile.get(i).setRow(row);
		}
	}
	@Override
	public void setCol(int col) {
		super.setCol(col);

		// move players on this tile to new column position
		for(int i = 0; i < playersOnTile.size(); i++) {
			playersOnTile.get(i).setCol(col);
		}
	}

	public boolean[] getOpenings() {
		return this.openings;
	}

	public boolean getOpening(int dir){
		return(openings[dir]);
	}

	public void setTreasure(Treasure treasure) {
		this.treasure = treasure;

		this.treasure.setTileOwner(this);
	}
	public Treasure getTreasure() {
		return this.treasure;
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
		return "model.Tile: row = " + getRow() + ", col = " + getCol() + ", type = " + type + ", orientation = " + (orientation * 90) + " degrees, " + "has treasure = " + hasTreasure()
				+ ", openings: " + openings[0] + " " + openings[1] + " " + openings[2] + " " + openings[3];
	}
}