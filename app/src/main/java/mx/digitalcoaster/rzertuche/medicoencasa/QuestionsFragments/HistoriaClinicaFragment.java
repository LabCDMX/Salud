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
import static mx.digitalcoaster.rzertuche.medicoencasa.Fragments.PacientesFragment.isSinExp;
import static mx.digitalcoaster.rzertuche.medicoencasa.QuestionsFragments.QuestionsAntecedentes.listCardio;
import static mx.digitalcoaster.rzertuche.medicoencasa.QuestionsFragments.QuestionsAntecedentes.listDiabetes;
import static mx.digitalcoaster.rzertuche.medicoencasa.QuestionsFragments.QuestionsAntecedentes.listDis;
import static mx.digitalcoaster.rzertuche.medicoencasa.QuestionsFragments.QuestionsAntecedentes.listEnf;
import static mx.digitalcoaster.rzertuche.medicoencasa.QuestionsFragments.QuestionsAntecedentes.listHTA;
import static mx.digitalcoaster.rzertuche.medicoencasa.QuestionsFragments.QuestionsAntecedentes.listObe;
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
    EditText digestivo,urinario,reproductor,hemo,endocrino,nervioso,piel,habitus,cabeza,cuello;
    EditText diabetes,disli,obesidad,enf_cardio;
    TextView cerebro;
    ImageButton next;

    private String cadenaCardio = new String();
    private String cadenaHTA = new String();
    private String cadenaPersonales = new String();
    private String cadenaDiabetes = new String();
    private String cadenaDis = new String();
    private String cadenaObe = new String();
    private String cadenaEnf = new String();




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



        respiratorio = (EditText) getActivity().findViewById(R.id.textViewRespiratorio);
        cardio = (EditText) getActivity().findViewById(R.id.textViewCardio);
        digestivo = (EditText) getActivity().findViewById(R.id.textViewDigestivo);
        urinario = (EditText) getActivity().findViewById(R.id.textViewUrinario);
        reproductor = (EditText) getActivity().findViewById(R.id.textViewReproductor);
        hemo = (EditText) getActivity().findViewById(R.id.textViewHemolinfatico);
        endocrino = (EditText) getActivity().findViewById(R.id.textViewEndocrino);
        nervioso = (EditText) getActivity().findViewById(R.id.textViewNervioso);
        piel = (EditText) getActivity().findViewById(R.id.textViewPiel);
        habitus = (EditText) getActivity().findViewById(R.id.textViewHabitus);
        cabeza = (EditText) getActivity().findViewById(R.id.textViewCabeza);
        cuello = (EditText) getActivity().findViewById(R.id.textViewCuello);




        peso = (EditText) getActivity().findViewById(R.id.textViewPeso);
        estatura = (EditText) getActivity().findViewById(R.id.textViewEstatura);

        HTA = (EditText) getActivity().findViewById(R.id.textViewHTA);
        cardioList = (EditText) getActivity().findViewById(R.id.textViewCardioVas);

        diabetes = (EditText) getActivity().findViewById(R.id.textViewDiabetes);
        disli = (EditText) getActivity().findViewById(R.id.textViewDisli);
        obesidad = (EditText) getActivity().findViewById(R.id.textViewObesidad);
        enf_cardio = (EditText) getActivity().findViewById(R.id.textViewCerebroVas);


        cerebro = (TextView) getActivity().findViewById(R.id.tvCerebro);

        hemotipo = (EditText) getActivity().findViewById(R.id.textViewHemotipo);
        talla = (EditText) getActivity().findViewById(R.id.textViewTalla);
        pulso = (EditText) getActivity().findViewById(R.id.textViewPulso);


        next = (ImageButton) getActivity().findViewById(R.id.next);



        //Obtencion de datos del sharedPreferences
        sharedPreferences = SharedPreferences.getInstance();

        if(isSinExp){

            String name = sharedPreferences.getStringData("nameItem");
            respiratorio.setText(sharedPreferences.getStringData("Respiratorio"+name));
            cardio.setText(sharedPreferences.getStringData("Cardio"+name));
            digestivo.setText(sharedPreferences.getStringData("Digestivo"+name));
            urinario.setText(sharedPreferences.getStringData("Urinario"+name));
            reproductor.setText(sharedPreferences.getStringData("Reproductor"+name));
            hemo.setText(sharedPreferences.getStringData("Hemo"+name));
            endocrino.setText(sharedPreferences.getStringData("Endocrino"+name));
            nervioso.setText(sharedPreferences.getStringData("Nervioso"+name));
            piel.setText(sharedPreferences.getStringData("Piel"+name));
            habitus.setText(sharedPreferences.getStringData("Habitus"+name));
            cabeza.setText(sharedPreferences.getStringData("Cabeza"+name));
            cuello.setText(sharedPreferences.getStringData("Cuello"+name));
            peso.setText(sharedPreferences.getStringData("Peso"+name));
            estatura.setText(sharedPreferences.getStringData("Estatura"+name));
            hemotipo.setText(sharedPreferences.getStringData("Hemotipo"+name));
            talla.setText(sharedPreferences.getStringData("Talla"+name));
            pulso.setText(sharedPreferences.getStringData("Pulso"+name));
            cardioList.setText(sharedPreferences.getStringData("cadenaCardio"+name));
            HTA.setText(sharedPreferences.getStringData("cadenaHTA"+name));
            cerebro.setText(sharedPreferences.getStringData("cadenaPersonales"+name));
            obesidad.setText(sharedPreferences.getStringData("cadenaObe"+name));
            diabetes.setText(sharedPreferences.getStringData("cadenaDiabetes"+name));
            disli.setText(sharedPreferences.getStringData("cadenaDis"+name));
            enf_cardio.setText(sharedPreferences.getStringData("cadenaEnf"+name));



        }else{

            respiratorio.setText(sharedPreferences.getStringData("Respiratorio"));
            cardio.setText(sharedPreferences.getStringData("Cardio"));
            digestivo.setText(sharedPreferences.getStringData("Digestivo"));
            urinario.setText(sharedPreferences.getStringData("Urinario"));
            reproductor.setText(sharedPreferences.getStringData("Reproductor"));
            hemo.setText(sharedPreferences.getStringData("Hemo"));
            endocrino.setText(sharedPreferences.getStringData("Endocrino"));
            nervioso.setText(sharedPreferences.getStringData("Nervioso"));
            piel.setText(sharedPreferences.getStringData("Piel"));
            habitus.setText(sharedPreferences.getStringData("Habitus"));
            cabeza.setText(sharedPreferences.getStringData("Cabeza"));
            cuello.setText(sharedPreferences.getStringData("Cuello"));
            peso.setText(sharedPreferences.getStringData("Peso") + "kg");
            estatura.setText(sharedPreferences.getStringData("Estatura") + "mts");
            hemotipo.setText(sharedPreferences.getStringData("Hemotipo"));
            talla.setText(sharedPreferences.getStringData("Talla"));
            pulso.setText(sharedPreferences.getStringData("Pulso"));



            for (String object: listCardio) {
                cadenaCardio+= "-" + object + "\n";
            }

            for (String object: listHTA) {
                cadenaHTA+= "-" + object + "\n";
            }

            for (String object: listPersonales) {
                cadenaPersonales+= "-" + object + "\n";
            }

            for (String object: listDiabetes) {
                cadenaDiabetes+= "-" + object + "\n";
            }

            for (String object: listDis) {
                cadenaDis+= "-" + object + "\n";
            }

            for (String object: listObe) {
                cadenaObe+= "-" + object + "\n";
            }

            for (String object: listEnf) {
                cadenaEnf+= "-" + object + "\n";
            }



            cardioList.setText(cadenaCardio);
            HTA.setText(cadenaHTA);
            cerebro.setText(cadenaPersonales);
            obesidad.setText(cadenaObe);
            diabetes.setText(cadenaDiabetes);
            disli.setText(cadenaDis);
            enf_cardio.setText(cadenaEnf);


        }





        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    saveAllDataPreferences();
                    ((MainActivity)getActivity()).fragmentNotasHistoric();

            }
        });


    }

    public void saveAllDataPreferences(){

        String name = sharedPreferences.getStringData("nameHistoric");
        sharedPreferences.setStringData("Respiratorio"+name, sharedPreferences.getStringData("Respiratorio"));
        sharedPreferences.setStringData("Cardio"+name, sharedPreferences.getStringData("Cardio"));
        sharedPreferences.setStringData("Digestivo"+name, sharedPreferences.getStringData("Digestivo"));
        sharedPreferences.setStringData("Urinario"+name, sharedPreferences.getStringData("Urinario"));
        sharedPreferences.setStringData("Reproductor"+name, sharedPreferences.getStringData("Reproductor"));
        sharedPreferences.setStringData("Hemo"+name, sharedPreferences.getStringData("Hemo"));
        sharedPreferences.setStringData("Endocrino"+name, sharedPreferences.getStringData("Endocrino"));
        sharedPreferences.setStringData("Nervioso"+name, sharedPreferences.getStringData("Nervioso"));
        sharedPreferences.setStringData("Piel"+name, sharedPreferences.getStringData("Piel"));
        sharedPreferences.setStringData("Habitus"+name, sharedPreferences.getStringData("Habitus"));
        sharedPreferences.setStringData("Cabeza"+name, sharedPreferences.getStringData("Cabeza"));
        sharedPreferences.setStringData("Cuello"+name, sharedPreferences.getStringData("Cuello"));
        sharedPreferences.setStringData("Peso"+name, sharedPreferences.getStringData("Peso" + "kg"));
        sharedPreferences.setStringData("Estatura"+name, sharedPreferences.getStringData("Estatura" + "mts"));
        sharedPreferences.setStringData("Hemotipo"+name, sharedPreferences.getStringData("Hemotipo"));
        sharedPreferences.setStringData("Talla"+name, sharedPreferences.getStringData("Talla"));
        sharedPreferences.setStringData("Pulso"+name, sharedPreferences.getStringData("Pulso"));
        sharedPreferences.setStringData("cadenaCardio"+name, cadenaCardio);
        sharedPreferences.setStringData("cadenaHTA"+name, cadenaHTA);
        sharedPreferences.setStringData("cadenaPersonales"+name, cadenaPersonales);
        sharedPreferences.setStringData("cadenaObe"+name, cadenaObe);
        sharedPreferences.setStringData("cadenaDiabetes"+name, cadenaDiabetes);
        sharedPreferences.setStringData("cadenaDis"+name, cadenaDis);
        sharedPreferences.setStringData("cadenaEnf"+name, cadenaEnf);

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
