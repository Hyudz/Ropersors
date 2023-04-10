package files;

//PVP

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.util.Timer;
import java.util.TimerTask;

public class gamev2 extends JPanel implements ActionListener {

    JLabel p1lives, p2lives, status, name1, name2, p1Card, p2Card, paused, overlay, background,
            gameOver, nameplate, nameplate2, nameplate3, round, p1Status, p2Status, confirm, damage, p1Hurt, p2Hurt;
    JPanel bg, names1, names2, thisPanel, exitPanel, dmgIndicator, gPaused, statuses, gameOverPanel;
    ImageIcon board, namplates, vignette;
    Random randomChoice;
    JButton rockButton, paperButton, scissorButton, retryButton, pauseButton, playButton, newGame, rockButton1,
            paperButton1, scissorsButton1, homeButton, homeButton2;
    String[] objects = { "rock", "paper", "scissors" };
    String[] elements = { "fire", "water", "leaf" };
    String[] boards = { "boards\\magam.png", "boards\\sky.png", "boards\\snad.png", "boards\\wood.png" };
    String[] nameplates = { "nameplates\\magam.png", "nameplates\\sky.png", "nameplates\\snad.png",
            "nameplates\\wood.png" };
    Random randomBg;
    int randomIndex, computerCard, roundNo, lastNum;
    Image boardImage, boardResized, vignetteImage, vignetteResized;
    players player1 = new players();
    players player2 = new players();
    music gameMusic = new music();

    boolean p1Choosed, p2Choosed;
    boolean p1Won, p2Won = false;

    ArrayList<Integer> recentIndex = new ArrayList<Integer>();

    public gamev2() {

        thisPanel = new JPanel();
        thisPanel.setLocation(0, 0);
        thisPanel.setSize(1920, 1080);
        thisPanel.setVisible(true);
        thisPanel.setLayout(null);
        this.add(thisPanel);

        roundNo = 1;

        randomBg = new Random();
        randomIndex = randomBg.nextInt(4);
        recentIndex.add(randomIndex);

        vignette = new ImageIcon("vignette 1080.png");
        vignetteImage = vignette.getImage();
        vignetteResized = vignetteImage.getScaledInstance(1535, 792, java.awt.Image.SCALE_SMOOTH);
        vignette = new ImageIcon(vignetteResized);

        overlay = new JLabel();
        overlay.setLocation(0, 0);
        overlay.setIcon(vignette);
        overlay.setSize(1535, 792);
        overlay.setVisible(false);
        thisPanel.add(overlay);

        player1.setName("Player 1");
        player2.setName("Player 2");

        // SET THE CONFIGURATION OF PLAYER 1 NAME
        name1 = new JLabel();
        name1.setText(player1.getName());
        name1.setSize(150, 30);
        name1.setFont(new Font("DePixel", Font.BOLD, 23));
        name1.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        name1.setForeground(Color.WHITE);

        // ETO NAMAN YUNG SA LIVES NILA
        p1lives = new JLabel();
        p1lives.setText("Lives: " + player1.getLives());
        p1lives.setFont(new Font("DePixel", Font.BOLD, 18));
        p1lives.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        p1lives.setForeground(Color.white);
        p1lives.setLocation(315, 120);

        p2lives = new JLabel();
        p2lives.setText("Lives: " + player2.getLives());
        p2lives.setForeground(Color.WHITE);
        p2lives.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        p2lives.setFont(new Font("DePixel", Font.BOLD, 18));
        p2lives.setSize(150, 30);

        // SET THE CONFIGURATION OF computer NAME
        name2 = new JLabel();
        name2.setText(player2.getName());
        name2.setForeground(Color.WHITE);
        name2.setSize(175, 30);
        name2.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        name2.setFont(new Font("DePixel", Font.BOLD, 23));

        // SET THE INDICATOR ANYTIME THEY ARE READY
        p1Status = new JLabel();
        p1Status.setIcon(new ImageIcon("buttons and prompts\\player 1 input butts.png"));
        p1Status.setSize(200, 120);

        // SET THE INDICATOR ANYTIME THEY ARE READY
        p2Status = new JLabel();
        p2Status.setIcon(new ImageIcon("buttons and prompts\\player 2 input butts.png"));
        p2Status.setSize(200, 120);

        statuses = new JPanel(new BorderLayout());
        statuses.setSize(515, 200);
        statuses.setOpaque(false);
        statuses.setLocation(485, 310);
        statuses.add(p1Status, BorderLayout.WEST);
        statuses.add(p2Status, BorderLayout.EAST);
        thisPanel.add(statuses);

        // ADD a panel of game over
        gameOverPanel = new JPanel();
        gameOverPanel.setLayout(null);
        gameOverPanel.setOpaque(false);
        gameOverPanel.setVisible(false);
        gameOverPanel.setSize(1200, 500);
        gameOverPanel.setLocation(150, 200);
        thisPanel.add(gameOverPanel);

        gameOver = new JLabel();
        gameOver.setSize(1100, 270);
        gameOver.setLocation(80, -35);
        gameOverPanel.add(gameOver);

        // PUT THE NAMES LIVES AND STATUS UNDER A PANEL
        names1 = new JPanel();
        names1.setLayout(new BoxLayout(names1, BoxLayout.Y_AXIS));
        names1.setSize(390, 120);
        names1.setLocation(160, 80);
        names1.setOpaque(false);
        names1.add(name1);
        names1.add(p1lives);
        thisPanel.add(names1);

        // PUT THE NAMES LIVES AND STATUS UNDER A PANEL
        names2 = new JPanel();
        names2.setLayout(new BoxLayout(names2, BoxLayout.Y_AXIS));
        names2.setSize(390, 120);
        names2.setLocation(945, 80);
        names2.setOpaque(false);
        names2.add(name2);
        names2.add(p2lives);
        thisPanel.add(names2);

        // SET THE ROUND INDICATOR
        round = new JLabel();
        round.setSize(175, 30);
        round.setForeground(Color.white);
        round.setFont(new Font("DePixel", Font.BOLD, 18));
        round.setText("Round No: " + Integer.toString(roundNo));
        round.setLocation(675, 100);
        thisPanel.add(round);

        // SET THE POSITIONS OF NAMEPLATE 1
        nameplate = new JLabel();
        nameplate.setSize(390, 120);
        nameplate.setLocation(180, 50);
        nameplate.setIcon(new ImageIcon(nameplates[randomIndex]));
        thisPanel.add(nameplate);

        // SET THE POSITIONS OF NAMEPLATE PARA SA ROUND INDICATOR
        nameplate2 = new JLabel();
        nameplate2.setSize(390, 120);
        nameplate2.setLocation(575, 50);
        nameplate2.setIcon(new ImageIcon(nameplates[randomIndex]));
        thisPanel.add(nameplate2);

        // SET THE POSITIONS OF NAMEPLATE 2
        nameplate3 = new JLabel();
        nameplate3.setSize(390, 120);
        nameplate3.setLocation(965, 50);
        nameplate3.setIcon(new ImageIcon(nameplates[randomIndex]));
        thisPanel.add(nameplate3);

        // CONFIRMATION PANEL FOR THE USER TO EXIT THE MODE TO HOME
        exitPanel = new JPanel();
        exitPanel.setOpaque(false);

        JButton exitt = new JButton(new ImageIcon("main menu stuff\\buttons\\exit confirmed.png"));
        exitt.setSize(46, 31);
        exitt.setLocation(75, 135);
        exitt.setBorderPainted(false);
        exitt.setContentAreaFilled(false);
        exitt.addActionListener(e -> homeButton());
        exitPanel.add(exitt);

        JButton cancel = new JButton(new ImageIcon("main menu stuff\\buttons\\cancel confirm.png"));
        cancel.setSize(75, 31);
        cancel.setLocation(250, 135);
        cancel.setBorderPainted(false);
        cancel.setContentAreaFilled(false);
        cancel.addActionListener(e -> cancel());
        exitPanel.add(cancel);

        JLabel exitLabel = new JLabel(new ImageIcon("main menu stuff\\pngs and text\\confirm exit.png"));
        exitLabel.setSize(426, 200);
        exitPanel.add(exitLabel);

        exitPanel.setSize(426, 200);
        exitPanel.setLocation(500, 300);
        exitPanel.setLayout(null);
        exitPanel.setVisible(false);
        gameOverPanel.add(exitPanel);
        thisPanel.add(exitPanel);

        // RETRY BUTTON (PAG YUNG ISA SAKANILA AY NATALO)
        retryButton = new JButton(new ImageIcon("game paused stuff\\restart.png"));
        retryButton.setSize(211, 75);
        retryButton.setContentAreaFilled(false);
        retryButton.setBorderPainted(false);
        retryButton.setLocation(500, 200);
        retryButton.addActionListener(e -> retry());
        retryButton.addActionListener(this);
        retryButton.addActionListener(e -> player1.setLives(10));
        retryButton.addActionListener(e -> player2.setLives(10));
        gameOverPanel.add(retryButton);

        // HOME BUTTON FOR MAIN MENU SA PART NA NATALO YUNG ISA
        homeButton2 = new JButton(new ImageIcon("game paused stuff\\main menu.png"));
        homeButton2.setSize(324, 75);
        homeButton2.setLocation(450, 265);
        homeButton2.setBorderPainted(false);
        homeButton2.setContentAreaFilled(false);
        homeButton2.addActionListener(e -> homeButton());
        gameOverPanel.add(homeButton2);

        // ADD ANOTHER PANEL FOR PAUSE PANEL
        gPaused = new JPanel();
        gPaused.setLayout(null);
        gPaused.setOpaque(false);
        gPaused.setVisible(false);
        gPaused.setSize(1150, 500);
        gPaused.setLocation(100, 200);
        thisPanel.add(gPaused);

        // PAUSE BUTTON
        pauseButton = new JButton(new ImageIcon("buttons and prompts\\pause button.png"));
        pauseButton.setSize(100, 100);
        pauseButton.setLocation(1445, -10);
        pauseButton.setFocusable(false);
        pauseButton.setBorderPainted(false);
        pauseButton.setFocusPainted(false);
        pauseButton.setContentAreaFilled(false);
        pauseButton.addActionListener(e -> pauseMethod());
        thisPanel.add(pauseButton);

        paused = new JLabel(new ImageIcon("game paused stuff\\GAME PAUSED (TEXT).png"));
        paused.setSize(925, 145);
        gPaused.add(paused);

        // PLAY BUTTON
        playButton = new JButton(new ImageIcon("game paused stuff\\continue.png"));
        playButton.setSize(246, 75);
        playButton.setFocusable(false);
        playButton.setBorderPainted(false);
        playButton.setFocusPainted(false);
        playButton.setContentAreaFilled(false);
        playButton.setLocation(0, 150);
        playButton.addActionListener(e -> playMethod());
        gPaused.add(playButton);

        // SET THE HOME BUTTON
        homeButton = new JButton(new ImageIcon("game paused stuff\\main menu.png"));
        homeButton.setSize(324, 75);
        homeButton.setLocation(0, 265);
        homeButton.setBorderPainted(false);
        homeButton.setContentAreaFilled(false);
        homeButton.addActionListener(e -> confirm());
        gPaused.add(homeButton);

        // NEW GAME BUTTON NA NASA PAUSE PANEL
        newGame = new JButton(new ImageIcon("game paused stuff\\restart.png"));
        newGame.setSize(211, 75);
        newGame.setLocation(0, 208);
        newGame.setBorderPainted(false);
        newGame.setFocusPainted(false);
        newGame.setContentAreaFilled(false);
        newGame.setFocusable(false);
        newGame.addActionListener(this);
        newGame.addActionListener(e -> tryAgain());
        gPaused.add(newGame);

        // SET THE HOME BUTTON (NOW AS MAIN MENU)
        homeButton = new JButton(new ImageIcon("game paused stuff\\main menu.png"));
        homeButton.setSize(324, 75);
        homeButton.setLocation(0, 265);
        homeButton.setBorderPainted(false);
        homeButton.setContentAreaFilled(false);
        homeButton.addActionListener(e -> confirm());
        gPaused.add(homeButton);

        // COMFIGURATIONS NG ROCK PAPER AND SCISSORS for Player 1
        rockButton = new JButton();
        thisPanel.add(rockButton);

        paperButton = new JButton();
        thisPanel.add(paperButton);

        scissorButton = new JButton();
        thisPanel.add(scissorButton);

        KeyStroke rockKey = KeyStroke.getKeyStroke(KeyEvent.VK_A, 0);
        Action action1 = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player1.setChoice("rock");
                p1Choosed = true;
                check();
            }
        };

        InputMap input1 = rockButton.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
        input1.put(rockKey, "performAction1");
        ActionMap actionMap1 = rockButton.getActionMap();
        actionMap1.put("performAction1", action1);

        KeyStroke paperKey = KeyStroke.getKeyStroke(KeyEvent.VK_S, 0);
        Action action2 = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player1.setChoice("paper");
                p1Choosed = true;
                check();
            }
        };

        InputMap input2 = rockButton.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
        input2.put(paperKey, "performAction2");
        ActionMap actionMap2 = rockButton.getActionMap();
        actionMap2.put("performAction2", action2);

        KeyStroke scissorKey = KeyStroke.getKeyStroke(KeyEvent.VK_D, 0);
        Action action3 = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player1.setChoice("scissors");
                p1Choosed = true;
                check();
            }
        };

        // PLAYER 2
        rockButton1 = new JButton();
        this.add(rockButton1);

        paperButton1 = new JButton();
        this.add(paperButton1);

        scissorsButton1 = new JButton();
        this.add(scissorsButton1);

        InputMap input3 = rockButton.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
        input3.put(scissorKey, "performAction3");
        ActionMap actionMap3 = rockButton.getActionMap();
        actionMap3.put("performAction3", action3);

        KeyStroke rockKey1 = KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0);
        Action action4 = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player2.setChoice("rock");
                p2Choosed = true;
                check();
            }
        };

        InputMap input4 = rockButton1.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
        input4.put(rockKey1, "performAction4");
        ActionMap actionMap4 = rockButton1.getActionMap();
        actionMap4.put("performAction4", action4);

        KeyStroke paperKey1 = KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0);
        Action action5 = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player2.setChoice("paper");
                p2Choosed = true;
                check();
            }
        };

        InputMap input5 = paperButton1.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
        input5.put(paperKey1, "performAction5");
        ActionMap actionMap5 = paperButton1.getActionMap();
        actionMap5.put("performAction5", action5);

        KeyStroke scissorsKey1 = KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0);
        Action action6 = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player2.setChoice("scissors");
                p2Choosed = true;
                check();
            }
        };

        InputMap input6 = scissorsButton1.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
        input6.put(scissorsKey1, "performAction6");
        ActionMap actionMap6 = scissorsButton1.getActionMap();
        actionMap6.put("performAction6", action6);

        p1Hurt = new JLabel();
        p1Hurt.setIcon(new ImageIcon("damage texts and hit indicator\\getting hit indicator.png"));
        p1Hurt.setSize(240, 360);
        p1Hurt.setVisible(false);
        p1Hurt.setLocation(240, 200);
        thisPanel.add(p1Hurt);

        p1Card = new JLabel();
        p1Card.setIcon(new ImageIcon("default series\\backcard.png"));
        p1Card.setSize(240, 360);
        p1Card.setLocation(240, 200);
        thisPanel.add(p1Card);

        // DITO LUMALABAS YUNG INFO -1 AND -2 DAMAGE
        dmgIndicator = new JPanel();
        dmgIndicator.setSize(652, 77);
        dmgIndicator.setLocation(450, 600);
        dmgIndicator.setOpaque(false);
        dmgIndicator.setVisible(false);
        thisPanel.add(dmgIndicator);

        // DMG INDICATOR FOR BOTH SIDES
        damage = new JLabel();
        damage.setSize(652, 77);
        dmgIndicator.add(damage);

        // P2 HURT (YUNG RED)
        p2Hurt = new JLabel();
        p2Hurt.setIcon(new ImageIcon("damage texts and hit indicator\\getting hit indicator.png"));
        p2Hurt.setSize(240, 360);
        p2Hurt.setVisible(false);
        p2Hurt.setLocation(1010, 200);
        thisPanel.add(p2Hurt);

        // LABELS OF ROCK PAPER SCISORS BUT AS IMAGES FOR COMPUTER SIDE
        p2Card = new JLabel();
        p2Card.setIcon(new ImageIcon("default series\\backcard.png"));
        p2Card.setSize(240, 360);
        p2Card.setLocation(1010, 200);
        thisPanel.add(p2Card);

        // RESIZE THE BG
        board = new ImageIcon(boards[randomIndex]);
        boardImage = board.getImage();
        boardResized = boardImage.getScaledInstance(1535, 792, java.awt.Image.SCALE_SMOOTH);
        board = new ImageIcon(boardResized);

        // Put in a JLabel
        background = new JLabel();
        background.setIcon(board);
        background.setLocation(0, -143);
        background.setSize(1920, 1080);
        thisPanel.add(background);

        // TAS ILALAGAY SA JPANEL?!
        bg = new JPanel();
        bg.setSize(1920, 1080);
        bg.setLocation(0, 0);
        bg.add(background);
        bg.setLayout(null);
        thisPanel.add(bg);

        // CONFIGURATION NG GUI
        this.setLayout(null);
        this.setVisible(true);
        this.setSize(1550, 830);
    }

    // PLAY THE SOUND IF THE FRAME IS VISIBLE
    public void pleasePlay() {
        switch (randomIndex) {
            case 0:
                gameMusic.playGame1();
                break;
            case 1:
                gameMusic.playGame2();
                break;
            case 2:
                gameMusic.playGame3();
                break;
            case 3:
                gameMusic.playGame4();
                break;
        }
        thisPanel.setVisible(true);
    }

    public void confirm() {
        exitPanel.setVisible(true);
        playButton.setEnabled(false);
        newGame.setEnabled(false);
        homeButton.setEnabled(false);
        retryButton.setEnabled(false);

        retryButton.setDisabledIcon(new ImageIcon("game paused stuff\\restart.png"));
        playButton.setDisabledIcon(new ImageIcon("game paused stuff\\continue.png"));
        newGame.setDisabledIcon(new ImageIcon("game paused stuff\\restart.png"));
        homeButton.setDisabledIcon(new ImageIcon("game paused stuff\\main menu.png"));
    }

    public void cancel() {
        retryButton.setEnabled(true);
        exitPanel.setVisible(false);
        playButton.setEnabled(true);
        newGame.setEnabled(true);
        homeButton.setEnabled(true);
    }

    public void homeButton() {
        mainMenu haha = new mainMenu();
        JPanel menuPanel = new JPanel();
        menuPanel = new JPanel();
        menuPanel.setSize(1550, 830);
        menuPanel.setLayout(null);
        menuPanel.add(haha);
        this.add(menuPanel);
        thisPanel.setVisible(false);
        lastNum = recentIndex.get(recentIndex.size() - 1);

        switch (lastNum) {
            case 0:
                gameMusic.stopMode1();
                break;
            case 1:
                gameMusic.stopMode2();
                break;
            case 2:
                gameMusic.stopMode3();
                break;
            case 3:
                gameMusic.stopMode4();
                break;
        }
    }

    // CHANGES THE BACKGROUND ALONG WITH THE NAMEPLATE
    @Override
    public void actionPerformed(ActionEvent e) {
        lastNum = recentIndex.get(recentIndex.size() - 1);

        switch (lastNum) {
            case 0:
                gameMusic.stopMode1();
                break;
            case 1:
                gameMusic.stopMode2();
                break;
            case 2:
                gameMusic.stopMode3();
                break;
            case 3:
                gameMusic.stopMode4();
                break;
        }

        randomBg = new Random();
        randomIndex = randomBg.nextInt(4);
        nameplate.setIcon(new ImageIcon(nameplates[randomIndex]));
        nameplate2.setIcon(new ImageIcon(nameplates[randomIndex]));
        nameplate3.setIcon(new ImageIcon(nameplates[randomIndex]));
        board = new ImageIcon(boards[randomIndex]);

        boardImage = board.getImage();
        boardResized = boardImage.getScaledInstance(1535, 792, java.awt.Image.SCALE_SMOOTH);
        board = new ImageIcon(boardResized);
        background.setIcon(board);

        switch (randomIndex) {
            case 0:
                gameMusic.playGame1();
                recentIndex.add(0);
                break;
            case 1:
                gameMusic.playGame2();
                recentIndex.add(1);
                break;
            case 2:
                gameMusic.playGame3();
                recentIndex.add(2);
                break;
            case 3:
                gameMusic.playGame4();
                recentIndex.add(3);
                break;
        }
    }

    // TRY AGAIN OR NEW GAME NA NAKALAGAY SA MAY PAUSE WINDOW
    public void tryAgain() {
        playMethod();
        pauseButton.setVisible(true);
        player1.setLives(10);
        player2.setLives(10);
        p1Card.setVisible(true);
        p2Card.setVisible(true);
        rockButton.setVisible(true);
        paperButton.setVisible(true);
        scissorButton.setVisible(true);
        gameOver.setVisible(false);
        roundNo = 1;
        round.setText("Round No: " + roundNo);
        p1lives.setText("Lives: " + Integer.toString(player1.getLives()));
        p2lives.setText("Lives: " + Integer.toString(player2.getLives()));
    }

    // ISET NATIN YUNG SA PAUSE METHOD
    public void pauseMethod() {
        p1Status.setVisible(false);
        p2Status.setVisible(false);
        p1Card.setVisible(false);
        p2Card.setVisible(false);
        overlay.setVisible(true);
        statuses.setVisible(false);
        names1.setVisible(false);
        names2.setVisible(false);
        pauseButton.setVisible(false);
        round.setVisible(false);
        gPaused.setVisible(true);
    }

    // ETO NAMAN YUNG SA PLAY
    public void playMethod() {
        p1Status.setVisible(true);
        p2Status.setVisible(true);
        pauseButton.setVisible(true);
        overlay.setVisible(false);
        statuses.setVisible(true);
        names1.setVisible(true);
        names2.setVisible(true);
        round.setVisible(true);
        p1Card.setVisible(true);
        p2Card.setVisible(true);
        gPaused.setVisible(false);
    }

    // ETONG METHOD NA TO, ETO YUNG PARA MAG NEW GAME ULIT KUNG SAKALING ANG ISA AY
    // MATALO
    public void retry() {
        enableButtons();
        lastNum = recentIndex.get(recentIndex.size() - 1);

        switch (lastNum) {
            case 0:
                gameMusic.gameRestartWon();
                break;
            case 1:
                gameMusic.gameRestartWon2();
                break;
            case 2:
                gameMusic.gameRestartWon3();
                break;
            case 3:
                gameMusic.gameRestartWon4();
                break;
        }
        gameOverPanel.setVisible(false);
        p1Choosed = false;
        p2Choosed = false;
        p1Status.setVisible(true);
        p2Status.setVisible(true);
        p1Status.setIcon(new ImageIcon("buttons and prompts\\player 1 input butts.png"));
        p2Status.setIcon(new ImageIcon("buttons and prompts\\player 2 input butts.png"));
        p1Card.setIcon(new ImageIcon("default series\\backcard.png"));
        p2Card.setIcon(new ImageIcon("default series\\backcard.png"));
        roundNo = 1;
        round.setText("Round No: " + roundNo);
        player1.setLives(10);
        player2.setLives(10);
        p1Card.setVisible(true);
        p2Card.setVisible(true);
        overlay.setVisible(false);
        pauseButton.setVisible(true);
        p1lives.setText("Lives: " + Integer.toString(player1.getLives()));
        p2lives.setText("Lives: " + Integer.toString(player2.getLives()));
        p1Won = false;
        p2Won = false;
    }

    public void disableButtons() {
        rockButton.setEnabled(false);
        paperButton.setEnabled(false);
        scissorButton.setEnabled(false);
        rockButton1.setEnabled(false);
        paperButton1.setEnabled(false);
        scissorsButton1.setEnabled(false);
    }

    public void enableButtons() {
        rockButton.setEnabled(true);
        paperButton.setEnabled(true);
        scissorButton.setEnabled(true);
        rockButton1.setEnabled(true);
        paperButton1.setEnabled(true);
        scissorsButton1.setEnabled(true);
    }

    public void gameIsOver() {
        disableButtons();
        overlay.setVisible(true);
        pauseButton.setVisible(false);
        gameOverPanel.setVisible(true);

    }

    // LAGAY NALANG NATIN SA MGA METHODS YUNG MABABAWASAN YUNG LIFE
    public void p1Life1() {
        int livesp1 = player1.getLives() - 1;
        player1.setLives(livesp1);
        p1lives.setText("Lives: " + Integer.toString(player1.getLives()));

        p1Hurt.setVisible(true);
        dmgIndicator.setVisible(true);
        damage.setIcon(new ImageIcon("damage texts and hit indicator\\- 1 damage.png"));

        Timer indicator = new Timer();
        TimerTask life = new TimerTask() {
            public void run() {
                p1Hurt.setVisible(false);
                dmgIndicator.setVisible(false);
            }
        };
        indicator.schedule(life, 2500);

    }

    public void p2Life1() {
        int livesp2 = player2.getLives() - 1;
        player2.setLives(livesp2);
        p2lives.setText("Lives: " + Integer.toString(player2.getLives()));

        p2Hurt.setVisible(true);
        dmgIndicator.setVisible(true);
        damage.setIcon(new ImageIcon("damage texts and hit indicator\\- 1 damage.png"));

        Timer indicator = new Timer();
        TimerTask life = new TimerTask() {
            public void run() {
                p2Hurt.setVisible(false);
                dmgIndicator.setVisible(false);
            }
        };
        indicator.schedule(life, 2500);
    }

    public void p1Life2() {
        int livesp1 = player1.getLives() - 2;
        player1.setLives(livesp1);
        p1lives.setText("Lives: " + Integer.toString(player1.getLives()));
        gameMusic.absoluteSound();

        p1Hurt.setVisible(true);
        dmgIndicator.setVisible(true);
        damage.setIcon(new ImageIcon("damage texts and hit indicator\\- 2 absolute damage.png"));

        Timer indicator = new Timer();
        TimerTask life = new TimerTask() {
            public void run() {
                dmgIndicator.setVisible(false);
                p1Hurt.setVisible(false);
            }
        };
        indicator.schedule(life, 2500);
    }

    public void p2Life2() {
        int livesp2 = player2.getLives() - 2;
        player2.setLives(livesp2);
        p2lives.setText("Lives: " + Integer.toString(player2.getLives()));
        gameMusic.absoluteSound();

        p2Hurt.setVisible(true);
        dmgIndicator.setVisible(true);
        damage.setIcon(new ImageIcon("damage texts and hit indicator\\- 2 absolute damage.png"));

        Timer indicator = new Timer();
        TimerTask life = new TimerTask() {
            public void run() {
                p2Hurt.setVisible(false);
                dmgIndicator.setVisible(false);
            }
        };
        indicator.schedule(life, 2500);
    }

    public void draw() {

        dmgIndicator.setVisible(true);
        damage.setIcon(new ImageIcon("damage texts and hit indicator\\draw.png"));

        Timer indicator = new Timer();
        TimerTask life = new TimerTask() {
            public void run() {
                dmgIndicator.setVisible(false);
            }
        };
        indicator.schedule(life, 2500);

    }

    public void check() {
        if (p1Choosed == true && p2Choosed == true) {
            proceed();
        } else if (p1Choosed == true) {
            p1Status.setIcon(new ImageIcon("buttons and prompts\\ready!.png"));
        } else if (p2Choosed == true) {
            p2Status.setIcon(new ImageIcon("buttons and prompts\\ready!.png"));
        }
    }

    public void proceed() {
        p1Status.setVisible(false);
        p2Status.setVisible(false);
        randomChoice = new Random();
        Random randomChoiceofElement = new Random();
        int randomInfuse = randomChoiceofElement.nextInt(3);
        // INFUSAL OF ELEMENT FOR computer

        if (player2.getChoice().equals("rock")) {
            if (randomInfuse == 0) {
                p2Card.setIcon(new ImageIcon("Fire Series\\fire rock card.png"));
                player2.setElement("fire");
            } else if (randomInfuse == 1) {
                p2Card.setIcon(new ImageIcon("Water Series\\water rock card.png"));
                player2.setElement("water");
            } else if (randomInfuse == 2) {
                p2Card.setIcon(new ImageIcon("Leaf Series\\leaf rock card.png"));
                player2.setElement("leaf");
            }
        } else if (player2.getChoice().equals("paper")) {
            if (randomInfuse == 0) {
                p2Card.setIcon(new ImageIcon("Fire Series\\fire paper card.png"));
                player2.setElement("fire");
            } else if (randomInfuse == 1) {
                p2Card.setIcon(new ImageIcon("Water Series\\water paper card.png"));
                player2.setElement("water");
            } else if (randomInfuse == 2) {
                p2Card.setIcon(new ImageIcon("Leaf Series\\leaf paper card.png"));
                player2.setElement("leaf");
            }
        } else if (player2.getChoice().equals("scissors")) {
            if (randomInfuse == 0) {
                p2Card.setIcon(new ImageIcon("Fire Series\\fire scissors card.png"));
                player2.setElement("fire");
            } else if (randomInfuse == 1) {
                p2Card.setIcon(new ImageIcon("Water Series\\water scissors card.png"));
                player2.setElement("water");
            } else if (randomInfuse == 2) {
                p2Card.setIcon(new ImageIcon("Leaf Series\\leaf scissors card.png"));
                player2.setElement("leaf");
            }
        }

        randomChoice = new Random();
        int randomElement = randomChoice.nextInt(3);
        player1.setElement((elements[randomElement]));

        // INFUSAL OF ELEMENTS FOR PLAYER
        if (player1.getChoice().equals("rock")) {
            if (player1.getElement().equals("fire")) {
                p1Card.setIcon(new ImageIcon("Fire Series\\fire rock card.png"));
            } else if (player1.getElement().equals("water")) {
                p1Card.setIcon(new ImageIcon("Water Series\\water rock card.png"));
            } else if (player1.getElement().equals("leaf")) {
                p1Card.setIcon(new ImageIcon("Leaf Series\\leaf rock card.png"));
            }

        } else if (player1.getChoice().equals("paper")) {
            if (randomElement == 0) {
                p1Card.setIcon(new ImageIcon("Fire Series\\fire paper card.png"));
            } else if (player1.getElement().equals("water")) {
                p1Card.setIcon(new ImageIcon("Water Series\\water paper card.png"));
            } else if (player1.getElement().equals("leaf")) {
                p1Card.setIcon(new ImageIcon("Leaf Series\\leaf paper card.png"));
            }
        } else if (player1.getChoice().equals("scissors")) {
            if (randomElement == 0) {
                p1Card.setIcon(new ImageIcon("Fire Series\\fire scissors card.png"));
            } else if (player1.getElement().equals("water")) {
                p1Card.setIcon(new ImageIcon("Water Series\\water scissors card.png"));
            } else if (player1.getElement().equals("leaf")) {
                p1Card.setIcon(new ImageIcon("Leaf Series\\leaf scissors card.png"));
            }
        }

        disableButtons();

        // ON THE FOLLOWING BLOCK OF CODE, THE PROGRAM WILL DETERMINE WHO IS THE WINNER
        // PLAYER 1 ROCK AND FIRE

        if (player1.getChoice().equals("rock") && player1.getElement().equals("fire")) {
            if (player2.getChoice().equals("rock") && player2.getElement().equals("fire")) {
                draw();
            } else if ((player2.getChoice().equals("rock") && player2.getElement().equals("water")) ||
                    (player2.getChoice().equals("paper") && player2.getElement().equals("fire")) ||
                    (player2.getChoice().equals("paper") && player2.getElement().equals("leaf"))) {
                p1Life1();
            } else if (player2.getChoice().equals("rock") && player2.getElement().equals("leaf") ||
                    (player2.getChoice().equals("scissors") && player2.getElement().equals("fire")) ||
                    (player2.getChoice().equals("scissors") && player2.getElement().equals("water"))) {
                p2Life1();
            } else if (player2.getChoice().equals("paper") && player2.getElement().equals("water")) {
                p1Life2();
            } else if (player2.getChoice().equals("scissors") && player2.getElement().equals("leaf")) {
                p2Life2();
            }
        }
        // PLAYER 1 ROCK AND WATER
        else if (player1.getChoice().equals("rock") && player1.getElement().equals("water")) {
            if ((player2.getChoice().equals("rock") && player2.getElement().equals("leaf")) ||
                    (player2.getChoice().equals("paper") && player2.getElement().equals("fire")) ||
                    (player2.getChoice().equals("paper") && player2.getElement().equals("water"))) {
                p1Life1();
            } else if ((player2.getChoice().equals("rock") && player2.getElement().equals("fire")) ||
                    (player2.getChoice().equals("scissors") && player2.getElement().equals("water")) ||
                    (player2.getChoice().equals("scissors") && player2.getElement().equals("leaf"))) {
                p2Life1();
            } else if (player2.getChoice().equals("paper") && player2.getElement().equals("leaf")) {
                p1Life2();
            } else if (player2.getChoice().equals("scissors") && player2.getElement().equals("fire")) {
                p2Life2();
            } else {
                draw();
            }
        }
        // PLAYER 1 ROCK AND LEAF
        else if (player1.getChoice().equals("rock") && player1.getElement().equals("leaf")) {
            if ((player2.getChoice().equals("rock") && player2.getElement().equals("fire")) ||
                    (player2.getChoice().equals("paper") && player2.getElement().equals("leaf")) ||
                    (player2.getChoice().equals("paper") && player2.getElement().equals("water"))) {
                p1Life1();
            } else if ((player2.getChoice().equals("rock") && player2.getElement().equals("water")) ||
                    (player2.getChoice().equals("scissors") && player2.getElement().equals("fire")) ||
                    (player2.getChoice().equals("scissors") && player2.getElement().equals("leaf"))) {
                p2Life1();
            } else if (player2.getChoice().equals("paper") && player2.getElement().equals("fire")) {
                p1Life2();
            } else if (player2.getChoice().equals("scissors") && player2.getElement().equals("water")) {
                p2Life2();
            } else {
                draw();
            }
        }
        // PLAYER 1 PAPER AND FIRE
        else if (player1.getChoice().equals("paper") && player1.getElement().equals("fire")) {
            if ((player2.getChoice().equals("paper") && player2.getElement().equals("water")) ||
                    (player2.getChoice().equals("scissors") && player2.getElement().equals("fire")) ||
                    (player2.getChoice().equals("scissors") && player2.getElement().equals("leaf"))) {
                p1Life1();
            } else if ((player2.getChoice().equals("rock") && player2.getElement().equals("fire")) ||
                    (player2.getChoice().equals("rock") && player2.getElement().equals("water")) ||
                    (player2.getChoice().equals("paper") && player2.getElement().equals("leaf"))) {
                p2Life1();
            } else if (player2.getChoice().equals("scissors") && player2.getElement().equals("water")) {
                p1Life2();
            } else if (player2.getChoice().equals("rock") && player2.getElement().equals("leaf")) {
                p2Life2();
            } else {
                draw();
            }
        }
        // PLAYER 1 PAPER AND WATER
        else if (player1.getChoice().equals("paper") && player1.getElement().equals("water")) {
            if ((player2.getChoice().equals("paper") && player2.getElement().equals("leaf")) ||
                    (player2.getChoice().equals("scissors") && player2.getElement().equals("fire")) ||
                    (player2.getChoice().equals("scissors") && player2.getElement().equals("water"))) {
                p1Life1();
            } else if ((player2.getChoice().equals("rock") && player2.getElement().equals("water")) ||
                    (player2.getChoice().equals("rock") && player2.getElement().equals("leaf")) ||
                    (player2.getChoice().equals("paper") && player2.getElement().equals("fire"))) {
                p2Life1();
            } else if (player2.getChoice().equals("scissors") && player2.getElement().equals("leaf")) {
                p1Life2();
            } else if (player2.getChoice().equals("rock") && player2.getElement().equals("fire")) {
                p2Life2();
            } else {
                draw();
            }
        }
        // PLAYER 1 PAPER AND LEAF
        else if (player1.getChoice().equals("paper") && player1.getElement().equals("leaf")) {
            if ((player2.getChoice().equals("paper") && player2.getElement().equals("fire")) ||
                    (player2.getChoice().equals("scissors") && player2.getElement().equals("water")) ||
                    (player2.getChoice().equals("scissors") && player2.getElement().equals("leaf"))) {
                p1Life1();
            } else if ((player2.getChoice().equals("rock") && player2.getElement().equals("fire")) ||
                    (player2.getChoice().equals("rock") && player2.getElement().equals("leaf")) ||
                    (player2.getChoice().equals("paper") && player2.getElement().equals("water"))) {
                p2Life1();
            } else if (player2.getChoice().equals("scissors") && player2.getElement().equals("fire")) {
                p1Life2();
            } else if (player2.getChoice().equals("rock") && player2.getElement().equals("water")) {
                p2Life2();
            } else {
                draw();
            }
        }
        // PLAYER 1 SCISSORS AND FIRE
        else if (player1.getChoice().equals("scissors") && player1.getElement().equals("fire")) {
            if ((player2.getChoice().equals("rock") && player2.getElement().equals("fire")) ||
                    (player2.getChoice().equals("rock") && player2.getElement().equals("leaf")) ||
                    (player2.getChoice().equals("scissors") && player2.getElement().equals("water"))) {
                p1Life1();
            } else if ((player2.getChoice().equals("paper") && player2.getElement().equals("fire")) ||
                    (player2.getChoice().equals("paper") && player2.getElement().equals("water")) ||
                    (player2.getChoice().equals("scissors") && player2.getElement().equals("leaf"))) {
                p2Life1();
            } else if (player2.getChoice().equals("rock") && player2.getElement().equals("water")) {
                p1Life2();
            } else if (player2.getChoice().equals("paper") && player2.getElement().equals("leaf")) {
                p2Life2();
            } else {
                draw();
            }
        }
        // PLAYER 1 SCISSORS AND WATER
        else if (player1.getChoice().equals("scissors") && player1.getElement().equals("water")) {
            if ((player2.getChoice().equals("rock") && player2.getElement().equals("fire")) ||
                    (player2.getChoice().equals("rock") && player2.getElement().equals("water")) ||
                    (player2.getChoice().equals("scissors") && player2.getElement().equals("leaf"))) {
                p1Life1();
            } else if ((player2.getChoice().equals("paper") && player2.getElement().equals("water")) ||
                    (player2.getChoice().equals("paper") && player2.getElement().equals("leaf")) ||
                    (player2.getChoice().equals("scissors") && player2.getElement().equals("fire"))) {
                p2Life1();
            } else if (player2.getChoice().equals("rock") && player2.getElement().equals("leaf")) {
                p1Life2();
            } else if (player2.getChoice().equals("paper") && player2.getElement().equals("fire")) {
                p2Life2();
            } else {
                draw();
            }
        }
        // PLAYER 1 SCISSORS AND LEAF
        else if (player1.getChoice().equals("scissors") && player1.getElement().equals("leaf")) {
            if ((player2.getChoice().equals("rock") && player2.getElement().equals("water")) ||
                    (player2.getChoice().equals("rock") && player2.getElement().equals("leaf")) ||
                    (player2.getChoice().equals("scissors") && player2.getElement().equals("fire"))) {
                p1Life1();
            } else if ((player2.getChoice().equals("paper") && player2.getElement().equals("fire")) ||
                    (player2.getChoice().equals("paper") && player2.getElement().equals("leaf")) ||
                    (player2.getChoice().equals("scissors") && player2.getElement().equals("water"))) {
                p2Life1();
            } else if (player2.getChoice().equals("rock") && player2.getElement().equals("fire")) {
                p1Life2();
            } else if (player2.getChoice().equals("paper") && player2.getElement().equals("water")) {
                p2Life2();
            } else {
                draw();
            }
        }

        if (player1.getLives() <= 0) {
            gameOver.setIcon(new ImageIcon(
                    "game end (game over- you won- good game- well played)\\game end texts\\GOOD GAME (TEXT).png"));
            p1lives.setText("Lives: 0");
            p2Won = true;
            lastNum = recentIndex.get(recentIndex.size() - 1);

            switch (lastNum) {
                case 0:
                    gameMusic.winner();
                    break;
                case 1:
                    gameMusic.winner2();
                    break;
                case 2:
                    gameMusic.winner3();
                    break;
                case 3:
                    gameMusic.winner4();
                    break;
            }
            gameIsOver();
        } else if (player2.getLives() <= 0) {
            p2lives.setText("Lives: 0");
            gameOver.setIcon(new ImageIcon(
                    "game end (game over- you won- good game- well played)\\game end texts\\well playedTEXT).png"));
            p1Won = true;
            lastNum = recentIndex.get(recentIndex.size() - 1);

            switch (lastNum) {
                case 0:
                    gameMusic.winner();
                    break;
                case 1:
                    gameMusic.winner2();
                    break;
                case 2:
                    gameMusic.winner3();
                    break;
                case 3:
                    gameMusic.winner4();
                    break;
            }
            gameIsOver();
        } else if (player1.getLives() == 1 && player2.getLives() == 1) {
            round.setText("Final round");
        } else {
            p1Choosed = false;
            p2Choosed = false;
            roundNo += 1;
            round.setText("Round No: " + Integer.toString(roundNo));

            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                public void run() {
                    p1Card.setIcon(new ImageIcon("default series\\backcard.png"));
                    p2Card.setIcon(new ImageIcon("default series\\backcard.png"));

                    enableButtons();

                    p1Status.setVisible(true);
                    p2Status.setVisible(true);

                    p1Status.setIcon(new ImageIcon("buttons and prompts\\player 1 input butts.png"));
                    p2Status.setIcon(new ImageIcon("buttons and prompts\\player 2 input butts.png"));

                }
            };
            timer.schedule(task, 2500);
        }

    }

    public static void main(String[] args) {

        gamev2 mainGame = new gamev2();

    }
}