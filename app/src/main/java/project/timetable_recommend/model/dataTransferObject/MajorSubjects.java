package project.timetable_recommend.model.dataTransferObject;
import project.timetable_recommend.model.valueObejct.SubjectItemVO;

import java.util.ArrayList;

public class MajorSubjects{
public ArrayList<SubjectItemVO> getEssential_Subject() {
        return essential_Subject;
        }
public ArrayList<SubjectItemVO> getSelect_Subject() {
        return select_Subject;
        }
        ArrayList<SubjectItemVO> essential_Subject = new ArrayList<SubjectItemVO>();
        ArrayList<SubjectItemVO> select_Subject = new ArrayList<SubjectItemVO>();
}