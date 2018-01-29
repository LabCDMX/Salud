package mx.digitalcoaster.rzertuche.medicoencasa;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.text.Html;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by rsonckjr on 9/10/15.
 */
public class QuestionDialog {
    Dialog dialog;
    Question currentQuestion;

    TextView question;
    RadioGroup multiples;
    RadioButton multiple1;
    RadioButton multiple2;
    RadioButton multiple3;
    RadioButton multiple4;
    RadioButton multiple5;
    EditText answer;

    LinearLayout multiple;
    LinearLayout open;

    ImageButton next;
    ArrayList<Question> questions;

    Activity activity;

    int indexCurrentQuestion = 0;

    public QuestionDialog(Activity context, ArrayList<Question> questions){
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_question);

        this.activity = context;
        this.questions = questions;

        open = (LinearLayout) dialog.findViewById(R.id.open);
        multiple = (LinearLayout) dialog.findViewById(R.id.multiple);


        question = (TextView) dialog.findViewById(R.id.question);
        answer = (EditText) dialog.findViewById(R.id.answer);
        multiple1 = (RadioButton) dialog.findViewById(R.id.multiple1);
        multiple2 = (RadioButton) dialog.findViewById(R.id.multiple2);
        multiple3 = (RadioButton) dialog.findViewById(R.id.multiple3);
        multiple4 = (RadioButton) dialog.findViewById(R.id.multiple4);
        multiple5 = (RadioButton) dialog.findViewById(R.id.multiple5);

        multiples = (RadioGroup) dialog.findViewById(R.id.multiples);

        loadQuestion();

        next = (ImageButton) dialog.findViewById(R.id.next);
        next.setOnClickListener(nextOnClickListener);
    }

    private View.OnClickListener nextOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if(currentQuestion.getMultiple()){
                int selectedId = multiples.getCheckedRadioButtonId();
                RadioButton selectedRadioButton = (RadioButton) dialog.findViewById(selectedId);
                Toast.makeText(activity,
                        selectedRadioButton.getText(), Toast.LENGTH_SHORT).show();
            }

            indexCurrentQuestion++;
            loadQuestion();
        }
    };

    public void loadQuestion(){
        currentQuestion = questions.get(indexCurrentQuestion);

        question.setText((indexCurrentQuestion+1)+".- "+currentQuestion.getQuestion());
        if (currentQuestion.multiple){
            multiple1.setChecked(false);
            multiple1.setText(currentQuestion.getMultiple1());
            multiple2.setChecked(false);
            multiple2.setText(currentQuestion.getMultiple2());
            multiple3.setChecked(false);
            multiple3.setText(currentQuestion.getMultiple3());
            multiple4.setChecked(false);
            multiple4.setText(currentQuestion.getMultiple4());
            multiple5.setChecked(false);
            multiple5.setText(currentQuestion.getMultiple5());
            multiple.setVisibility(View.VISIBLE);
            open.setVisibility(View.GONE);
        } else {
            answer.setText("");
            multiple.setVisibility(View.GONE);
            open.setVisibility(View.VISIBLE);
        }
    }


    public void show(){
        dialog.show();
        dialog.setOnKeyListener(new Dialog.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface arg0, int keyCode,
                                 KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                }
                return true;
            }
        });
    }
}
