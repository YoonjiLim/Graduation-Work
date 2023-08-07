package com.example.graduation1;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.util.ArrayList;
import java.util.List;

public class VideoMainActivity extends AppCompatActivity implements RecognitionListener {
    private String[][] mVideoUrls={

            {"가","/raw/go"},
            {"가격이","/raw/price"},
            {"가까운","/raw/near"},
            {"가능","/raw/cando"},
            {"가자","/raw/go"},
            {"가장","/raw/ffirst"},
            {"가지고","/raw/have"},
            {"감기","/raw/iicold"},
            {"감사합니다","/raw/thanks"},
            {"갔다","/raw/igo"},
            {"같이","/raw/together"},
            {"거절합니다","/raw/refusal"},
            {"걱정","/raw/worry"},
            {"걱정하지","/raw/worry"},
            {"걸렸어요","/raw/caught"},
            {"경찰서가","/raw/police"},
            {"계산할게요","/raw/calculation"},
            {"고장났다","/raw/bbreakdown"},
            {"곳이","/raw/place"},
            {"괜찮습니다","/raw/fine"},
            {"교환해","/raw/exchange"},
            {"구경할","/raw/ssightseeing"},
            {"그게","/raw/thisis"},
            {"그립다","/raw/miss"},
            {"기다려","/raw/wait"},
            {"길로","/raw/road"},
            {"깎아","/raw/discount"},
            {"꺼졌어요","/raw/trunoff"},

            {"나는","/raw/i"},
            {"남은","/raw/rremaining"},
            {"내일","/raw/ttomorrow"},
            {"너를","/raw/you"},

            {"대단하다","/raw/great"},
            {"도움이","/raw/help"},
            {"동의합니다","/raw/agree"},
            {"됐어요","/raw/become"},
            {"되나요","/raw/doubt"},
            {"되세요","/raw/doubt"},
            {"따라오세요","/raw/follow"},
            {"뜻","/raw/mean"},
            {"뜻인가요","/raw/mean"},

            {"마시러","/raw/drink"},
            {"만나요","/raw/meet"},
            {"맛있다","/raw/tasty"},
            {"맡길게요","/raw/takecare"},
            {"메뉴로","/raw/menu"},
            {"몇","/raw/few"},
            {"물","/raw/worter"},
            {"미안합니다","/raw/sorry"},

            {"방이","/raw/room"},
            {"배고픕니다","/raw/hungry"},
            {"배부릅니다","/raw/full"},
            {"버스정류장","/raw/busstop"},
            {"변기가","/raw/toilet"},
            {"병원이","/raw/hospital"},
            {"볼게요","/raw/tryit"},
            {"부탁드려요","/raw/please"},
            {"부탁합니다","/raw/please"},
            {"비밀번호","/raw/number"},
            {"빌어요","/raw/wish"},
            {"빠른","/raw/fast"},

            {"사랑해","/raw/love"},
            {"사진","/raw/picture"},
            {"생각","/raw/mind"},
            {"생각해","/raw/mind"},
            {"센터","/raw/center"},
            {"수건이","/raw/ttowel"},
            {"술","/raw/wine"},
            {"시간이","/raw/time"},
            {"시인가요","/raw/time"},
            {"식사는","/raw/meat"},
            {"싶습니다","/raw/want"},

            {"아니에요","/raw/not"},
            {"아침","/raw/morning"},
            {"아파요","/raw/pain"},
            {"안녕","/raw/hi"},
            {"알레르기를","/raw/aallergy"},
            {"알려","/raw/tellme"},
            {"약국이","/raw/pharmacy"},
            {"어디인가요","/raw/where"},
            {"어땠어","/raw/hhow"},
            {"어떤","/raw/hhow"},
            {"어떻게","/raw/hhowis"},
            {"언제","/raw/when"},
            {"언제인가요","/raw/when"},
            {"없어졌어요","/raw/disappear"},
            {"영업","/raw/sales"},
            {"예약을","/raw/rreservation"},
            {"오늘","/raw/today"},
            {"올게요","/raw/come"},
            {"와이파이","/raw/wifi"},
            {"유명한","/raw/popularity"},
            {"인가요","/raw/iisit"},
            {"있습니다","/raw/is"},

            {"잔돈은","/raw/change"},
            {"잘","/raw/good"},
            {"잤어요","/raw/sleep"},
            {"장소가","/raw/place"},
            {"재미있다","/raw/funny"},
            {"저녁","/raw/dinner"},
            {"저를","/raw/i"},
            {"점심","/raw/lunch"},
            {"정류장은","/raw/sstation"},
            {"정말","/raw/very"},
            {"조심하세요","/raw/careful"},
            {"조용히","/raw/queiet"},
            {"주문이","/raw/order"},
            {"주세요","/raw/givethem"},
            {"준비","/raw/ready"},
            {"지금","/raw/now"},
            {"질문","/raw/question"},
            {"짐이","/raw/baggage"},
            {"찍어요","/raw/shoot"},

            {"천천히","/raw/slowly"},
            {"추워요","/raw/cold"},
            {"축하합니다","/raw/ccongratulations"},
            {"취미가","/raw/hhobby"},

            {"카페","/raw/cafe"},

            {"택시","/raw/taxi"},

            {"파손됐어요","/raw/lostarticle"},
            {"포장해","/raw/ppackaging"},
            {"피곤합니다","/raw/tired"},
            {"필요합니다","/raw/need"},

            {"하고","/raw/and"},
            {"하루","/raw/day"},
            {"하지마세요","/raw/dont"},
            {"한국","/raw/korea"},
            {"한국에서","/raw/korea"},
            {"해라","/raw/doit"},
            {"해볼게요","/raw/tryit"},
            {"핸드폰이","/raw/phone"},
            {"행운을","/raw/luck"},
            {"화장실","/raw/wwashroom"},
            {"환불해","/raw/refund"},
            {"힘듭니다","/raw/hard"},
    };
    private static final int PERMISSION = 1;
    private ImageButton buttonSpeech;
    private Button[] buttons = new Button[10];
    private Button alter,connectionButton;
    private int buttonIndex = 0;
    private SpeechRecognizer speechRecognizer;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_activity_main);


        if ( Build.VERSION.SDK_INT >= 23 ){
            // 퍼미션 체크
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.INTERNET,
                    Manifest.permission.RECORD_AUDIO},PERMISSION);
        }

        buttonSpeech = findViewById(R.id.buttonSpeech);
        alter=findViewById(R.id.alter);
        connectionButton=findViewById(R.id.connectionButton);

        buttons[0] = findViewById(R.id.button1);
        buttons[1] = findViewById(R.id.button2);
        buttons[2] = findViewById(R.id.button3);
        buttons[3] = findViewById(R.id.button4);
        buttons[4] = findViewById(R.id.button5);
        buttons[5] = findViewById(R.id.button6);
        buttons[6] = findViewById(R.id.button7);
        buttons[7] = findViewById(R.id.button8);
        buttons[8] = findViewById(R.id.button9);
        buttons[9] = findViewById(R.id.button10);
        buttons[0].setVisibility(View.INVISIBLE);
        buttons[1].setVisibility(View.INVISIBLE);
        buttons[2].setVisibility(View.INVISIBLE);
        buttons[3].setVisibility(View.INVISIBLE);
        buttons[4].setVisibility(View.INVISIBLE);
        buttons[5].setVisibility(View.INVISIBLE);
        buttons[6].setVisibility(View.INVISIBLE);
        buttons[7].setVisibility(View.INVISIBLE);
        buttons[8].setVisibility(View.INVISIBLE);
        buttons[9].setVisibility(View.INVISIBLE);


        // Initialize SpeechRecognizer
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        speechRecognizer.setRecognitionListener(this);


        // Set onClickListener for buttonSpeech
        buttonSpeech.setOnClickListener(view -> {
            Toast.makeText(VideoMainActivity.this, "음성인식 중입니다.", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "ko-KR");
            speechRecognizer.startListening(intent);
        });
        for (int i = 0; i < buttons.length; i++) {
            final int index=i;
            buttons[i].setOnClickListener(v -> {
                String buttonWord=buttons[index].getText().toString();
                Intent intent=new Intent(VideoMainActivity.this,VideoActivity.class);
                intent.putExtra("word",buttonWord);
                intent.putExtra("list",mVideoUrls);
                startActivity(intent);
            });
        }
        connectionButton.setOnClickListener(v ->{
            List<String> wordList = new ArrayList<>();
            for(int i=0; i<buttons.length; i++){

                final int index=i;
                if(buttons[index].getText().toString() == ""){
                    break;
                }
                wordList.add(buttons[index].getText().toString());}
                String[] nword=wordList.toArray(new String[wordList.size()]);
                Intent intent=new Intent(VideoMainActivity.this,VideoActivity.class);
                intent.putExtra("wordList",nword);
                intent.putExtra("list",mVideoUrls);
                startActivity(intent);



});

        alter.setOnClickListener(view ->{
            StringBuilder sb=new StringBuilder();
            for(int i=0; i<mVideoUrls.length;i++){
                sb.append(mVideoUrls[i][0]+',');
            }
            String arrayString=sb.toString();
            AlertDialog.Builder builder=new AlertDialog.Builder(this);

            builder.setTitle("사용 가능한 단어").setMessage(arrayString);
            AlertDialog alertDialog=builder.create();
            alertDialog.show();
        });
    }





    @Override
    public void onReadyForSpeech(Bundle params) {
        // Do nothing
    }

    @Override
    public void onBeginningOfSpeech() {
        // Do nothing
    }

    @Override
    public void onRmsChanged(float rmsdB) {
        // Do nothing
    }

    @Override
    public void onBufferReceived(byte[] buffer) {
        // Do nothing
    }

    @Override
    public void onEndOfSpeech() {
        // Do nothing
    }

    @Override
    public void onError(int error) {
        String message;
        switch (error) {
            case SpeechRecognizer.ERROR_AUDIO:
                message = "오디오 에러 발생";
                break;
            case SpeechRecognizer.ERROR_CLIENT:
                message = "클라이언트 에러 발생";
                break;
            case SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS:
                message = "권한 부족 에러 발생";
                break;
            case SpeechRecognizer.ERROR_NETWORK:
                message = "네트워크 에러 발생";
                break;
            case SpeechRecognizer.ERROR_NETWORK_TIMEOUT:
                message = "네트워크 타임아웃 에러 발생";
                break;
            case SpeechRecognizer.ERROR_NO_MATCH:
                message = "일치하는 단어가 없습니다.";
                break;
            case SpeechRecognizer.ERROR_RECOGNIZER_BUSY:
                message = "음성인식이 바쁩니다.";
                break;
            case SpeechRecognizer.ERROR_SERVER:
                message = "서버 에러 발생";
                break;
            case SpeechRecognizer.ERROR_SPEECH_TIMEOUT:
                message = "음성인식 시간이 초과되었습니다.";
                break;
            default:
                message = "음성인식 중 에러 발생";
                break;
        }
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onResults(Bundle results) {
        ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        if (matches != null && matches.size() > 0) {
            String[] words = matches.get(0).split(" ");
            initializeButtons();
            for (int i = 0; i < words.length && buttonIndex < buttons.length; i++) {
                buttons[buttonIndex].setText(words[i]);
                buttons[buttonIndex].setEnabled(true);
                buttons[buttonIndex].setVisibility(View.VISIBLE);
                buttonIndex++;
            }
            buttonIndex=0;
        }
    }

    private void initializeButtons() {
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setText("");
            buttons[i].setEnabled(false);
            buttons[i].setVisibility(View.INVISIBLE);
        }
    }


    @Override
    public void onPartialResults(Bundle partialResults) {
        // Do nothing
    }

    @Override
    public void onEvent(int eventType, Bundle params) {
        // Do nothing
    }

}

