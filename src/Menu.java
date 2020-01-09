import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame implements ActionListener {
    JFrame menu;
    JButton[] b = new JButton[3];

    Menu() {
        // setup title
        JLabel title = new JLabel("Menu");
        title.setBounds(640, 50, 70, 70);
        title.setVisible(true);
        add(title);

        // setup Menu buttons
        for (int i = 0; i < 3; i++) {
            b[i] = new JButton();
            b[i].setBounds(640, 170 + (i * 100), 100, 50);
            b[i].setVisible(true);
            b[i].addActionListener(this);
            add(b[i]);
        }

        b[0].setText("Start");
        b[1].setText("Settings");
        b[2].setText("Exit");

        // setup Menu
        setTitle("Menu");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1280, 720);
        setLayout(null);
        setVisible(true);
    }

    // Action performed for buttons
    @Override
    public void actionPerformed(ActionEvent e) {
        // if start button is pressed start game
        if (e.getSource() == b[0]) {
            System.out.println("Start Pressed");
            GameDisplay GameDisplay = new GameDisplay();
            dispose();
        }

        // if settings is pressed
        if (e.getSource() == b[1]) {
            System.out.println("Settings Pressed");
        }

        // if exit is pressed exit
        if (e.getSource() == b[2]) {
            System.out.println("Exit Pressed");
            dispose();
        }
    }
}
