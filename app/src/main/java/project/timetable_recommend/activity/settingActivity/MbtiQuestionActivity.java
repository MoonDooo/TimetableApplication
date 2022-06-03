package project.timetable_recommend.activity.settingActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import project.timetable_recommend.R;

public class MbtiQuestionActivity extends AppCompatActivity {


    //간결성을 위해 위젯의 id를 배열로 생성, id는 final int형
    final int[] TextViewId = {
            R.id.textView1, R.id.textView2, R.id.textView3, R.id.textView4,
            R.id.textView5, R.id.textView6, R.id.textView7, R.id.textView8,
            R.id.textView9, R.id.textView10, R.id.textView11, R.id.textView12
    };

    final int[] yButtonId = {R.id.Y1, R.id.Y2, R.id.Y3, R.id.Y4, R.id.Y5, R.id.Y6, R.id.Y7, R.id.Y8, R.id.Y9,
            R.id.Y10, R.id.Y11, R.id.Y12};
    final int[] nButtonId = {R.id.N1, R.id.N2, R.id.N3, R.id.N4, R.id.N5, R.id.N6, R.id.N7, R.id.N8, R.id.N9,
            R.id.N10, R.id.N11, R.id.N12};
    final int[] groupId = {R.id.group1, R.id.group2, R.id.group3, R.id.group4, R.id.group5, R.id.group6, R.id.group7, R.id.group8,
            R.id.group9, R.id.group10, R.id.group11, R.id.group12};
    final int[] layoutId = {R.id.layout1, R.id.layout2, R.id.layout3, R.id.layout4, R.id.layout5, R.id.layout6, R.id.layout7,
            R.id.layout8, R.id.layout9, R.id.layout10, R.id.layout11, R.id.layout12};

    //배열로 위젯 생성
    TextView[] textViews;
    RadioButton[] yButtons;
    RadioButton[] nButtons;
    RadioGroup[] groups;
    LinearLayout[] layouts;
    Button button;
    StringBuilder MBTI;
    char[] alphabetResult = new char[12];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mbtiquestion);


        //배열로 위젯 생성
        textViews = new TextView[12]; //질문 배열
        yButtons = new RadioButton[12]; //yes 배열
        nButtons = new RadioButton[12]; //no 배열
        groups = new RadioGroup[12]; //라디오그룹 배열
        layouts = new LinearLayout[12]; //질문지, y버튼, n버튼을 포함하는 레이아웃 배열


        //for문을 이용해 MBTI질문 textView12개, Yes RadioButton 12개, No RadioButton 12개 초기화
        for (int i = 0; i < 12; i++) {
            textViews[i] = findViewById(TextViewId[i]);
            yButtons[i] = findViewById(yButtonId[i]);
            nButtons[i] = findViewById(nButtonId[i]);
            groups[i] = findViewById(groupId[i]);
            layouts[i] = findViewById(layoutId[i]);
        }
        button = findViewById(R.id.button);
        MBTI = new StringBuilder(4); //성능개선을 위해 StringBuilder객체로 생성(String으로 변환가능)


        //라디오그룹 클릭 시(라디오버튼 Y, N 클릭 시) 실행되는 로직
        for (int i = 0; i < 12; i++) {
            groups[i].setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    switch (group.getId()) {
                        //J-P
                        case R.id.group12:
                            if (checkedId == R.id.Y12)
                                alphabetResult[11] = 'J';
                            else
                                alphabetResult[11] = 'P';
                            button.setVisibility(View.VISIBLE);
                            break;
                        case R.id.group11:
                            if (checkedId == R.id.Y11)
                                alphabetResult[10] = 'J';
                            else
                                alphabetResult[10] = 'P';
                            layouts[11].setVisibility(View.VISIBLE);
                            break;
                        case R.id.group10:
                            if (checkedId == R.id.Y10)
                                alphabetResult[9] = 'J';
                            else
                                alphabetResult[9] = 'P';
                            layouts[10].setVisibility(View.VISIBLE);
                            break;
                        //T-F
                        case R.id.group9:
                            if (checkedId == R.id.Y9)
                                alphabetResult[8] = 'T';
                            else
                                alphabetResult[8] = 'F';
                            layouts[9].setVisibility(View.VISIBLE);
                            break;
                        case R.id.group8:
                            if (checkedId == R.id.Y8)
                                alphabetResult[7] = 'T';
                            else
                                alphabetResult[7] = 'F';
                            layouts[8].setVisibility(View.VISIBLE);
                            break;
                        case R.id.group7:
                            if (checkedId == R.id.Y7)
                                alphabetResult[6] = 'T';
                            else
                                alphabetResult[6] = 'F';
                            layouts[7].setVisibility(View.VISIBLE);
                            break;
                        //N-S
                        case R.id.group6:
                            if (checkedId == R.id.Y6)
                                alphabetResult[5] = 'N';
                            else
                                alphabetResult[5] = 'S';
                            layouts[6].setVisibility(View.VISIBLE);
                            break;
                        case R.id.group5:
                            if (checkedId == R.id.Y5)
                                alphabetResult[4] = 'N';
                            else
                                alphabetResult[4] = 'S';
                            layouts[5].setVisibility(View.VISIBLE);
                            break;
                        case R.id.group4:
                            if (checkedId == R.id.Y4)
                                alphabetResult[3] = 'N';
                            else
                                alphabetResult[3] = 'S';
                            layouts[4].setVisibility(View.VISIBLE);
                            break;
                        //E-I
                        case R.id.group3:
                            if (checkedId == R.id.Y3)
                                alphabetResult[2] = 'E';
                            else
                                alphabetResult[2] = 'I';
                            layouts[3].setVisibility(View.VISIBLE);
                            break;
                        case R.id.group2:
                            if (checkedId == R.id.Y2)
                                alphabetResult[1] = 'E';
                            else
                                alphabetResult[1] = 'I';
                            layouts[2].setVisibility(View.VISIBLE);
                            break;
                        case R.id.group1:
                            if (checkedId == R.id.Y1)
                                alphabetResult[0] = 'E';
                            else
                                alphabetResult[0] = 'I';
                            layouts[1].setVisibility(View.VISIBLE);
                            break;
                        default:
                            break;
                    }
                }
            });
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //E-I 판별                        0 1 2 3 4 5 6 7
                int[] MBTICount = new int[8];  //E I N S T F J P

                for (int i = 0; i < 3; i++) {  //E I 판별
                    if (alphabetResult[i] == 'E')
                        MBTICount[0]++; //E
                    else
                        MBTICount[1]++; //I
                }
                //MBTI 결과 만들기(첫 번째 알파벳) _***
                if ((MBTICount[0] - MBTICount[1]) > 0)
                    MBTI.append("E");
                else
                    MBTI.append("I");

                //N-S 판별
                for (int i = 3; i < 6; i++) {
                    if (alphabetResult[i] == 'N')
                        MBTICount[2]++; // N
                    else
                        MBTICount[3]++; //S
                }
                //MBTI 결과 만들기(두 번째 알파벳) *_**
                if ((MBTICount[2] - MBTICount[3]) > 0)
                    MBTI.append("N");
                else
                    MBTI.append("S");

                //T-S 판별
                for (int i = 6; i < 9; i++) {
                    if (alphabetResult[i] == 'T')
                        MBTICount[4]++; //T
                    else
                        MBTICount[5]++; //F
                }
                //MBTI 결과 만들기(세 번째 알파벳) **_*
                if ((MBTICount[4] - MBTICount[5]) > 0)
                    MBTI.append("T");
                else
                    MBTI.append("F");

                //J-P판별
                for (int i = 9; i < 12; i++) {
                    if (alphabetResult[i] == 'J')
                        MBTICount[6]++; //J
                    else
                        MBTICount[7]++; //P
                }
                //MBTI 결과 만들기(네 번째 알파벳) ***_
                if ((MBTICount[6] - MBTICount[7]) > 0)
                    MBTI = MBTI.append("J");
                else
                    MBTI = MBTI.append("P");

                Intent intent = getIntent();
                intent.putExtra("MBTI", MBTI.toString()); //MBTI를 intent에 넣음
                setResult(RESULT_OK, intent); //SettingActivity에 intent를 보내면서 화면 전환
                finish();
            }
        });

    }


}