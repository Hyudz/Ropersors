import javax.swing.*;
import java.awt.*;

public class NestedBorderLayoutExample extends JFrame {
    public NestedBorderLayoutExample() {
        setTitle("Nested Border Layout Example");
        setSize(400, 300);

        // Create the main container with border layout
        Container container = getContentPane();
        container.setLayout(new BorderLayout());

        // Create a panel for the north region with a label
        JPanel panel1 = new JPanel();
        panel1.setLayout(new BorderLayout());
        JLabel label1 = new JLabel("This is panel 1");
        panel1.add(label1, BorderLayout.CENTER);

        // Create a panel for the center region with a nested border layout
        JPanel panel2 = new JPanel();
        panel2.setLayout(new BorderLayout());

        // Create a panel for the west region with a button
        JPanel panel3 = new JPanel();
        panel3.setLayout(new BorderLayout());
        JButton button1 = new JButton("Click me!");
        panel3.add(button1, BorderLayout.CENTER);

        // Add panel3 to the west region of panel2
        panel2.add(panel3, BorderLayout.WEST);

        // Create a panel for the center region with a label
        JPanel panel4 = new JPanel();
        panel4.setLayout(new BorderLayout());
        JLabel label2 = new JLabel("This is panel 4");
        panel4.add(label2, BorderLayout.CENTER);

        // Add panel4 to the center region of panel2
        panel2.add(panel4, BorderLayout.CENTER);

        // Add panel2 to the center region of the main container
        container.add(panel2, BorderLayout.CENTER);

        // Create a panel for the south region with a label
        JPanel panel5 = new JPanel();
        panel5.setLayout(new BorderLayout());
        JLabel label3 = new JLabel("This is panel 5");
        panel5.add(label3, BorderLayout.CENTER);

        // Add panel1 to the north region of the main container
        container.add(panel1, BorderLayout.NORTH);

        // Add panel5 to the south region of the main container
        container.add(panel5, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        NestedBorderLayoutExample example = new NestedBorderLayoutExample();
        example.setVisible(true);
    }
}
