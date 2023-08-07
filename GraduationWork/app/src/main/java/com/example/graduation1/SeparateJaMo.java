package com.example.graduation1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

//자모음 추출 코드
public class SeparateJaMo extends AppCompatActivity {
    TextView textOutput;
    static String temp;

    //초성, 중성, 종성의 배열
    final static String[] CHO = {"ㄱ", "ㄲ", "ㄴ", "ㄷ", "ㄸ", "ㄹ", "ㅁ",
            "ㅂ", "ㅃ", "ㅅ", "ㅆ", "ㅇ", "ㅈ", "ㅉ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ"};

    final static String[] JOONG = {"ㅏ", "ㅐ", "ㅑ", "ㅒ", "ㅓ",
            "ㅔ", "ㅕ", "ㅖ", "ㅗ", "ㅘ", "ㅙ", "ㅚ", "ㅛ", "ㅜ",
            "ㅝ", "ㅞ", "ㅟ", "ㅠ", "ㅡ", "ㅢ", "ㅣ"};

    final static String[] JONG = {"", "ㄱ", "ㄲ", "ㄳ", "ㄴ",
            "ㄵ", "ㄶ", "ㄷ", "ㄹ", "ㄺ", "ㄻ", "ㄼ", "ㄽ", "ㄾ", "ㄿ", "ㅀ",
            "ㅁ", "ㅂ", "ㅄ",  "ㅅ", "ㅆ", "ㅇ", "ㅈ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ"};


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jihwa_gridview);

        Intent receive_intent = getIntent();
        String temp = receive_intent.getStringExtra("textSend");
        textOutput = findViewById(R.id.textOutput);
        textOutput.setText(temp); //입력받은 내용 보여줌

        //자모 분리 시작
        String text = temp;
        List<String> sepList = new ArrayList<>();

        for (int a = 0; a < text.length(); a++) { //ex.졸:일때
            char uni = text.charAt(a); //a의 문자를 반환
            //한글일 경우 실행
            if (uni >= 0xAC00) {
                uni = (char) (uni - 0x0AC00);
                char cho = (char) (uni/28/21);     //ㅈ
                char joong = (char) ((uni)/28%21); //ㅗ
                char jong = (char) (uni%28);       //ㄹ
                sepList.add(CHO[cho]);
                sepList.add(JOONG[joong]);
                sepList.add(JONG[jong]);
            }
        }

        String[] sepArray = sepList.toArray(new String[0]); //리스트-> 배열 변환
        //지화 사진 이미지 파일들의 리소스 id 값의 배열(일단 리스트로 생성)
        GridView gridViewImg = findViewById(R.id.gridViewImg); //xml연결
        JihwaGridAdapter jihwaGridAdapter = new JihwaGridAdapter();

        //사진 for문-배열에 담긴 값 가져와서 지화 사진과 매칭
        for (int b = 0; b < sepArray.length; b++) {
            String textArray = sepArray[b];
            //배열에 있는 글자가 "( )"일 때 해당되는 사진을 배열에 넣어주기

            switch (textArray) {
                case "ㄱ":
                    //리스트에 이미지 리소스 id 추가
                    //new 클래스명
                    jihwaGridAdapter.addItem(new Jihwa("ㄱ", R.drawable.r));
                    break;
                case "ㄴ":
                    jihwaGridAdapter.addItem(new Jihwa("ㄴ", R.drawable.s));
                    break;
                case "ㄷ":
                    jihwaGridAdapter.addItem(new Jihwa("ㄷ", R.drawable.e));
                    break;
                case "ㄹ":
                    jihwaGridAdapter.addItem(new Jihwa("ㄹ", R.drawable.f));
                    break;
                case "ㅁ":
                    jihwaGridAdapter.addItem(new Jihwa("ㅁ", R.drawable.a));
                    break;
                case "ㅂ":
                    jihwaGridAdapter.addItem(new Jihwa("ㅂ", R.drawable.q));
                    break;
                case "ㅅ":
                    jihwaGridAdapter.addItem(new Jihwa("ㅅ", R.drawable.t));
                    break;
                case "ㅇ":
                    jihwaGridAdapter.addItem(new Jihwa("ㅇ", R.drawable.d));
                    break;
                case "ㅈ":
                    jihwaGridAdapter.addItem(new Jihwa("ㅈ", R.drawable.w));
                    break;
                case "ㅊ":
                    jihwaGridAdapter.addItem(new Jihwa("ㅊ", R.drawable.c));
                    break;
                case "ㅋ":
                    jihwaGridAdapter.addItem(new Jihwa("ㅋ", R.drawable.z));
                    break;
                case "ㅌ":
                    jihwaGridAdapter.addItem(new Jihwa("ㅌ", R.drawable.x));
                    break;
                case "ㅍ":
                    jihwaGridAdapter.addItem(new Jihwa("ㅍ", R.drawable.v));
                    break;
                case "ㅎ":
                    jihwaGridAdapter.addItem(new Jihwa("ㅎ", R.drawable.g));
                    break;
                case "ㄲ":
                    jihwaGridAdapter.addItem(new Jihwa("ㄲ", R.drawable.rr));
                    break;
                case "ㄸ":
                    jihwaGridAdapter.addItem(new Jihwa("ㄸ", R.drawable.ee));
                    break;
                case "ㅆ":
                    jihwaGridAdapter.addItem(new Jihwa("ㅆ", R.drawable.tt));
                    break;
                case "ㅃ":
                    jihwaGridAdapter.addItem(new Jihwa("ㅃ", R.drawable.qq));
                    break;
                case "ㅉ":
                    jihwaGridAdapter.addItem(new Jihwa("ㅉ", R.drawable.ww));
                    break;
                case "ㅏ":
                    jihwaGridAdapter.addItem(new Jihwa("ㅏ", R.drawable.k));
                    break;
                case "ㅑ":
                    jihwaGridAdapter.addItem(new Jihwa("ㅑ", R.drawable.i));
                    break;
                case "ㅓ":
                    jihwaGridAdapter.addItem(new Jihwa("ㅓ", R.drawable.j));
                    break;
                case "ㅕ":
                    jihwaGridAdapter.addItem(new Jihwa("ㅕ", R.drawable.u));
                    break;
                case "ㅗ":
                    jihwaGridAdapter.addItem(new Jihwa("ㅗ", R.drawable.h));
                    break;
                case "ㅛ":
                    jihwaGridAdapter.addItem(new Jihwa("ㅛ", R.drawable.y));
                    break;
                case "ㅜ":
                    jihwaGridAdapter.addItem(new Jihwa("ㅜ", R.drawable.n));
                    break;
                case "ㅠ":
                    jihwaGridAdapter.addItem(new Jihwa("ㅠ", R.drawable.b));
                    break;
                case "ㅡ":
                    jihwaGridAdapter.addItem(new Jihwa("ㅡ", R.drawable.m));
                    break;
                case "ㅣ":
                    jihwaGridAdapter.addItem(new Jihwa("ㅣ", R.drawable.l));
                    break;
                case "ㅐ":
                    jihwaGridAdapter.addItem(new Jihwa("ㅐ", R.drawable.o));
                    break;
                case "ㅒ":
                    jihwaGridAdapter.addItem(new Jihwa("ㅒ", R.drawable.oo));
                    break;
                case "ㅔ":
                    jihwaGridAdapter.addItem(new Jihwa("ㅔ", R.drawable.p));
                    break;
                case "ㅖ":
                    jihwaGridAdapter.addItem(new Jihwa("ㅖ", R.drawable.pp));
                    break;
                case "ㅢ":
                    jihwaGridAdapter.addItem(new Jihwa("ㅢ", R.drawable.ml));
                    break;
                case "ㅚ":
                    jihwaGridAdapter.addItem(new Jihwa("ㅚ", R.drawable.hl));
                    break;
                case "ㅟ":
                    jihwaGridAdapter.addItem(new Jihwa("ㅟ", R.drawable.nl));
                    break;
                case "ㅘ":
                    jihwaGridAdapter.addItem(new Jihwa("ㅘ", R.drawable.hk));
                    break;
                case "ㅙ":
                    jihwaGridAdapter.addItem(new Jihwa("ㅙ", R.drawable.ho));
                    break;
                case "ㅝ":
                    jihwaGridAdapter.addItem(new Jihwa("ㅝ", R.drawable.nj));
                    break;
                case "ㄳ":
                    jihwaGridAdapter.addItem(new Jihwa("ㄱ", R.drawable.r));
                    jihwaGridAdapter.addItem(new Jihwa("ㅅ", R.drawable.t));
                    break;
                case "ㄵ":
                    jihwaGridAdapter.addItem(new Jihwa("ㄴ", R.drawable.s));
                    jihwaGridAdapter.addItem(new Jihwa("ㅈ", R.drawable.w));
                    break;
                case "ㄶ":
                    jihwaGridAdapter.addItem(new Jihwa("ㄴ", R.drawable.s));
                    jihwaGridAdapter.addItem(new Jihwa("ㅎ", R.drawable.g));
                    break;
                case "ㄺ":
                    jihwaGridAdapter.addItem(new Jihwa("ㄹ", R.drawable.f));
                    jihwaGridAdapter.addItem(new Jihwa("ㄱ", R.drawable.r));
                    break;
                case "ㄻ":
                    jihwaGridAdapter.addItem(new Jihwa("ㄹ", R.drawable.f));
                    jihwaGridAdapter.addItem(new Jihwa("ㅁ", R.drawable.a));
                    break;
                case "ㄼ":
                    jihwaGridAdapter.addItem(new Jihwa("ㄹ", R.drawable.f));
                    jihwaGridAdapter.addItem(new Jihwa("ㅂ", R.drawable.q));
                    break;
                case "ㄽ":
                    jihwaGridAdapter.addItem(new Jihwa("ㄹ", R.drawable.f));
                    jihwaGridAdapter.addItem(new Jihwa("ㅅ", R.drawable.t));
                    break;
                case "ㄾ":
                    jihwaGridAdapter.addItem(new Jihwa("ㄹ", R.drawable.f));
                    jihwaGridAdapter.addItem(new Jihwa("ㅌ", R.drawable.x));
                    break;
                case "ㄿ":
                    jihwaGridAdapter.addItem(new Jihwa("ㄹ", R.drawable.f));
                    jihwaGridAdapter.addItem(new Jihwa("ㅍ", R.drawable.v));
                    break;
                case "ㅀ":
                    jihwaGridAdapter.addItem(new Jihwa("ㄹ", R.drawable.f));
                    jihwaGridAdapter.addItem(new Jihwa("ㅎ", R.drawable.g));
                    break;
                case "ㅄ":
                    jihwaGridAdapter.addItem(new Jihwa("ㅂ", R.drawable.q));
                    jihwaGridAdapter.addItem(new Jihwa("ㅅ", R.drawable.t));
            }

            gridViewImg.setAdapter(jihwaGridAdapter); //adapter 연결
        }

    }
}
