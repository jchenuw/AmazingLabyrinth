package util;
import model.Board;

import java.util.ArrayList;
import java.util.List;

public class SlideCommand implements Command {

	// The only rows and columns that are shiftable
	private static final List<Integer> SHIFTABLE_LINES = new ArrayList<Integer>(List.of(1, 3, 5));

	private Board board;
	private int orientation;
	private int line;

	public SlideCommand(Board board, int orientation, int line) {
		this.board = board;
		this.orientation = orientation;
		this.line = line;
	}

	@Override
	public void execute() {
		switch (orientation) {
			case 0 :
				board.shiftColUp(line);
				break;

			case 1:
				board.shiftRowRight(line);
				break;

			case 2:
				board.shiftColDown(line);
				break;

			case 3:
				board.shiftRowLeft(line);
				break;

			default:
		}
	}

	@Override
	public boolean isLegal() {
		return SHIFTABLE_LINES.contains(line);
	}
}
