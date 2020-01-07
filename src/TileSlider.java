import javax.swing.*;

/**
 * This class represents the tile slider than inserts the extra tile
 * and shifts the row or column the slider is responsible for
 *
 * @author Jier Chen
 */
public class TileSlider extends JLabel {

	// Type of slider:
	// 'R' - row slider
	// 'C' - column slider
	private char type;

	// row or column line the slider is responsible for
	private int lineResponsible;

	// Direction the tiles are pushed
	// 'U' - up
	// 'D' - down
	private char direction;

	/**
	 * Constructor
	 *
	 * @param type slider type
	 * @param lineResponsible row or column index responsible for
	 * @param direction direction the tiles are pushed
	 * @param image png to display in Swing
	 */
	public TileSlider(char type, int lineResponsible, char direction, ImageIcon image) {
		this.type = type;
		this.lineResponsible = lineResponsible;
		this.direction = direction;

		setIcon(image);
	}

	// Getters
	public char getType() {
		return this.type;
	}

	public int getLineResponsible() {
		return this.lineResponsible;
	}

	public int getDirection() {
		return this.direction;
	}


}
