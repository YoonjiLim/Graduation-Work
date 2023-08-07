package com.example.graduation1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        //카드뷰에 클릭 리스너 설정
        CardView jihwaCardView = findViewById(R.id.jihwaCardView);
        jihwaCardView.setOnClickListener(this);

        CardView videoCardView = findViewById(R.id.videoCardView);
        videoCardView.setOnClickListener(v -> onVideoClick());

        CardView gameCardView = findViewById(R.id.gameCardView);
        gameCardView.setOnClickListener(v -> onGameClick());

        CardView boardCardView = findViewById(R.id.boardCardView);
        boardCardView.setOnClickListener(v -> onBoardClick());

        CardView centerCardView = findViewById(R.id.centerCardView);
        centerCardView.setOnClickListener(v -> onCenterClick());

        CardView dicCardView = findViewById(R.id.dicCardView);
        dicCardView.setOnClickListener(v -> onDicClick());


    }

    @Override
    public void onClick(View v) {
        Intent intentJihwa = new Intent(this, JihwaMainActivity.class);
        startActivity(intentJihwa);
    }

    public void onVideoClick() {
        Intent intentVideo = new Intent(this, VideoMainActivity.class);
        startActivity(intentVideo);
    }

    public void onGameClick() {
        Intent intentGame = new Intent(this, Quiz_main.class);
        startActivity(intentGame);
    }

    public void onBoardClick() {
        Intent intentBoard = new Intent(this, BoardMainActivity.class);
        startActivity(intentBoard);
    }

    public void onCenterClick() {
        Intent intentCenter = new Intent(this, CenterActivity.class);
        startActivity(intentCenter);
    }

    public void onDicClick() {
        Uri webpage = Uri.parse("https://sldict.korean.go.kr/front/main/main.do");
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        startActivity(intent);
    }

}
