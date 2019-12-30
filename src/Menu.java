import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame implements ActionListener {
    JFrame menu;
    JButton[] b = new JButton[3];

    Menu() {
        JLabel title = new JLabel("Test");
        title.setBounds(640, 50, 70, 70);
        title.setVisible(true);

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

        add(title);

        setSize(1280, 720);
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b[0]) {
            System.out.println("Start Pressed");
        }

        if (e.getSource() == b[1]) {
            System.out.println("Settings Pressed");
        }

        if (e.getSource() == b[2]) {
            System.out.println("Exit Pressed");
            dispose();
        }

    }
}
