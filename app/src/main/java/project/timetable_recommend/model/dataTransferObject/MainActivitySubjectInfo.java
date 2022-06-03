package project.timetable_recommend.model.dataTransferObject;

import android.os.Parcel;
import android.os.Parcelable;
import project.timetable_recommend.model.valueObejct.SubjectItemVO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.security.auth.Subject;

public class MainActivitySubjectInfo implements Parcelable {
    ArrayList<SubjectItemVO> subjectItemDTOS;
    public ArrayList<SubjectItemVO> getSubjectItemDTOS() {
        return subjectItemDTOS;
    }

    public MainActivitySubjectInfo(ArrayList<SubjectItemVO> subjectItemDTO){
        this.subjectItemDTOS = subjectItemDTO;
    }
    protected MainActivitySubjectInfo(Parcel in) {
        subjectItemDTOS = in.createTypedArrayList(SubjectItemVO.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(subjectItemDTOS);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MainActivitySubjectInfo> CREATOR = new Creator<MainActivitySubjectInfo>() {
        @Override
        public MainActivitySubjectInfo createFromParcel(Parcel in) {
            return new MainActivitySubjectInfo(in);
        }

        @Override
        public MainActivitySubjectInfo[] newArray(int size) {
            return new MainActivitySubjectInfo[size];
        }
    };


}
