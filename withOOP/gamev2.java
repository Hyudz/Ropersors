package withOOP;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.*;
import java.awt.event.*;
import java.util.Random;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.util.Timer;
import java.util.TimerTask;

public class gamev2 extends JFrame implements ActionListener {

    Runnable turnBack;
    JLabel p1lives, p2lives, status, name1, name2, p1Card, p2Card, paused, overlay, background,
            gameOver, nameplate, nameplate2, round, sampleLog;
    JPanel bg;
    ImageIcon board, namplates;
    Random randomChoice;
    JButton rockButton, paperButton, scissorButton, retryButton, pauseButton, playButton, newGame;
    String[] objects = { "rock", "paper", "scissors" };
    String[] elements = { "fire", "water", "leaf" };
    String[] boards = { "boards\\magam.png", "boards\\sky.png", "boards\\snad.png", "boards\\wood.png" };
    String[] nameplates = { "nameplates\\magam.png", "nameplates\\sky.png", "nameplates\\snad.png",
            "nameplates\\wood.png" };
    Random randomBg;
    int randomIndex, computerCard, roundNo;
    Image boardImage, boardResized;
    players player1 = new players();
    players player2 = new players();

    public gamev2() {
        roundNo = 1;

        randomBg = new Random();
        randomIndex = randomBg.nextInt(4);

        player1.setName("Player 1");
        player2.setName("Player 2");

        ImageIcon vignette = new ImageIcon("vignette 1080.png");
        overlay = new JLabel();
        overlay.setLocation(0, 0);
        overlay.setIcon(vignette);
        overlay.setSize(1920, 1080);
        overlay.setVisible(false);
        this.add(overlay);

        // SET THE CONFIGURATION OF PLAYER 1 NAME
        name1 = new JLabel();
        name1.setText(player1.getName());
        name1.setLocation(300, 80);
        name1.setSize(150, 30);
        name1.setFont(new Font("DePixel", Font.BOLD, 23));
        name1.setForeground(Color.WHITE);
        this.add(name1);

        // ETO NAMAN YUNG SA LIVES NILA
        p1lives = new JLabel();
        p1lives.setText("Lives: " + player1.getLives());
        p1lives.setFont(new Font("DePixel", Font.BOLD, 18));
        p1lives.setForeground(Color.white);
        p1lives.setSize(150, 30);
        p1lives.setLocation(315, 120);
        this.add(p1lives);

        p2lives = new JLabel();
        p2lives.setText("Lives: " + player2.getLives());
        p2lives.setForeground(Color.WHITE);
        p2lives.setFont(new Font("DePixel", Font.BOLD, 18));
        p2lives.setSize(150, 30);
        p2lives.setLocation(1100, 120);
        this.add(p2lives);

        // SET THE CONFIGURATION OF computer NAME
        name2 = new JLabel();
        name2.setText(player2.getName());
        name2.setLocation(1075, 80);
        name2.setForeground(Color.WHITE);
        name2.setSize(175, 30);
        name2.setFont(new Font("DePixel", Font.BOLD, 23));
        this.add(name2);

        // SET THE ROUND INDICATOR
        round = new JLabel();
        round.setSize(100, 25);
        round.setForeground(Color.white);
        round.setText("Round No: " + Integer.toString(roundNo));
        round.setLocation(750, 100);
        this.add(round);

        // SET THE POSITIONS OF NAMEPLATE
        nameplate = new JLabel();
        nameplate.setSize(390, 120);
        nameplate.setLocation(180, 50);
        nameplate.setIcon(new ImageIcon(nameplates[randomIndex]));
        this.add(nameplate);

        // SET THE POSITIONS OF NAMEPLATE
        nameplate2 = new JLabel();
        nameplate2.setSize(390, 120);
        nameplate2.setLocation(965, 50);
        nameplate2.setIcon(new ImageIcon(nameplates[randomIndex]));
        this.add(nameplate2);

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
        retryButton.addActionListener(e -> player2.setLives(10));
        this.add(retryButton);

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
        this.add(pauseButton);

        paused = new JLabel("Game Paused");
        paused.setFont(new Font("DePixel", Font.BOLD, 48));
        paused.setSize(500, 70);
        paused.setLocation(570, 300);
        paused.setVisible(false);
        this.add(paused);

        // NEW GAME BUTTON
        newGame = new JButton(newGameIcon);
        newGame.setSize(32, 32);
        newGame.setLocation(700, 500);
        newGame.setVisible(false);
        newGame.setBorderPainted(false);
        newGame.setFocusPainted(false);
        newGame.setContentAreaFilled(false);
        newGame.setFocusable(false);
        newGame.addActionListener(this);
        newGame.addActionListener(e -> tryAgain());
        this.add(newGame);

        // PLAY BUTTON
        ImageIcon playIcon = new ImageIcon("Buttons\\play_continue (w_color).png");
        playButton = new JButton(playIcon);
        playButton.setSize(32, 32);
        playButton.setFocusable(false);
        playButton.setBorderPainted(false);
        playButton.setFocusPainted(false);
        playButton.setContentAreaFilled(false);
        playButton.setLocation(800, 500);
        playButton.setVisible(false);
        playButton.addActionListener(e -> playMethod());
        this.add(playButton);

        gameOver = new JLabel();
        gameOver.setFont(new Font("DePixel", Font.BOLD, 48));
        gameOver.setSize(500, 70);
        gameOver.setLocation(600, 300);
        gameOver.setVisible(false);
        this.add(gameOver);

        // COMFIGURATIONS NG ROCK PAPER AND SCISSORS
        rockButton = new JButton();
        // rockButton.setSize(266, 365);
        this.add(rockButton);

        paperButton = new JButton();
        paperButton.setIcon(new ImageIcon("Default RPS\\unknown  266x365.png"));
        this.add(paperButton);

        scissorButton = new JButton();
        scissorButton.setLocation(560, 280);
        this.add(scissorButton);

        KeyStroke rockKey = KeyStroke.getKeyStroke(KeyEvent.VK_A, 0);
        Action action1 = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player1.setChoice("rock");
                buttonPressed();
            }
        };

        InputMap input1 = rockButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        input1.put(rockKey, "performAction1");
        ActionMap actionMap1 = rockButton.getActionMap();
        actionMap1.put("performAction1", action1);

        KeyStroke paperKey = KeyStroke.getKeyStroke(KeyEvent.VK_S, 0);
        Action action2 = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player1.setChoice("paper");
                buttonPressed();
            }
        };

        InputMap input2 = rockButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        input2.put(paperKey, "performAction2");
        ActionMap actionMap2 = rockButton.getActionMap();
        actionMap2.put("performAction2", action2);

        KeyStroke scissorKey = KeyStroke.getKeyStroke(KeyEvent.VK_D, 0);
        Action action3 = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player1.setChoice("scissors");
                buttonPressed();
            }
        };

        InputMap input3 = rockButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        input3.put(scissorKey, "performAction3");
        ActionMap actionMap3 = rockButton.getActionMap();
        actionMap3.put("performAction3", action3);

        p1Card = new JLabel();
        p1Card.setIcon(new ImageIcon("Default RPS\\unknown  266x365.png"));
        p1Card.setSize(266, 365);
        p1Card.setLocation(250, 280);
        this.add(p1Card);

        // LABELS OF ROCK PAPER SCISORS BUT AS IMAGES FOR COMPUTER SIDE
        p2Card = new JLabel();
        p2Card.setIcon(new ImageIcon("Default RPS\\unknown  266x365.png"));
        p2Card.setSize(266, 365);
        p2Card.setLocation(1000, 280);
        this.add(p2Card);

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
        this.add(bg);

        // CONFIGURATION NG GUI
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Ropersors");
        this.setLayout(null);
        this.setVisible(true);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setSize(1550, 830);
        this.setResizable(false);
        // this.setUndecorated(true);
        ImageIcon icon = new ImageIcon("6793733.png");
        this.setIconImage(icon.getImage());
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
        pauseButton.setVisible(true);
        player1.setLives(10);
        player2.setLives(10);
        p1Card.setVisible(true);
        p2Card.setVisible(true);
        rockButton.setVisible(true);
        paperButton.setVisible(true);
        scissorButton.setVisible(true);
        rockButton.setIcon(new ImageIcon("Default RPS\\Default Rock (266x365).png"));
        paperButton.setIcon(new ImageIcon("Default RPS\\Default Paper (266x365).png"));
        scissorButton.setIcon(new ImageIcon("Default RPS\\Default Scissor (266x365).png"));
        gameOver.setVisible(false);
        roundNo = 1;
        round.setText("Round No: " + roundNo);
        p1lives.setText("Lives: " + Integer.toString(player1.getLives()));
        p2lives.setText("Lives: " + Integer.toString(player2.getLives()));
    }

    // ISET NATIN YUNG SA PAUSE METHOD
    public void pauseMethod() {
        pauseButton.setVisible(true);
        paused.setVisible(true);
        p1lives.setVisible(false);
        p2lives.setVisible(false);
        p1Card.setVisible(false);
        p2Card.setVisible(false);
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
        overlay.setVisible(false);
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
        p1Card.setVisible(true);
        p2Card.setVisible(true);

    }

    public void buttonPressed() {
        if (player1.getLives() <= 0) {
            gameOver.setText("Game over!");
            p1lives.setText("Lives: 0");

            gameIsOver();
        } else if (player2.getLives() <= 0) {
            p2lives.setText("Lives: 0");
            gameOver.setText("You won");
            gameIsOver();
        } else {
            proceed();
        }
    }

    // ETONG METHOD NA TO, ETO YUNG PARA MAG NEW GAME ULIT KUNG SAKALING ANG ISA AY
    // MATALO
    public void retry(ActionEvent e) {
        roundNo = 1;
        round.setText("Round No: " + roundNo);
        player1.setLives(10);
        player2.setLives(10);
        p1Card.setVisible(true);
        p2Card.setVisible(true);
        retryButton.setVisible(false);
        overlay.setVisible(false);
        gameOver.setVisible(false);
        p1Card.setIcon(new ImageIcon("Default RPS\\unknown  266x365.png"));
        p2Card.setIcon(new ImageIcon("Default RPS\\unknown  266x365.png"));
        pauseButton.setVisible(true);
        p1lives.setText("Lives: " + Integer.toString(player1.getLives()));
        p2lives.setText("Lives: " + Integer.toString(player2.getLives()));

    }

    public void gameIsOver() {
        overlay.setVisible(true);
        pauseButton.setVisible(false);
        gameOver.setVisible(true);
        retryButton.setVisible(true);

    }

    // LAGAY NALANG NATIN SA MGA METHODS YUNG MABABAWASAN YUNG LIFE
    public void p1Life1() {
        int livesp1 = player1.getLives() - 1;
        player1.setLives(livesp1);
        p1lives.setText("Lives: " + Integer.toString(player1.getLives()));

    }

    public void computerLife1() {
        int livesp2 = player2.getLives() - 1;
        player2.setLives(livesp2);
        p2lives.setText("Lives: " + Integer.toString(player2.getLives()));
    }

    public void p1Life2() {
        int livesp1 = player1.getLives() - 2;
        player1.setLives(livesp1);
        p1lives.setText("Lives: " + Integer.toString(player1.getLives()));
    }

    public void computerLife2() {
        int livesp2 = player2.getLives() - 2;
        player2.setLives(livesp2);
        p2lives.setText("Lives: " + Integer.toString(player2.getLives()));
    }

    public void proceed() {
        randomChoice = new Random();

        // RANDOMIZER FOR computer TO SELECT WHAT OBJECT
        int computerChoice = randomChoice.nextInt(3);
        player2.setChoice(objects[computerChoice]);
        computerCard = computerChoice;

        Random randomChoiceofElement = new Random();
        int randomInfuse = randomChoiceofElement.nextInt(3);
        // INFUSAL OF ELEMENT FOR computer

        if (player2.getChoice().equals("rock")) {
            if (randomInfuse == 0) {
                p2Card.setIcon(new ImageIcon("Fire Series\\Fire Rock (266x365).png"));
                player2.setElement("fire");
            } else if (randomInfuse == 1) {
                p2Card.setIcon(new ImageIcon("Water Series\\Water Rock(266x365).png"));
                player2.setElement("water");
            } else if (randomInfuse == 2) {
                p2Card.setIcon(new ImageIcon("Leaf Series\\Leaf Rock (266x365).png"));
                player2.setElement("leaf");
            }
        } else if (player2.getChoice().equals("paper")) {
            if (randomInfuse == 0) {
                p2Card.setIcon(new ImageIcon("Fire Series\\Fire Rock (266x365).png"));
                player2.setElement("fire");
            } else if (randomInfuse == 1) {
                p2Card.setIcon(new ImageIcon("Water Series\\Water Rock(266x365).png"));
                player2.setElement("water");
            } else if (randomInfuse == 2) {
                p2Card.setIcon(new ImageIcon("Leaf Series\\Leaf Rock (266x365).png"));
                player2.setElement("leaf");
            }
        } else if (player2.getChoice().equals("scissors")) {
            if (randomInfuse == 0) {
                p2Card.setIcon(new ImageIcon("Fire Series\\Fire Rock (266x365).png"));
                player2.setElement("fire");
            } else if (randomInfuse == 1) {
                p2Card.setIcon(new ImageIcon("Water Series\\Water Rock(266x365).png"));
                player2.setElement("water");
            } else if (randomInfuse == 2) {
                p2Card.setIcon(new ImageIcon("Leaf Series\\Leaf Rock (266x365).png"));
                player2.setElement("leaf");
            }
        }

        randomChoice = new Random();
        int randomElement = randomChoice.nextInt(3);
        player1.setElement((elements[randomElement]));

        // INFUSAL OF ELEMENTS FOR PLAYER
        if (player1.getChoice().equals("rock")) {
            if (player1.getElement().equals("fire")) {
                p1Card.setIcon(new ImageIcon("Fire Series\\Fire Rock (266x365).png"));
            } else if (player1.getElement().equals("water")) {
                p1Card.setIcon(new ImageIcon("Water Series\\Water Rock(266x365).png"));
            } else if (player1.getElement().equals("leaf")) {
                p1Card.setIcon(new ImageIcon("Leaf Series\\Leaf Rock (266x365).png"));
            }

        } else if (player1.getChoice().equals("paper")) {
            if (randomElement == 0) {
                p1Card.setIcon(new ImageIcon("Fire Series\\Fire Paper (266x365).png"));
            } else if (randomElement == 1) {
                p1Card.setIcon(new ImageIcon("Water Series\\Water Paper(266x365).png"));
            } else if (randomElement == 2) {
                p1Card.setIcon(new ImageIcon("Leaf Series\\Leaf Paper (266x365).png"));
            }
        } else if (player1.getChoice().equals("scissors")) {
            if (randomElement == 0) {
                p1Card.setIcon(new ImageIcon("Fire Series\\Fire Scissor(266x365).png"));
            } else if (randomElement == 1) {
                p1Card.setIcon(new ImageIcon("Water Series\\Water Scissors (266x365).png"));
            } else if (randomElement == 2) {
                p1Card.setIcon(new ImageIcon("Leaf Series\\Leaf Scissor (266x365).png"));
            }
        }

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                p1Card.setIcon(new ImageIcon("Default RPS\\unknown  266x365.png"));
                p2Card.setIcon(new ImageIcon("Default RPS\\unknown  266x365.png"));
            }
        };
        timer.schedule(task, 1000);

        if (player2.getChoice().equals("rock")) {
            if (player2.getElement().equals("fire")) {
                p2Card.setIcon(new ImageIcon("Fire Series\\Fire Rock (266x365).png"));
            } else if (player2.getElement().equals("water")) {
                p2Card.setIcon(new ImageIcon("Water Series\\Water Rock(266x365).png"));
            } else if (player2.getElement().equals("leaf")) {
                p2Card.setIcon(new ImageIcon("Leaf Series\\Leaf Rock (266x365).png"));
            }
        } else if (player2.getChoice().equals("paper")) {
            if (player2.getElement().equals("fire")) {
                p2Card.setIcon(new ImageIcon("Fire Series\\Fire Paper (266x365).png"));
            } else if (player2.getElement().equals("water")) {
                p2Card.setIcon(new ImageIcon("Water Series\\Water Paper(266x365).png"));
            } else if (player2.getElement().equals("leaf")) {
                p2Card.setIcon(new ImageIcon("Leaf Series\\Leaf Paper (266x365).png"));
            }
        } else if (player2.getChoice().equals("scissors")) {
            if (player2.getElement().equals("fire")) {
                p2Card.setIcon(new ImageIcon("Fire Series\\Fire Scissor(266x365).png"));
            } else if (player2.getElement().equals("water")) {
                p2Card.setIcon(new ImageIcon("Water Series\\Water Scissors (266x365).png"));
            } else if (player2.getElement().equals("leaf")) {
                p2Card.setIcon(new ImageIcon("Leaf Series\\Leaf Scissor (266x365).png"));
            }
        }

        // ON THE FOLLOWING BLOCK OF CODE, THE PROGRAM WILL DETERMINE WHO IS THE WINNER
        // PLAYER 1 ROCK AND FIRE

        if (player1.getChoice().equals("rock") && player1.getElement().equals("fire")) {
            if (player2.getChoice().equals("rock") && player2.getElement().equals("fire")) {
                // DO NOTHING KASI DRAW NAMAN
            } else if ((player2.getChoice().equals("rock") && player2.getElement().equals("water")) ||
                    (player2.getChoice().equals("paper") && player2.getElement().equals("fire")) ||
                    (player2.getChoice().equals("paper") && player2.getElement().equals("leaf"))) {
                p1Life1();
            } else if (player2.getChoice().equals("rock") && player2.getElement().equals("leaf") ||
                    (player2.getChoice().equals("scissors") && player2.getElement().equals("fire")) ||
                    (player2.getChoice().equals("scissors") && player2.getElement().equals("water"))) {
                computerLife1();
            } else if (player2.getChoice().equals("paper") && player2.getElement().equals("water")) {
                p1Life2();
            } else if (player2.getChoice().equals("scissors") && player2.getElement().equals("leaf")) {
                computerLife2();
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
                computerLife1();
            } else if (player2.getChoice().equals("paper") && player2.getElement().equals("leaf")) {
                p1Life2();
            } else if (player2.getChoice().equals("scissors") && player2.getElement().equals("fire")) {
                computerLife2();
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
                computerLife1();
            } else if (player2.getChoice().equals("paper") && player2.getElement().equals("fire")) {
                p1Life2();
            } else if (player2.getChoice().equals("scissors") && player2.getElement().equals("water")) {
                computerLife2();
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
                computerLife1();
            } else if (player2.getChoice().equals("scissors") && player2.getElement().equals("water")) {
                p1Life2();
            } else if (player2.getChoice().equals("rock") && player2.getElement().equals("leaf")) {
                computerLife2();
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
                computerLife1();
            } else if (player2.getChoice().equals("scissors") && player2.getElement().equals("leaf")) {
                p1Life2();
            } else if (player2.getChoice().equals("rock") && player2.getElement().equals("fire")) {
                computerLife2();
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
                computerLife1();
            } else if (player2.getChoice().equals("scissors") && player2.getElement().equals("fire")) {
                p1Life2();
            } else if (player2.getChoice().equals("rock") && player2.getElement().equals("water")) {
                computerLife2();
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
                computerLife1();
            } else if (player2.getChoice().equals("rock") && player2.getElement().equals("water")) {
                p1Life2();
            } else if (player2.getChoice().equals("paper") && player2.getElement().equals("leaf")) {
                computerLife2();
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
                computerLife1();
            } else if (player2.getChoice().equals("rock") && player2.getElement().equals("leaf")) {
                p1Life2();
            } else if (player2.getChoice().equals("paper") && player2.getElement().equals("fire")) {
                computerLife2();
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
                computerLife1();
            } else if (player2.getChoice().equals("rock") && player2.getElement().equals("fire")) {
                p1Life2();
            } else if (player2.getChoice().equals("paper") && player2.getElement().equals("water")) {
                computerLife2();
            }
        }

        if (player1.getLives() <= 0) {
            gameOver.setText("Game over!");
            p1lives.setText("Lives: 0");
            gameIsOver();
        } else if (player2.getLives() <= 0) {
            p2lives.setText("Lives: 0");
            gameOver.setText("You won");
            gameIsOver();
        } else if (player1.getLives() == 1 && player2.getLives() == 1) {
            round.setText("Final round");
        } else {
            roundNo += 1;
            round.setText("Round No: " + Integer.toString(roundNo));
        }

    }

    public static void main(String[] args) {

        gamev2 mainGame = new gamev2();

    }
}