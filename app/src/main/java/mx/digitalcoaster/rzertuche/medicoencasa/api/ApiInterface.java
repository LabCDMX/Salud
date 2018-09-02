package mx.digitalcoaster.rzertuche.medicoencasa.api;


import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    @GET("/api/admin/api/preguntas")
    Call<JSONObject> loadPreguntas();

    @GET("/api/admin/api/paciente/")
    Call<JSONObject> loadPaciente();

    @GET("/api/admin/api/localidades")
    Call<JSONObject> loadLocalidades();

    @GET("/api/admin/api/codigospostales")
    Call<JSONObject> loadCodigosPostales();

    @GET("/api/admin/api/categorias")
    Call<JSONObject> loadCategorias();

    @POST("/api/admin/api/paciente")
    Call<JSONObject> sendPaciente(@Body JSONObject Paciente);

}
