package resources;

import javax.swing.ImageIcon;
import javax.swing.;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class gamesu extends JFrame {

    JButton button;
    JLabel label;
    JPanel intro;
    JPanel subintro;

    gamesu() {

        JFrame frame = new JFrame();

        button = new JButton("Start");
        button.setFocusPainted(false);
        button.setBounds(200, 300, 100, 50);
        button.addActionListener(e -> button(e));
        button.addActionListener(e -> frame.dispose());

        label = new JLabel();

        label = new JLabel("Rock Paper Scisors");
        label.setLocation(50, 100);
        label.setSize(500, 100);

        label.setFont(new Font("Impact", Font.PLAIN, 52));

        frame.add(label);
        frame.add(button);

        ImageIcon icon = new ImageIcon("6793733.png");
        frame.setIconImage(icon.getImage());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Rock Paper Scisors");
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setSize(500, 500);
        frame.setResizable(false);

    }

    private void button(java.awt.event.ActionEvent evt) {

        JFrame frame2 = new JFrame();

        JLabel askMode = new JLabel("Select Mode:");
        askMode.setFont(new Font("Impact", Font.PLAIN, 52));
        askMode.setLocation(100, 100);
        askMode.setSize(500, 100);

        frame2.add(askMode);

        JButton button1 = new JButton("PVE");
        button1.setLocation(100, 300);
        button1.setSize(100, 50);
        button1.setFocusable(false);
        button1.addActionListener(e -> button2(e));
        button1.addActionListener(e -> frame2.dispose());

        JButton button2 = new JButton("PVP");
        button2.setLocation(300, 300);
        button2.setSize(100, 50);
        button2.setFocusable(false);
        button2.addActionListener(e -> button3(e));
        button2.addActionListener(e -> frame2.dispose());

        frame2.add(button2);
        frame2.add(button1);

        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.setTitle("Rock Paper Scisors");
        frame2.setLayout(null);
        frame2.setVisible(true);
        frame2.setSize(500, 500);
        frame2.setResizable(false);
        ImageIcon icon = new ImageIcon("6793733.png");
        frame2.setIconImage(icon.getImage());

    }

    private void button2(java.awt.event.ActionEvent evt) {
        JFrame frame3 = new JFrame();
        JLabel warning = new JLabel("This mode is Under development. Please come back later");
        warning.setSize(500, 500);
        frame3.add(warning);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> button(e));
        backButton.setLocation(0, 0);
        backButton.setFocusable(false);
        backButton.setSize(65, 25);
        backButton.addActionListener(e -> frame3.dispose());
        frame3.add(backButton);

        frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame3.setTitle("Rock Paper Scisors");
        frame3.setLayout(null);
        frame3.setVisible(true);
        frame3.setSize(500, 500);
        frame3.setResizable(false);
        ImageIcon icon = new ImageIcon("6793733.png");
        frame3.setIconImage(icon.getImage());

    }

    private void button3(java.awt.event.ActionEvent evt) {
        JFrame frame4 = new JFrame();

        JLabel name1 = new JLabel();
        name1.setText("Enter Player 1's name:");
        name1.setSize(127, 25);
        name1.setLocation(200, 75);
        frame4.add(name1);

        JLabel name2 = new JLabel();
        name2.setText("Enter Player 2's name:");
        name2.setSize(127, 25);
        name2.setLocation(200, 175);
        frame4.add(name2);

        JTextField field1 = new JTextField();
        field1.setSize(125, 25);
        field1.setLocation(200, 100);
        frame4.add(field1);

        JTextField field2 = new JTextField();
        field2.setSize(125, 25);
        field2.setLocation(200, 200);
        frame4.add(field2);

        JButton submitButton = new JButton("Submit");
        submitButton.setSize(100, 25);
        submitButton.setFocusable(false);
        submitButton.setLocation(200, 300);
        frame4.add(submitButton);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> button(e));
        backButton.setLocation(0, 0);
        backButton.setFocusable(false);
        backButton.setSize(65, 25);
        backButton.addActionListener(e -> frame4.dispose());
        frame4.add(backButton);

        frame4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame4.setTitle("Rock Paper Scisors");
        frame4.setLayout(null);
        frame4.setVisible(true);
        frame4.setSize(500, 500);
        frame4.setResizable(false);
        ImageIcon icon = new ImageIcon("6793733.png");
        frame4.setIconImage(icon.getImage());

    }

}