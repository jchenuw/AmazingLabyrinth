import javax.swing.*;
import java.awt.*;

/**
 * ImageLoader is used to preload all images at the beginning
 */
public class ImageLoader {
    // url for icons
    private final String T_TILE_URL = "/resource/TTile.jpg";
    private final String L_TILE_URL = "/resource/LTile.jpg";
    private final String I_TILE_URL = "/resource/ITile.jpg";

    // Icons for game
    private final ImageIcon T_TILE_ICON;
    private final ImageIcon L_TILE_ICON;
    private final ImageIcon I_TILE_ICON;

    /**
     * Constructor
     */
    public ImageLoader(){
        T_TILE_ICON = new ImageIcon(this.getClass().getResource(T_TILE_URL));
        L_TILE_ICON = new ImageIcon(this.getClass().getResource(L_TILE_URL));
        I_TILE_ICON = new ImageIcon(this.getClass().getResource(I_TILE_URL));
    }
}
