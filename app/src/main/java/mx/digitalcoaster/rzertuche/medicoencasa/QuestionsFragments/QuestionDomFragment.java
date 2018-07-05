package mx.digitalcoaster.rzertuche.medicoencasa.QuestionsFragments;

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
import android.widget.TextView;

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
import static mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity.registros;
import static mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity.seguimiento;
import static mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity.sincronizacion;


public class QuestionDomFragment extends Fragment {


    Question currentQuestion;

    TextView question,question2,question3;
    TextView title;
    TextView category;
    EditText answer,answer2,answer3;

    LinearLayout multiple;
    LinearLayout open;
    LinearLayout finish;
    LinearLayout review;

    ImageButton next;
    ImageView imageLogo;
    ImageView imageIcon2;

    String checking = "personales";

    User user;
    Contexto contexto;
    HistoriaClinica historiaClinica;
    String random_uuid = "";



    Activity activity;
    View container;




    private QuestionsFragment.OnFragmentInteractionListener mListener;
    SharedPreferences sharedPreferences;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.dialog_question_domiciliarios, container, false);
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
        question2 = (TextView) view.findViewById(R.id.question2);
        question3 = (TextView) view.findViewById(R.id.question3);
        title = (TextView) view.findViewById(R.id.title);
        answer = (EditText) view.findViewById(R.id.answer);
        answer2 = (EditText) view.findViewById(R.id.answer2);
        answer3 = (EditText) view.findViewById(R.id.answer3);

        category = (TextView) view.findViewById(R.id.category);




        imageLogo = (ImageView) view.findViewById(R.id.imageView8);
        imageIcon2 = (ImageView) view.findViewById(R.id.icon2);

        category.setText("Personales");

        sharedPreferences = SharedPreferences.getInstance();


        String sharedNombre = sharedPreferences.getStringData("Estado");
        String sharedApellidoP = sharedPreferences.getStringData("Municipio");
        String sharedApellidoM = sharedPreferences.getStringData("Localidad");


        if(!sharedNombre.isEmpty() && !sharedApellidoM.isEmpty() && !sharedApellidoM.isEmpty() ){
            answer.setText(sharedNombre);
            answer2.setText(sharedApellidoP);
            answer3.setText(sharedApellidoM);
        }


        ImageButton next = (ImageButton) view.findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = answer.getText().toString();
                String apeP = answer2.getText().toString();
                String apeM = answer3.getText().toString();


                if(checkName(nombre)){
                    sharedPreferences.setStringData("Estado",nombre);
                    if(checkAP(apeP)){
                        sharedPreferences.setStringData("Municipio",apeP);
                        if(checkAM(apeM)){
                            sharedPreferences.setStringData("Localidad",apeM);
                            sharedPreferences.setStringData("Direccion", nombre + " " +  apeP + " " + apeM);

                            ((MainActivity)getActivity()).questionDomTwo();
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
        });



    }


    public Boolean checkName(String name){
        if(name.isEmpty() || name == null){
            return false;
        }else{
            return true;
        }
    }

    public Boolean checkAP(String name){
        if(name.isEmpty() || name == null){
            return false;
        }else{
            return true;
        }
    }

    public Boolean checkAM(String name){
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
        if (context instanceof QuestionsFragment.OnFragmentInteractionListener) {
            mListener = (QuestionsFragment.OnFragmentInteractionListener) context;
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

    public void blockListeners(){
        inicio.setEnabled(false);
        registros.setEnabled(false);
        seguimiento.setEnabled(false);
        sincronizacion.setEnabled(false);
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
