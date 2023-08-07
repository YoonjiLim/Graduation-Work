package com.example.graduation1;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import android.Manifest;


public class CenterActivity extends AppCompatActivity {
    private static final int REQUEST_CALL_PERMISSION = 1;
    private RecyclerView recyclerView;
    private CustomAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Contact> contactList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.center_activity);

        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // 데이터 추가
        contactList = new ArrayList<>();
        contactList.add(new Contact("서울","서울시수화통역센터지역지원본부", "서울 서대문구 경기대로 15 3층", "02-323-4996"));
        contactList.add(new Contact("서울","서울특별시농아인협회 노원구지부", "서울 노원구 상계로23길 17 원터행복발전소 2층", "02-931-6103"));
        contactList.add(new Contact("서울","종로구 수어통역센터", "서울 종로구 종로17길 8 3층", "02-730-5563"));
        contactList.add(new Contact("서울","구로구수어통역센터", "서울 구로구 함동로3길 4 7층", "02-856-1077"));
        contactList.add(new Contact("서울","도봉구수어통역센터", "서울 도봉구 마들로13길 153 옥산빌딩 4층", "02-900-2331"));
        contactList.add(new Contact("서울","서울농아인협회 영등포구지회 부설 수어통역센터", "서울 영등포구 경인로112길 13 2층", "02-6404-4291"));
        contactList.add(new Contact("서울","중랑구수어통역센터", "서울 중랑구 면목로 299 6,7층", "02-2208-1083"));
        contactList.add(new Contact("서울","강서구수어통역센터", "서울 강서구 발산로 40 서울농수산식품공사 강서지사 관리동 419호", "0507-1312-4474"));
        contactList.add(new Contact("서울","강북구수어통역센터", "서울 강북구 도봉로 388 5층", "02-990-0872"));
        contactList.add(new Contact("서울","은평구수어통역센터", "서울 은평구 증산서길 82 2층 은평구수어통역센터", "02-358-7051"));
        contactList.add(new Contact("서울","동대문구수어통역센터 농아인쉼터", "서울 동대문구 청계천로 521 다사랑행복센터 8층", "02-962-5798"));
        contactList.add(new Contact("서울","광진구수어통역센터", "서울 광진구 아차산로 417 3층", "02-454-3888"));
        contactList.add(new Contact("서울","성동구수어통역센터", "서울 성동구 아차산로 33 8층 성동구수어통역센터", "02-2299-3338"));
        contactList.add(new Contact("서울","마포구농아인협회 수어통역센터", "서울 마포구 월드컵로 213 마포장애인복지회관 3층", "02-707-1062"));
        contactList.add(new Contact("서울","용산구수어통역센터", "서울 용산구 서빙고로 245 4층", "02-706-8367"));
        contactList.add(new Contact("부산","동부산수어통역센터", "부산 금정구 금정로161번길 9", "051-513-6340"));
        contactList.add(new Contact("부산","부산광역시농아인협회 사하구지회", "부산 사하구 낙동남로 1400 정우헤리티지 4층 401호", "051-203-2006"));
        contactList.add(new Contact("대전","대덕구수어통역센터", "대전 대덕구 비래동로7번길 54", "042-345-0032"));
        contactList.add(new Contact("경기","고양시수어통역센터", "경기 고양시 일산서구 일현로 97-11", "031-963-0797"));
        contactList.add(new Contact("경기","한국농아인협회 경기도협회 하남시지회", "경기 하남시 검단로19번길 27 하남시다목적복지회관 2층", "031-791-5513"));
        contactList.add(new Contact("경기","남양주시수어통역센터", "경기 남양주시 금곡로 58", "031-575-5647"));
        contactList.add(new Contact("경기","파주시수화통역센터 파주시지회", "경기 파주시 시청로 25 광우프라자", "031-945-3351"));
        contactList.add(new Contact("강원","강릉시수어통역센터", "강원 강릉시 용지각길 20 2층 201호 강릉시수어통역센터", "033-642-0432"));
        contactList.add(new Contact("강원","정선군수화통역센터", "강원 정선군 정선읍 녹송4길 71", "033-563-0609"));
        contactList.add(new Contact("강원","홍천군수어통역센터", "강원 홍천군 홍천읍 희망로 55 1층 홍천군수어통역센터", "033-432-1961"));
        contactList.add(new Contact("충북","단양군수어통역센터", "충북 단양군 단양읍 별곡5길 5 단양군장애인복지관 3층", "043-421-8385"));
        contactList.add(new Contact("충남","충남농아인협회 아산시지부 수화통역센터", "충남 아산시 탕정면 선문로 217-2 3층", "041-544-8961"));
        contactList.add(new Contact("충남","부여군수어통역센터", "충남 부여군 규암면 흥수로 816 부여군장애인재활센터 1층", "0507-1481-8345"));
        contactList.add(new Contact("충남","논산시수어통역센터", "충남 논산시 관촉로277번길 23-13", "041-736-4749"));
        contactList.add(new Contact("충남","보령시수어통역센터", "충남 보령시 천변북길 121", "041-936-6658"));
        contactList.add(new Contact("전남","광양수화통역센터", "전남 광양시 광장로 1", "061-793-3114"));
        contactList.add(new Contact("전남","전남수어교육원", "전남 목포시 용당로 281", "061-272-2165"));
        contactList.add(new Contact("경북","한국농아인협회 경상북도협회 김천시지회", "경북 김천시 중앙공원4길 33 2층", "054-434-6181"));
        contactList.add(new Contact("경북","한국농아인협회 경상북도협회 구미시지회", "경북 구미시 송원서로6길 20-5", "054-451-9226"));
        contactList.add(new Contact("경북","고령군수어통역센터", "경북 고령군 대가야읍 연조길 39", "054-956-5592"));
        contactList.add(new Contact("경남","거제시수어통역센터", "경남 거제시 거제중앙로 1849 404호", "055-633-5988"));
        contactList.add(new Contact("경남","양산시수어통역센터", "경남 양산시 북안남5길 15", "055-363-8711"));
        contactList.add(new Contact("경남","창원시창원수어통역센터", "경남 창원시 성산구 용지로 90 4층 34호", "055-275-7418"));
        contactList.add(new Contact("경남","밀양시수어통역센터", "경남 밀양시 가곡14길 22 3층", "055-356-7154"));
        contactList.add(new Contact("경남","사천시수어통역센터", "경남 사천시 노대길 178 2층", "055-833-2826"));
        contactList.add(new Contact("경남","통영시수어통역센터", "경남 통영시 용남면 기호바깥길 7-89 통영시장애인종합복지관 3층", "055-649-4787"));
        contactList.add(new Contact("경남","김해시수어통역센터", "경남 김해시 인제로 258", "070-7947-0203"));
        contactList.add(new Contact("경남","경남농아인협화 진주시지회", "경남 진주시 신안로100번길 11-5 2층", "070-7947-0202"));
        contactList.add(new Contact("경남","진주시수어통역센터", "경남 진주시 신안로100번길 11-5 2층", "055-742-4873"));
        contactList.add(new Contact("경남","경남농아인협회 양산시지회", "경남 양산시 북안남5길 15", "055-388-8722"));
        contactList.add(new Contact("경남","경남농아인협회 밀양시지회", "경남 밀양시 가곡14길 22 밀양시장애인복지관 3층", "055-356-7154"));
        adapter = new CustomAdapter(contactList);
        recyclerView.setAdapter(adapter);
        adapter.setOnCallButtonClickListener(phoneNumber -> {
            // 전화 걸기 권한을 확인합니다.
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                // 권한이 허용된 경우 전화를 거둡니다.
                makePhoneCall(phoneNumber);
            } else {
                // 권한이 없는 경우 권한을 요청합니다.
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL_PERMISSION);
            }
        });










    }

    private void makePhoneCall(String phoneNumber) {
        // 전화를 거는 코드를 구현합니다.
        // 필요한 권한 등을 처리해야 할 수도 있습니다.
        // 예를 들어, 다음과 같이 Intent를 사용하여 전화 앱을 실행할 수 있습니다.
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        startActivity(intent);
    }

}