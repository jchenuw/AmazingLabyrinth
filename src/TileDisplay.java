import javax.swing.*;
import java.awt.event.ActionListener;

public class TileDisplay extends JLayeredPane{

	private TileImage tileImage;
	private JLabel treasureImage;

	public TileDisplay(int row, int col) {
		tileImage = new TileImage(row, col);
		treasureImage = new JLabel();

		//setBorderPainted(false);
		setVisible(true);
		setLayout(null);

		// Properties of background image
		tileImage.setSize(BoardDisplay.TILES_SIDE_LENGTH, BoardDisplay.TILES_SIDE_LENGTH);
		tileImage.setLocation(0, 0);

		// Properties of treasure image
		treasureImage.setSize(50,50);
		treasureImage.setLocation(19,18);

		add(tileImage, JLayeredPane.DEFAULT_LAYER);
		add(treasureImage, JLayeredPane.POPUP_LAYER);
	}

	public void update(Tile tile){
		tileImage.setIcon(new ImageIcon(this.getClass().
				getResource("/resource/tiles/" + tile.getType() + tile.getOrientation() + ".png")));

		if(tile.hasTreasure()) {
			treasureImage.setIcon(new ImageIcon(this.getClass().
					getResource("/resource/treasures/" + tile.getTreasure().getTreasureNum() + ".png")));
		}
	}

	public void removeTreasureImage() {
		this.remove(treasureImage);
		this.revalidate();
		this.repaint();

		treasureImage = null;
	}

	public void addRotateListener(ActionListener rotateListener) {
		tileImage.addActionListener(rotateListener);
	}

	public void addMoveListener(ActionListener moveListener) {
		tileImage.addActionListener(moveListener);
	}

}
