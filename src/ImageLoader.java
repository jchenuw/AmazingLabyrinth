import javax.swing.*;
import java.awt.*;

/**
 * ImageLoader is used to preload all images at the beginning
 */
public class ImageLoader {
	// url for icons
	private final String T_TILE_URL = "/resource/TTile";
	private final String L_TILE_URL = "/resource/LTile";
	private final String I_TILE_URL = "/resource/ITile";

	// Icons for game
	public final ImageIcon[] T_TILE_ICON = new ImageIcon[4];
	public final ImageIcon[] L_TILE_ICON = new ImageIcon[4];
	public final ImageIcon[] I_TILE_ICON = new ImageIcon[4];

	/**
	 * Constructor
	 */
	public ImageLoader(){

		for(int j = 0; j < 4; j++){
			T_TILE_ICON[j] = new ImageIcon(this.getClass().getResource(T_TILE_URL + j + ".jpg"));
			L_TILE_ICON[j] = new ImageIcon(this.getClass().getResource(L_TILE_URL + j + ".jpg"));
			I_TILE_ICON[j] = new ImageIcon(this.getClass().getResource(I_TILE_URL + j + ".jpg"));
		}
	}

	public static ImageIcon resizeImageIcon(int newX, int newY, ImageIcon icon){
		Image resizedImage = icon.getImage().getScaledInstance(newX, newY, Image.SCALE_SMOOTH);
		return new ImageIcon(resizedImage);
	}
}


