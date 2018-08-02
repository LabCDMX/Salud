package mx.digitalcoaster.rzertuche.medicoencasa.QuestionsFragments;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity;
import mx.digitalcoaster.rzertuche.medicoencasa.DataBase.DataBaseDB;
import mx.digitalcoaster.rzertuche.medicoencasa.DataBase.DataBaseHelper;
import mx.digitalcoaster.rzertuche.medicoencasa.R;
import mx.digitalcoaster.rzertuche.medicoencasa.Utils.SharedPreferences;

import static mx.digitalcoaster.rzertuche.medicoencasa.Fragments.PacientesFragment.isSinExp;
import static mx.digitalcoaster.rzertuche.medicoencasa.Fragments.VisitasFragment.isSeguimiento;

;


public class NotasHojaDiaria extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    SharedPreferences sharedPreferences;

    EditText textArea_information, textArea_information2, textArea_information3, textArea_information4;
    ImageButton next;
    TextView textViewFecha;

    private SQLiteDatabase db = null;      // Objeto para utilizar la base de datos
    private DataBaseHelper sqliteHelper;   // Objeto para abrir la base de Datos
    private Cursor c = null;

    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;






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
        return inflater.inflate(R.layout.dialog_question_notas_hoja_diaria, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        //Obtencion de datos del sharedPreferences
        sharedPreferences = SharedPreferences.getInstance();

        ImageButton next = (ImageButton) getActivity().findViewById(R.id.next);
        textArea_information = getActivity().findViewById(R.id.textArea_information);
        textArea_information2 = getActivity().findViewById(R.id.textArea_information2);
        textViewFecha = getActivity().findViewById(R.id.textViewFecha);

        textViewFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                dateFormatter = new SimpleDateFormat("dd/MM/yyyy");

                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                textViewFecha.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.getDatePicker().setSpinnersShown(true);
                datePickerDialog.setCancelable(false);
                datePickerDialog.show();

            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveAllData();
                addNewVisita();
                ((MainActivity)getActivity()).stopCronometro();
                ((MainActivity)getActivity()).succededClinica();

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

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void saveAllData(){

    }

    private void addNewVisita(){

        String auxVisita = sharedPreferences.getStringData("numero_visita");
        int numeroVisita = Integer.valueOf(auxVisita)+1;

        db = getActivity().openOrCreateDatabase(DataBaseDB.DB_NAME, Context.MODE_PRIVATE ,null);

        /*------------------------- Revisar si existe ------------------------*/
        c = db.rawQuery("SELECT * FROM " + DataBaseDB.TABLE_NAME_PACIENTES_SEGUIMIENTO, null);
        try {
            if(c.moveToFirst()) {

                ContentValues values = new ContentValues();

                values.put(DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_NOMBRE,sharedPreferences.getStringData("nameSeguimiento"));
                values.put(DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_CURP, sharedPreferences.getStringData("curpSeguimiento"));
                values.put(DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_DIAGNOSTICO, textArea_information.getText().toString());
                values.put(DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_TRATAMIENTO, textArea_information2.getText().toString());
                values.put(DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_EXPEDIENTE, sharedPreferences.getStringData("Expediente"));
                values.put(DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_NOTAS_ENFERMERIA, sharedPreferences.getStringData("NotasEnfermeria"));
                values.put(DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_SUBJETIVO, sharedPreferences.getStringData("SubjetivoNotas"));
                values.put(DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_HEMOTIPO, sharedPreferences.getStringData("Hemotipo"));
                values.put(DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_PESO, sharedPreferences.getStringData("Peso"));
                values.put(DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_ESTATURA, sharedPreferences.getStringData("Estatura"));
                values.put(DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_TENSION_ARTERIAL, sharedPreferences.getStringData("Tension1") +"/"+ sharedPreferences.getStringData("Tension2"));
                values.put(DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_FRECUENCIA_CARDIACA, sharedPreferences.getStringData("Frecuencia Cardiaca"));
                values.put(DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_FRECUENCIA_RESPIRATORIA, sharedPreferences.getStringData("Frecuencia Respiratoria"));
                values.put(DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_TALLA, sharedPreferences.getStringData("Talla"));
                values.put(DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_PULSO, sharedPreferences.getStringData("Pulso"));
                values.put(DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_GLUCEMIA, sharedPreferences.getStringData("Glucemia"));
                values.put(DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_TEMPERATURA, sharedPreferences.getStringData("Temperatura"));
                values.put(DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_FECHA, textViewFecha.getText().toString());
                values.put(DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_NUMERO, String.valueOf(numeroVisita));


                db.insert(DataBaseDB.TABLE_NAME_PACIENTES_SEGUIMIENTO, null, values);
                System.out.println("Visita  insertada correctamente");
            }
            else {

                ContentValues values = new ContentValues();

                values.put(DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_NOMBRE,sharedPreferences.getStringData("nameSeguimiento"));
                values.put(DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_CURP, sharedPreferences.getStringData("curpSeguimiento"));
                values.put(DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_DIAGNOSTICO, textArea_information.getText().toString());
                values.put(DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_TRATAMIENTO, textArea_information2.getText().toString());
                values.put(DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_EXPEDIENTE, sharedPreferences.getStringData("Expediente"));
                values.put(DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_NOTAS_ENFERMERIA, sharedPreferences.getStringData("NotasEnfermeria"));
                values.put(DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_SUBJETIVO, sharedPreferences.getStringData("SubjetivoNotas"));
                values.put(DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_HEMOTIPO, sharedPreferences.getStringData("Hemotipo"));
                values.put(DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_PESO, sharedPreferences.getStringData("Peso"));
                values.put(DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_ESTATURA, sharedPreferences.getStringData("Estatura"));
                values.put(DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_TENSION_ARTERIAL, sharedPreferences.getStringData("Tension1") + sharedPreferences.getStringData("Tension2"));
                values.put(DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_FRECUENCIA_CARDIACA, sharedPreferences.getStringData("Frecuencia Cardiaca"));
                values.put(DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_FRECUENCIA_RESPIRATORIA, sharedPreferences.getStringData("Frecuencia Respiratoria"));
                values.put(DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_TALLA, sharedPreferences.getStringData("Talla"));
                values.put(DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_PULSO, sharedPreferences.getStringData("Pulso"));
                values.put(DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_GLUCEMIA, sharedPreferences.getStringData("Glucemia"));
                values.put(DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_TEMPERATURA, sharedPreferences.getStringData("Temperatura"));
                values.put(DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_FECHA, textViewFecha.getText().toString());
                values.put(DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_NUMERO, String.valueOf(numeroVisita));


                db.insert(DataBaseDB.TABLE_NAME_PACIENTES_SEGUIMIENTO, null, values);
                System.out.println("Sin expediente insertado correctamente");

            }
            c.close();
        } catch (SQLException ex) {
            System.out.println("Error al insertar productos: " + ex);
        }
        db.close();
        //updateVisita(String.valueOf(numeroVisita), sharedPreferences.getStringData("Expediente"));
    }



}
