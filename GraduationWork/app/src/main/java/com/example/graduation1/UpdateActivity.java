package com.example.graduation1;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class UpdateActivity extends AppCompatActivity {

    public static SQLiteHelper sqLiteHelper;
    TextView title_text, content_text,re_text;
    ImageView imageView,back_btn;

    String boardID;

    FloatingActionButton modify_btn;

    ListView re_list;
    ArrayList<Re> list;
    ReAdapter adapter ;
    @Override
    protected void onResume(){ //생명주기 onResume()으로 화면이동시 데이터가 바뀌게끔함
        super.onResume();
        list = new ArrayList<>(); //리스트를 새로운 ArrayList로 생성
        adapter = new ReAdapter(this,R.layout.re_item,list); //어댑터를 diary_item의 뷰와 리스트를 연결
        re_list.setAdapter(adapter); //리스트뷰에 setAdapter를 함으로써 리스트가 보이게됨
        Cursor cursor = sqLiteHelper.getData("SELECT * FROM REW WHERE boardID = '"+boardID+"' ORDER BY time DESC"); //cursor를 통해 쿼리로 데이터를 가져올수 있음
        list.clear(); //리스트 초기화
        while (cursor.moveToNext()){ //다음 데이터가 없을때까지 반복문
            String id = cursor.getString(0);
            String user = cursor.getString(1);
            String content = cursor.getString(2);
            String time = cursor.getString(3);
            String boardID = cursor.getString(4);

            list.add(new Re(id,user,content,time,boardID)); //리스트에 데이터들을 담고 모델로 넘겨줌
        }
        adapter.notifyDataSetChanged(); //어댑터 동기화를 해줌으로써 바뀐 데이터를 리스트뷰에 알려줌
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        sqLiteHelper = new SQLiteHelper(this,"BOARD.sqlite",null,1);


        Intent intent = getIntent();
        boardID = intent.getStringExtra("boardID");
        re_list = findViewById(R.id.re_list);

        title_text = findViewById(R.id.title_text);
        content_text = findViewById(R.id.content_text);
        imageView = findViewById(R.id.image);
        back_btn = findViewById(R.id.back_btn);
        re_text = findViewById(R.id.re_text);
        re_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UpdateActivity.this,ReWriteActivity.class);
                intent.putExtra("boardID",boardID);
                startActivity(intent);
            }
        });
        modify_btn = findViewById(R.id.modify_btn);
        modify_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UpdateActivity.this,ModifyActivity.class);
                intent.putExtra("boardID",boardID);
                startActivity(intent);
            }
        });



        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Cursor cursor = sqLiteHelper.getData("SELECT * FROM BOARD WHERE id = '"+boardID+"'");

        while (cursor.moveToNext()){ //다음 데이터가 없을때까지 반복문

            int boardID = cursor.getInt(0);
            byte[] image = cursor.getBlob(1);
            String title = cursor.getString(2);
            String content = cursor.getString(3);
            String time = cursor.getString(4);

            title_text.setText(title);
            content_text.setText(content);
            Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
            Glide.with(this)
                    .load(bitmap)
                    .into(imageView);


        }

    }
}