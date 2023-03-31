package files;

public class players {

    private String name;
    private int lives = 10;
    private String defaultChoice = null;
    private String element = null;

    void setName(String p1Name) {
        this.name = p1Name;
    }

    void setChoice(String defaultChoice) {
        this.defaultChoice = defaultChoice;
    }

    void setElement(String element) {
        this.element = element;
    }

    String getElement() {
        return element;
    }

    String getName() {
        return name;
    }

    int getLives() {
        return lives;
    }

    void setLives(int lives) {
        this.lives = lives;
    }

    String getChoice() {
        return defaultChoice;
    }

}
