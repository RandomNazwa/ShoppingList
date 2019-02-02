package com.example.administrator.myapka;

import android.media.MediaPlayer;

public class Music {
    private static MediaPlayer mediaPlayer;
    private static boolean paused = false;
    private static int currentSong = 0;

    public static void Initialize() {
        mediaPlayer = new MediaPlayer();
    }

    public static MediaPlayer getInstance() {
        if (mediaPlayer == null) {
            mediaPlayer = new MediaPlayer();
        }
        return mediaPlayer;
    }

    public static boolean isPaused() {
        return paused;
    }

    public static void setPaused(boolean value) {
        paused = value;
    }

    public static int getCurrentSong(){
        return currentSong;
    }

    public static void setCurrentSong(int i){
        currentSong = i;
    }

}
