package mx.digitalcoaster.rzertuche.medicoencasa.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;


import com.google.gson.JsonObject;

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
        //load questions...
        loadQuestions();
        startActivity(new Intent(SplashScreenActivity.this,MainActivity.class));

        progress.setVisibility(View.GONE);


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
                Log.d("rd_data_history",response.body().toString());
                
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



}


