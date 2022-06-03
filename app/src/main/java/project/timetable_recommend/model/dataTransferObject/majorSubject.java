package project.timetable_recommend.model.dataTransferObject;

import project.timetable_recommend.model.dataTransferObject.*;
import static project.timetable_recommend.activity.controller.GsonThread.subjectList;

import java.util.ArrayList;

public class majorSubject {

    public MajorSubjects getFirstGrade() {
        return firstGrade;
    }

    MajorSubjects firstGrade;

    public MajorSubjects getSecondGrade() {
        return secondGrade;
    }

    MajorSubjects secondGrade;

    public MajorSubjects getThirdGrade() {
        return thirdGrade;
    }

    MajorSubjects thirdGrade;

    public MajorSubjects getFourthGrade() {
        return fourthGrade;
    }

    MajorSubjects fourthGrade;
    public majorSubject(){
        firstGrade = new MajorSubjects();
        secondGrade = new MajorSubjects();
        thirdGrade = new MajorSubjects();
        fourthGrade = new MajorSubjects();
        for(int i = 0; i<subjectList.getSubjects().size(); i++){
            if(subjectList.getSubjects().get(i).getDivision().contains("전공")){
                switch(subjectList.getSubjects().get(i).getGrade()){
                    case 1:
                        if(subjectList.getSubjects().get(i).getDivision().contains("필수"))
                            firstGrade.essential_Subject.add(subjectList.getSubjects().get(i));
                        else
                            firstGrade.select_Subject.add(subjectList.getSubjects().get(i));
                        break;
                    case 2:
                        if(subjectList.getSubjects().get(i).getDivision().contains("필수"))
                            secondGrade.essential_Subject.add(subjectList.getSubjects().get(i));
                        else
                            secondGrade.select_Subject.add(subjectList.getSubjects().get(i));
                        break;
                    case 3:
                        if(subjectList.getSubjects().get(i).getDivision().contains("필수"))
                            thirdGrade.essential_Subject.add(subjectList.getSubjects().get(i));
                        else
                            thirdGrade.select_Subject.add(subjectList.getSubjects().get(i));
                        break;
                    case 4:
                        if(subjectList.getSubjects().get(i).getDivision().contains("필수"))
                            fourthGrade.essential_Subject.add(subjectList.getSubjects().get(i));
                        else
                            fourthGrade.select_Subject.add(subjectList.getSubjects().get(i));
                        break;
                }
            }
        }
    }
}
