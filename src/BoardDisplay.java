import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoardDisplay extends JLayeredPane implements ActionListener {

	public static final int BOARD_SIDE_LENGTH = 600;
	public static final int TILES_SIDE_LENGTH = BOARD_SIDE_LENGTH/Board.TILES_PER_SIDE;

	// Underlying board model
	private Board board;

	private TileDisplay[][] tileDisplays = new TileDisplay[7][7];
	private JLabel[] playerViews;

	public BoardDisplay(Board board) {
		this.board = board;

		// Properties setup
		setSize(BOARD_SIDE_LENGTH, BOARD_SIDE_LENGTH);
		setLayout(new GridLayout(7,7));
		setVisible(true);

		setupTileDisplays();
	}

	private void setupTileDisplays() {
		for(int i = 0; i < Board.TILES_PER_SIDE; i++){
			for(int j = 0; j < Board.TILES_PER_SIDE; j++){
				tileDisplays[i][j] = new TileDisplay(board.getTiles()[i][j]);
				tileDisplays[i][j].addActionListener(this);
				add(tileDisplays[i][j], Integer.valueOf(10));
			}
		}
	}

	/**
	 * Updates board to match model
	 */
	public void updateBoard(){

		for(int i = 0; i < 7; i++){
			for(int j = 0; j < 7; j++){
				tileDisplays[i][j].setTile(board.getTiles()[i][j]);
			}
		}
	}

	// ----------------------- Ronalds uncommented code -----------------------
	public void markAdj(TileDisplay tile){
		tile.setBorder(new LineBorder(Color.blue, 5));

		for(int x = 0; x < 7; x++){
			for(int y = 0; y < 7; y++){
				for(Tile a: tile.getTile().getAdjTiles()){
					if(tileDisplays[x][y].getTile() == a){
						tileDisplays[x][y].setBorder(new LineBorder(Color.red, 5));
					}
				}
			}
		}
	}

	public void unMarkAdj(){
		for(int i = 0; i < 7; i++){
			for(int j = 0; j < 7; j++){
				tileDisplays[i][j].setBorder(UIManager.getBorder("Button.border"));
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for(int i = 0; i < 7; i++){
			for(int j = 0; j < 7; j++){
				if(e.getSource() == tileDisplays[i][j]){
					unMarkAdj();
					markAdj(tileDisplays[i][j]);
				}
			}
		}
	}
}
