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
import static mx.digitalcoaster.rzertuche.medicoencasa.Fragments.VisitasFragment.isSeguimiento;


public class QuestionsAntecedentes extends Fragment {



    private OnFragmentInteractionListener mListener;

    SharedPreferences sharedPreferences;
    private CheckBox estufa,refrigerador,lavadora,telefono,horno,televisor;
    private ImageButton next,back;
    public static List<String> listCardio = new ArrayList<String>();
    public static List<String> listHTA = new ArrayList<String>();
    public static List<String> listDiabetes = new ArrayList<String>();
    public static List<String> listDis = new ArrayList<String>();
    public static List<String> listObe = new ArrayList<String>();
    public static List<String> listEnf = new ArrayList<String>();




    private Boolean isCardio = true;
    private Boolean isHTA = false;
    private Boolean isDiabetes = false;
    private Boolean isDis = false;
    private Boolean isObe = false;
    private Boolean isEnf = false;

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


        sharedPreferences = SharedPreferences.getInstance();
        String name = sharedPreferences.getStringData("nameHistoric");

        TextView title2 = (TextView) getActivity().findViewById(R.id.title2);
        title2.setText(name);

        questions2 = (TextView) getActivity().findViewById(R.id.question2);

        TextView title = (TextView) getActivity().findViewById(R.id.title);


        abuelos = (CheckBox) getActivity().findViewById(R.id.abuelos);
        padres = (CheckBox) getActivity().findViewById(R.id.padres);
        tios = (CheckBox) getActivity().findViewById(R.id.tios);
        hermanos = (CheckBox) getActivity().findViewById(R.id.hermanos);
        ninguno = (CheckBox) getActivity().findViewById(R.id.ninguno);

        if(isSeguimiento){
            title.setText("DATOS DE CONTROL");
        }


        if(sharedPreferences.getBooleanData("BackToAntecedentesPersonales")){
            sharedPreferences.setBooleanData("BackToAntecedentesPersonales",false);

            count=5;
            questions2.setText("f) Enfermedades Cerebrovascular");


        }


        next = (ImageButton) getActivity().findViewById(R.id.next);
        back = (ImageButton) getActivity().findViewById(R.id.back);

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
                }else if((abuelos.isChecked() || padres.isChecked() || tios.isChecked() || hermanos.isChecked() || ninguno.isChecked()) && isDiabetes){
                    if(abuelos.isChecked()){
                        listDiabetes.add("Abuelos");
                    }
                    if(padres.isChecked()){
                        listDiabetes.add("Padres");
                    }
                    if(tios.isChecked()){
                        listDiabetes.add("Tios");
                    }
                    if(hermanos.isChecked()){
                        listDiabetes.add("Hermanos");
                    }
                    if(ninguno.isChecked()){
                        listDiabetes.add("Ninguno");
                    }
                }else if((abuelos.isChecked() || padres.isChecked() || tios.isChecked() || hermanos.isChecked() || ninguno.isChecked()) && isDis){
                    if(abuelos.isChecked()){
                        listDis.add("Abuelos");
                    }
                    if(padres.isChecked()){
                        listDis.add("Padres");
                    }
                    if(tios.isChecked()){
                        listDis.add("Tios");
                    }
                    if(hermanos.isChecked()){
                        listDis.add("Hermanos");
                    }
                    if(ninguno.isChecked()){
                        listDis.add("Ninguno");
                    }
                }else if((abuelos.isChecked() || padres.isChecked() || tios.isChecked() || hermanos.isChecked() || ninguno.isChecked()) && isObe){
                    if(abuelos.isChecked()){
                        listObe.add("Abuelos");
                    }
                    if(padres.isChecked()){
                        listObe.add("Padres");
                    }
                    if(tios.isChecked()){
                        listObe.add("Tios");
                    }
                    if(hermanos.isChecked()){
                        listObe.add("Hermanos");
                    }
                    if(ninguno.isChecked()){
                        listObe.add("Ninguno");
                    }
                }else if((abuelos.isChecked() || padres.isChecked() || tios.isChecked() || hermanos.isChecked() || ninguno.isChecked()) && isEnf){
                    if(abuelos.isChecked()){
                        listEnf.add("Abuelos");
                    }
                    if(padres.isChecked()){
                        listEnf.add("Padres");
                    }
                    if(tios.isChecked()){
                        listEnf.add("Tios");
                    }
                    if(hermanos.isChecked()){
                        listEnf.add("Hermanos");
                    }
                    if(ninguno.isChecked()){
                        listEnf.add("Ninguno");
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
                    isDiabetes = true;
                    clearCheckBox();
                    questions2.setText("c) Diabetes");

                }

                if(count == 3){
                    isDiabetes = false;
                    isDis=true;

                    clearCheckBox();
                    questions2.setText("d) Dislipidemias");

                }

                if(count == 4){
                    isDis=false;
                    isObe = true;
                    clearCheckBox();
                    questions2.setText("e) Obesidad");

                }
                if(count == 5){
                    isObe=false;
                    isEnf = true;
                    clearCheckBox();
                    questions2.setText("f) Enfermedades Cerebrovascular");

                }
                if(count == 6){
                    isEnf = false;
                    count=0;

                    if(isSeguimiento){
                        ((MainActivity)getActivity()).questionInterrogatorio();


                    }else{
                        ((MainActivity)getActivity()).questionsAntPersonales();

                    }


                }

            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count --;

                if(questions2.getText().toString().equals(("a) Enfermedad cardiovascular"))){
                    sharedPreferences.setBooleanData("BackToAntecedentes",true);
                    ((MainActivity)getActivity()).notasEnfermeria();

                }

                if(questions2.getText().toString().equals(("b) HTA"))){

                    isCardio = true;
                    isHTA = false;
                    clearCheckBox();
                    questions2.setText("a) Enfermedad cardiovascular");

                }

                if(questions2.getText().toString().equals(("c) Diabetes"))){

                    isHTA=true;
                    isDiabetes = false;
                    clearCheckBox();
                    questions2.setText("b) HTA");

                }

                if(questions2.getText().toString().equals(("d) Dislipidemias"))){

                    isDiabetes = true;
                    isDis=false;
                    clearCheckBox();

                    questions2.setText("c) Diabetes");

                }

                if(questions2.getText().toString().equals(("e) Obesidad"))){

                    isDis=true;
                    isObe = false;
                    clearCheckBox();

                    questions2.setText("d) Dislipidemias");

                }

                if(questions2.getText().toString().equals(("f) Enfermedades Cerebrovascular"))){

                    isObe=true;
                    isEnf = false;
                    clearCheckBox();
                    questions2.setText("e) Obesidad");

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
