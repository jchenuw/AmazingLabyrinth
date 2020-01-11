import javax.swing.*;

public class GameDisplay extends JFrame {

	// Underlying game model
	private Game game;
	private JFrame GameDisplay;

	// The extra tile
	private TileDisplay ExtraTileDisplay;

	// The board
	private Board board;
	private BoardDisplay BoardDisplay;

	public static ImageLoader ImageLoader;

	public GameDisplay() {
		game = new Game(4);

		ImageLoader = new ImageLoader();

		// setup board
		board = game.getBoard();
		BoardDisplay = new BoardDisplay(board);
		BoardDisplay.setLocation(200,50);
		add(BoardDisplay);

		// setup extra
		ExtraTileDisplay = new TileDisplay(new LTile(0,0,0));
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
