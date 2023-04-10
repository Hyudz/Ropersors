package files;

//PVE

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.util.Timer;
import java.util.TimerTask;

public class game extends JPanel implements ActionListener {

    JLabel p1lives, p2lives, status, name1, name2, paused, overlay, background, gameOver, nameplate, nameplate2,
            nameplate3, round, unknownCard, damage, p1Hurt, p2Hurt;
    JPanel bg, thisPanel, gPaused, gameOverPanel, exitPanel, dmgIndicator;
    ImageIcon board, namplates, vignette;
    Random randomChoice;
    JButton rockButton, paperButton, scissorButton, retryButton, pauseButton, playButton, newGame, homeButton, yes, no,
            homeButton2;
    String[] objects = { "rock", "paper", "scissors" };
    String[] elements = { "fire", "water", "leaf" };
    String[] boards = { "boards\\magam.png", "boards\\sky.png", "boards\\snad.png", "boards\\wood.png" };
    String[] nameplates = { "nameplates\\magam.png", "nameplates\\sky.png", "nameplates\\snad.png",
            "nameplates\\wood.png" };
    Random randomBg;
    int randomIndex, computerCard, roundNo, lastNum;
    Image boardImage, boardResized, vignetteImage, vignetteResized;
    players player1 = new players();
    players computer = new players();
    music gameMusic = new music();

    boolean p1Won, p2Won = false;

    ArrayList<Integer> recentIndex = new ArrayList<Integer>();

    game() {
        thisPanel = new JPanel();
        thisPanel.setLocation(0, 0);
        thisPanel.setSize(1920, 1080);
        thisPanel.setVisible(true);
        thisPanel.setLayout(null);
        this.add(thisPanel);

        roundNo = 1;

        randomBg = new Random();
        randomIndex = randomBg.nextInt(4);
        // randomIndex = 3;
        recentIndex.add(randomIndex);

        player1.setName("Player 1");
        computer.setName("Computer");

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

        // ADD a panel of game over
        gameOverPanel = new JPanel();
        gameOverPanel.setLayout(null);
        gameOverPanel.setOpaque(false);
        gameOverPanel.setVisible(false);
        gameOverPanel.setSize(1150, 500);
        gameOverPanel.setLocation(200, 200);
        thisPanel.add(gameOverPanel);

        gameOver = new JLabel();
        gameOver.setSize(871, 270);
        gameOver.setLocation(125, -35);
        gameOverPanel.add(gameOver);

        // SET THE CONFIGURATION OF PLAYER 1 NAME
        name1 = new JLabel();
        name1.setText(player1.getName());
        name1.setLocation(275, 80);
        name1.setSize(150, 30);
        name1.setFont(new Font("DePixel", Font.BOLD, 23));
        name1.setForeground(Color.WHITE);
        thisPanel.add(name1);

        // ETO NAMAN YUNG SA LIVES NILA
        p1lives = new JLabel();
        p1lives.setText("Lives: " + player1.getLives());
        p1lives.setFont(new Font("DePixel", Font.BOLD, 18));
        p1lives.setForeground(Color.white);
        p1lives.setSize(150, 30);
        p1lives.setLocation(290, 120);
        thisPanel.add(p1lives);

        p2lives = new JLabel();
        p2lives.setText("Lives: " + computer.getLives());
        p2lives.setForeground(Color.WHITE);
        p2lives.setFont(new Font("DePixel", Font.BOLD, 18));
        p2lives.setSize(150, 30);
        p2lives.setLocation(1085, 120);
        thisPanel.add(p2lives);

        // SET THE CONFIGURATION OF COMPUTER NAME
        name2 = new JLabel();
        name2.setText(computer.getName());
        name2.setLocation(1055, 80);
        name2.setForeground(Color.WHITE);
        name2.setSize(175, 30);
        name2.setFont(new Font("DePixel", Font.BOLD, 23));
        thisPanel.add(name2);

        // SET THE ROUND INDICATOR
        round = new JLabel();
        round.setSize(175, 30);
        round.setForeground(Color.white);
        round.setFont(new Font("DePixel", Font.BOLD, 18));
        round.setText("Round No: " + Integer.toString(roundNo));
        round.setLocation(675, 100);
        thisPanel.add(round);

        // SET THE POSITIONS OF NAMEPLATE
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

        // SET THE POSITIONS OF NAMEPLATE
        nameplate3 = new JLabel();
        nameplate3.setSize(390, 120);
        nameplate3.setLocation(965, 50);
        nameplate3.setIcon(new ImageIcon(nameplates[randomIndex]));
        thisPanel.add(nameplate3);

        // RETRY BUTTON (WHEN THE GAME IS OVER)
        retryButton = new JButton(new ImageIcon("game paused stuff\\restart.png"));
        retryButton.setSize(211, 75);
        retryButton.setContentAreaFilled(false);
        retryButton.setBorderPainted(false);
        retryButton.setLocation(500, 200);
        retryButton.addActionListener(e -> retry());
        retryButton.addActionListener(this);
        retryButton.addActionListener(e -> player1.setLives(10));
        retryButton.addActionListener(e -> computer.setLives(10));
        gameOverPanel.add(retryButton);

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

        // SET THE HOME BUTTON (NOW AS MAIN MENU)
        homeButton = new JButton(new ImageIcon("game paused stuff\\main menu.png"));
        homeButton.setSize(324, 75);
        homeButton.setLocation(0, 265);
        homeButton.setBorderPainted(false);
        homeButton.setContentAreaFilled(false);
        homeButton.addActionListener(e -> confirm());
        gPaused.add(homeButton);

        paused = new JLabel(new ImageIcon("game paused stuff\\GAME PAUSED (TEXT).png"));
        paused.setSize(925, 145);
        gPaused.add(paused);

        // NEW GAME BUTTON (INSIDE THE PAUSE BUTTON)
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

        // RED INDICATOR KUNG SINO YUNG NABAWASAN
        p1Hurt = new JLabel();
        p1Hurt.setIcon(new ImageIcon("damage texts and hit indicator\\getting hit indicator.png"));
        p1Hurt.setSize(240, 360);
        p1Hurt.setVisible(false);
        p1Hurt.setLocation(200, 200);
        thisPanel.add(p1Hurt);

        // COMFIGURATIONS NG ROCK PAPER AND SCISSORS
        rockButton = new JButton();
        rockButton.setSize(240, 360);
        rockButton.setLocation(90, 200);
        rockButton.setIcon(new ImageIcon("default series\\default rock card.png"));
        rockButton.setOpaque(true);
        rockButton.setContentAreaFilled(false);
        rockButton.setBorderPainted(false);
        rockButton.addActionListener(e -> proceed());
        rockButton.addActionListener(e -> player1.setChoice("rock"));
        rockButton.addActionListener(e -> p1Hurt.setLocation(90, 200));
        thisPanel.add(rockButton);

        paperButton = new JButton();
        paperButton.setSize(240, 360);
        paperButton.setLocation(330, 200);
        paperButton.setIcon(new ImageIcon("default series\\default paper card.png"));
        paperButton.setContentAreaFilled(false);
        paperButton.setBorderPainted(false);
        paperButton.addActionListener(e -> proceed());
        paperButton.addActionListener(e -> player1.setChoice("paper"));
        paperButton.addActionListener(e -> p1Hurt.setLocation(330, 200));
        thisPanel.add(paperButton);

        scissorButton = new JButton();
        scissorButton.setSize(240, 360);
        scissorButton.setLocation(570, 200);
        scissorButton.setIcon(new ImageIcon("default series\\default scissors card.png"));
        scissorButton.setContentAreaFilled(false);
        scissorButton.setBorderPainted(false);
        scissorButton.addActionListener(e -> proceed());
        scissorButton.addActionListener(e -> player1.setChoice("scissors"));
        scissorButton.addActionListener(e -> p1Hurt.setLocation(570, 200));
        thisPanel.add(scissorButton);

        // P2 HURT (YUNG RED)
        p2Hurt = new JLabel();
        p2Hurt.setIcon(new ImageIcon("damage texts and hit indicator\\getting hit indicator.png"));
        p2Hurt.setSize(240, 360);
        p2Hurt.setVisible(false);
        p2Hurt.setLocation(1000, 200);
        thisPanel.add(p2Hurt);

        // LABELS OF ROCK PAPER SCISORS BUT AS IMAGES FOR COMPUTER SIDE
        unknownCard = new JLabel();
        unknownCard.setIcon(new ImageIcon("default series\\backcard.png"));
        unknownCard.setSize(240, 360);
        unknownCard.setLocation(1000, 200);
        thisPanel.add(unknownCard);

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

        // SET THE BACKGROUND OF THE BOARD
        board = new ImageIcon(boards[randomIndex]);
        boardImage = board.getImage();
        boardResized = boardImage.getScaledInstance(1535, 792, java.awt.Image.SCALE_SMOOTH);
        board = new ImageIcon(boardResized);

        // ISET NATIN YUNG SA BACKGROUND NAMAN
        background = new JLabel();
        background.setIcon(board);
        background.setLocation(0, -143);
        background.setSize(1920, 1080);

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
        // this.setUndecorated(true);
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
        unknownCard.setVisible(true);
        unknownCard.setIcon(new ImageIcon("default series\\backcard.png"));
        pauseButton.setVisible(true);
        p1lives.setForeground(Color.white);
        p2lives.setForeground(Color.white);
        player1.setLives(10);
        computer.setLives(10);
        rockButton.setVisible(true);
        paperButton.setVisible(true);
        scissorButton.setVisible(true);
        gameOver.setVisible(false);
        roundNo = 1;
        round.setText("Round No: " + roundNo);
        p1lives.setText("Lives: " + Integer.toString(player1.getLives()));
        p2lives.setText("Lives: " + Integer.toString(computer.getLives()));
    }

    // ISET NATIN YUNG SA PAUSE METHOD
    public void pauseMethod() {
        rockButton.setVisible(false);
        paperButton.setVisible(false);
        scissorButton.setVisible(false);
        unknownCard.setVisible(false);
        paused.setVisible(true);
        p1lives.setVisible(false);
        p2lives.setVisible(false);
        overlay.setVisible(true);
        name1.setVisible(false);
        name2.setVisible(false);
        gPaused.setVisible(true);
        round.setVisible(false);
        p1Hurt.setVisible(false);
        p2Hurt.setVisible(false);
        dmgIndicator.setVisible(false);
    }

    // ETO NAMAN YUNG SA PLAY
    public void playMethod() {
        overlay.setVisible(false);
        unknownCard.setVisible(true);
        rockButton.setVisible(true);
        paperButton.setVisible(true);
        scissorButton.setVisible(true);
        pauseButton.setVisible(true);
        p1lives.setVisible(true);
        p2lives.setVisible(true);
        name1.setVisible(true);
        name2.setVisible(true);
        gPaused.setVisible(false);
        round.setVisible(true);
    }

    // ETONG METHOD NA TO, ETO YUNG PARA MAG NEW GAME ULIT KUNG SAKALING ANG ISA AY
    // MATALO
    public void retry() {
        if (p1Won == true) {
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
        } else if (p2Won == true) {
            lastNum = recentIndex.get(recentIndex.size() - 1);

            switch (lastNum) {
                case 0:
                    gameMusic.gameRestartLost();
                    break;
                case 1:
                    gameMusic.gameRestartLost2();
                    break;
                case 2:
                    gameMusic.gameRestartLost3();
                    break;
                case 3:
                    gameMusic.gameRestartLost4();
                    break;
            }
        }

        roundNo = 1;
        gameOverPanel.setVisible(false);
        round.setText("Round No: " + roundNo);
        player1.setLives(10);
        computer.setLives(10);
        rockButton.setVisible(true);
        paperButton.setVisible(true);
        scissorButton.setVisible(true);
        overlay.setVisible(false);
        rockButton.setEnabled(true);
        paperButton.setEnabled(true);
        scissorButton.setEnabled(true);
        unknownCard.setVisible(true);
        pauseButton.setVisible(true);
        p1lives.setText("Lives: " + Integer.toString(player1.getLives()));
        p2lives.setText("Lives: " + Integer.toString(computer.getLives()));
        round.setVisible(true);
        p1lives.setVisible(true);
        p2lives.setVisible(true);
        name1.setVisible(true);
        name2.setVisible(true);
        p1Won = false;
        p2Won = false;
    }

    public void gameIsOver() {
        round.setVisible(false);
        p1lives.setVisible(false);
        p2lives.setVisible(false);
        name1.setVisible(false);
        name2.setVisible(false);
        dmgIndicator.setVisible(false);
        overlay.setVisible(true);
        p1Hurt.setVisible(false);
        p2Hurt.setVisible(false);
        gameOverPanel.setVisible(true);
        pauseButton.setVisible(false);
        rockButton.setVisible(false);
        paperButton.setVisible(false);
        scissorButton.setVisible(false);
        unknownCard.setVisible(false);
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
                dmgIndicator.setVisible(false);
                p1Hurt.setVisible(false);
            }
        };
        indicator.schedule(life, 2500);
    }

    public void computerLife1() {
        int livesp2 = computer.getLives() - 1;
        computer.setLives(livesp2);
        p2lives.setText("Lives: " + Integer.toString(computer.getLives()));

        p2Hurt.setVisible(true);
        dmgIndicator.setVisible(true);
        damage.setIcon(new ImageIcon("damage texts and hit indicator\\- 1 damage.png"));

        Timer indicator = new Timer();
        TimerTask life = new TimerTask() {
            public void run() {
                dmgIndicator.setVisible(false);
                p2Hurt.setVisible(false);
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

    public void computerLife2() {
        int livesp2 = computer.getLives() - 2;
        computer.setLives(livesp2);
        p2lives.setText("Lives: " + Integer.toString(computer.getLives()));
        gameMusic.absoluteSound();

        p2Hurt.setVisible(true);
        dmgIndicator.setVisible(true);
        damage.setIcon(new ImageIcon("damage texts and hit indicator\\- 2 absolute damage.png"));

        Timer indicator = new Timer();
        TimerTask life = new TimerTask() {
            public void run() {
                dmgIndicator.setVisible(false);
                p2Hurt.setVisible(false);
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

    public void proceed() {
        randomChoice = new Random();

        // DISABLE THE BUTTONS TO AVOID SPAMMING
        rockButton.setEnabled(false);
        scissorButton.setEnabled(false);
        paperButton.setEnabled(false);
        rockButton.setDisabledIcon(new ImageIcon("default series\\default rock card.png"));
        paperButton.setDisabledIcon(new ImageIcon("default series\\default paper card.png"));
        scissorButton.setDisabledIcon(new ImageIcon("default series\\default scissors card.png"));

        // RANDOMIZER FOR COMPUTER TO SELECT WHAT OBJECT
        int computerChoice = randomChoice.nextInt(3);
        computer.setChoice(objects[computerChoice]);
        computerCard = computerChoice;

        Random randomChoiceofElement = new Random();
        int randomInfuse = randomChoiceofElement.nextInt(3);
        // INFUSAL OF ELEMENT FOR COMPUTER

        if (computer.getChoice().equals("rock")) {
            if (randomInfuse == 0) {
                unknownCard.setIcon(new ImageIcon("Fire Series\\fire rock card.png"));
                computer.setElement("fire");
            } else if (randomInfuse == 1) {
                unknownCard.setIcon(new ImageIcon("Water Series\\water rock card.png"));
                computer.setElement("water");
            } else if (randomInfuse == 2) {
                unknownCard.setIcon(new ImageIcon("Leaf Series\\leaf rock card.png"));
                computer.setElement("leaf");
            }
        } else if (computer.getChoice().equals("paper")) {
            if (randomInfuse == 0) {
                unknownCard.setIcon(new ImageIcon("Fire Series\\fire paper card.png"));
                computer.setElement("fire");
            } else if (randomInfuse == 1) {
                unknownCard.setIcon(new ImageIcon("Water Series\\water paper card.png"));
                computer.setElement("water");
            } else if (randomInfuse == 2) {
                unknownCard.setIcon(new ImageIcon("Leaf Series\\leaf paper card.png"));
                computer.setElement("leaf");
            }
        } else if (computer.getChoice().equals("scissors")) {
            if (randomInfuse == 0) {
                unknownCard.setIcon(new ImageIcon("Fire Series\\fire scissors card.png"));
                computer.setElement("fire");
            } else if (randomInfuse == 1) {
                unknownCard.setIcon(new ImageIcon("Water Series\\water scissors card.png"));
                computer.setElement("water");
            } else if (randomInfuse == 2) {
                unknownCard.setIcon(new ImageIcon("Leaf Series\\leaf scissors card.png"));
                computer.setElement("leaf");
            }
        }

        randomChoice = new Random();
        int randomElement = randomChoice.nextInt(3);
        player1.setElement((elements[randomElement]));

        // INFUSAL OF ELEMENTS FOR PLAYER
        if (player1.getChoice().equals("rock")) {
            if (player1.getElement().equals("fire")) {
                rockButton.setDisabledIcon(new ImageIcon("Fire Series\\fire rock card.png"));
            } else if (player1.getElement().equals("water")) {
                rockButton.setDisabledIcon(new ImageIcon("Water Series\\water rock card.png"));
            } else if (player1.getElement().equals("leaf")) {
                rockButton.setDisabledIcon(new ImageIcon("Leaf Series\\leaf rock card.png"));
            }

        } else if (player1.getChoice().equals("paper")) {
            if (randomElement == 0) {
                paperButton.setDisabledIcon(new ImageIcon("Fire Series\\fire paper card.png"));
            } else if (player1.getElement().equals("water")) {
                paperButton.setDisabledIcon(new ImageIcon("Water Series\\water paper card.png"));
            } else if (player1.getElement().equals("leaf")) {
                paperButton.setDisabledIcon(new ImageIcon("Leaf Series\\leaf paper card.png"));
            }
        } else if (player1.getChoice().equals("scissors")) {
            if (randomElement == 0) {
                scissorButton.setDisabledIcon(new ImageIcon("Fire Series\\fire scissors card.png"));
            } else if (player1.getElement().equals("water")) {
                scissorButton.setDisabledIcon(new ImageIcon("Water Series\\water scissors card.png"));
            } else if (player1.getElement().equals("leaf")) {
                scissorButton.setDisabledIcon(new ImageIcon("Leaf Series\\leaf scissors card.png"));
            }
        }

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                rockButton.setIcon(new ImageIcon("default series\\default rock card.png"));
                paperButton.setIcon(new ImageIcon("default series\\default paper card.png"));
                scissorButton.setIcon(new ImageIcon("default series\\default scissors card.png"));
                unknownCard.setIcon(new ImageIcon("default series\\backcard.png"));

                rockButton.setEnabled(true);
                scissorButton.setEnabled(true);
                paperButton.setEnabled(true);
            }
        };
        timer.schedule(task, 2500);

        // ON THE FOLLOWING BLOCK OF CODE, THE PROGRAM WILL DETERMINE WHO IS THE WINNER
        // PLAYER 1 ROCK AND FIRE

        if (player1.getChoice().equals("rock") && player1.getElement().equals("fire")) {
            if (computer.getChoice().equals("rock") && computer.getElement().equals("fire")) {
                draw();
            } else if ((computer.getChoice().equals("rock") && computer.getElement().equals("water")) ||
                    (computer.getChoice().equals("paper") && computer.getElement().equals("fire")) ||
                    (computer.getChoice().equals("paper") && computer.getElement().equals("leaf"))) {
                p1Life1();
            } else if (computer.getChoice().equals("rock") && computer.getElement().equals("leaf") ||
                    (computer.getChoice().equals("scissors") && computer.getElement().equals("fire")) ||
                    (computer.getChoice().equals("scissors") && computer.getElement().equals("water"))) {
                computerLife1();
            } else if (computer.getChoice().equals("paper") && computer.getElement().equals("water")) {
                p1Life2();
            } else if (computer.getChoice().equals("scissors") && computer.getElement().equals("leaf")) {
                computerLife2();
            }
        }
        // PLAYER 1 ROCK AND WATER
        else if (player1.getChoice().equals("rock") && player1.getElement().equals("water")) {
            if ((computer.getChoice().equals("rock") && computer.getElement().equals("leaf")) ||
                    (computer.getChoice().equals("paper") && computer.getElement().equals("fire")) ||
                    (computer.getChoice().equals("paper") && computer.getElement().equals("water"))) {
                p1Life1();
            } else if ((computer.getChoice().equals("rock") && computer.getElement().equals("fire")) ||
                    (computer.getChoice().equals("scissors") && computer.getElement().equals("water")) ||
                    (computer.getChoice().equals("scissors") && computer.getElement().equals("leaf"))) {
                computerLife1();
            } else if (computer.getChoice().equals("paper") && computer.getElement().equals("leaf")) {
                p1Life2();
            } else if (computer.getChoice().equals("scissors") && computer.getElement().equals("fire")) {
                computerLife2();
            } else {
                draw();
            }
        }
        // PLAYER 1 ROCK AND LEAF
        else if (player1.getChoice().equals("rock") && player1.getElement().equals("leaf")) {
            if ((computer.getChoice().equals("rock") && computer.getElement().equals("fire")) ||
                    (computer.getChoice().equals("paper") && computer.getElement().equals("leaf")) ||
                    (computer.getChoice().equals("paper") && computer.getElement().equals("water"))) {
                p1Life1();
            } else if ((computer.getChoice().equals("rock") && computer.getElement().equals("water")) ||
                    (computer.getChoice().equals("scissors") && computer.getElement().equals("fire")) ||
                    (computer.getChoice().equals("scissors") && computer.getElement().equals("leaf"))) {
                computerLife1();
            } else if (computer.getChoice().equals("paper") && computer.getElement().equals("fire")) {
                p1Life2();
            } else if (computer.getChoice().equals("scissors") && computer.getElement().equals("water")) {
                computerLife2();
            } else {
                draw();
            }
        }
        // PLAYER 1 PAPER AND FIRE
        else if (player1.getChoice().equals("paper") && player1.getElement().equals("fire")) {
            if ((computer.getChoice().equals("paper") && computer.getElement().equals("water")) ||
                    (computer.getChoice().equals("scissors") && computer.getElement().equals("fire")) ||
                    (computer.getChoice().equals("scissors") && computer.getElement().equals("leaf"))) {
                p1Life1();
            } else if ((computer.getChoice().equals("rock") && computer.getElement().equals("fire")) ||
                    (computer.getChoice().equals("rock") && computer.getElement().equals("water")) ||
                    (computer.getChoice().equals("paper") && computer.getElement().equals("leaf"))) {
                computerLife1();
            } else if (computer.getChoice().equals("scissors") && computer.getElement().equals("water")) {
                p1Life2();
            } else if (computer.getChoice().equals("rock") && computer.getElement().equals("leaf")) {
                computerLife2();
            } else {
                draw();
            }
        }
        // PLAYER 1 PAPER AND WATER
        else if (player1.getChoice().equals("paper") && player1.getElement().equals("water")) {
            if ((computer.getChoice().equals("paper") && computer.getElement().equals("leaf")) ||
                    (computer.getChoice().equals("scissors") && computer.getElement().equals("fire")) ||
                    (computer.getChoice().equals("scissors") && computer.getElement().equals("water"))) {
                p1Life1();
            } else if ((computer.getChoice().equals("rock") && computer.getElement().equals("water")) ||
                    (computer.getChoice().equals("rock") && computer.getElement().equals("leaf")) ||
                    (computer.getChoice().equals("paper") && computer.getElement().equals("fire"))) {
                computerLife1();
            } else if (computer.getChoice().equals("scissors") && computer.getElement().equals("leaf")) {
                p1Life2();
            } else if (computer.getChoice().equals("rock") && computer.getElement().equals("fire")) {
                computerLife2();
            } else {
                draw();
            }
        }
        // PLAYER 1 PAPER AND LEAF
        else if (player1.getChoice().equals("paper") && player1.getElement().equals("leaf")) {
            if ((computer.getChoice().equals("paper") && computer.getElement().equals("fire")) ||
                    (computer.getChoice().equals("scissors") && computer.getElement().equals("water")) ||
                    (computer.getChoice().equals("scissors") && computer.getElement().equals("leaf"))) {
                p1Life1();
            } else if ((computer.getChoice().equals("rock") && computer.getElement().equals("fire")) ||
                    (computer.getChoice().equals("rock") && computer.getElement().equals("leaf")) ||
                    (computer.getChoice().equals("paper") && computer.getElement().equals("water"))) {
                computerLife1();
            } else if (computer.getChoice().equals("scissors") && computer.getElement().equals("fire")) {
                p1Life2();
            } else if (computer.getChoice().equals("rock") && computer.getElement().equals("water")) {
                computerLife2();
            } else {
                draw();
            }
        }
        // PLAYER 1 SCISSORS AND FIRE
        else if (player1.getChoice().equals("scissors") && player1.getElement().equals("fire")) {
            if ((computer.getChoice().equals("rock") && computer.getElement().equals("fire")) ||
                    (computer.getChoice().equals("rock") && computer.getElement().equals("leaf")) ||
                    (computer.getChoice().equals("scissors") && computer.getElement().equals("water"))) {
                p1Life1();
            } else if ((computer.getChoice().equals("paper") && computer.getElement().equals("fire")) ||
                    (computer.getChoice().equals("paper") && computer.getElement().equals("water")) ||
                    (computer.getChoice().equals("scissors") && computer.getElement().equals("leaf"))) {
                computerLife1();
            } else if (computer.getChoice().equals("rock") && computer.getElement().equals("water")) {
                p1Life2();
            } else if (computer.getChoice().equals("paper") && computer.getElement().equals("leaf")) {
                computerLife2();
            } else {
                draw();
            }
        }
        // PLAYER 1 SCISSORS AND WATER
        else if (player1.getChoice().equals("scissors") && player1.getElement().equals("water")) {
            if ((computer.getChoice().equals("rock") && computer.getElement().equals("fire")) ||
                    (computer.getChoice().equals("rock") && computer.getElement().equals("water")) ||
                    (computer.getChoice().equals("scissors") && computer.getElement().equals("leaf"))) {
                p1Life1();
            } else if ((computer.getChoice().equals("paper") && computer.getElement().equals("water")) ||
                    (computer.getChoice().equals("paper") && computer.getElement().equals("leaf")) ||
                    (computer.getChoice().equals("scissors") && computer.getElement().equals("fire"))) {
                computerLife1();
            } else if (computer.getChoice().equals("rock") && computer.getElement().equals("leaf")) {
                p1Life2();
            } else if (computer.getChoice().equals("paper") && computer.getElement().equals("fire")) {
                computerLife2();
            } else {
                draw();
            }
        }
        // PLAYER 1 SCISSORS AND LEAF
        else if (player1.getChoice().equals("scissors") && player1.getElement().equals("leaf")) {
            if ((computer.getChoice().equals("rock") && computer.getElement().equals("water")) ||
                    (computer.getChoice().equals("rock") && computer.getElement().equals("leaf")) ||
                    (computer.getChoice().equals("scissors") && computer.getElement().equals("fire"))) {
                p1Life1();
            } else if ((computer.getChoice().equals("paper") && computer.getElement().equals("fire")) ||
                    (computer.getChoice().equals("paper") && computer.getElement().equals("leaf")) ||
                    (computer.getChoice().equals("scissors") && computer.getElement().equals("water"))) {
                computerLife1();
            } else if (computer.getChoice().equals("rock") && computer.getElement().equals("fire")) {
                p1Life2();
            } else if (computer.getChoice().equals("paper") && computer.getElement().equals("water")) {
                computerLife2();
            } else {
                draw();
            }
        }

        if (player1.getLives() <= 0) {
            p2Won = true;
            gameOver.setIcon(new ImageIcon(
                    "game end (game over- you won- good game- well played)\\game end texts\\GAME OVER(TEXT).png"));
            p1lives.setText("Lives: 0");
            lastNum = recentIndex.get(recentIndex.size() - 1);

            switch (lastNum) {
                case 0:
                    gameMusic.looser();
                    break;
                case 1:
                    gameMusic.looser2();
                    break;
                case 2:
                    gameMusic.looser3();
                    break;
                case 3:
                    gameMusic.looser4();
                    break;
            }
            gameIsOver();
        } else if (computer.getLives() <= 0) {
            p1Won = true;
            p2lives.setText("Lives: 0");

            gameOver.setIcon(new ImageIcon(
                    "game end (game over- you won- good game- well played)\\game end texts\\YOU WON(TEXT).png"));
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
        } else if (player1.getLives() == 1 && computer.getLives() == 1) {
            round.setText("Final round");
        } else {
            roundNo += 1;
            round.setText("Round No: " + Integer.toString(roundNo));
        }

    }

    public static void main(String[] args) {

        game mainGame = new game();

    }
}