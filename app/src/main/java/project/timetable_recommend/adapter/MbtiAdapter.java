package project.timetable_recommend.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import project.timetable_recommend.model.dataTransferObject.MbtiRecyclerViewCellDTO;
import project.timetable_recommend.model.dataTransferObject.MbtiTypeDTO;
import project.timetable_recommend.R;

public class MbtiAdapter extends RecyclerView.Adapter<MbtiAdapter.ViewHolder> {
    private ArrayList<MbtiTypeDTO> mbtiList = new ArrayList<>(); //이거 일단 스테틱으로바꿧는데(onclick때매 문제있으면 다시 static 빼자
    //뷰 홀더 원래 스태틱인데 아니도록 만들어봄
    public class ViewHolder extends RecyclerView.ViewHolder
    {
        MbtiRecyclerViewCellDTO cellText;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cellText = new MbtiRecyclerViewCellDTO();
            findViewByTevtViewID(itemView, cellText);

            // 아이템 클릭 이벤트 처리.
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition() ;
                    if (pos != RecyclerView.NO_POSITION) {
                        // TODO : use pos.
                        MbtiTypeDTO mType = mbtiList.get(pos);
                        if (mListener != null) {
                            mListener.onItemClick(v, pos) ;
                            notifyItemChanged(pos) ;
                        }
                    }
                }
            });
        }

        /**
         * 외부에서 이 함수를 통해 mbtiList에 값을 집어넣음
         */
        public void setItem(MbtiTypeDTO mbti){
            cellText.mbtiType.setText(mbti.getType());
            cellText.mbtiInfo.setText(mbti.getFeautre());
            cellText.mbtiImage.setImageResource(mbti.getImage());
        }

    }
    public interface OnItemClickListener {
        void onItemClick(View v, int position) ;
    }

    private OnItemClickListener mListener = null ;

    // OnItemClickListener 리스너 객체 참조를 어댑터에 전달하는 메서드
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener =  listener;
    }


    /**
     * inflater를 통해 뷰 홀더를 만든다.
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.mbti_item,parent,false);
        return new ViewHolder(itemView);
    }

    /**
     * position에 따라서 목록의 내용을 만든다.
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
        MbtiTypeDTO mbti = mbtiList.get(position);
        holder.setItem(mbti);
    }

    /**
     * getItemCount의 개수 만큼 목록이 생성된다.
     * 가장 중요함.
     */
    @Override
    public int getItemCount() {
        return mbtiList.size();
    }

    /**
     * mbtiCellText의 TextView mbtiType, mbtiInfo , ImageView mbtiImage 위젯의 아이디 값을 찾아 객체화 한다.
     */
    public static void findViewByTevtViewID(@NonNull View itemView, MbtiRecyclerViewCellDTO mbtiCellText){
        mbtiCellText.mbtiType  = (TextView) itemView.findViewById(mbtiCellText.widgetID[mbtiCellText.mbti_type_num]);
        mbtiCellText.mbtiInfo = (TextView) itemView.findViewById(mbtiCellText.widgetID[mbtiCellText.mbti_info_num]);
        mbtiCellText.mbtiImage = (ImageView) itemView.findViewById(mbtiCellText.widgetID[mbtiCellText.mbti_image_num]);
    }
    public void addItem(MbtiTypeDTO mbti ){
        mbtiList.add(mbti);
    }
    public void setItems(ArrayList<MbtiTypeDTO> list){
        this.mbtiList = list;
    }
    public MbtiTypeDTO getItem(int position){
        return mbtiList.get(position);
    }
    public void setItem(int position, MbtiTypeDTO item){
        mbtiList.set(position,item);
    }
}
