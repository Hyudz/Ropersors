package resources;

public class profile {

    private String name;
    private int lives = 8;
    private int defaultChoice;

    void setName(String p1Name) {
        this.name = p1Name;
    }

    void setChoice(int defaultChoice) {
        this.defaultChoice = defaultChoice;
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

    int getChoice() {
        return defaultChoice;
    }

}
