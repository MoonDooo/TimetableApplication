package project.timetable_recommend.fragment;

import static project.timetable_recommend.activity.controller.GsonThread.subjectList;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import project.timetable_recommend.activity.MainActivity;
import project.timetable_recommend.model.dataTransferObject.MBTISubjectDTO;
import project.timetable_recommend.model.dataTransferObject.StudentInfoDTO;
import project.timetable_recommend.R;
import project.timetable_recommend.activity.settingActivity.SettingActivity;
import java.io.Serializable;
import java.util.ArrayList;
import javax.security.auth.Subject;

import project.timetable_recommend.model.dataTransferObject.DayTimeDTO;
import project.timetable_recommend.model.dataTransferObject.MBTISubjectDTO;
import project.timetable_recommend.model.dataTransferObject.MainActivitySubjectInfo;
import project.timetable_recommend.model.valueObejct.SubjectItemVO;
import project.timetable_recommend.model.dataTransferObject.majorSubject;
import project.timetable_recommend.model.dataTransferObject.StudentInfoDTO;


public class RecommendSubjectFragment extends Fragment {
    Button button1, button2, button3, buttonFinish;
    TextView textView, textView2;
    public int searchMBTI(String MBTI){
        switch(MBTI){
            case "ISFJ" :
                return 0;
            case "ESFJ":
                return 5;
            case "ESTJ":
                return 10;
            case "ISTJ":
                return 15;
            case "ESTP":
                return 20;
            case "ESFP":
                return 25;
            case "ISTP":
                return 30;
            case "ISFP":
                return 35;
            case "INTP":
                return 40;
            case "INTJ":
                return 45;
            case "ENTJ":
                return 50;
            case "ENTP":
                return 55;
            case "ENFP":
                return 60;
            case "ENFJ":
                return 65;
            case "INFJ":
                return 70;
            case "INFP":
                return 75;
        }
        return -1;
    }
    SettingActivity settingActivity;
    int count;
    int first;
    int second;
    int third;
    int subject_max;
    int majorScore;
    int grade;
    int searchSubjectNum;
    ArrayList<SubjectItemVO> subjectItemDTOS;
    StudentInfoDTO studentInfoDTO;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup recommendSubjectView = (ViewGroup) inflater.inflate(R.layout.fragment_recommend_subject, container, false);
        settingActivity = (SettingActivity)getActivity();
        studentInfoDTO = (StudentInfoDTO)getArguments().getSerializable("studentInfoDTO");
        button1 = recommendSubjectView.findViewById(R.id.buttonFRS_1);
        button2 = recommendSubjectView.findViewById(R.id.buttonFRS_2);
        button3 = recommendSubjectView.findViewById(R.id.buttonFRS_3);
        buttonFinish = recommendSubjectView.findViewById(R.id.buttonFRS_Finish);
        textView = recommendSubjectView.findViewById(R.id.textViewFRS_MBTI);
        textView2 = recommendSubjectView.findViewById(R.id.textView);
        final int[] subject_max = {studentInfoDTO.getCultureScore()};
        textView2.setText("남은 교양 학점 : " + subject_max[0]);

        MBTISubjectDTO subject = new MBTISubjectDTO();
        subjectItemDTOS = new ArrayList<>();
        //settingActivity에 담겨있는 bundle객체 반환받음
        Bundle bundle = getArguments();
        String MBTI = bundle.getString("MBTI"); //번들에서 MBTI값 가져옴
        count  = searchMBTI(MBTI);
        button1.setText(""+subject.getMBTI80subjects(++count).getSubjectName()
                + "\n" + subject.getMBTI80subjects(count).getSubjectTimetable()
                + "\n학점 : " + subject.getMBTI80subjects(count).getCredit());
        first = count;
        button2.setText(""+subject.getMBTI80subjects(++count).getSubjectName()
                + "\n" + subject.getMBTI80subjects(count).getSubjectTimetable()
                + "\n학점 : " + subject.getMBTI80subjects(count).getCredit());
        second = count;
        button3.setText(""+subject.getMBTI80subjects(++count).getSubjectName()
                + "\n" + subject.getMBTI80subjects(count).getSubjectTimetable()
                + "\n학점 : " + subject.getMBTI80subjects(count).getCredit());
        third = count;
        textView.setText(MBTI);
        //선택한 과목 넣기
        searchSubjectNum = studentInfoDTO.getSubjectId();
        searchSubject(searchSubjectNum);
        //이 앞. 전공 집어 넣는 중
        majorScore = studentInfoDTO.getMajorScore();
        grade = studentInfoDTO.getGrade();
        int remain_credit = majorInsertAlg(majorScore,grade);
        subject_max[0] = studentInfoDTO.getCultureScore()+remain_credit;
        textView2.setText("남은 교양 학점 : " + subject_max[0]);
        //bundle에 담아놓은 studentInfoDTO(객체)를 꺼냄
        /*
        * Button에
        * */
        //버튼 클릭시 과목 객체를 시간표액티비티로 넘기면서 시간표액티비티로 화면전환
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(subject_max[0] - subject.getMBTI80subjects(first).getCredit()<0) {
                    Toast.makeText(getContext(), "설정하신 교양학점을 초과하였습니다.", Toast.LENGTH_SHORT).show();
                }
                else {
                    int x = settingActivity.setTimeTable(subject.getMBTI80subjects(first));
                    if (x == 1) {
                        subject_max[0] -= subject.getMBTI80subjects(first).getCredit();
                        subjectItemDTOS.add(subject.getMBTI80subjects(first));
                    }
                }
                if(count==79)count=-1;
                button1.setText(""+subject.getMBTI80subjects(++count).getSubjectName()
                        + "\n" + subject.getMBTI80subjects(count).getSubjectTimetable()
                        + "\n학점 : " + subject.getMBTI80subjects(count).getCredit());
                textView2.setText("남은 교양 학점 : " + subject_max[0]);
                first = count;
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(subject_max[0] - subject.getMBTI80subjects(second).getCredit()<0) {
                    Toast.makeText(getContext(), "설정하신 교양학점을 초과하였습니다.", Toast.LENGTH_SHORT).show();
                }
                else {
                    int x = settingActivity.setTimeTable(subject.getMBTI80subjects(second));
                    if (x == 1) {
                        subject_max[0] -= subject.getMBTI80subjects(second).getCredit();
                        subjectItemDTOS.add(subject.getMBTI80subjects(second));
                    }
                }
                if(count==79)count=-1;
                button2.setText(""+subject.getMBTI80subjects(++count).getSubjectName()
                        + "\n" + subject.getMBTI80subjects(count).getSubjectTimetable()
                        + "\n학점 : " + subject.getMBTI80subjects(count).getCredit());
                textView2.setText("남은 교양 학점 : " + subject_max[0]);
                second = count;
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(subject_max[0] - subject.getMBTI80subjects(third).getCredit()<0) {
                    Toast.makeText(getContext(), "설정하신 교양학점을 초과하였습니다.", Toast.LENGTH_SHORT).show();
                }
                else {
                    int x = settingActivity.setTimeTable(subject.getMBTI80subjects(third));
                    if (x == 1) {
                        subject_max[0] -= subject.getMBTI80subjects(third).getCredit();
                        subjectItemDTOS.add(subject.getMBTI80subjects(third));
                    }
                }
                if(count==79)count=-1;
                button3.setText(""+subject.getMBTI80subjects(++count).getSubjectName()
                + "\n" + subject.getMBTI80subjects(count).getSubjectTimetable()
                + "\n학점 : " + subject.getMBTI80subjects(count).getCredit());
                textView2.setText("남은 교양 학점 : " + subject_max[0]);
                third = count;
            }
        });
        buttonFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivitySubjectInfo mainActivitySubjectInfo = new MainActivitySubjectInfo(subjectItemDTOS);
                Intent intent = new Intent(settingActivity.getApplicationContext(), MainActivity.class);
                intent.putExtra("MainActivitySubject", mainActivitySubjectInfo);
                for(int i = 0 ; i< subjectItemDTOS.size(); i++){
                    System.out.println(subjectItemDTOS.get(i).getSubjectName());
                }
                startActivity(intent);
            }
        });


        return recommendSubjectView;
    }
    public int majorInsertAlg(int m_majorScore, int grade){
        majorSubject majorSubject = new majorSubject();
        int majorScore = m_majorScore;
        switch(grade){
            case 1:
                for(int i = 0; i<majorSubject.getFirstGrade().getEssential_Subject().size(); i++) {
                    if(majorScore - majorSubject.getFirstGrade().getEssential_Subject().get(i).getCredit()<0) {
                        return 0;
                    }
                    else {
                        int x = settingActivity.setTimeTable(majorSubject.getFirstGrade().getEssential_Subject().get(i));
                        if (x == 1) {
                            majorScore -= majorSubject.getFirstGrade().getEssential_Subject().get(i).getCredit();
                            subjectItemDTOS.add(majorSubject.getFirstGrade().getEssential_Subject().get(i));
                        }
                    }
                }
                for(int i = 0; i<majorSubject.getFirstGrade().getSelect_Subject().size(); i++) {
                    if(majorScore - majorSubject.getFirstGrade().getSelect_Subject().get(i).getCredit()<0) {
                        return 0;
                    }
                    else {
                        int x = settingActivity.setTimeTable(majorSubject.getFirstGrade().getSelect_Subject().get(i));
                        if (x == 1) {
                            majorScore -= majorSubject.getFirstGrade().getSelect_Subject().get(i).getCredit();
                            subjectItemDTOS.add(majorSubject.getFirstGrade().getSelect_Subject().get(i));
                        }

                    }
                }
                Toast.makeText(getContext(),"전공을 할당하고 남은 학점은 교양 점수로 반환합니다.", Toast.LENGTH_SHORT).show();
                return majorScore;
            case 2:
                for(int i = 0; i<majorSubject.getSecondGrade().getEssential_Subject().size(); i++) {
                    if(majorScore - majorSubject.getSecondGrade().getEssential_Subject().get(i).getCredit()<0) {
                        return 0;
                    }
                    else {
                        int x = settingActivity.setTimeTable(majorSubject.getSecondGrade().getEssential_Subject().get(i));
                        if (x == 1) {
                            majorScore -= majorSubject.getSecondGrade().getEssential_Subject().get(i).getCredit();
                            subjectItemDTOS.add(majorSubject.getSecondGrade().getEssential_Subject().get(i));
                        }
                    }
                }
                for(int i = 0; i<majorSubject.getSecondGrade().getSelect_Subject().size(); i++) {
                    if(majorScore - majorSubject.getSecondGrade().getSelect_Subject().get(i).getCredit()<0) {
                        return 0;
                    }
                    else {
                        int x = settingActivity.setTimeTable(majorSubject.getSecondGrade().getSelect_Subject().get(i));
                        if (x == 1){
                            majorScore -= majorSubject.getSecondGrade().getSelect_Subject().get(i).getCredit();
                            subjectItemDTOS.add(majorSubject.getSecondGrade().getSelect_Subject().get(i));
                        }
                    }
                }
                Toast.makeText(getContext(),"전공을 할당하고 남은 학점은 교양 점수로 반환합니다.", Toast.LENGTH_SHORT).show();
                return majorScore;
            case 3:
                for(int i = 0; i<majorSubject.getThirdGrade().getEssential_Subject().size(); i++) {
                    if(majorScore - majorSubject.getThirdGrade().getEssential_Subject().get(i).getCredit()<0) {
                        return 0;
                    }
                    else {
                        int x = settingActivity.setTimeTable(majorSubject.getThirdGrade().getEssential_Subject().get(i));
                        if (x == 1){
                            majorScore -= majorSubject.getThirdGrade().getEssential_Subject().get(i).getCredit();
                            subjectItemDTOS.add(majorSubject.getThirdGrade().getEssential_Subject().get(i));
                        }
                    }
                }
                for(int i = 0; i<majorSubject.getThirdGrade().getSelect_Subject().size(); i++) {
                    if(majorScore - majorSubject.getThirdGrade().getSelect_Subject().get(i).getCredit()<0) {
                        return 0;
                    }
                    else {
                        int x = settingActivity.setTimeTable(majorSubject.getThirdGrade().getSelect_Subject().get(i));
                        if (x == 1) {
                            majorScore -= majorSubject.getThirdGrade().getSelect_Subject().get(i).getCredit();
                            subjectItemDTOS.add(majorSubject.getThirdGrade().getSelect_Subject().get(i));
                        }
                    }
                }
                Toast.makeText(getContext(),"전공을 할당하고 남은 학점은 교양 점수로 반환합니다.", Toast.LENGTH_SHORT).show();
                return majorScore;
            case 4:
                for(int i = 0; i<majorSubject.getFourthGrade().getEssential_Subject().size(); i++) {
                    if(majorScore - majorSubject.getFourthGrade().getEssential_Subject().get(i).getCredit()<0) {
                        return 0;
                    }
                    else {
                        int x = settingActivity.setTimeTable(majorSubject.getFourthGrade().getEssential_Subject().get(i));
                        if (x == 1) {
                            majorScore -= majorSubject.getFourthGrade().getEssential_Subject().get(i).getCredit();
                            subjectItemDTOS.add(majorSubject.getFourthGrade().getEssential_Subject().get(i));
                        }
                    }
                }
                for(int i = 0; i<majorSubject.getFourthGrade().getSelect_Subject().size(); i++) {
                    if(majorScore - majorSubject.getFourthGrade().getSelect_Subject().get(i).getCredit()<0) {
                        return 0;
                    }
                    else {
                        int x = settingActivity.setTimeTable(majorSubject.getFourthGrade().getSelect_Subject().get(i));
                        if (x == 1) {
                            majorScore -= majorSubject.getFourthGrade().getSelect_Subject().get(i).getCredit();
                            subjectItemDTOS.add(majorSubject.getFourthGrade().getSelect_Subject().get(i));
                        }
                    }
                }
                Toast.makeText(getContext(),"전공을 할당하고 남은 학점은 교양 점수로 반환합니다.", Toast.LENGTH_SHORT).show();
                return majorScore;
        }
        return majorScore;
    }
    public void searchSubject(int num){
        for(int i = 0; i<subjectList.getSubjects().size(); i++){
            if(num == subjectList.getSubjects().get(i).getSubjectId()) {
                settingActivity.setTimeTable(subjectList.getSubjects().get(i));
                subjectItemDTOS.add(subjectList.getSubjects().get(i));
                return;
            }
        }
        Toast.makeText(getContext(), "학수번호를 찾을 수 없습니다.", Toast.LENGTH_SHORT).show();
        return;
    }
}