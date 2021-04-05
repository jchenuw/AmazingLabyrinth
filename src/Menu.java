import controller.GameController;
import model.Game;
import view.GameDisplay;

import java.awt.Color;
import java.awt.event.*;

import javax.swing.*;

/**
 * Game menu GUI that allows players to start, choose AIs, and exit game
 */
public class Menu extends JFrame implements ActionListener {

	// Buttons for menu options
	private JButton startButton = new JButton();
	private JButton[] aiButton = new JButton[4];
	private JButton exitButton = new JButton();

	// Colors for Players
	private Color[] colors = new Color[4];

	// AI status
	private boolean[] isAI = new boolean[4];

	// Constructor
	public Menu() {

		// Setup menu frame properties
		setTitle("Amazing Labyrinth");
		setSize(1370, 800);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(new Color(150, 220, 220));
		setVisible(true);
		setLocationRelativeTo(null);

		// Setup colors
		colors[0] = new Color(142, 30, 193);
		colors[1] = new Color(51, 153, 255);
		colors[2] = new Color(200, 30, 30);
		colors[3] = new Color(0, 153, 0);

		// Properties of start button
		startButton.setBounds(555, 265, 250, 70);
		startButton.setBorderPainted(false);
		startButton.addActionListener(this);
		startButton.setText("Start");
		startButton.setBackground(new Color(100, 100, 100));
		startButton.setFocusPainted(false);

		// setup player button properties
		for (int i = 0; i < aiButton.length; i++) {

			aiButton[i] = new JButton();

			// Properties of AIButtons
			aiButton[i].setBounds(420 + 130 * i, 350, 130, 70);
			aiButton[i].setBorderPainted(false);
			aiButton[i].addActionListener(this);
			aiButton[i].setText("Player");
			aiButton[i].setBackground(colors[i]);
			aiButton[i].setFocusPainted(false);

			this.add(aiButton[i]);
		}

		// Properties of exit button
		exitButton.setBounds(555, 435, 250, 70); // location and size
		exitButton.setBorderPainted(false); // no borders
		exitButton.addActionListener(this); // add actionlistener
		exitButton.setText("Exit");
		exitButton.setBackground(new Color(100, 100, 100));
		exitButton.setFocusPainted(false);

		// setup intro image properties
		this.setBounds(0, 0, 1366, 768);

		this.add(startButton);
		this.add(exitButton);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// if action is the start button
		if (e.getSource() == startButton) {

			// create board
			Game game = new Game(4, isAI);
			GameDisplay gameDisplay = new GameDisplay(game);
			GameController gameController = new GameController(gameDisplay, game);

			// remove the frame
			this.dispose();
		}

		// scroll through all possible ai buttons
		for (int i = 0; i < aiButton.length; i++) {

			// if actions is one of the four bottons
			if (e.getSource() == aiButton[i]) {

				// set player to AI, if player is currently a human
				if (isAI[i] == false) {
					aiButton[i].setText("AI");
					isAI[i] = true;
				}

				// set player to human, if player is currently an AI
				else if (isAI[i] == true) {
					aiButton[i].setText("Player");
					isAI[i] = false;
				}
			}

		}

		// if action is the exit button
		if (e.getSource() == exitButton) {

			// remove the frame
			this.dispose();
		}

	}

}