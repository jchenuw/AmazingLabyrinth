import javax.swing.*;

public class ITile extends Tile {

	// Non-stationary I-tiles amount
	public static final int TILE_AMOUNT = 12;

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
		super(row, col, orientation, Game.ImageLoader.I_TILE_ICON);

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
