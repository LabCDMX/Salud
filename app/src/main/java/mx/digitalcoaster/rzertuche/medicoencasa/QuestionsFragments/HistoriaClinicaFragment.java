package mx.digitalcoaster.rzertuche.medicoencasa.QuestionsFragments;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.CurrencyPluralInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;;
import android.util.Log;
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
import mx.digitalcoaster.rzertuche.medicoencasa.DataBase.DataBaseDB;
import mx.digitalcoaster.rzertuche.medicoencasa.R;
import mx.digitalcoaster.rzertuche.medicoencasa.Utils.SharedPreferences;
import mx.digitalcoaster.rzertuche.medicoencasa.models.Item;

import static mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity.inicio;
import static mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity.registros;
import static mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity.seguimiento;
import static mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity.sincronizacion;
import static mx.digitalcoaster.rzertuche.medicoencasa.Fragments.PacientesFragment.isSinExp;
import static mx.digitalcoaster.rzertuche.medicoencasa.Fragments.VisitasFragment.isSeguimiento;
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
    TextView personales;
    ImageButton next;

    public static String cadenaCardio = new String();
    public static String cadenaHTA = new String();
    public static String cadenaPersonales = new String();
    public static String cadenaDiabetes = new String();
    public static String cadenaDis = new String();
    public static String cadenaObe = new String();
    public static String cadenaEnf = new String();

    private SQLiteDatabase db = null;   // Objeto para usar la base de datos local
    private Cursor c = null;            // Objeto para hacer consultas a la base de datos





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


        personales = (TextView) getActivity().findViewById(R.id.tvCerebro);

        hemotipo = (EditText) getActivity().findViewById(R.id.textViewHemotipo);
        talla = (EditText) getActivity().findViewById(R.id.textViewTalla);
        pulso = (EditText) getActivity().findViewById(R.id.textViewPulso);


        next = (ImageButton) getActivity().findViewById(R.id.next);



        //Obtencion de datos del sharedPreferences
        sharedPreferences = SharedPreferences.getInstance();

        if(isSinExp){

            getDatosExp();
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
            personales.setText(sharedPreferences.getStringData("cadenaPersonales"+name));
            obesidad.setText(sharedPreferences.getStringData("cadenaObe"+name));
            diabetes.setText(sharedPreferences.getStringData("cadenaDiabetes"+name));
            disli.setText(sharedPreferences.getStringData("cadenaDis"+name));
            enf_cardio.setText(sharedPreferences.getStringData("cadenaEnf"+name));



        }

        else{

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
            personales.setText(cadenaPersonales);
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

    public void getDatosExp(){

        db = getActivity().openOrCreateDatabase(DataBaseDB.DB_NAME, Context.MODE_PRIVATE, null);
        try {
            c = db.rawQuery("SELECT * FROM " + DataBaseDB.TABLE_NAME_PACIENTES_SIN_EXPEDIENTE + " WHERE "
                    + DataBaseDB.PACIENTES_EXPEDIENTE_NOMBRE + " = '" + sharedPreferences.getStringData("nameItem") + "' AND "
                    + DataBaseDB.PACIENTES_EXPEDIENTE_CURP + " = '" + sharedPreferences.getStringData("curpItem")+"'", null);
            if (c.moveToFirst()) {
                do {
                    String name = sharedPreferences.getStringData("nameItem");

                    sharedPreferences.setStringData("Respiratorio"+name,c.getString(30));
                    sharedPreferences.setStringData("Cardio"+name,c.getString(31));
                    sharedPreferences.setStringData("Digestivo"+name,c.getString(32));
                    sharedPreferences.setStringData("Urinario"+name,c.getString(33));
                    sharedPreferences.setStringData("Reproductor"+name,c.getString(34));
                    sharedPreferences.setStringData("Hemo"+name,c.getString(35));
                    sharedPreferences.setStringData("Endocrino"+name,c.getString(36));
                    sharedPreferences.setStringData("Nervioso"+name,c.getString(37));
                    sharedPreferences.setStringData("Piel"+name,c.getString(39));
                    sharedPreferences.setStringData("Habitus"+name,c.getString(40));
                    sharedPreferences.setStringData("Cabeza"+name,c.getString(41));
                    sharedPreferences.setStringData("Cuello"+name,c.getString(42));
                    sharedPreferences.setStringData("Peso"+name,c.getString(8));
                    sharedPreferences.setStringData("Estatura"+name,c.getString(9));
                    sharedPreferences.setStringData("Hemotipo"+name,c.getString(7));
                    sharedPreferences.setStringData("Talla"+name,c.getString(13));
                    sharedPreferences.setStringData("Pulso"+name,c.getString(14));
                    sharedPreferences.setStringData("cadenaCardio"+name,c.getString(19));
                    sharedPreferences.setStringData("cadenaHTA"+name,c.getString(18));
                    sharedPreferences.setStringData("cadenaPersonales"+name,c.getString(24));
                    sharedPreferences.setStringData("cadenaObe"+name,c.getString(20));
                    sharedPreferences.setStringData("cadenaDiabetes"+name,c.getString(21));
                    sharedPreferences.setStringData("cadenaDis"+name,c.getString(22));
                    sharedPreferences.setStringData("cadenaEnf"+name,c.getString(23));


                }while (c.moveToNext());
            } else {
                System.out.println("No existen PACIENTES");
            }
            c.close();
        } catch (Exception ex) {
            Log.e("Error", ex.toString());
        } finally {
            db.close();
        }

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
        sharedPreferences.setStringData("Peso"+name, sharedPreferences.getStringData("Peso"));
        sharedPreferences.setStringData("Estatura"+name, sharedPreferences.getStringData("Estatura"));
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
