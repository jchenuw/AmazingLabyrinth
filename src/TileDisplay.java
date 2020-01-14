import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TileDisplay extends JButton implements ActionListener{

	// The tile this image represents
	private Tile tile;

	/**
	 * Constructor
	 *
	 * @param tile the Tile object this represents
	 */
	public TileDisplay(Tile tile) {
		this.tile = tile;

		//setBorderPainted(false);
		setIcon(tile.getOrientation());
		addActionListener(this);
		setVisible(true);
	}

	public void setIcon(int rotation){
		if(tile.getType() == 'T'){
			setIcon(ImageLoader.resizeImageIcon(checkSize(), checkSize(), GameDisplay.ImageLoader.T_TILE_ICON[tile.getOrientation()]));
		}
		else if(tile.getType() == 'L'){
			setIcon(ImageLoader.resizeImageIcon(checkSize(), checkSize(), GameDisplay.ImageLoader.L_TILE_ICON[tile.getOrientation()]));
		}
		else if(tile.getType() == 'I'){
			setIcon(ImageLoader.resizeImageIcon(checkSize(), checkSize(), GameDisplay.ImageLoader.I_TILE_ICON[tile.getOrientation()]));
		}
	}

	public int checkSize(){
		if(tile.isExtra()){
			return BoardDisplay.EXTRA_TILE_SIDE_LENGTH;
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

	}
}
