package oldVer;

public class players {

    private String name;
    private int lives = 10;
    private int defaultChoice;
    private String element;

    public void setName(String name) {
        this.name = name;
    }

    public void setChoice(int defaultChoice) {
        this.defaultChoice = defaultChoice;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public String getElement() {
        return element;
    }

    public String getName() {
        return name;
    }

    int getLives() {
        return lives;
    }

    void setLives(int lives) {
        this.lives = lives;
    }

    int getChoice() {
        return defaultChoice;
    }
}
