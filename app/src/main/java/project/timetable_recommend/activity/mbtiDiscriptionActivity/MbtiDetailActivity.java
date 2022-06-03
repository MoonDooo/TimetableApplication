package project.timetable_recommend.activity.mbtiDiscriptionActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import project.timetable_recommend.R;

public class MbtiDetailActivity extends AppCompatActivity {

    /**
     * @param img_name : 이미지 id값
     * @param mbti_type : 텍스트 mbti 종류 문자열
     * @param Content : 현재 화면 인스턴스 얻어오는 변수
     * @param btn_back: 뒤로가기 객체
     * @param img_thumbnail : mbti 이미지 객체
     * @param mbti_text_type : 텍스트 mbti 객체
     * @param mbti_text_introduce : mbti detail한 설명
     */
    private int       img_name;
    private String    mbti_type;
    private Context   mContext;
    private ImageView btn_back;
    private ImageView img_thumbnail;
    private TextView  mbti_text_type;
    private TextView  mbti_text_introduce;

    /**
     * 이 Activity 출력 될 때 mContent에 현재 화면 객체 얻어냄
     * getData()를 통해 intent에서 값 빼옴
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mbtidetail);
        mContext = this;
        getData();
    }

    /**
     * .makeSceneTransitionAnimation func 를 통해 이 화면에 들어 왔을 때
     * setInit을 통해 위젯 변수들 객체 얻어옴
     */
    @Override
    public void onEnterAnimationComplete() {
        super.onEnterAnimationComplete();
        setInit();
    }

    /**
     * 인텐트를 통해 이전 화면에서 키값을 통해 value 받아옴.
     */
    public void getData() {
        Intent intent = getIntent();
        img_name      = (int) intent.getSerializableExtra("mbtiImage");
        mbti_type     = (String) intent.getSerializableExtra("mbtiType");
    }

    /**
     * 에니메이션 작동에 의해 이 화면이 로딩 됬을 때
     * 이 클래스의 위젯멤버변수 객체 인스턴스 얻어옴
     * Glide를 통해 이미지를 불러왔습니다.
     */
    public void setInit() {
        btn_back            = (ImageView) findViewById(R.id.btn_back);
        img_thumbnail       = (ImageView) findViewById(R.id.mbti_image2);
        mbti_text_type      = (TextView) findViewById(R.id.text_mbti_title);
        mbti_text_introduce = (TextView) findViewById(R.id.text_mbti_introduce);
        Glide.with(this)
                .load(img_name) // Load the image resource
                .into(img_thumbnail);

        //img_thumbnail.setImageResource(img_name);
        mbti_text_type.setText(mbti_type);
        mbti_text_introduce.setText(setDetailMbtiText(mbti_type));
        mbti_text_introduce.setMovementMethod(new ScrollingMovementMethod());
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
    /**
     *
     * @param mbti_type
     * @return mbti type 에 따라 상세 설명 string을 반환합니다.
     */
    public String setDetailMbtiText(String mbti_type){
        String contents;
        switch(mbti_type){
            case "INTJ":
                contents =
                                "구상과 파악은 거시적으로 처리하고, 지식과 사고는 수렴적으로 파고들어 나아감\n" +
                                "이성과 논리를 바탕으로 세상 모든 대상을 개념화하는 것을 선호함\n" +
                                "무엇이든 체계와 진행 방식부터 이해하려고 함\n" +
                                "질서 있는 추론을 사용하여 흥미로운 문제를 해결하고 싶어 함\n" +
                                "창의적 사고(참신한 발상)를 탐구하는 것을 지향함\n" +
                                "합리성을 적용하는 것을 우선시함\n" +
                                "색다른 관점이나 반대 의견에 더 주목하는 경향이 있음\n" +
                                "내용의 실존성 · 진실 여부를 중시하는 편임\n" +
                                "내용의 깊이(detail and depth)를 중시하는 편임\n" +
                                "사회 통념을 회의적으로 바라볼 줄 알며 때로는 전문 지식에도 의문을 제기할 수 있음\n";
                break;
            case "INTP":
                contents =
                                "눈치는 빠르지만 눈치를 안본다\n" +
                                "대체로 센스가 좋고 유머감각이 뛰어나기에 사람들이 좋아하는 경우가 많지만 정작 INTP는 혼자있는 것을 좋아한다\n" +
                                "16 유형 중 천재 발현율이 가장 높다\n" +
                                "무언가에 혼자 몰두하는 것을 즐기므로 연구원, 학자, 프로그래머, 프로게이머, 작가 등의 직업에서 높은 능률을 발휘할 수 있다\n" +
                                "자신만의 세계가 매우 뚜렷한 편이다\n" +
                                "개인주의 성향이 강하며 자신의 사생활, 비밀, 퍼스널 스페이스를 침해받는 것을 매우 싫어한다\n" +
                                "논리력이 뛰어나므로 토론, 발표 등에서 유리하다\n" +
                                "화를 잘 안 낸다. 진실에 어긋나는 것이 아니면 적당히 넘어간다\n" +
                                "겉과 속이 일치한다\n" +
                                "아랫사람에겐 따뜻하고, 윗사람에겐 엄격하다.\n";
                break;
            case "ENTJ":
                contents =
                                "외향형 중 제일 내향적이므로 타 외향형이 보기에 대인 교류에 그다지 적극적이지 않다\n" +
                                "직관형(N) 중에서는 감각형(S)에 가장 가깝다\n" +
                                "이론가의 성향이 있다\n" +
                                "조직적, 체계적, 계획적이다. 구조화와 체계 수립 및 플랜 설계와 통제, 정책에 매우 탁월하다\n" +
                                "빠른 추진력으로 인해 일은 일대로 하고 욕은 욕대로 먹을 수 있다\n" +
                                "항상 계획을 하고 반드시 실행한다. 단계에 맞게 목표를 세우고 달성하는 것이 이들이 사는 이유이자 큰 행복감을 느낀다\n" +
                                "자신의 분야에서 완벽주의를 추구하기 때문에 남이 비집고 들어갈 틈이 없다\n" +
                                "상상을 많이 하고 호기심이 많다. 아이디어가 도출되나 의미없는 것에 시간 낭비를 할 수도 있다\n" +
                                "고집이 세 보이지만, 논리적으로 납득 가능한 비판에 대한 수용은 빠른 편이다.\n" +
                                "일을 진행할 때 일 자체에 관심을 두고, 그 일과 관련된 사람에 대해서는 상대적으로 관심도가 낮다\n";
                break;
            case "ENTP":
                contents =
                                "외향형 중 제일 내성적인 사람이 많다. 타 외향형이 보기에 대인 교류에 그다지 적극적이지 않은 ENTP유형이 드물지 않게 관찰되곤 한다\n" +
                                "외향형이지만 혼자만의 시간도 많이 필요로 하며 중요하게 여긴다\n" +
                                "알고만 지내는 사이와 친한 사이의 선을 확실하게 긋는다\n" +
                                "주기능인 Ne(외향 직관)으로 인해 두뇌 회전이 빠르며 사안에 대한 직관력을 발휘해 대략적인 이해가 뛰어나다\n" +
                                "말하거나 글 쓰는 재능이 있는 경우가 많다\n" +
                                "복잡한 문제의 지름길을 발견하는 것에 능하다. 뛰어난 통찰력과 별개의 것을 연결하는 등 창의력이 뛰어난 유형\n" +
                                "상담과 조언을 요구할 때에는 단순히 상대방의 의견이 궁금해서일 뿐이다\n" +
                                "모험심이 강하며, 위험을 감수해서라도 새로운 시도를 하기도 한다\n" +
                                "개방적이고 자유를 추구하며, 억압당하는 것을 견디지 못한다\n" +
                                "벼락치기식 공부를 선호하는 경향이 있다\n";
                break;
            case "INFJ":
                contents =
                                "3차 기능이 Ti(내향 사고)로 겉으로는 공감할 수 있지만, 속으로는 비판적으로 생각하는 경향이 있다\n" +
                                "본인만의 철칙이 뚜렷하여 고집이 세다고 느껴질 수 있다\n" +
                                "감정적이면서 동시에 이성적이다\n" +
                                "목적과 의미가 있는 일에 대해 열정적이다\n" +
                                "번아웃에 취약한 유형이다. 반복적이고 의미 없는 일이나 과도한 업무량, 불편한 인간관계 등에 쉽게 지친다\n" +
                                "겉보기에는 아주 차갑지만, 속은 난로처럼 따스하다\n" +
                                "사람이나 일에 있어 호불호가 분명히 나뉜다\n" +
                                "호기심이 많고, 열정적이며 언제나 의문을 갖는다. 가슴 속에 묻어둔 질문들이 많다\n" +
                                "자아성찰을 자주 하는 편이며, 본인 스스로에게 매우 엄격하다\n" +
                                "스스로 옳다고 확신이 생긴 신념은 끝까지 관철해 나간다\n";
                break;
            case "INFP":
                contents =
                                "개인주의자다. 이들은 각 개인이 꼭 보편적인 길을 선택할 필요 없이 각자만의 길을 찾아가야 한다고 믿는다\n" +
                                "이상주의자다. 자신이 지향하는 이상에 대해서는 정열적인 신념을 지니고 있다\n" +
                                "복잡한 상황에서는 잘 견디지만, 반복되는 일에는 인내심이 없다\n" +
                                "창의력이 뛰어나고 새로운 아이디어와 정보를 잘 수용하는 편이다\n" +
                                "호기심이 많고, 어떠한 일의 결과보다 '가능성'을 보는 경향이 있으며, 아이디어를 수행하기 위한 촉매 역할을 한다\n" +
                                "사람들의 본질을 이해하려 하고, 이들의 가능성을 성취할 수 있도록 돕는다\n" +
                                "타인의 감정에 민감하고, 좋아하는 사람을 기쁘게 하는 것을 즐거워한다\n" +
                                "마음이 따뜻하나 상대방과 친해지기 전까지 자신의 따뜻함을 잘 표현하지 않으며 오히려 조용하고 과묵하다\n" +
                                "소수의 특별한 사람들과 매우 깊게 아주 정열적으로 대의에 대해 관심을 갖는다\n" +
                                "추함, 선과 악 등 도덕적인 것과 비도덕적인 것에 대하여 민감하게 반응한다\n";
                break;
            case "ENFJ":
                contents =
                                "직관(N)형들 중에서 가장 외향적이다\n" +
                                "전형적인 모범이 되는 학생회장 유형이다\n" +
                                "군중을 이끄는 뛰어난 리더십이 있다\n" +
                                "말하는 재능, 글쓰는 재능이 대단히 뛰어나다\n" +
                                "대중과 상대방의 분위기를 읽는 공감능력이 탁월하기에, 유머감각이 뛰어나다\n" +
                                "사람을 굉장히 좋아한다. 연민과 동정, 이해심이 대단히 많다\n" +
                                "인생과 인간을 따뜻하게 바라본다. 감정이입이 너무 잘돼서 스스로도 스트레스를 받는다\n" +
                                "모든 사람들이 특별하다고 생각한다. 세상 모든 사람이 함께 행복했으면 좋겠다고 생각한다\n" +
                                "타인의 관심사에 귀 기울이며 그들을 배려한다\n" +
                                "사람을 좋아해 대가 없는 베품을 굉장히 좋아한다. 호감있는 사람에게는 간이고 쓸개고 빼 주는 편이다\n";
                break;
            case "ENFP":
                contents =
                                "감정이 얼굴에 잘 드러난다. 인정을 받으려는 욕구가 강하고 타인의 시선에 민감하다\n" +
                                "관심 받기를 원하며 생각이 많고 예민하다. 새로운 시도를 좋아한다\n" +
                                "계획하기보다는 그때그때 일을 처리하는 편이다\n" +
                                "감동을 잘하고 눈물도 잘 흘린다\n" +
                                "경제 관념이 희박하여 돈을 모으기는 힘들다\n" +
                                "작은 일에도 감정의 기복이 심하다\n" +
                                "내면은 소심하여 자주 삐친다. 그러나 삐치더라도 몇 시간 후면 다시 원래대로 돌아온다\n" +
                                "내면에 열정을 지녔다. 위기 대처능력이 뛰어나다\n" +
                                "행사나 일을 잘 주선한다. 다만 보통 이걸 하자! 선에서 끝나며, 구체적인 계획은 없다\n" +
                                "타인이 보기엔 자칫 허영이라 느낄 정도로, 멋 내고 뽐내는 것을 굉장히 좋아한다\n";
                break;
            case "ISTJ":
                contents =
                                "낯가림이 심한 편이다. 지나고 난 다음에 따지는 편이다\n" +
                                "주어진 업무나 책임을 끝까지 완수한다. 예고없이 갑작스러운 변화를 매우 싫어한다\n" +
                                "실수한 것을 참지 못하고 즉각 수정하기를 원한다\n" +
                                "남들이 속을 모른다고 말한다. 휴일에도 집에서 주로 지낸다 \n" +
                                "정리정돈을 해놓는 것이 우선이다\n" +
                                "직설적인 표현을 많이 하는 편이다(주로 가까운 사람들에게)\n" +
                                "현실성, 대중성을 중시하므로 취미를 가져도 일반적인 것, 아니면 전통적인 것을 선호한다\n" +
                                "건강하지 않을 경우, 자신이 속해 있는 소속 내의 사람들을 관리하고 통제하려는 경향이 있다\n" +
                                "남들이 본인의 일에 참견하는 것을 싫어한다\n" +
                                "보편적인 요소와 일들에 많은 흥미를 가지며 소위 말하는 특이한 것과 튀는 것에는 관심이 적은 편이다\n";
                break;
            case "ISFJ":
                contents =
                                "전통과 조직에 충실한 관리적인 성격이다\n" +
                                "실용적이고 동정심이 많으며, 다른 사람들을 보살피며 위험으로부터 보호하려고 한다\n" +
                                "검증된 방법과 확실한 가치에 기인하고, 사회에 받아들여진 일을 하는 것을 원한다\n" +
                                "안정적인 삶을 지향하지만, 본인이 이해 또는 존경받는다고 생각되는 범위에서 변화를 수용할 수 있다\n" +
                                "다른 사람들에 대한 깊은 책임감을 가지며 꾸준하고 헌신적이다\n" +
                                "특히 다른 사람들의 욕구를 충족시킬 때 의무를 다한다\n" +
                                "다른 사람들에게 자신이 믿을만 하고, 나에 대한 신뢰가 있기를 바란다\n" +
                                "개인적인 가치에 따라 행동하며 양심적이다\n" +
                                "관계를 중요시 여기고 조화를 유지하기 위해 노력한다. 조용하고 내성적인 반면 관계술에 뛰어나 인간관계를 잘 만들어간다\n" +
                                "그룹에서 역사가의 역할을 맡아 새로운 회원이 기존의 관습을 존중하고 가치있게 여기도록 한다\n";
                break;
            case "ESTJ":
                contents =
                                "현실적, 구체적, 사실적이며 어떠한 활동을 조직화하고 주도해 나가는 지도력이 있다\n" +
                                "실질적이고 현실 감각이 뛰어나며 일을 조직하고 계획하여 추진시키는 능력이 있다\n" +
                                "타고난 지도자로서 프로젝트의 목표를 설정하고, 지시하고, 결정하고, 독려하여 기한 내에 철저히 이행하는 능력이 있다\n" +
                                "불확실한 미래의 가능성보다 흔들리지 않는 현재의 사실을 추구하기 때문에 현실중심적, 실용적인 면이 강하다\n" +
                                "건강한 유형은 타인에 대한 지적을 자제하고, 타인의 성격과 능력치를 상식적, 논리적으로 고려하여 업무를 배분한다\n" +
                                "리더십과 안정성을 중시하는 성격으로 경제력이 좋은 유형이다\n" +
                                "조직적이고 절도 있으며 무게감이 있는 선배, 교육자, 상사, 사장, 가부장적인 부모 등의 모습을 많이 보여준다\n" +
                                "츤데레다\n" +
                                "스스로 빡세게 일을 하며, 현실적이면서 커다란 도전을 좋아하는 유형이기도 하다\n" +
                                "현실적이고 실용적이며 효율성을 중시하기 때문에 자본주의 한국사회에서 도태될 가능성이 매우 낮은 유형이기도 하다\n";
                break;
            case "ESFJ":
                contents =
                                "스트레스를 받으면 누군가를 만나야 한다\n" +
                                "사교성은 매우 뛰어나지만, 노는 것은 별로 좋아하지 않는다\n" +
                                "참을성이 많고 타인을 잘 돕는다\n" +
                                "남에게 동조하는 경향이 뛰어나며 사람들과의 상호 활동에서 기력이 생긴다\n" +
                                "INTP에 주로 많이 끌리며 매우 높은 관심과 애정을 가진 경우가 많다\n" +
                                "조화와 균형을 중요시한다\n" +
                                "집단의 일이나 목적을 개인의 이익보다 앞세운다\n" +
                                "관계에서 중재자 역할을 자처한다\n" +
                                "명확하고 활발한 칭찬표현이 없을시 실망하는 경향이있다\n" +
                                "1대 1보다는 다수 속에 있는 것을 좋아한다\n";
                break;
            case "ISTP":
                contents =
                                "마음에 없는 얘기를 상대방 기분 때문에 하지 않는다\n" +
                                "일반적으로 조용한 편이나 필요에 따라 사교적이다\n" +
                                "틀에 박히고 통념적인 생활을 싫어한다\n" +
                                "타인의 일에 무관심한 편이다\n" +
                                "고집이 세고 주관이 뚜렷하다\n" +
                                "말이 없고 내색을 않는다\n" +
                                "객관적 원리에 관심이 많다\n" +
                                "느낌과 감정, 타인에 대한 마음을 표현하기 어려워한다\n" +
                                "정밀을 요하는 일을 잘 해낸다\n" +
                                "시사적인 분석이나 문제에 열중한다\n";
                break;
            case "ISFP":
                contents =
                                "화가 나더라도 차분하고 친절하게 의견을 표현한다\n" +
                                "남에게 싫은 소리 못하고 속으로 삭인다\n" +
                                "다른 사람의 부탁을 거절하기 어려워한다\n" +
                                "남을 잘 믿고 의심하지 않는다\n" +
                                "분쟁을 싫어하며 조화롭게 지내고자 노력한다\n" +
                                "잘 단련된 인내심을 가지고 있다. 내향(I)형답게 혼자만의 시간을 즐긴다\n" +
                                "지시적이고 전통적인 성향이 강한 상대방과 상성이 좋지 않다\n" +
                                "체계적이고 결단력있게 살고자 노력한다\n" +
                                "조용히 손절하는 편이다. 싫은 티를 크게 내지 않고 지나가는 편이다\n" +
                                "어색한 사람과 있으면 몸부터 굳는다. 이 사람이 나를 지루해할까 봐 아무 말이나 나누면서 적응하려고 노력한다\n";
                break;
            case "ESTP":
                contents =
                                "내기를 좋아하며, 삶을 즐기며 산다\n" +
                                "스릴을 좋아한다\n" +
                                "매우 빠르게 생각과 행동을 하고 현실적이기 때문에 감정적이거나 우유부단한 사람에 대해 답답해 하는 경향이 있다\n" +
                                "무대의 중심에 서는 것과 관심을 즐긴다\n" +
                                "타고난 해결사로써 문제를 해결하는 능력이 타의 추종을 불허한다\n" +
                                "흔히 오감이라고 부르는 시각, 청각, 후각, 미각, 촉각의 신경이 전부 예민하게 잘 발달되어 있어 이를 통한 촉이 정말 좋다\n" +
                                "미래지향적인 생각을 잘 하지 않고 현재지향적인 생각을 많이 한다\n" +
                                "언제나 사전의 이론 공부보다는 상황에 직접 뛰어들어 체험을 통해 경험하는 것을 더 선호하는 활동적인 성격이다\n" +
                                "대학교 평균 학점이 가장 낮다\n" +
                                "교사가 ESTP일 때 학생이 가장 학교에 잘 적응하는 것으로 나타났다\n";
                break;
            case "ESFP":
                contents =
                                "Se(외향 감각)이 주기능이라서 예민한 신경으로 인한 오감 발달과 인지 능력이 발달되어 있다\n" +
                                "신나고 재미있는 성격이다\n" +
                                "모든 MBTI 유형 중 외향 성향이 ESFJ와 함께 가장 강한 편이다\n" +
                                "천성적으로 사교적인 성격으로, 쉽게 사람들과 친해진다\n" +
                                "예전의 일도 쉽게 잊고 잘 털어내는 편이며, 예전 일을 들먹이며 뒤끝이 긴 사람들을 불편해하는 편이다\n" +
                                "타인을 기쁘게 해 줄 깜짝쇼를 준비하면서 즐거워한다\n" +
                                "틀에 박힌 것을 싫어하고, 계획에 따라 행동하는 것을 힘들어한다\n" +
                                "개성적인 매력이 있는 편이다\n" +
                                "무계획적이고 충동적인 여행을 즐긴다\n" +
                                "현재를 즐기며 주어진 삶에 감사하는 사람들이다\n";
                break;
            default:
                contents = "";
                break;
        }
        return contents;
    }
}