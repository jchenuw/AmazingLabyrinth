import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TileDisplay extends JButton implements ActionListener{

	// The tile this image represents
	private Tile tile;
	private JButton TileDisplay;

	public TileDisplay(Tile tile) {
		this.tile = tile;
		//setBorderPainted(false);
		setIcon(tile.getOrientation());
		addActionListener(this);
		setVisible(true);
	}

	public void setIcon(int rotation){
		if(tile.getType() == 'T'){
			setIcon(Game.ImageLoader.resizeImageIcon(Board.TILES_SIDE_LENGTH, Board.TILES_SIDE_LENGTH, Game.ImageLoader.T_TILE_ICON[tile.getOrientation()]));
		}
		else if(tile.getType() == 'L'){
			setIcon(Game.ImageLoader.resizeImageIcon(Board.TILES_SIDE_LENGTH, Board.TILES_SIDE_LENGTH, Game.ImageLoader.L_TILE_ICON[tile.getOrientation()]));
		}
		else if(tile.getType() == 'I'){
			setIcon(Game.ImageLoader.resizeImageIcon(Board.TILES_SIDE_LENGTH, Board.TILES_SIDE_LENGTH, Game.ImageLoader.I_TILE_ICON[tile.getOrientation()]));
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {

	}
}
