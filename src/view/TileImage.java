package view;

import javax.swing.*;

public class TileImage extends JButton {
	private int row;
	private int col;

	public TileImage(int row, int col) {
		this.row = row;
		this.col = col;
	}

	// Getters
	public int getRow() {
		return row;
	}
	public int getCol() {
		return col;
	}
}
