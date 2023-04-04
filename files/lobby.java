package files;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class lobby extends JFrame {

    mainMenu menu = new mainMenu();

    lobby() {

        this.add(menu);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Ropersors");
        this.setLayout(null);
        this.setVisible(true);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setSize(1550, 830);
        this.setResizable(false);
        ImageIcon icon = new ImageIcon("boosh withdoro1.png");
        this.setIconImage(icon.getImage());
    }

    public static void main(String[] args) {
        lobby newLobby = new lobby();
    }
}
