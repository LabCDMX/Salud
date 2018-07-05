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


public class QuestionsAntecedentesPersonales extends Fragment {



    private OnFragmentInteractionListener mListener;

    SharedPreferences sharedPreferences;
    private CheckBox estufa,refrigerador,lavadora,telefono,horno,televisor;
    private ImageButton next;

    private LinearLayout questions,questions3,questions4;
    private TextView questions2;
    private int count = 0;

    private CheckBox enf_cerebro,sobrepeso,tabaquismo,vih,enf_cardiovascular,sedentarismo,tuberculosis;

    public static List<String> listPersonales = new ArrayList<String>();




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



        blockListeners();


        questions2 = (TextView) getActivity().findViewById(R.id.question2);

        enf_cerebro = (CheckBox) getActivity().findViewById(R.id.enf_cerebro);
        vih = (CheckBox) getActivity().findViewById(R.id.vih);
        sobrepeso = (CheckBox) getActivity().findViewById(R.id.sobrepeso);
        tabaquismo = (CheckBox) getActivity().findViewById(R.id.tabaquismo);
        sedentarismo = (CheckBox) getActivity().findViewById(R.id.sedentarismo);
        tuberculosis = (CheckBox) getActivity().findViewById(R.id.tuberculosis);
        enf_cardiovascular = (CheckBox) getActivity().findViewById(R.id.enf_cardiovascular);





        next = (ImageButton) getActivity().findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(enf_cerebro.isChecked() || vih.isChecked() || sobrepeso.isChecked() || tabaquismo.isChecked() || sedentarismo.isChecked()
                        || tuberculosis.isChecked() || enf_cardiovascular.isChecked()) {
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
                }




                ((MainActivity)getActivity()).questionInterrogatorio();
            }
        });





        sharedPreferences = SharedPreferences.getInstance();

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
