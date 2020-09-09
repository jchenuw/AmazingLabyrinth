package model;

import util.MoveCommand;

import java.util.Random;

public class CPUPlayer extends Player {

	private final double MAX_PRIORITY = 900;
	private final double MIN_PRIORITY = -900;

	// AI's target tile location
	private int targetRow;
	private int targetCol;

	// AI's selected tile location
	private int selectedRow;
	private int selectedCol;

	// AI's selected slider
	private int selectedSlider = 0;

	// AI's priority scale
	private double[][] priority = new double[7][7];

	// Copy of current board
	private Board boardCopy;

	// Previous tile AI was on
	private Tile previousTile;

	public CPUPlayer(int row, int col, String color){
		super(row, col, color);
	}

	public void moveSimulation(Board board) {

		Tile[][] boardTiles = board.getTiles();

		calculateTarget(boardTiles);

		MoveCommand AImoveCommand =
				new MoveCommand(boardCopy, this, 0,0);

		double highest = 0;

		// Check entire board
		for(int row = 0; row < 7; row++) {
			AImoveCommand.targetRow = row;

			for(int col = 0; col < 7; col++) {
				AImoveCommand.targetCol = col;

				// initalize priority to 0
				priority[row][col] = 0;

				if(AImoveCommand.isLegal()) {
					if(row == targetRow && col == targetCol) {
						priority[row][col] = MAX_PRIORITY;
					} else {
						priority[row][col] = calcRelative(row, col);
					}
				} else {
					priority[row][col] = MIN_PRIORITY;
				}

				if(boardTiles[row][col] == previousTile) {
					priority[row][col] -= 100;
				}

				// set highest priority square to move to
				if(priority[row][col] > highest) {
					highest = priority[row][col];
					selectedRow = row;
					selectedRow = col;
				}
			}
		}
	}

	private void calculateTarget(Tile[][] boardTiles) {
		outerloop: for(int row = 0; row < 7; row++) {
			for(int col = 0; col < 7; col++) {
				if(this.getCurrentCard() != null
						&& boardTiles[row][col].hasTreasure()
						&& this.getCurrentCard().getTreasure() == boardTiles[row][col].getTreasure()) {
					targetRow = row;
					targetCol = col;
					break outerloop;
				} else if (this.hasCollectedAll() && boardTiles[row][col] == this.getHomeTile()) {
					targetRow = row;
					targetCol = col;
					break outerloop;
				}
			}
		}
	}

	private double calcRelative(int currentRow, int currentCol) {
		double distance = Math.sqrt(Math.pow(currentRow - targetRow, 2)
				+ Math.pow(currentCol - targetCol, 2));

		return 150 - Math.pow(distance, 1.5);
	}

	/**
	 * AI calculate which slider to use
	 * @param board
	 */
	public void slideSimulation(Board board) {
		Random rand = new Random();
		// placeholder
		selectedSlider = rand.nextInt(12);
	}
}
