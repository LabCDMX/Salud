package mx.digitalcoaster.rzertuche.medicoencasa.Activitys;

import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import mx.digitalcoaster.rzertuche.medicoencasa.DataBase.DataBaseDB;
import mx.digitalcoaster.rzertuche.medicoencasa.DataBase.DataBaseHelper;
import mx.digitalcoaster.rzertuche.medicoencasa.R;
import mx.digitalcoaster.rzertuche.medicoencasa.api.ApiInterface;
import mx.digitalcoaster.rzertuche.medicoencasa.api.MedicalService;

//RETROFIT....
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashScreenActivity extends AppCompatActivity {

    ProgressBar progress;
    DataBaseHelper dataBaseDBH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        progress = findViewById(R.id.pb_loading);

        //ADD QUESTIONS...
        loadQuestions();

        //loadMedicalHistory();
        loadMedicalHistory();
    }

    public void loadQuestions(){

        dataBaseDBH = new DataBaseHelper(this, DataBaseDB.DB_NAME, null, DataBaseDB.VERSION);
        ApiInterface getDataQuestions = MedicalService.getMedicalApiData().create(ApiInterface.class);
        getDataQuestions.loadPreguntas().enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                //Log.d("response_data_questions","body..." + response.body());
                dataBaseDBH.addDataDB(response.body());
                progress.setVisibility(View.GONE);
                //startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d("response_data_questions",t.toString());
            }
        });
    }

    public void loadMedicalHistory(){
        ApiInterface getMedicalHistory = MedicalService.getMedicalApiData().create(ApiInterface.class);
        getMedicalHistory.loadPaciente().enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.d("response_data_history",response.body().toString());

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d("response_data_history",t.toString());

            }
        });
    }



}


