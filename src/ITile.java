public class ITile extends Tile {

	private static boolean[][] openingsByOrientation = new boolean[][] {
			{true, false, true, false},
			{false, true, false, true},
			{true, false, true, false},
			{false, true, false, true}
	};

	/**
	 * Creates new I-tile based on row position, column position, and orientation
	 *
	 * @param row row position on the board
	 * @param col column position on the board
	 * @param orientation orientation of the tile
	 */
	public ITile(int row, int col, int orientation) {
		super(row, col, orientation);

		setType('I');
		updateOpenings();
	}

	/**
	 * Sets openings for I-tiles based on orientation
	 */
	@Override
	public void updateOpenings() {
		System.arraycopy(openingsByOrientation[this.getOrientation()], 0, openings, 0, 4);
	}

}
