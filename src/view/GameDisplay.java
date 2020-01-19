package view;

import model.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GameDisplay extends JFrame {

	// Underlying game model
	private Game game;

	// The extra tile
	private TileDisplay extraTileDisplay;

	// The board
	private BoardDisplay boardDisplay;

	// model.Tile slider arrows
	private TileSlider[] tileSliders = new TileSlider[12];

	public GameDisplay() {
		game = new Game(4);

		// Properties of view.GameDisplay
		setTitle("The Amazing Labyrinth");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1370, 830);
		setLayout(null);
		setVisible(true);
		setLocationRelativeTo(null);
		getContentPane().setBackground(new Color(150, 220, 220));

		// GUI initialization
		boardDisplay = new BoardDisplay(game.getBoard());

		extraTileDisplay = new TileDisplay(-1, -1);
		extraTileDisplay.update(game.getBoard().getExtraTile());

		setupTileSliders();

		// Properties of board GUI
		boardDisplay.setLocation(100,100);

		// Properties of insertable tile GUI
		extraTileDisplay.setSize(BoardDisplay.TILES_SIDE_LENGTH, BoardDisplay.TILES_SIDE_LENGTH);
		extraTileDisplay.setLocation(1035, 180);

		// add components to view.GameDisplay
		add(boardDisplay);
		add(extraTileDisplay);
	}

	private void setupTileSliders() {
		int tileLength = BoardDisplay.TILES_SIDE_LENGTH;
		int boardLength = BoardDisplay.BOARD_SIDE_LENGTH;

		for(int i = 0; i < tileSliders.length; i++) {
			// order of current slider within its group
			int order = i % 3;

			// Setup top sliders
			if(i >= 0 && i < 3) {
				tileSliders[i] = new TileSlider((2 * order) + 1, 2);
				tileSliders[i].setLocation(100 + (2 * order + 1) * tileLength, 100 - tileLength);
			}
			// Setup right sliders
			else if (i >= 3 && i < 6) {
				tileSliders[i] = new TileSlider((2 * order) + 1, 3);
				tileSliders[i].setLocation(100 + boardLength, 100 + (2 * order + 1) * tileLength);
			}
			// Setup bottom sliders
			else if (i >= 6 && i < 9) {
				tileSliders[i] = new TileSlider((2 * order) + 1, 0);
				tileSliders[i].setLocation(100 + (2 * order + 1) * tileLength, 100 + boardLength);
			}
			// Setup left sliders
			else {
				tileSliders[i] = new TileSlider(5 - (2 * order), 1);
				tileSliders[i].setLocation(100 - tileLength, 100 + (2 * order + 1) * tileLength);
			}

			// Add to GUI
			add(tileSliders[i]);
		}

	}

	public void updateBoard(){
		boardDisplay.updateBoard();
	}

	public void addSlideListeners(ActionListener sliderListener) {
		for(int i = 0; i < tileSliders.length; i++) {
			tileSliders[i].addActionListener(sliderListener);
		}
	}

	// Getters
	public Game getGame() {
		return game;
	}

	public BoardDisplay getBoardDisplay() {
		return boardDisplay;
	}

	public TileDisplay getExtraTileDisplay() {
		return extraTileDisplay;
	}

	public TileSlider[] getTileSliders() {
		return tileSliders;
	}

}
