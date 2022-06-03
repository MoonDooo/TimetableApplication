package project.timetable_recommend.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import project.timetable_recommend.model.dataTransferObject.StudentInfoDTO;
import project.timetable_recommend.R;
import project.timetable_recommend.activity.settingActivity.SettingActivity;


public class StudentInfoFragment extends Fragment {
    EditText grade, subjectId;
    TextView majorNum, cultureNum;
    SeekBar seekbarMajor, seekBarCulture;
    Button button;
    public int numMajor = 0, numCulture = 0; //number 받아주는 변수
    StudentInfoDTO studentInfoDTO;
    FrameLayout frameLayout;
    RecommendSubjectFragment recommendSubjectFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_student_info, container, false);
        frameLayout = rootView.findViewById(R.id.container);
        grade = rootView.findViewById(R.id.editTextTextGrade);
        subjectId = rootView.findViewById(R.id.editTextTextSubjectId);
        seekbarMajor = rootView.findViewById(R.id.seekBarMajorScore);
        seekBarCulture = rootView.findViewById(R.id.seekBarCultureScore);
        majorNum = rootView.findViewById(R.id.textViewMajorNum);
        cultureNum = rootView.findViewById(R.id.textViewCultureNum);
        button = rootView.findViewById(R.id.buttonFSI_OK);


        SettingActivity settingActivity = (SettingActivity) getActivity();
        recommendSubjectFragment = settingActivity.recommendSubjectFragment;
        //전공 학점 수 (SeekBar) 변화 시 호출
        seekbarMajor.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                numMajor = seekBar.getProgress();
                updateMajor();

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                numMajor = seekBar.getProgress();

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                numMajor = seekBar.getProgress();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //교양 학점 수 (SeekBar) 변화 시 호출
        seekBarCulture.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                numCulture = seekBar.getProgress();

                updateCulture();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                numCulture = seekBar.getProgress();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                numCulture = seekBar.getProgress();
            }
        });


        //확인 버튼 클릭 시 호출
        /*
        * 번들에 studentInfoDTO 객체를 담아서
        * */
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //넘겨 받을 프래그먼트 생성

                studentInfoDTO = new StudentInfoDTO();

                if (grade.getText().toString().equals("")){
                    grade.setText("0");
                }
                if (subjectId.getText().toString().equals("")){
                    subjectId.setText("0");
                }
                studentInfoDTO.setGrade(Integer.parseInt(grade.getText().toString()));

                studentInfoDTO.setMajorScore(Integer.parseInt(majorNum.getText().toString()));
                studentInfoDTO.setCultureScore(Integer.parseInt(cultureNum.getText().toString()));
                studentInfoDTO.setSubjectId(Integer.parseInt(subjectId.getText().toString())); //학수번호는 문자열! (시작 번호가 0으로 시작할 수도 있기 때문
               // Toast.makeText(getContext(), studentInfoDTO.getGrade(), Toast.LENGTH_SHORT).show();
                // SettingActivity에서 생성한 bundle 받음 (MBTI값 들어있음)

                Bundle bundle = getArguments();
                bundle.putSerializable("studentInfoDTO", studentInfoDTO); //bundle에 객체 담음


                //넘겨 받을 프래그먼트에 bundle 저장
               // recommendSubjectFragment.setArguments(bundle); //해당 bundle에는 MBTI값, StudentInfoDTO 객체 들어있음

                //현재 프래그먼트에서 RecommendSubjectFragment로 넘김
                SettingActivity settingActivity = (SettingActivity) getActivity();
                settingActivity.onFragmentChanged(1, bundle);

            }
        });
        return rootView;
    }

    //SeekBar 변화에 따라 TextView 바꿔주는 메소드
    public void updateMajor() {
        majorNum.setText(new StringBuilder().append(numMajor));
    }
    public void updateCulture() {
        cultureNum.setText(new StringBuilder().append(numCulture));
    }
}