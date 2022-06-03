package project.timetable_recommend.activity.previousActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import project.timetable_recommend.activity.controller.BottomNavigationListener;
import project.timetable_recommend.R;

public class PreviousActivity extends AppCompatActivity {
    /**
     * @param buttonTransJunior           : 1학년 1학기 시간표로 이동하는 버튼
     * @param buttonTransSenior           : 2학년 1학기 시간표로 이동하는 버튼
     * @param buttonTransJunoir_Sophister : 3학년 1학기 시간표로 이동하는 버튼
     * @param buttonTransSenior_Shphister : 4학년 1학기 시간표로 이동하는 버튼
     * @param bottomNavigationView        : 바텀 네비게이션 뷰 인스턴스
     */
    Button  buttonTransJunior,
            buttonTransSenior,
            buttonTransJunoir_Sophister,
            buttonTransSenior_Shphister;
    BottomNavigationView  bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous);

        //인스턴스 ID 값 부여
        bottomNavigationView        = findViewById(R.id.bottom_navigation);
        buttonTransJunior           = findViewById(R.id.button);
        buttonTransSenior           = findViewById(R.id.button2);
        buttonTransJunoir_Sophister = findViewById(R.id.button3);
        buttonTransSenior_Shphister = findViewById(R.id.button4);

        //바텀 네비게이션 바
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationListener());

        Button.OnClickListener onClickListener = new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.button:
                        Intent intent1 = new Intent(getApplicationContext(), PreviousLayout1.class);
                        startActivity(intent1);
                        break ;
                    case R.id.button2:
                        Intent intent2 = new Intent(getApplicationContext(), PreviousLayout2.class);
                        startActivity(intent2);
                        break ;
                    case R.id.button3:
                        Intent intent3 = new Intent(getApplicationContext(), PreviousLayout3.class);
                        startActivity(intent3);
                        break ;
                    case R.id.button4:
                        Intent intent4 = new Intent(getApplicationContext(), PreviousLayout4.class);
                        startActivity(intent4);

                }
            }
        } ;
        
        buttonTransJunior.setOnClickListener(onClickListener);
        buttonTransSenior.setOnClickListener(onClickListener);
        buttonTransJunoir_Sophister.setOnClickListener(onClickListener);
        buttonTransSenior_Shphister.setOnClickListener(onClickListener);

    }

}