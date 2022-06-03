package project.timetable_recommend.activity.mbtiDiscriptionActivity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.OvershootInterpolator;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import project.timetable_recommend.R;
import project.timetable_recommend.adapter.MbtiAdapter;
import project.timetable_recommend.activity.controller.BottomNavigationListener;
import project.timetable_recommend.model.valueObejct.MBTIDataVO;
import project.timetable_recommend.model.dataTransferObject.MbtiTypeDTO;
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;

public class MbtiActivity extends AppCompatActivity implements MbtiAdapter.OnItemClickListener{

    /**
     * @param bottomNavigationView : 바텀 네비게이션 바의 다음 화면을 전환하기 위해 필요한 변수
     * @param layoutManager        : 리싸이클러 뷰를 호출하기 위해 필요한 변수
     * @param adapter              : 이 변수를 통해 목록 데이터 저장, 실행
     * @param recyclerView         : 리싸이클러 뷰를 호출하기 위해 필요한 변수
     * @param mbtiData             : 성격 유형별 특징이 저장되어있는 변수
     * @param context_mbtiActivity : 현재 mbtiActivity 화면 객체를 얻을 수 있는 변수
     *
     * https://developer.android.com/training/transitions/start-activity?hl=ko#java
     * 내가 사용하려는 트렌지션 animation은 롤리팝버전이후로 지원됨...
     */
    BottomNavigationView       bottomNavigationView;
    RecyclerView.LayoutManager layoutManager;
    MbtiAdapter                adapter;
    RecyclerView               recyclerView;
    MBTIDataVO                 mbtiData;
    public static Context      context_mbtiActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mbtiactivity);

        /**
         * 변수들 초기화
         */


        context_mbtiActivity    = this;
        bottomNavigationView    = findViewById(R.id.bottom_navigation);
        recyclerView            = (RecyclerView)findViewById(R.id.mbti_recyclerView);
        layoutManager           = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        adapter                 = new MbtiAdapter();
        mbtiData                = new MBTIDataVO();

        //adapter에 데이터 넣기.
        inputDataToAdapter();
        recyclerView.setLayoutManager(layoutManager);

        /**
         * adapter에 에니메이션 adapter 설정.
         * add recycler view animation
         */
        recyclerView.setAdapter(new AlphaInAnimationAdapter(adapter));

        //add recycler view animation
        AlphaInAnimationAdapter    alphaInAnimationAdapter = new AlphaInAnimationAdapter(adapter);
        alphaInAnimationAdapter.setDuration(1700);
        alphaInAnimationAdapter.setInterpolator(new OvershootInterpolator());
        alphaInAnimationAdapter.setFirstOnly(true);
        recyclerView.setAdapter(new ScaleInAnimationAdapter(alphaInAnimationAdapter));
        adapter.setOnItemClickListener(new MbtiAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position)  {
                Intent intent = new Intent(MbtiActivity.this, MbtiDetailActivity.class);
                MbtiTypeDTO mbti = adapter.getItem(position);

                intent.putExtra("mbtiType", mbti.getType());
                intent.putExtra("mbtiImage",mbti.getImage());
                View mbtiView = v.findViewById(R.id.mbti_image);

                ActivityOptionsCompat option =
                        ActivityOptionsCompat.makeSceneTransitionAnimation(MbtiActivity.this, mbtiView, "mbtiImg");
                startActivity(intent, option.toBundle());

            }
        }) ;


        /**
         * bottomNavigationView함수는 bottom_navigation_menu.xml에서 정의한 메뉴 네비게이션을 통해 정의한 네비게이션 바의 객체입니다.
         * 이 객체에 이벤트 헨들러를 통해 어떤 네비게이션 바의 아이콘이 클릭 됬는지 찾는 메서드 입니다.
         */
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationListener());

    }

    /**
     * 주어진 데이터를 adapter에 추가하는 func
     * 추후 recyclerView를 통해 이 데이터가 보여짐.
     */
    public void inputDataToAdapter(){
        for (int i = 0; i < this.mbtiData.getMbti_type().length; i++){
            this.adapter.addItem(this.mbtiData.getMbti_type()[i]);
        }
    }

    /**
     *              Used recyclerview shared element transition                *
     *
     * @param v        : view를 통해 현재 출력된 특정 position의 이미지thumbnail을 얻어낸다.
     * @param position : recyclerView 의 특정 Cell에서 몇번째 값이 선택 됬는지 알 수 있다.
     * .makeSceneTransitionAnimation func를 통해서 특정 thumbnail의 이미지 객체 v와
     *                 애니메이션을 통해 전환될 이미지 thumbnail을 매칭시켜준다.
     *                 그 후 detailActivity 시작
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onItemClick(View v, int position)  {
        Intent intent    = new Intent(MbtiActivity.this, MbtiDetailActivity.class);
        MbtiTypeDTO mbti = adapter.getItem(position);

        intent.putExtra("mbtiType", mbti.getType());
        intent.putExtra("mbtiImage",mbti.getImage());
        View mbtiView = v.findViewById(R.id.mbti_image);
        ActivityOptionsCompat option =
                ActivityOptionsCompat.makeSceneTransitionAnimation(this, mbtiView, "mbti_image_detail");
        startActivity(intent, option.toBundle());
    }

}


