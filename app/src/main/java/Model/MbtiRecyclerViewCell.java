package Model;

import android.widget.TextView;

import project.timetable_recommend.R;

/**
 * 리사이클러 뷰의 목록 안 위젯 변수들을 저장하는 클래스
 */
public class MbtiRecyclerViewCell {

    /**
     * @param textID[] : 여기에 recyclerView의 card위젯 안 textView 변수의 id값을 저장한다.
     * @param mbtiType : cardView 위젯 안 첫번째 textView
     * @param mbtiInfo : cardView 위젯 안 두번째 textView
     * @param mbti_info_num : textID를 호출하기 위한 변수
     * @param mbti_type_num : "
     */
    public final int textID[] = {R.id.mbti_info, R.id.mbti_type};

    public TextView mbtiType , mbtiInfo;

    public int mbti_info_num ;
    public int mbti_type_num ;

    //생성자 함수
    public MbtiRecyclerViewCell(){
        mbti_info_num = 0;
        mbti_type_num = 1;
    }



}

