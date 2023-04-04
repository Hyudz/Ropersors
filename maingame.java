import javax.swing.*;
import java.awt.event.*;
import java.util.Random;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.util.Timer;
import java.util.TimerTask;

public class maingame extends JFrame implements ActionListener {

    Runnable turnBack;
    JLabel p1lives, p2lives, status, name1, name2, rockLabel1, paperLabel1, scissorsLabel1, paused, overlay, background,
            gameOver, nameplate, nameplate2;
    JLabel rockFire, rockWater, rockNature, paperFire, paperWater, paperNature, scissorsFire, scissorsWater,
            scissorsNature, round;
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
    int randomIndex, computerCard, roundNo, p1Lives, p2Lives;
    Image boardImage, boardResized;
    String p1Choice, computerObj, p1Element, computerElement;

    maingame() {
        roundNo = 1;

        randomBg = new Random();
        randomIndex = randomBg.nextInt(4);

        String player1 = "Player 1";
        String computer = "Computer";
        p1Lives = 10;
        p2Lives = 10;

        ImageIcon vignette = new ImageIcon("vignette 1080.png");
        overlay = new JLabel();
        overlay.setLocation(0, 0);
        overlay.setIcon(vignette);
        overlay.setSize(1920, 1080);
        overlay.setVisible(false);
        this.add(overlay);

        // SET THE CONFIGURATION OF PLAYER 1 NAME
        name1 = new JLabel();
        name1.setText(player1);
        name1.setLocation(300, 80);
        name1.setSize(150, 30);
        name1.setFont(new Font("DePixel", Font.BOLD, 23));
        name1.setForeground(Color.WHITE);
        this.add(name1);

        // ETO NAMAN YUNG SA LIVES NILA
        p1lives = new JLabel();
        p1lives.setText("Lives: " + p1Lives);
        p1lives.setFont(new Font("DePixel", Font.BOLD, 18));
        p1lives.setForeground(Color.white);
        p1lives.setSize(150, 30);
        p1lives.setLocation(315, 120);
        this.add(p1lives);

        p2lives = new JLabel();
        p2lives.setText("Lives: " + p2Lives);
        p2lives.setForeground(Color.WHITE);
        p2lives.setFont(new Font("DePixel", Font.BOLD, 18));
        p2lives.setSize(150, 30);
        p2lives.setLocation(1100, 120);
        this.add(p2lives);

        // SET THE CONFIGURATION OF computer NAME
        name2 = new JLabel();
        name2.setText(computer);
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
        retryButton.addActionListener(e -> p1Lives = 10);
        retryButton.addActionListener(e -> p2Lives = 10);
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
        rockButton.setSize(266, 365);
        rockButton.setLocation(90, 280);
        rockButton.setIcon(new ImageIcon("Default RPS\\Default Rock (266x365).png"));
        rockButton.addActionListener(e -> buttonPressed());
        rockButton.addActionListener(e -> p1Choice = "rock");
        this.add(rockButton);

        paperButton = new JButton();
        paperButton.setSize(266, 365);
        paperButton.setLocation(350, 280);
        paperButton.setIcon(new ImageIcon("Default RPS\\Default Paper (266x365).png"));
        paperButton.addActionListener(e -> buttonPressed());
        paperButton.addActionListener(e -> p1Choice = "paper");
        this.add(paperButton);

        scissorButton = new JButton();
        scissorButton.setSize(266, 365);
        scissorButton.setLocation(610, 280);
        scissorButton.setIcon(new ImageIcon("Default RPS\\Default Scissor (266x365).png"));
        scissorButton.addActionListener(e -> buttonPressed());
        scissorButton.addActionListener(e -> p1Choice = "scissors");
        this.add(scissorButton);

        // LABELS OF ROCK PAPER SCISORS BUT AS IMAGES FOR computer SIDE
        rockLabel1 = new JLabel();
        rockLabel1.setIcon(new ImageIcon("Default RPS\\Default Rock (266x365).png"));
        rockLabel1.setSize(266, 365);
        rockLabel1.setLocation(1000, 280);
        rockLabel1.setVisible(false);
        this.add(rockLabel1);

        paperLabel1 = new JLabel();
        paperLabel1.setIcon(new ImageIcon("Default RPS\\Default Paper (266x365).png"));
        paperLabel1.setSize(266, 365);
        paperLabel1.setLocation(1000, 280);
        paperLabel1.setVisible(false);
        this.add(paperLabel1);

        scissorsLabel1 = new JLabel();
        scissorsLabel1.setIcon(new ImageIcon("Default RPS\\Default Scissor (266x365).png"));
        scissorsLabel1.setSize(266, 365);
        scissorsLabel1.setLocation(1000, 280);
        scissorsLabel1.setVisible(false);
        this.add(scissorsLabel1);

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
        p1Lives = 10;
        p2Lives = 10;
        rockLabel1.setVisible(false);
        paperLabel1.setVisible(false);
        scissorsLabel1.setVisible(false);
        rockButton.setVisible(true);
        paperButton.setVisible(true);
        scissorButton.setVisible(true);
        rockButton.setIcon(new ImageIcon("Default RPS\\Default Rock (266x365).png"));
        paperButton.setIcon(new ImageIcon("Default RPS\\Default Paper (266x365).png"));
        scissorButton.setIcon(new ImageIcon("Default RPS\\Default Scissor (266x365).png"));
        gameOver.setVisible(false);
        roundNo = 1;
        round.setText("Round No: " + roundNo);
        p1lives.setText("Lives: " + Integer.toString(p1Lives));
        p2lives.setText("Lives: " + Integer.toString(p2Lives));
    }

    // ISET NATIN YUNG SA PAUSE METHOD
    public void pauseMethod() {
        rockButton.setVisible(false);
        paperButton.setVisible(false);
        scissorButton.setVisible(false);
        pauseButton.setVisible(true);
        rockLabel1.setVisible(false);
        paperLabel1.setVisible(false);
        scissorsLabel1.setVisible(false);
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
        overlay.setVisible(false);
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
        if (computerCard == 0) {
            rockLabel1.setVisible(true);
            paperLabel1.setVisible(false);
            scissorsLabel1.setVisible(false);
        } else if (computerCard == 1) {
            rockLabel1.setVisible(false);
            paperLabel1.setVisible(true);
            scissorsLabel1.setVisible(false);
        } else if (computerCard == 2) {
            rockLabel1.setVisible(false);
            paperLabel1.setVisible(false);
            scissorsLabel1.setVisible(true);
        } else {
            rockLabel1.setVisible(false);
            paperLabel1.setVisible(false);
            scissorsLabel1.setVisible(false);
        }
    }

    public void buttonPressed() {
        if (p1Lives <= 0) {
            gameOver.setText("Game over!");
            p1lives.setText("Lives: 0");
            gameIsOver();
        } else if (p2Lives <= 0) {
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
        p1Lives = 10;
        p2Lives = 10;
        rockButton.setVisible(true);
        paperButton.setVisible(true);
        scissorButton.setVisible(true);
        retryButton.setVisible(false);
        overlay.setVisible(false);
        gameOver.setVisible(false);
        rockButton.setIcon(new ImageIcon("Default RPS\\Default Rock (266x365).png"));
        paperButton.setIcon(new ImageIcon("Default RPS\\Default Paper (266x365).png"));
        scissorButton.setIcon(new ImageIcon("Default RPS\\Default Scissor (266x365).png"));
        rockButton.setEnabled(true);
        paperButton.setEnabled(true);
        scissorButton.setEnabled(true);
        pauseButton.setVisible(true);
        p1lives.setText("Lives: " + Integer.toString(p1Lives));
        p2lives.setText("Lives: " + Integer.toString(p2Lives));

    }

    public void gameIsOver() {
        overlay.setVisible(true);
        pauseButton.setVisible(false);
        gameOver.setVisible(true);
        retryButton.setVisible(true);
        rockButton.setVisible(false);
        paperButton.setVisible(false);
        scissorButton.setVisible(false);
        rockLabel1.setVisible(false);
        paperLabel1.setVisible(false);
        scissorsLabel1.setVisible(false);
    }

    // LAGAY NALANG NATIN SA MGA METHODS YUNG MABABAWASAN YUNG LIFE
    public void p1Life1() {
        int livesp1 = p1Lives - 1;
        p1Lives = livesp1;
        p1lives.setText("Lives: " + Integer.toString(p1Lives));

    }

    public void computerLife1() {
        int livesp2 = p2Lives - 1;
        p2Lives = livesp2;
        p2lives.setText("Lives: " + Integer.toString(p2Lives));
    }

    public void p1Life2() {
        int livesp1 = p1Lives - 2;
        p1Lives = livesp1;
        p1lives.setText("Lives: " + Integer.toString(p1Lives));
    }

    public void computerLife2() {
        int livesp2 = p2Lives - 2;
        p2Lives = livesp2;
        p2lives.setText("Lives: " + Integer.toString(p2Lives));
    }

    public void proceed() {
        randomChoice = new Random();
        int computerChoice = randomChoice.nextInt(3);
        computerObj = (objects[computerChoice]);
        computerCard = computerChoice;

        Random randomChoiceofElement = new Random();
        int randomInfuse = randomChoiceofElement.nextInt(3);
        // INFUSAL OF ELEMENT FOR computer

        if (computerObj.equals("rock")) {
            if (randomInfuse == 0) {
                rockLabel1.setIcon(new ImageIcon("Fire Series\\Fire Rock (266x365).png"));
                computerElement = "fire";
            } else if (randomInfuse == 1) {
                rockLabel1.setIcon(new ImageIcon("Water Series\\Water Rock(266x365).png"));
                computerElement = "water";
            } else if (randomInfuse == 2) {
                rockLabel1.setIcon(new ImageIcon("Leaf Series\\Leaf Rock (266x365).png"));
                computerElement = "leaf";
            }
        } else if (computerObj.equals("paper")) {
            if (randomInfuse == 0) {
                rockLabel1.setIcon(new ImageIcon("Fire Series\\Fire Rock (266x365).png"));
                computerElement = "fire";
            } else if (randomInfuse == 1) {
                rockLabel1.setIcon(new ImageIcon("Water Series\\Water Rock(266x365).png"));
                computerElement = "water";
            } else if (randomInfuse == 2) {
                rockLabel1.setIcon(new ImageIcon("Leaf Series\\Leaf Rock (266x365).png"));
                computerElement = "leaf";
            }
        } else if (computerObj.equals("scissors")) {
            if (randomInfuse == 0) {
                rockLabel1.setIcon(new ImageIcon("Fire Series\\Fire Rock (266x365).png"));
                computerElement = "fire";
            } else if (randomInfuse == 1) {
                rockLabel1.setIcon(new ImageIcon("Water Series\\Water Rock(266x365).png"));
                computerElement = "water";
            } else if (randomInfuse == 2) {
                rockLabel1.setIcon(new ImageIcon("Leaf Series\\Leaf Rock (266x365).png"));
                computerElement = "leaf";
            }
        }

        randomChoice = new Random();
        int randomElement = randomChoice.nextInt(3);
        p1Element = (elements[randomElement]);

        // INFUSAL OF ELEMENTS FOR PLAYER
        if (p1Choice.equals("rock")) {
            if (p1Element.equals("fire")) {
                rockButton.setIcon(new ImageIcon("Fire Series\\Fire Rock (266x365).png"));
            } else if (p1Element.equals("water")) {
                rockButton.setIcon(new ImageIcon("Water Series\\Water Rock(266x365).png"));
            } else if (p1Element.equals("leaf")) {
                rockButton.setIcon(new ImageIcon("Leaf Series\\Leaf Rock (266x365).png"));
            }

        } else if (p1Choice.equals("paper")) {
            if (randomElement == 0) {
                paperButton.setIcon(new ImageIcon("Fire Series\\Fire Paper (266x365).png"));
            } else if (randomElement == 1) {
                paperButton.setIcon(new ImageIcon("Water Series\\Water Paper(266x365).png"));
            } else if (randomElement == 2) {
                paperButton.setIcon(new ImageIcon("Leaf Series\\Leaf Paper (266x365).png"));
            }
        } else if (p1Choice.equals("scissors")) {
            if (randomElement == 0) {
                scissorButton.setIcon(new ImageIcon("Fire Series\\Fire Scissor(266x365).png"));
            } else if (randomElement == 1) {
                scissorButton.setIcon(new ImageIcon("Water Series\\Water Scissors (266x365).png"));
            } else if (randomElement == 2) {
                scissorButton.setIcon(new ImageIcon("Leaf Series\\Leaf Scissor (266x365).png"));
            }
        }

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                rockButton.setIcon(new ImageIcon("Default RPS\\Default Rock (266x365).png"));
                paperButton.setIcon(new ImageIcon("Default RPS\\Default Paper (266x365).png"));
                scissorButton.setIcon(new ImageIcon("Default RPS\\Default Scissor (266x365).png"));
            }
        };
        timer.schedule(task, 1000);

        if (computerObj.equals("rock")) {
            rockLabel1.setVisible(true);
            if (computerElement.equals("fire")) {
                rockLabel1.setIcon(new ImageIcon("Fire Series\\Fire Rock (266x365).png"));
            } else if (computerElement.equals("water")) {
                rockLabel1.setIcon(new ImageIcon("Water Series\\Water Rock(266x365).png"));
            } else if (computerElement.equals("leaf")) {
                rockLabel1.setIcon(new ImageIcon("Leaf Series\\Leaf Rock (266x365).png"));
            }
            paperLabel1.setVisible(false);
            scissorsLabel1.setVisible(false);
        } else if (computerObj.equals("paper")) {
            rockLabel1.setVisible(false);
            paperLabel1.setVisible(true);
            if (computerElement.equals("fire")) {
                paperLabel1.setIcon(new ImageIcon("Fire Series\\Fire Paper (266x365).png"));
            } else if (computerElement.equals("water")) {
                paperLabel1.setIcon(new ImageIcon("Water Series\\Water Paper(266x365).png"));
            } else if (computerElement.equals("leaf")) {
                paperLabel1.setIcon(new ImageIcon("Leaf Series\\Leaf Paper (266x365).png"));
            }
            scissorsLabel1.setVisible(false);
        } else if (computerObj.equals("scissors")) {
            rockLabel1.setVisible(false);
            paperLabel1.setVisible(false);
            scissorsLabel1.setVisible(true);
            if (computerElement.equals("fire")) {
                scissorsLabel1.setIcon(new ImageIcon("Fire Series\\Fire Scissor(266x365).png"));
            } else if (computerElement.equals("water")) {
                scissorsLabel1.setIcon(new ImageIcon("Water Series\\Water Scissors (266x365).png"));
            } else if (computerElement.equals("leaf")) {
                scissorsLabel1.setIcon(new ImageIcon("Leaf Series\\Leaf Scissor (266x365).png"));
            }
        }

        // ON THE FOLLOWING BLOCK OF CODE, THE PROGRAM WILL DETERMINE WHO IS THE WINNER
        // PLAYER 1 ROCK AND FIRE

        if (p1Choice.equals("rock") && p1Element.equals("fire")) {
            if (computerObj.equals("rock") && computerElement.equals("fire")) {
                // DO NOTHING KASI DRAW NAMAN
            } else if ((computerObj.equals("rock") && computerElement.equals("water")) ||
                    (computerObj.equals("paper") && computerElement.equals("fire")) ||
                    (computerObj.equals("paper") && computerElement.equals("leaf"))) {
                p1Life1();
            } else if (computerObj.equals("rock") && computerElement.equals("leaf") ||
                    (computerObj.equals("scissors") && computerElement.equals("fire")) ||
                    (computerObj.equals("scissors") && computerElement.equals("water"))) {
                computerLife1();
            } else if (computerObj.equals("paper") && computerElement.equals("water")) {
                p1Life2();
            } else if (computerObj.equals("scissors") && computerElement.equals("leaf")) {
                computerLife2();
            }
        }
        // PLAYER 1 ROCK AND WATER
        else if (p1Choice.equals("rock") && p1Element.equals("water")) {
            if ((computerObj.equals("rock") && computerElement.equals("leaf")) ||
                    (computerObj.equals("paper") && computerElement.equals("fire")) ||
                    (computerObj.equals("paper") && computerElement.equals("water"))) {
                p1Life1();
            } else if ((computerObj.equals("rock") && computerElement.equals("fire")) ||
                    (computerObj.equals("scissors") && computerElement.equals("water")) ||
                    (computerObj.equals("scissors") && computerElement.equals("leaf"))) {
                computerLife1();
            } else if (computerObj.equals("paper") && computerElement.equals("leaf")) {
                p1Life2();
            } else if (computerObj.equals("scissors") && computerElement.equals("fire")) {
                computerLife2();
            }
        }
        // PLAYER 1 ROCK AND LEAF
        else if (p1Choice.equals("rock") && p1Element.equals("leaf")) {
            if ((computerObj.equals("rock") && computerElement.equals("fire")) ||
                    (computerObj.equals("paper") && computerElement.equals("leaf")) ||
                    (computerObj.equals("paper") && computerElement.equals("water"))) {
                p1Life1();
            } else if ((computerObj.equals("rock") && computerElement.equals("water")) ||
                    (computerObj.equals("scissors") && computerElement.equals("fire")) ||
                    (computerObj.equals("scissors") && computerElement.equals("leaf"))) {
                computerLife1();
            } else if (computerObj.equals("paper") && computerElement.equals("fire")) {
                p1Life2();
            } else if (computerObj.equals("scissors") && computerElement.equals("water")) {
                computerLife2();
            }
        }
        // PLAYER 1 PAPER AND FIRE
        else if (p1Choice.equals("paper") && p1Element.equals("fire")) {
            if ((computerObj.equals("paper") && computerElement.equals("water")) ||
                    (computerObj.equals("scissors") && computerElement.equals("fire")) ||
                    (computerObj.equals("scissors") && computerElement.equals("leaf"))) {
                p1Life1();
            } else if ((computerObj.equals("rock") && computerElement.equals("fire")) ||
                    (computerObj.equals("rock") && computerElement.equals("water")) ||
                    (computerObj.equals("paper") && computerElement.equals("leaf"))) {
                computerLife1();
            } else if (computerObj.equals("scissors") && computerElement.equals("water")) {
                p1Life2();
            } else if (computerObj.equals("rock") && computerElement.equals("leaf")) {
                computerLife2();
            }
        }
        // PLAYER 1 PAPER AND WATER
        else if (p1Choice.equals("paper") && p1Element.equals("water")) {
            if ((computerObj.equals("paper") && computerElement.equals("leaf")) ||
                    (computerObj.equals("scissors") && computerElement.equals("fire")) ||
                    (computerObj.equals("scissors") && computerElement.equals("water"))) {
                p1Life1();
            } else if ((computerObj.equals("rock") && computerElement.equals("water")) ||
                    (computerObj.equals("rock") && computerElement.equals("leaf")) ||
                    (computerObj.equals("paper") && computerElement.equals("fire"))) {
                computerLife1();
            } else if (computerObj.equals("scissors") && computerElement.equals("leaf")) {
                p1Life2();
            } else if (computerObj.equals("rock") && computerElement.equals("fire")) {
                computerLife2();
            }
        }
        // PLAYER 1 PAPER AND LEAF
        else if (p1Choice.equals("paper") && p1Element.equals("leaf")) {
            if ((computerObj.equals("paper") && computerElement.equals("fire")) ||
                    (computerObj.equals("scissors") && computerElement.equals("water")) ||
                    (computerObj.equals("scissors") && computerElement.equals("leaf"))) {
                p1Life1();
            } else if ((computerObj.equals("rock") && computerElement.equals("fire")) ||
                    (computerObj.equals("rock") && computerElement.equals("leaf")) ||
                    (computerObj.equals("paper") && computerElement.equals("water"))) {
                computerLife1();
            } else if (computerObj.equals("scissors") && computerElement.equals("fire")) {
                p1Life2();
            } else if (computerObj.equals("rock") && computerElement.equals("water")) {
                computerLife2();
            }
        }
        // PLAYER 1 SCISSORS AND FIRE
        else if (p1Choice.equals("scissors") && p1Element.equals("fire")) {
            if ((computerObj.equals("rock") && computerElement.equals("fire")) ||
                    (computerObj.equals("rock") && computerElement.equals("leaf")) ||
                    (computerObj.equals("scissors") && computerElement.equals("water"))) {
                p1Life1();
            } else if ((computerObj.equals("paper") && computerElement.equals("fire")) ||
                    (computerObj.equals("paper") && computerElement.equals("water")) ||
                    (computerObj.equals("scissors") && computerElement.equals("leaf"))) {
                computerLife1();
            } else if (computerObj.equals("rock") && computerElement.equals("water")) {
                p1Life2();
            } else if (computerObj.equals("paper") && computerElement.equals("leaf")) {
                computerLife2();
            }
        }
        // PLAYER 1 SCISSORS AND WATER
        else if (p1Choice.equals("scissors") && p1Element.equals("water")) {
            if ((computerObj.equals("rock") && computerElement.equals("fire")) ||
                    (computerObj.equals("rock") && computerElement.equals("water")) ||
                    (computerObj.equals("scissors") && computerElement.equals("leaf"))) {
                p1Life1();
            } else if ((computerObj.equals("paper") && computerElement.equals("water")) ||
                    (computerObj.equals("paper") && computerElement.equals("leaf")) ||
                    (computerObj.equals("scissors") && computerElement.equals("fire"))) {
                computerLife1();
            } else if (computerObj.equals("rock") && computerElement.equals("leaf")) {
                p1Life2();
            } else if (computerObj.equals("paper") && computerElement.equals("fire")) {
                computerLife2();
            }
        }
        // PLAYER 1 SCISSORS AND LEAF
        else if (p1Choice.equals("scissors") && p1Element.equals("leaf")) {
            if ((computerObj.equals("rock") && computerElement.equals("water")) ||
                    (computerObj.equals("rock") && computerElement.equals("leaf")) ||
                    (computerObj.equals("scissors") && computerElement.equals("fire"))) {
                p1Life1();
            } else if ((computerObj.equals("paper") && computerElement.equals("fire")) ||
                    (computerObj.equals("paper") && computerElement.equals("leaf")) ||
                    (computerObj.equals("scissors") && computerElement.equals("water"))) {
                computerLife1();
            } else if (computerObj.equals("rock") && computerElement.equals("fire")) {
                p1Life2();
            } else if (computerObj.equals("paper") && computerElement.equals("water")) {
                computerLife2();
            }
        }

        if (p1Lives <= 0) {
            gameOver.setText("Game over!");
            p1lives.setText("Lives: 0");

            gameIsOver();
        } else if (p2Lives <= 0) {
            p2lives.setText("Lives: 0");
            gameOver.setText("You won");
            gameIsOver();
        } else {
            roundNo += 1;
            round.setText("Round No: " + Integer.toString(roundNo));
        }

    }

    public static void main(String[] args) {

        maingame mainGame = new maingame();

    }
}