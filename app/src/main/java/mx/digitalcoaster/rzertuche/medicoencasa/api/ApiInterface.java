package mx.digitalcoaster.rzertuche.medicoencasa.api;


import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiInterface {

    //GET...
    @GET("/api/admin/api/preguntas")
    Call<JsonObject> loadPreguntas();
    //DATA
    @GET("/users/defunkt")
    Call<JsonObject> loadGit();

    @GET("/api/admin/api/paciente/")
    Call<JsonObject> loadPaciente();

    @GET("/api/admin/api/localidades")
    Call<JsonObject> loadLocalidades();

    @GET("/api/admin/api/codigospostales")
    Call<JsonObject> loadCodigosPostales();

    @GET("/api/admin/api/categorias")
    Call<JsonObject> loadCategorias();

    //POST!!!....
    @POST("/api/admin/api/paciente")
    Call<JsonObject> sendPaciente(@Body JsonObject paciente);

    @POST("/api/admin/api/pacienteResultados")
    Call<JsonObject> sendPacienteResultados(@Body JsonObject pacienteResultados);


}
