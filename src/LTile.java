
public class LTile extends Tile{

	private static boolean[][] openingsByOrientation = new boolean[][] {
			{true, true, false, false},
			{false, true, true, false},
			{false, false, true, true},
			{true, false, false, true}
	};

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
