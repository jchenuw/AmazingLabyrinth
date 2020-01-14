import javax.swing.*;
import java.awt.*;

public class BoardDisplay extends JPanel{

	public static final int BOARD_SIDE_LENGTH = 600;
	public static final int EXTRA_TILE_SIDE_LENGTH = 75;
	public static final int TILES_SIDE_LENGTH = BOARD_SIDE_LENGTH/Board.NUM_TILES_SIDE;

	// Underlying board model
	private Board board;
	private Tile[][] tiles;

	private GridLayout GridLayout = new GridLayout(7,7);

	private TileDisplay[][] tileDisplays = new TileDisplay[7][7];

	public BoardDisplay(Board board) {
		this.board = board;
		this.tiles = board.getTiles();

		// setup tile displays for board GUI
		for(int i = 0; i < 7; i++){
			for(int j = 0; j < 7; j++){
				tileDisplays[i][j] = new TileDisplay(tiles[i][j]);
				add(tileDisplays[i][j]);
			}
		}

		// Swing setup
		setSize(BOARD_SIDE_LENGTH, BOARD_SIDE_LENGTH);
		setLayout(GridLayout);
		setVisible(true);
	}

	/**
	 * Updates the board GUI to match the game board by connecting each {@code tileGUI}
	 * to its new {@code tile}
	 */
	public void updateBoard(){
		// update each tileGUI and connect it to its new respective tile
		for(int row = 0; row < Board.NUM_TILES_SIDE; row++){
			for(int col = 0; col < Board.NUM_TILES_SIDE; col++){
				tileDisplays[row][col].setTile(tiles[row][col]);
			}
		}
	}
}
