package files;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class music {

    public boolean playSound = false;

    Clip clip, panalo, natalo, absoluteDmg, mode1, mode2;
    AudioInputStream sound, sound1;

    File lobby = new File("Sound FX\\BG music\\Eggy-Toast-Crater.wav");
    File victory = new File("Sound FX\\other sound fx\\kl-peach-game-over-iii-142453.wav");
    File defeat = new File("Sound FX\\other sound fx\\dead-8bit-41400.wav");
    File crit = new File("Sound FX\\other sound fx\\playerhit-43108.wav");
    File bg1 = new File("Sound FX\\BG music\\HoliznaCC0-Level-1.wav");
    File bg2 = new File("Sound FX\\BG music\\HoliznaCC0-Level-2.wav");
    File bg3 = new File("Sound FX\\BG music\\HoliznaCC0-Level-3.wav");
    File bg4 = new File("Sound FX\\BG music\\Eggy-Toast-Pressure.wav");

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

    public void stopLobby() {
        clip.stop();
    }

    public void playPvEMode() {

        try {
            sound1 = AudioSystem.getAudioInputStream(bg1);
            mode1 = AudioSystem.getClip();
            mode1.setFramePosition(0);
            mode1.open(sound1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        mode1.start();
        mode1.loop(Clip.LOOP_CONTINUOUSLY);

    }

    public void gameRestart1Lost() {
        natalo.stop();
        mode1.setFramePosition(0);
        mode1.start();
    }

    public void gameRestart1Won() {
        panalo.stop();
        mode1.setFramePosition(0);
        mode1.start();
    }

    public void gameRestart1Normal() {
        mode1.setFramePosition(0);
    }

    public void stopPveMode() {
        mode1.stop();
    }

    public void playPvPMode() {
        try {
            AudioInputStream sound = AudioSystem.getAudioInputStream(bg1);
            mode2 = AudioSystem.getClip();
            mode2.open(sound);
        } catch (Exception e) {
            e.printStackTrace();
        }

        mode2.setFramePosition(0);
        mode2.start();
        mode2.loop(Clip.LOOP_CONTINUOUSLY);

    }

    public void gameRestart2Lost() {
        natalo.stop();
        mode2.setFramePosition(0);
        mode2.start();
    }

    public void gameRestart2Won() {
        panalo.stop();
        mode2.setFramePosition(0);
        mode2.start();
    }

    public void gameRestart2Normal() {
        mode2.setFramePosition(0);
    }

    public void stopPvpMode() {
        mode2.stop();
    }

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

    public void winner() {
        try {
            AudioInputStream sound = AudioSystem.getAudioInputStream(victory);
            panalo = AudioSystem.getClip();
            panalo.open(sound);
        } catch (Exception e) {
            e.printStackTrace();
        }

        mode1.stop();
        // mode2.stop();
        panalo.setFramePosition(0);
        panalo.loop(Clip.LOOP_CONTINUOUSLY);
        panalo.start();
    }

    public void looser() {
        try {
            AudioInputStream sound = AudioSystem.getAudioInputStream(defeat);
            natalo = AudioSystem.getClip();
            natalo.open(sound);
        } catch (Exception e) {
            e.printStackTrace();
        }

        mode1.stop();
        // mode2.stop();
        natalo.setFramePosition(0);
        natalo.loop(Clip.LOOP_CONTINUOUSLY);
        natalo.start();
    }

}