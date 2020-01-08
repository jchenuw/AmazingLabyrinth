
public class LTile extends Tile{

	// Non-stationary L-tiles amount
	public static final int TILE_AMOUNT = 16;

	private static final boolean[][] openingsByOrientation = new boolean[][] {
			{true, true, false, false},
			{false, true, true, false},
			{false, false, true, true},
			{true, false, false, true}
	};

	/**
	 * Creates new L-tile based on row position, column position, and orientation
	 *
	 * @param row row position on the board
	 * @param col column position on the board
	 * @param orientation orientation of the tile
	 */
	public LTile(int row, int col, int orientation) {
		super(row, col, orientation);

		setType('L');
		updateOpenings();
	}

	/**
	 * Sets openings for L-tiles based on orientation
	 */
	@Override
	public void updateOpenings() {
		System.arraycopy(openingsByOrientation[this.getOrientation()], 0, openings, 0, 4);
	}

}
