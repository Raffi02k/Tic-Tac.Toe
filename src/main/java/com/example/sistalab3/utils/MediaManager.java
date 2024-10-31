package com.example.sistalab3.utils;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.net.URL;

public class MediaManager {
    private MediaPlayer mediaPlayer;

    public void playBackgroundMusic(String filePath, boolean loop) {
        URL musicUrl = getClass().getResource(filePath);
        if (musicUrl == null) {
            throw new IllegalArgumentException("Musikfilen kunde inte hittas: " + filePath);
        }

        Media media = new Media(musicUrl.toExternalForm());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(loop ? MediaPlayer.INDEFINITE : 1);
        mediaPlayer.play();
    }

    public void stopBackgroundMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }
}
