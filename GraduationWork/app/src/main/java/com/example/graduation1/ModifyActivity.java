package com.example.graduation1;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.bumptech.glide.Glide;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ModifyActivity extends AppCompatActivity {

    public static SQLiteHelper sqLiteHelper;
    String boardID;
    Button save_btn,cancel_btn;
    ImageView image_btn,back_btn;
    EditText title_text,content_text;
    private final int GET_GALLERY_IMAGE = 200;
    AlertDialog dialog;

    long mNow;
    Date mDate;
    SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);

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

        title_text = findViewById(R.id.title_text);
        content_text = findViewById(R.id.content_text);
        image_btn = findViewById(R.id.image_btn);
        image_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ActivityCompat.requestPermissions(
                        ModifyActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        GET_GALLERY_IMAGE
                );
            }
        });
        save_btn = findViewById(R.id.save_btn);
        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = title_text.getText().toString();
                String content = content_text.getText().toString();
                mNow = System.currentTimeMillis();
                mDate = new Date(mNow);
                String time = mFormat.format(mDate);
                if (title.equals("") || content.equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ModifyActivity.this);
                    dialog = builder.setMessage("빈 칸 없이 입력해주세요.")
                            .setNegativeButton("확인", null)
                            .create();
                    dialog.show();
                    return;

                }
                try {
                    sqLiteHelper.updateBoard(
                            imageViewToByte(image_btn),
                            title,
                            content,
                            time,
                            boardID

                    );
                    Toast.makeText(getApplicationContext(),"글이 수정되었습니다.",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(ModifyActivity.this, MainActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(i);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
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
                    .into(image_btn);


        }
    }

    public static byte[] imageViewToByte(ImageView imageView){
        Bitmap bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == GET_GALLERY_IMAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Select Image"), GET_GALLERY_IMAGE);
            } else {
                Toast.makeText(getApplicationContext(), "갤러리 권한을 허용해주세요", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == GET_GALLERY_IMAGE && resultCode == RESULT_OK ) {
            Uri imageUri = data.getData();
            image_btn.setImageURI(imageUri);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}