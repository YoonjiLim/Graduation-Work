package com.example.graduation1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class JihwaMainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText textInput;
    TextView textOutput;
    Button btnPrint, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jihwa_activity_main);

        //텍스트 입력받는 부분
        textInput = (EditText) findViewById(R.id.textInput);
        btnPrint = (Button) findViewById(R.id.btnPrint);
        btnCancel = (Button) findViewById(R.id.btnCancel);

        btnPrint.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    //버튼 클릭 이벤트 처리
    //입력값을 문자열로 변환해서 보낼 데이터인 temp에 저장
    //textSend라는 이름으로 temp를 보냄
    @Override
    public void onClick(View v){
        if(v.getId() == R.id.btnPrint){
            String temp = textInput.getText().toString();
            Intent intent1 = new Intent(this, SeparateJaMo.class);
            intent1.putExtra("textSend", temp);
            startActivity(intent1);
        }
        if(v.getId() == R.id.btnCancel){
            finish();
        }
    }
}