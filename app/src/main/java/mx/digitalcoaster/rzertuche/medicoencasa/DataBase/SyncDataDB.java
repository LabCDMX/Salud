package mx.digitalcoaster.rzertuche.medicoencasa.DataBase;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;

import mx.digitalcoaster.rzertuche.medicoencasa.R;
import mx.digitalcoaster.rzertuche.medicoencasa.api.ApiInterface;
import mx.digitalcoaster.rzertuche.medicoencasa.api.MedicalService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SyncDataDB {
    private String TAG = SyncDataDB.this.getClass().getSimpleName();
    private SQLiteDatabase  db = null,
                            dbVisitas = null;      // Objeto para utilizar la base de datos

    private DataBaseHelper sqliteHelper;   // Objeto para abrir la base de Datos
    private Cursor  cursorGeneral = null,
                    cursorHistoric = null,
                    cursorVisit;

    private Activity activity;

    ProgressDialog mProgress;


    //COUNTING AND SHOW DATA SYNC
    TextView tvCountGeneralData,
            tvCountHistory,
            tvCountVisit;

    View mViewSyncCount;

    public void addBaseActivity(Activity activity,View view ){
        this.activity = activity;
        this.mViewSyncCount = view;
        showCountDataSync();

        mProgress = new ProgressDialog(activity);
        mProgress.setMessage(" Espere un momento..");
        mProgress.setTitle(" Syncronizando ");

    }

    public void initSyncVisitas(){

        dbVisitas = activity.openOrCreateDatabase(DataBaseDB.DB_NAME, Context.MODE_PRIVATE ,null);

        try{
            cursorVisit = dbVisitas.rawQuery("SELECT * FROM " + DataBaseDB.TABLE_NAME_PACIENTES_SEGUIMIENTO + " WHERE " + DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_SYNC + " = 'NOT_SYNC'" , null);
            Log.d(TAG,"count visitas::: " + cursorVisit.getCount());


            String id;
            if(cursorVisit.moveToFirst()){
                Cursor cursorId = dbVisitas.rawQuery("SELECT " + DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_ID + " FROM " + DataBaseDB.TABLE_NAME_PACIENTES_SINCRONIZAR_HISTORIC + " WHERE " + DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_EXPEDIENTE + " = ? ", new String[]{cursorVisit.getString(7)});

                Log.d(TAG,"CURSOR_ID for EXPEDIENTE:: " + DatabaseUtils.dumpCursorToString(cursorId) + " \n PARA EXPEDIENTE :: " + cursorVisit.getString(7));
                cursorId.moveToFirst();
                id = cursorId.getString(0);

                ContentValues cValues = new ContentValues();
                cValues.put(DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_ID,cursorId.moveToFirst());
                dbVisitas.update(DataBaseDB.TABLE_NAME_PACIENTES_SEGUIMIENTO,cValues,null,null);

                Cursor cursorPrintAll = dbVisitas.rawQuery("SELECT * FROM " + DataBaseDB.TABLE_NAME_PACIENTES_SEGUIMIENTO,null);
                Log.d(TAG, "cursor_print_all ::: " + DatabaseUtils.dumpCursorToString(cursorPrintAll));
                cursorPrintAll.close();
                cursorId.close();

                do{
                    sendDataVisitas(
                            cursorVisit.getInt(0),
                            cursorVisit.getString(15),//peso
                            cursorVisit.getString(16),//estatura
                            cursorVisit.getString(20),//talla
                            cursorVisit.getString(21),//pulso
                            cursorVisit.getString(17),//tensión arterial

                            cursorVisit.getString(18),//freciencía cardiaca
                            cursorVisit.getString(19),//frecuencía respiratoria
                            cursorVisit.getString(22),//glucemia
                            cursorVisit.getString(23),//temperatura
                            cursorVisit.getString(9),//notas_enfermeria
                            "notas_medicas...",
                            cursorVisit.getString(3),//diagnostico
                            cursorVisit.getString(5),//seguimiento visitas
                            cursorVisit.getString(6),//no_visita
                            id + ""
                    );

                }while (cursorVisit.moveToNext());

            }else {
                Toast.makeText(activity,"No hay visitas a sincronizar",Toast.LENGTH_LONG).show();
                //DataBaseDB.TABLE_NAME_PACIENTES_VISITAS;

            }

        }catch (Exception e){
            Log.e(TAG, "error::" + e);
        }finally {
        }

    }

    public void initSyncGeneralesData(){

        db = activity.openOrCreateDatabase(DataBaseDB.DB_NAME, Context.MODE_PRIVATE ,null);

        try {
            cursorGeneral = db.rawQuery("SELECT * FROM " + DataBaseDB.TABLE_NAME_PACIENTES_SINCRONIZAR, null);

            Log.d(TAG,"count pacientes::: " + cursorGeneral.getCount());

            if (cursorGeneral.moveToFirst()) {
                Log.d(TAG,DatabaseUtils.dumpCursorToString(cursorGeneral));


                do {
                    sendDataGenerales(cursorGeneral.getString(2),cursorGeneral.getString(4),cursorGeneral.getString(5),
                            cursorGeneral.getString(1),cursorGeneral.getString(6),cursorGeneral.getString(7),cursorGeneral.getString(8),
                            cursorGeneral.getString(9),cursorGeneral.getString(10), cursorGeneral.getString(11),cursorGeneral.getString(21),
                            cursorGeneral.getString(22),cursorGeneral.getString(12),cursorGeneral.getString(3), cursorGeneral.getString(13),
                            cursorGeneral.getString(14),cursorGeneral.getString(15),cursorGeneral.getString(17),cursorGeneral.getString(18),
                            cursorGeneral.getString(19), cursorGeneral.getString(0));

                }while (cursorGeneral.moveToNext());
            } else {
                Toast.makeText(activity,"No hay pacientes a sincronizar",Toast.LENGTH_LONG).show();
            }
        } catch (Exception ex) {
            Log.e(TAG,"error " +  ex.toString());
        } finally {
            cursorGeneral.close();
            db.close();

        }
    }

    public void initSyncHistoric(){

        db = activity.openOrCreateDatabase(DataBaseDB.DB_NAME, Context.MODE_PRIVATE ,null);

        try {

            cursorHistoric = db.rawQuery("SELECT * FROM " + DataBaseDB.TABLE_NAME_PACIENTES_SINCRONIZAR_HISTORIC + " WHERE " + DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_SEND_SUCCESS + " == 'NOT_SYNC' ", null);
            Log.d(TAG,"count historic::: " + cursorHistoric.getCount());

            if (cursorHistoric.moveToFirst()) {
                do {
                    sendDataHistoric(cursorHistoric.getString(53),cursorHistoric.getString(4),cursorHistoric.getString(7),
                            cursorHistoric.getString(8),cursorHistoric.getString(9),cursorHistoric.getString(10),cursorHistoric.getString(11),
                            cursorHistoric.getString(12),cursorHistoric.getString(13),cursorHistoric.getString(14),cursorHistoric.getString(15),cursorHistoric.getString(16),cursorHistoric.getString(48),"",
                            cursorHistoric.getString(24),cursorHistoric.getString(26),cursorHistoric.getString(23),cursorHistoric.getString(28),cursorHistoric.getString(29),cursorHistoric.getString(30),cursorHistoric.getString(31),
                            cursorHistoric.getString(32),cursorHistoric.getString(33),cursorHistoric.getString(34),
                            cursorHistoric.getString(35),cursorHistoric.getString(36),cursorHistoric.getString(37),cursorHistoric.getString(38),cursorHistoric.getString(39),cursorHistoric.getString(40),cursorHistoric.getString(41),
                            cursorHistoric.getString(54),cursorHistoric.getString(42),cursorHistoric.getString(43),cursorHistoric.getString(44),
                            cursorHistoric.getString(45),cursorHistoric.getString(46),cursorHistoric.getString(47),cursorHistoric.getString(49),cursorHistoric.getString(50),cursorHistoric.getString(51),cursorHistoric.getString(52));

                }while (cursorHistoric.moveToNext());
            } else {
                Toast.makeText(activity,"No hay historias clinicas a sincronizar",Toast.LENGTH_LONG).show();
                Log.d(TAG,"No existen PACIENTES");
            }
        } catch (Exception ex) {
            Log.e(TAG,"error " +  ex.toString());
        } finally {
            db.close();
            cursorHistoric.close();
        }
    }

    public void sendDataGenerales(final String curp, String a_pa, String a_ma, final String nombre, String fechaNac, String estadoNac, String sexo, final String nac, String estadoRes,
                                  String municipio, String cp, String poblacion, String colonia, String nombreCalle, String edo_civil, String ocupacion, String derecho,
                                  String telFijo, String telCel, String correo, final String _id){

        mProgress.show();

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

                    Log.d( TAG, response.toString());
                    switch (response.code()){
                        case 500:
                            Toast.makeText(activity,"ERROR EN EL SERVIDOR, CONTACTAR A SOPORTE",Toast.LENGTH_SHORT).show();
                            mProgress.dismiss();

                            return;

                        case 200:


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
                                    db.update(DataBaseDB.TABLE_NAME_PACIENTES_SINCRONIZAR_HISTORIC, updates, "_id = " + _id,null);

                                    db.delete(DataBaseDB.TABLE_NAME_PACIENTES_SINCRONIZAR,DataBaseDB.PACIENTES_SINCRONIZAR_CURP + "=? AND "+ DataBaseDB.PACIENTES_SINCRONIZAR_NOMBRE + "=?",new String[]{curp,nombre});


                                }catch(Exception e){
                                    e.printStackTrace();
                                }finally {
                                    cursorGeneral.close();
                                    db.close();

                                    mProgress.dismiss();

                                    showCountDataSync();
                                }
                            }

                            break;
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
                                 String notas_doc, String plan, String impresion_diag, String tratamiento) {

        mProgress.show();

        if(no_expediente == null || no_expediente.equals("") ){
            no_expediente = "";
        }

        if(id == null || id.equals("")){

            mProgress.dismiss();
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
                                mProgress.dismiss();

                                Log.e(TAG, "RESPONSE ALL...: " + response.toString());
                                Toast.makeText(activity, "Problemas de conexión..",Toast.LENGTH_SHORT).show();
                                return;

                            case 200:
                                Log.d(TAG,"RESPONSE: " + response.body().toString());

                                if (response.body().get("success").getAsBoolean()) {

                                    //String  = user.get("id").getAsString();


                                    db = activity.openOrCreateDatabase(DataBaseDB.DB_NAME, Context.MODE_PRIVATE, null);

                                    try {

                                        ContentValues updates = new ContentValues();
                                        updates.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_SEND_SUCCESS, "OK_SYNC");
                                        db.update(DataBaseDB.TABLE_NAME_PACIENTES_SINCRONIZAR_HISTORIC, updates, DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_ID+ "='"+id+"'", null);

                                    }catch(Exception e){
                                        e.printStackTrace();
                                    }finally {
                                        db.close();

                                    }
                                }
                                //ContentValues updates = new ContentValues();
                                //updates.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_SEND_SUCCESS, "SYNC_OK");
                                //db.update(DataBaseDB.TABLE_NAME_PACIENTES_SINCRONIZAR_HISTORIC, updates, , null);
                                mProgress.dismiss();

                                break;

                                default:
                                    mProgress.dismiss();
                                    break;

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
            showCountDataSync();
        }


    }

    public void sendDataVisitas(final int _id,
                                String peso,
                                String estatura,
                                String talla,
                                String pulso,
                                String tensionsArterial,
                                String frecuenciaCar,
                                String frecuenciaRes,
                                String glucemia,
                                String temperatura,
                                String notasEnf,
                                String notasDoc,
                                String diagnostico,
                                String siguienteVisita,
                                String no_visita,
                                String id_usuario){

        mProgress.show();

        final ContentValues updateVisit = new ContentValues();
        updateVisit.put(DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_SYNC, "OK_SYNC");


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
        jsonParams.addProperty("notas_doc",notasDoc);
        jsonParams.addProperty("51",diagnostico);
        jsonParams.addProperty("69",siguienteVisita);
        jsonParams.addProperty("no_visita",no_visita);
        jsonParams.addProperty("id_usuario",id_usuario);

        jsonParams.toString().replace("null"," \" \" ");
        Log.d(TAG,"JSON_:::"+jsonParams.toString());
        try {
            ApiInterface postDataVisits = MedicalService.getMedicalApiData().create(ApiInterface.class);
            postDataVisits.sendPacienteResultados(jsonParams).enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                    Log.d( TAG, "RESPONSE ::: " + response.toString());
                    dbVisitas = activity.openOrCreateDatabase(DataBaseDB.DB_NAME, Context.MODE_PRIVATE ,null);
                    dbVisitas.update(DataBaseDB.TABLE_NAME_PACIENTES_SEGUIMIENTO, updateVisit,"_id = " + _id +" ",null);

                    //db.update(DataBaseDB.TABLE_NAME_PACIENTES_VISITAS,updateVisit,DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_ID)


                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                    Log.e(TAG,"POST_VISITAS ERROR ::: " + t);

                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            cursorVisit.close();
            dbVisitas.close();
            mProgress.dismiss();
        }
    }

    Cursor c_count = null;
    public int getCountData(String datosCount){

        db = activity.openOrCreateDatabase(DataBaseDB.DB_NAME, Context.MODE_PRIVATE ,null);

        Cursor c_pacientes, c_historic_, c_seguimiento;

        if(datosCount.equals("Generales")){

            c_count = db.rawQuery("SELECT * FROM " + DataBaseDB.TABLE_NAME_PACIENTES_SINCRONIZAR, null);

            c_pacientes = db.query(DataBaseDB.TABLE_NAME_PACIENTES_SINCRONIZAR,null,null,null,null,null,null);
            Log.d("::: pacientes", DatabaseUtils.dumpCursorToString(c_pacientes));
            c_pacientes.close();

        }else if(datosCount.equals("Historic")){
            c_count = db.rawQuery("SELECT * FROM " + DataBaseDB.TABLE_NAME_PACIENTES_SINCRONIZAR_HISTORIC + " WHERE " + DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_SEND_SUCCESS + " = 'NOT_SYNC' " , null);

            c_historic_ = db.query(DataBaseDB.TABLE_NAME_PACIENTES_SINCRONIZAR_HISTORIC,null,null,null,null,null,null);
            Log.d("::: historic", DatabaseUtils.dumpCursorToString(c_historic_));
            c_historic_.close();

            if (c_count.moveToFirst())
                do{
                    Log.d(TAG,"DATA_SUCCESS::: " + c_count.getString(56));
                }while (c_count.moveToNext());

        }else if(datosCount.equals("Visitas")){
            c_count = db.rawQuery("SELECT * FROM " + DataBaseDB.TABLE_NAME_PACIENTES_SEGUIMIENTO + " WHERE " + DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_SYNC + " = 'NOT_SYNC' ", null);

            c_seguimiento = db.query(DataBaseDB.TABLE_NAME_PACIENTES_SEGUIMIENTO,null,null,null,null,null,null);;
            Log.d("::: seguimiento", DatabaseUtils.dumpCursorToString(c_seguimiento));
            c_seguimiento.close();

        }
        db.close();


        try {
            if (c_count.moveToFirst()) {
                return c_count.getCount();
            } else {
                return 0;
            }
        } catch (Exception ex) {
            Log.e(TAG, "ERROR: " + ex.toString());

        } finally {
            c_count.close();
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
