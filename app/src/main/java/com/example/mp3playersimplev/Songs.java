package com.example.mp3playersimplev;

import android.widget.ImageView;
import android.widget.TextView;

public class Songs {

    private void songName(int currentSong, TextView songName, ImageView songImage) {

        if (currentSong == 0) {
            songName.setText("Given UP - LP");
            songImage.setImageResource(R.drawable.lpmtm);
        } else if (currentSong == 1) {
            songName.setText("Points of Authority - LP");
            songImage.setImageResource(R.drawable.hybrid);
        } else if (currentSong == 2) {
            songName.setText("Somewhere I Belong - LP");
            songImage.setImageResource(R.drawable.meteora);
        } else if (currentSong == 3) {
            songName.setText("Awaken (HardStyle)");
            songImage.setImageResource(R.drawable.awaken);
        } else if (currentSong == 4) {
            songName.setText("Vendetta");
            songImage.setImageResource(R.drawable.vendeta);
        } else if (currentSong == 5) {
            songName.setText("Pull The Trigger");
            songImage.setImageResource(R.drawable.trigger);
        } else if (currentSong == 6) {
            songName.setText("Lovely");
            songImage.setImageResource(R.drawable.lovely);
        } else if (currentSong == 7) {
            songName.setText("GHOST!");
            songImage.setImageResource(R.drawable.ghost);
        } else if (currentSong == 8) {
            songName.setText("BANKAI!");
            songImage.setImageResource(R.drawable.bankai);
        }

    }
}

