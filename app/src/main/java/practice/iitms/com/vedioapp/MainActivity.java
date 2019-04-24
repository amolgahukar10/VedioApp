package practice.iitms.com.vedioapp;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import tcking.github.com.giraffeplayer2.VideoView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        String testUrl = "http://zhibo.hkstv.tv/livestream/zb2yhapo/playlist.m3u8"; //test live stream;
//        String testUrl = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4"; //test live stream;
        String testUrl  = "http://distribution.bbb3d.renderfarming.net/video/mp4/bbb_sunflower_1080p_30fps_normal.mp4";
        //test live stream;

        getSupportFragmentManager().beginTransaction().add(R.id.container, new MainFragment()).commit();

        /*final VideoView videoView = findViewById(R.id.video_view);
        videoView.setVideoPath(testUrl);
        videoView.getVideoInfo().setPortraitWhenFullScreen(true);*/
    }

    @Override
    protected void onPause() {
        super.onPause();
    }


}