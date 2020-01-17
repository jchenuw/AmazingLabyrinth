import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * This class represents the tile slider than inserts the extra tile
 * and shifts the row or column the slider is responsible for
 *
 * @author Jier Chen
 */
public class TileSlider extends JButton {

	// Orientation of the slider direction
	// 0 - Up
	// 1 - Right
	// 2 - Down
	// 3 - Left
	private int orientation;

	// row or column line the slider is responsible for
	private int lineResponsible;

	private boolean disabled = false;

	public TileSlider(int lineResponsible, int orientation) {
		this.orientation = orientation;
		this.lineResponsible = lineResponsible;

		// Properties
		setIcon(new ImageIcon(this.getClass().getResource("/resource/sliders/arrow" + this.orientation + ".png")));// Properties
		setSize(BoardDisplay.TILES_SIDE_LENGTH, BoardDisplay.TILES_SIDE_LENGTH);
		setVisible(true);
		setOpaque(false);
		setContentAreaFilled(false);
		setBorderPainted(false);
		setFocusPainted(false);
	}

	/**
	 * Toggles {@code disabled} boolean
	 */
	public void toggleDisabled() {
		this.disabled = !disabled;
	}

	// Getters
	public int getLineResponsible() {
		return this.lineResponsible;
	}
	public boolean isDisabled() {
		return this.disabled;
	}
	public int getOrientation() {
		return this.orientation;
	}

}
