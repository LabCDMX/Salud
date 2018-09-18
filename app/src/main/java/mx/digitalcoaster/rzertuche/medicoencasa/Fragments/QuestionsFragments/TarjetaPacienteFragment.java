package mx.digitalcoaster.rzertuche.medicoencasa.Fragments.QuestionsFragments;

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
import static mx.digitalcoaster.rzertuche.medicoencasa.Fragments.QuestionsFragments.HistoriaClinicaFragment.cadenaCardio;
import static mx.digitalcoaster.rzertuche.medicoencasa.Fragments.QuestionsFragments.HistoriaClinicaFragment.cadenaDiabetes;
import static mx.digitalcoaster.rzertuche.medicoencasa.Fragments.QuestionsFragments.HistoriaClinicaFragment.cadenaDis;
import static mx.digitalcoaster.rzertuche.medicoencasa.Fragments.QuestionsFragments.HistoriaClinicaFragment.cadenaEnf;
import static mx.digitalcoaster.rzertuche.medicoencasa.Fragments.QuestionsFragments.HistoriaClinicaFragment.cadenaHTA;
import static mx.digitalcoaster.rzertuche.medicoencasa.Fragments.QuestionsFragments.HistoriaClinicaFragment.cadenaObe;
import static mx.digitalcoaster.rzertuche.medicoencasa.Fragments.QuestionsFragments.HistoriaClinicaFragment.cadenaPersonales;




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
    EditText diagnostico, tratamiento,nombreMedico,textViewExpediente;

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
        nombreMedico = (EditText) getActivity().findViewById(R.id.textViewPeso);
        textViewExpediente = (EditText) getActivity().findViewById(R.id.textViewExpediente);

        status = (ImageView) getActivity().findViewById(R.id.status);
        tratamiento = (EditText) getActivity().findViewById(R.id.textViewTratamiento);
        diagnostico = (EditText) getActivity().findViewById(R.id.textViewDiagnostico);


        if(isSinExp){
            getDatosSinExp();
            String name = sharedPreferences.getStringData("nameItem");

            nombreMedico.setText(sharedPreferences.getStringData("Elaboro"+name));
            textViewFecha.setText(sharedPreferences.getStringData("Siguiente Visita"+name));
            tratamiento.setText(sharedPreferences.getStringData("Tratamiento"+name));
            diagnostico.setText(sharedPreferences.getStringData("Diagnostico"+name));


            tvNombreItem.setText(sharedPreferences.getStringData("nameItem"));
            tvCurpItem.setText(sharedPreferences.getStringData("curpItem"));
            tvDireccionItem.setText(sharedPreferences.getStringData("direccionItem"));

            nombreMedico.setEnabled(false);
            textViewFecha.setEnabled(false);

            textViewExpediente.setVisibility(View.VISIBLE);
            textViewExpediente.setHint("Ingresa numero de expediente");
            textViewExpediente.setEnabled(true);



            String statusImage = sharedPreferences.getStringData("ImageItem");

            if(statusImage.equals("Sano")){
                status.setBackground(getResources().getDrawable(R.drawable.status_green));

            }else if(statusImage.equals("Sobrepeso")){
                status.setBackground(getResources().getDrawable(R.drawable.status_ambar));


            }else if(statusImage.equals("Obeso")){
                status.setBackground(getResources().getDrawable(R.drawable.status_red));


            }


        }else{


            tvNombreItem.setText(sharedPreferences.getStringData("nameHistoric"));
            tvCurpItem.setText(sharedPreferences.getStringData("curpHistoric"));
            tvDireccionItem.setText(sharedPreferences.getStringData("direccionHistoric"));


            sharedPreferences.setStringData("DiagnosticoGeneral",sharedPreferences.getStringData("Diagnostico1") + "\n"+
                    sharedPreferences.getStringData("Diagnostico2") + "\n"+
                    sharedPreferences.getStringData("Diagnostico3"));

            sharedPreferences.setStringData("TratamientoGeneral",sharedPreferences.getStringData("Tratamiento1") + "\n"+
                    sharedPreferences.getStringData("Tratamiento2") +"\n"+
                    sharedPreferences.getStringData("Tratamiento3"));



            //Diagnostico
            diagnostico.setText(sharedPreferences.getStringData("DiagnosticoGeneral"));


            //Tratamiento
            tratamiento.setText(sharedPreferences.getStringData("TratamientoGeneral"));


            String statusImage = sharedPreferences.getStringData("ImageItem");

            if(statusImage.equals("Sano")){
                status.setBackground(getResources().getDrawable(R.drawable.status_green));

            }else if(statusImage.equals("Sobrepeso")){
                status.setBackground(getResources().getDrawable(R.drawable.status_ambar));


            }else if(statusImage.equals("Obeso")){
                status.setBackground(getResources().getDrawable(R.drawable.status_red));


            }


        }

        ImageButton next = (ImageButton) getActivity().findViewById(R.id.next);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((MainActivity)getActivity()).stopCronometro();

                if(isSeguimiento){
                    addNewVisita();
                    ((MainActivity)getActivity()).succededClinica();

                }else{
                    if(textViewFecha.getText().toString().equals("")){
                        Toast.makeText(getActivity(),"Define la siguiente visita para poder avanzar", Toast.LENGTH_SHORT).show();
                    }else{
                        if(nombreMedico.getText().toString().equals("")){
                            Toast.makeText(getActivity(),"Define el nombre de quien realizó la historia para poder avanzar", Toast.LENGTH_SHORT).show();
                        }else{
                            if(isSinExp){
                                if(textViewExpediente.getText().toString().equals("")){
                                    Toast.makeText(getActivity(),"Define un expediente para poder avanzar", Toast.LENGTH_SHORT).show();
                                }else{
                                    //Toast.makeText(getActivity(),"TERMINASTE TUS FLUJILLOS Y SELLASTE EL CLIENTE", Toast.LENGTH_SHORT).show();
                                    sharedPreferences.setStringData("Expediente", textViewExpediente.getText().toString());
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
        String siguienteVisita = textViewFecha.getText().toString();
        String fechaActual = siguienteVisita;
        String nombreDoctor = nombreMedico.getText().toString();
        String name = sharedPreferences.getStringData("nameHistoric");
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
                values.put(DataBaseDB.PACIENTES_ANTECEDENTES_CARDIO, sharedPreferences.getStringData("cadenaCardio"+name));
                values.put(DataBaseDB.PACIENTES_ANTECEDENTES_HDA, sharedPreferences.getStringData("cadenaHTA"+name));
                values.put(DataBaseDB.PACIENTES_PADECIMIENTOS_ACTUALES, sharedPreferences.getStringData("cadenaPersonales"+name));
                values.put(DataBaseDB.PACIENTES_ANTECEDENTES_OBESIDAD, sharedPreferences.getStringData("cadenaObe"+name));
                values.put(DataBaseDB.PACIENTES_ANTECEDENTES_DIABETES, sharedPreferences.getStringData("cadenaDiabetes"+name));
                values.put(DataBaseDB.PACIENTES_ANTECEDENTES_DISLEP, sharedPreferences.getStringData("cadenaDis"+name));
                values.put(DataBaseDB.PACIENTES_ANTECEDENTES_CEREBRO, sharedPreferences.getStringData("cadenaEnf"+name));
                values.put(DataBaseDB.PACIENTES_NOTAS_DOC, sharedPreferences.getStringData("SubjetivoNotas"));
                values.put(DataBaseDB.PACIENTES_NOTAS_ENFERMERIA, sharedPreferences.getStringData("NotasEnfermeria"));
                values.put(DataBaseDB.PACIENTES_TENSION, sharedPreferences.getStringData("Tension1") + "/" +sharedPreferences.getStringData("Tension2"));
                values.put(DataBaseDB.PACIENTES_GLUCEMIA, sharedPreferences.getStringData("Glucemia"));
                values.put(DataBaseDB.PACIENTES_CARDIACA, sharedPreferences.getStringData("Frecuencia Cardiaca"));
                values.put(DataBaseDB.PACIENTES_RESPIRATORIA, sharedPreferences.getStringData("Frecuencia Respiratoria"));
                values.put(DataBaseDB.PACIENTES_TEMPERATURA, sharedPreferences.getStringData("Temperatura"));
                values.put(DataBaseDB.PACIENTES_PERSONALES_HEREDO, sharedPreferences.getStringData("Heredofamiliares"));
                values.put(DataBaseDB.PACIENTES_PERSONALES_NO_PATOLOGICOS, sharedPreferences.getStringData("NoPatologicos"));
                values.put(DataBaseDB.PACIENTES_PERSONALES_PATOLOGICOS, sharedPreferences.getStringData("Patologicos"));
                values.put(DataBaseDB.PACIENTES_GINECOOBSTERICOS, sharedPreferences.getStringData("Ginecobstericos"));
                values.put(DataBaseDB.PACIENTES_TORAX, sharedPreferences.getStringData("Torax"));
                values.put(DataBaseDB.PACIENTES_ABDOMEN, sharedPreferences.getStringData("Abdomen"));
                values.put(DataBaseDB.PACIENTES_GINECOLOGICA, sharedPreferences.getStringData("Ginecologica"));
                values.put(DataBaseDB.PACIENTES_EXTREMIDADES, sharedPreferences.getStringData("Extremidades"));
                values.put(DataBaseDB.PACIENTES_COLUMNA, sharedPreferences.getStringData("Columna"));
                values.put(DataBaseDB.PACIENTES_NEUROLOGICA, sharedPreferences.getStringData("Neurologica"));
                values.put(DataBaseDB.PACIENTES_GENITALES, sharedPreferences.getStringData("Genitales"));




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
                values.put(DataBaseDB.PACIENTES_ANTECEDENTES_CARDIO, sharedPreferences.getStringData("cadenaCardio"+name));
                values.put(DataBaseDB.PACIENTES_ANTECEDENTES_HDA, sharedPreferences.getStringData("cadenaHTA"+name));
                values.put(DataBaseDB.PACIENTES_PADECIMIENTOS_ACTUALES, sharedPreferences.getStringData("cadenaPersonales"+name));
                values.put(DataBaseDB.PACIENTES_ANTECEDENTES_OBESIDAD, sharedPreferences.getStringData("cadenaObe"+name));
                values.put(DataBaseDB.PACIENTES_ANTECEDENTES_DIABETES, sharedPreferences.getStringData("cadenaDiabetes"+name));
                values.put(DataBaseDB.PACIENTES_ANTECEDENTES_DISLEP, sharedPreferences.getStringData("cadenaDis"+name));
                values.put(DataBaseDB.PACIENTES_ANTECEDENTES_CEREBRO, sharedPreferences.getStringData("cadenaEnf"+name));
                values.put(DataBaseDB.PACIENTES_NOTAS_DOC, sharedPreferences.getStringData("SubjetivoNotas"));
                values.put(DataBaseDB.PACIENTES_NOTAS_ENFERMERIA, sharedPreferences.getStringData("NotasEnfermeria"));
                values.put(DataBaseDB.PACIENTES_TENSION, sharedPreferences.getStringData("Tension1") + "/" +sharedPreferences.getStringData("Tension2"));
                values.put(DataBaseDB.PACIENTES_GLUCEMIA, sharedPreferences.getStringData("Glucemia"));
                values.put(DataBaseDB.PACIENTES_CARDIACA, sharedPreferences.getStringData("Frecuencia Cardiaca"));
                values.put(DataBaseDB.PACIENTES_RESPIRATORIA, sharedPreferences.getStringData("Frecuencia Respiratoria"));
                values.put(DataBaseDB.PACIENTES_TEMPERATURA, sharedPreferences.getStringData("Temperatura"));
                values.put(DataBaseDB.PACIENTES_PERSONALES_HEREDO, sharedPreferences.getStringData("Heredofamiliares"));
                values.put(DataBaseDB.PACIENTES_PERSONALES_NO_PATOLOGICOS, sharedPreferences.getStringData("NoPatologicos"));
                values.put(DataBaseDB.PACIENTES_PERSONALES_PATOLOGICOS, sharedPreferences.getStringData("Patologicos"));
                values.put(DataBaseDB.PACIENTES_GINECOOBSTERICOS, sharedPreferences.getStringData("Ginecobstericos"));
                values.put(DataBaseDB.PACIENTES_TORAX, sharedPreferences.getStringData("Torax"));
                values.put(DataBaseDB.PACIENTES_ABDOMEN, sharedPreferences.getStringData("Abdomen"));
                values.put(DataBaseDB.PACIENTES_GINECOLOGICA, sharedPreferences.getStringData("Ginecologica"));
                values.put(DataBaseDB.PACIENTES_EXTREMIDADES, sharedPreferences.getStringData("Extremidades"));
                values.put(DataBaseDB.PACIENTES_COLUMNA, sharedPreferences.getStringData("Columna"));
                values.put(DataBaseDB.PACIENTES_NEUROLOGICA, sharedPreferences.getStringData("Neurologica"));
                values.put(DataBaseDB.PACIENTES_GENITALES, sharedPreferences.getStringData("Genitales"));



                db.insert(DataBaseDB.TABLE_NAME_PACIENTES_SIN_EXPEDIENTE, null, values);
                System.out.println("Sin expediente insertado correctamente");

            }
            c.close();
        } catch (SQLException ex) {
            System.out.println("Error al insertar productos: " + ex);
        }

        db.delete(DataBaseDB.TABLE_NAME_PACIENTES,DataBaseDB.PACIENTES_CURP + "=? AND "+ DataBaseDB.PACIENTES_NOMBRE + "=?",new String[]{CURP,sharedPreferences.getStringData("nameHistoric")});


        db.close();


        //TABLA ESPEJO PARA GUARDAR LA PRIMER HISTORIA CLINICA
        // SUPER MEGA IMPORTANTEEEEEEEEE.......!!!!


        db = getActivity().openOrCreateDatabase(DataBaseDB.DB_NAME, Context.MODE_PRIVATE ,null);

        /*------------------------- Revisar si existe ------------------------*/
        c = db.rawQuery("SELECT * FROM " + DataBaseDB.TABLE_NAME_PACIENTES_SINCRONIZAR_HISTORIC, null);
        try {
            if(c.moveToFirst()) {

                ContentValues values = new ContentValues();

                values.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_NOMBRE,sharedPreferences.getStringData("nameHistoric"));
                values.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_CURP, sharedPreferences.getStringData("curpHistoric"));
                values.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_STATUS, sharedPreferences.getStringData("ImageItem"));
                values.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_DIAGNOSTICO, sharedPreferences.getStringData("DiagnosticoGeneral"));
                values.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_TRATAMIENTO, sharedPreferences.getStringData("TratamientoGeneral"));
                values.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_SIGUIENTE_VISITA, fechaActual);
                values.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_ELABORO, nombreDoctor);
                values.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_RESPIRATORIO, sharedPreferences.getStringData("Respiratorio"));
                values.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_CARDIO, sharedPreferences.getStringData("Cardio"));
                values.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_DIGESTIVO, sharedPreferences.getStringData("Digestivo"));
                values.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_URINARIO, sharedPreferences.getStringData("Urinario"));
                values.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_REPRODUCTOR, sharedPreferences.getStringData("Reproductor"));
                values.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_HEMOLI, sharedPreferences.getStringData("Hemo"));
                values.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_ENDOCRINO, sharedPreferences.getStringData("Endocrino"));
                values.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_NERVIOSO, sharedPreferences.getStringData("Nervioso"));
                values.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_PIEL, sharedPreferences.getStringData("Piel"));
                values.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_HABITUS, sharedPreferences.getStringData("Habitus"));
                values.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_CABEZA, sharedPreferences.getStringData("Cabeza"));
                values.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_CUELLO, sharedPreferences.getStringData("Cuello"));
                values.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_PESO, sharedPreferences.getStringData("Peso"));
                values.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_ESTATURA, sharedPreferences.getStringData("Estatura"));
                values.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_HEMOTIPO, sharedPreferences.getStringData("Hemotipo"));
                values.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_TALLA, sharedPreferences.getStringData("Talla"));
                values.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_PULSO, sharedPreferences.getStringData("Pulso"));
                values.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_ANTECEDENTES_CARDIO, sharedPreferences.getStringData("cadenaCardio"+name));
                values.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_ANTECEDENTES_HDA, sharedPreferences.getStringData("cadenaHTA"+name));
                values.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_PADECIMIENTOS_ACTUALES, sharedPreferences.getStringData("cadenaPersonales"+name));
                values.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_ANTECEDENTES_OBESIDAD, sharedPreferences.getStringData("cadenaObe"+name));
                values.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_ANTECEDENTES_DIABETES, sharedPreferences.getStringData("cadenaDiabetes"+name));
                values.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_ANTECEDENTES_DISLEP, sharedPreferences.getStringData("cadenaDis"+name));
                values.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_ANTECEDENTES_CEREBRO, sharedPreferences.getStringData("cadenaEnf"+name));
                values.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_NOTAS_DOC, sharedPreferences.getStringData("SubjetivoNotas"));
                values.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_NOTAS_ENFERMERIA, sharedPreferences.getStringData("NotasEnfermeria"));


                values.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_PERSONALES_HEREDO, sharedPreferences.getStringData("Heredofamiliares"));
                values.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_PERSONALES_NO_PATOLOGICOS, sharedPreferences.getStringData("NoPatologicos"));
                values.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_PERSONALES_PATOLOGICOS, sharedPreferences.getStringData("Patologicos"));
                values.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_GINECOOBSTERICOS, sharedPreferences.getStringData("Ginecobstericos"));
                values.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_TORAX, sharedPreferences.getStringData("Torax"));
                values.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_ABDOMEN, sharedPreferences.getStringData("Abdomen"));
                values.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_GINECOLOGICA, sharedPreferences.getStringData("Ginecologica"));
                values.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_EXTREMIDADES, sharedPreferences.getStringData("Extremidades"));
                values.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_COLUMNA, sharedPreferences.getStringData("Columna"));
                values.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_NEUROLOGICA, sharedPreferences.getStringData("Neurologica"));
                values.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_GENITALES, sharedPreferences.getStringData("Genitales"));




                db.insert(DataBaseDB.TABLE_NAME_PACIENTES_SINCRONIZAR_HISTORIC, null, values);
                System.out.println("Visita  insertada correctamente");
            }
            else {

                ContentValues values = new ContentValues();


                values.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_NOMBRE,sharedPreferences.getStringData("nameHistoric"));
                values.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_CURP, sharedPreferences.getStringData("curpHistoric"));
                values.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_STATUS, sharedPreferences.getStringData("ImageItem"));
                values.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_DIAGNOSTICO, sharedPreferences.getStringData("DiagnosticoGeneral"));
                values.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_TRATAMIENTO, sharedPreferences.getStringData("TratamientoGeneral"));
                values.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_SIGUIENTE_VISITA, fechaActual);
                values.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_ELABORO, nombreDoctor);
                values.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_RESPIRATORIO, sharedPreferences.getStringData("Respiratorio"));
                values.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_CARDIO, sharedPreferences.getStringData("Cardio"));
                values.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_DIGESTIVO, sharedPreferences.getStringData("Digestivo"));
                values.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_URINARIO, sharedPreferences.getStringData("Urinario"));
                values.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_REPRODUCTOR, sharedPreferences.getStringData("Reproductor"));
                values.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_HEMOLI, sharedPreferences.getStringData("Hemo"));
                values.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_ENDOCRINO, sharedPreferences.getStringData("Endocrino"));
                values.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_NERVIOSO, sharedPreferences.getStringData("Nervioso"));
                values.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_PIEL, sharedPreferences.getStringData("Piel"));
                values.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_HABITUS, sharedPreferences.getStringData("Habitus"));
                values.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_CABEZA, sharedPreferences.getStringData("Cabeza"));
                values.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_CUELLO, sharedPreferences.getStringData("Cuello"));
                values.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_PESO, sharedPreferences.getStringData("Peso"));
                values.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_ESTATURA, sharedPreferences.getStringData("Estatura"));
                values.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_HEMOTIPO, sharedPreferences.getStringData("Hemotipo"));
                values.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_TALLA, sharedPreferences.getStringData("Talla"));
                values.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_PULSO, sharedPreferences.getStringData("Pulso"));
                values.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_ANTECEDENTES_CARDIO, sharedPreferences.getStringData("cadenaCardio"+name));
                values.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_ANTECEDENTES_HDA, sharedPreferences.getStringData("cadenaHTA"+name));
                values.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_PADECIMIENTOS_ACTUALES, sharedPreferences.getStringData("cadenaPersonales"+name));
                values.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_ANTECEDENTES_OBESIDAD, sharedPreferences.getStringData("cadenaObe"+name));
                values.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_ANTECEDENTES_DIABETES, sharedPreferences.getStringData("cadenaDiabetes"+name));
                values.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_ANTECEDENTES_DISLEP, sharedPreferences.getStringData("cadenaDis"+name));
                values.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_ANTECEDENTES_CEREBRO, sharedPreferences.getStringData("cadenaEnf"+name));
                values.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_NOTAS_DOC, sharedPreferences.getStringData("SubjetivoNotas"));
                values.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_NOTAS_ENFERMERIA, sharedPreferences.getStringData("NotasEnfermeria"));

                values.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_PERSONALES_HEREDO, sharedPreferences.getStringData("Heredofamiliares"));
                values.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_PERSONALES_NO_PATOLOGICOS, sharedPreferences.getStringData("NoPatologicos"));
                values.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_PERSONALES_PATOLOGICOS, sharedPreferences.getStringData("Patologicos"));
                values.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_GINECOOBSTERICOS, sharedPreferences.getStringData("Ginecobstericos"));
                values.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_TORAX, sharedPreferences.getStringData("Torax"));
                values.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_ABDOMEN, sharedPreferences.getStringData("Abdomen"));
                values.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_GINECOLOGICA, sharedPreferences.getStringData("Ginecologica"));
                values.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_EXTREMIDADES, sharedPreferences.getStringData("Extremidades"));
                values.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_COLUMNA, sharedPreferences.getStringData("Columna"));
                values.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_NEUROLOGICA, sharedPreferences.getStringData("Neurologica"));
                values.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_GENITALES, sharedPreferences.getStringData("Genitales"));



                db.insert(DataBaseDB.TABLE_NAME_PACIENTES_SINCRONIZAR_HISTORIC, null, values);
                System.out.println("Sin expediente insertado correctamente");

            }
            c.close();
        } catch (SQLException ex) {
            System.out.println("Error al insertar productos: " + ex);
        }

        db.close();
    }


    private void deleteUserSinExpediente(String CURP,String name){

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
                values.put(DataBaseDB.PACIENTES_VISITA_STATUS, sharedPreferences.getStringData("ImageItem"));
                values.put(DataBaseDB.PACIENTES_VISITA_DIAGNOSTICO, sharedPreferences.getStringData("DiagnosticoGeneral"));
                values.put(DataBaseDB.PACIENTES_VISITA_EXPEDIENTE, sharedPreferences.getStringData("Expediente"));
                values.put(DataBaseDB.PACIENTES_VISITA_FECHA, fecha);
                values.put(DataBaseDB.PACIENTES_VISITA_NUMERO, "1");



                db.insert(DataBaseDB.TABLE_NAME_PACIENTES_VISITAS, null, values);
                System.out.println("Visita  insertada correctamente");
            }
            else {

                ContentValues values = new ContentValues();

                values.put(DataBaseDB.PACIENTES_VISITA_NOMBRE,sharedPreferences.getStringData("nameItem"));
                values.put(DataBaseDB.PACIENTES_VISITA_CURP, sharedPreferences.getStringData("curpItem"));
                values.put(DataBaseDB.PACIENTES_VISITA_STATUS, sharedPreferences.getStringData("ImageItem"));
                values.put(DataBaseDB.PACIENTES_VISITA_DIAGNOSTICO, sharedPreferences.getStringData("DiagnosticoGeneral"));
                values.put(DataBaseDB.PACIENTES_VISITA_EXPEDIENTE, sharedPreferences.getStringData("Expediente"));
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


        db = getActivity().openOrCreateDatabase(DataBaseDB.DB_NAME, Context.MODE_PRIVATE ,null);

        /*------------------------- Revisar si existe ------------------------*/
        c = db.rawQuery("SELECT * FROM " + DataBaseDB.TABLE_NAME_PACIENTES_SEGUIMIENTO, null);
        try {
            if(c.moveToFirst()) {

                ContentValues values = new ContentValues();

                values.put(DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_NOMBRE,sharedPreferences.getStringData("nameItem"));
                values.put(DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_CURP, sharedPreferences.getStringData("curpItem"));
                values.put(DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_STATUS, sharedPreferences.getStringData("ImageItem"));
                values.put(DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_DIAGNOSTICO, sharedPreferences.getStringData("DiagnosticoGeneral"));
                values.put(DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_TRATAMIENTO, sharedPreferences.getStringData("TratamientoGeneral"));
                values.put(DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_FECHA, fecha);
                values.put(DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_NUMERO, "1");
                values.put(DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_EXPEDIENTE, sharedPreferences.getStringData("Expediente"));
                values.put(DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_NOTAS_ENFERMERIA, sharedPreferences.getStringData("NotasEnfermeria"));
                values.put(DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_SUBJETIVO, sharedPreferences.getStringData("SubjetivoNotas"));
                values.put(DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_HEMOTIPO, sharedPreferences.getStringData("Hemotipo"));
                values.put(DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_PESO, sharedPreferences.getStringData("Peso"));
                values.put(DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_ESTATURA, sharedPreferences.getStringData("Estatura"));
                values.put(DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_TENSION_ARTERIAL, sharedPreferences.getStringData("Tension1") +"/"+sharedPreferences.getStringData("Tension2"));
                values.put(DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_FRECUENCIA_CARDIACA, sharedPreferences.getStringData("Frecuencia Cardiaca"));
                values.put(DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_FRECUENCIA_RESPIRATORIA, sharedPreferences.getStringData("Frecuencia Respiratoria"));
                values.put(DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_TALLA, sharedPreferences.getStringData("Talla"));
                values.put(DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_PULSO, sharedPreferences.getStringData("Pulso"));
                values.put(DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_GLUCEMIA, sharedPreferences.getStringData("Glucemia"));
                values.put(DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_TEMPERATURA, sharedPreferences.getStringData("Temperatura"));




                db.insert(DataBaseDB.TABLE_NAME_PACIENTES_SEGUIMIENTO, null, values);
                System.out.println("Visita  insertada correctamente");
            }
            else {

                ContentValues values = new ContentValues();

                values.put(DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_NOMBRE,sharedPreferences.getStringData("nameItem"));
                values.put(DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_CURP, sharedPreferences.getStringData("curpItem"));
                values.put(DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_STATUS, sharedPreferences.getStringData("ImageItem"));
                values.put(DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_DIAGNOSTICO, sharedPreferences.getStringData("DiagnosticoGeneral"));
                values.put(DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_TRATAMIENTO, sharedPreferences.getStringData("TratamientoGeneral"));
                values.put(DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_FECHA, fecha);
                values.put(DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_NUMERO, "1");
                values.put(DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_EXPEDIENTE, sharedPreferences.getStringData("Expediente"));
                values.put(DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_NOTAS_ENFERMERIA, sharedPreferences.getStringData("NotasEnfermeria"));
                values.put(DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_SUBJETIVO, sharedPreferences.getStringData("SubjetivoNotas"));
                values.put(DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_HEMOTIPO, sharedPreferences.getStringData("Hemotipo"));
                values.put(DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_PESO, sharedPreferences.getStringData("Peso"));
                values.put(DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_ESTATURA, sharedPreferences.getStringData("Estatura"));
                values.put(DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_TENSION_ARTERIAL, sharedPreferences.getStringData("Tension1") + "/"+sharedPreferences.getStringData("Tension2"));
                values.put(DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_FRECUENCIA_CARDIACA, sharedPreferences.getStringData("Frecuencia Cardiaca"));
                values.put(DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_FRECUENCIA_RESPIRATORIA, sharedPreferences.getStringData("Frecuencia Respiratoria"));
                values.put(DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_TALLA, sharedPreferences.getStringData("Talla"));
                values.put(DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_PULSO, sharedPreferences.getStringData("Pulso"));
                values.put(DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_GLUCEMIA, sharedPreferences.getStringData("Glucemia"));
                values.put(DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_TEMPERATURA, sharedPreferences.getStringData("Temperatura"));


                db.insert(DataBaseDB.TABLE_NAME_PACIENTES_SEGUIMIENTO, null, values);
                System.out.println("Sin expediente insertado correctamente");

            }
            c.close();
        } catch (SQLException ex) {
            System.out.println("Error al insertar productos: " + ex);
        }

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
        int numeroVisita = Integer.valueOf(auxVisita)+1;

        db = getActivity().openOrCreateDatabase(DataBaseDB.DB_NAME, Context.MODE_PRIVATE ,null);

        /*------------------------- Revisar si existe ------------------------*/
        c = db.rawQuery("SELECT * FROM " + DataBaseDB.TABLE_NAME_PACIENTES_SEGUIMIENTO, null);
        try {
            if(c.moveToFirst()) {

                ContentValues values = new ContentValues();

                values.put(DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_NOMBRE,sharedPreferences.getStringData("nameSeguimiento"));
                values.put(DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_CURP, sharedPreferences.getStringData("curpSeguimiento"));
                values.put(DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_STATUS, sharedPreferences.getStringData("ImageItem"));
                values.put(DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_DIAGNOSTICO, sharedPreferences.getStringData("DiagnosticoGeneral"));
                values.put(DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_TRATAMIENTO, sharedPreferences.getStringData("TratamientoGeneral"));
                values.put(DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_FECHA, fecha);
                values.put(DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_NUMERO, numeroVisita);
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



                db.insert(DataBaseDB.TABLE_NAME_PACIENTES_SEGUIMIENTO, null, values);
                System.out.println("Visita  insertada correctamente");
            }
            else {

                ContentValues values = new ContentValues();

                values.put(DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_NOMBRE,sharedPreferences.getStringData("nameSeguimiento"));
                values.put(DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_CURP, sharedPreferences.getStringData("curpSeguimiento"));
                //values.put(DataBaseDB.SEGUIMIENTO, sharedPreferences.getStringData("EXPEDIENTE ASIGNADO"));
                values.put(DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_STATUS, sharedPreferences.getStringData("ImageItem"));
                values.put(DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_DIAGNOSTICO, sharedPreferences.getStringData("DiagnosticoGeneral"));
                values.put(DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_TRATAMIENTO, sharedPreferences.getStringData("TratamientoGeneral"));
                values.put(DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_FECHA, fecha);
                values.put(DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_NUMERO, numeroVisita);
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


                db.insert(DataBaseDB.TABLE_NAME_PACIENTES_SEGUIMIENTO, null, values);
                System.out.println("Sin expediente insertado correctamente");

            }
            c.close();
        } catch (SQLException ex) {
            System.out.println("Error al insertar productos: " + ex);
        }



        db.close();
    }


    public void getDatosSinExp(){

        db = getActivity().openOrCreateDatabase(DataBaseDB.DB_NAME, Context.MODE_PRIVATE, null);
        try {
            c = db.rawQuery("SELECT * FROM " + DataBaseDB.TABLE_NAME_PACIENTES_SIN_EXPEDIENTE + " WHERE "
                    + DataBaseDB.PACIENTES_EXPEDIENTE_NOMBRE + " = '" + sharedPreferences.getStringData("nameItem") + "' AND "
                    + DataBaseDB.PACIENTES_EXPEDIENTE_CURP + " = '" + sharedPreferences.getStringData("curpItem")+"'", null);
            if (c.moveToFirst()) {
                do {
                    String name = sharedPreferences.getStringData("nameItem");


                    sharedPreferences.setStringData("Elaboro"+name,c.getString(5));
                    sharedPreferences.setStringData("Siguiente Visita"+name,c.getString(6));
                    sharedPreferences.setStringData("Diagnostico"+name,c.getString(51));
                    sharedPreferences.setStringData("Tratamiento"+name,c.getString(52));


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



}
