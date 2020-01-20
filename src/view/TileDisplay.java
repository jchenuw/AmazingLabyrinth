package view;

import model.Tile;

import javax.swing.*;
import java.awt.event.ActionListener;

public class TileDisplay extends JLayeredPane{

	private JButton tileImage;
	private JLabel treasureImage;

	public TileDisplay() {
		tileImage = new JButton();
		treasureImage = new JLabel();

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
				getResource("../resources/tiles/" + tile.getType() + tile.getOrientation() + ".png")));

		if(tile.hasTreasure()) {
			treasureImage.setIcon(new ImageIcon(this.getClass().
					getResource("../resources/treasures/" + tile.getTreasure().getTreasureNum() + ".png")));
		} else {
			treasureImage.setIcon(null);
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

	// Getter
	public JButton getTileImage() {
		return this.tileImage;
	}
}
