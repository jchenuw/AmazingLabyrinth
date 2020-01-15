import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TileDisplay extends JButton implements ActionListener{

	// The tile this image represents
	private Tile tile;

	public TileDisplay(Tile tile) {
		this.tile = tile;

		//setBorderPainted(false);
		updateIcon();
		addActionListener(this);
		setVisible(true);
	}

	public void updateIcon(){
		setIcon(new ImageIcon(this.getClass().getResource("/resource/tiles/" + tile.getType() + tile.getOrientation() + ".png")));
	}

	public int checkSize(){
		if(tile.isExtra()){
			return 80;
		}
		else{
			return BoardDisplay.TILES_SIDE_LENGTH;
		}
	}

	// Setters and Getters
	public void setTile(Tile tile) {
		this.tile = tile;
	}
	public Tile getTile() {
		return this.tile;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this){
			if(tile.isExtra()){
				tile.rotate();
				updateIcon();
			}
		}
	}
}
