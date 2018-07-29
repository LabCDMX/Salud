package mx.digitalcoaster.rzertuche.medicoencasa.QuestionsFragments;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity;
import mx.digitalcoaster.rzertuche.medicoencasa.DataBase.DataBaseDB;
import mx.digitalcoaster.rzertuche.medicoencasa.R;
import mx.digitalcoaster.rzertuche.medicoencasa.Utils.SharedPreferences;

import static mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity.inicio;
import static mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity.registros;
import static mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity.seguimiento;
import static mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity.sincronizacion;
import static mx.digitalcoaster.rzertuche.medicoencasa.Fragments.PacientesFragment.isSinExp;
import static mx.digitalcoaster.rzertuche.medicoencasa.Fragments.VisitasFragment.isSeguimiento;

;


public class NotasHistoric extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    SharedPreferences sharedPreferences;

    EditText textArea_information;
    ImageButton next;

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
        return inflater.inflate(R.layout.dialog_question_notas, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        //Obtencion de datos del sharedPreferences
        sharedPreferences = SharedPreferences.getInstance();

        ImageButton next = (ImageButton) getActivity().findViewById(R.id.next);
        textArea_information = getActivity().findViewById(R.id.textArea_information);

        if(isSinExp){
            String name = sharedPreferences.getStringData("nameItem");
            textArea_information.setText(sharedPreferences.getStringData("NotasMedicas"+name));

        }

        if(isSeguimiento){
            String name = sharedPreferences.getStringData("nameSeguimiento");
            String curpSeguimiento = sharedPreferences.getStringData("curpSeguimiento");
            String numeroVisita = sharedPreferences.getStringData("numero_visita");

            getNotas(name, curpSeguimiento,numeroVisita);

            textArea_information.setText(sharedPreferences.getStringData("NotasMedicasSegumiento"));


        }



        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isSeguimiento){
                    saveAllData();
                    ((MainActivity)getActivity()).notasHojaDiaria();


                }else{


                    saveAllData();
                    ((MainActivity)getActivity()).fragmentDiagnostico();


                }
            }
        });


    }


    public void saveAllData(){

        sharedPreferences.setStringData("SubjetivoNotas",textArea_information.getText().toString());

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

    public void getNotas(String nombrePatient, String curpPatient, String numeroVisita){

        db = getActivity().openOrCreateDatabase(DataBaseDB.DB_NAME, Context.MODE_PRIVATE, null);
        try {

            c = db.rawQuery("SELECT * FROM " + DataBaseDB.TABLE_NAME_PACIENTES_SEGUIMIENTO + " WHERE " +
                    DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_NOMBRE + " ='"+nombrePatient+"' AND "+
                    DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_CURP + " ='"+curpPatient+"' AND "+
                    DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_NUMERO+ " = '"+numeroVisita+"'", null);


            if (c.moveToFirst()) {

                sharedPreferences.setStringData("NotasMedicasSegumiento",c.getString(10));

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

}
