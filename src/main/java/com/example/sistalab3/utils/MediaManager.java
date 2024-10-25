package com.example.sistalab3.utils;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MediaManager {
    private MediaPlayer backgroundMusicPlayer;

    public void playBackgroundMusic(String filePath, boolean loop) {
        Media media = new Media(getClass().getResource(filePath).toExternalForm());
        backgroundMusicPlayer = new MediaPlayer(media);
        if (loop) {
            backgroundMusicPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Loopar musiken
        }
        backgroundMusicPlayer.play();
    }

    public void stopBackgroundMusic() {
        if (backgroundMusicPlayer != null) {
            backgroundMusicPlayer.stop();
        }
    }

    public void playSoundEffect(String filePath) {
        Media soundEffect = new Media(getClass().getResource(filePath).toExternalForm());
        MediaPlayer soundEffectPlayer = new MediaPlayer(soundEffect);
        soundEffectPlayer.play();
    }
}
