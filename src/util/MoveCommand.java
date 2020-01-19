package util;

import model.Board;
import model.Player;
import model.Tile;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class MoveCommand implements Command {

	public Board board;
	public Tile[][] tiles;
	public Player player;
	public int targetRow;
	public int targetCol;

	public MoveCommand(Board board, Player player, int targetRow, int targetCol) {
		this.board = board;
		this.tiles = board.getTiles();
		this.player = player;
		this.targetRow = targetRow;
		this.targetCol = targetCol;
	}

	@Override
	public void execute() {
		board.movePlayer(player, targetRow, targetCol);
	}

	/**
	 * Determine if player can move to target location by using Breadth-first search
	 * to find target tile
	 *
	 * @return if tile can be reached
	 */
	@Override
	public boolean isLegal() {
		// Variable to keep track of visited tiles
		boolean[][] visited = new boolean[tiles.length][tiles.length];

		// BFS queue
		Queue<Tile> queue = new LinkedList<Tile>();

		// Visit initial tile
		visited[player.getRow()][player.getCol()] = true;
		queue.add(tiles[player.getRow()][player.getCol()]);

		// Search through queue
		while(queue.size() != 0) {

			Tile currentTile = queue.poll();

			// Tile is reachable
			if(targetRow == currentTile.getRow() && targetCol == currentTile.getCol()) {
				return true;
			}

			// Get all adjacent tiles from currentTile
			ArrayList<Tile> adjTiles = currentTile.getAdjTiles();

			// Check all adjacent tiles to see if an adjacent tile
			// has the target row and column.
			for(int i = 0; i < adjTiles.size(); i++) {

				Tile adjTile = adjTiles.get(i);
				int row = adjTile.getRow();
				int col = adjTile.getCol();

				// Check adjacent tile only if it has not been visited already
				if(!visited[row][col]) {

					// Mark this tile as visited and enqueue it
					queue.add(adjTile);
					visited[row][col] = true;
				}
			}
		}

		return false;
	}
}
