package com.example.graduation1;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class BoardMainActivity extends AppCompatActivity {
    public static SQLiteHelper sqLiteHelper;
    FloatingActionButton add_btn;
    ListView board_list;
    ArrayList<Board> list;
    BoardAdapter adapter ;
    @Override
    protected void onResume(){ //생명주기 onResume()으로 화면이동시 데이터가 바뀌게끔함
        super.onResume();
        list = new ArrayList<>(); //리스트를 새로운 ArrayList로 생성
        adapter = new BoardAdapter(this,R.layout.board_item,list); //어댑터를 diary_item의 뷰와 리스트를 연결
        board_list.setAdapter(adapter); //리스트뷰에 setAdapter를 함으로써 리스트가 보이게됨
        updateBoardList(); //다이어리 리스트를 가져오는 메소드
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.board_activity_main);


        sqLiteHelper = new SQLiteHelper(this,"BOARD.sqlite",null,1);
        sqLiteHelper.queryData("CREATE TABLE IF NOT EXISTS BOARD(id INTEGER PRIMARY KEY AUTOINCREMENT, image BLOB, title VARCHAR , content VARCHAR, time VARCHAR)");
        sqLiteHelper.queryData("CREATE TABLE IF NOT EXISTS REW(id INTEGER PRIMARY KEY AUTOINCREMENT, user VARCHAR, content VARCHAR , time VARCHAR , boardID VARCHAR)");


        board_list =(ListView)findViewById(R.id.board_list); //리스트 아이디 연결
        list = new ArrayList<>();
        adapter = new BoardAdapter(this,R.layout.board_item,list);
        board_list.setAdapter(adapter); //위와 동일
        board_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int boardID = list.get(i).getId();
                Intent intent = new Intent(BoardMainActivity.this,UpdateActivity.class);
                intent.putExtra("boardID",String.valueOf(boardID));
                startActivity(intent);
            }
        });
        board_list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                int boardID = list.get(i).getId();
                showDialogDelete(boardID);
                return true;
            }
        });

        add_btn = findViewById(R.id.add_btn);
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BoardMainActivity.this,AddActivity.class);
                startActivity(intent);
            }
        });
    }

    private void updateBoardList() { //DB로부터 데이터를 가져오는 메소드
        Cursor cursor = sqLiteHelper.getData("SELECT * FROM BOARD ORDER BY time DESC"); //cursor를 통해 쿼리로 데이터를 가져올수 있음
        list.clear(); //리스트 초기화
        while (cursor.moveToNext()){ //다음 데이터가 없을때까지 반복문

            int boardID = cursor.getInt(0);
            byte[] image = cursor.getBlob(1);
            String title = cursor.getString(2);
            String content = cursor.getString(3);
            String time = cursor.getString(4);

            list.add(new Board(boardID,image,title,content,time)); //리스트에 데이터들을 담고 모델로 넘겨줌
        }
        adapter.notifyDataSetChanged(); //어댑터 동기화를 해줌으로써 바뀐 데이터를 리스트뷰에 알려줌
    }

    private void showDialogDelete(final int id){
        AlertDialog.Builder dialog = new AlertDialog.Builder(BoardMainActivity.this);
        dialog.setTitle("삭제");
        dialog.setMessage("해당 글을 삭제하시겠습니까?");
        dialog.setPositiveButton("네", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                try {
                    sqLiteHelper.deleteBoard(id);
                    Toast.makeText(getApplicationContext(),"삭제되었습니다",Toast.LENGTH_SHORT).show();
                }
                catch (Exception e){
                    Log.e("error", e.getMessage());
                }
                updateBoardList();
            }
        });
        dialog.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        dialog.show();
    }
}