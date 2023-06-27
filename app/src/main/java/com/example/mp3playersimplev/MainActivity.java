package com.example.mp3playersimplev;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ImageView songImage, nextImage, backImage, playImage;
    TextView songName;
    SeekBar durationBar;


    int currentSong = 0;
    static MediaPlayer mPlayer;
    private MediaController mPlayerControl;
    private Runnable mRun;
    private AudioManager mPlayerManager;

    private void songName(){

        if(currentSong == 0)
        {
            songName.setText("Given UP - LP");
            songImage.setImageResource(R.drawable.lpmtm);
        }
        else if (currentSong == 1)
        {
            songName.setText("Points of Authority - LP");
            songImage.setImageResource(R.drawable.hybrid);
        }
        else if (currentSong == 2)
        {
            songName.setText("Somewhere I Belong - LP");
            songImage.setImageResource(R.drawable.meteora);
        }
        else if (currentSong == 3)
        {
            songName.setText("Awaken (HardStyle)");
            songImage.setImageResource(R.drawable.awaken);
        }
        else if (currentSong == 4)
        {
            songName.setText("Vendetta");
            songImage.setImageResource(R.drawable.vendeta);
        }
        else if (currentSong == 5)
        {
            songName.setText("Pull The Trigger");
            songImage.setImageResource(R.drawable.trigger);
        }
        else if (currentSong == 6)
        {
            songName.setText("Lovely");
            songImage.setImageResource(R.drawable.lovely);
        }
        else if (currentSong == 7)
        {
            songName.setText("GHOST!");
            songImage.setImageResource(R.drawable.ghost);
        }
        else if (currentSong == 8)
        {
            songName.setText("BANKAI!");
            songImage.setImageResource(R.drawable.bankai);
        }


        mPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
               durationBar.setMax(mPlayer.getDuration());
               mPlayer.start();
            }
        });
        durationBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(b){
                    mPlayer.seekTo(i);
                    durationBar.setProgress(i);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(mPlayer!=null)
                {
                    try
                    {
                        if(mPlayer.isPlaying())
                        {
                            Message message = new Message();
                            message.what=mPlayer.getCurrentPosition();
                            handler.sendMessage(message);
                            Thread.sleep(1000);
                        }
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
    @SuppressLint("Leak")
    Handler handler=new Handler(){
        @Override
        public void handleMessage (Message msg)
        {
            durationBar.setProgress(msg.what);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPlayerManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        durationBar = findViewById(R.id.durationBar);
        nextImage = findViewById(R.id.nextImage);
        playImage = findViewById(R.id.playImage);
        backImage = findViewById(R.id.backImage);
        songName = findViewById(R.id.songName);
        songImage = findViewById(R.id.songImage);

        ArrayList<Integer> mSong = new ArrayList<>();
        mSong.add(0,R.raw.givenup);
        mSong.add(1,R.raw.pointsofa);
        mSong.add(2,R.raw.somewhere);
        mSong.add(3,R.raw.awaken);
        mSong.add(4,R.raw.vendetta);
        mSong.add(5,R.raw.pullthetriger);
        mSong.add(6,R.raw.lovely);
        mSong.add(7,R.raw.ghost);
        mSong.add(8,R.raw.bankai);

        mPlayer = MediaPlayer.create(getApplicationContext(), mSong.get(currentSong));

        playImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                durationBar.setMax(mPlayer.getDuration());
                if(mPlayer!=null && mPlayer.isPlaying())
                {
                    mPlayer.pause();
                    playImage.setImageResource(R.drawable.playff);
                }
                else
                {
                    mPlayer.start();
                    playImage.setImageResource(R.drawable.pauseff);
                }
                songName();
            }
        });

        nextImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mPlayer!=null)
                {
                    playImage.setImageResource(R.drawable.pauseff);
                }
                if(currentSong<mSong.size()-1)
                {
                    currentSong++;
                }
                else
                {
                    currentSong=0;
                }
                if(mPlayer.isPlaying())
                {
                    mPlayer.stop();
                }

                mPlayer=MediaPlayer.create(getApplicationContext(), mSong.get(currentSong));
                mPlayer.start();
                songName();
            }
        });

        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mPlayer!=null)
                {
                    playImage.setImageResource(R.drawable.pauseff);
                }
                if(currentSong >0)
                {
                    currentSong--;
                }
                else
                {
                    currentSong = mSong.size() - 1;
                }
                if(mPlayer.isPlaying())
                {
                    mPlayer.stop();
                }

                mPlayer=MediaPlayer.create(getApplicationContext(), mSong.get(currentSong));
                mPlayer.start();
                songName();
            }
        });
    }


}