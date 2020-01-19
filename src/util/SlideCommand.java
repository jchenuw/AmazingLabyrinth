package util;

import model.Board;
import view.TileSlider;

public class SlideCommand implements Command{

	private TileSlider slider;
	private Board board;

	public SlideCommand(Board board, TileSlider slider) {
		this.slider = slider;
	}

	@Override
	public void execute() {
		int line = slider.getLineResponsible();
		int type = slider.getOrientation();

		switch (type) {
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
		if(slider.isDisabled()) {
			return false;
		}

		return true;
	}
}
