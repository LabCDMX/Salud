package mx.digitalcoaster.rzertuche.medicoencasa.Activitys;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.StrictMode;
import android.os.SystemClock;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import io.realm.Realm;
import mx.digitalcoaster.rzertuche.medicoencasa.DataBase.DataBaseDB;
import mx.digitalcoaster.rzertuche.medicoencasa.DataBase.DataBaseHelper;
import mx.digitalcoaster.rzertuche.medicoencasa.Fragments.DatosCensado;
import mx.digitalcoaster.rzertuche.medicoencasa.Fragments.InicioFragment;
import mx.digitalcoaster.rzertuche.medicoencasa.Fragments.InicioFragmentMain;
import mx.digitalcoaster.rzertuche.medicoencasa.Fragments.PacienteFragment;
import mx.digitalcoaster.rzertuche.medicoencasa.Fragments.PacientesFragment;
import mx.digitalcoaster.rzertuche.medicoencasa.Fragments.RegistroFragment;
import mx.digitalcoaster.rzertuche.medicoencasa.Fragments.SeguimientoFragment;
import mx.digitalcoaster.rzertuche.medicoencasa.Fragments.SincronizacionFragment;
import mx.digitalcoaster.rzertuche.medicoencasa.Fragments.SuccededFragment;
import mx.digitalcoaster.rzertuche.medicoencasa.Fragments.SuccededFragmentDom;
import mx.digitalcoaster.rzertuche.medicoencasa.Fragments.SuccededHistoriaFragment;
import mx.digitalcoaster.rzertuche.medicoencasa.Fragments.VisitaFragment;
import mx.digitalcoaster.rzertuche.medicoencasa.Fragments.VisitasFragment;
import mx.digitalcoaster.rzertuche.medicoencasa.QuestionsFragments.ContextoSocialFragment;
import mx.digitalcoaster.rzertuche.medicoencasa.QuestionsFragments.DatosGeneralesFragment;
import mx.digitalcoaster.rzertuche.medicoencasa.QuestionsFragments.DiagnosticoFragment;
import mx.digitalcoaster.rzertuche.medicoencasa.QuestionsFragments.HistoriaClinicaFragment;
import mx.digitalcoaster.rzertuche.medicoencasa.QuestionsFragments.NotasEnfermeria;
import mx.digitalcoaster.rzertuche.medicoencasa.QuestionsFragments.NotasHistoric;
import mx.digitalcoaster.rzertuche.medicoencasa.QuestionsFragments.NotasHojaDiaria;
import mx.digitalcoaster.rzertuche.medicoencasa.QuestionsFragments.QuestionCuidador;
import mx.digitalcoaster.rzertuche.medicoencasa.QuestionsFragments.QuestionDomFragment;
import mx.digitalcoaster.rzertuche.medicoencasa.QuestionsFragments.QuestionPoblacion;
import mx.digitalcoaster.rzertuche.medicoencasa.QuestionsFragments.QuestionsAlimentos;
import mx.digitalcoaster.rzertuche.medicoencasa.QuestionsFragments.QuestionsAntecedentes;
import mx.digitalcoaster.rzertuche.medicoencasa.QuestionsFragments.QuestionsAntecedentesPersonales;
import mx.digitalcoaster.rzertuche.medicoencasa.QuestionsFragments.QuestionsContextElectro;
import mx.digitalcoaster.rzertuche.medicoencasa.QuestionsFragments.QuestionsDomFragmentThree;
import mx.digitalcoaster.rzertuche.medicoencasa.QuestionsFragments.QuestionsDomFragmentTwo;
import mx.digitalcoaster.rzertuche.medicoencasa.QuestionsFragments.QuestionsEducacion;
import mx.digitalcoaster.rzertuche.medicoencasa.QuestionsFragments.QuestionsExploracion;
import mx.digitalcoaster.rzertuche.medicoencasa.QuestionsFragments.QuestionsFragment;
import mx.digitalcoaster.rzertuche.medicoencasa.QuestionsFragments.QuestionsFragmentTwo;
import mx.digitalcoaster.rzertuche.medicoencasa.QuestionsFragments.QuestionsHistoriaClinica;
import mx.digitalcoaster.rzertuche.medicoencasa.QuestionsFragments.TarjetaPacienteFragment;
import mx.digitalcoaster.rzertuche.medicoencasa.R;
import mx.digitalcoaster.rzertuche.medicoencasa.Utils.SharedPreferences;
import mx.digitalcoaster.rzertuche.medicoencasa.models.HistoriaClinica;

public class MainActivity extends AppCompatActivity implements
        InicioFragment.OnFragmentInteractionListener,
        SeguimientoFragment.OnFragmentInteractionListener,
        QuestionsFragment.OnFragmentInteractionListener,
        SincronizacionFragment.OnFragmentInteractionListener,
        PacienteFragment.OnFragmentInteractionListener,
        VisitaFragment.OnFragmentInteractionListener,
        InicioFragmentMain.OnFragmentInteractionListener,
        QuestionDomFragment.OnFragmentInteractionListener,
        DatosGeneralesFragment.OnFragmentInteractionListener,
        QuestionsEducacion.OnFragmentInteractionListener,
        QuestionsContextElectro.OnFragmentInteractionListener,
        QuestionsAlimentos.OnFragmentInteractionListener,
        ContextoSocialFragment.OnFragmentInteractionListener,
        SuccededFragment.OnFragmentInteractionListener,
        QuestionsFragmentTwo.OnFragmentInteractionListener,
        QuestionsDomFragmentTwo.OnFragmentInteractionListener,
        QuestionsDomFragmentThree.OnFragmentInteractionListener,
        RegistroFragment.OnFragmentInteractionListener,
        QuestionsAntecedentes.OnFragmentInteractionListener,
        QuestionsAntecedentesPersonales.OnFragmentInteractionListener,
        QuestionsHistoriaClinica.OnFragmentInteractionListener,
        QuestionsExploracion.OnFragmentInteractionListener,
        SuccededHistoriaFragment.OnFragmentInteractionListener,
        PacientesFragment.OnFragmentInteractionListener,
        HistoriaClinicaFragment.OnFragmentInteractionListener,
        NotasHistoric.OnFragmentInteractionListener,
        DiagnosticoFragment.OnFragmentInteractionListener,
        TarjetaPacienteFragment.OnFragmentInteractionListener,
        VisitasFragment.OnFragmentInteractionListener,
        SuccededFragmentDom.OnFragmentInteractionListener,
        DatosCensado.OnFragmentInteractionListener,
        QuestionCuidador.OnFragmentInteractionListener,
        NotasEnfermeria.OnFragmentInteractionListener,
        NotasHojaDiaria.OnFragmentInteractionListener{

    public String patientID;
    public static ImageView inicio, registros, seguimiento, sincronizacion;
    public static Context appContext;
    public static Boolean notListerners=false;
    public static Chronometer cronometro;

    /*---------------------------------- Objetos de Base de Datos --------------------------------*/
    private SQLiteDatabase db = null;      // Objeto para utilizar la base de datos
    private DataBaseHelper sqliteHelper;   // Objeto para abrir la base de Datos
    private Cursor c = null;
    private JSONObject respuestaJSON;
    private SharedPreferences sharedPreferences;

    HttpURLConnection conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("");
        appContext = getApplicationContext();
        enableStrictMode();
        // Initialize Realm
        //Realm.init(this);

        //Initialize ImageView to set change with onclick
        inicio = findViewById(R.id.imageButton);
        registros = findViewById(R.id.imageButton2);
        seguimiento = findViewById(R.id.pacientesButton);
        sincronizacion = findViewById(R.id.imageButton4);

        cronometro = findViewById(R.id.chronomether);

        sqliteHelper = new DataBaseHelper(this, DataBaseDB.DB_NAME, null, DataBaseDB.VERSION);
        db = sqliteHelper.getWritableDatabase();
        db.close();

        sharedPreferences = SharedPreferences.getInstance();

        //Home Fragment
        InicioFragmentMain fragment = new InicioFragmentMain();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragmentHolder, fragment);
        transaction.commit();

        registros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(notListerners){
                    final Dialog dialog = new Dialog(MainActivity.this);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                    dialog.setCancelable(false);
                    dialog.setContentView(R.layout.layout_alert);

                    TextView txtTitle = (TextView) dialog.findViewById(R.id.lblTitle);
                    txtTitle.setText("ALERTA");

                    TextView txtMessage = (TextView) dialog.findViewById(R.id.lblMessage);
                    txtMessage.setText("Si sales perderas toda la información del usuario hasta el momento");

                    Button btnCancelar = (Button) dialog.findViewById(R.id.btnCancel);
                    btnCancelar.setText("Cancelar");
                    btnCancelar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });

                    Button btnAceptar = (Button) dialog.findViewById(R.id.btnAccept);
                    btnAceptar.setText("Aceptar");
                    btnAceptar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            notListerners = false;
                            stopCronometro();
                            dialog.dismiss();

                            fragmentDomiciliarios();

                            restartImageButtons();
                            registros.setImageDrawable(getResources().getDrawable(R.drawable.nuevo_pink));

                        }
                    });
                    dialog.show();
                }else{
                    Log.e("BLOCK","Bloqueado alv");
                    fragmentDomiciliarios();
                    restartImageButtons();
                    registros.setImageDrawable(getResources().getDrawable(R.drawable.nuevo_pink));
                }
            }
        });

        inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(notListerners){
                    notListerners=false;
                    final Dialog dialog = new Dialog(MainActivity.this);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                    dialog.setCancelable(false);
                    dialog.setContentView(R.layout.layout_alert);

                    TextView txtTitle = (TextView) dialog.findViewById(R.id.lblTitle);
                    txtTitle.setText("ALERTA");

                    TextView txtMessage = (TextView) dialog.findViewById(R.id.lblMessage);
                    txtMessage.setText("Si sales perderas toda la información del usuario hasta el momento");

                    Button btnCancelar = (Button) dialog.findViewById(R.id.btnCancel);
                    btnCancelar.setText("Cancelar");
                    btnCancelar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });

                    Button btnAceptar = (Button) dialog.findViewById(R.id.btnAccept);
                    btnAceptar.setText("Aceptar");
                    btnAceptar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            notListerners = false;
                            dialog.dismiss();

                            InicioFragment fragment = new InicioFragment();
                            FragmentManager manager = getSupportFragmentManager();
                            FragmentTransaction transaction = manager.beginTransaction();
                            transaction.replace(R.id.fragmentHolder, fragment);
                            transaction.commit();



                            restartImageButtons();
                            inicio.setImageDrawable(getResources().getDrawable(R.drawable.inicio_pink));

                        }
                    });
                    dialog.show();
                }else{
                    Log.e("BLOCK","Bloqueado alv");

                    InicioFragment fragment = new InicioFragment();
                    FragmentManager manager = getSupportFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    transaction.replace(R.id.fragmentHolder, fragment);
                    transaction.commit();



                    restartImageButtons();
                    inicio.setImageDrawable(getResources().getDrawable(R.drawable.inicio_pink));
                }


            }
        });


        seguimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(notListerners){
                    notListerners=false;
                    final Dialog dialog = new Dialog(MainActivity.this);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                    dialog.setCancelable(false);
                    dialog.setContentView(R.layout.layout_alert);

                    TextView txtTitle =  dialog.findViewById(R.id.lblTitle);
                    txtTitle.setText("ALERTA");

                    TextView txtMessage = dialog.findViewById(R.id.lblMessage);
                    txtMessage.setText("Si sales perderas toda la información del usuario hasta el momento");

                    Button btnCancelar = dialog.findViewById(R.id.btnCancel);
                    btnCancelar.setText("Cancelar");
                    btnCancelar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });

                    Button btnAceptar = dialog.findViewById(R.id.btnAccept);
                    btnAceptar.setText("Aceptar");
                    btnAceptar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            notListerners = false;
                            stopCronometro();
                            dialog.dismiss();

                            PacientesFragment fragment = new PacientesFragment();
                            FragmentManager manager = getSupportFragmentManager();
                            FragmentTransaction transaction = manager.beginTransaction();
                            transaction.replace(R.id.fragmentHolder, fragment);
                            transaction.commit();

                            restartImageButtons();
                            seguimiento.setImageDrawable(getResources().getDrawable(R.drawable.pacientes_pink));

                        }
                    });
                    dialog.show();
                }else{
                    Log.e("BLOCK","Bloqueado alv");

                    PacientesFragment fragment = new PacientesFragment();
                    FragmentManager manager = getSupportFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    transaction.replace(R.id.fragmentHolder, fragment);
                    transaction.commit();

                    restartImageButtons();
                    seguimiento.setImageDrawable(getResources().getDrawable(R.drawable.pacientes_pink));

                }


            }
        });

    }


    public static void startCronometro(){
        cronometro.setVisibility(View.VISIBLE);
        cronometro.setBase(SystemClock.elapsedRealtime());
        cronometro.start();
    }


    public void stopCronometro(){
        cronometro.stop();
        cronometro.getText().toString();
        sharedPreferences.setStringData("TiempoEncuesta",cronometro.getText().toString());
        Log.e("Tiempo Tardanza", sharedPreferences.getStringData("TiempoEncuesta"));
        cronometro.setVisibility(View.GONE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        super.onOptionsItemSelected(item);

        switch(item.getItemId()){
//            case R.id.phone:
//                Toast.makeText(getBaseContext(), "You selected Phone", Toast.LENGTH_SHORT).show();
//                break;
        }
        return true;

    }


    public void inicio(View v){
        InicioFragment fragment = new InicioFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragmentHolder, fragment);
        transaction.commit();

        restartImageButtons();
        inicio.setImageDrawable(getResources().getDrawable(R.drawable.inicio_pink));


    }

    public void domiciliarios(View v){

        QuestionDomFragment fragment = new QuestionDomFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragmentHolder, fragment);
        transaction.commit();

        startCronometro();

        restartImageButtons();
        registros.setImageDrawable(getResources().getDrawable(R.drawable.nuevo_pink));


    }


    public void activityRegistros(){
        QuestionsFragment fragment = new QuestionsFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragmentHolder, fragment);
        transaction.commit();

    }

    public void candidatoPrograma(){
        QuestionPoblacion fragment = new QuestionPoblacion();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragmentHolder, fragment);
        transaction.commit();

    }


    public void datosCuidador(){
        QuestionCuidador fragment = new QuestionCuidador();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragmentHolder, fragment);
        transaction.commit();

    }


    public void datosCensado(){
        DatosCensado fragment = new DatosCensado();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragmentHolder, fragment);
        transaction.commit();

    }

    public void notasEnfermeria(){
        NotasEnfermeria fragment = new NotasEnfermeria();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragmentHolder, fragment);
        transaction.commit();

    }


    public void questionInterrogatorio(){
        QuestionsHistoriaClinica fragment = new QuestionsHistoriaClinica();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragmentHolder, fragment);
        transaction.commit();

    }

    public void questionFragmentTwo(){
        QuestionsFragmentTwo fragment = new QuestionsFragmentTwo();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragmentHolder, fragment);
        transaction.commit();

    }

    public void domRegistrado(){
        SuccededFragmentDom fragment = new SuccededFragmentDom();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragmentHolder, fragment);
        transaction.commit();

    }

    public void succededClinica(){
        SuccededHistoriaFragment fragment = new SuccededHistoriaFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragmentHolder, fragment);
        transaction.commit();

    }



    public void fragmentDiagnostico(){
        DiagnosticoFragment fragment = new DiagnosticoFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragmentHolder, fragment);
        transaction.commit();

    }

    public void fragmentTarjetaPaciente(){
        TarjetaPacienteFragment fragment = new TarjetaPacienteFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragmentHolder, fragment);
        transaction.commit();

    }

    public void pacientesFragment(){
        SuccededHistoriaFragment fragment = new SuccededHistoriaFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragmentHolder, fragment);
        transaction.commit();

    }

    public void questionDomTwo(){
        QuestionsDomFragmentTwo fragment = new QuestionsDomFragmentTwo();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragmentHolder, fragment);
        transaction.commit();

    }

    public void questionDomThree(){
        QuestionsDomFragmentThree fragment = new QuestionsDomFragmentThree();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragmentHolder, fragment);
        transaction.commit();

    }



    public void questionsAntPersonales(){
        QuestionsAntecedentesPersonales fragment = new QuestionsAntecedentesPersonales();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragmentHolder, fragment);
        transaction.commit();

    }

    public void questionExploracion(){
        startCronometro();
        QuestionsExploracion fragment = new QuestionsExploracion();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragmentHolder, fragment);
        transaction.commit();

    }

    public void visitasFragment(String nameUser){
        VisitasFragment fragment = new VisitasFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragmentHolder, fragment);
        transaction.commit();

    }

    public void notasHojaDiaria(){
        NotasHojaDiaria fragment = new NotasHojaDiaria();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragmentHolder, fragment);
        transaction.commit();

    }


    public void questionAntecedentes(){
        QuestionsAntecedentes fragment = new QuestionsAntecedentes();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragmentHolder, fragment);
        transaction.commit();

    }

    public void historiaClinica(){
        RegistroFragment fragment = new RegistroFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragmentHolder, fragment);
        transaction.commit();

    }


    public void seguimiento(View v){
        PacientesFragment fragment = new PacientesFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragmentHolder, fragment);
        transaction.commit();

        restartImageButtons();
        seguimiento.setImageDrawable(getResources().getDrawable(R.drawable.pacientes_pink));

    }

    public void activitySeguimiento(){
        SeguimientoFragment fragment = new SeguimientoFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragmentHolder, fragment);
        transaction.commit();
    }

    public void paciente(String useruuid){
        PacienteFragment fragment = new PacienteFragment();
        fragment.userID = useruuid;
        patientID = useruuid;
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragmentHolder, fragment);
        transaction.commit();
    }

    public void visita(View v){
        VisitaFragment fragment = new VisitaFragment();
        fragment.userID = patientID;
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragmentHolder, fragment);
        transaction.commit();

    }

    public void sincronizacion(View v){
        SincronizacionFragment fragment = new SincronizacionFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragmentHolder, fragment);
        transaction.commit();

        restartImageButtons();
        sincronizacion.setImageDrawable(getResources().getDrawable(R.drawable.registros_pink));

    }

    public void activitySincronizacion(){
        SincronizacionFragment fragment = new SincronizacionFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragmentHolder, fragment);
        transaction.commit();
    }




    public void fragmentNotasHistoric(){
        NotasHistoric fragment = new NotasHistoric();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragmentHolder, fragment);
        transaction.commit();
    }

    public void historiaClinicaFragment(){
        HistoriaClinicaFragment fragment = new HistoriaClinicaFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragmentHolder, fragment);
        transaction.commit();
    }

    public void fragmentDomiciliarios(){
        startCronometro();

        QuestionDomFragment fragment = new QuestionDomFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragmentHolder, fragment);
        transaction.commit();
    }

    public void fragmentDatosGenerales(){
        DatosGeneralesFragment fragment = new DatosGeneralesFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragmentHolder, fragment);
        transaction.commit();
    }

    public void fragmentContextElectro(){
        QuestionsContextElectro fragment = new QuestionsContextElectro();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragmentHolder, fragment);
        transaction.commit();
    }

    public void fragmentQuestionsEsc(){
        QuestionsEducacion fragment = new QuestionsEducacion();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragmentHolder, fragment);
        transaction.commit();
    }


    public void fragmentQuestionsAlimentos(){
        QuestionsAlimentos fragment = new QuestionsAlimentos();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragmentHolder, fragment);
        transaction.commit();
    }

    public void fragmentSucceded(){
        SuccededFragment fragment = new SuccededFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragmentHolder, fragment);
        transaction.commit();
    }


    public void fragmentQuestionsContexto(){
        ContextoSocialFragment fragment = new ContextoSocialFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragmentHolder, fragment);
        transaction.commit();
    }

    public void restartImageButtons(){
        inicio.setImageDrawable(getResources().getDrawable(R.drawable.inicio_black));
        seguimiento.setImageDrawable(getResources().getDrawable(R.drawable.pacientes_black));
        registros.setImageDrawable(getResources().getDrawable(R.drawable.nuevo_black));
        sincronizacion.setImageDrawable(getResources().getDrawable(R.drawable.registros_black));

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void enableStrictMode() {
        StrictMode.setThreadPolicy(
                new StrictMode.ThreadPolicy.Builder()
                        .detectDiskReads()
                        .detectDiskWrites()
                        .detectNetwork()
                        .penaltyLog()
                        .build());
        StrictMode.setVmPolicy(
                new StrictMode.VmPolicy.Builder()
                        .detectLeakedSqlLiteObjects()
                        .penaltyLog()
                        .build());
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        //create a dialog to ask yes no question whether or not the user wants to exit
    }







}
