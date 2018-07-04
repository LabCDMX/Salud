package mx.digitalcoaster.rzertuche.medicoencasa.QuestionsFragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

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
import static mx.digitalcoaster.rzertuche.medicoencasa.QuestionsFragments.QuestionsAntecedentes.listCardio;
import static mx.digitalcoaster.rzertuche.medicoencasa.QuestionsFragments.QuestionsAntecedentes.listHTA;
import static mx.digitalcoaster.rzertuche.medicoencasa.QuestionsFragments.QuestionsAntecedentesPersonales.listPersonales;

import static mx.digitalcoaster.rzertuche.medicoencasa.QuestionsFragments.QuestionsContextElectro.listElectro;


public class HistoriaClinicaFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    SharedPreferences sharedPreferences;

    EditText respiratorio, cardio, peso, estatura, HTA, cardioList, talla, pulso, hemotipo;
    TextView cerebro;
    ImageButton next;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_historia_clinica, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        blockListeners();

        respiratorio = (EditText) getActivity().findViewById(R.id.textViewRespiratorio);
        cardio = (EditText) getActivity().findViewById(R.id.textViewCardio);


        peso = (EditText) getActivity().findViewById(R.id.textViewPeso);
        estatura = (EditText) getActivity().findViewById(R.id.textViewEstatura);

        HTA = (EditText) getActivity().findViewById(R.id.textViewHTA);
        cardioList = (EditText) getActivity().findViewById(R.id.textViewCardioVas);

        cerebro = (TextView) getActivity().findViewById(R.id.tvCerebro);

        hemotipo = (EditText) getActivity().findViewById(R.id.textViewHemotipo);
        talla = (EditText) getActivity().findViewById(R.id.textViewTalla);
        pulso = (EditText) getActivity().findViewById(R.id.textViewPulso);


        next = (ImageButton) getActivity().findViewById(R.id.next);



        //Obtencion de datos del sharedPreferences
        sharedPreferences = SharedPreferences.getInstance();

        respiratorio.setText(sharedPreferences.getStringData("Respiratorio"));
        cardio.setText(sharedPreferences.getStringData("Cardio"));

        peso.setText(sharedPreferences.getStringData("Peso") + "kg");
        estatura.setText(sharedPreferences.getStringData("Estatura") + "mts");

        hemotipo.setText(sharedPreferences.getStringData("Hemotipo"));
        talla.setText(sharedPreferences.getStringData("Talla"));
        pulso.setText(sharedPreferences.getStringData("Pulso"));


        String cadenaCardio = new String();
        String cadenaHTA = new String();

        String cadenaCerebro = new String();



        for (String object: listCardio) {
            cadenaCardio+= "-" + object + "\n";
        }

        for (String object: listHTA) {
            cadenaHTA+= "-" + object + "\n";
        }

        for (String object: listPersonales) {
            cadenaCerebro+= "-" + object + "\n";
        }



        cardioList.setText(cadenaCardio);
        HTA.setText(cadenaHTA);

        cerebro.setText(cadenaCerebro);



        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((MainActivity)getActivity()).fragmentNotasHistoric();


            }
        });


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

    public List<String> borrarRepetidos(List<String> arraycar){
        Set<String> hs = new HashSet<>();
        hs.addAll(arraycar);
        arraycar.clear();
        arraycar.addAll(hs);

        return arraycar;

    }

}
