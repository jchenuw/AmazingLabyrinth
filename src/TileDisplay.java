import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TileDisplay extends JButton implements ActionListener{

	// The tile this image represents
	private Tile tile;
	private JButton TileDisplay;

	public TileDisplay() {
		setText("tile");
		addActionListener(this);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
}
