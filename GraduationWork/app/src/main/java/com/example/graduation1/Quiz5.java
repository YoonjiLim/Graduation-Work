package com.example.graduation1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Quiz5 extends AppCompatActivity {
    protected void onCreate( Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_quiz5);

        Button imageButton = (Button) findViewById(R.id.btn4);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Quiz6.class);
                startActivity(intent);
            }
        });
    }
}