package project.timetable_recommend.model.dataTransferObject;

/**
 * type로 mbti 알아내고
 * feature에 특징 써줌.
 */
public class MbtiTypeDTO {
    public String type;
    public String feautre;
    public int image;

    public MbtiTypeDTO(String type , String feautre, int image){
        this.type    = type;
        this.feautre = feautre;
        this.image   = image;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFeautre() {
        return feautre;
    }

    public void setFeautre(String feautre) {
        this.feautre = feautre;
    }
    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
