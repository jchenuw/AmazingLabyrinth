import javax.swing.*;
import java.awt.*;

public class BoardDisplay extends JPanel{

	// Underlying board model
	private Board board;
	private BoardDisplay JPanel;

	private GridLayout GridLayout = new GridLayout(7,7);

	private TileDisplay[][] tiles = new TileDisplay[7][7];

	public BoardDisplay() {
		// setup board tiles
		for(int i = 0; i < 7; i++){
			for(int j = 0; j < 7; j++){
				tiles[i][j] = new TileDisplay();
				add(tiles[i][j]);
			}
		}

		setSize(600, 600);
		setLayout(GridLayout);
		setVisible(true);
	}

	public void updateBoard(TileDisplay[][] tiles){
		for(int i = 0; i < 7; i++){
			for(int j = 0; j < 7; j++){
				this.tiles[i][j] = tiles[i][j];
			}
		}
	}
}
