package mx.digitalcoaster.rzertuche.medicoencasa;

/**
 * Created by rzertuche on 1/29/18.
 */

public class Question {

    String question;
    Boolean multiple = true;

    String multiple1;
    String multiple2;
    String multiple3;
    String multiple4;
    String multiple5;

    String multiple1_id;
    String multiple2_id;
    String multiple3_id;
    String multiple4_id;
    String multiple5_id;


    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }

    public void setMultiple(Boolean multiple) {
        this.multiple = multiple;
    }

    public Boolean getMultiple() {
        return multiple;
    }

    public void setMultiple1(String multiple1) {
        this.multiple1 = multiple1;
    }

    public void setMultiple2(String multiple2) {
        this.multiple2 = multiple2;
    }

    public void setMultiple3(String multiple3) {
        this.multiple3 = multiple3;
    }

    public void setMultiple4(String multiple4) {
        this.multiple4 = multiple4;
    }

    public void setMultiple5(String multiple5) {
        this.multiple5 = multiple5;
    }

    public String getMultiple1() {
        return multiple1;
    }

    public String getMultiple2() {
        return multiple2;
    }

    public String getMultiple3() {
        return multiple3;
    }

    public String getMultiple4() {
        return multiple4;
    }

    public String getMultiple5() {
        return multiple5;
    }
}
