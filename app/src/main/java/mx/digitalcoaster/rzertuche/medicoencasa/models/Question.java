package mx.digitalcoaster.rzertuche.medicoencasa.models;

import io.realm.RealmObject;

/**
 * Created by rzertuche on 1/29/18.
 */

public class Question extends RealmObject {

    String question;
    int order = 999;
    Boolean multiple = true;

    String multiple1;
    String multiple2;
    String multiple3;
    String multiple4;
    String multiple5;
    String multiple6;
    String multiple7;
    String multiple8;

    String category;
    String answer;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    String status = "verde";


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

    public void setMultiple6(String multiple6) {
        this.multiple6 = multiple6;
    }
    public void setMultiple7(String multiple7) {
        this.multiple7 = multiple7;
    }
    public void setMultiple8(String multiple8) {
        this.multiple8 = multiple8;
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

    public String getMultiple6() {
        return multiple6;
    }

    public String getMultiple7() {
        return multiple7;
    }

    public String getMultiple8() {
        return multiple8;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }


    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
