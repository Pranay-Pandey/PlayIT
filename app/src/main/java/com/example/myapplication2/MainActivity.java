package com.example.myapplication2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.LayoutInflater;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication2.databinding.ActivityMainBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.MediaController;
import android.widget.VideoView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class MainActivity extends AppCompatActivity {

    RecyclerView MrecyclerView;
    FirebaseDatabase database;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        VideoView videoView = findViewById(R.id.video_view2);
////        String videoPath = "https://hopewellbc.com/wp-content/uploads/2014/02/Trianing-Video.mp4";
////        String videoPath = "res/raw/sdc.mp4";
//        String videoPath  = "https://drive.google.com/file/d/10E_2WCYl7pn0air1U0V2bvaZIQ37sNJT/view";
//        Uri uri = Uri.parse(videoPath);
//        videoView.setVideoURI(uri);
//
//
//        MediaController mediaController = new MediaController(this);
//        videoView.setMediaController(mediaController);
//        mediaController.setAnchorView(videoView);

        MrecyclerView = findViewById(R.id.recyclerview_video);
        MrecyclerView.setHasFixedSize(true);
        MrecyclerView.setLayoutManager(new LinearLayoutManager(this));
        database= FirebaseDatabase.getInstance();
        reference = database.getReference("video");
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<member> options =
                new FirebaseRecyclerOptions.Builder<member>()
                        .setQuery(reference, member.class)
                        .build();

        FirebaseRecyclerAdapter<member, ViewHolder>firebaseRecyclerAdapter =

                new FirebaseRecyclerAdapter<member, ViewHolder>(
                        options
                ) {
                    @NonNull
                    @Override
                    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.text_files_view, parent, false);

                        return new ViewHolder(view);

                    }

                    @Override
                    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull member model) {
                        holder.setText(getApplication(),model.getTitle(), model.getUrl());
                    }

                    protected void populateViewHolder(ViewHolder viewHolder, member member, int i)
                    {
                            viewHolder.setText(getApplication(),member.getTitle(), member.getUrl());

                    }
                };
     MrecyclerView.setAdapter(firebaseRecyclerAdapter);
        firebaseRecyclerAdapter.startListening();
    }


//
//    public String downloadUrl(String myurl) throws IOException {
//        InputStream is = null;
//        try {
//            URL url = new URL(myurl);
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.setReadTimeout(10000);
//            conn.setConnectTimeout(15000);
//            conn.setRequestMethod("GET");
//            conn.setDoInput(true);
//            conn.connect();
//            is = conn.getInputStream();
//            String contentAsString = readIt(is);
//            return contentAsString;
//        } finally {
//            if (is != null) {
//                is.close();
//            }
//        }
//    }
//
//    public String readIt(InputStream stream) throws IOException {
//        BufferedReader reader = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
//        StringBuilder sb = new StringBuilder();
//        String line;
//        while ((line = reader.readLine()) != null) {
//            if (line.contains("fmt_stream_map")) {
//                sb.append(line + "\n");
//                break;
//            }
//        }
//        reader.close();
//        String result = decode(sb.toString());
//        String[] url = result.split("\\|");
//        return url[1];
//    }
//
//    public String decode(String in) {
//        String working = in;
//        int index;
//        index = working.indexOf("\\u");
//        while (index > -1) {
//            int length = working.length();
//            if (index > (length - 6)) break;
//            int numStart = index + 2;
//            int numFinish = numStart + 4;
//            String substring = working.substring(numStart, numFinish);
//            int number = Integer.parseInt(substring, 16);
//            String stringStart = working.substring(0, index);
//            String stringEnd = working.substring(numFinish);
//            working = stringStart + ((char) number) + stringEnd;
//            index = working.indexOf("\\u");
//        }
//        return working;
//    }
}