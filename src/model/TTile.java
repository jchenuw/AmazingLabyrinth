package model;

public class TTile extends Tile {

	// Non-stationary T-tiles amount
	public static final int TILE_AMOUNT = 6;

	// Openings for T-tiles
	public static boolean[][] OPENINGS_BY_ORIENTATION = new boolean[][] {
			{true, true, false, true},
			{true, true, true, false},
			{false, true, true, true},
			{true, false, true, true}
	};

	/**
	 * Creates new T-tile based on row position, column position, and orientation
	 *
	 * @param row row position on the board
	 * @param col column position on the board
	 * @param orientation orientation of the tile
	 */
	public TTile(int row, int col, int orientation) {
		super(row, col, orientation);

		setType('T');
		updateOpenings();
	}

	/**
	 * Sets openings for T-tiles based on orientation
	 */
	@Override
	public void updateOpenings() {
		System.arraycopy(OPENINGS_BY_ORIENTATION[this.getOrientation()], 0, openings, 0, 4);
	}

}
