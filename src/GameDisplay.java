import javax.swing.*;

public class GameDisplay extends JFrame {

	// Underlying game model
	//private Game game;
	private JFrame GameDisplay;

	// The extra tile
	private TileDisplay ExtraTileDisplay = new TileDisplay();

	// The board
	private BoardDisplay BoardDisplay;

	public GameDisplay(Board board) {
		// setup board
		BoardDisplay = new BoardDisplay(board);
		BoardDisplay.setLocation(200,50);
		add(BoardDisplay);

		// setup extra tile
		ExtraTileDisplay.setBounds(50, 300, 75, 75);
		ExtraTileDisplay.setText("Extra");
		ExtraTileDisplay.setEnabled(false);
		add(ExtraTileDisplay);

		// setup game frame
		setTitle("GameDisplay");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(1280, 720);
		setLayout(null);
		setVisible(true);
	}
}
