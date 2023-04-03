package files;

//PVE

import javax.swing.*;
import java.awt.event.*;
import java.util.Random;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.util.Timer;
import java.util.TimerTask;

public class game extends JPanel implements ActionListener {

    JLabel p1lives, p2lives, status, name1, name2, paused, overlay, background,
            gameOver, nameplate, nameplate2, round, unknownCard;
    JPanel bg, confirmation, thisPanel;
    ImageIcon board, namplates;
    Random randomChoice;
    JButton rockButton, paperButton, scissorButton, retryButton, pauseButton, playButton, newGame, homeButton, yes, no;
    String[] objects = { "rock", "paper", "scissors" };
    String[] elements = { "fire", "water", "leaf" };
    String[] boards = { "boards\\magam.png", "boards\\sky.png", "boards\\snad.png", "boards\\wood.png" };
    String[] nameplates = { "nameplates\\magam.png", "nameplates\\sky.png", "nameplates\\snad.png",
            "nameplates\\wood.png" };
    Random randomBg;
    int randomIndex, computerCard, roundNo;
    Image boardImage, boardResized;
    players player1 = new players();
    players computer = new players();
    music gameMusic = new music();

    boolean p1Won, p2Won = false;

    game() {

        thisPanel = new JPanel();
        thisPanel.setLocation(0, 0);
        thisPanel.setSize(1920, 1080);
        thisPanel.setVisible(true);
        thisPanel.setLayout(null);
        this.add(thisPanel);

        roundNo = 1;
        // gameMusic.playPvEMode();

        randomBg = new Random();
        randomIndex = randomBg.nextInt(4);

        player1.setName("Player 1");
        computer.setName("Computer");

        ImageIcon vignette = new ImageIcon("vignette 1080.png");
        overlay = new JLabel();
        overlay.setLocation(0, 0);
        overlay.setIcon(vignette);
        overlay.setSize(1920, 1080);
        overlay.setVisible(false);
        thisPanel.add(overlay);

        // SET THE CONFIGURATION OF PLAYER 1 NAME
        name1 = new JLabel();
        name1.setText(player1.getName());
        name1.setLocation(300, 80);
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
        p1lives.setLocation(315, 120);
        thisPanel.add(p1lives);

        p2lives = new JLabel();
        p2lives.setText("Lives: " + computer.getLives());
        p2lives.setForeground(Color.WHITE);
        p2lives.setFont(new Font("DePixel", Font.BOLD, 18));
        p2lives.setSize(150, 30);
        p2lives.setLocation(1100, 120);
        thisPanel.add(p2lives);

        // SET THE CONFIGURATION OF COMPUTER NAME
        name2 = new JLabel();
        name2.setText(computer.getName());
        name2.setLocation(1075, 80);
        name2.setForeground(Color.WHITE);
        name2.setSize(175, 30);
        name2.setFont(new Font("DePixel", Font.BOLD, 23));
        thisPanel.add(name2);

        // SET THE ROUND INDICATOR
        round = new JLabel();
        round.setSize(100, 25);
        round.setForeground(Color.white);
        round.setText("Round No: " + Integer.toString(roundNo));
        round.setLocation(750, 100);
        thisPanel.add(round);

        // SET THE POSITIONS OF NAMEPLATE
        nameplate = new JLabel();
        nameplate.setSize(390, 120);
        nameplate.setLocation(180, 50);
        nameplate.setIcon(new ImageIcon(nameplates[randomIndex]));
        thisPanel.add(nameplate);

        // SET THE POSITIONS OF NAMEPLATE
        nameplate2 = new JLabel();
        nameplate2.setSize(390, 120);
        nameplate2.setLocation(965, 50);
        nameplate2.setIcon(new ImageIcon(nameplates[randomIndex]));
        thisPanel.add(nameplate2);

        // RETRY BUTTON
        ImageIcon newGameIcon = new ImageIcon(
                "Buttons\\try (w_color).png");
        retryButton = new JButton();
        retryButton.setIcon(newGameIcon);
        retryButton.setSize(32, 32);
        retryButton.setLocation(750, 500);
        retryButton.setVisible(false);
        retryButton.addActionListener(e -> retry(e));
        retryButton.addActionListener(this);
        retryButton.addActionListener(e -> player1.setLives(10));
        retryButton.addActionListener(e -> computer.setLives(10));
        thisPanel.add(retryButton);

        // PAUSE BUTTON
        ImageIcon pauseIcon = new ImageIcon(
                "Buttons\\pause (w_color).png");
        pauseButton = new JButton();
        pauseButton.setSize(32, 32);
        pauseButton.setIcon(pauseIcon);
        pauseButton.setLocation(750, 10);
        pauseButton.setFocusable(false);
        pauseButton.setBorderPainted(false);
        pauseButton.setFocusPainted(false);
        pauseButton.setContentAreaFilled(false);
        pauseButton.addActionListener(e -> pauseMethod());
        thisPanel.add(pauseButton);

        paused = new JLabel("Game Paused");
        paused.setFont(new Font("DePixel", Font.BOLD, 48));
        paused.setSize(500, 70);
        paused.setLocation(570, 300);
        paused.setVisible(false);
        thisPanel.add(paused);

        // SET THE HOME BUTTON
        homeButton = new JButton("Home");
        homeButton.setSize(32, 32);
        homeButton.setLocation(750, 500);
        homeButton.setVisible(false);
        homeButton.addActionListener(e -> homeButton());
        thisPanel.add(homeButton);

        // NEW GAME BUTTON (INSIDE THE PAUSE BUTTON)
        newGame = new JButton(newGameIcon);
        newGame.setSize(32, 32);
        newGame.setLocation(600, 500);
        newGame.setVisible(false);
        newGame.setBorderPainted(false);
        newGame.setFocusPainted(false);
        newGame.setContentAreaFilled(false);
        newGame.setFocusable(false);
        newGame.addActionListener(this);
        newGame.addActionListener(e -> tryAgain());
        thisPanel.add(newGame);

        // PLAY BUTTON
        ImageIcon playIcon = new ImageIcon("Buttons\\play_continue (w_color).png");
        playButton = new JButton(playIcon);
        playButton.setSize(32, 32);
        playButton.setFocusable(false);
        playButton.setBorderPainted(false);
        playButton.setFocusPainted(false);
        playButton.setContentAreaFilled(false);
        playButton.setLocation(900, 500);
        playButton.setVisible(false);
        playButton.addActionListener(e -> playMethod());
        thisPanel.add(playButton);

        gameOver = new JLabel();
        gameOver.setFont(new Font("DePixel", Font.BOLD, 48));
        gameOver.setSize(500, 70);
        gameOver.setLocation(600, 300);
        gameOver.setVisible(false);
        thisPanel.add(gameOver);

        // COMFIGURATIONS NG ROCK PAPER AND SCISSORS
        rockButton = new JButton();
        rockButton.setSize(240, 360);
        rockButton.setLocation(90, 280);
        rockButton.setIcon(new ImageIcon("default series\\default rock card.png"));
        rockButton.setOpaque(true);
        rockButton.setContentAreaFilled(false);
        rockButton.setBorderPainted(false);
        rockButton.addActionListener(e -> proceed());
        rockButton.addActionListener(e -> player1.setChoice("rock"));
        thisPanel.add(rockButton);

        paperButton = new JButton();
        paperButton.setSize(240, 360);
        paperButton.setLocation(350, 280);
        paperButton.setIcon(new ImageIcon("default series\\default paper card.png"));
        paperButton.setContentAreaFilled(false);
        paperButton.setBorderPainted(false);
        paperButton.addActionListener(e -> proceed());
        paperButton.addActionListener(e -> player1.setChoice("paper"));
        thisPanel.add(paperButton);

        scissorButton = new JButton();
        scissorButton.setSize(240, 360);
        scissorButton.setLocation(610, 280);
        scissorButton.setIcon(new ImageIcon("default series\\default scissors card.png"));
        scissorButton.setContentAreaFilled(false);
        scissorButton.setBorderPainted(false);
        scissorButton.addActionListener(e -> proceed());
        scissorButton.addActionListener(e -> player1.setChoice("scissors"));
        thisPanel.add(scissorButton);

        // LABELS OF ROCK PAPER SCISORS BUT AS IMAGES FOR COMPUTER SIDE
        unknownCard = new JLabel();
        unknownCard.setIcon(new ImageIcon("default series\\backcard.png"));
        unknownCard.setSize(240, 360);
        unknownCard.setLocation(1000, 280);
        thisPanel.add(unknownCard);

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
        gameMusic.playPvEMode();
        thisPanel.setVisible(true);
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

        gameMusic.stopPveMode();
    }

    // CHANGES THE BACKGROUND ALONG WITH THE NAMEPLATE
    @Override
    public void actionPerformed(ActionEvent e) {
        randomBg = new Random();
        randomIndex = randomBg.nextInt(4);
        nameplate.setIcon(new ImageIcon(nameplates[randomIndex]));
        nameplate2.setIcon(new ImageIcon(nameplates[randomIndex]));
        board = new ImageIcon(boards[randomIndex]);

        boardImage = board.getImage();
        boardResized = boardImage.getScaledInstance(1535, 792, java.awt.Image.SCALE_SMOOTH);
        board = new ImageIcon(boardResized);
        background.setIcon(board);

    }

    // TRY AGAIN OR NEW GAME NA NAKALAGAY SA MAY PAUSE WINDOW
    public void tryAgain() {
        playMethod();
        gameMusic.gameRestart1Normal();
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
        ;
    }

    // ISET NATIN YUNG SA PAUSE METHOD
    public void pauseMethod() {
        homeButton.setVisible(true);
        rockButton.setVisible(false);
        paperButton.setVisible(false);
        scissorButton.setVisible(false);
        pauseButton.setVisible(true);
        unknownCard.setVisible(false);
        paused.setVisible(true);
        p1lives.setVisible(false);
        p2lives.setVisible(false);
        overlay.setVisible(true);
        name1.setVisible(false);
        name2.setVisible(false);
        pauseButton.setVisible(false);
        newGame.setVisible(true);
        playButton.setVisible(true);
        round.setVisible(false);
    }

    // ETO NAMAN YUNG SA PLAY
    public void playMethod() {
        homeButton.setVisible(false);
        overlay.setVisible(false);
        unknownCard.setVisible(true);
        rockButton.setVisible(true);
        paperButton.setVisible(true);
        scissorButton.setVisible(true);
        pauseButton.setVisible(true);
        newGame.setVisible(false);
        paused.setVisible(false);
        p1lives.setVisible(true);
        p2lives.setVisible(true);
        name1.setVisible(true);
        name2.setVisible(true);
        pauseButton.setVisible(true);
        playButton.setVisible(false);
        round.setVisible(true);
    }

    // ETONG METHOD NA TO, ETO YUNG PARA MAG NEW GAME ULIT KUNG SAKALING ANG ISA AY
    // MATALO
    public void retry(ActionEvent e) {
        if (p1Won == true) {
            System.out.println("hahah");
            gameMusic.gameRestart1Won();
        } else if (p2Won == true) {
            gameMusic.gameRestart1Lost();
            System.out.println("hehe");
        } else {
            System.out.println("hoho");
        }
        roundNo = 1;
        homeButton.setVisible(false);
        round.setText("Round No: " + roundNo);
        player1.setLives(10);
        computer.setLives(10);
        rockButton.setVisible(true);
        paperButton.setVisible(true);
        scissorButton.setVisible(true);
        retryButton.setVisible(false);
        overlay.setVisible(false);
        gameOver.setVisible(false);
        rockButton.setEnabled(true);
        paperButton.setEnabled(true);
        scissorButton.setEnabled(true);
        unknownCard.setVisible(true);
        pauseButton.setVisible(true);
        p1lives.setText("Lives: " + Integer.toString(player1.getLives()));
        p1lives.setForeground(Color.white);
        p2lives.setForeground(Color.white);
        p2lives.setText("Lives: " + Integer.toString(computer.getLives()));
        p1Won = false;
        p2Won = false;
    }

    public void gameIsOver() {
        overlay.setVisible(true);
        pauseButton.setVisible(false);
        gameOver.setVisible(true);
        retryButton.setVisible(true);
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

    }

    public void computerLife1() {
        int livesp2 = computer.getLives() - 1;
        computer.setLives(livesp2);
        p2lives.setText("Lives: " + Integer.toString(computer.getLives()));
    }

    public void p1Life2() {
        int livesp1 = player1.getLives() - 2;
        player1.setLives(livesp1);
        p1lives.setText("Lives: " + Integer.toString(player1.getLives()));
        gameMusic.absoluteSound();
    }

    public void computerLife2() {
        int livesp2 = computer.getLives() - 2;
        computer.setLives(livesp2);
        p2lives.setText("Lives: " + Integer.toString(computer.getLives()));
        gameMusic.absoluteSound();
    }

    public void proceed() {
        randomChoice = new Random();

        // RANDOMIZER FOR COMPUTER TO SELECT WHAT OBJECT
        int computerChoice = randomChoice.nextInt(3);
        computer.setChoice(objects[computerChoice]);
        computerCard = computerChoice;

        Random randomChoiceofElement = new Random();
        int randomInfuse = randomChoiceofElement.nextInt(3);
        // INFUSAL OF ELEMENT FOR COMPUTER

        if (computer.getChoice().equals("rock")) {
            if (randomInfuse == 0) {
                unknownCard.setIcon(new ImageIcon("Fire Series\\fire rock card (2).png"));
                computer.setElement("fire");
            } else if (randomInfuse == 1) {
                unknownCard.setIcon(new ImageIcon("Water Series\\water rock card.png"));
                computer.setElement("water");
            } else if (randomInfuse == 2) {
                unknownCard.setIcon(new ImageIcon("Leaf Series\\leaf rock card (1).png"));
                computer.setElement("leaf");
            }
        } else if (computer.getChoice().equals("paper")) {
            if (randomInfuse == 0) {
                unknownCard.setIcon(new ImageIcon("Fire Series\\fire paper card (2).png"));
                computer.setElement("fire");
            } else if (randomInfuse == 1) {
                unknownCard.setIcon(new ImageIcon("Water Series\\water paper card.png"));
                computer.setElement("water");
            } else if (randomInfuse == 2) {
                unknownCard.setIcon(new ImageIcon("Leaf Series\\leaf paper card (1).png"));
                computer.setElement("leaf");
            }
        } else if (computer.getChoice().equals("scissors")) {
            if (randomInfuse == 0) {
                unknownCard.setIcon(new ImageIcon("Fire Series\\fire scissors card (2).png"));
                computer.setElement("fire");
            } else if (randomInfuse == 1) {
                unknownCard.setIcon(new ImageIcon("Water Series\\water scissors card.png"));
                computer.setElement("water");
            } else if (randomInfuse == 2) {
                unknownCard.setIcon(new ImageIcon("Leaf Series\\leaf scissors card (1).png"));
                computer.setElement("leaf");
            }
        }

        randomChoice = new Random();
        int randomElement = randomChoice.nextInt(3);
        player1.setElement((elements[randomElement]));

        // INFUSAL OF ELEMENTS FOR PLAYER
        if (player1.getChoice().equals("rock")) {
            if (player1.getElement().equals("fire")) {
                rockButton.setIcon(new ImageIcon("Fire Series\\fire rock card (2).png"));
            } else if (player1.getElement().equals("water")) {
                rockButton.setIcon(new ImageIcon("Water Series\\water rock card.png"));
            } else if (player1.getElement().equals("leaf")) {
                rockButton.setIcon(new ImageIcon("Leaf Series\\leaf rock card (1).png"));
            }

        } else if (player1.getChoice().equals("paper")) {
            if (randomElement == 0) {
                paperButton.setIcon(new ImageIcon("Fire Series\\fire paper card (2).png"));
            } else if (player1.getElement().equals("water")) {
                paperButton.setIcon(new ImageIcon("Water Series\\water paper card.png"));
            } else if (player1.getElement().equals("leaf")) {
                paperButton.setIcon(new ImageIcon("Leaf Series\\leaf paper card (1).png"));
            }
        } else if (player1.getChoice().equals("scissors")) {
            if (randomElement == 0) {
                scissorButton.setIcon(new ImageIcon("Fire Series\\fire scissors card (2).png"));
            } else if (player1.getElement().equals("water")) {
                scissorButton.setIcon(new ImageIcon("Water Series\\water scissors card.png"));
            } else if (player1.getElement().equals("leaf")) {
                scissorButton.setIcon(new ImageIcon("Leaf Series\\leaf scissors card (1).png"));
            }
        }

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                rockButton.setIcon(new ImageIcon("default series\\default rock card.png"));
                paperButton.setIcon(new ImageIcon("default series\\default paper card.png"));
                scissorButton.setIcon(new ImageIcon("default series\\default scissors card.png"));
                unknownCard.setIcon(new ImageIcon("default series\\backcard.png"));
            }
        };
        timer.schedule(task, 2000);

        // ON THE FOLLOWING BLOCK OF CODE, THE PROGRAM WILL DETERMINE WHO IS THE WINNER
        // PLAYER 1 ROCK AND FIRE

        if (player1.getChoice().equals("rock") && player1.getElement().equals("fire")) {
            if (computer.getChoice().equals("rock") && computer.getElement().equals("fire")) {
                // DO NOTHING KASI DRAW NAMAN
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
            }
        }

        if (player1.getLives() <= 0) {
            p2Won = true;
            gameOver.setText("Game over!");
            p1lives.setText("Lives: 0");
            gameMusic.looser();
            gameIsOver();
        } else if (computer.getLives() <= 0) {
            p1Won = true;
            p2lives.setText("Lives: 0");
            gameOver.setText("You won");
            gameMusic.winner();
            gameIsOver();
        } else if (player1.getLives() == 1 && computer.getLives() == 1) {
            round.setText("Final round");
        } else if (player1.getLives() <= 3) {
            p1lives.setForeground(Color.red);
        } else if (computer.getLives() <= 3) {
            p2lives.setForeground(Color.red);
        } else {
            roundNo += 1;
            round.setText("Round No: " + Integer.toString(roundNo));
        }

    }

    public static void main(String[] args) {

        game mainGame = new game();

    }
}