package com.example.chesminnguyen.app_edm_music.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chesminnguyen.app_edm_music.Model.BaiHat;
import com.example.chesminnguyen.app_edm_music.R;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.text.SimpleDateFormat;

import de.hdodenhof.circleimageview.CircleImageView;

public class DiaNhacActivity extends AppCompatActivity {

    CircleImageView circleImageView;
    BaiHat baiHat;
    public ObjectAnimator objectAnimator;
    TextView txtTimesong,txttotaltimesong;
    Toolbar  toolbar;
    SeekBar seekBarTime;
    ImageButton imageButtonnext,imageButtonpreview,imageButtonlisten,imageButtonrepeat,imageButtonrandom;
    MediaPlayer mediaPlayer;
    String uRL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dia_nhac);
        getDataIntent();
        AnhXa();
        GetXuLyDiaChay();
        KhoiTaoNhac(uRL);

        imageButtonlisten.setImageResource(R.drawable.iconpause);

        imageButtonlisten.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {


                if(mediaPlayer.isPlaying())
                {
                    mediaPlayer.pause();
                    imageButtonlisten.setImageResource(R.drawable.iconplay);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        objectAnimator.pause();
                    }
                }
                else {
                    mediaPlayer.start();
                    imageButtonlisten.setImageResource(R.drawable.iconpause);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        objectAnimator.resume();

                    }
                    TimeSong();
                    UpdateTimeSong();
                }

            }
        });
        imageButtonrepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageButtonrepeat.setImageResource(R.drawable.iconsyned);
                if(mediaPlayer.isPlaying())
                {

                }

            }
        });
        seekBarTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBarTime.getProgress());
            }
        });
    }
//    @SuppressWarnings("deprecation")
//class  PlayMP3 extends AsyncTask<String, Void, String>
//{
//
//    @Override
//    protected String doInBackground(String... strings) {
//        return strings[0];
//    }

   // @Override
//    protected void onPostExecute(String baihat) {
//
//        super.onPostExecute(baihat);
//
//            mediaPlayer= new MediaPlayer();
//            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);//nghe dưới dạng online
//            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//                @Override
//                public void onCompletion(MediaPlayer mp) {
//                    mediaPlayer.stop();
//                    mediaPlayer.reset();
//                }
//            });
//        try{
//            mediaPlayer.setDataSource(baihat);
//            mediaPlayer.prepare();
//         } catch (
//            IOException e) {
//            e.printStackTrace();
//        }
//                    mediaPlayer.start();
//        TimeSong();
//    }
//}
    private void GetXuLyDiaChay() {

        objectAnimator= ObjectAnimator.ofFloat(circleImageView,"rotation",0f,360f);
        objectAnimator.setDuration(10000);
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        objectAnimator.setRepeatMode(ValueAnimator.RESTART);
        //Tránh tình trạng khi 1 dòng play đĩa nhạc lại dừng
        objectAnimator.setInterpolator( new LinearInterpolator());
        objectAnimator.start();
        Picasso.with(DiaNhacActivity.this).load(baiHat.getHinhBaiHat()).into(circleImageView);
    }

    private void getDataIntent()
        {
            Intent intent= getIntent();
            if(intent!=null)
            {
                if(intent.hasExtra("idbaihatdianhac"))
                {
                    baiHat= (BaiHat) intent.getParcelableExtra("idbaihatdianhac");
                   Log.d("QQQQ",""+baiHat.getLinkBaiHat());

                   uRL=baiHat.getLinkBaiHat();
                }
            }
        }
    private void KhoiTaoNhac(String url) {
       // String url =baiHat.getLinkBaiHat();
        try {
            mediaPlayer= new MediaPlayer();
            mediaPlayer.setDataSource(url);
            mediaPlayer.prepare();

            mediaPlayer.start();
            TimeSong();
            UpdateTimeSong();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //new PlayMP3().execute(baiHat.getLinkBaiHat());

    }
    private void UpdateTimeSong()
    {
        final Handler handler= new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                        if(mediaPlayer !=null)
                        {
                            seekBarTime.setProgress(mediaPlayer.getCurrentPosition());
                            SimpleDateFormat simpledinhDangGio= new SimpleDateFormat("mm:ss");
                            txtTimesong.setText(simpledinhDangGio.format(mediaPlayer.getCurrentPosition()));
                            handler.postDelayed(this,200);
                            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    try {
                                        Thread.sleep(1000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                        }
                    }

        },100);

    }
    private void TimeSong() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        txttotaltimesong.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        seekBarTime.setProgress(mediaPlayer.getCurrentPosition());
        seekBarTime.setMax(mediaPlayer.getDuration());
    }

    @SuppressLint("ResourceAsColor")
    private void AnhXa() {
        circleImageView=(CircleImageView)findViewById(R.id.imageviewCircle);
        txtTimesong=(TextView)findViewById(R.id.textviewtimesong);
        txttotaltimesong=(TextView)findViewById(R.id.textviewtotaltimesong);
        seekBarTime=(SeekBar)findViewById(R.id.seekbarsong);
        imageButtonlisten=(ImageButton)findViewById(R.id.imagebuttonplay);
        imageButtonnext=(ImageButton)findViewById(R.id.imagebuttonnext);
        imageButtonpreview=(ImageButton)findViewById(R.id.imagebuttonpreview);
        imageButtonrepeat=(ImageButton)findViewById(R.id.imagebuttonrepeat);
        imageButtonrandom=(ImageButton)findViewById(R.id.imagebuttonsuffle);

        toolbar=(Toolbar)findViewById(R.id.toolbardia_nhac);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitleTextColor(R.color.mautimden);
        getSupportActionBar().setTitle(baiHat.getTenBaiHat());

        Toast.makeText(DiaNhacActivity.this,""+baiHat.getTenBaiHat(),Toast.LENGTH_SHORT).show();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                mediaPlayer.stop();
            }
        });
    }

}
