package mx.digitalcoaster.rzertuche.medicoencasa.Activitys;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;


import com.google.gson.JsonObject;

import mx.digitalcoaster.rzertuche.medicoencasa.DataBase.DataBaseDB;
import mx.digitalcoaster.rzertuche.medicoencasa.DataBase.DataBaseHelper;
import mx.digitalcoaster.rzertuche.medicoencasa.R;
import mx.digitalcoaster.rzertuche.medicoencasa.Utils.IsOnline;
import mx.digitalcoaster.rzertuche.medicoencasa.api.ApiInterface;
import mx.digitalcoaster.rzertuche.medicoencasa.api.MedicalService;

//RETROFIT....
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashScreenActivity extends AppCompatActivity {

    ProgressBar progress;
    DataBaseHelper dataBaseDBH;
    IsOnline online;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        progress = findViewById(R.id.pb_loading);
        online = new IsOnline();
        //load questions...

        if (online.validateOnline(getApplicationContext()))
            loadQuestions();
        else
            startActivity(new Intent(SplashScreenActivity.this,MainActivity.class));

        progress.setVisibility(View.GONE);

        //loadTableTest();


    }

    public void loadQuestions(){

        dataBaseDBH = new DataBaseHelper(this, DataBaseDB.DB_NAME, null, DataBaseDB.VERSION);
        ApiInterface getDataQuestions = MedicalService.getMedicalApiData().create(ApiInterface.class);
        getDataQuestions.loadPreguntas().enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                //Log.d("response_data_questions","body..." + response.body());


                //dataBaseDBH.addQuestionsDB(response.body());

                progress.setVisibility(View.GONE);
                //startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
                loadMedicalHistory();
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d("rd_questions",t.toString());
            }
        });
    }

    public void loadMedicalHistory(){
        ApiInterface getMedicalHistory = MedicalService.getMedicalApiData().create(ApiInterface.class);
        getMedicalHistory.loadPaciente().enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.d("rd_data_history",response.toString());
                
                loadCodePostal();
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d("rd_data_history",t.toString());

            }
        });
    }

    public void loadCodePostal(){

        dataBaseDBH = new DataBaseHelper(this, DataBaseDB.DB_NAME, null, DataBaseDB.VERSION);

        ApiInterface getMedicalHistory = MedicalService.getMedicalApiData().create(ApiInterface.class);
        getMedicalHistory.loadCodigosPostales().enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                //Add cp_data...


                //dataBaseDBH.addCodePostalDB(response.body());


                startActivity(new Intent(SplashScreenActivity.this,MainActivity.class));

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d("rd_cp",t.toString());

            }
        });

    }


    /*public void loadTableTest(){
        //TODO("SE TIENE QUE REMOVER EL CÓDIGO PARA PRODUCCIÓN!!")
        SQLiteDatabase db = this.openOrCreateDatabase(DataBaseDB.DB_NAME,Context.MODE_PRIVATE,null);

        ContentValues addTestPaciente = new ContentValues();

        addTestPaciente.put(DataBaseDB.PACIENTES_SINCRONIZAR_NOMBRE,"UserTest_");
        addTestPaciente.put(DataBaseDB.PACIENTES_SINCRONIZAR_CURP ,"BAJO890612HTNL07_");
        addTestPaciente.put(DataBaseDB.PACIENTES_SINCRONIZAR_CALLE,"CALLE_1_");
        addTestPaciente.put(DataBaseDB.PACIENTES_SINCRONIZAR_AP_PATERNO,"PATERNO_1_");
        addTestPaciente.put(DataBaseDB.PACIENTES_SINCRONIZAR_AP_MATERNO ,"MATERNO_TEST_");
        addTestPaciente.put(DataBaseDB.PACIENTES_SINCRONIZAR_FECHA_NACIMIENTO,"10/05/");
        addTestPaciente.put(DataBaseDB.PACIENTES_SINCRONIZAR_SEXO,"SEXO");
        addTestPaciente.put(DataBaseDB.PACIENTES_SINCRONIZAR_NACIONALIDAD,"NACIONALIDAD_");
        addTestPaciente.put(DataBaseDB.PACIENTES_SINCRONIZAR_ESTADO,"ESTADO_TEST");
        addTestPaciente.put(DataBaseDB.PACIENTES_SINCRONIZAR_MUNICIPIO,"MUNICIPIO_TEST_");
        addTestPaciente.put(DataBaseDB.PACIENTES_SINCRONIZAR_COLONIA,"COLONIA_TEST");
        addTestPaciente.put(DataBaseDB.PACIENTES_SINCRONIZAR_ESTADO_CIVIL,"CIVIL_TEST");
        addTestPaciente.put(DataBaseDB.PACIENTES_SINCRONIZAR_OCUPACION,"OCUPACION_TEST");
        addTestPaciente.put(DataBaseDB.PACIENTES_SINCRONIZAR_DERECHO ,"DERECHO_TEST");
        addTestPaciente.put(DataBaseDB.PACIENTES_SINCRONIZAR_FOLIO_DERECHO,"FOLIO_DERECHO");
        addTestPaciente.put(DataBaseDB.PACIENTES_SINCRONIZAR_TEL_FIJO,"TEL_FIJO_TEST");
        addTestPaciente.put(DataBaseDB.PACIENTES_SINCRONIZAR_CEL,"CEL_TEST_");
        addTestPaciente.put(DataBaseDB.PACIENTES_SINCRONIZAR_EMAIL,"TEST@HOT.COM_");
        addTestPaciente.put(DataBaseDB.PACIENTES_SINCRONIZAR_CREATED_BY,"CREATE....TEST_");
        addTestPaciente.put(DataBaseDB.PACIENTES_SINCRONIZAR_CODIGO,"CODIGO_TEST");
        addTestPaciente.put(DataBaseDB.PACIENTES_SINCRONIZAR_POBLACION,"POBLACIÓN..._TEST");
        addTestPaciente.put(DataBaseDB.PACIENTES_SINCRONIZAR_TIEMPO_ENCUESTA,"10_HRS_test");
        addTestPaciente.put(DataBaseDB.PACIENTES_SINCRONIZAR_EDAD,"23");

        db.insert(DataBaseDB.TABLE_NAME_PACIENTES_SINCRONIZAR,null,addTestPaciente);


        db.close();
    }*/

}


