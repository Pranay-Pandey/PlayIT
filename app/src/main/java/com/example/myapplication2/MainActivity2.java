package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent = getIntent();
        String str = intent.getStringExtra("url");

        VideoView videoView = findViewById(R.id.videoplayerView);
//        String str = "https://hopewellbc.com/wp-content/uploads/2014/02/Trianing-Video.mp4";
        Uri uri = Uri.parse(str);
        videoView.setVideoURI(uri);


        android.widget.MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);
    }
}