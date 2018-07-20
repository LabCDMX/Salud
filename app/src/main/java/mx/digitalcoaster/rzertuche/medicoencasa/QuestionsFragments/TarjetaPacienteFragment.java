package mx.digitalcoaster.rzertuche.medicoencasa.QuestionsFragments;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity;
import mx.digitalcoaster.rzertuche.medicoencasa.DataBase.DataBaseDB;
import mx.digitalcoaster.rzertuche.medicoencasa.DataBase.DataBaseHelper;
import mx.digitalcoaster.rzertuche.medicoencasa.R;
import mx.digitalcoaster.rzertuche.medicoencasa.Utils.SharedPreferences;

import static mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity.inicio;
import static mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity.registros;
import static mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity.seguimiento;
import static mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity.sincronizacion;
import static mx.digitalcoaster.rzertuche.medicoencasa.Fragments.PacientesFragment.isSinExp;
import static mx.digitalcoaster.rzertuche.medicoencasa.Fragments.VisitasFragment.isSeguimiento;
import static mx.digitalcoaster.rzertuche.medicoencasa.QuestionsFragments.HistoriaClinicaFragment.cadenaCardio;
import static mx.digitalcoaster.rzertuche.medicoencasa.QuestionsFragments.HistoriaClinicaFragment.cadenaDiabetes;
import static mx.digitalcoaster.rzertuche.medicoencasa.QuestionsFragments.HistoriaClinicaFragment.cadenaDis;
import static mx.digitalcoaster.rzertuche.medicoencasa.QuestionsFragments.HistoriaClinicaFragment.cadenaEnf;
import static mx.digitalcoaster.rzertuche.medicoencasa.QuestionsFragments.HistoriaClinicaFragment.cadenaHTA;
import static mx.digitalcoaster.rzertuche.medicoencasa.QuestionsFragments.HistoriaClinicaFragment.cadenaObe;
import static mx.digitalcoaster.rzertuche.medicoencasa.QuestionsFragments.HistoriaClinicaFragment.cadenaPersonales;

;


public class TarjetaPacienteFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    SharedPreferences sharedPreferences;

    TextView tvNombreItem, tvCurpItem, tvDireccionItem,textViewFecha;
    ImageButton next;
    ImageView status;
    EditText diagnostico, tratamiento,textViewPeso,textViewExpediente;

    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;


    private SQLiteDatabase db = null;      // Objeto para utilizar la base de datos
    private DataBaseHelper sqliteHelper;   // Objeto para abrir la base de Datos
    private Cursor c = null;



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
        return inflater.inflate(R.layout.fragment_tarjeta_paciente, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        //Obtencion de datos del sharedPreferences
        sharedPreferences = SharedPreferences.getInstance();

        tvNombreItem = (TextView) getActivity().findViewById(R.id.tvNombreItem);
        tvCurpItem = (TextView) getActivity().findViewById(R.id.tvCurpItem);
        tvDireccionItem = (TextView) getActivity().findViewById(R.id.tvDireccionItem);
        textViewFecha = (TextView) getActivity().findViewById(R.id.textViewFecha);
        textViewPeso = (EditText) getActivity().findViewById(R.id.textViewPeso);
        textViewExpediente = (EditText) getActivity().findViewById(R.id.textViewExpediente);



        if(isSinExp){
            textViewExpediente.setVisibility(View.VISIBLE);
            textViewExpediente.setHint("Ingresa numero de expediente");
            textViewExpediente.setEnabled(true);
        }



            status = (ImageView) getActivity().findViewById(R.id.status);

        tratamiento = (EditText) getActivity().findViewById(R.id.textViewTratamiento);
        diagnostico = (EditText) getActivity().findViewById(R.id.textViewDiagnostico);

        tvNombreItem.setText(sharedPreferences.getStringData("nameHistoric"));
        tvCurpItem.setText(sharedPreferences.getStringData("curpHistoric"));
        tvDireccionItem.setText(sharedPreferences.getStringData("direccionHistoric"));


        sharedPreferences.setStringData("DiagnosticoGeneral",sharedPreferences.getStringData("Diagnostico1") + "\n"+
                sharedPreferences.getStringData("Diagnostico2" + "\n") +
                sharedPreferences.getStringData("Diagnostico3"));

        sharedPreferences.setStringData("TratamientoGeneral",sharedPreferences.getStringData("Tratamiento1") + "\n"+
                sharedPreferences.getStringData("Tratamiento2" + "\n") +
                sharedPreferences.getStringData("Tratamiento3"));



        //Diagnostico
        diagnostico.setText(sharedPreferences.getStringData("Diagnostico1") + "\n"+
                sharedPreferences.getStringData("Diagnostico2") + "\n" +
                sharedPreferences.getStringData("Diagnostico3")
        );


        //Tratamiento
        tratamiento.setText(sharedPreferences.getStringData("Tratamiento1") + "\n"+
                sharedPreferences.getStringData("Tratamiento2") + "\n" +
                sharedPreferences.getStringData("Tratamiento3")
        );


        String statusImage = sharedPreferences.getStringData("ImageItem");

        if(statusImage.equals("Sano")){
            status.setBackground(getResources().getDrawable(R.drawable.status_green));

        }else if(statusImage.equals("Sobrepeso")){
            status.setBackground(getResources().getDrawable(R.drawable.status_ambar));


        }else if(statusImage.equals("Obeso")){
            status.setBackground(getResources().getDrawable(R.drawable.status_red));


        }

        ImageButton next = (ImageButton) getActivity().findViewById(R.id.next);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isSeguimiento){
                    addNewVisita();
                    ((MainActivity)getActivity()).succededClinica();

                }


                if(textViewFecha.getText().toString().equals("")){
                    Toast.makeText(getActivity(),"Define la siguiente visita para poder avanzar", Toast.LENGTH_SHORT).show();
                }else{
                    if(textViewPeso.getText().toString().equals("")){
                        Toast.makeText(getActivity(),"Define el nombre de quien realizó la historia para poder avanzar", Toast.LENGTH_SHORT).show();
                    }else{
                        if(isSinExp){
                            if(textViewExpediente.getText().toString().equals("")){
                                Toast.makeText(getActivity(),"Define un expediente para poder avanzar", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(getActivity(),"TERMINASTE TUS FLUJILLOS Y SELLASTE EL CLIENTE", Toast.LENGTH_SHORT).show();
                                deleteUserSinExpediente(sharedPreferences.getStringData("curpItem"), sharedPreferences.getStringData("nameItem"));
                                ((MainActivity)getActivity()).succededClinica();
                            }

                        }else{
                            deleteUser(sharedPreferences.getStringData("curpHistoric"));
                            ((MainActivity)getActivity()).succededClinica();
                        }

                    }
                }


            }
        });

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

    private void deleteUser(String CURP){

        Date myDate = new Date();
        System.out.println(myDate);
        System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(myDate));

        String siguienteVisita = textViewFecha.getText().toString();
        String fechaActual = siguienteVisita;

        String nombreDoctor = textViewPeso.getText().toString();


        Log.e("NOMBRE HISTORIA CLINICA",sharedPreferences.getStringData("nameHistoric"));

        db = getActivity().openOrCreateDatabase(DataBaseDB.DB_NAME, Context.MODE_PRIVATE ,null);

        /*------------------------- Revisar si existe ------------------------*/
        c = db.rawQuery("SELECT * FROM " + DataBaseDB.TABLE_NAME_PACIENTES_SIN_EXPEDIENTE, null);
        try {
            if(c.moveToFirst()) {

                ContentValues values = new ContentValues();

                values.put(DataBaseDB.PACIENTES_EXPEDIENTE_NOMBRE,sharedPreferences.getStringData("nameHistoric"));
                values.put(DataBaseDB.PACIENTES_EXPEDIENTE_CURP, sharedPreferences.getStringData("curpHistoric"));
                values.put(DataBaseDB.PACIENTES_STATUS, sharedPreferences.getStringData("ImageItem"));
                values.put(DataBaseDB.PACIENTES_IMPRESION_DIAGNOSTICA, sharedPreferences.getStringData("DiagnosticoGeneral"));
                values.put(DataBaseDB.PACIENTES_TRATAMIENTO, sharedPreferences.getStringData("TratamientoGeneral"));
                values.put(DataBaseDB.PACIENTES_SIGUIENTE_VISITA, fechaActual);
                values.put(DataBaseDB.PACIENTES_ELABORO, nombreDoctor);
                values.put(DataBaseDB.PACIENTES_RESPIRATORIO, sharedPreferences.getStringData("Respiratorio"));
                values.put(DataBaseDB.PACIENTES_CARDIO, sharedPreferences.getStringData("Cardio"));
                values.put(DataBaseDB.PACIENTES_DIGESTIVO, sharedPreferences.getStringData("Digestivo"));
                values.put(DataBaseDB.PACIENTES_URINARIO, sharedPreferences.getStringData("Urinario"));
                values.put(DataBaseDB.PACIENTES_REPRODUCTOR, sharedPreferences.getStringData("Reproductor"));
                values.put(DataBaseDB.PACIENTES_HEMOLI, sharedPreferences.getStringData("Hemo"));
                values.put(DataBaseDB.PACIENTES_ENDOCRINO, sharedPreferences.getStringData("Endocrino"));
                values.put(DataBaseDB.PACIENTES_NERVIOSO, sharedPreferences.getStringData("Nervioso"));
                values.put(DataBaseDB.PACIENTES_PIEL, sharedPreferences.getStringData("Piel"));
                values.put(DataBaseDB.PACIENTES_HABITUS, sharedPreferences.getStringData("Habitus"));
                values.put(DataBaseDB.PACIENTES_CABEZA, sharedPreferences.getStringData("Cabeza"));
                values.put(DataBaseDB.PACIENTES_CUELLO, sharedPreferences.getStringData("Cuello"));
                values.put(DataBaseDB.PACIENTES_PESO, sharedPreferences.getStringData("Peso"));
                values.put(DataBaseDB.PACIENTES_ESTATURA, sharedPreferences.getStringData("Estatura"));
                values.put(DataBaseDB.PACIENTES_HEMOTIPO, sharedPreferences.getStringData("Hemotipo"));
                values.put(DataBaseDB.PACIENTES_TALLA, sharedPreferences.getStringData("Talla"));
                values.put(DataBaseDB.PACIENTES_PULSO, sharedPreferences.getStringData("Pulso"));
                values.put(DataBaseDB.PACIENTES_ANTECEDENTES_CARDIO, sharedPreferences.getStringData(cadenaCardio));
                values.put(DataBaseDB.PACIENTES_ANTECEDENTES_HDA, sharedPreferences.getStringData(cadenaHTA));
                values.put(DataBaseDB.PACIENTES_PERSONALES_PATOLOGICOS, sharedPreferences.getStringData(cadenaPersonales));
                values.put(DataBaseDB.PACIENTES_ANTECEDENTES_OBESIDAD, sharedPreferences.getStringData(cadenaObe));
                values.put(DataBaseDB.PACIENTES_ANTECEDENTES_DIABETES, sharedPreferences.getStringData(cadenaDiabetes));
                values.put(DataBaseDB.PACIENTES_ANTECEDENTES_DISLEP, sharedPreferences.getStringData(cadenaDis));
                values.put(DataBaseDB.PACIENTES_ANTECEDENTES_CEREBRO, sharedPreferences.getStringData(cadenaEnf));




                db.insert(DataBaseDB.TABLE_NAME_PACIENTES_SIN_EXPEDIENTE, null, values);
                System.out.println("Visita  insertada correctamente");
            }
            else {

                ContentValues values = new ContentValues();

                values.put(DataBaseDB.PACIENTES_EXPEDIENTE_NOMBRE,sharedPreferences.getStringData("nameHistoric"));
                values.put(DataBaseDB.PACIENTES_EXPEDIENTE_CURP, sharedPreferences.getStringData("curpHistoric"));
                values.put(DataBaseDB.PACIENTES_STATUS, sharedPreferences.getStringData("ImageItem"));
                values.put(DataBaseDB.PACIENTES_IMPRESION_DIAGNOSTICA, sharedPreferences.getStringData("DiagnosticoGeneral"));
                values.put(DataBaseDB.PACIENTES_TRATAMIENTO, sharedPreferences.getStringData("TratamientoGeneral"));
                values.put(DataBaseDB.PACIENTES_SIGUIENTE_VISITA, fechaActual);
                values.put(DataBaseDB.PACIENTES_ELABORO, nombreDoctor);
                values.put(DataBaseDB.PACIENTES_RESPIRATORIO, sharedPreferences.getStringData("Respiratorio"));
                values.put(DataBaseDB.PACIENTES_CARDIO, sharedPreferences.getStringData("Cardio"));
                values.put(DataBaseDB.PACIENTES_DIGESTIVO, sharedPreferences.getStringData("Digestivo"));
                values.put(DataBaseDB.PACIENTES_URINARIO, sharedPreferences.getStringData("Urinario"));
                values.put(DataBaseDB.PACIENTES_REPRODUCTOR, sharedPreferences.getStringData("Reproductor"));
                values.put(DataBaseDB.PACIENTES_HEMOLI, sharedPreferences.getStringData("Hemo"));
                values.put(DataBaseDB.PACIENTES_ENDOCRINO, sharedPreferences.getStringData("Endocrino"));
                values.put(DataBaseDB.PACIENTES_NERVIOSO, sharedPreferences.getStringData("Nervioso"));
                values.put(DataBaseDB.PACIENTES_PIEL, sharedPreferences.getStringData("Piel"));
                values.put(DataBaseDB.PACIENTES_HABITUS, sharedPreferences.getStringData("Habitus"));
                values.put(DataBaseDB.PACIENTES_CABEZA, sharedPreferences.getStringData("Cabeza"));
                values.put(DataBaseDB.PACIENTES_CUELLO, sharedPreferences.getStringData("Cuello"));
                values.put(DataBaseDB.PACIENTES_PESO, sharedPreferences.getStringData("Peso"));
                values.put(DataBaseDB.PACIENTES_ESTATURA, sharedPreferences.getStringData("Estatura"));
                values.put(DataBaseDB.PACIENTES_HEMOTIPO, sharedPreferences.getStringData("Hemotipo"));
                values.put(DataBaseDB.PACIENTES_TALLA, sharedPreferences.getStringData("Talla"));
                values.put(DataBaseDB.PACIENTES_PULSO, sharedPreferences.getStringData("Pulso"));
                values.put(DataBaseDB.PACIENTES_ANTECEDENTES_CARDIO, sharedPreferences.getStringData(cadenaCardio));
                values.put(DataBaseDB.PACIENTES_ANTECEDENTES_HDA, sharedPreferences.getStringData(cadenaHTA));
                values.put(DataBaseDB.PACIENTES_PERSONALES_PATOLOGICOS, sharedPreferences.getStringData(cadenaPersonales));
                values.put(DataBaseDB.PACIENTES_ANTECEDENTES_OBESIDAD, sharedPreferences.getStringData(cadenaObe));
                values.put(DataBaseDB.PACIENTES_ANTECEDENTES_DIABETES, sharedPreferences.getStringData(cadenaDiabetes));
                values.put(DataBaseDB.PACIENTES_ANTECEDENTES_DISLEP, sharedPreferences.getStringData(cadenaDis));
                values.put(DataBaseDB.PACIENTES_ANTECEDENTES_CEREBRO, sharedPreferences.getStringData(cadenaEnf));



                db.insert(DataBaseDB.TABLE_NAME_PACIENTES_SIN_EXPEDIENTE, null, values);
                System.out.println("Sin expediente insertado correctamente");

            }
            c.close();
        } catch (SQLException ex) {
            System.out.println("Error al insertar productos: " + ex);
        }

        db.delete(DataBaseDB.TABLE_NAME_PACIENTES,DataBaseDB.PACIENTES_CURP + "=? AND "+ DataBaseDB.PACIENTES_NOMBRE + "=?",new String[]{CURP,sharedPreferences.getStringData("nameHistoric")});


        db.close();
    }


    private void deleteUserSinExpediente(String CURP,String name){

        /* Date myDate = new Date();
        System.out.println(myDate);
        System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(myDate));

        String siguienteVisita = textViewFecha.getText().toString();
        String fechaActual = siguienteVisita;

        String nombreDoctor = textViewPeso.getText().toString();
        */
        Date myDate = new Date();
        System.out.println(myDate);
        System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(myDate));
        String fecha= new SimpleDateFormat("yyyy-MM-dd").format(myDate);

        db = getActivity().openOrCreateDatabase(DataBaseDB.DB_NAME, Context.MODE_PRIVATE ,null);

        /*------------------------- Revisar si existe ------------------------*/
        c = db.rawQuery("SELECT * FROM " + DataBaseDB.TABLE_NAME_PACIENTES_VISITAS, null);
        try {
            if(c.moveToFirst()) {

                ContentValues values = new ContentValues();

                values.put(DataBaseDB.PACIENTES_VISITA_NOMBRE,sharedPreferences.getStringData("nameItem"));
                values.put(DataBaseDB.PACIENTES_VISITA_CURP, sharedPreferences.getStringData("curpItem"));
                values.put(DataBaseDB.PACIENTES_VISITA_DIRECCION, sharedPreferences.getStringData("EXPEDIENTE ASIGNADO"));
                values.put(DataBaseDB.PACIENTES_VISITA_STATUS, sharedPreferences.getStringData("ImageItem"));
                values.put(DataBaseDB.PACIENTES_VISITA_DIAGNOSTICO, sharedPreferences.getStringData("DiagnosticoGeneral"));
                values.put(DataBaseDB.PACIENTES_VISITA_FECHA, fecha);
                values.put(DataBaseDB.PACIENTES_VISITA_NUMERO, "1");



                db.insert(DataBaseDB.TABLE_NAME_PACIENTES_VISITAS, null, values);
                System.out.println("Visita  insertada correctamente");
            }
            else {

                ContentValues values = new ContentValues();

                values.put(DataBaseDB.PACIENTES_VISITA_NOMBRE,sharedPreferences.getStringData("nameSeguimiento"));
                values.put(DataBaseDB.PACIENTES_VISITA_CURP, sharedPreferences.getStringData("curpSeguimiento"));
                values.put(DataBaseDB.PACIENTES_VISITA_DIRECCION, sharedPreferences.getStringData("EXPEDIENTE ASIGNADO"));
                values.put(DataBaseDB.PACIENTES_VISITA_STATUS, sharedPreferences.getStringData("ImageItem"));
                values.put(DataBaseDB.PACIENTES_VISITA_DIAGNOSTICO, sharedPreferences.getStringData("DiagnosticoGeneral"));
                values.put(DataBaseDB.PACIENTES_VISITA_FECHA, fecha);
                values.put(DataBaseDB.PACIENTES_VISITA_NUMERO, "1");

                db.insert(DataBaseDB.TABLE_NAME_PACIENTES_VISITAS, null, values);
                System.out.println("Sin expediente insertado correctamente");

            }
            c.close();
        } catch (SQLException ex) {
            System.out.println("Error al insertar productos: " + ex);
        }

        db.delete(DataBaseDB.TABLE_NAME_PACIENTES_SIN_EXPEDIENTE,DataBaseDB.PACIENTES_EXPEDIENTE_CURP + "=? AND "+ DataBaseDB.PACIENTES_EXPEDIENTE_NOMBRE + "=?",new String[]{CURP,name});


        db.close();
    }




    private void addNewVisita(){

        /* Date myDate = new Date();
        System.out.println(myDate);
        System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(myDate));

        String siguienteVisita = textViewFecha.getText().toString();
        String fechaActual = siguienteVisita;

        String nombreDoctor = textViewPeso.getText().toString();
        */
        Date myDate = new Date();
        System.out.println(myDate);
        System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(myDate));
        String fecha= new SimpleDateFormat("yyyy-MM-dd").format(myDate);


        String auxVisita = sharedPreferences.getStringData("numero_visita");
        String numeroVisita = String.valueOf(Integer.valueOf(auxVisita)+1);

        db = getActivity().openOrCreateDatabase(DataBaseDB.DB_NAME, Context.MODE_PRIVATE ,null);

        /*------------------------- Revisar si existe ------------------------*/
        c = db.rawQuery("SELECT * FROM " + DataBaseDB.TABLE_NAME_PACIENTES_VISITAS, null);
        try {
            if(c.moveToFirst()) {

                ContentValues values = new ContentValues();

                values.put(DataBaseDB.PACIENTES_VISITA_NOMBRE,sharedPreferences.getStringData("nameItem"));
                values.put(DataBaseDB.PACIENTES_VISITA_CURP, sharedPreferences.getStringData("curpItem"));
                values.put(DataBaseDB.PACIENTES_VISITA_DIRECCION, sharedPreferences.getStringData("EXPEDIENTE ASIGNADO"));
                values.put(DataBaseDB.PACIENTES_VISITA_STATUS, sharedPreferences.getStringData("ImageItem"));
                values.put(DataBaseDB.PACIENTES_VISITA_DIAGNOSTICO, sharedPreferences.getStringData("DiagnosticoGeneral"));
                values.put(DataBaseDB.PACIENTES_VISITA_FECHA, fecha);
                values.put(DataBaseDB.PACIENTES_VISITA_NUMERO, numeroVisita );



                db.insert(DataBaseDB.TABLE_NAME_PACIENTES_VISITAS, null, values);
                System.out.println("Visita  insertada correctamente");
            }
            else {

                ContentValues values = new ContentValues();

                values.put(DataBaseDB.PACIENTES_VISITA_NOMBRE,sharedPreferences.getStringData("nameItem"));
                values.put(DataBaseDB.PACIENTES_VISITA_CURP, sharedPreferences.getStringData("curpItem"));
                values.put(DataBaseDB.PACIENTES_VISITA_DIRECCION, sharedPreferences.getStringData("EXPEDIENTE ASIGNADO"));
                values.put(DataBaseDB.PACIENTES_VISITA_STATUS, sharedPreferences.getStringData("ImageItem"));
                values.put(DataBaseDB.PACIENTES_VISITA_DIAGNOSTICO, sharedPreferences.getStringData("DiagnosticoGeneral"));
                values.put(DataBaseDB.PACIENTES_VISITA_FECHA, fecha);
                values.put(DataBaseDB.PACIENTES_VISITA_NUMERO, numeroVisita);

                db.insert(DataBaseDB.TABLE_NAME_PACIENTES_VISITAS, null, values);
                System.out.println("Sin expediente insertado correctamente");

            }
            c.close();
        } catch (SQLException ex) {
            System.out.println("Error al insertar productos: " + ex);
        }


        db.close();
    }



}
