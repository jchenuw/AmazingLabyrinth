package view;

import model.Game;
import model.Player;
import model.Treasure;

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

	// Tile slider arrows
	private TileSlider[] tileSliders = new TileSlider[12];

	// Visual representation of the cards the players hold
	private JLabel[] cardDisplays;
	private JLabel[] cardTreasureImages;

	// Game information GUI
	private JLabel playerTurn = new JLabel("Player Turn:");

	public GameDisplay(Game game) {
		this.game = game;

		// Properties of view.GameDisplay
		setTitle("The Amazing Labyrinth");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1370, 800);
		setLayout(null);
		setVisible(true);
		setLocationRelativeTo(null);
		getContentPane().setBackground(new Color(150, 220, 220));

		// GUI initialization
		boardDisplay = new BoardDisplay(game.getBoard());
		extraTileDisplay = new TileDisplay();
		extraTileDisplay.update(game.getBoard().getExtraTile());
		setupTileSliders();
		setupCardGUIs();
		setupGameInfo();

		// Properties of board GUI
		boardDisplay.setLocation(100,80);

		// Properties of insertable tile GUI
		extraTileDisplay.setSize(BoardDisplay.TILES_SIDE_LENGTH, BoardDisplay.TILES_SIDE_LENGTH);
		extraTileDisplay.setLocation(1035, 180);

		// add components to view.GameDisplay
		add(boardDisplay);
		add(extraTileDisplay);
	}

	private void setupGameInfo() {
		for(int i = 0; i < game.getNumPlayers(); i++) {
			JLabel playerImage = new JLabel(new ImageIcon(getClass().getResource(
					"../resources/players/colour" + i + ".png")));

			playerImage.setSize(70, 110);
			playerImage.setLocation(cardDisplays[i].getX() - 80, cardDisplays[i].getY() + 20);

			add(playerImage);
		}
	}

	private void setupCardGUIs() {
		cardDisplays = new JLabel[game.getNumPlayers()];
		cardTreasureImages = new JLabel[cardDisplays.length];

		for(int i = 0; i < cardDisplays.length; i++) {
			cardDisplays[i] = new JLabel(new ImageIcon(getClass().getResource("../resources/card.png")));
			cardTreasureImages[i] = new JLabel();

			// Properties of empty card
			cardDisplays[i].setSize(96, 150);
			cardDisplays[i].setLocation(950 + 260 * (i / 2), 350 + 200 * (i % 2));

			// Properties of card treasure images
			cardTreasureImages[i].setSize(50, 50);
			cardTreasureImages[i].setLocation(23, 50);

			cardDisplays[i].add(cardTreasureImages[i]);
			add(cardDisplays[i]);
		}

		updateCardDisplay();
	}

	public void updateCardDisplay() {
		Player[] players = game.getBoard().getPlayers();

		for(int i = 0; i < cardDisplays.length; i++) {

			// clear all treasures
			cardTreasureImages[i].setIcon(null);

			if(!players[i].hasCollectedAll()) {
				Treasure cardTreasure = players[i].getCurrentCard().getTreasure();

				cardTreasureImages[i].setIcon(new ImageIcon(getClass().getResource(
						"../resources/treasures/" + cardTreasure.getTreasureNum() + ".png")));


				cardDisplays[i].revalidate();
				cardDisplays[i].repaint();
			}
		}
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
				tileSliders[i].setLocation(100 + (2 * order + 1) * tileLength, 80 - tileLength);
			}
			// Setup right sliders
			else if (i >= 3 && i < 6) {
				tileSliders[i] = new TileSlider((2 * order) + 1, 3);
				tileSliders[i].setLocation(100 + boardLength, 80 + (2 * order + 1) * tileLength);
			}
			// Setup bottom sliders
			else if (i >= 6 && i < 9) {
				tileSliders[i] = new TileSlider((2 * order) + 1, 0);
				tileSliders[i].setLocation(100 + (2 * order + 1) * tileLength, 80 + boardLength);
			}
			// Setup left sliders
			else {
				tileSliders[i] = new TileSlider((2 * order) + 1, 1);
				tileSliders[i].setLocation(100 - tileLength, 80 + (2 * order + 1) * tileLength);
			}

			// Add to GUI
			add(tileSliders[i]);
		}

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
