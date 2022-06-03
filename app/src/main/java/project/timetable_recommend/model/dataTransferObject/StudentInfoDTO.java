package project.timetable_recommend.model.dataTransferObject;

import java.io.Serializable;

public class StudentInfoDTO implements Serializable {
    int grade; //학년
    int majorScore; //수강할 전공 학점 합계
    int cultureScore; //수강할 교양 학점 합계
    int subjectId; //학수번호, 절대 0으로 시작하지 않기때문에 int형으로 구현해도 됨

    public StudentInfoDTO() {
        grade = 0;
        majorScore = 0;
        cultureScore = 0;
        subjectId = 0;
    }
    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getMajorScore() {
        return majorScore;
    }

    public void setMajorScore(int majorScore) {
        this.majorScore = majorScore;
    }

    public int getCultureScore() {
        return cultureScore;
    }

    public void setCultureScore(int cultureScore) {
        this.cultureScore = cultureScore;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

}
