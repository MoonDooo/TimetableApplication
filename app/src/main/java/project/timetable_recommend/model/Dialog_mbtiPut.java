package project.timetable_recommend.model;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

import project.timetable_recommend.R;

public class Dialog_mbtiPut extends Dialog {
    private Button   dlg_save;
    private EditText dlg_mbti_input;
    private Context  context;
    private String   userMbtiType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_mbti_input);

        //다이얼로그 뒷 배경 투명하게 만듬
        Objects.requireNonNull(getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        dlg_save = findViewById(R.id.dialog_buttonSave);
        dlg_mbti_input = findViewById(R.id.put_text);

        //객체에 리스너 전달
        dlg_save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                boolean flag = false;
                while (!flag){
                    userMbtiType = dlg_mbti_input.getText().toString();
                    //예외(소문자로 MBTI 기입한 경우)처리를 위해 사용자가 입력한 문자열을 대문자로 처리
                    userMbtiType = userMbtiType.toUpperCase();

                    //정상적으로 MBTI 기입한 경우
                    if (userMbtiType.equals("ISFP") ||
                            userMbtiType.equals("ISFJ") ||
                            userMbtiType.equals("ISTP") ||
                            userMbtiType.equals("ISTJ") ||
                            userMbtiType.equals("INFP") ||
                            userMbtiType.equals("INFJ") ||
                            userMbtiType.equals("INTP") ||
                            userMbtiType.equals("INTJ") ||

                            userMbtiType.equals("ESFP") ||
                            userMbtiType.equals("ESFJ") ||
                            userMbtiType.equals("ESTP") ||
                            userMbtiType.equals("ESTJ") ||
                            userMbtiType.equals("ENFP") ||
                            userMbtiType.equals("ENFJ") ||
                            userMbtiType.equals("ENTP") ||
                            userMbtiType.equals("ENTJ")
                    )
                    {
                        savedInstanceState.putString("user_mbti",userMbtiType);
                        dismiss();
                    }
                    else
                    {
                        //MBTI 정확하게 기입하지 않은 경우
                        Toast.makeText(context.getApplicationContext(),
                                "MBTI를 정확히 입력해주세요.", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
    public Dialog_mbtiPut(Context mContext) {
        super(mContext);
        this.context      = mContext;
    }
}
