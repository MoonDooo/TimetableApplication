package project.timetable_recommend.activity.settingActivity;

import static project.timetable_recommend.activity.controller.GsonThread.subjectList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import project.timetable_recommend.activity.controller.GsonThread;
import project.timetable_recommend.model.Dialog_mbtiShow;
import project.timetable_recommend.model.valueObejct.PreviousSelectedColorVO;
import project.timetable_recommend.model.valueObejct.SubjectItemVO;
import project.timetable_recommend.model.valueObejct.TableCellVO;
import project.timetable_recommend.fragment.InputMBTIFragment;
import project.timetable_recommend.R;
import project.timetable_recommend.fragment.RecommendSubjectFragment;
import project.timetable_recommend.fragment.StudentInfoFragment;


public class SettingActivity extends AppCompatActivity {
    String MBTI;
    StudentInfoFragment studentInfoFragment;
    InputMBTIFragment inputMBTIFragment;
    public RecommendSubjectFragment recommendSubjectFragment;
    TableCellVO c;
    EditText editText;
    Button button;
    static final int REQUEST_SETTING_TO_QUESTION = 1; //requestCode of SettnigActivity -> MBTIQuestionActivity
    Bundle bundle;
    Button button5;
    boolean isDialogCancel;
    public static Context context_settingActivity;
    BottomNavigationView bottomNavigationView;
    LinearLayout input_Layout_instacnce;
    TableLayout tableLayout;
    ArrayList<String> subjectNameTest;
    boolean[][] checkSubject;
    String[][] checkColor;
    PreviousSelectedColorVO temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        c = new TableCellVO();
        findTextViewById(c);
        subjectNameTest = new ArrayList<String>();
        checkSubject = new boolean[c.getHeight()][c.getWidth()];
        checkColor = new String[c.getHeight()][c.getWidth()];
        tableLayout = findViewById(R.id.table);
        tableLayout.setVisibility(View.INVISIBLE);
        temp = new PreviousSelectedColorVO();
        for (int i = 1; i < (c.getHeight() - 1); i++) {
            for (int j = 1; j < (c.getWidth() - 1); j++) {
                checkSubject[i][j] = false;
            }
        }
        GsonThread gsonThread = new GsonThread(getApplicationContext());
        gsonThread.start();
        /**
         * @param context_settingActivity : settingActivity 화면 객체 얻어오는 변수
         * @param input_Layout_instacnce  : mbti 값 읽어 들일 때 사용하는 변수
         */
        context_settingActivity = this;
        isDialogCancel = false;
        input_Layout_instacnce = findViewById(R.id.mbti_input_window);
        bundle = new Bundle();
        //이 버튼 임시적으로 만든거
        editText = findViewById(R.id.mbti_put_text);
        button = findViewById(R.id.mbti_buttonSave);
        //액티비티 위에 돌아갈 프래그먼트 객체 생성
        studentInfoFragment = new StudentInfoFragment();
        inputMBTIFragment = new InputMBTIFragment();
        recommendSubjectFragment = new RecommendSubjectFragment();

        //시작화면 다이얼로그
        /*
         * 어플 시작 시 MBTI 검사 여부 체크하기 위해 다이얼로그 띄움
         * OK: MBTI 검사 시작 (MBTIQuestionActivity로 화면 전환)
         * Cancel: 사용자의 MBTI 요구 (INVISIBLE 되어있던 위젯들을 VISIBLE로 전환)
         *
         *
         * */
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("MBTI 검사 하시겠습니까?").setMessage("");
        //OK 클릭 시 MBTIQuestionActivity로 화면 전환
        dialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                Intent intent = new Intent(getApplicationContext(), MbtiQuestionActivity.class);
                startActivityForResult(intent, REQUEST_SETTING_TO_QUESTION);
            }
        });

        //cancel 클릭 시 위젯보이게 하기(INVISIBLE -> VISIBLE)
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                //숨겼던 위젯 보이게 하기
                input_Layout_instacnce.setVisibility(View.VISIBLE);
            }
        });
        dialogBuilder.show();


        //ENTER 버튼 눌렀을 시
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MBTI = editText.getText().toString();
                MBTI = MBTI.toUpperCase(); //예외(소문자로 MBTI 기입한 경우)처리를 위해 사용자가 입력한 문자열을 대문자로 처리
                //정상적으로 MBTI 기입한 경우
                if (MBTI.equals("ISFP") ||
                        MBTI.equals("ISFJ") ||
                        MBTI.equals("ISTP") ||
                        MBTI.equals("ISTJ") ||
                        MBTI.equals("INFP") ||
                        MBTI.equals("INFJ") ||
                        MBTI.equals("INTP") ||
                        MBTI.equals("INTJ") ||
                        MBTI.equals("ESFP") ||
                        MBTI.equals("ESFJ") ||
                        MBTI.equals("ESTP") ||
                        MBTI.equals("ESTJ") ||
                        MBTI.equals("ENFP") ||
                        MBTI.equals("ENFJ") ||
                        MBTI.equals("ENTP") ||
                        MBTI.equals("ENTJ")) {
                    // 프래그먼트 화면 전환(0),
                    onFragmentChanged(0, bundle); // index 0 호출(flag의 개념)
                } else { //MBTI 정확하게 기입하지 않은 경우
                    Toast.makeText(getApplicationContext(),
                            "MBTI를 정확히 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    ////////접근 금지///////
    public int setTimeTable(SubjectItemVO subjectItemDTO) {

        String color = temp.getColor();
        int tmp_day = 0;
        int tmp_time = 0;
        int checked_day = 0;
        int checked_time = 0;
        int compare_day = 0;
        boolean compare = false;
        boolean checked_subjectTime = false;
        for (int i = 0; i < subjectItemDTO.getSubject_day().size(); i++) {
            checked_day = subjectItemDTO.getSubject_day().get(i).getDay();
            checked_time = subjectItemDTO.getSubject_day().get(i).getTime();
            if (checkSubject[checked_time][checked_day]) {
                Toast.makeText(getApplicationContext(), "중첩된 시간표가 있습니다.", Toast.LENGTH_SHORT).show();
                return -1;
            }
        }//시간표 중첩 확인 반복문입니다. 최대 3번정도 실행
        for(int i = 0; i< subjectNameTest.size(); i++){
            if(subjectNameTest.get(i).equals(subjectItemDTO.getSubjectName())) {
                System.out.println("중첩있음");
                return -1;
            }
        }
        subjectNameTest.add(subjectItemDTO.getSubjectName());
        for (int i = 0; i < subjectItemDTO.getSubject_day().size(); i++) {
            tmp_day = subjectItemDTO.getSubject_day().get(i).getDay();
            tmp_time = subjectItemDTO.getSubject_day().get(i).getTime();
            if(compare_day!=tmp_day){
                compare = true;
            }
            else compare = false;
            if ((i == 0||compare) && tmp_day != 0 && tmp_time != 0) {
                c.cell[tmp_time][tmp_day].setTextColor(Color.WHITE);
                c.cell[tmp_time][tmp_day].setText(subjectItemDTO.getSubjectName()); // 시간표에 추가되는 부분
                c.addNeonMovingEffectInTextView(c.cell[tmp_time][tmp_day]);
            }
            checkSubject[tmp_time][tmp_day] = true;
            c.cell[tmp_time][tmp_day].setBackgroundColor(Color.parseColor(color));
            compare_day = tmp_day;
        }
        return 1;
    }


    public void onFragmentChanged(int index, Bundle bundle) {

        if (index == 0) { //12개 질문지 선택하고 MBTI값 정확하게 받은 경우, studentInfoFragment로 이동


            //다이얼로그를 통해서 보여줌
            Dialog_mbtiShow dlg = new Dialog_mbtiShow(SettingActivity.this,MBTI.toString());
            dlg.show();

            //MBTI 값을 받고, 그 값을 StudentInfoFragment로 전환하면서 넘기기
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            //MBTI값 담을 Bundle 객체 생성
            bundle.putString("MBTI", MBTI); //번들에 저장

            // 변경될 프래그먼트로 번들을(데이터) 보냄
            // setArguments() 메소드를 사용하지 않으면, 받는 쪽에서 null 값으로 받음.
            studentInfoFragment.setArguments(bundle);

            //프레임 레이아웃(첫 번째 인자)에서 프래그먼트(두 번째 인자)로 변경(replace)
            transaction.replace(R.id.container, studentInfoFragment);
            transaction.commit(); //저장
        }
        //학생 정보(StudentInfoFragment에서 넘어옴) 기입 받은 경우, recommendSubjectFragment 이동

        if(index == 1) {
            tableLayout.setVisibility(View.VISIBLE);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            recommendSubjectFragment.setArguments(bundle);
            transaction.replace(R.id.container, recommendSubjectFragment);
            transaction.commit();
        }
    }


    //넘긴 액티비티에서 다시 원래로 넘어온 경우 호출되는 메소드
    public void findTextViewById(TableCellVO tCell) {
        for (int y = 0; y < tCell.getHeight(); y++) {
            final int curY = y;
            for (int x = 0; x < tCell.getWidth(); x++) {
                final int curX = x;
                tCell.cell[curY][curX] = findViewById(tCell.cellID[curY][curX]);
            }
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_SETTING_TO_QUESTION) { //MBTIQuestionActivity에서 넘어왔을 경우
                MBTI = data.getStringExtra("MBTI"); //Intent에 담겨져 있는 MBTI 꺼냄
                onFragmentChanged(0, bundle); //Fragment 전환 메소드 호출(index == 0, StudentInfoFragment)

            }
        }
    }
}