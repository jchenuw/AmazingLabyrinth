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

	public GameController(GameDisplay gameDisplay) {
		this.gameDisplay = gameDisplay;
		this.game = gameDisplay.getGame();

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
			System.out.println(slider.getLineResponsible());
		}
	}

	public class RotateListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// Rotate extra tile
			game.getExtraTile().rotate();

			// Update extraTileDisplay
			gameDisplay.getExtraTileDisplay().update(game.getExtraTile());
		}
	}

	public class MoveListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			TileImage tileImage = (TileImage) e.getSource();

			System.out.println(tileImage.getRow() + " " + tileImage.getCol());

		}
	}
}
