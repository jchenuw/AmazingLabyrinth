public class TTile extends Tile {

	// openings for T-tile class
	private static boolean[][] openingsByOrientation = new boolean[][] {
			{true, true, false, true},
			{true, true, true, false},
			{false, true, true, true},
			{true, false, true, true}
	};

	// Constructor
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
		System.arraycopy(openingsByOrientation[this.getOrientation()], 0, openings, 0, 4);
	}

}
