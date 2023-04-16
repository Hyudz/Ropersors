package files;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class music {

    public boolean playSound = false;

    Clip clip, panalo, natalo, absoluteDmg, gameMode1, gameMode2, gameMode3, gameMode4;
    AudioInputStream sound, sound1, sound2, sound3, sound4;

    File lobby = new File("Sound FX\\BG music\\lobby.wav");
    File victory = new File("Sound FX\\other sound fx\\win4.wav");
    File defeat = new File("Sound FX\\other sound fx\\dead-8bit-41400.wav");
    File crit = new File("Sound FX\\other sound fx\\playerhit-43108.wav");
    File bg1 = new File("Sound FX\\BG music\\magma.wav");
    File bg2 = new File("Sound FX\\BG music\\sky.wav");
    File bg3 = new File("Sound FX\\BG music\\sand.wav");
    File bg4 = new File("Sound FX\\BG music\\wood.wav");

    // ITONG MUSIC NA TO PARA SA LOBBY
    public void playLobby() {
        try {
            sound = AudioSystem.getAudioInputStream(lobby);
            clip = AudioSystem.getClip();
            clip.setFramePosition(0);
            clip.open(sound);
        } catch (Exception e) {
            e.printStackTrace();
        }

        clip.loop(Clip.LOOP_CONTINUOUSLY);
        clip.start();
    }

    // ITO NAMAN PARA MAG STOP YUNG SOUND NG LOBBY
    public void stopLobby() {
        clip.stop();
    }

    // ITO NAMAN PARA SA PvE and PvP NA SOUND
    public void playGame1() {

        try {
            sound1 = AudioSystem.getAudioInputStream(bg1);
            gameMode1 = AudioSystem.getClip();
            gameMode1.setFramePosition(0);
            gameMode1.open(sound1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        gameMode1.start();
        gameMode1.loop(Clip.LOOP_CONTINUOUSLY);

    }

    // ITO NAMAN PARA SA PvE and PvP NA SOUND
    public void playGame2() {

        try {
            sound2 = AudioSystem.getAudioInputStream(bg2);
            gameMode2 = AudioSystem.getClip();
            gameMode2.setFramePosition(0);
            gameMode2.open(sound2);
        } catch (Exception e) {
            e.printStackTrace();
        }

        gameMode2.start();
        gameMode2.loop(Clip.LOOP_CONTINUOUSLY);

    }

    // ITO NAMAN PARA SA PvE and PvP NA SOUND
    public void playGame3() {

        try {
            sound3 = AudioSystem.getAudioInputStream(bg3);
            gameMode3 = AudioSystem.getClip();
            gameMode3.setFramePosition(0);
            gameMode3.open(sound3);
        } catch (Exception e) {
            e.printStackTrace();
        }

        gameMode3.start();
        gameMode3.loop(Clip.LOOP_CONTINUOUSLY);

    }

    // ITO NAMAN PARA SA PvE and PvP NA SOUND
    public void playGame4() {

        try {
            sound4 = AudioSystem.getAudioInputStream(bg4);
            gameMode4 = AudioSystem.getClip();
            gameMode4.setFramePosition(0);
            gameMode4.open(sound4);
        } catch (Exception e) {
            e.printStackTrace();
        }

        gameMode4.start();
        gameMode4.loop(Clip.LOOP_CONTINUOUSLY);

    }

    // THIS IS TO RESTART THE SOUND AFTER MATALO NI USER

    // BOARD MUSIC 1
    public void gameRestartLost() {
        natalo.stop();
        gameMode1.setFramePosition(0);
        gameMode1.start();
    }

    // THIS IS TO RESTART THE SOUND AFTER MANALO NI USER
    public void gameRestartWon() {
        panalo.stop();
        gameMode1.setFramePosition(0);
        gameMode1.start();
    }

    // THIS IS TO RESTART THE SOUND PAG NAG NEW GAME SI USER
    public void gameRestartNormal() {
        gameMode1.setFramePosition(0);
    }

    // THIS IS TO STOP THE SOUND AT PVP/PVE
    public void stopMode1() {
        gameMode1.stop();
    }

    // GAME ENDS AND YOU WON ON PVP/PVE
    public void winner() {
        try {
            AudioInputStream sound = AudioSystem.getAudioInputStream(victory);
            panalo = AudioSystem.getClip();
            panalo.open(sound);
        } catch (Exception e) {
            e.printStackTrace();
        }

        gameMode1.stop();
        panalo.setFramePosition(0);
        panalo.start();
    }

    // GAME ENDS AND YOU LOST ON PVE
    public void looser() {
        try {
            AudioInputStream sound = AudioSystem.getAudioInputStream(defeat);
            natalo = AudioSystem.getClip();
            natalo.open(sound);
        } catch (Exception e) {
            e.printStackTrace();
        }

        gameMode1.stop();
        natalo.setFramePosition(0);
        natalo.start();
    }

    // BOARD MUSIC 2========================================
    public void gameRestartLost2() {
        natalo.stop();
        gameMode2.setFramePosition(0);
        gameMode2.start();
    }

    // THIS IS TO RESTART THE SOUND AFTER MANALO NI USER
    public void gameRestartWon2() {
        panalo.stop();
        gameMode2.setFramePosition(0);
        gameMode2.start();
    }

    // THIS IS TO RESTART THE SOUND PAG NAG NEW GAME SI USER
    public void gameRestartNormal2() {
        gameMode2.setFramePosition(0);
    }

    // THIS IS TO STOP THE SOUND AT PVP/PVE
    public void stopMode2() {
        gameMode2.stop();
    }

    // GAME ENDS AND YOU WON ON PVP/PVE==========================
    public void winner2() {
        try {
            AudioInputStream sound = AudioSystem.getAudioInputStream(victory);
            panalo = AudioSystem.getClip();
            panalo.open(sound);
        } catch (Exception e) {
            e.printStackTrace();
        }

        gameMode2.stop();
        panalo.setFramePosition(0);
        panalo.start();
    }

    // GAME ENDS AND YOU LOST ON PVE
    public void looser2() {
        try {
            AudioInputStream sound = AudioSystem.getAudioInputStream(defeat);
            natalo = AudioSystem.getClip();
            natalo.open(sound);
        } catch (Exception e) {
            e.printStackTrace();
        }

        gameMode2.stop();
        natalo.setFramePosition(0);
        natalo.start();
    }

    // BOARD MUSIC 3==========================================
    public void gameRestartLost3() {
        natalo.stop();
        gameMode3.setFramePosition(0);
        gameMode3.start();
    }

    // THIS IS TO RESTART THE SOUND AFTER MANALO NI USER
    public void gameRestartWon3() {
        panalo.stop();
        gameMode3.setFramePosition(0);
        gameMode3.start();
    }

    // THIS IS TO RESTART THE SOUND PAG NAG NEW GAME SI USER
    public void gameRestartNormal3() {
        gameMode3.setFramePosition(0);
    }

    // THIS IS TO STOP THE SOUND AT PVP/PVE
    public void stopMode3() {
        gameMode3.stop();
    }

    // GAME ENDS AND YOU WON ON PVP/PVE
    public void winner3() {
        try {
            AudioInputStream sound = AudioSystem.getAudioInputStream(victory);
            panalo = AudioSystem.getClip();
            panalo.open(sound);
        } catch (Exception e) {
            e.printStackTrace();
        }

        gameMode3.stop();
        panalo.setFramePosition(0);
        panalo.start();
    }

    // GAME ENDS AND YOU LOST ON PVE
    public void looser3() {
        try {
            AudioInputStream sound = AudioSystem.getAudioInputStream(defeat);
            natalo = AudioSystem.getClip();
            natalo.open(sound);
        } catch (Exception e) {
            e.printStackTrace();
        }

        gameMode3.stop();
        natalo.setFramePosition(0);
        natalo.start();
    }

    // BOARD MUSIC 4 ========================================
    public void gameRestartLost4() {
        natalo.stop();
        gameMode4.setFramePosition(0);
        gameMode4.start();
    }

    // THIS IS TO RESTART THE SOUND AFTER MANALO NI USER
    public void gameRestartWon4() {
        panalo.stop();
        gameMode4.setFramePosition(0);
        gameMode4.start();
    }

    // THIS IS TO RESTART THE SOUND PAG NAG NEW GAME SI USER
    public void gameRestartNormal4() {
        gameMode4.setFramePosition(0);
    }

    // THIS IS TO STOP THE SOUND AT PVP/PVE
    public void stopMode4() {
        gameMode4.stop();
    }

    // GAME ENDS AND YOU WON ON PVP/PVE
    public void winner4() {
        try {
            AudioInputStream sound = AudioSystem.getAudioInputStream(victory);
            panalo = AudioSystem.getClip();
            panalo.open(sound);
        } catch (Exception e) {
            e.printStackTrace();
        }

        gameMode4.stop();
        panalo.setFramePosition(0);
        panalo.start();
    }

    // GAME ENDS AND YOU LOST ON PVE
    public void looser4() {
        try {
            AudioInputStream sound = AudioSystem.getAudioInputStream(defeat);
            natalo = AudioSystem.getClip();
            natalo.open(sound);
        } catch (Exception e) {
            e.printStackTrace();
        }

        gameMode4.stop();
        natalo.setFramePosition(0);
        natalo.start();
    }

    // PLAYS THE SOUND IF ABSOLUTE DAMAGE IS TRIGGERED
    public void absoluteSound() {
        try {
            AudioInputStream sound = AudioSystem.getAudioInputStream(crit);
            absoluteDmg = AudioSystem.getClip();
            absoluteDmg.open(sound);
        } catch (Exception e) {
            e.printStackTrace();
        }

        absoluteDmg.setFramePosition(0);
        absoluteDmg.start();
    }

}