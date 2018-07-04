package mx.digitalcoaster.rzertuche.medicoencasa.QuestionsFragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.BoringLayout;
import android.util.Log;
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
import static mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity.registros;
import static mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity.seguimiento;
import static mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity.sincronizacion;


public class QuestionsAntecedentes extends Fragment {



    private OnFragmentInteractionListener mListener;

    SharedPreferences sharedPreferences;
    private CheckBox estufa,refrigerador,lavadora,telefono,horno,televisor;
    private ImageButton next;
    public static List<String> listCardio = new ArrayList<String>();
    public static List<String> listHTA = new ArrayList<String>();


    private Boolean isCardio = true;
    private Boolean isHTA = false;

    private LinearLayout questions,questions3,questions4;
    private TextView questions2;
    private int count = 0;


    private CheckBox abuelos, padres, tios, hermanos, ninguno;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.dialog_question_antecedentes, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        blockListeners();

        sharedPreferences = SharedPreferences.getInstance();
        String name = sharedPreferences.getStringData("nameHistoric");

        TextView title2 = (TextView) getActivity().findViewById(R.id.title2);
        title2.setText(name);

        questions2 = (TextView) getActivity().findViewById(R.id.question2);


        abuelos = (CheckBox) getActivity().findViewById(R.id.abuelos);
        padres = (CheckBox) getActivity().findViewById(R.id.padres);
        tios = (CheckBox) getActivity().findViewById(R.id.tios);
        hermanos = (CheckBox) getActivity().findViewById(R.id.hermanos);
        ninguno = (CheckBox) getActivity().findViewById(R.id.ninguno);



        next = (ImageButton) getActivity().findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                count++;


                if((abuelos.isChecked() || padres.isChecked() || tios.isChecked() || hermanos.isChecked() || ninguno.isChecked()) && isCardio){
                    if(abuelos.isChecked()){
                        listCardio.add("Abuelos");
                    }
                    if(padres.isChecked()){
                        listCardio.add("Padres");
                    }
                    if(tios.isChecked()){
                        listCardio.add("Tios");
                    }
                    if(hermanos.isChecked()){
                        listCardio.add("Hermanos");
                    }
                    if(ninguno.isChecked()){
                        listCardio.add("Ninguno");
                    }

                }else if((abuelos.isChecked() || padres.isChecked() || tios.isChecked() || hermanos.isChecked() || ninguno.isChecked()) && isHTA){
                    if(abuelos.isChecked()){
                        listHTA.add("Abuelos");
                    }
                    if(padres.isChecked()){
                        listHTA.add("Padres");
                    }
                    if(tios.isChecked()){
                        listHTA.add("Tios");
                    }
                    if(hermanos.isChecked()){
                        listHTA.add("Hermanos");
                    }
                    if(ninguno.isChecked()){
                        listHTA.add("Ninguno");
                    }
                }



                if(count == 1){
                    isCardio = false;
                    isHTA = true;
                    clearCheckBox();
                    questions2.setText("b) HTA");
                }

                if(count == 2){
                    isHTA=false;
                    clearCheckBox();
                    questions2.setText("c) Diabetes");

                }

                if(count == 3){
                    clearCheckBox();
                    questions2.setText("d) Dislipidemias");

                }

                if(count == 4){
                    clearCheckBox();
                    questions2.setText("e) Obesidad");

                }
                if(count == 5){
                    clearCheckBox();
                    questions2.setText("f) Enfermedades Cerebrovascular");

                }
                if(count == 6){
                    count=0;
                    ((MainActivity)getActivity()).questionsAntPersonales();

                }

            }
        });

    }


    private void clearCheckBox(){
        abuelos.setChecked(false);
        padres.setChecked(false);
        tios.setChecked(false);
        hermanos.setChecked(false);
        abuelos.setChecked(false);
        ninguno.setChecked(false);
    }

    public void blockListeners(){
        inicio.setEnabled(false);
        registros.setEnabled(false);
        seguimiento.setEnabled(false);
        sincronizacion.setEnabled(false);
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

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
