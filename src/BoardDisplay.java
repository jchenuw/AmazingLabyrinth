import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoardDisplay extends JLayeredPane implements ActionListener {

	public static final int BOARD_SIDE_LENGTH = 600;
	public static final int EXTRA_TILE_SIDE_LENGTH = 75;
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
				tiles[i][j].addActionListener(this);
				add(tiles[i][j], Integer.valueOf(10));
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

	public void markAdj(TileDisplay tile){
		tile.setBorder(new LineBorder(Color.red, 5));

		for(int x = 0; x < 7; x++){
			for(int y = 0; y < 7; y++){
				for(Tile a: tile.getTile().getAdjTiles()){
					if(tiles[x][y].getTile() == a){
						tiles[x][y].setBorder(new LineBorder(Color.red, 5));
					}
				}
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for(int i = 0; i < 7; i++){
			for(int j = 0; j < 7; j++){
				if(e.getSource() == tiles[i][j]){
					markAdj(tiles[i][j]);
				}
			}
		}
	}
}
