package project.timetable_recommend.model.dataTransferObject;

import java.util.ArrayList;

import project.timetable_recommend.model.valueObejct.SubjectItemVO;

//GSON으로 가져온 JSON을 파싱하기 위해 만든 클래스입니다. 이곳안에 있는 ArrayList로 설정한 subject에 저장합니다.
public class SubjectListDTO {
    public ArrayList<SubjectItemVO> getSubjects() {
        return subject;
    }
    ArrayList<SubjectItemVO> subject = new ArrayList<SubjectItemVO>();

}
