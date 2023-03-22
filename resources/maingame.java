package resources;

import javax.swing.*;
import java.awt.event.*;
import java.util.Random;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

public class maingame extends JFrame implements ActionListener {

    JLabel p1lives, p2lives, name1, name2, rockLabel1, paperLabel1, scissorsLabel1, paused, overlay, background,
            gameOver, nameplate, nameplate2;
    profile player = new profile();
    profile computer = new profile();
    JPanel bg;
    String winOrLose;
    ImageIcon board, namplates;
    Random randomChoice;
    JButton rockButton, paperButton, scissorButton, retryButton, pauseButton, playButton, newGame;
    String[] choices = { "rock", "paper", "scissors" };
    String[] boards = { "boards\\magam.png", "boards\\sky.png", "boards\\snad.png", "boards\\wood.png" };
    String[] nameplates = { "nameplates\\magam.png", "nameplates\\sky.png", "nameplates\\snad.png",
            "nameplates\\wood.png" };
    Random randomBg;
    int randomIndex, computerCard;
    Image boardImage, boardResized;

    maingame() {

        randomBg = new Random();
        randomIndex = randomBg.nextInt(4);

        player.setName("Player 1");
        computer.setName("Computer");

        ImageIcon vignette = new ImageIcon("vignette 1080.png");
        overlay = new JLabel();
        overlay.setLocation(0, 0);
        overlay.setIcon(vignette);
        overlay.setSize(1920, 1080);
        overlay.setVisible(false);
        this.add(overlay);

        // SET THE CONFIGURATION OF PLAYER 1 NAME
        name1 = new JLabel();
        name1.setText(player.getName());
        name1.setLocation(300, 80);
        name1.setSize(150, 30);
        name1.setFont(new Font("DePixel", Font.BOLD, 23));
        name1.setForeground(Color.WHITE);
        this.add(name1);

        // ETO NAMAN YUNG SA LIVES NILA
        p1lives = new JLabel();
        p1lives.setText("Lives: " + player.getLives());
        p1lives.setFont(new Font("DePixel", Font.BOLD, 18));
        p1lives.setForeground(Color.white);
        p1lives.setSize(150, 30);
        p1lives.setLocation(325, 120);
        this.add(p1lives);

        p2lives = new JLabel();
        p2lives.setText("Lives: " + computer.getLives());
        p2lives.setForeground(Color.WHITE);
        p2lives.setFont(new Font("DePixel", Font.BOLD, 18));
        p2lives.setSize(150, 30);
        p2lives.setLocation(1100, 120);
        this.add(p2lives);

        // SET THE CONFIGURATION OF COMPUTER NAME
        name2 = new JLabel();
        name2.setText(computer.getName());
        name2.setLocation(1075, 80);
        name2.setForeground(Color.WHITE);
        name2.setSize(175, 30);
        name2.setFont(new Font("DePixel", Font.BOLD, 23));
        this.add(name2);

        // SET THE POSITIONS OF NAMEPLATE
        nameplate = new JLabel();
        nameplate.setSize(390, 120);
        nameplate.setLocation(180, 50);
        nameplate.setIcon(new ImageIcon(nameplates[3]));
        this.add(nameplate);

        // SET THE POSITIONS OF NAMEPLATE
        nameplate2 = new JLabel();
        nameplate2.setSize(390, 120);
        nameplate2.setLocation(965, 50);
        nameplate2.setIcon(new ImageIcon(nameplates[3]));
        this.add(nameplate2);

        // RETRY BUTTON
        ImageIcon newGameIcon = new ImageIcon(
                "Buttons\\try (w_color).png");
        retryButton = new JButton();
        retryButton.setIcon(newGameIcon);
        retryButton.setEnabled(false);
        retryButton.setSize(32, 32);
        retryButton.setLocation(750, 500);
        retryButton.setVisible(false);
        retryButton.addActionListener(e -> retry(e));
        retryButton.addActionListener(this);
        retryButton.addActionListener(e -> computer.setLives(8));
        retryButton.addActionListener(e -> player.setLives(8));
        this.add(retryButton);

        // PAUSE BUTTON
        ImageIcon pauseIcon = new ImageIcon(
                "Buttons\\pause (w_color).png");
        pauseButton = new JButton();
        pauseButton.setSize(32, 32);
        pauseButton.setIcon(pauseIcon);
        pauseButton.setFocusable(false);
        pauseButton.setLocation(750, 10);
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
        ImageIcon playIcon = new ImageIcon(
                "Buttons\\play_continue (w_color).png");
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
        gameOver.setVisible(false);
        if (player.getLives() == 0) {
            gameOver.setText("You Lost!");
        } else {
            gameOver.setText("You won!");
        }
        this.add(gameOver);

        // COMFIGURATIONS NG ROCK PAPER AND SCISSORS
        ImageIcon rockIcon = new ImageIcon("Default RPS\\Default Rock (266x365).png");
        rockButton = new JButton();
        rockButton.setSize(266, 365);
        rockButton.setLocation(80, 280);
        rockButton.setIcon(rockIcon);
        rockButton.addActionListener(e -> buttonPressed());
        rockButton.addActionListener(e -> player.setChoice(1));
        this.add(rockButton);

        ImageIcon paperIcon = new ImageIcon("Default RPS\\Default Paper (266x365).png");
        paperButton = new JButton();
        paperButton.setSize(266, 365);
        paperButton.setLocation(350, 280);
        paperButton.setIcon(paperIcon);
        paperButton.addActionListener(e -> buttonPressed());
        paperButton.addActionListener(e -> player.setChoice(2));
        this.add(paperButton);

        ImageIcon scissorIcon = new ImageIcon("Default RPS\\Default Scissor (266x365).png");
        scissorButton = new JButton();
        scissorButton.setSize(266, 365);
        scissorButton.setLocation(620, 280);
        scissorButton.setIcon(scissorIcon);
        scissorButton.addActionListener(e -> buttonPressed());
        scissorButton.addActionListener(e -> player.setChoice(3));
        this.add(scissorButton);

        // LABELS OF ROCK PAPER SCISORS BUT AS IMAGES

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

        // Configurations ng Elements HUHUHUHUHUHUHUHUHUHUHUHUHUHUHUHUHUH

        // board = new ImageIcon(boards[randomIndex]);
        board = new ImageIcon(boards[3]);
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
        nameplate.setIcon(new ImageIcon(nameplates[3]));
        nameplate2.setIcon(new ImageIcon(nameplates[3]));
        board = new ImageIcon(boards[3]);
        boardImage = board.getImage();
        boardResized = boardImage.getScaledInstance(1535, 792, java.awt.Image.SCALE_SMOOTH);
        board = new ImageIcon(boardResized);

        background.setIcon(board);

    }

    // TRY AGAIN OR NEW GAME
    public void tryAgain() {
        playMethod();
        player.setName("Player 1");
        computer.setName("Computer");
        player.setLives(8);
        computer.setLives(8);
        rockLabel1.setVisible(false);
        paperLabel1.setVisible(false);
        scissorsLabel1.setVisible(false);
        p1lives.setText("Lives: " + Integer.toString(player.getLives()));
        p2lives.setText("Lives: " + Integer.toString(computer.getLives()));
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
        if (computerCard == 1) {
            rockLabel1.setVisible(true);
            paperLabel1.setVisible(false);
            scissorsLabel1.setVisible(false);
        } else if (computerCard == 2) {
            rockLabel1.setVisible(false);
            paperLabel1.setVisible(true);
            scissorsLabel1.setVisible(false);
        } else if (computerCard == 3) {
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
        if (player.getLives() == 1) {
            int livesp1 = player.getLives() - 1;
            player.setLives(livesp1);
            p1lives.setText("Lives: " + Integer.toString(player.getLives()));
            gameIsOver();
        } else if (computer.getLives() == 1) {
            int livesp2 = computer.getLives() - 1;
            computer.setLives(livesp2);
            p2lives.setText("Lives: " + Integer.toString(computer.getLives()));
            gameIsOver();
        } else {
            proceed();
        }
    }

    public void retry(ActionEvent e) {
        computer.setLives(8);
        player.setLives(8);
        rockButton.setEnabled(true);
        paperButton.setEnabled(true);
        scissorButton.setEnabled(true);
        retryButton.setEnabled(false);
        overlay.setVisible(false);
        retryButton.setVisible(false);
        computer.setLives(8);
        player.setLives(8);
        p1lives.setText("Lives: " + Integer.toString(player.getLives()));
        p2lives.setText("Lives: " + Integer.toString(computer.getLives()));

    }

    public void gameIsOver() {
        overlay.setVisible(true);
        rockButton.setEnabled(false);
        paperButton.setEnabled(false);
        scissorButton.setEnabled(false);
        retryButton.setEnabled(true);
        retryButton.setVisible(true);
    }

    public void proceed() {
        randomChoice = new Random();
        int computerChoice = randomChoice.nextInt(3) + 1;
        computerCard = computerChoice;

        try {
            Thread.sleep(500);
            if (player.getChoice() == computerChoice) {
            } else if ((player.getChoice() == 1 && computerChoice == 3) ||
                    (player.getChoice() == 2 && computerChoice == 1) ||
                    (player.getChoice() == 3 && computerChoice == 2)) {
                int livesp2 = computer.getLives() - 1;
                computer.setLives(livesp2);
                p2lives.setText("Lives: " + Integer.toString(computer.getLives()));
            } else {
                int livesp1 = player.getLives() - 1;
                player.setLives(livesp1);
                p1lives.setText("Lives: " + Integer.toString(player.getLives()));
            }
        } catch (InterruptedException e) {
            System.out.println("Something went wrong");
        }

        if (computerChoice == 1) {
            rockLabel1.setVisible(true);
            paperLabel1.setVisible(false);
            scissorsLabel1.setVisible(false);
        } else if (computerChoice == 2) {
            rockLabel1.setVisible(false);
            paperLabel1.setVisible(true);
            scissorsLabel1.setVisible(false);
        } else if (computerChoice == 3) {
            rockLabel1.setVisible(false);
            paperLabel1.setVisible(false);
            scissorsLabel1.setVisible(true);
        }

    }

    public static void main(String[] args) {

        maingame mainnGame = new maingame();

    }
}