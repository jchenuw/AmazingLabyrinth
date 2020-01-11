import javax.swing.*;
import java.awt.*;

public class BoardDisplay extends JPanel{

	public static final int BOARD_SIDE_LENGTH = 600;
	public static final int TILES_SIDE_LENGTH = BOARD_SIDE_LENGTH/Board.NUM_TILES_SIDE;

	// Underlying board model
	private Board board;

	private GridLayout GridLayout = new GridLayout(7,7);

	private TileDisplay[][] tiles = new TileDisplay[7][7];

	public BoardDisplay(Board board) {
		this.board = board;

		// setup board tiles
		for(int i = 0; i < 7; i++){
			for(int j = 0; j < 7; j++){
				tiles[i][j] = new TileDisplay(board.getTiles()[i][j]);
				add(tiles[i][j]);
			}
		}

		setSize(BOARD_SIDE_LENGTH, BOARD_SIDE_LENGTH);
		setLayout(GridLayout);
		setVisible(true);
	}

	public void updateBoard(Board board){
		this.board = board;
		for(int i = 0; i < 7; i++){
			for(int j = 0; j < 7; j++){
				tiles[i][j] = new TileDisplay(board.getTiles()[i][j]);
			}
		}
	}
}
