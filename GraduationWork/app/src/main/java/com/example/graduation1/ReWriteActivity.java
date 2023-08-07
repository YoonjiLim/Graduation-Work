package com.example.graduation1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class ReWriteActivity extends AppCompatActivity {

    public static SQLiteHelper sqLiteHelper;
    ImageView back_btn;
    Button cancel_btn , save_btn;
    EditText content_text;
    AlertDialog dialog;

    String boardID;
    long mNow;
    Date mDate;
    SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_re_write);


        sqLiteHelper = new SQLiteHelper(this,"BOARD.sqlite",null,1);

        Intent intent = getIntent();
        boardID = intent.getStringExtra("boardID");

        back_btn = findViewById(R.id.back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        cancel_btn = findViewById(R.id.cancel_btn);
        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        save_btn = findViewById(R.id.save_btn);
        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int min = 10000;
                int max = 99999;
                int randumNum = new Random().nextInt(max - min + 1) + 1;
                String user = "익명"+ randumNum;
                String content = content_text.getText().toString();
                mNow = System.currentTimeMillis();
                mDate = new Date(mNow);
                String time = mFormat.format(mDate);
                if ( content.equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ReWriteActivity.this);
                    dialog = builder.setMessage("빈 칸 없이 입력해주세요.")
                            .setNegativeButton("확인", null)
                            .create();
                    dialog.show();
                    return;

                }
                try {
                    sqLiteHelper.insertRew(
                            user,
                            content,
                            time,
                            boardID


                    );
                    Toast.makeText(getApplicationContext(),"댓글이 등록되었습니다.",Toast.LENGTH_SHORT).show();
                    finish();
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        content_text = findViewById(R.id.content_text);
    }
}
