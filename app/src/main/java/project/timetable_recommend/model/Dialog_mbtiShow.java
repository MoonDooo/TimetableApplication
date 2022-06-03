package project.timetable_recommend.model;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Objects;
import project.timetable_recommend.R;

public class Dialog_mbtiShow extends Dialog {

    private Button   dlg_ok_button;
    private TextView dlg_mbti_text;
    private String   user_mbti;
    private Context  context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_mbti_show);
        //다이얼로그 뒷 배경 투명하게 만듬
        Objects.requireNonNull(getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        dlg_ok_button = findViewById(R.id.dialog_button_ok);
        dlg_mbti_text = findViewById(R.id.dialog_mbti_result);
        String text = "당신의 mbti는 " + user_mbti + "입니다.";
        dlg_mbti_text.setText(text);

        //객체에 리스너 전달
        dlg_ok_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                dismiss();
            }
        });
    }
    public Dialog_mbtiShow(Context mContext, String mbti) {
        super(mContext);
        this.context   = mContext;
        this.user_mbti = mbti;
    }
}
