package project.timetable_recommend.adapter;

import static project.timetable_recommend.activity.controller.GsonThread.subjectList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import project.timetable_recommend.model.valueObejct.SubjectItemVO;
import project.timetable_recommend.R;

public class PreviousAdapter extends RecyclerView.Adapter<PreviousAdapter.ViewHolder>{
    public interface OnItemClickListener{
        void onItemClick(View v, int pos);
    }
    private OnItemClickListener listener = null;
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        /**
         * textView : 과목명을 보여주는 텍스트 뷰입니다.
         * textView2 : 각 과목의 정보를 보여줍니다.
         */
        TextView textView;
        TextView textView2;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.subjectId);
            textView2 = itemView.findViewById(R.id.previous_info);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION){
                        listener.onItemClick(view, pos);
                    }
                }
            });
        }
        public void setItem(SubjectItemVO m_subject){
            textView.setTextSize(15);
            textView.setText( "  " + m_subject.getSubjectName());
            textView2.setTextSize(10);
            textView2.setText("  구분 :" + m_subject.getDivision() +
                            "\n  교수명 :" +  m_subject.getProfessor()+
                            "\n  시간표 : " + m_subject.getSubjectTimetable() +
                             "\n    " + m_subject.getGrade() + "학년 " +  m_subject.getCredit() + "학점  " + m_subject.getSubjectId()  +" - " + m_subject.getSeparatedClass() );
        }
    }
    @NonNull
    @Override
    public PreviousAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.previous_item,parent,false);
        return new ViewHolder(itemView);
    }
    //여기서 subjectItemDTO에서 가져와 리사이클 뷰에 set시킵니다.
    @Override
    public void onBindViewHolder(@NonNull PreviousAdapter.ViewHolder holder, int position) {
        SubjectItemVO subject = subjectList.getSubjects().get(position);
        holder.setItem(subject);
    }
    //리사이클 사이즈는 데이터베이스에서 가져온 SUBJECT 배열의 크기입니다.
    @Override
    public int getItemCount() {
        return subjectList.getSubjects().size();
    }
}
