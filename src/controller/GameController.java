package controller;

import model.*;
import view.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameController {

	private GameDisplay gameDisplay;
	private Game game;

	private BoardDisplay boardDisplay;
	private Board board;

	private Game.TurnState turnState;

	public GameController(GameDisplay gameDisplay, Game game) {
		this.gameDisplay = gameDisplay;
		this.game = game;

		this.boardDisplay = gameDisplay.getBoardDisplay();
		this.board = game.getBoard();

		this.turnState = game.getTurnState();

		init();
	}

	public void init() {
		// Add slider listeners
		gameDisplay.addSlideListeners(new SlideListener());

		// Add rotate listener to extraTileDisplay
		gameDisplay.getExtraTileDisplay().addRotateListener(new RotateListener());

		// Add move listener to tileDisplays in
		boardDisplay.addMoveListeners(new MoveListener());
	}

	public class SlideListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			TileSlider slider = (TileSlider) e.getSource();

			game.slideExtraTileTurn(slider.getOrientation(), slider.getLineResponsible());

			boardDisplay.updateBoard();
			gameDisplay.getExtraTileDisplay().update(board.getExtraTile());
			boardDisplay.updatePlayerViews();
		}
	}

	public class RotateListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// Rotate extra tile
			board.getExtraTile().rotate();

			// Update extraTileDisplay
			gameDisplay.getExtraTileDisplay().update(board.getExtraTile());
		}
	}

	public class MoveListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			TileDisplay[][] tileDisplays = boardDisplay.getTileDisplays();

			// Scroll through all Tile JButtons
			for(int row = 0; row < tileDisplays.length; row++) {
				for(int col = 0; col < tileDisplays.length; col++) {
					if(e.getSource() == tileDisplays[row][col].getTileImage()) {
						game.movePlayerTurn(row, col);

						boardDisplay.updateBoard();
						boardDisplay.updatePlayerViews();
					}
				}
			}

		}
	}
}
