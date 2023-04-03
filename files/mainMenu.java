package files;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class mainMenu extends JPanel implements ActionListener {
    int width;
    int height;
    Clip clip, pveSound;

    JButton PVP, PVE, exit;
    JPanel lobbyPanel, mode1Panel, mode2Panel;

    music gameMusic = new music();
    game mode1Game = new game();
    gamev2 mode2Game = new gamev2();

    mainMenu() {

        // LOBBY PANEL CONTAINS THE TITLE AND THE SUBTITLE AS FOR NOW
        lobbyPanel = new JPanel();
        lobbyPanel.setSize(1550, 830);
        lobbyPanel.setLayout(null);
        gameMusic.playLobby();

        mode1Panel = new JPanel();
        mode1Panel.setSize(1550, 830);
        mode1Panel.setLayout(null);
        mode1Panel.add(mode1Game);
        mode1Panel.setVisible(false);
        this.add(mode1Panel);

        mode2Panel = new JPanel();
        mode2Panel.setSize(1550, 830);
        mode2Panel.setLayout(null);
        mode2Panel.add(mode2Game);
        mode2Panel.setVisible(false);
        this.add(mode2Panel);

        JLabel title = new JLabel("Ropersors");
        title.setFont(new Font("8-bit Arcade In", Font.PLAIN, 150));
        title.setSize(800, 150);
        title.setLocation(450, 50);
        // title.setForeground(Color.white);
        lobbyPanel.add(title);

        JLabel subtitle = new JLabel("a Rock Paper Scissors Game");
        subtitle.setFont(new Font("8-bit Arcade In", Font.PLAIN, 40));
        // subtitle.setForeground(Color.white);
        subtitle.setSize(800, 150);
        subtitle.setLocation(500, 100);
        lobbyPanel.add(subtitle);

        PVE = new JButton();
        PVE.setText("Player Versus Computer");
        PVE.setSize(200, 25);
        PVE.setLocation(690, 300);
        PVE.setFocusable(false);
        PVE.addActionListener(this);
        lobbyPanel.add(PVE);

        PVP = new JButton();
        PVP.setText("Player Versus Player");
        PVP.setSize(200, 25);
        PVP.setLocation(690, 350);
        PVP.setFocusable(false);
        PVP.addActionListener(this);
        lobbyPanel.add(PVP);

        exit = new JButton();
        exit.setText("Exit to desktop");
        exit.setSize(200, 25);
        exit.setLocation(690, 400);
        exit.addActionListener(this);
        exit.setFocusable(false);
        lobbyPanel.add(exit);

        ImageIcon background = new ImageIcon("main menu.png");
        Image backkground = background.getImage();
        Image bgResized = backkground.getScaledInstance(1535, 792, java.awt.Image.SCALE_SMOOTH);
        background = new ImageIcon(bgResized);

        // ISET NATIN YUNG SA BACKGROUND NAMAN
        JLabel backgroundd = new JLabel();
        backgroundd.setIcon(background);
        backgroundd.setLocation(0, -143);
        backgroundd.setSize(1920, 1080);

        JPanel bg = new JPanel();
        bg.setSize(1920, 1080);
        bg.setLocation(0, 0);
        bg.add(backgroundd);
        bg.setLayout(null);
        lobbyPanel.add(bg);
        this.add(lobbyPanel);

        // CONFIGURATION NG GUI
        this.setLayout(null);
        this.setVisible(true);
        width = this.getWidth();
        height = this.getHeight();
        this.setSize(1550, 830);
        // this.setUndecorated(true);
        ImageIcon icon = new ImageIcon("6793733.png");

    }

    public static void main(String[] args) {
        mainMenu welcome = new mainMenu();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gameMusic.stopLobby();
        try {
            if (e.getSource() == PVP) {

                lobbyPanel.setVisible(false);
                mode2Panel.setVisible(true);

                mode2Game.pleasePlay();
                PVE.setVisible(false);
                PVP.setVisible(false);
                exit.setVisible(false);

            } else if (e.getSource() == PVE) {

                lobbyPanel.setVisible(false);
                mode1Panel.setVisible(true);

                mode1Game.pleasePlay();
                PVE.setVisible(false);
                PVP.setVisible(false);
                exit.setVisible(false);
            } else if (e.getSource() == exit) {

            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }

}
