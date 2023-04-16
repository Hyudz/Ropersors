package files;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
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

    JButton PVP, PVE, help, exit;
    JPanel lobbyPanel, mode1Panel, mode2Panel, clickPanel, instructionPanel, exitPanel;

    music gameMusic = new music();
    game mode1Game = new game();
    gamev2 mode2Game = new gamev2();

    mainMenu() {

        // CONFIGURATION NG GUI
        this.setLayout(null);
        this.setVisible(true);
        this.setSize(1550, 830);

        // CONTAINS THE CLICK ANYWHERE SCREEN
        clickPanel = new JPanel(new BorderLayout());
        clickPanel.setSize(1550, 830);
        this.add(clickPanel);

        JButton clickAnywhere = new JButton();
        ImageIcon titleIcon = new ImageIcon("titlescreen.png");
        Image tittleIcon = titleIcon.getImage();
        Image titleIconResized = tittleIcon.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);
        titleIcon = new ImageIcon(titleIconResized);

        clickAnywhere.setIcon(titleIcon);
        clickAnywhere.addActionListener(e -> panels());
        clickPanel.add(clickAnywhere);

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

        // ETO YUNG PANEL PARA SA INSTRUCTIONS
        instructionPanel = new JPanel();
        instructionPanel.setSize(989, 556);
        instructionPanel.setLayout(null);

        // YUNG CLOSE BUTTON NG INSTRUCTION
        JButton closeButton = new JButton(new ImageIcon("main menu stuff\\buttons\\close button.png"));
        closeButton.setSize(26, 26);
        closeButton.setLocation(944, 10);
        closeButton.setContentAreaFilled(false);
        closeButton.setBorderPainted(false);
        closeButton.addActionListener(e -> close());
        instructionPanel.add(closeButton);

        // ETO YUNG INSTRUCTION
        JLabel instructionsLabel = new JLabel(
                new ImageIcon("main menu stuff\\pngs and text\\how to play screen.png"));
        instructionsLabel.setSize(989, 556);
        instructionPanel.add(instructionsLabel);
        instructionPanel.setVisible(false);
        instructionPanel.setOpaque(false);
        instructionPanel.setLocation(250, 145);
        lobbyPanel.add(instructionPanel);

        // ETO NAMAN PARA PAG MAG EXIT YUNG USER
        exitPanel = new JPanel();
        exitPanel.setLayout(null);

        // ETO NAMAN YUNG PARA MAG EXIT NA YUNG PROGRAM
        JButton exitt = new JButton(new ImageIcon("main menu stuff\\buttons\\exit confirmed.png"));
        exitt.setSize(46, 31);
        exitt.setLocation(75, 135);
        exitt.setBorderPainted(false);
        exitt.setContentAreaFilled(false);
        exitt.addActionListener(e -> System.exit(0));
        exitPanel.add(exitt);

        // ETO NAMAN PARA I-CLOSE YUNG EXIT PANEL
        JButton cancel = new JButton(new ImageIcon("main menu stuff\\buttons\\cancel confirm.png"));
        cancel.setSize(75, 31);
        cancel.setLocation(250, 135);
        cancel.setBorderPainted(false);
        cancel.setContentAreaFilled(false);
        cancel.addActionListener(e -> cancelled());
        exitPanel.add(cancel);

        // POP-UP NA TO CONFIRM NA I-EXIT
        JLabel exitLabel = new JLabel(new ImageIcon("main menu stuff\\pngs and text\\confirm exit.png"));
        exitLabel.setSize(426, 200);
        exitPanel.add(exitLabel);
        exitPanel.setSize(426, 200);
        exitPanel.setOpaque(false);
        exitPanel.setVisible(false);
        exitPanel.setLocation(500, 300);

        lobbyPanel.add(exitPanel);

        // SELECT MODE TEXT NA NASA TAAS
        JLabel selectMode = new JLabel(new ImageIcon("main menu stuff\\pngs and text\\select game mode (text).png"));
        selectMode.setSize(684, 104);
        selectMode.setLocation(10, 10);
        lobbyPanel.add(selectMode);

        PVE = new JButton(new ImageIcon("main menu stuff\\buttons\\pvai card.png"));
        PVE.setSize(400, 600);
        PVE.setLocation(800, 125);
        PVE.setContentAreaFilled(false);
        PVE.setBorderPainted(false);
        PVE.addActionListener(this);
        lobbyPanel.add(PVE);

        PVP = new JButton(new ImageIcon("main menu stuff\\buttons\\pvp card.png"));
        PVP.setSize(400, 600);
        PVP.setLocation(300, 125);
        PVP.setContentAreaFilled(false);
        PVP.setBorderPainted(false);
        PVP.addActionListener(this);
        lobbyPanel.add(PVP);

        help = new JButton(new ImageIcon("main menu stuff\\buttons\\user-guide button.png"));
        help.setSize(64, 70);
        help.setLocation(1460, 20);
        help.addActionListener(e -> instruct());
        help.setContentAreaFilled(false);
        help.setBorderPainted(false);
        lobbyPanel.add(help);

        exit = new JButton(new ImageIcon("main menu stuff\\buttons\\exit button.png"));
        exit.setSize(64, 70);
        exit.setLocation(1460, 690);
        exit.addActionListener(this);
        exit.setContentAreaFilled(false);
        exit.setBorderPainted(false);
        lobbyPanel.add(exit);

        ImageIcon background = new ImageIcon("main menu stuff\\new main menu bg.png");
        Image backkground = background.getImage();
        Image bgResized = backkground.getScaledInstance(this.getWidth(), this.getHeight(), java.awt.Image.SCALE_SMOOTH);
        background = new ImageIcon(bgResized);

        // ISET NATIN YUNG SA BACKGROUND NAMAN
        JLabel backgroundd = new JLabel();
        backgroundd.setIcon(background);
        backgroundd.setLocation(0, 0);
        backgroundd.setSize(this.getWidth(), this.getHeight());

        JPanel bg = new JPanel();
        bg.setSize(this.getWidth(), this.getHeight());
        bg.setLocation(0, 0);
        bg.add(backgroundd);
        bg.setLayout(null);
        lobbyPanel.add(bg);
        this.add(lobbyPanel);

    }

    public static void main(String[] args) {
        mainMenu welcome = new mainMenu();
    }

    public void panels() {
        clickPanel.setVisible(false);
        lobbyPanel.setVisible(true);
    }

    public void instruct() {
        instructionPanel.setVisible(true);
        PVP.setEnabled(false);
        PVE.setEnabled(false);
        exit.setEnabled(false);

        PVP.setDisabledIcon(new ImageIcon("main menu stuff\\buttons\\pvp card.png"));
        PVE.setDisabledIcon(new ImageIcon("main menu stuff\\buttons\\pvai card.png"));
        exit.setDisabledIcon(new ImageIcon("main menu stuff\\buttons\\exit button.png"));
    }

    public void close() {
        instructionPanel.setVisible(false);
        PVP.setEnabled(true);
        PVE.setEnabled(true);
        exit.setEnabled(true);
    }

    public void cancelled() {
        exitPanel.setVisible(false);
        PVP.setEnabled(true);
        PVE.setEnabled(true);
        help.setEnabled(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == PVP) {
                gameMusic.stopLobby();
                lobbyPanel.setVisible(false);
                mode2Panel.setVisible(true);

                mode2Game.pleasePlay();
                PVE.setVisible(false);
                PVP.setVisible(false);
                exit.setVisible(false);

            } else if (e.getSource() == PVE) {
                gameMusic.stopLobby();
                lobbyPanel.setVisible(false);
                mode1Panel.setVisible(true);

                mode1Game.pleasePlay();
                PVE.setVisible(false);
                PVP.setVisible(false);
                exit.setVisible(false);
            } else if (e.getSource() == exit) {
                PVP.setEnabled(false);
                help.setEnabled(false);
                PVE.setEnabled(false);

                PVP.setDisabledIcon(new ImageIcon("main menu stuff\\buttons\\pvp card.png"));
                PVE.setDisabledIcon(new ImageIcon("main menu stuff\\buttons\\pvai card.png"));
                help.setDisabledIcon(new ImageIcon("main menu stuff\\buttons\\user-guide button.png"));

                exitPanel.setVisible(true);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }

}