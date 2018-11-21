package mx.digitalcoaster.rzertuche.medicoencasa.DataBase;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.UnicodeSetSpanner;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;

import org.json.JSONException;

import java.text.ParseException;

import mx.digitalcoaster.rzertuche.medicoencasa.R;
import mx.digitalcoaster.rzertuche.medicoencasa.api.ApiInterface;
import mx.digitalcoaster.rzertuche.medicoencasa.api.MedicalService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SyncDataDB {
    private String TAG = SyncDataDB.this.getClass().getSimpleName();
    private SQLiteDatabase db = null;      // Objeto para utilizar la base de datos
    private DataBaseHelper sqliteHelper;   // Objeto para abrir la base de Datos
    private Cursor c = null;

    private Activity activity;

    ProgressDialog mProgressPaciente;
    ProgressDialog progHistorycSync;
    ProgressDialog progressVisitas;


    //COUNTING AND SHOW DATA SYNC
    TextView tvCountGeneralData,
            tvCountHistory,
            tvCountVisit;

    View mViewSyncCount;

    public void addBaseActivity(Activity activity,View view ){
        this.activity = activity;
        this.mViewSyncCount = view;
        showCountDataSync();
    }

    public void initSyncVisitas(){

        db = activity.openOrCreateDatabase(DataBaseDB.DB_NAME, Context.MODE_PRIVATE ,null);

        try{
            c = db.rawQuery("SELECT * FROM " + DataBaseDB.TABLE_NAME_PACIENTES_VISITAS, null);
            Log.d(TAG,"count visitas::: " + c.getCount());

            if(c.moveToFirst()){

                do{
                    sendDataVisitas(
                            c.getString(11),
                            c.getString(12),
                            c.getString(16),
                            c.getString(17),
                            c.getString(13),
                            c.getString(14),
                            c.getString(15),
                            c.getString(18),
                            c.getString(19),
                            c.getString(20),
                            c.getString(43),
                            c.getString(7),
                            c.getString(47),
                            "siguiente_visita"
                    );
                    //11,12,16,17,
                      //      13,14,15,18,
                        //    19,20,43,7,47,"siguiente_visita"

                }while (c.moveToNext());

                //-------------------------- Obtener información del cliente ---------------------
                /*try {
                    c = db.rawQuery("SELECT * FROM " + DataBaseDB.TABLE_NAME_PACIENTES_SEGUIMIENTO + " WHERE " +
                            DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_NOMBRE + " ='"+nombrePatient+"' AND "+
                            DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_NUMERO+ " = '"+numeroVisita+"'", null);


                    if (c.moveToFirst()) {


                        ((TextView) view.findViewById(R.id.textViewPeso)).setText(c.getString(15));
                        ((TextView) view.findViewById(R.id.textViewEstatura)).setText(c.getString(16));
                        ((TextView) view.findViewById(R.id.textViewTalla)).setText(c.getString(20));
                        ((TextView) view.findViewById(R.id.textViewPulso)).setText(c.getString(21));
                        ((TextView) view.findViewById(R.id.textViewTensionArterial)).setText(c.getString(17));
                        ((TextView) view.findViewById(R.id.textViewFrecuenciaCar)).setText(c.getString(18));
                        ((TextView) view.findViewById(R.id.textViewFrecuenciaRes)).setText(c.getString(19));
                        ((TextView) view.findViewById(R.id.textViewGlucemia)).setText(c.getString(22));
                        ((TextView) view.findViewById(R.id.textViewTemperatura)).setText(c.getString(23));
                        notasEnf.setText(c.getString(9));
                        notasDoc.setText(c.getString(10));
                        ((TextView) view.findViewById(R.id.etDiagnostico)).setText(c.getString(3));
                        ((TextView) view.findViewById(R.id.etTratamiento)).setText(c.getString(4));
                        ((TextView) view.findViewById(R.id.edSiguienteVisita)).setText(c.getString(5));


                    } else {
                        System.out.println("No existe información del cliente");
                    }
                } catch (Exception ex) {
                    Log.d("Visitas","Exception: " + ex);
                }finally {
                    c.close();
                    db.close();
                }*/
            }else {
                Toast.makeText(activity,"No hay visitas a sincronizar",Toast.LENGTH_LONG).show();
                //DataBaseDB.TABLE_NAME_PACIENTES_VISITAS;

            }

        }catch (Exception e){
            Log.e(TAG, "error::" + e);
        }finally {
            db.close();
        }

    }

    public void initSyncGeneralesData(){

        db = activity.openOrCreateDatabase(DataBaseDB.DB_NAME, Context.MODE_PRIVATE ,null);

        try {
            c = db.rawQuery("SELECT * FROM " + DataBaseDB.TABLE_NAME_PACIENTES_SINCRONIZAR, null);

            Log.d(TAG,"count pacientes::: " + c.getCount());

            if (c.moveToFirst()) {

                do {
                    sendDataGenerales(c.getString(2),c.getString(4),c.getString(5),c.getString(1),c.getString(6),c.getString(7),c.getString(8),
                            c.getString(9),c.getString(10), c.getString(11),c.getString(21),c.getString(22),c.getString(12),c.getString(3),
                            c.getString(13), c.getString(14),c.getString(15),c.getString(17),c.getString(18),c.getString(19));

                }while (c.moveToNext());
            } else {
                Toast.makeText(activity,"No hay pacientes a sincronizar",Toast.LENGTH_LONG).show();
            }
        } catch (Exception ex) {
            Log.e(TAG,"error " +  ex.toString());
        } finally {
            c.close();
            db.close();

        }
    }

    public void initSyncHistoric(){

        db = activity.openOrCreateDatabase(DataBaseDB.DB_NAME, Context.MODE_PRIVATE ,null);

        try {

            c = db.rawQuery("SELECT * FROM " + DataBaseDB.TABLE_NAME_PACIENTES_SINCRONIZAR_HISTORIC + " WHERE " + DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_SEND_SUCCESS + " == 'NOT_SYNC' ", null);
            Log.d(TAG,"count historic::: " + c.getCount());

            if (c.moveToFirst()) {

                do {
                    sendDataHistoric(c.getString(53),c.getString(4),c.getString(7),c.getString(8),c.getString(9),c.getString(10),c.getString(11),
                            c.getString(12),c.getString(13), c.getString(14),c.getString(15),c.getString(16),c.getString(48),"",
                            c.getString(24), c.getString(26),c.getString(23),c.getString(28),c.getString(29),c.getString(30),c.getString(31),c.getString(32),c.getString(33),c.getString(34),
                            c.getString(35),c.getString(36),c.getString(37),c.getString(38),c.getString(39),c.getString(40),c.getString(41),c.getString(54),c.getString(42),c.getString(43),c.getString(44),
                            c.getString(45),c.getString(46),c.getString(47),c.getString(49),c.getString(50),c.getString(51),c.getString(52));

                }while (c.moveToNext());
            } else {
                Toast.makeText(activity,"No hay pacientes a sincronizar",Toast.LENGTH_LONG).show();
                Log.d(TAG,"No existen PACIENTES");
            }
        } catch (Exception ex) {
            Log.e(TAG,"error " +  ex.toString());
        } finally {
            db.close();
            c.close();
        }
    }

    public void sendDataGenerales(final String curp, String a_pa, String a_ma, final String nombre, String fechaNac, String estadoNac, String sexo, String nac, String estadoRes,
                                  String municipio, String cp, String poblacion, String colonia, String nombreCalle, String edo_civil, String ocupacion, String derecho,
                                  String telFijo, String telCel, String correo) throws JSONException, ParseException {
        mProgressPaciente = ProgressDialog.show(activity, "Syncronizando",
                "Espere un momento..", true);

        if(sexo.equals("Masculino") || sexo.equals("HOMBRE")){
            sexo = String.valueOf(0);
        } else {
            sexo = String.valueOf(1);
        }

        JsonObject jsonParams = new JsonObject();
        jsonParams.addProperty("curp",curp);
        jsonParams.addProperty("apellidoPaterno",a_pa);
        jsonParams.addProperty("apellidoMaterno",a_ma);
        jsonParams.addProperty("nombre",nombre);
        jsonParams.addProperty("fechadeNacimiento",fechaNac);
        jsonParams.addProperty("estadodeNacimiento",estadoNac);
        jsonParams.addProperty("sexo",sexo);
        jsonParams.addProperty("nacionalidadOrigen",nac);
        jsonParams.addProperty("estadoResidencia",estadoRes);
        jsonParams.addProperty("municipio",municipio);
        jsonParams.addProperty("cp",cp);
        jsonParams.addProperty("pob_vul",poblacion);
        jsonParams.addProperty("colonia",colonia);
        jsonParams.addProperty("nombreCalle",nombreCalle);
        jsonParams.addProperty("estadoCivil",edo_civil);
        jsonParams.addProperty("ocupacion",ocupacion);
        jsonParams.addProperty("derechoHabiencia",derecho);
        jsonParams.addProperty("telFijo",telFijo);
        jsonParams.addProperty("telCelular",telCel);
        jsonParams.addProperty("correoElectronico",correo);
        jsonParams.addProperty("folioDerechoHabiencia","0");
        jsonParams.addProperty("createdBy","0");

        //entity = new StringEntity(json, "UTF-8");
        Log.d("JSON EMV", jsonParams.toString());

        //connectPostWithCookies("https://medico.digitalcoaster.mx/api/admin/api/paciente",jsonParams.toString());


        try {

            ApiInterface getDataQuestions = MedicalService.getMedicalApiData().create(ApiInterface.class);
            getDataQuestions.sendPaciente(jsonParams).enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                    Log.d(TAG, "https://medico.digitalcoaster.mx/api/admin/api/paciente");
                    Log.d( TAG, response.body().toString());

                    JsonObject responsePaciente;
                    responsePaciente = response.body();
                    Boolean isSucceded = responsePaciente.get("success").getAsBoolean();


                    if (isSucceded) {

                        String idUser = responsePaciente.get("user").getAsString();
                        //String  = user.get("id").getAsString();

                        Log.e(TAG, " // IdUser:: " + idUser + " // curp:: " + curp + " //nombre:: " + nombre);

                        db = activity.openOrCreateDatabase(DataBaseDB.DB_NAME, Context.MODE_PRIVATE, null);

                        try {

                            ContentValues updates = new ContentValues();
                            updates.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_ID, idUser);

                            updates.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_CURP, curp);

                            db.update(DataBaseDB.TABLE_NAME_PACIENTES_SINCRONIZAR_HISTORIC, updates, DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_CURP+ "='"+curp+"'", null);
                            db.delete(DataBaseDB.TABLE_NAME_PACIENTES_SINCRONIZAR,DataBaseDB.PACIENTES_SINCRONIZAR_CURP + "=? AND "+ DataBaseDB.PACIENTES_SINCRONIZAR_NOMBRE + "=?",new String[]{curp,nombre});

                        }catch(Exception e){
                            e.printStackTrace();
                        }finally {
                            db.close();
                            mProgressPaciente.dismiss();
                            showCountDataSync();
                        }
                    }
                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                    t.printStackTrace();
                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void sendDataHistoric(final String id, String no_expediente, String hemo, final String peso, String estatura, String tension, String frecuencia_car, String frecuencia_resp,
                                 String talla, String pulso, String glucemia, String temp, String notas_enf, String ant_personales, String ant_patologicos, String ant_obstericos, String padecimiento_actual,
                                 String sistemas_generales, String respiratorio, String cardio, String digestivo, String urinario, String repro, String hemolin, String endo, String s_nervioso, String esqueletico, String piel,
                                 String habitus, String cabeza, String cuello, final String torax, String abdomen, String gine, String extremidades, String c_vertebral, String neuro, String genitales,
                                 String notas_doc, String plan, String impresion_diag, String tratamiento) throws JSONException, ParseException {

        progHistorycSync = ProgressDialog.show(activity, "Syncronizando",
                "Espere un momento..", true);

        if(no_expediente == null || no_expediente.equals("") ){
            no_expediente = "";
        }

        if(id == null || id.equals("")){

            progHistorycSync.dismiss();
            Toast.makeText(activity,"SYNCRONIZAR PRIMERO GENERALES",Toast.LENGTH_SHORT).show();
            return;
        }


        JsonObject jsonParams = new JsonObject();
        jsonParams.addProperty("id_usuario",id);
        jsonParams.addProperty("no_expediente",no_expediente);
        jsonParams.addProperty("6",hemo);
        jsonParams.addProperty("7",peso);
        jsonParams.addProperty("8",estatura);
        jsonParams.addProperty("tensión",tension);
        jsonParams.addProperty("10",frecuencia_car);
        jsonParams.addProperty("11",frecuencia_resp);
        jsonParams.addProperty("12",talla);
        jsonParams.addProperty("13",pulso);
        jsonParams.addProperty("14",glucemia);
        jsonParams.addProperty("15",temp);
        jsonParams.addProperty("notas_enf",notas_enf);
        jsonParams.addProperty("ant_personales",ant_personales);
        jsonParams.addProperty("ant_patologicos",ant_patologicos);
        jsonParams.addProperty("ant_obstericos",ant_obstericos);
        jsonParams.addProperty("19",padecimiento_actual);
        jsonParams.addProperty("27",sistemas_generales);
        jsonParams.addProperty("28",respiratorio);
        jsonParams.addProperty("29",cardio);
        jsonParams.addProperty("30",digestivo);
        jsonParams.addProperty("32",urinario);
        jsonParams.addProperty("33",endo);
        jsonParams.addProperty("34",hemolin);
        jsonParams.addProperty("36",s_nervioso);
        jsonParams.addProperty("37",esqueletico);
        jsonParams.addProperty("38",piel);
        jsonParams.addProperty("39",habitus);
        jsonParams.addProperty("40",cabeza);
        jsonParams.addProperty("41",cuello);
        jsonParams.addProperty("42",torax);
        jsonParams.addProperty("43",abdomen);
        jsonParams.addProperty("44",gine);
        jsonParams.addProperty("45",extremidades);
        jsonParams.addProperty("46",c_vertebral);
        jsonParams.addProperty("neuro",neuro);
        jsonParams.addProperty("48",genitales);
        jsonParams.addProperty("49",notas_doc);
        jsonParams.addProperty("plan",plan);
        jsonParams.addProperty("51",impresion_diag);
        jsonParams.addProperty("52",tratamiento);
        jsonParams.addProperty("no_visita","1");


        Log.d(TAG, "SEND DATA HISTORIC : " + jsonParams.toString().replace("null"," \" \" "));

        try {

            ApiInterface getDataQuestions = MedicalService.getMedicalApiData().create(ApiInterface.class);
            getDataQuestions.sendPacienteResultados(jsonParams).enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    Log.e(TAG, "RESPONSE ALL...: " + response.toString());


                    switch (response.code()){
                        case 500:
                            Log.e(TAG, "RESPONSE ALL...: " + response.toString());
                            Toast.makeText(activity, "Problemas de conexión..",Toast.LENGTH_SHORT).show();
                            break;

                            case 200:
                                Log.d(TAG,"RESPONSE: " + response.body().toString());

                                if (response.body().get("success").getAsBoolean()) {

                                    //String  = user.get("id").getAsString();


                                    db = activity.openOrCreateDatabase(DataBaseDB.DB_NAME, Context.MODE_PRIVATE, null);

                                    try {

                                        ContentValues updates = new ContentValues();
                                        updates.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_SEND_SUCCESS, "SUCCESS_SYNC");
                                        db.update(DataBaseDB.TABLE_NAME_PACIENTES_SINCRONIZAR_HISTORIC, updates, DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_ID+ "='"+id+"'", null);

                                    }catch(Exception e){
                                        e.printStackTrace();
                                        db.close();
                                    }
                                }
                                //ContentValues updates = new ContentValues();
                                //updates.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_SEND_SUCCESS, "SYNC_OK");
                                //db.update(DataBaseDB.TABLE_NAME_PACIENTES_SINCRONIZAR_HISTORIC, updates, , null);

                                break;

                                default:
                                    Log.d(TAG, "hOLA :3");
                    }
                    //hideActivityIndicator();



                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                    t.printStackTrace();
                }
            });

        }catch (Exception e){

            Log.e(TAG," :( ERROR_:: " + e);

        } finally {
            progHistorycSync.dismiss();
            showCountDataSync();
        }


    }

    public void sendDataVisitas(String peso, String estatura, String talla, String pulso, String tensionsArterial, String frecuenciaCar, String frecuenciaRes, String glucemia, String temperatura, String notasEnf, String notasDoc, String diagnostico, String siguienteVisita, String no_visita){

        JsonObject jsonParams = new JsonObject();

        jsonParams.addProperty("7",peso);
        jsonParams.addProperty("8",estatura);
        jsonParams.addProperty("12",talla);
        jsonParams.addProperty("13",pulso);
        jsonParams.addProperty("tensión",tensionsArterial);
        jsonParams.addProperty("10",frecuenciaCar);
        jsonParams.addProperty("11",frecuenciaRes);
        jsonParams.addProperty("14",glucemia);
        jsonParams.addProperty("15",temperatura);
        jsonParams.addProperty("notas_enf",notasEnf);
        jsonParams.addProperty("51",diagnostico);
        jsonParams.addProperty("69",siguienteVisita);
        jsonParams.addProperty("no_visita",no_visita);

        try {
            ApiInterface postDataVisits = MedicalService.getMedicalApiData().create(ApiInterface.class);
            postDataVisits.sendPaciente(jsonParams).enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    Log.d( TAG, response.body().toString());

                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                    Log.e(TAG,"POST_VISITAS ERROR ::: " + t);
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public int getCountData(String datosCount){

        db = activity.openOrCreateDatabase(DataBaseDB.DB_NAME, Context.MODE_PRIVATE ,null);
        String tableName = null;
        if(datosCount.equals("Generales")){
            tableName = DataBaseDB.TABLE_NAME_PACIENTES_SINCRONIZAR;
            c = db.rawQuery("SELECT * FROM " + tableName, null);

        }else if(datosCount.equals("Historic")){
            tableName = DataBaseDB.TABLE_NAME_PACIENTES_SINCRONIZAR_HISTORIC + " WHERE " + DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_SEND_SUCCESS + " == 'NOT_SYNC' ";
            c = db.rawQuery("SELECT * FROM " + tableName , null);
            if (c.moveToFirst())
                do{
                    Log.d(TAG,"DATA_SUCCESS::: " + c.getString(56));
                }while (c.moveToNext());

        }else if(datosCount.equals("Visitas")){
            tableName = DataBaseDB.TABLE_NAME_PACIENTES_VISITAS;
            c = db.rawQuery("SELECT * FROM " + tableName, null);


        }
        try {
            if (c.moveToFirst()) {
                return c.getCount();
            } else {
                return 0;
            }
        } catch (Exception ex) {
            Log.e(TAG, "ERROR: " + ex.toString());

        } finally {
            c.close();
            db.close();
        }

        return 0;
    }

    public void showCountDataSync(){


        tvCountGeneralData = mViewSyncCount.findViewById(R.id.text_count_general_data);
        tvCountHistory = mViewSyncCount.findViewById(R.id.text_count_history);
        tvCountVisit = mViewSyncCount.findViewById(R.id.text_count_visit_medic);

        String countGenerales = String.valueOf(getCountData("Generales"));
        String countHistoric  = String.valueOf(getCountData("Historic"));
        String countVisitas   = String.valueOf(getCountData("Visitas"));

        tvCountGeneralData.setText(countGenerales + "/" + countGenerales);
        tvCountHistory.setText(countHistoric + "/" + countHistoric);
        tvCountVisit.setText(countVisitas + "/" + countVisitas);

    }
}
