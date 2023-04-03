import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainGUI extends JFrame implements ActionListener {
    private JButton openSubGUIButton;
    private JPanel mainPanel;

    public MainGUI() {
        setTitle("Main GUI");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create main panel
        mainPanel = new JPanel();
        openSubGUIButton = new JButton("Open Sub GUI");
        openSubGUIButton.addActionListener(this);

        mainPanel.add(openSubGUIButton);
        getContentPane().add(mainPanel, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == openSubGUIButton) {
            SubGUI subGUI = new SubGUI(this);
            subGUI.setVisible(true);
        }
    }

    public void removePanel() {
        mainPanel.removeAll();
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    public static void main(String[] args) {
        MainGUI mainGUI = new MainGUI();
        mainGUI.setVisible(true);
    }
}
