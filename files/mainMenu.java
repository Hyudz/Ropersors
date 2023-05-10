package files;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class mainMenu extends JPanel implements ActionListener {
    int width;
    int height;
    Clip clip, pveSound;

    JButton PVP, PVE, help, exit,dev1,dev2,dev3,dev4,dev5,left,right;
    JPanel lobbyPanel, mode1Panel, mode2Panel, clickPanel, instructionPanel, exitPanel, aboutdevs;

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

        JLabel clickAnywhere = new JLabel();
        ImageIcon titleIcon = new ImageIcon("titlescreen.png");
        Image tittleIcon = titleIcon.getImage();
        Image titleIconResized = tittleIcon.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);
        titleIcon = new ImageIcon(titleIconResized);
        clickAnywhere.setIcon(titleIcon);
        clickPanel.add(clickAnywhere);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e){
            clickPanel.setVisible(false);
            lobbyPanel.setVisible(true);
            }
        });

        Point point = new Point(0,0);

        Toolkit tkit=Toolkit.getDefaultToolkit();
        Image img1 = tkit.getImage("mouse cursor.png");

        Cursor cursor1 = tkit.createCustomCursor(img1, point,"");
        clickPanel.setCursor(cursor1);

        // LOBBY PANEL CONTAINS THE TITLE AND THE SUBTITLE AS FOR NOW
        lobbyPanel = new JPanel();
        lobbyPanel.setSize(1550, 830);
        lobbyPanel.setLayout(null);
        lobbyPanel.setCursor(cursor1);
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

        // ETO YUNG PANEL PARA SA ABOUT DEVS
        aboutdevs = new JPanel();
        aboutdevs.setSize(989, 556);
        aboutdevs.setLayout(null);

        dev1 = new JButton(new ImageIcon("about devs\\an.png"));
        dev1.setSize(212, 249);
        dev1.setBorderPainted(false);
        dev1.setContentAreaFilled(false);
        dev1.setLocation(220, 50);
        dev1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String url = "https://www.facebook.com/geellie.gel";
                    java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
                } catch (java.io.IOException ee) {
                    System.out.println(ee.getMessage());
                }
            }
        });
        aboutdevs.add(dev1);

        dev2 = new JButton(new ImageIcon("about devs\\hn.png"));
        dev2.setSize(212, 249);
        dev2.setBorderPainted(false);
        dev2.setContentAreaFilled(false);
        dev2.setLocation(520, 50);
        dev2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String url2 = "https://www.facebook.com/johnrod.jovellanos.5";
                    java.awt.Desktop.getDesktop().browse(java.net.URI.create(url2));
                } catch (java.io.IOException ee) {
                    System.out.println(ee.getMessage());
                }
            }
        });
        aboutdevs.add(dev2);

        dev3 = new JButton(new ImageIcon("about devs\\ion.png"));
        dev3.setSize(212, 249);
        dev3.setBorderPainted(false);
        dev3.setContentAreaFilled(false);
        dev3.setLocation(70, 235);
        dev3.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String url3 = "https://www.facebook.com/hyudz.esguerra.1";
                    java.awt.Desktop.getDesktop().browse(java.net.URI.create(url3));
                } catch (java.io.IOException ee) {
                    System.out.println(ee.getMessage());
                }
            }
        });
        aboutdevs.add(dev3);

        dev4 = new JButton(new ImageIcon("about devs\\jo.png"));
        dev4.setSize(212, 249);
        dev4.setBorderPainted(false);
        dev4.setContentAreaFilled(false);
        dev4.setLocation(380, 235);
        dev4.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String url4 = "https://www.facebook.com/johhzz";
                    java.awt.Desktop.getDesktop().browse(java.net.URI.create(url4));
                } catch (java.io.IOException ee) {
                    System.out.println(ee.getMessage());
                }
            }
        });
        aboutdevs.add(dev4);

        dev5 = new JButton(new ImageIcon("about devs\\us.png"));
        dev5.setSize(212, 249);
        dev5.setBorderPainted(false);
        dev5.setContentAreaFilled(false);
        dev5.setLocation(680, 235);
        dev5.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String url5 = "https://www.facebook.com/marcussauzie";
                    java.awt.Desktop.getDesktop().browse(java.net.URI.create(url5));
                } catch (java.io.IOException ee) {
                    System.out.println(ee.getMessage());
                }
            }
        });
        aboutdevs.add(dev5);

        // YUNG CLOSE BUTTON NG INSTRUCTION
        JButton closeButton = new JButton(new ImageIcon("main menu stuff\\buttons\\close button.png"));
        closeButton.setSize(26, 26);
        closeButton.setLocation(944, 10);
        closeButton.setContentAreaFilled(false);
        closeButton.setBorderPainted(false);
        closeButton.addActionListener(e -> close());
        instructionPanel.add(closeButton);

        //CLOSE BUTTON NG ABOUT DEVS
        JButton closeButton2 = new JButton(new ImageIcon("main menu stuff\\buttons\\close button.png"));
        closeButton2.setSize(26, 26);
        closeButton2.setLocation(944, 10);
        closeButton2.setContentAreaFilled(false);
        closeButton2.setBorderPainted(false);
        closeButton2.addActionListener(e -> close());
        aboutdevs.add(closeButton2);

        right = new JButton(new ImageIcon("right.png"));
        right.setSize(32, 32);
        right.setContentAreaFilled(false);
        right.setBorderPainted(false);
        right.setLocation(475, 500);
        right.addActionListener(e -> devs());
        instructionPanel.add(right);

        left = new JButton(new ImageIcon("left.png"));
        left.setSize(32, 32);
        left.setContentAreaFilled(false);
        left.setBorderPainted(false);
        left.setLocation(475, 500);
        left.addActionListener(e -> instruct());
        aboutdevs.add(left);

        // ETO YUNG INSTRUCTION
        JLabel instructionsLabel = new JLabel(
                new ImageIcon("main menu stuff\\pngs and text\\how to play screen.png"));
        instructionsLabel.setSize(989, 556);
        instructionPanel.add(instructionsLabel);
        instructionPanel.setVisible(false);
        instructionPanel.setOpaque(false);
        instructionPanel.setLocation(250, 145);
        lobbyPanel.add(instructionPanel);

        //ETO YUNG SA DEVELOPERS
        JLabel devsLabel = new JLabel(new ImageIcon("about devs\\about.png"));
        devsLabel.setSize(989, 556);
        aboutdevs.add(devsLabel);
        aboutdevs.setVisible(false);
        aboutdevs.setOpaque(false);
        aboutdevs.setLocation(250, 145);
        lobbyPanel.add(aboutdevs);

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

        lobbyPanel.setVisible(false);

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
        new mainMenu();
    }

    public void devs() {
        instructionPanel.setVisible(false);
        aboutdevs.setVisible(true);
        PVP.setEnabled(false);
        PVE.setEnabled(false);
        exit.setEnabled(false);

        PVP.setDisabledIcon(new ImageIcon("main menu stuff\\buttons\\pvp card.png"));
        PVE.setDisabledIcon(new ImageIcon("main menu stuff\\buttons\\pvai card.png"));
        exit.setDisabledIcon(new ImageIcon("main menu stuff\\buttons\\exit button.png"));
    }

    public void instruct() {
        instructionPanel.setVisible(true);
        aboutdevs.setVisible(false);
        PVP.setEnabled(false);
        PVE.setEnabled(false);
        exit.setEnabled(false);
        help.setEnabled(false);

        help.setDisabledIcon(new ImageIcon("main menu stuff\\buttons\\user-guide button.png"));
        PVP.setDisabledIcon(new ImageIcon("main menu stuff\\buttons\\pvp card.png"));
        PVE.setDisabledIcon(new ImageIcon("main menu stuff\\buttons\\pvai card.png"));
        exit.setDisabledIcon(new ImageIcon("main menu stuff\\buttons\\exit button.png"));
    }

    public void close() {
        instructionPanel.setVisible(false);
        aboutdevs.setVisible(false);
        
        help.setEnabled(true);
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