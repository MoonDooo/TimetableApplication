package project.timetable_recommend.Model;

import static project.timetable_recommend.activity.Controller.GsonThread.subjectList;

public class MBTISubjectDTO {
    //mbti별로 추천하는 80개의 subject를 담고있습니다.
    public MBTISubjectDTO(){
        MBTI80subject();
    }

    //mbti별로 추천하는 80개의 subject를 지정하는 학수번호를 담고 있습니다.
    SubjectItemVO[] MBTI80subjects = new SubjectItemVO[80];
    int MBTIsubjects[] = {
            10018, 10043, 10571, 11096, 11109,//ISFJ
            10046, 10047, 10592, 11093, 11096,//ESFJ
            10042, 10575, 11107, 11120, 11405,//ESTJ
            10019, 10044, 11098, 11105, 11117,//ISTJ
            10021, 11078, 11079, 11097, 11119,//ESTP
            10038, 10572, 11077, 11104, 11409,//ESFP
            10037, 10040, 11118, 10626, 10627,//ISTP
            9883, 9884, 10017, 10020, 10025,//ISFP
            10049, 10050, 10051, 10054, 10055,//INTP
            10045, 10048, 10052, 10053, 10057,//INTJ
            10030, 10033, 10034, 11828, 11826,//ENTJ
            10027, 10039, 11402, 11404, 11832,//ENTP
            10024, 10036, 11103, 11123, 11401,//ENFP
            10060, 10574, 11089, 10036, 11101,//ENFJ
            10022, 10028, 11092, 10573, 11074,//INFJ
            10016, 10612, 11075, 11076, 11095//INFP
    };
    //80개를 초기화 시켜주는 함수입니다.
    public void MBTI80subject(){
        int tmp = subjectList.getSubjects().size(); //리스트에 들어있는 객체 개수
        for(int i = 0; i<tmp; i++){
            for(int j = 0; j < 80; j++){
                if(subjectList.getSubjects().get(i).getSubjectId() == MBTIsubjects[j]){
                    MBTI80subjects[j] = subjectList.getSubjects().get(i);
                }
            }
        }
    }
    public SubjectItemVO getMBTI80subjects(int m_count){
        return MBTI80subjects[m_count];
    }
}

