import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import withOOP.maingame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class mainMenu extends JFrame implements ActionListener {

    private Image backgroundImage;
    int width;
    int height;

    mainMenu() {

        JPanel contentPane = new JPanel() {
            Image backgroundImage;

            {
                try {
                    backgroundImage = ImageIO.read(getClass().getResource("main menu.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), null);
            }
        };
        contentPane.setSize(this.getWidth(), this.getHeight());
        this.setContentPane(contentPane);

        JLabel title = new JLabel("Ropersors");
        title.setFont(new Font("8-bit Arcade In", Font.PLAIN, 150));
        title.setSize(800, 150);
        title.setLocation(450, 50);
        title.setForeground(Color.white);
        this.add(title);

        JLabel subtitle = new JLabel("a Rock Paper Scissors Game");
        subtitle.setFont(new Font("8-bit Arcade In", Font.PLAIN, 40));
        subtitle.setForeground(Color.white);
        subtitle.setSize(800, 150);
        subtitle.setLocation(500, 100);
        this.add(subtitle);

        JButton PVE = new JButton();
        PVE.setText("Player Versus Computer");
        PVE.setSize(200, 25);
        PVE.setBackground(Color.BLUE);
        PVE.setLocation(690, 300);
        PVE.addActionListener(this);
        this.add(PVE);

        JButton PVP = new JButton();
        PVP.setText("Player Versus Player");
        PVP.setSize(200, 25);
        PVP.setLocation(690, 350);
        PVP.addActionListener(e -> subtitle.setText("Unavailable as of now"));
        this.add(PVP);

        JButton exit = new JButton();
        exit.setText("Exit to desktop");
        exit.setSize(200, 25);
        exit.setLocation(690, 400);
        exit.addActionListener(e -> this.dispose());
        this.add(exit);

        // CONFIGURATION NG GUI
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Ropersors");
        this.setLayout(null);
        this.setVisible(true);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        width = this.getWidth();
        height = this.getHeight();
        this.setSize(1550, 830);
        this.setResizable(false);
        // this.setUndecorated(true);
        ImageIcon icon = new ImageIcon("6793733.png");
        this.setIconImage(icon.getImage());

    }

    public static void main(String[] args) {

        mainMenu welcome = new mainMenu();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.dispose();
        maingame game = new maingame();
    }
}
