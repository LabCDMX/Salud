package mx.digitalcoaster.rzertuche.medicoencasa.api;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MedicalService {

    private static Retrofit retrofit;

    public static final String BASE_URL = "http://187.210.47.140:9999";
    //https://medico.digitalcoaster.mx
    //http://187.210.47.140:9999
    public static final String BASE_URL_BETA = "https://api.github.com";
    public static Retrofit getMedicalApiData(){

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(300, TimeUnit.SECONDS)
                .writeTimeout(105, TimeUnit.SECONDS)
                .build();


        //if (retrofit == null)
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();

        return retrofit;
    }


}
