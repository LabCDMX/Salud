package mx.digitalcoaster.rzertuche.medicoencasa.QuestionsFragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity;
import mx.digitalcoaster.rzertuche.medicoencasa.R;
import mx.digitalcoaster.rzertuche.medicoencasa.Utils.SharedPreferences;

import static mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity.inicio;
import static mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity.notListerners;
import static mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity.registros;
import static mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity.seguimiento;
import static mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity.sincronizacion;


public class QuestionsHistoriaClinica extends Fragment {



    private OnFragmentInteractionListener mListener;

    SharedPreferences sharedPreferences;
    private CheckBox estufa,refrigerador,lavadora,telefono,horno,televisor;
    private ImageButton next;
    public static List<String> listElectro = new ArrayList<String>();

    private TextView question2,question;
    private EditText textArea_information;
    private int count = 0;





    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.dialog_question_historia_clinica, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        blockListeners();

        question2 = getActivity().findViewById(R.id.question2);
        question = getActivity().findViewById(R.id.question);


        textArea_information = (EditText) getActivity().findViewById(R.id.textArea_information);

        next = (ImageButton) getActivity().findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;



                if(count == 1){
                    sharedPreferences.setStringData("Respiratorio",textArea_information.getText().toString());
                    textArea_information.setText("");
                    question.setText(getActivity().getResources().getString(R.string.interrogatorio_title1));
                    question2.setText(getActivity().getResources().getString(R.string.interrogatorio1));
                }

                if(count == 2){
                    sharedPreferences.setStringData("Cardio",textArea_information.getText().toString());
                    textArea_information.setText("");
                    question.setText(getActivity().getResources().getString(R.string.interrogatorio_title2));
                    question2.setText(getActivity().getResources().getString(R.string.interrogatorio2));
                }

                if(count == 3){
                    sharedPreferences.setStringData("Urinario",textArea_information.getText().toString());
                    textArea_information.setText("");
                    question.setText(getActivity().getResources().getString(R.string.interrogatorio_title3));
                    question2.setText(getActivity().getResources().getString(R.string.interrogatorio3));
                }

                if(count == 4){
                    sharedPreferences.setStringData("Reproductor",textArea_information.getText().toString());
                    textArea_information.setText("");
                    question.setText(getActivity().getResources().getString(R.string.interrogatorio_title4));
                    question2.setText(getActivity().getResources().getString(R.string.interrogatorio4));
                }
                if(count == 5){
                    sharedPreferences.setStringData("Hemo",textArea_information.getText().toString());
                    textArea_information.setText("");
                    question.setText(getActivity().getResources().getString(R.string.interrogatorio_title5));
                    question2.setText(getActivity().getResources().getString(R.string.interrogatorio5));
                }
                if(count == 6){
                    sharedPreferences.setStringData("Endocrino",textArea_information.getText().toString());
                    textArea_information.setText("");
                    question.setText(getActivity().getResources().getString(R.string.interrogatorio_title6));
                    question2.setText(getActivity().getResources().getString(R.string.interrogatorio6));
                }
                if(count == 7){
                    sharedPreferences.setStringData("Nervioso",textArea_information.getText().toString());
                    textArea_information.setText("");
                    question.setText(getActivity().getResources().getString(R.string.interrogatorio_title7));
                    question2.setText(getActivity().getResources().getString(R.string.interrogatorio7));
                }
                if(count == 8){
                    sharedPreferences.setStringData("Esqueletico",textArea_information.getText().toString());
                    textArea_information.setText("");
                    question.setText(getActivity().getResources().getString(R.string.interrogatorio_title8));
                    question2.setText(getActivity().getResources().getString(R.string.interrogatorio8));
                }
                if(count == 9){
                    sharedPreferences.setStringData("Piel",textArea_information.getText().toString());
                    textArea_information.setText("");
                    question.setText(getActivity().getResources().getString(R.string.interrogatorio_title9));
                    question2.setText(getActivity().getResources().getString(R.string.interrogatorio9));
                }
                if(count == 10){
                    sharedPreferences.setStringData("Habitus",textArea_information.getText().toString());
                    textArea_information.setText("");
                    question.setText(getActivity().getResources().getString(R.string.interrogatorio_title10));
                    question2.setText(getActivity().getResources().getString(R.string.interrogatorio10));
                }
                if(count == 11){
                    sharedPreferences.setStringData("Cabeza",textArea_information.getText().toString());
                    textArea_information.setText("");
                    question.setText(getActivity().getResources().getString(R.string.interrogatorio_title11));
                    question2.setText(getActivity().getResources().getString(R.string.interrogatorio11));
                }
                if(count == 12){
                    sharedPreferences.setStringData("Cuello",textArea_information.getText().toString());
                    textArea_information.setText("");
                    question.setText(getActivity().getResources().getString(R.string.interrogatorio_title12));
                    question2.setText(getActivity().getResources().getString(R.string.interrogatorio12));
                }
                if(count == 13){
                    textArea_information.setText("");
                    question.setText(getActivity().getResources().getString(R.string.interrogatorio_title20));
                    question2.setText(getActivity().getResources().getString(R.string.interrogatorio13));
                }
                if(count == 14){
                    textArea_information.setText("");
                    question.setText(getActivity().getResources().getString(R.string.interrogatorio_title14));
                    question2.setText(getActivity().getResources().getString(R.string.interrogatorio14));
                }
                if(count == 15){
                    textArea_information.setText("");
                    question.setText(getActivity().getResources().getString(R.string.interrogatorio_title15));
                    question2.setText(getActivity().getResources().getString(R.string.interrogatorio15));
                }
                if(count == 16){
                    textArea_information.setText("");
                    question.setText(getActivity().getResources().getString(R.string.interrogatorio_title16));
                    question2.setText(getActivity().getResources().getString(R.string.interrogatorio16));
                }
                if(count == 17){
                    textArea_information.setText("");
                    question.setText(getActivity().getResources().getString(R.string.interrogatorio_title17));
                    question2.setText(getActivity().getResources().getString(R.string.interrogatorio17));
                }
                if(count == 18){
                    textArea_information.setText("");
                    question.setText(getActivity().getResources().getString(R.string.interrogatorio_title18));
                    question2.setText(getActivity().getResources().getString(R.string.interrogatorio18));
                }
                if(count == 19){
                    textArea_information.setText("");
                    question.setText(getActivity().getResources().getString(R.string.interrogatorio_title19));
                    question2.setText(getActivity().getResources().getString(R.string.interrogatorio19));

                }
                if(count == 20){
                    textArea_information.setText("");
                    count=0;
                    ((MainActivity)getActivity()).historiaClinicaFragment();

                }


            }
        });





        sharedPreferences = SharedPreferences.getInstance();

    }


    public void blockListeners(){
        notListerners = true;
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
