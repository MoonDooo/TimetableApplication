package project.timetable_recommend;

import static Controller.GsonThread.subjectList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

import Controller.PreviousAdapter;
import Model.PreviousSelectedColor;
import Model.SubjectItemDTO;
import Model.TableCellDTO;

public class PreviousLayout3 extends AppCompatActivity {
    PreviousAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView recyclerView;
    TableCellDTO c;
    public static Context context_main;
    private ImageView btn_back;
    boolean[][] checkSubject;
    String[][] checkColor;
    Button button5;
    ArrayList<SubjectItemDTO> select_item = new ArrayList<SubjectItemDTO>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous_layout3);
        button5 = findViewById(R.id.button6);
        //초기화 시키는 임시 버튼, 나중에 삭제 기능 추가하면 삭제 예정
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences pref = getSharedPreferences("pref3", Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.clear();
                editor.commit();
                for (int i = 1; i < c.getHeight(); i++) {
                    for (int j = 1; j < c.getWidth(); j++) {
                        c.cell[i][j].setText(null);
                        c.cell[i][j].setBackgroundColor(Color.WHITE);
                        checkSubject[i][j] = false;
                    }
                }
            }
        });
        context_main = this;
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        c = new TableCellDTO();
        findTextViewById(c);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        adapter = new PreviousAdapter();
        ////////////////////
        checkSubject = new boolean[c.getHeight()][c.getWidth()];
        checkColor = new String[c.getHeight()][c.getWidth()];
        for (int i = 1; i < (c.getHeight() - 1); i++) {
            for (int j = 1; j < (c.getWidth() - 1); j++) {
                checkSubject[i][j] = false;
            }
        }
        adapter.setOnItemClickListener(
                new PreviousAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View v, int pos) {
                        select_item.add(subjectList.getSubjects().get(pos));
                        PreviousSelectedColor temp = new PreviousSelectedColor();
                        String color = temp.getColor();
                        int tmp_day = 0;
                        int tmp_time = 0;
                        int checked_day = 0;
                        int checked_time = 0;
                        boolean checked_subjectTime = false;
                        for (int i = 0; i < subjectList.getSubjects().get(pos).getSubject_day().size(); i++) {
                            checked_day = subjectList.getSubjects().get(pos).getSubject_day().get(i).getDay();
                            checked_time = subjectList.getSubjects().get(pos).getSubject_day().get(i).getTime();
                            if (checkSubject[checked_time][checked_day]) {
                                Toast.makeText(getApplicationContext(), "중첩된 시간표가 있습니다.", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }
                        for (int i = 0; i < subjectList.getSubjects().get(pos).getSubject_day().size(); i++) {
                            tmp_day = subjectList.getSubjects().get(pos).getSubject_day().get(i).getDay();
                            tmp_time = subjectList.getSubjects().get(pos).getSubject_day().get(i).getTime();
                            if (i == 0 && tmp_day != 0 && tmp_time != 0) c.cell[tmp_time][tmp_day].setText(subjectList.getSubjects().get(pos).getSubjectName()); // 시간표에 추가되는 부분
                            checkSubject[tmp_time][tmp_day] = true;
                            c.cell[tmp_time][tmp_day].setBackgroundColor(Color.parseColor(color));
                            checkColor[tmp_time][tmp_day] = color;
                        }
                    }
                }
        );
        ////////////////////
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        setInit();
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveState();
    }

    //지금까지 정한 시간표가 저장됩니다.
    protected void saveState() {
        SharedPreferences pref = getSharedPreferences("pref3", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        for (int i = 1; i < (c.getHeight()); i++) {
            for (int j = 1; j < (c.getWidth()); j++) {
                editor.putString("layout3String" + i + "," + j, c.cell[i][j].getText() + "");
                editor.putBoolean("layout3boolean" + i + "," + j, checkSubject[i][j]);
                if(checkColor[i][j]!=null) editor.putString("layout3StringColor"+ i+","+j, checkColor[i][j]);
            }
        }
        editor.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        restoreState();

    }

    //불러옵니다.
    protected void restoreState() {
        SharedPreferences pref = getSharedPreferences("pref3", Activity.MODE_PRIVATE);
        if (pref != null) {
            for (int i = 1; i < c.getHeight(); i++) {
                for (int j = 1; j < c.getWidth(); j++) {
                    if (pref.contains("layout3String" + i + "," + j)) {
                        c.cell[i][j].setText(pref.getString("layout3String" + i + "," + j, ""));
                        checkSubject[i][j] = pref.getBoolean("layout3boolean" + i + "," + j, false);
                        if(pref.contains("layout3StringColor"+ i+","+j)) {
                            c.cell[i][j].setBackgroundColor(Color.parseColor(pref.getString("layout3StringColor" + i + "," + j, "")));
                        }
                    }
                }
            }
        }
    }

    public void findTextViewById(TableCellDTO tCell) {
        for (int y = 0; y < tCell.getHeight(); y++) {
            final int curY = y;
            for (int x = 0; x < tCell.getWidth(); x++) {
                final int curX = x;
                tCell.cell[curY][curX] = findViewById(tCell.cellID[curY][curX]);
            }
        }
    }

    public void setInit() {
        btn_back = (ImageView) findViewById(R.id.btn_back);
        //뒤로가기 이벤트 헨들러
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btn_back:
                        supportFinishAfterTransition();
                        break;
                }
            }
        });
    }

}