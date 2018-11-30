package mx.digitalcoaster.rzertuche.medicoencasa.Fragments.QuestionsFragments;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.util.UUID;

import mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity;
import mx.digitalcoaster.rzertuche.medicoencasa.Utils.SharedPreferences;
import mx.digitalcoaster.rzertuche.medicoencasa.R;
import mx.digitalcoaster.rzertuche.medicoencasa.models.Contexto;
import mx.digitalcoaster.rzertuche.medicoencasa.models.HistoriaClinica;
import mx.digitalcoaster.rzertuche.medicoencasa.models.Question;
import mx.digitalcoaster.rzertuche.medicoencasa.models.User;

import static mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity.inicio;
import static mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity.notListerners;
import static mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity.registros;
import static mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity.seguimiento;
import static mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity.sincronizacion;


public class QuestionsFragment extends Fragment {

    Question currentQuestion;

    TextView question,question2,question3;
    TextView title;
    TextView category;
    EditText answer,answer2,answer3,answer4;

    LinearLayout multiple;
    LinearLayout open;
    LinearLayout finish;
    LinearLayout review;
    LinearLayout checkCurp;

    ImageButton next,back;
    ImageView imageLogo;
    ImageView imageIcon2;

    String checking = "personales";

    User user;
    Contexto contexto;
    HistoriaClinica historiaClinica;
    String random_uuid = "";



    Activity activity;
    View container;
    private int count=1;
    private RadioGroup radioCurp;
    private RadioButton conoce, desconoce, noesp;




    private OnFragmentInteractionListener mListener;
    SharedPreferences sharedPreferences;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        random_uuid = UUID.randomUUID().toString();

        user = new User();
        user.setUserUUID(random_uuid);
        user.fecha = new Date();
        contexto = new Contexto();
        contexto.setUserUUID(random_uuid);
        historiaClinica = new HistoriaClinica();
        historiaClinica.setUserUUID(random_uuid);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.dialog_question, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        this.activity = getActivity();
        this.container = view;

        blockListeners();

        open = (LinearLayout) view.findViewById(R.id.open);
        checkCurp = (LinearLayout) view.findViewById(R.id.linear_curp);


        multiple = (LinearLayout) view.findViewById(R.id.multiple);
        finish = (LinearLayout) view.findViewById(R.id.finishLayout);
        review = (LinearLayout) view.findViewById(R.id.ReviewLayout);


        question = (TextView) view.findViewById(R.id.question);
        question2 = (TextView) view.findViewById(R.id.question2);
        question3 = (TextView) view.findViewById(R.id.question3);
        title = (TextView) view.findViewById(R.id.title);
        answer = (EditText) view.findViewById(R.id.answer);
        answer2 = (EditText) view.findViewById(R.id.answer2);
        answer3 = (EditText) view.findViewById(R.id.answer3);
        answer4 = (EditText) view.findViewById(R.id.answer4);


        category = (TextView) view.findViewById(R.id.category);

        radioCurp = (RadioGroup) view.findViewById(R.id.radioCurp);

        conoce = (RadioButton) view.findViewById(R.id.conoce_curp);
        desconoce = (RadioButton) view.findViewById(R.id.desconoce_curp);
        noesp = (RadioButton) view.findViewById(R.id.no_especifica_curp);

        next = (ImageButton) view.findViewById(R.id.next);
        back = (ImageButton) view.findViewById(R.id.back);

        imageLogo = (ImageView) view.findViewById(R.id.imageView8);
        imageIcon2 = (ImageView) view.findViewById(R.id.icon2);

        category.setText("Personales");

        sharedPreferences = SharedPreferences.getInstance();

        if(sharedPreferences.getBooleanData("BackToQuestionsTwo")){
            count=2;
            back.setVisibility(View.VISIBLE);
            checkCurp.setVisibility(View.GONE);
            open.setVisibility(View.VISIBLE);
        }


        if(!sharedPreferences.getStringData("NombrePatient").isEmpty() && !sharedPreferences.getStringData("ApellidoP").isEmpty() && !sharedPreferences.getStringData("ApellidoM").isEmpty() ){
            answer.setText(sharedPreferences.getStringData("NombrePatient"));
            answer2.setText(sharedPreferences.getStringData("ApellidoP"));
            answer3.setText(sharedPreferences.getStringData("ApellidoM"));
        }







        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count == 1){
                    if(conoce.isChecked()){
                        checkCURP();

                    }else if(desconoce.isChecked() || noesp.isChecked()){
                        count++;
                        back.setVisibility(View.VISIBLE);
                        checkCurp.setVisibility(View.GONE);
                        open.setVisibility(View.VISIBLE);
                    }else{
                        Toast.makeText(getContext(),"Selecciona una opcion antes de continuar", Toast.LENGTH_SHORT).show();

                    }


                }else if(count == 2){

                    String nombre = answer.getText().toString();
                    String apeP = answer2.getText().toString();
                    String apeM = answer3.getText().toString();


                    if(checkNull(nombre)){
                        sharedPreferences.setStringData("NombrePatient",nombre);
                        if(checkNull(apeP)){
                            sharedPreferences.setStringData("ApellidoP",apeP);
                            if(checkNull(apeM)){
                                sharedPreferences.setStringData("ApellidoM",apeM);
                                sharedPreferences.setBooleanData("BackToQuestionsTwo",false);
                                ((MainActivity)getActivity()).questionFragmentTwo();
                            }else{
                                answer3.setError("Campo faltante");
                                answer3.requestFocus();
                                InputMethodManager imm = (InputMethodManager) // con esto abres el teclado despues de ubicar el foco en tu editText
                                        getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                                imm.showSoftInput(answer3, InputMethodManager.SHOW_IMPLICIT);
                            }
                        }else{
                            answer2.setError("Campo faltante");
                            answer2.requestFocus();
                            InputMethodManager imm = (InputMethodManager) // con esto abres el teclado despues de ubicar el foco en tu editText
                                    getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.showSoftInput(answer2, InputMethodManager.SHOW_IMPLICIT);

                        }
                    }else{

                        answer.setError("Campo faltante");
                        answer.requestFocus();
                        InputMethodManager imm = (InputMethodManager) // con esto abres el teclado despues de ubicar el foco en tu editText
                                getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.showSoftInput(answer, InputMethodManager.SHOW_IMPLICIT);

                    }


                }

            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count --;

                if(open.getVisibility() == View.VISIBLE){
                    back.setVisibility(View.GONE);
                    checkCurp.setVisibility(View.VISIBLE);
                    open.setVisibility(View.GONE);

                }

            }
        });


        radioCurp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                if (checkedId == R.id.conoce_curp){
                    answer4.setVisibility(View.VISIBLE);

                }else if (checkedId == R.id.no_especifica_curp){

                    answer4.setVisibility(View.GONE);
                    sharedPreferences.setStringData("CURP","XXXX888888XXXXXX88");

                }else if (checkedId == R.id.desconoce_curp){

                    answer4.setVisibility(View.GONE);
                    sharedPreferences.setStringData("CURP","XXXX999999XXXXXX99");

                }

            }

        });



    }

    public void checkCURP(){

        if(answer4.getVisibility() == View.VISIBLE){

            if(checkNull(answer4.getText().toString())){

                sharedPreferences.setStringData("CURP",answer4.getText().toString());
                count++;
                checkCurp.setVisibility(View.GONE);
                open.setVisibility(View.VISIBLE);
            }else{
                answer4.setError("Campo faltante");
                answer4.requestFocus();
                InputMethodManager imm = (InputMethodManager) // con esto abres el teclado despues de ubicar el foco en tu editText
                        getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(answer4, InputMethodManager.SHOW_IMPLICIT);
            }
        }

    }

    public void blockListeners(){
        notListerners = true;
    }


    public Boolean checkNull(String name){
        if(name.isEmpty() || name == null){
            return false;
        }else{
            return true;
        }
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
