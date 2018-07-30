package mx.digitalcoaster.rzertuche.medicoencasa.QuestionsFragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Range;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.text.DecimalFormat;
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
import static mx.digitalcoaster.rzertuche.medicoencasa.Fragments.PacientesFragment.isSinExp;
import static mx.digitalcoaster.rzertuche.medicoencasa.Fragments.VisitasFragment.isSeguimiento;


public class QuestionsExploracion extends Fragment {


    Double imcAux = 0.0;
    private EditText imc,estatura,peso, talla, pulso;
    private OnFragmentInteractionListener mListener;

    SharedPreferences sharedPreferences;
    private ImageButton next;
    public static List<String> listElectro = new ArrayList<String>();

    private LinearLayout pesoLayout,tipoSangre,tensionLayout, tallaLayout, tensionLayout2, temperaturaLayout;
    private TextView questions2,title;
    private int count = 0;

    private RadioGroup radioSangre, radioTipoSangre,radioDec;
    private EditText tension1,tension2,frecuenciaCard,frecuenciaRes,glucemia,temperatura;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.dialog_question_exploracion, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        blockListeners();

        sharedPreferences = SharedPreferences.getInstance();
        String name = sharedPreferences.getStringData("nameHistoric");

        TextView title2 = (TextView) getActivity().findViewById(R.id.title2);
        title2.setText(name);

        title = (TextView) getActivity().findViewById(R.id.title);




        estatura = (EditText) getActivity().findViewById(R.id.answer2);
        estatura.addTextChangedListener(imcWatcher);

        imc = (EditText) getActivity().findViewById(R.id.answer3);

        peso = (EditText) getActivity().findViewById(R.id.answer);
        peso.addTextChangedListener(imcWatcherPeso);


        talla = (EditText) getActivity().findViewById(R.id.answers7);
        pulso = (EditText) getActivity().findViewById(R.id.answers8);
        tension1 = (EditText) getActivity().findViewById(R.id.answers2);
        tension2 = (EditText) getActivity().findViewById(R.id.answersDiasto);
        frecuenciaCard= (EditText) getActivity().findViewById(R.id.answers3);
        frecuenciaRes= (EditText) getActivity().findViewById(R.id.answers4);
        glucemia= (EditText) getActivity().findViewById(R.id.answers9);
        temperatura= (EditText) getActivity().findViewById(R.id.answers10);


        pesoLayout = (LinearLayout) getActivity().findViewById(R.id.questions);
        tipoSangre = (LinearLayout) getActivity().findViewById(R.id.questionsHemotipo);
        tensionLayout = (LinearLayout) getActivity().findViewById(R.id.questions2);
        tensionLayout2 = (LinearLayout) getActivity().findViewById(R.id.questions5);
        tallaLayout = (LinearLayout) getActivity().findViewById(R.id.questions6);
        temperaturaLayout = (LinearLayout) getActivity().findViewById(R.id.questions12);


        radioSangre = (RadioGroup) getActivity().findViewById(R.id.radioSangre);
        radioTipoSangre = (RadioGroup) getActivity().findViewById(R.id.radioTipoSangre);
        radioDec = (RadioGroup) getActivity().findViewById(R.id.radioDec);

        if(isSeguimiento){

            title.setText("DATOS DE CONTROL");
            tipoSangre.setVisibility(View.GONE);
            pesoLayout.setVisibility(View.VISIBLE);

        }

        next = (ImageButton) getActivity().findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                count++;

                if(count == 1){
                    if(isSeguimiento){
                        sharedPreferences.setStringData("Peso", peso.getText().toString());
                        sharedPreferences.setStringData("Estatura", estatura.getText().toString());


                        pesoLayout.setVisibility(View.GONE);
                        tensionLayout.setVisibility(View.VISIBLE);

                    }else{

                        tipoSangre.setVisibility(View.GONE);
                        pesoLayout.setVisibility(View.VISIBLE);
                    }
                }

                if(count == 2){


                    if(isSeguimiento){

                        sharedPreferences.setStringData("Tension1", tension1.getText().toString());
                        sharedPreferences.setStringData("Tension2", tension2.getText().toString());


                        tensionLayout.setVisibility(View.GONE);
                        tensionLayout2.setVisibility(View.VISIBLE);

                    }else{

                        sharedPreferences.setStringData("Peso", peso.getText().toString());
                        sharedPreferences.setStringData("Estatura", estatura.getText().toString());


                        pesoLayout.setVisibility(View.GONE);
                        tensionLayout.setVisibility(View.VISIBLE);

                    }


                }

                if(count == 3){

                    if(isSeguimiento){

                        sharedPreferences.setStringData("Frecuencia Cardiaca", frecuenciaCard.getText().toString());
                        sharedPreferences.setStringData("Frecuencia Respiratoria", frecuenciaRes.getText().toString());


                        tensionLayout2.setVisibility(View.GONE);
                        tallaLayout.setVisibility(View.VISIBLE);


                    }else{

                        sharedPreferences.setStringData("Tension1", tension1.getText().toString());
                        sharedPreferences.setStringData("Tension2", tension2.getText().toString());


                        tensionLayout.setVisibility(View.GONE);
                        tensionLayout2.setVisibility(View.VISIBLE);


                    }

                }

                if(count == 4){



                    if(isSeguimiento){

                        sharedPreferences.setStringData("Talla", talla.getText().toString());
                        sharedPreferences.setStringData("Pulso", pulso.getText().toString());
                        sharedPreferences.setStringData("Glucemia", glucemia.getText().toString());


                        tallaLayout.setVisibility(View.GONE);
                        temperaturaLayout.setVisibility(View.VISIBLE);



                    }else{

                        sharedPreferences.setStringData("Frecuencia Cardiaca", frecuenciaCard.getText().toString());
                        sharedPreferences.setStringData("Frecuencia Respiratoria", frecuenciaRes.getText().toString());


                        tensionLayout2.setVisibility(View.GONE);
                        tallaLayout.setVisibility(View.VISIBLE);

                    }






                }

                if(count == 5){

                    if(isSeguimiento){

                        sharedPreferences.setStringData("Temperatura", temperatura.getText().toString());

                        ((MainActivity)getActivity()).notasEnfermeria();


                    }else{

                        sharedPreferences.setStringData("Talla", talla.getText().toString());
                        sharedPreferences.setStringData("Pulso", pulso.getText().toString());
                        sharedPreferences.setStringData("Glucemia", glucemia.getText().toString());



                        tallaLayout.setVisibility(View.GONE);
                        temperaturaLayout.setVisibility(View.VISIBLE);



                    }



                }

                if(count == 6){

                    sharedPreferences.setStringData("Temperatura", temperatura.getText().toString());

                    ((MainActivity)getActivity()).notasEnfermeria();





                }

            }
        });



        radioSangre.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                // TODO Auto-generated method stub
                if (checkedId == R.id.tipoA){
                    radioDec.clearCheck();
                    sharedPreferences.setStringData("Hemotipo","Tipo A");

                }else if (checkedId == R.id.tipoB){
                    radioDec.clearCheck();
                    sharedPreferences.setStringData("Hemotipo","Tipo B");

                }else if (checkedId == R.id.tipoAB){
                    radioDec.clearCheck();
                    sharedPreferences.setStringData("Hemotipo","Tipo AB");

                }else if (checkedId == R.id.tipoO){
                    radioDec.clearCheck();
                    sharedPreferences.setStringData("Hemotipo","Tipo O");

                }else if (checkedId == R.id.tipoRHPositivo){
                    radioDec.clearCheck();
                    sharedPreferences.setStringData("Hemotipo","Tipo RH +");

                }else if (checkedId == R.id.tipoRHNegativo){
                    radioDec.clearCheck();
                    sharedPreferences.setStringData("Hemotipo","Tipo RH -");

                }else if (checkedId == R.id.tipoDesconocido){
                    radioDec.clearCheck();
                    sharedPreferences.setStringData("Hemotipo","Desconocido");

                }

            }

        });

        radioTipoSangre.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                // TODO Auto-generated method stub
               if (checkedId == R.id.tipoRHPositivo){
                   radioDec.clearCheck();
                   sharedPreferences.setStringData("Hemotipo","Tipo RH +");

                }else if (checkedId == R.id.tipoRHNegativo){
                   radioDec.clearCheck();
                   sharedPreferences.setStringData("Hemotipo","Tipo RH -");

                }

            }

        });

        radioDec.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                if (checkedId == R.id.tipoDesconocido){
                    sharedPreferences.setStringData("Hemotipo","Tipo RH +");
                    radioSangre.clearCheck();
                    radioTipoSangre.clearCheck();

                }

            }

        });





    }





    private final TextWatcher imcWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {


        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            imc.setVisibility(View.VISIBLE);
            Double pesoAux = 0.0;
            int estaturaAux = 0;


            if(!peso.getText().toString().isEmpty()){
                pesoAux = Double.valueOf(peso.getText().toString());
            }

            if(!estatura.getText().toString().isEmpty()){
                estaturaAux = Integer.valueOf(estatura.getText().toString());
            }



            imcAux = (pesoAux / (estaturaAux * estaturaAux)*10000);




        }

        public void afterTextChanged(Editable s) {
            if (s.length() == 0) {

                imc.setVisibility(View.GONE);

            }else{
                DecimalFormat df = new DecimalFormat("#.00");
                Double imcAuxiliar = Double.valueOf(df.format(imcAux));

                Log.e("prueba de double" , String.valueOf(imcAuxiliar));

                if(imcAuxiliar<18.5){
                    imc.setText(df.format(imcAux) + " Peso Insuficiente");
                }else if(imcAuxiliar >= 18.5 && imcAuxiliar <= 24.9){
                    imc.setText(df.format(imcAux) + " Peso Normal");
                }else if(imcAuxiliar >= 25 && imcAuxiliar <= 26.9){
                    imc.setText(df.format(imcAux) + " Sobrepeso Grado I");
                }else if(imcAuxiliar >= 27 && imcAuxiliar <= 29.9){
                    imc.setText(df.format(imcAux) + " Sobrepeso Grado II");
                }else if(imcAuxiliar >= 30 && imcAuxiliar <= 34.9){
                    imc.setText(df.format(imcAux) + " Obesidad Tipo I");
                }else if(imcAuxiliar >= 35 && imcAuxiliar >= 34.9){
                    imc.setText(df.format(imcAux) + " Obesidad Tipo II");
                }

            }
        }
    };


    private final TextWatcher imcWatcherPeso = new TextWatcher() {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {


        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            imc.setVisibility(View.VISIBLE);
            Double pesoAux = 0.0;
            int estaturaAux = 0;


            if(!estatura.getText().toString().isEmpty()){
                estaturaAux = Integer.valueOf(estatura.getText().toString());
            }

            if(!peso.getText().toString().isEmpty()){
                pesoAux = Double.valueOf(peso.getText().toString());
            }

            imcAux = (pesoAux / (estaturaAux * estaturaAux)*10000);

        }

        public void afterTextChanged(Editable s) {
            if (s.length() == 0) {

                imc.setVisibility(View.GONE);

            }else if (!estatura.getText().toString().isEmpty()){
                DecimalFormat df = new DecimalFormat("#.00");
                Double imcAuxiliar = Double.valueOf(df.format(imcAux));


                if(imcAuxiliar<18.5){
                    imc.setText(df.format(imcAux) + " Peso Insuficiente");
                }else if(imcAuxiliar >= 18.5 && imcAuxiliar <= 24.9){
                    imc.setText(df.format(imcAux) + " Peso Normal");
                }else if(imcAuxiliar >= 25 && imcAuxiliar <= 26.9){
                    imc.setText(df.format(imcAux) + " Sobrepeso Grado I");
                }else if(imcAuxiliar >= 27 && imcAuxiliar <= 29.9){
                    imc.setText(df.format(imcAux) + " Sobrepeso Grado II");
                }else if(imcAuxiliar >= 30 && imcAuxiliar <= 34.9){
                    imc.setText(df.format(imcAux) + " Obesidad Tipo I");
                }else if(imcAuxiliar >= 35 && imcAuxiliar >= 34.9){
                    imc.setText(df.format(imcAux) + " Obesidad Tipo II");
                }

            }
        }
    };



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
