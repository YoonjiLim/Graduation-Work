package com.example.graduation1;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
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

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddActivity extends AppCompatActivity {

    public static SQLiteHelper sqLiteHelper;
    ImageView back_btn,image_btn;
    Button cancel_btn , save_btn;
    private final int GET_GALLERY_IMAGE = 200;
    EditText title_text,content_text;

    AlertDialog dialog;

    long mNow;
    Date mDate;
    SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        sqLiteHelper = new SQLiteHelper(this,"BOARD.sqlite",null,1);

        title_text = findViewById(R.id.title_text);
        content_text = findViewById(R.id.content_text);
        cancel_btn = findViewById(R.id.cancel_btn);
        save_btn = findViewById(R.id.save_btn);
        image_btn = findViewById(R.id.image_btn);
        image_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ActivityCompat.requestPermissions(
                        AddActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        GET_GALLERY_IMAGE
                );
            }
        });

        back_btn = findViewById(R.id.back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = title_text.getText().toString();
                String content = content_text.getText().toString();
                mNow = System.currentTimeMillis();
                mDate = new Date(mNow);
                String time = mFormat.format(mDate);
                if (title.equals("") || content.equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(AddActivity.this);
                    dialog = builder.setMessage("빈 칸 없이 입력해주세요.")
                            .setNegativeButton("확인", null)
                            .create();
                    dialog.show();
                    return;

                }
                try {
                    sqLiteHelper.insertBoard(
                            imageViewToByte(image_btn),
                            title,
                            content,
                            time

                    );
                    Toast.makeText(getApplicationContext(),"글이 등록되었습니다.",Toast.LENGTH_SHORT).show();
                    finish();
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
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
