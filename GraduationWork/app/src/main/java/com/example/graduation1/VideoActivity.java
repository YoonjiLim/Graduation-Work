package com.example.graduation1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.Collator;
import java.util.Arrays;
import java.util.stream.Collectors;

public class VideoActivity extends AppCompatActivity {
    private ImageButton backButton;
    private VideoView mVideoView;
    private TextView wordText;



    @SuppressLint({"SetJavaScriptEnabled", "MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_activity);
        Intent intent=getIntent();
        String[][] mVideoUrls=(String[][]) intent.getSerializableExtra("list");
        String word = intent.getStringExtra("word");
        String[] wordList=(String[]) intent.getSerializableExtra("wordList");

        int left = 0;
        int right = mVideoUrls.length - 1;
        boolean found = false;
        Collator collator = Collator.getInstance();

        backButton=findViewById(R.id.back_Button);
        wordText=findViewById(R.id.wordText);


        mVideoView=findViewById(R.id.videoView);
        mVideoView.setMediaController(new MediaController(this));
        if (wordList!= null) {
            String TextWord= Arrays.toString(wordList);
            String result=TextWord.replace(","," ");
            wordText.setText(result);
            backButton.setOnClickListener(v -> finish());
            String[] videoList = new String[wordList.length];
            for (int i = 0; i < wordList.length; i++) {
                found=false;
                left=0;
                right=mVideoUrls.length-1;
                while (left <= right) {
                    int mid = (left + right) / 2;
                    int comparisonResult = collator.compare(mVideoUrls[mid][0], wordList[i]);
                    if (comparisonResult == 0) {
                        videoList[i] = mVideoUrls[mid][1];
                        found = true;
                        break;
                    } else if (comparisonResult > 0) {
                        right = mid - 1;
                    } else {
                        left = mid + 1;
                    }
                }
                if (!found) {
                    Toast.makeText(VideoActivity.this, "등록 되어 있지 않은 단어가 포함되어 있습니다.", Toast.LENGTH_SHORT).show();
                    finish();

                }

            }
            final int[] currentVideoIndex = {0};

            mVideoView.setOnCompletionListener(mediaPlayer -> {
                // 현재 재생된 비디오가 완료되었을 때 다음 비디오를 설정하고 재생합니다.
                currentVideoIndex[0]++;
                if (currentVideoIndex[0] < videoList.length) {
                    Uri videoUri = Uri.parse("android.resource://" + getPackageName() + videoList[currentVideoIndex[0]]);
                    mVideoView.setVideoURI(videoUri);
                    mVideoView.start();
                }
            });

// 첫 번째 비디오 설정 및 재생
            Uri firstVideoUri = Uri.parse("android.resource://" + getPackageName() + videoList[currentVideoIndex[0]]);
            mVideoView.setVideoURI(firstVideoUri);
            mVideoView.start();
        }
            else{
                wordText.setText(word);
                backButton.setOnClickListener(v -> finish());
                while (left <= right) {
                    int mid = (left + right) / 2;
                    int comparisonResult = collator.compare(mVideoUrls[mid][0], word);
                    if (comparisonResult == 0) {
                        String videoUrl = mVideoUrls[mid][1];
                        Uri videoUri = Uri.parse("android.resource://" + getPackageName() + videoUrl);
                        mVideoView.setVideoURI(videoUri);
                        mVideoView.start();
                        found = true;
                        break;
                    } else if (comparisonResult > 0) {
                        right = mid - 1;
                    } else {
                        left = mid + 1;
                    }
                }

                if (!found) {
                    Toast.makeText(VideoActivity.this, "등록 되어 있지 않은 단어입니다.", Toast.LENGTH_SHORT).show();
                }
            }
        }
}
