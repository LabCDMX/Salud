package mx.digitalcoaster.rzertuche.medicoencasa.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MedicalService {

    private static Retrofit retrofit;
    MedicalService apiWS;

    public static final String BASE_URL = "http://187.210.47.140:9999";

    public static Retrofit getMedicalApiData(){

        if (retrofit == null)
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        return retrofit;
    }


}
