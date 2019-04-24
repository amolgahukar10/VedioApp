package practice.iitms.com.vedioapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;

import com.github.tcking.viewquery.ViewQuery;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import tcking.github.com.giraffeplayer2.DefaultMediaController;
import tcking.github.com.giraffeplayer2.GiraffePlayer;
import tcking.github.com.giraffeplayer2.MediaController;
import tcking.github.com.giraffeplayer2.Option;
import tcking.github.com.giraffeplayer2.PlayerManager;
import tcking.github.com.giraffeplayer2.VideoInfo;
import tcking.github.com.giraffeplayer2.VideoView;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;


public class MainFragment extends Fragment {
    private ViewQuery $;
    private int aspectRatio = VideoInfo.AR_ASPECT_FIT_PARENT;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set global configuration: turn on multiple_requests
        PlayerManager.getInstance().getDefaultVideoInfo().addOption(Option.create(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "multiple_requests", 1L));
//        PlayerManager.getInstance().getDefaultVideoInfo().addOptions(Option.preset4Realtime());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        $ = new ViewQuery(view);

        String testUrl = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";
//        testUrl = "file:///sdcard/tmp/o.mp4"; //test local file;
//        testUrl = "https://tungsten.aaplimg.com/VOD/bipbop_adv_example_v2/master.m3u8"; //test live stream;
//        testUrl = "http://playertest.longtailvideo.com/adaptive/oceans_aes/oceans_aes.m3u8"; //test live stream;
//        testUrl = "http://zhibo.hkstv.tv/livestream/zb2yhapo/playlist.m3u8"; //test live stream;
        //testUrl = "https://gcs-vimeo.akamaized.net/exp=1554301278~acl=%2A%2F1066768972.mp4%2A~hmac=2d71ae7eeb86b1c503b897bbdb6596c0eb6a966d2a3a424fcf6fa13fb2949583/vimeo-prod-skyfire-std-us/01/1730/11/283653955/1066768972.mp4"; //test live stream;
//        testUrl = "http://player.vimeo.com/external/283653955.sd.mp4?s=838d4ed86b8611781681c6cfb91d7c5af2604fd4&profile_id=165&oauth2_token_id=1197585134.mp4"; //test live stream;
        testUrl = "https://player.vimeo.com/external/283653955.sd.mp4?s=838d4ed86b8611781681c6cfb91d7c5af2604fd4&profile_id=164&oauth2_token_id=1197585134.mp4"; //test live stream;

        Uri myUri = Uri.parse(testUrl);
        Log.v("URL1" ,testUrl);
        Log.v("URL" ,myUri.toString());

//        final VideoView videoView = $.id(R.id.video_view).view();
        final VideoView videoView = view.findViewById(R.id.video_view);
        videoView.getVideoInfo().setPortraitWhenFullScreen(true);
        videoView.setVideoPath(testUrl);
        videoView.getPlayer().start();

        getVedio();

        $.id(R.id.et_url).text(testUrl);
        CheckBox cb = $.id(R.id.cb_pwf).view();
        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                videoView.getVideoInfo().setPortraitWhenFullScreen(isChecked);
            }
        });

        RadioGroup rb = $.id(R.id.rg_ra).view();
        rb.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (checkedId == R.id.rb_4_3) {
                    aspectRatio = VideoInfo.AR_4_3_FIT_PARENT;
                } else if (checkedId == R.id.rb_16_9) {
                    aspectRatio = VideoInfo.AR_16_9_FIT_PARENT;
                } else if (checkedId == R.id.rb_fill_parent) {
                    aspectRatio = VideoInfo.AR_ASPECT_FILL_PARENT;
                } else if (checkedId == R.id.rb_fit_parent) {
                    aspectRatio = VideoInfo.AR_ASPECT_FIT_PARENT;
                } else if (checkedId == R.id.rb_wrap_content) {
                    aspectRatio = VideoInfo.AR_ASPECT_WRAP_CONTENT;
                } else if (checkedId == R.id.rb_match_parent) {
                    aspectRatio = VideoInfo.AR_MATCH_PARENT;
                }
                videoView.getPlayer().aspectRatio(aspectRatio);

            }
        });



    }


    public void getVedio() {
        String accessToken = "7e9002e8904e541ff4651528ae3c0136";
        Map<String, String> headers = new HashMap<>();

        headers.put("Authorization", "Bearer " + accessToken);
        headers.put("Accept", "application/vnd.vimeo.*+json;version=3.2");
        String videoId = "330460188";//enter code here`
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<String> call = apiInterface.getPrivateVimeoVideo(headers,videoId);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
            }
        });
    }



}
