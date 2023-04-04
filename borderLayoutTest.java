import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Panel;
import java.io.IOException;

public class borderLayoutTest {

    int width;
    int height;

    public static void main(String[] args) {
        JFrame frame1 = new JFrame();
        frame1.setLayout(new BorderLayout());

        JPanel panelNorth = new JPanel();
        panelNorth.setOpaque(false);
        panelNorth.setPreferredSize(new Dimension(500, 100));

        JPanel panelSouth = new JPanel();
        panelSouth.setOpaque(false);
        panelSouth.setPreferredSize(new Dimension(300, 100));

        JPanel panelEast = new JPanel();
        panelEast.setOpaque(false);
        panelEast.setPreferredSize(new Dimension(100, 300));

        JPanel panelWest = new JPanel();
        panelWest.setOpaque(false);
        panelWest.setPreferredSize(new Dimension(100, 300));

        JPanel panelCenter = new JPanel() {
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
        panelCenter.setSize(frame1.getWidth(), frame1.getHeight());
        panelCenter.setLayout(new BorderLayout());

        // CREATE ANOTHER JPANEL AND PUT THIS INSIDE THE CENTER PANEL IN BORDER LAYOUT
        JPanel panel1 = new JPanel();
        panel1.setOpaque(false);
        panel1.setPreferredSize(new Dimension(300, 250));

        JPanel panel2 = new JPanel();
        panel2.setOpaque(false);
        panel2.setPreferredSize(new Dimension(300, 250));

        JPanel panel3 = new JPanel();
        panel3.setOpaque(false);
        panel3.setPreferredSize(new Dimension(600, 300));

        JPanel panel4 = new JPanel();
        panel4.setOpaque(false);
        panel4.setPreferredSize(new Dimension(600, 300));

        JPanel panel5 = new JPanel(new BorderLayout());
        panel5.setOpaque(false);
        panel5.setPreferredSize(new Dimension(300, 300));

        JPanel newPanel = new JPanel();
        newPanel.setOpaque(false);
        BoxLayout layoutBox = new BoxLayout(newPanel, BoxLayout.Y_AXIS);
        newPanel.setLayout(layoutBox);

        JLabel title = new JLabel("Ropersors");
        title.setFont(new Font("8-bit Arcade In", Font.PLAIN, 150));
        title.setSize(800, 150);
        title.setLocation(450, 50);
        title.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        // title.setForeground(Color.white);
        newPanel.add(title);

        JLabel subtitle = new JLabel("a Rock Paper Scissors Game");
        subtitle.setFont(new Font("8-bit Arcade In", Font.PLAIN, 40));
        // subtitle.setForeground(Color.white);
        subtitle.setSize(800, 150);
        subtitle.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        newPanel.add(subtitle);

        panel1.add(newPanel, BorderLayout.NORTH);

        panel5.add(panel1, BorderLayout.NORTH);
        panel5.add(panel2, BorderLayout.SOUTH);
        panel5.add(panel3, BorderLayout.EAST);
        panel5.add(panel4, BorderLayout.WEST);
        panelCenter.add(panel5);

        JPanel buttonPanel = new JPanel(new GridLayout(3, 1));
        JButton button1 = new JButton("Player vs. AI");
        JButton button2 = new JButton("Player vs. Player");
        JButton button3 = new JButton("Exit to Desktop");

        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);

        panel5.add(buttonPanel, BorderLayout.CENTER);
        panelCenter.add(panelNorth, BorderLayout.NORTH);
        panelCenter.add(panelSouth, BorderLayout.SOUTH);
        panelCenter.add(panelEast, BorderLayout.EAST);
        panelCenter.add(panelWest, BorderLayout.WEST);
        frame1.add(panelCenter, BorderLayout.CENTER);

        frame1.setVisible(true);
    }
}
