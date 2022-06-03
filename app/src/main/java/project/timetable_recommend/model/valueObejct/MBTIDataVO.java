package project.timetable_recommend.model.valueObejct;

import project.timetable_recommend.model.dataTransferObject.MbtiTypeDTO;
import project.timetable_recommend.R;

public class MBTIDataVO {

    MbtiTypeDTO mbti_type[];

    public int imageID[] = {R.drawable.mbti_image_intj,R.drawable.mbti_image_intp,R.drawable.mbti_image_entj,R.drawable.mbti_image_entp,
            R.drawable.mbti_image_infj,R.drawable.mbti_image_infp,R.drawable.mbti_image_enfj,R.drawable.mbti_image_enfp,
            R.drawable.mbti_image_istj,R.drawable.mbti_image_isfj,R.drawable.mbti_image_estj,R.drawable.mbti_image_esfj,
            R.drawable.mbti_image_istp,R.drawable.mbti_image_isfp,R.drawable.mbti_image_estp,R.drawable.mbti_image_esfp};

    String mbti_type_data[][] =
            {
                {"INTJ","\"상상력이 풍푸하며 철두 철미한 계획을 세우는 전략가형\""},
                {"INTP", "\"끊임없이 새로운 지식에 목말라 하는 혁신가형\""},
                {"ENTJ","\"다양한 방법을 모색하거나 여의치 않을 경우 새로운 방안을 창출하는 리더형\""},
                {"ENTP","\"지적인 도전을 두려워하지 않는 똑똑한 호기심형\""},
                {"INFJ", "\"조용하고 신비로우며 샘솟는 영감으로 지칠 줄 모르는 이상주의자\""},
                {"INFP", "\"상냥한 성격의 이타주의자로 건강하고 밝은 사회 건설에 앞장서는 낭만형\""},
                {"ENFJ","\"넘치는 카리스마와 영향력으로 청중을 압도하는 리더형\""},
                {"ENFP", "\"사람들과 자유롭게 어울리기를 좋아하는 넘치는 열정의 소유자\""},
                {"ISTJ","\"사실에 근거하여 사고하며 이들의 행동이나 결정 사항에 한 치의 의심을 사지 않는 현실주의자형\""},
                {"ISFJ","\"소중한 이들을 수호하는 데 심혈을 기울이는 헌신적이며 성실한 방어자형\""},
                {"ESTJ", "\"사물이나 사람을 관리하는 데 타의 추종을 불하하는 뛰어난\""},
                {"ESFJ", "\"타인을 향한 세심한 관심과 사교적인 성향으로 사람들 내에서 인기가 많으며, 타인을 돕는데 열성적인 세심형\""},
                {"ISTP","\"대담하고 현실적인 성향으로 다양한 도구 사용에 능숙한 탐험형\""},
                {"ISFP", "\"항시 새로운 것을 찾아 시도하거나 도전할 준비가 되어 있는 융통성 있는 성격의 매력 넘치는 예술가형\""},
                {"ESTP","\"벼랑 끝의 아슬아슬한 삶을 진정으로 즐길 줄 아는 이들로 명석한 두뇌와 에너지, 그리고 뛰어난 직관력을 가지고 있는 유형\""},
                {"ESFP","\"주위에 있으면 인생이 지루할 새가 없을 정도로 즉흥적이며 열정과 에너지가 넘치는 연예인형\""},
            };

    /**
     * 생성자 함수
     * 여기에 inputMbtiData() func 를 통해 mbti_type에 데이터를 집어 넣는다.
     */
    public MBTIDataVO(){
        mbti_type = new MbtiTypeDTO[mbti_type_data.length];
        this.inputMbtiData();
    }

    /**
     * mbti_type에 데이터를 저장하는 func
     */
    public void inputMbtiData(){
        for (int i = 0; i< this.mbti_type_data.length; i ++){
            mbti_type[i] = new MbtiTypeDTO(this.mbti_type_data[i][0],this.mbti_type_data[i][1], this.imageID[i]);
        }
    }

    /******************************************** 멤버변수 호출하는 함수들 ***************************************************/


    public MbtiTypeDTO[] getMbti_type() {
        return mbti_type;
    }

    public void setMbti_type(MbtiTypeDTO[] mbti_type) {
        this.mbti_type = mbti_type;
    }

    public String[][] getMbti_type_data() {
        return mbti_type_data;
    }

    public void setMbti_type_data(String[][] mbti_type_data) {
        this.mbti_type_data = mbti_type_data;
    }
}
