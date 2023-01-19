package com.example.myapplication2;

import android.app.Application;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.analytics.PlayerId;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaExtractor;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.ExoTrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.ui.StyledPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource;

public class ViewHolder extends RecyclerView.ViewHolder{

    View mView;
    ExoPlayer exoPlayer;

    private SimpleExoPlayer player;
    private PlayerView playerView;
    private String videoUrl = "YOUR_VIDEO_URL";

    private StyledPlayerView mExoplayerView;



    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        mView = itemView;
    }
    public void setText( final Application ctx, String Title, final String url)
    {
        TextView mtextView = mView.findViewById(R.id.textbox_view);
        mtextView.setText(Title);
        mtextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(ctx, "URL="+url.toString(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ctx.getApplicationContext(), MainActivity2.class);
                intent.putExtra("url", url);
                ctx.startActivity(intent);
            }

        });

    }

    public void setVideo( final Application ctx, String Title, final String url)
    {
        TextView mtextView = mView.findViewById(R.id.textbox_view);
        mExoplayerView = mView.findViewById(R.id.exoplayer_view);

        mtextView.setText(Title);
        try
        {
            BandwidthMeter bandwidthMeter =  new DefaultBandwidthMeter.Builder(ctx).build();
            TrackSelector trackSelector = new  DefaultTrackSelector(ctx);
            exoPlayer = (ExoPlayer) new ExoPlayer.Builder(ctx).build();
            Uri video = Uri.parse(url);
            DefaultHttpDataSource.Factory dataSource = new DefaultHttpDataSource.Factory();
            ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
            MediaSource mediaSource = new ProgressiveMediaSource.Factory(dataSource).createMediaSource(MediaItem.fromUri(video));
            mExoplayerView.setPlayer(exoPlayer);
            exoPlayer.setMediaSource(mediaSource);
            exoPlayer.prepare();
            exoPlayer.setPlayWhenReady(true);
        }
        catch (Exception e)
        {
            Log.e("ViewHolder", "exoplayer error"+ e.toString());
        }
    }
}
