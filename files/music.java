package files;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class music {

    Clip clip, panalo, natalo, absoluteDmg, mode1, mode2;
    AudioInputStream sound;

    File file = new File("sounds\\lobby.wav");
    File victory = new File("sounds\\victory.WAV");
    File defeat = new File("sounds\\defeat.wav");
    File crit = new File("sounds\\crit.WAV");
    File pvpMode = new File("sounds\\pvp.WAV");
    File pveMode = new File("sounds\\pve.WAV");

    public music() {
        try {
            clip = AudioSystem.getClip();
            panalo = AudioSystem.getClip();
            natalo = AudioSystem.getClip();
            absoluteDmg = AudioSystem.getClip();
            mode1 = AudioSystem.getClip();
            mode2 = AudioSystem.getClip();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void playMusic() {
        try {
            sound = AudioSystem.getAudioInputStream(file);
            clip.open(sound);
            clip.loop(Clip.LOOP_CONTINUOUSLY);

            sound = AudioSystem.getAudioInputStream(defeat);
            natalo = AudioSystem.getClip();
            natalo.open(sound);

            sound = AudioSystem.getAudioInputStream(crit);
            absoluteDmg = AudioSystem.getClip();
            absoluteDmg.open(sound);

            sound = AudioSystem.getAudioInputStream(pvpMode);
            mode1 = AudioSystem.getClip();
            mode1.open(sound);

            sound = AudioSystem.getAudioInputStream(pveMode);
            mode2 = AudioSystem.getClip();
            mode2.open(sound);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void playPvPMode() {
        try {
            clip.stop();
            sound = AudioSystem.getAudioInputStream(pveMode);
            panalo.open(sound);
            panalo.start();
        } catch (Exception e) {

        }

    }

    public void playPvEMode() {
        clip.stop();
        mode2.setFramePosition(0);
        mode2.loop(Clip.LOOP_CONTINUOUSLY);
        mode2.start();

    }
}