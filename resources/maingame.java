package resources;

import javax.swing.*;
import java.awt.event.*;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

public class maingame extends JFrame implements ActionListener {

    JLabel p1lives, p2lives, status;
    profile player = new profile();
    profile computer = new profile();
    int defaultLife = 8;
    String winOrLose;
    JButton rockButton, paperButton, scissorButton, retryButton;
    String[] choices = { "rock", "paper", "scissors" };

    maingame() {

        player.setName("Player 1");
        computer.setName("Computer");

        String[] fireElements = { "Rock Fire", "Paper Fire", "Scissors Fire" };
        String[] waterElements = { "Rock Water", "Paper Water", "Scissors Water" };
        String[] natureElements = { "Rock Water", "Paper Water", "Scissors Water" };

        ImageIcon board = new ImageIcon("board2.png");
        Image boardImage = board.getImage();
        Image boardResized = boardImage.getScaledInstance(1535, 792, java.awt.Image.SCALE_SMOOTH);
        board = new ImageIcon(boardResized);
        this.setContentPane(new JLabel(board));

        // SET THE CONFIGURATION OF PLAYER 1 NAME
        JLabel name1 = new JLabel();
        name1.setText(player.getName());
        name1.setLocation(300, 100);
        name1.setSize(150, 30);
        name1.setFont(new Font("DePixel", Font.BOLD, 23));
        name1.setForeground(Color.WHITE);
        this.add(name1);

        // SET THE CONFIGURATION OF COMPUTER NAME
        JLabel name2 = new JLabel();
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
        placeholder.setLocation(0, 3);
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

        // COMFIGURATIONS NG ROCK PAPER AND SCISSORS
        ImageIcon rockIcon = new ImageIcon("Rock Default.png");
        rockButton = new JButton();
        rockButton.setSize(50, 50);
        rockButton.setLocation(350, 300);
        rockButton.setIcon(rockIcon);
        rockButton.setBorderPainted(false);
        rockButton.setFocusPainted(false);
        rockButton.setContentAreaFilled(false);
        rockButton.addActionListener(this);
        rockButton.addActionListener(e -> player.setChoice(1));
        this.add(rockButton);

        ImageIcon paperIcon = new ImageIcon("Paper Default (2).png");
        paperButton = new JButton();
        paperButton.setSize(50, 50);
        paperButton.setLocation(350, 400);
        paperButton.setIcon(paperIcon);
        paperButton.setBorderPainted(false);
        paperButton.setFocusPainted(false);
        paperButton.setContentAreaFilled(false);
        paperButton.addActionListener(this);
        paperButton.addActionListener(e -> player.setChoice(2));
        this.add(paperButton);

        ImageIcon scissorIcon = new ImageIcon("Scissors Default.png");
        scissorButton = new JButton();
        scissorButton.setSize(50, 50);
        scissorButton.setLocation(350, 500);
        scissorButton.setIcon(scissorIcon);
        scissorButton.setBorderPainted(false);
        scissorButton.setFocusPainted(false);
        scissorButton.setContentAreaFilled(false);
        scissorButton.addActionListener(this);
        scissorButton.addActionListener(e -> player.setChoice(3));
        this.add(scissorButton);

        // LABELS OF ROCK PAPER SCISORS BUT AS IMAGES
        JLabel rockLabel1 = new JLabel();
        rockLabel1.setIcon(new ImageIcon("Rock Default.png"));
        rockLabel1.setSize(50, 50);
        rockLabel1.setLocation(1075, 300);
        this.add(rockLabel1);

        JLabel paperLabel1 = new JLabel();
        paperLabel1.setIcon(new ImageIcon("Paper Default (2).png"));
        paperLabel1.setSize(50, 50);
        paperLabel1.setLocation(1075, 400);
        this.add(paperLabel1);

        JLabel scissorsLabel1 = new JLabel();
        scissorsLabel1.setIcon(new ImageIcon("Scissors Default.png"));
        scissorsLabel1.setSize(50, 50);
        scissorsLabel1.setLocation(1075, 500);
        this.add(scissorsLabel1);

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
        status.setText("Computer is thinking");
        Random randomChoice = new Random();
        int computerChoice = randomChoice.nextInt(3) + 1;

        if (player.getChoice() == computerChoice) {
            winOrLose = "Tie";
        } else if (player.getChoice() == 1 && computerChoice == 3) {
            winOrLose = "You Won";
            int livesp2 = computer.getLives() - 1;
            computer.setLives(livesp2);
            p2lives.setText("Lives: " + Integer.toString(computer.getLives()));
        } else if (player.getChoice() == 2 && computerChoice == 1) {
            winOrLose = "You Won";
            int livesp2 = computer.getLives() - 1;
            computer.setLives(livesp2);
            p2lives.setText("Lives: " + Integer.toString(computer.getLives()));
        } else if (player.getChoice() == 3 && computerChoice == 2) {
            winOrLose = "You Won";
            int livesp2 = computer.getLives() - 1;
            computer.setLives(livesp2);
            p2lives.setText("Lives: " + Integer.toString(computer.getLives()));
        } else if (computerChoice == 1 && player.getChoice() == 3) {
            winOrLose = "You Lost";
            int livesp1 = player.getLives() - 1;
            player.setLives(livesp1);
            p1lives.setText("Lives: " + Integer.toString(player.getLives()));
        } else if (computerChoice == 2 && player.getChoice() == 1) {
            winOrLose = "You Lost";
            int livesp1 = player.getLives() - 1;
            player.setLives(livesp1);
            p1lives.setText("Lives: " + Integer.toString(player.getLives()));
        } else if (computerChoice == 3 && player.getChoice() == 2) {
            winOrLose = "You Lost";
            int livesp1 = player.getLives() - 1;
            player.setLives(livesp1);
            p1lives.setText("Lives: " + Integer.toString(player.getLives()));
        }

        try {
            TimeUnit.SECONDS.sleep(1);
            if (computerChoice == 1) {
                status.setText(winOrLose + ". " + computer.getName() + " choosed Rock");
            } else if (computerChoice == 2) {
                status.setText(winOrLose + ". " + computer.getName() + " choosed Paper");
            } else if (computerChoice == 3) {
                status.setText(winOrLose + ". " + computer.getName() + " choosed Scissors");
            }
        } catch (InterruptedException e) {
            System.out.println("Something went wrong");
        }

    }

    public static void main(String[] args) {

        maingame haha = new maingame();

    }
}
