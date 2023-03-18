package resources;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

public class maingame extends JFrame implements ActionListener {

    JLabel p1lives, p2lives, status, name1, name2, rockLabel1, paperLabel1, scissorsLabel1, paused, overlay;
    profile player = new profile();
    profile computer = new profile();
    int defaultLife = 8;
    JPanel pauseWindow;
    String winOrLose;
    ImageIcon board;
    Random randomChoice;
    BufferedImage cardFire1;
    JButton rockButton, paperButton, scissorButton, retryButton, pauseButton, playButton, newGame;
    String[] choices = { "rock", "paper", "scissors" };
    String[] fireElements = { "Rock Fire", "Paper Fire", "Scissors Fire" };
    String[] waterElements = { "Rock Water", "Paper Water", "Scissors Water" };
    String[] natureElements = { "Rock Water", "Paper Water", "Scissors Water" };
    String[] boards = { "boards\\board.png", "boards\\board2.png", "boards\\board2.2.png", "boards\\board2up.png",
            "boards\\board3.png", "boards\\board5.png", "boards\\board3v2.png", "boards\\board4.png",
            "boards\\board4v2.png" };
    Random randomBg;
    int randomIndex;
    Image boardImage, boardResized;

    maingame() {

        randomBg = new Random();
        randomIndex = randomBg.nextInt(8);

        player.setName("Player 1");
        computer.setName("Computer");

        board = new ImageIcon(boards[randomIndex]);
        boardImage = board.getImage();
        boardResized = boardImage.getScaledInstance(1535, 792, java.awt.Image.SCALE_SMOOTH);
        board = new ImageIcon(boardResized);
        this.setContentPane(new JLabel(board));

        // SET THE CONFIGURATION OF PLAYER 1 NAME
        name1 = new JLabel();
        name1.setText(player.getName());
        name1.setLocation(300, 100);
        name1.setSize(150, 30);
        name1.setFont(new Font("DePixel", Font.BOLD, 23));
        name1.setForeground(Color.WHITE);
        this.add(name1);

        // SET THE CONFIGURATION OF COMPUTER NAME
        name2 = new JLabel();
        name2.setText(computer.getName());
        name2.setLocation(1075, 100);
        name2.setForeground(Color.WHITE);
        name2.setSize(175, 30);
        name2.setFont(new Font("DePixel", Font.BOLD, 23));
        this.add(name2);

        // ETO NAMAN YUNG SA LIVES NILA
        p1lives = new JLabel();
        p1lives.setText("Lives: " + player.getLives());
        p1lives.setFont(new Font("DePixel", Font.BOLD, 23));
        p1lives.setForeground(Color.white);
        p1lives.setSize(150, 30);
        p1lives.setLocation(40, 28);
        this.add(p1lives);

        // read file
        ImageIcon cardFire = new ImageIcon(
                "C:\\Users\\Fraion\\Documents\\ITE\\Java files\\Final project\\Fire Series\\Fire Paper (362x497).png");
        JLabel fireCard = new JLabel(cardFire);
        // fireCard.setSize(100, 100);
        this.add(fireCard);

        p2lives = new JLabel();
        p2lives.setText("Lives: " + computer.getLives());
        p2lives.setForeground(Color.WHITE);
        p2lives.setFont(new Font("DePixel", Font.BOLD, 23));
        p2lives.setSize(150, 30);
        p2lives.setLocation(1390, 28);
        this.add(p2lives);

        // STATUS KUNG ANO YUNG PINILI NI COMPUTER AND KUNG SINO NANALO
        status = new JLabel("Your Turn");
        status.setForeground(Color.WHITE);
        status.setFont(new Font("DePixel", Font.BOLD, 20));
        status.setSize(300, 50);
        this.add(status);

        // GUMAWA NG JPANEL PARA PAGLALAGYAN NG STATUS AND PARA MA-ALIGN SA CENTER
        JPanel placeholder = new JPanel();
        placeholder.setSize(1550, 30);
        placeholder.setLocation(0, 750);
        placeholder.setOpaque(false);
        placeholder.add(status);
        this.add(placeholder);

        // RETRY BUTTON
        retryButton = new JButton();
        retryButton.setText("Retry");
        retryButton.setEnabled(false);
        retryButton.setSize(75, 25);
        retryButton.setLocation(750, 500);
        retryButton.setVisible(false);
        retryButton.addActionListener(e -> retry(e));
        retryButton.addActionListener(e -> computer.setLives(8));
        retryButton.addActionListener(e -> player.setLives(8));
        this.add(retryButton);

        // PAUSE BUTTON
        ImageIcon pauseIcon = new ImageIcon(
                "Buttons\\pause.png");
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
        ImageIcon newGameIcon = new ImageIcon(
                "Buttons\\try (w_color).png");
        newGame = new JButton(newGameIcon);
        newGame.setSize(32, 32);
        newGame.setLocation(700, 500);
        newGame.setVisible(false);
        newGame.setBorderPainted(false);
        newGame.setFocusPainted(false);
        newGame.setContentAreaFilled(false);
        newGame.setFocusable(false);
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

        ImageIcon vignette = new ImageIcon("vignette 1080.png");
        Image vignetteImage = vignette.getImage();
        Image vignetteResized = vignetteImage.getScaledInstance(1535, 1065, java.awt.Image.SCALE_SMOOTH);

        overlay = new JLabel();
        overlay.setLocation(0, 0);
        overlay.setIcon(new ImageIcon(vignetteResized));
        // overlay.setBackground(Color.BLUE);
        overlay.setSize(1920, 1080);
        overlay.setVisible(false);
        this.add(overlay);

        // COMFIGURATIONS NG ROCK PAPER AND SCISSORS
        ImageIcon rockIcon = new ImageIcon("rock\\pixil-frame-0 (2).png");
        Image rockImage = rockIcon.getImage();
        Image rockResized = rockImage.getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH);
        rockIcon = new ImageIcon(rockResized);
        rockButton = new JButton();
        rockButton.setSize(80, 80);
        rockButton.setLocation(350, 300);
        rockButton.setIcon(rockIcon);
        rockButton.setBorderPainted(false);
        rockButton.setFocusPainted(false);
        rockButton.setContentAreaFilled(false);
        rockButton.addActionListener(this);
        rockButton.addActionListener(e -> player.setChoice(1));
        this.add(rockButton);

        ImageIcon paperIcon = new ImageIcon("paper\\Paper Default (2).png");
        Image paperImage = paperIcon.getImage();
        Image paperResized = paperImage.getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH);
        paperIcon = new ImageIcon(paperResized);
        paperButton = new JButton();
        paperButton.setSize(80, 80);
        paperButton.setLocation(350, 400);
        paperButton.setIcon(paperIcon);
        paperButton.setBorderPainted(false);
        paperButton.setFocusPainted(false);
        paperButton.setContentAreaFilled(false);
        paperButton.addActionListener(this);
        paperButton.addActionListener(e -> player.setChoice(2));
        this.add(paperButton);

        ImageIcon scissorIcon = new ImageIcon("scissors\\Scissors Default.png");
        Image scissorImage = scissorIcon.getImage();
        Image scissorResized = scissorImage.getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH);
        scissorIcon = new ImageIcon(scissorResized);
        scissorButton = new JButton();
        scissorButton.setSize(80, 80);
        scissorButton.setLocation(350, 500);
        scissorButton.setIcon(scissorIcon);
        scissorButton.setBorderPainted(false);
        scissorButton.setFocusPainted(false);
        scissorButton.setContentAreaFilled(false);
        scissorButton.addActionListener(this);
        scissorButton.addActionListener(e -> player.setChoice(3));
        this.add(scissorButton);

        // LABELS OF ROCK PAPER SCISORS BUT AS IMAGES
        rockLabel1 = new JLabel();
        rockLabel1.setIcon(new ImageIcon(rockResized));
        rockLabel1.setSize(75, 75);
        rockLabel1.setLocation(1075, 300);
        this.add(rockLabel1);

        paperLabel1 = new JLabel();
        paperLabel1.setIcon(new ImageIcon(paperResized));
        paperLabel1.setSize(75, 75);
        paperLabel1.setLocation(1075, 400);
        this.add(paperLabel1);

        scissorsLabel1 = new JLabel();
        scissorsLabel1.setIcon(new ImageIcon(scissorResized));
        scissorsLabel1.setSize(75, 75);
        scissorsLabel1.setLocation(1075, 500);
        this.add(scissorsLabel1);

        // Configurations ng Elements HUHUHUHUHUHUHUHUHUHUHUHUHUHUHUHUHUH
        JLabel rockFire = new JLabel();
        ImageIcon rockElement1 = new ImageIcon("Rock Fire.png");
        rockFire.setIcon(rockElement1);
        rockFire.setSize(50, 50);
        // this.add(rockFire);

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

    public void tryAgain() {
        playMethod();
        player.setName("Player 1");
        computer.setName("Computer");
        status.setText("Your turn");
        player.setLives(8);
        computer.setLives(8);
        p1lives.setText("Lives: " + Integer.toString(player.getLives()));
        p2lives.setText("Lives: " + Integer.toString(computer.getLives()));
    }

    public void pauseMethod() {
        rockButton.setVisible(false);
        paperButton.setVisible(false);
        scissorButton.setVisible(false);
        pauseButton.setVisible(true);
        rockLabel1.setVisible(false);
        paperLabel1.setVisible(false);
        scissorsLabel1.setVisible(false);
        status.setVisible(false);
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

    public void playMethod() {
        overlay.setVisible(false);
        rockButton.setVisible(true);
        paperButton.setVisible(true);
        scissorButton.setVisible(true);
        pauseButton.setVisible(true);
        newGame.setVisible(false);
        rockLabel1.setVisible(true);
        paperLabel1.setVisible(true);
        scissorsLabel1.setVisible(true);
        status.setVisible(true);
        paused.setVisible(false);
        p1lives.setVisible(true);
        p2lives.setVisible(true);
        name1.setVisible(true);
        name2.setVisible(true);
        pauseButton.setVisible(true);
        playButton.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (player.getLives() == 1) {
            status.setText("You Lost!");
            rockButton.setEnabled(false);
            paperButton.setEnabled(false);
            scissorButton.setEnabled(false);
            retryButton.setEnabled(true);
            retryButton.setVisible(true);
        } else if (computer.getLives() == 1) {
            status.setText("You Won!");
            rockButton.setEnabled(false);
            paperButton.setEnabled(false);
            scissorButton.setEnabled(false);
            retryButton.setEnabled(true);
            retryButton.setVisible(true);
        } else {
            randomElement();
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
        retryButton.setVisible(false);
        computer.setLives(8);
        player.setLives(8);
        p1lives.setText("Lives: " + Integer.toString(player.getLives()));
        p2lives.setText("Lives: " + Integer.toString(computer.getLives()));
        status.setText("Your Turn");

    }

    public void proceed() {
        randomChoice = new Random();
        int computerChoice = randomChoice.nextInt(3) + 1;

        try {
            TimeUnit.SECONDS.sleep(1);
            if (player.getChoice() == computerChoice) {
                winOrLose = "Tie";
            } else if ((player.getChoice() == 1 && computerChoice == 3) ||
                    (player.getChoice() == 2 && computerChoice == 1) ||
                    (player.getChoice() == 3 && computerChoice == 2)) {
                winOrLose = "You Won";
                int livesp2 = computer.getLives() - 1;
                computer.setLives(livesp2);
                p2lives.setText("Lives: " + Integer.toString(computer.getLives()));
            } else {
                winOrLose = "You Lost";
                int livesp1 = player.getLives() - 1;
                player.setLives(livesp1);
                p1lives.setText("Lives: " + Integer.toString(player.getLives()));
            }
        } catch (InterruptedException e) {
            System.out.println("Something went wrong");
        }

        if (computerChoice == 1) {
            status.setText(winOrLose + ". " + computer.getName() + " choosed Rock");
        } else if (computerChoice == 2) {
            status.setText(winOrLose + ". " + computer.getName() + " choosed Paper");
        } else if (computerChoice == 3) {
            status.setText(winOrLose + ". " + computer.getName() + " choosed Scissors");
        }

    }

    void randomElement() {

        // int elements = randomChoice.nextInt(3);
        // System.out.println(elements);

    }

    public static void main(String[] args) {

        maingame mainGame = new maingame();

    }
}
