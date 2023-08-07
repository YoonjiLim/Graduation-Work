package com.example.graduation1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Quiz8 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz8);

        Button imageButton = (Button) findViewById(R.id.btn5);

        imageButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), Quiz9.class);
                startActivity(intent);
            }
        });

        Button imageButton2 = (Button) findViewById(R.id.btn8);

        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Quiz5.class);
                startActivity(intent);
            }
        });
    }
}