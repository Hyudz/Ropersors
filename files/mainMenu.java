package files;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class mainMenu extends JFrame implements ActionListener {

    int width;
    int height;
    Clip clip;

    JButton PVP, PVE, exit;

    mainMenu() {
        File file = new File("sounds\\lobby.WAV");
        try {
            AudioInputStream sound = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(sound);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            e.printStackTrace();
        }

        clip.start();

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

        PVE = new JButton();
        PVE.setText("Player Versus Computer");
        PVE.setSize(200, 25);
        PVE.setLocation(690, 300);
        PVE.addActionListener(this);
        this.add(PVE);

        PVP = new JButton();
        PVP.setText("Player Versus Player");
        PVP.setSize(200, 25);
        PVP.setLocation(690, 350);
        PVP.addActionListener(this);
        this.add(PVP);

        exit = new JButton();
        exit.setText("Exit to desktop");
        exit.setSize(200, 25);
        exit.setLocation(690, 400);
        exit.addActionListener(this);
        this.add(exit);

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
        this.add(bg);

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
        clip.stop();
        try {
            if (e.getSource() == PVP) {
                gamev2 player = new gamev2();
            } else if (e.getSource() == PVE) {
                game computer = new game();
            } else if (e.getSource() == exit) {
                this.dispose();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }

}
