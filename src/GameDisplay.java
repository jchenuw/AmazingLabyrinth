import javax.swing.*;

public class GameDisplay extends JFrame {

	// Underlying game model
	private Game game;
	private JFrame GameDisplay;

	// The extra tile
	private TileDisplay extraTileDisplay;

	// The board
	private Board board;
	private BoardDisplay boardDisplay;

	public static ImageLoader ImageLoader;

	public GameDisplay() {
		game = new Game(4);

		ImageLoader = new ImageLoader();

		// setup board
		board = game.getBoard();
		boardDisplay = new BoardDisplay(board);
		boardDisplay.setLocation(200,50);
		add(boardDisplay);

		// setup extra
		extraTileDisplay = new TileDisplay(board.getExtraTile());
		extraTileDisplay.setBounds(50, 300, 75, 75);
		//ExtraTileDisplay.setEnabled(false);
		add(extraTileDisplay);

		// setup game frame
		setTitle("GameDisplay");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(1280, 720);
		setLayout(null);
		setVisible(true);
	}

	public void updateBoard(){
		boardDisplay.updateBoard();
	}
}
