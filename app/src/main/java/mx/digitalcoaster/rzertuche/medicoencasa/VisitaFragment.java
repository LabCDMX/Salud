package mx.digitalcoaster.rzertuche.medicoencasa;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import mx.digitalcoaster.rzertuche.medicoencasa.models.Contexto;
import mx.digitalcoaster.rzertuche.medicoencasa.models.HistoriaClinica;
import mx.digitalcoaster.rzertuche.medicoencasa.models.Question;
import mx.digitalcoaster.rzertuche.medicoencasa.models.User;
import mx.digitalcoaster.rzertuche.medicoencasa.models.Visita;


public class VisitaFragment extends Fragment {

    Question currentQuestion;

    TextView question;
    TextView title;
    TextView category;
    RadioGroup multiples1;
    RadioGroup multiples2;
    RadioButton multiple1;
    RadioButton multiple2;
    RadioButton multiple3;
    RadioButton multiple4;
    RadioButton multiple5;
    RadioButton multiple6;
    RadioButton multiple7;
    RadioButton multiple8;
    EditText answer;

    LinearLayout multiple;
    LinearLayout open;
    LinearLayout finish;
    LinearLayout review;

    ImageButton next;
    ImageView imageLogo;
    ImageView imageIcon2;



    String checking = "visita";

    public String userID;

    User user;

    Visita visita;


    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }
    ArrayList<Question> questions;

    Activity activity;
    View container;

    int indexCurrentQuestion = 0;



    private OnFragmentInteractionListener mListener;

    public VisitaFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Realm realm = Realm.getDefaultInstance();

        RealmQuery<Question> query = realm.where(Question.class).equalTo("category", "visita");
        RealmResults<Question> questions = query.findAll();

        if(questions.size() == 0){
            Log.d("Realm", "We DO NOT have questions saved");
            ArrayList<String> visitQ = new ArrayList<String>();
            visitQ.add("Peso");
            visitQ.add("Estatura");
            visitQ.add("Tensión arterial");
            visitQ.add("Frecuencia cardiaca");
            visitQ.add("Frecuencia respiratoria");
            visitQ.add("Talla");
            visitQ.add("Pulso");
            visitQ.add("Glucemia");
            visitQ.add("Notas");
            visitQ.add("Tratamineto");

            for(int i = 0; i<visitQ.size(); i++){
                realm.beginTransaction();
                final Question question = realm.createObject(Question.class);
                question.setCategory("visita");
                question.setOrder(i);
                question.setQuestion(visitQ.get(i));
                question.setMultiple(false);
                realm.commitTransaction();
            }

            Log.d("Realm", "SAVED QUESTIONS");


        } else {
            Log.d("Realm", "We have questions saved");
        }


        RealmQuery<Question> query2 = realm.where(Question.class).equalTo("category", "visita");
        RealmResults<Question> visitasQuestion = query2.findAll();

        setQuestions(new ArrayList(visitasQuestion));


        user = realm.where(User.class).equalTo("userUUID", userID).findFirst();

        String random_uuid = UUID.randomUUID().toString();

        visita = new Visita();
        visita.visitUUID = random_uuid;
        visita.setUserUUID(user.getUserUUID());
        visita.fecha = new Date();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.dialog_visita, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        this.activity = getActivity();
        this.container = view;

        open = (LinearLayout) view.findViewById(R.id.open);
        multiple = (LinearLayout) view.findViewById(R.id.multiple);
        finish = (LinearLayout) view.findViewById(R.id.finishLayout);
        review = (LinearLayout) view.findViewById(R.id.ReviewLayout);


        question = (TextView) view.findViewById(R.id.question);
        title = (TextView) view.findViewById(R.id.title);
        answer = (EditText) view.findViewById(R.id.answer);
        category = (TextView) view.findViewById(R.id.category);
        multiple1 = (RadioButton) view.findViewById(R.id.multiple1);
        multiple2 = (RadioButton) view.findViewById(R.id.multiple2);
        multiple3 = (RadioButton) view.findViewById(R.id.multiple3);
        multiple4 = (RadioButton) view.findViewById(R.id.multiple4);
        multiple5 = (RadioButton) view.findViewById(R.id.multiple5);
        multiple6 = (RadioButton) view.findViewById(R.id.multiple6);
        multiple7 = (RadioButton) view.findViewById(R.id.multiple7);
        multiple8 = (RadioButton) view.findViewById(R.id.multiple8);

        multiples1 = (RadioGroup) view.findViewById(R.id.multiples1);
        multiples2 = (RadioGroup) view.findViewById(R.id.multiples2);
        multiples1.clearCheck(); // this is so we can start fresh, with no selection on both RadioGroups
        multiples2.clearCheck();
        multiples1.setOnCheckedChangeListener(listener1);
        multiples2.setOnCheckedChangeListener(listener2);

        loadQuestion();

        next = (ImageButton) view.findViewById(R.id.next);
        next.setOnClickListener(nextOnClickListener);

        imageLogo = (ImageView) view.findViewById(R.id.imageView8);
        imageIcon2 = (ImageView) view.findViewById(R.id.icon2);

        category.setText("Visita Médica");
    }

    private RadioGroup.OnCheckedChangeListener listener1 = new RadioGroup.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId != -1) {
                multiples2.setOnCheckedChangeListener(null); // remove the listener before clearing so we don't throw that stackoverflow exception(like Vladimir Volodin pointed out)
                multiples2.clearCheck(); // clear the second RadioGroup!
                multiples2.setOnCheckedChangeListener(listener2); //reset the listener
            }
        }
    };

    private RadioGroup.OnCheckedChangeListener listener2 = new RadioGroup.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId != -1) {
                multiples1.setOnCheckedChangeListener(null); // remove the listener before clearing so we don't throw that stackoverflow exception(like Vladimir Volodin pointed out)
                multiples1.clearCheck(); // clear the second RadioGroup!
                multiples1.setOnCheckedChangeListener(listener1); //reset the listener
            }
        }
    };

    private View.OnClickListener nextOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if (indexCurrentQuestion==0){visita.setPeso(answer.getText().toString());}
            if (indexCurrentQuestion==1){visita.setEstatura(answer.getText().toString());}
            if (indexCurrentQuestion==2){visita.setTension_arterial(answer.getText().toString());}
            if (indexCurrentQuestion==3){visita.setFrecuencia_cardiaca(answer.getText().toString());}
            if (indexCurrentQuestion==4){visita.setFrecuencia_respiratoria(answer.getText().toString());}
            if (indexCurrentQuestion==5){visita.setTalla(answer.getText().toString());}
            if (indexCurrentQuestion==6){visita.setPulso(answer.getText().toString());}
            if (indexCurrentQuestion==7){
                visita.setGlucemia(answer.getText().toString());
                imageLogo.setImageResource(R.drawable.icon_interrogatorio);
                category.setText("Notas Médicas");
                title.setText(checking.toUpperCase());
                imageIcon2.setImageResource(R.drawable.number_two_pink);
                loadQuestion();
            }
            if (indexCurrentQuestion==8){visita.setNota(answer.getText().toString());}
            if (indexCurrentQuestion==9){visita.setTratamiento(answer.getText().toString());}


            indexCurrentQuestion++;
            if (indexCurrentQuestion < questions.size()){
                loadQuestion();
            } else {

                Realm realm = Realm.getDefaultInstance();

                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        realm.copyToRealmOrUpdate(visita);
                    }
                });


                finish.setVisibility(View.VISIBLE);
            }


        }
    };

    public void loadQuestion(){
        currentQuestion = questions.get(indexCurrentQuestion);

        question.setText((indexCurrentQuestion+1)+".- "+currentQuestion.getQuestion());
        if (currentQuestion.getMultiple()){
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
            multiple6.setChecked(false);
            multiple6.setText(currentQuestion.getMultiple6());
            multiple7.setChecked(false);
            multiple7.setText(currentQuestion.getMultiple7());
            multiple8.setChecked(false);
            multiple8.setText(currentQuestion.getMultiple8());
            if(currentQuestion.getMultiple1() == null){multiple1.setVisibility(View.GONE);}else{multiple1.setVisibility(View.VISIBLE);}
            if(currentQuestion.getMultiple2() == null){multiple2.setVisibility(View.GONE);}else{multiple2.setVisibility(View.VISIBLE);}
            if(currentQuestion.getMultiple3() == null){multiple3.setVisibility(View.GONE);}else{multiple3.setVisibility(View.VISIBLE);}
            if(currentQuestion.getMultiple4() == null){multiple4.setVisibility(View.GONE);}else{multiple4.setVisibility(View.VISIBLE);}
            if(currentQuestion.getMultiple5() == null){multiple5.setVisibility(View.GONE);}else{multiple5.setVisibility(View.VISIBLE);}
            if(currentQuestion.getMultiple6() == null){multiple6.setVisibility(View.GONE);}else{multiple6.setVisibility(View.VISIBLE);}
            if(currentQuestion.getMultiple7() == null){multiple7.setVisibility(View.GONE);}else{multiple7.setVisibility(View.VISIBLE);}
            if(currentQuestion.getMultiple8() == null){multiple8.setVisibility(View.GONE);}else{multiple8.setVisibility(View.VISIBLE);}

            multiple.setVisibility(View.VISIBLE);
            open.setVisibility(View.GONE);
        } else {
            answer.setText("");
            multiple.setVisibility(View.GONE);
            open.setVisibility(View.VISIBLE);
        }
    }

    public void saveGenerales(){

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
