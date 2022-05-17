package com.example.krishi_network;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.cast.VideoInfo;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;;

import java.util.Random;


public class YoutubeActivity extends YouTubeBaseActivity {

    private YouTubePlayerView mYouTubePlayerView;
    private com.google.android.youtube.player.YouTubePlayer.OnInitializedListener mOnInitializedListener;
    private com.google.android.youtube.player.YouTubePlayer mYouTubePlayer;
    private Button mButton,btnNext;
    Random random;
    int vidIdx=0;

    private final String[] VIDEO_CODE={"IEF6mw7eK4s", "8CEJoCr_9UI", "3-nM3yNi3wg","kJQP7kiw5Fk","pRpeEdMmmQ0","RgKAFK5djSk","yU0tnrEk8H4"};
    private final String API_KEY="AIzaSyCZZ8_YMUFPRTcvHTUZ-6Y2_brRlhiTjbk";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube);

        youTubePlayerSetup();
        mButton  = findViewById(R.id.btnPlay);
        btnNext  = findViewById(R.id.btnNext);
        random=new Random();

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (vidIdx<VIDEO_CODE.length-1) {
                    vidIdx++;
                }else{
                    vidIdx=0;
                }
                mYouTubePlayer.cueVideo(VIDEO_CODE[vidIdx]);
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(i);
            }
        });
    }

    private void youTubePlayerSetup() {
        mYouTubePlayerView = (YouTubePlayerView) findViewById(R.id.ytPlayer);
        mOnInitializedListener = new com.google.android.youtube.player.YouTubePlayer.OnInitializedListener(){

            @Override
            public void onInitializationSuccess(com.google.android.youtube.player.YouTubePlayer.Provider provider, com.google.android.youtube.player.YouTubePlayer youTubePlayer, boolean b) {
                if(!b){
                    mYouTubePlayer =youTubePlayer;
                    mYouTubePlayer.cueVideo(VIDEO_CODE[vidIdx]);
                    mYouTubePlayer.play();
                }
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };
        mYouTubePlayerView.initialize(API_KEY,mOnInitializedListener);
    }

}