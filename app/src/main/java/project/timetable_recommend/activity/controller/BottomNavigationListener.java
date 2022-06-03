package project.timetable_recommend.activity.controller;

import android.content.Intent;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import project.timetable_recommend.activity.mbtiDiscriptionActivity.MbtiActivity;
import project.timetable_recommend.activity.MainActivity;

import project.timetable_recommend.activity.previousActivity.PreviousActivity;
import project.timetable_recommend.R;
import project.timetable_recommend.activity.settingActivity.SettingActivity;


/**
 * BottomNavigation custom Listener 입니다.
 * 각각의 바텀 네비게이션 뷰 위젯의 각 item에 따른 화면 전환 기능이 구현되어있습니다.
 * Activity 의 호출 스택이 쌓이지 않도록
 *      FLAG_ACTIVITY_CLEAR_TOP & FLAG_ACTIVITY_SINGLE_TOP 작업을 진행했습니다.
 */
public class BottomNavigationListener implements BottomNavigationView.OnNavigationItemSelectedListener{

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.tab_timetable:
                Intent intent1 = new Intent(MainActivity.context_main.getApplicationContext(), MainActivity.class);
                intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent1.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                MainActivity.context_main.startActivity(intent1);
                return true;
            case R.id.tab_MBTI:
                Intent intent2 = new Intent(MainActivity.context_main.getApplicationContext(), MbtiActivity.class);
                intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent2.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                MainActivity.context_main.startActivity(intent2);
                return true;
            case R.id.tab_setting:
                Intent intent3 = new Intent(MainActivity.context_main.getApplicationContext(), SettingActivity.class);
                intent3.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent3.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                MainActivity.context_main.startActivity(intent3);
                return true;
            case R.id.tab_previous:
                Intent intent4= new Intent(MainActivity.context_main.getApplicationContext(), PreviousActivity.class);
                intent4.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent4.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                MainActivity.context_main.startActivity(intent4);
                return true;
        }
        return false;
    }
}
