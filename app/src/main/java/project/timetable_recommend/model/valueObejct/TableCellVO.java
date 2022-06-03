
package project.timetable_recommend.model.valueObejct;

import project.timetable_recommend.*;

import android.text.TextUtils;
import android.widget.TextView;

public class TableCellVO {
    /**
     * 테이블 뷰 가로 세로 크기
     */
    private int height = 11;
    private int width  = 6;

    /**
     * 테이블 뷰의 TextView를 호출 할 수있는 객체 배열
     */
    public TextView cell[][]  = new TextView[height][width];

    /**
     * tableLayout에 속한 텍스트 뷰의 각 id값
     */
    public final int cellID[][] = {
            {R.id.emptyCell, R.id.mon0, R.id.tue0, R.id.wed0, R.id.thu0, R.id.fri0},
            {R.id.time1, R.id.mon1, R.id.tue1, R.id.wed1, R.id.thu1, R.id.fri1},
            {R.id.time2, R.id.mon2, R.id.tue2, R.id.wed2, R.id.thu2, R.id.fri2},
            {R.id.time3, R.id.mon3, R.id.tue3, R.id.wed3, R.id.thu3, R.id.fri3},
            {R.id.time4, R.id.mon4, R.id.tue4, R.id.wed4, R.id.thu4, R.id.fri4},
            {R.id.time5, R.id.mon5, R.id.tue5, R.id.wed5, R.id.thu5, R.id.fri5},
            {R.id.time6, R.id.mon6, R.id.tue6, R.id.wed6, R.id.thu6, R.id.fri6},
            {R.id.time7, R.id.mon7, R.id.tue7, R.id.wed7, R.id.thu7, R.id.fri7},
            {R.id.time8, R.id.mon8, R.id.tue8, R.id.wed8, R.id.thu8, R.id.fri8},
            {R.id.time9, R.id.mon9, R.id.tue9, R.id.wed9, R.id.thu9, R.id.fri9},
            {R.id.time10, R.id.mon10, R.id.tue10, R.id.wed10, R.id.thu10, R.id.fri10}
    };

    /**
     * 이 함수를 적용시킴으로 네온전광판? 처럼 텍스트가 옆으로 이동합니다.
     */
    public void addNeonMovingEffectInTextView(TextView t){
        t.setSingleLine();
        t.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        t.setSelected(true);
    }



    //클래스의 멤버 변수 get, set 함수.
    public TextView[][] getCell() {
        return cell;
    }

    public void setCell(TextView[][] cell) {
        this.cell = cell;
    }

    public int[][] getCellID() {
        return cellID;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
