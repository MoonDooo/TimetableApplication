package project.timetable_recommend.model.dataTransferObject;

import android.os.Parcel;
import android.os.Parcelable;

public class DayTimeDTO implements Parcelable{
                public DayTimeDTO(int m_day, int m_time){
            day = m_day;
            time = m_time;
        }
        int day;
        int time;

    protected DayTimeDTO(Parcel in) {
        day = in.readInt();
        time = in.readInt();
    }

    public static final Creator<DayTimeDTO> CREATOR = new Creator<DayTimeDTO>() {
        @Override
        public DayTimeDTO createFromParcel(Parcel in) {
            return new DayTimeDTO(in);
        }

        @Override
        public DayTimeDTO[] newArray(int size) {
            return new DayTimeDTO[size];
        }
    };

    public int getDay() {
        return day;
    }
    public int getTime() {
        return time;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(day);
        parcel.writeInt(time);
    }
}
