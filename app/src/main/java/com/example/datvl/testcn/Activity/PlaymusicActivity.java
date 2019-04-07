package com.example.datvl.testcn.Activity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;


import com.example.datvl.testcn.Adapter.Viewpager_Playmusic;
import com.example.datvl.testcn.Fragment.Fragment_Dianhac;
import com.example.datvl.testcn.Fragment.Fragment_Playmusic;
import com.example.datvl.testcn.Model.Baihat;
import com.example.datvl.testcn.R;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

public class PlaymusicActivity extends AppCompatActivity {
    Toolbar toolbarplaymusic;
    TextView timesong, sumtimesong;
    SeekBar   sktime;
    ImageButton btplay,btrepaeat,btrandom,btnext,btpreview;
    ViewPager viewPagerplaymusic;
    public static ArrayList<Baihat> mangbaihat = new ArrayList<>();
    public static Viewpager_Playmusic viewpager_playmusic;
    Fragment_Playmusic fragment_playmusic;
    Fragment_Dianhac fragment_dianhac;
    MediaPlayer mediaPlayer;
    int position = 0;
    boolean repeat = false;
    boolean checkrandom = false;
    boolean nextbh = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playmusic);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        anhxa();
        Getintent();
        init();
        evenclick();

    }

    private void init() {
        setSupportActionBar(toolbarplaymusic);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Play Music");
        toolbarplaymusic.setTitleTextColor(getResources().getColor(R.color.mautim));
        toolbarplaymusic.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                // if(mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
                mangbaihat.clear();
                // }
            }
        });

        fragment_dianhac = new Fragment_Dianhac();
        fragment_playmusic = new Fragment_Playmusic();
        viewpager_playmusic = new Viewpager_Playmusic(getSupportFragmentManager());
        viewpager_playmusic.addfragment(fragment_playmusic);
        viewpager_playmusic.addfragment(fragment_dianhac);
        viewPagerplaymusic.setAdapter(viewpager_playmusic);

        fragment_dianhac = (Fragment_Dianhac) viewpager_playmusic.getItem(1);
        if(mangbaihat.size() > 0 ){
            getSupportActionBar().setTitle(mangbaihat.get(0).getTenbaihat());
            new Playmp3().execute(mangbaihat.get(0).getLinkbaihat());
            btplay.setImageResource(R.drawable.iconpause);

        }
    }

    private void evenclick() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(viewpager_playmusic.getItem(1) != null){
                    if(mangbaihat.size() > 0){
                        fragment_dianhac.Playnhac(mangbaihat.get(0).getHinhbaihat());
                        handler.removeCallbacks(this);
                    }else {
                        handler.postDelayed(this,300);
                    }
                }
            }
        },500);

        btplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    btplay.setImageResource(R.drawable.iconplay);

                }else {
                    mediaPlayer.start();
                    btplay.setImageResource(R.drawable.iconpause);

                }
            }
        });

        btrepaeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(repeat == false){
                    if(checkrandom == true){
                        checkrandom = false;
                        btrepaeat.setImageResource(R.drawable.iconsyned);
                        btrandom.setImageResource(R.drawable.iconsuffle);
                    }
                    btrepaeat.setImageResource(R.drawable.iconsyned);
                    repeat = true;

                }else {
                    btrepaeat.setImageResource(R.drawable.iconrepeat);
                    repeat = false;
                }
            }
        });

        btrandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkrandom == false){
                    if(repeat == true){
                        repeat = false;
                        btrandom.setImageResource(R.drawable.iconshuffled);
                        btrepaeat.setImageResource(R.drawable.iconrepeat);
                    }
                    btrandom.setImageResource(R.drawable.iconshuffled);
                    repeat = true;

                }else {
                    btrandom.setImageResource(R.drawable.iconsuffle);
                    checkrandom  = false;
                }
            }
        });

        sktime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });

        btnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mangbaihat.size() > 0){
                    if(mediaPlayer.isPlaying() || mediaPlayer != null){
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                        if(position < mangbaihat.size()){
                        btplay.setImageResource(R.drawable.iconpause);
                        position ++;
                        if(repeat == true){
                            if(position == 0){
                                position = mangbaihat.size();
                            }
                            position -= 1;
                        }
                        if(checkrandom == true){
                            Random random = new Random();
                            int index = random.nextInt(mangbaihat.size());
                            if(index == position){
                                position = index - 1;
                            }
                            position = index;
                        }
                        if(position > (mangbaihat.size() - 1)){
                            position = 0;
                        }

                        new  Playmp3().execute(mangbaihat.get(position).getLinkbaihat() );
                        fragment_dianhac.Playnhac(mangbaihat.get(position).getHinhbaihat());
                        getSupportActionBar().setTitle(mangbaihat.get(position).getTenbaihat());
                        updateTimeSk();

                    }

                }

                btpreview.setClickable(false);
                btnext.setClickable(false);
                Handler handler1 = new  Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btpreview.setClickable(true);
                        btnext.setClickable(true);
                    }
                },5000);

            }
        });

        btpreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mangbaihat.size() > 0){
                    if(mediaPlayer.isPlaying() || mediaPlayer != null){
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if(position < mangbaihat.size()){
                        btplay.setImageResource(R.drawable.iconpause);
                        position --;
                        if(position < 0){
                            position = mangbaihat.size() - 1;
                        }
                        if(repeat == true){
                            position += 1;
                        }
                        if(checkrandom == true){
                            Random random = new Random();
                            int index = random.nextInt(mangbaihat.size());
                            if(index == position){
                                position = index - 1;
                            }
                            position = index;
                        }

                        new  Playmp3().execute(mangbaihat.get(position).getLinkbaihat() );
                        fragment_dianhac.Playnhac(mangbaihat.get(position).getHinhbaihat());
                        getSupportActionBar().setTitle(mangbaihat.get(position).getTenbaihat());
                        updateTimeSk();

                    }

                }

                btpreview.setClickable(false);
                btnext.setClickable(false);
                Handler handler1 = new  Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btpreview.setClickable(true);
                        btnext.setClickable(true);
                    }
                },5000);
            }
        });
    }

    private void Getintent() {

        Intent intent =getIntent();
        mangbaihat.clear();
        if(intent != null){
            if(intent.hasExtra("cakhuc")){
                Baihat baihat = intent.getParcelableExtra("cakhuc");
                mangbaihat.add(baihat);
            }

            if(intent.hasExtra("allbaihat")){
                ArrayList<Baihat> arrbaihat = intent.getParcelableArrayListExtra("allbaihat");
                mangbaihat = arrbaihat;
            }
        }

    }

    private void anhxa() {
        toolbarplaymusic = findViewById(R.id.tbplaymusic);
        timesong         = findViewById(R.id.tvtimebaihatplaymusic);
        sumtimesong      = findViewById(R.id.tvsumtimebaihat);
        sktime           = findViewById(R.id.sbbaihatplaymusic);
        btplay             = findViewById(R.id.ibpalyplaymusic);
        btrepaeat          = findViewById(R.id.ibrepeatplaymusic);
        btrandom           = findViewById(R.id.ibsuffleplaymusic);
        btnext             = findViewById(R.id.ibnextplaymusic);
        btpreview          = findViewById(R.id.ibpreviewplaymusic);
        viewPagerplaymusic = findViewById(R.id.vpplaymusic);


    }
    class Playmp3 extends AsyncTask<String, Void, String>{


        @Override
        protected String doInBackground(String... strings) {
            return strings[0];
        }

        @Override
        protected void onPostExecute(String baihat) {
            super.onPostExecute(baihat);
            try {

            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mediaPlayer.stop();
                    mediaPlayer.reset();
                }
            });

            mediaPlayer.setDataSource(baihat);
            mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }

            mediaPlayer.start();
            TimeSong();
            updateTimeSk();
        }
    }

    private void TimeSong() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        sumtimesong.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        sktime.setMax(mediaPlayer.getDuration());

    }

    private void updateTimeSk(){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(mediaPlayer != null){
                    sktime.setProgress(mediaPlayer.getCurrentPosition());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                    timesong.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
                    handler.postDelayed(this,300);
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            nextbh = true;
                            try {
                                Thread.sleep(10000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        },300);

        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(nextbh == true){
                    if(position < mangbaihat.size()){
                        btplay.setImageResource(R.drawable.iconpause);
                        position ++;
                        if(repeat == true){
                            if(position == 0){
                                position = mangbaihat.size();
                            }
                            position -= 1;
                        }
                        if(checkrandom == true){
                            Random random = new Random();
                            int index = random.nextInt(mangbaihat.size());
                            if(index == position){
                                position = index - 1;
                            }
                            position = index;
                        }
                        if(position > (mangbaihat.size() - 1)){
                            position = 0;
                        }

                        new  Playmp3().execute(mangbaihat.get(position).getLinkbaihat() );
                        fragment_dianhac.Playnhac(mangbaihat.get(position).getHinhbaihat());
                        getSupportActionBar().setTitle(mangbaihat.get(position).getTenbaihat());

                    }


                    btpreview.setClickable(false);
                    btnext.setClickable(false);
                    Handler handler1 = new  Handler();
                    handler1.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            btpreview.setClickable(true);
                            btnext.setClickable(true);
                        }
                    },5000);

                    nextbh = false;
                    handler1.removeCallbacks(this);

                    }else {
                        handler1.postDelayed(this,1000);
                    }
                }
        },1000);
    }


}
