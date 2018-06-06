package mx.digitalcoaster.rzertuche.medicoencasa;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity implements
        InicioFragment.OnFragmentInteractionListener,
        SeguimientoFragment.OnFragmentInteractionListener,
        QuestionsFragment.OnFragmentInteractionListener,
        SincronizacionFragment.OnFragmentInteractionListener,
        PacienteFragment.OnFragmentInteractionListener,
        VisitaFragment.OnFragmentInteractionListener,
        InicioFragmentMain.OnFragmentInteractionListener,
        NewPatientFragment.OnFragmentInteractionListener,
        QuestionDomFragment.OnFragmentInteractionListener{

    public String patientID;
    public static ImageView inicio, registros, seguimiento, sincronizacion;
    public static Context appContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("");
        appContext = getApplicationContext();

        // Initialize Realm
        Realm.init(this);


        //Initialize ImageView to set change with onclick
        inicio = (ImageView) findViewById(R.id.imageButton);
        registros = (ImageView) findViewById(R.id.imageButton2);
        seguimiento = (ImageView) findViewById(R.id.imageButton3);
        sincronizacion = (ImageView) findViewById(R.id.imageButton4);





        //Home Fragment
        InicioFragmentMain fragment = new InicioFragmentMain();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragmentHolder, fragment);
        transaction.commit();

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

    public void registros(View v){
        NewPatientFragment fragment = new NewPatientFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragmentHolder, fragment);
        transaction.commit();

        restartImageButtons();
        registros.setImageDrawable(getResources().getDrawable(R.drawable.nuevo_pink));
    }

    public void activityNewPatient(){
        NewPatientFragment fragment = new NewPatientFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragmentHolder, fragment);
        transaction.commit();

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


    public void seguimiento(View v){
        SeguimientoFragment fragment = new SeguimientoFragment();
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

    public void fragmentDomiciliarios(){
        QuestionDomFragment fragment = new QuestionDomFragment();
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
}
