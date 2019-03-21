package com.luan.session10_view.demomediaplayer;

import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    void playFromRaw(View view){
        mediaPlayer=MediaPlayer.create(getApplicationContext(),R.raw.music);
        mediaPlayer.start();
    }

    void playFromSdcard(View view) throws IOException {
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setDataSource(Environment.getExternalStorageDirectory()+"/music.mp3");
        mediaPlayer.prepare();
        mediaPlayer.start();
    }

    void playFromServer(View view){
        mediaPlayer =new MediaPlayer();
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.start();
            }
        });
        mediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
                Log.i("BUFFERRING",i+"");
            }
        });

        try {
            mediaPlayer.setDataSource("https://audiodemos.github.io/embedding/original.wav");
            mediaPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void stop(View view){
        if(mediaPlayer!=null){
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }
}
