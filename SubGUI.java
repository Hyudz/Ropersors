import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SubGUI extends JFrame implements ActionListener {
    private JButton removePanelButton;

    private MainGUI mainGUI;

    public SubGUI(MainGUI mainGUI) {
        setTitle("Sub GUI");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.mainGUI = mainGUI;

        // Create sub panel
        JPanel subPanel = new JPanel();
        removePanelButton = new JButton("Remove Main Panel");
        removePanelButton.addActionListener(this);

        subPanel.add(removePanelButton);
        getContentPane().add(subPanel, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == removePanelButton) {
            mainGUI.removePanel();
            dispose();
        }
    }
}
