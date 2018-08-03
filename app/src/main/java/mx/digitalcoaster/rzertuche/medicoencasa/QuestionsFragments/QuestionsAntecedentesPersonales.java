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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity;
import mx.digitalcoaster.rzertuche.medicoencasa.R;
import mx.digitalcoaster.rzertuche.medicoencasa.Utils.SharedPreferences;

import static mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity.inicio;
import static mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity.registros;
import static mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity.seguimiento;
import static mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity.sincronizacion;


public class QuestionsAntecedentesPersonales extends Fragment {



    private OnFragmentInteractionListener mListener;

    SharedPreferences sharedPreferences;
    private CheckBox estufa,refrigerador,lavadora,telefono,horno,televisor;
    private ImageButton next,back;

    private LinearLayout personales, antecedentes;
    private TextView questions2;
    private int count = 0;

    private CheckBox enf_cerebro,sobrepeso,tabaquismo,vih,enf_cardiovascular,sedentarismo,tuberculosis,ninguno;

    public static List<String> listPersonales = new ArrayList<String>();
    private TextView category, bodyCheckBox;
    private EditText textArea_information;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.dialog_question_antecedentes_personales, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        sharedPreferences = SharedPreferences.getInstance();



        questions2 = (TextView) getActivity().findViewById(R.id.question2);

        category = (TextView) getActivity().findViewById(R.id.category);
        bodyCheckBox = (TextView) getActivity().findViewById(R.id.titleAntecedentes);

        enf_cerebro = (CheckBox) getActivity().findViewById(R.id.enf_cerebro);
        vih = (CheckBox) getActivity().findViewById(R.id.vih);
        sobrepeso = (CheckBox) getActivity().findViewById(R.id.sobrepeso);
        tabaquismo = (CheckBox) getActivity().findViewById(R.id.tabaquismo);
        sedentarismo = (CheckBox) getActivity().findViewById(R.id.sedentarismo);
        tuberculosis = (CheckBox) getActivity().findViewById(R.id.tuberculosis);
        enf_cardiovascular = (CheckBox) getActivity().findViewById(R.id.enf_cardiovascular);
        ninguno = (CheckBox) getActivity().findViewById(R.id.ninguno);

        personales = (LinearLayout) getActivity().findViewById(R.id.questions);
        antecedentes = (LinearLayout) getActivity().findViewById(R.id.questions2);
        textArea_information = getActivity().findViewById(R.id.textArea_information);

        category.setText(getActivity().getResources().getString(R.string.antecedentes_title));
        bodyCheckBox.setText(getActivity().getResources().getString(R.string.antecedentes_body));


        next = (ImageButton) getActivity().findViewById(R.id.next);
        back = (ImageButton) getActivity().findViewById(R.id.back);



        if(sharedPreferences.getStringData("Heredofamiliares") != null && !sharedPreferences.getStringData("Heredofamiliares").isEmpty() && !sharedPreferences.getBooleanData("BackToInterrogatorio")){

            textArea_information.setText(sharedPreferences.getStringData("Heredofamiliares"));


        }

        if(sharedPreferences.getBooleanData("BackToInterrogatorio")){

            sharedPreferences.setBooleanData("BackToInterrogatorio",false);

            count=4;

            category.setText(getActivity().getResources().getString(R.string.antecedentes_title4));
            bodyCheckBox.setText(getActivity().getResources().getString(R.string.antecedentes_body4));
            textArea_information.setText(sharedPreferences.getStringData("Ginecobstericos") != null && !sharedPreferences.getStringData("Ginecobstericos").isEmpty() ? sharedPreferences.getStringData("Ginecobstericos"): "");


        }


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                count ++;


                if(count == 1){
                    sharedPreferences.setStringData("Heredofamiliares", textArea_information.getText().toString());
                    category.setText(getActivity().getResources().getString(R.string.antecedentes_personales));


                    antecedentes.setVisibility(View.GONE);
                    personales.setVisibility(View.VISIBLE);
                }

                if(count == 2){

                    textArea_information.setText(sharedPreferences.getStringData("NoPatologicos") != null && !sharedPreferences.getStringData("NoPatologicos").isEmpty() ? sharedPreferences.getStringData("NoPatologicos"): "");

                    category.setText(getActivity().getResources().getString(R.string.antecedentes_title2));
                    bodyCheckBox.setText(getActivity().getResources().getString(R.string.antecedentes_body2));


                    antecedentes.setVisibility(View.VISIBLE);
                    personales.setVisibility(View.GONE);
                }

                if(count == 3){
                    sharedPreferences.setStringData("NoPatologicos", textArea_information.getText().toString());

                    textArea_information.setText(sharedPreferences.getStringData("Patologicos") != null && !sharedPreferences.getStringData("Patologicos").isEmpty() ? sharedPreferences.getStringData("Patologicos"): "");

                    category.setText(getActivity().getResources().getString(R.string.antecedentes_title3));
                    bodyCheckBox.setText(getActivity().getResources().getString(R.string.antecedentes_body3));


                }

                if(count == 4){
                    sharedPreferences.setStringData("Patologicos", textArea_information.getText().toString());

                    textArea_information.setText(sharedPreferences.getStringData("Ginecobstericos") != null && !sharedPreferences.getStringData("Ginecobstericos").isEmpty() ? sharedPreferences.getStringData("Ginecobstericos"): "");

                    category.setText(getActivity().getResources().getString(R.string.antecedentes_title4));
                    bodyCheckBox.setText(getActivity().getResources().getString(R.string.antecedentes_body4));


                }

                if(count == 5){
                    sharedPreferences.setStringData("Ginecobstericos", textArea_information.getText().toString());
                    //textArea_information.setText("");
                    //listPersonales = quitarRepedidos(listPersonales);
                    ((MainActivity)getActivity()).questionInterrogatorio();

                }



                if (enf_cerebro.isChecked()) {
                    listPersonales.add("Enfermedad Cerebrovascular");
                }
                if (vih.isChecked()) {
                    listPersonales.add("VIH");
                }
                if (sobrepeso.isChecked()) {
                    listPersonales.add("Sobrepeso");
                }
                if (tabaquismo.isChecked()) {
                    listPersonales.add("Tabaquismo");
                }
                if (sedentarismo.isChecked()) {
                    listPersonales.add("Sedentarismo");
                }
                if (tuberculosis.isChecked()) {
                    listPersonales.add("Tuberculosis");
                }
                if (enf_cardiovascular.isChecked()) {
                    listPersonales.add("Enfermedad Cardiovascular");
                }
                if (ninguno.isChecked()) {
                    listPersonales.clear();
                    listPersonales.add("Ninguno");
                }


            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //count --;


            }
        });


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

    public List<String> quitarRepedidos(List<String> repeditos){
        Set<String> hs = new HashSet<>();
        hs.addAll(repeditos);
        repeditos.clear();
        repeditos.addAll(hs);

        return repeditos;
    }


}
