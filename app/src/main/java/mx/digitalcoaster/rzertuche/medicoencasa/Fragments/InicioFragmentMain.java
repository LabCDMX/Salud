package mx.digitalcoaster.rzertuche.medicoencasa.Fragments;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
//import android.telecom.Call;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import javax.net.ssl.HttpsURLConnection;

import mx.digitalcoaster.rzertuche.medicoencasa.DataBase.DataBaseDB;
import mx.digitalcoaster.rzertuche.medicoencasa.DataBase.DataBaseHelper;
import mx.digitalcoaster.rzertuche.medicoencasa.Utils.SharedPreferences;
import mx.digitalcoaster.rzertuche.medicoencasa.R;
import mx.digitalcoaster.rzertuche.medicoencasa.api.ApiInterface;
import mx.digitalcoaster.rzertuche.medicoencasa.api.MedicalService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link InicioFragmentMain.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link InicioFragmentMain#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InicioFragmentMain extends Fragment {

    public static String TAG = InicioFragment.class.getSimpleName();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    SharedPreferences sharedPreferences;
    ImageButton imageButton3;
    private ProgressDialog progress;
    private SQLiteDatabase db = null;      // Objeto para utilizar la base de datos
    private DataBaseHelper sqliteHelper;   // Objeto para abrir la base de Datos
    private Cursor c = null;
    HttpURLConnection conn;
    URL url; // URL de donde queremos obtener información
    private JSONObject respuestaJSON;
    private Boolean sendPacientes = false;
    private Boolean sendHistoric = false;



    public InicioFragmentMain() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InicioFragmentMain newInstance(String param1, String param2) {
        InicioFragmentMain fragment = new InicioFragmentMain();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sincronizar_inicio, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progress = new ProgressDialog(getActivity());
        progress.setMessage("Sincronizando datos...");
        progress.setIndeterminate(false);
        progress.setCancelable(false);


        sharedPreferences = SharedPreferences.getInstance();
        sharedPreferences.clearPreferences();

        imageButton3 = getActivity().findViewById(R.id.imageButton3);
        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                viewAlertSincronizar();

                /*db = getActivity().openOrCreateDatabase(DataBaseDB.DB_NAME, Context.MODE_PRIVATE ,null);

                try {
                    c = db.rawQuery("SELECT * FROM " + DataBaseDB.TABLE_NAME_PACIENTES_SINCRONIZAR, null);
                    if (c.moveToFirst()) {
                        do {

                            sendData(c.getString(2),c.getString(4),c.getString(5),c.getString(1),c.getString(6),c.getString(7),c.getString(8),
                                    c.getString(9),c.getString(10), c.getString(11),c.getString(21),c.getString(22),c.getString(12),c.getString(3),
                                    c.getString(13), c.getString(14),c.getString(15),c.getString(17),c.getString(18),c.getString(19));

                        }while (c.moveToNext());
                    } else {
                        Toast.makeText(getActivity(),"No hay clientes a sincronizar",Toast.LENGTH_LONG).show();
                        System.out.println("No existen PACIENTES");
                    }
                    c.close();
                } catch (Exception ex) {
                    Log.e("Error", ex.toString());
                } finally {

                    db.close();
                }*/


            }









        });




    }


    public void onButtonPressed(Uri uri) {
        callParentMethod();
    }


    public void callParentMethod(){
        getActivity().onBackPressed();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    public void sendDataGenerales(final String curp, String a_pa, String a_ma, final String nombre, String fechaNac, String estadoNac, String sexo, String nac, String estadoRes,
                         String municipio, String cp, String poblacion, String colonia, String nombreCalle, String edo_civil, String ocupacion, String derecho,
                         String telFijo, String telCel, String correo) throws JSONException, ParseException {

        progress.show();


       if(sexo.equals("Masculino")){
           sexo = String.valueOf(0);
       }else{
           sexo = String.valueOf(1);
       }


        /*String json = "" +
                "{\"curp\":\""+curp+"\"," +
                "\"apellidoPaterno\":\""+a_pa+"\"," +
                "\"apellidoMaterno\":\""+a_ma+"\"," +
                "\"nombre\":\""+nombre+"\"," +
                "\"fechadeNacimiento\":\""+fechaNac+"\"," +
                "\"estadodeNacimiento\":\""+estadoNac+"\"," +
                "\"sexo\":"+sexo+"," +
                "\"nacionalidadOrigen\":\""+nac+"\"," +
                "\"estadoResidencia\":\""+estadoRes+"\"," +
                "\"municipio\":\""+municipio+"\"," +
                "\"cp\":\""+cp+"\"," +
                "\"pob_vul\":\""+poblacion+"\"," +
                "\"colonia\":\""+colonia+"\"," +
                "\"nombreCalle\":\""+nombreCalle+"\"," +
                "\"estadoCivil\":\""+edo_civil+"\"," +
                "\"ocupacion\":\""+ocupacion+"\"," +
                "\"derechoHabiencia\":\""+derecho+"\"," +
                "\"telFijo\":\""+telFijo+"\"," +
                "\"telCelular\":\""+telCel+"\"," +
                "\"correoElectronico\":\""+correo+"\"," +
                "\"folioDerechoHabiencia\": 0," +
                "\"createdBy\": 0" +

                "}";*/



        JSONObject jsonParams = new JSONObject();
        jsonParams.put("curp",curp);
        jsonParams.put("apellidoPaterno",a_pa);
        jsonParams.put("apellidoMaterno",a_ma);
        jsonParams.put("nombre",nombre);
        jsonParams.put("fechadeNacimiento",fechaNac);
        jsonParams.put("estadodeNacimiento",estadoNac);
        jsonParams.put("sexo",sexo);
        jsonParams.put("nacionalidadOrigen",nac);
        jsonParams.put("estadoResidencia",estadoRes);
        jsonParams.put("municipio",municipio);
        jsonParams.put("cp",cp);
        jsonParams.put("pob_vul",poblacion);
        jsonParams.put("colonia",colonia);
        jsonParams.put("nombreCalle",nombreCalle);
        jsonParams.put("estadoCivil",edo_civil);
        jsonParams.put("ocupacion",ocupacion);
        jsonParams.put("derechoHabiencia",derecho);
        jsonParams.put("telFijo",telFijo);
        jsonParams.put("telCelular",telCel);
        jsonParams.put("correoElectronico",correo);
        jsonParams.put("folioDerechoHabiencia","0");
        jsonParams.put("createdBy","0");



        //entity = new StringEntity(json, "UTF-8");
        Log.d("JSON EMV", jsonParams.toString());

        //connectPostWithCookies("https://medico.digitalcoaster.mx/api/admin/api/paciente",jsonParams.toString());


        try {
            progress.show();


            /*URL url = new URL("https://medico.digitalcoaster.mx/api/admin/api/paciente");
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();

            urlConnection.setConnectTimeout(10000);
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            urlConnection.setRequestProperty("Content-type", "application/json");


            //urlConnection.setSSLSocketFactory(getCertificates().getSocketFactory());

            DataOutputStream wr = new DataOutputStream(urlConnection.getOutputStream());

            wr.write(jsonParams.toString().getBytes());
            wr.flush();
            wr.close();

            int responseCode = urlConnection.getResponseCode();

            if(responseCode == 200) {

                BufferedReader in = new BufferedReader(
                        new InputStreamReader(urlConnection.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                Log.e("URL", "https://medico.digitalcoaster.mx/api/admin/api/paciente");
                Log.e("cadenaRespuesta", response.toString());

                respuestaJSON = new JSONObject(response.toString());
                Boolean isSucceded = respuestaJSON.getBoolean("success");
                if (isSucceded) {

                    JSONObject user = respuestaJSON.getJSONObject("user");
                    String idUser = user.getString("id");

                    Log.e("IDUser", idUser);

                    db = getActivity().openOrCreateDatabase(DataBaseDB.DB_NAME, Context.MODE_PRIVATE, null);

                    try {

                        ContentValues updates = new ContentValues();
                        updates.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_ID, idUser);
                        updates.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_NOMBRE, nombre);
                        updates.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_CURP, curp);

                        db.update(DataBaseDB.TABLE_NAME_PACIENTES_SINCRONIZAR_HISTORIC, updates, DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_NOMBRE + "='" + nombre +
                                "' AND " + DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_CURP+ "='"+curp+"'", null);

                        db.delete(DataBaseDB.TABLE_NAME_PACIENTES_SEGUIMIENTO,DataBaseDB.PACIENTES_EXPEDIENTE_CURP + "=? AND "+ DataBaseDB.PACIENTES_EXPEDIENTE_NOMBRE + "=?",new String[]{curp,nombre});

                        progress.dismiss();
                    }catch(Exception e){

                    }
                }*/



            /*}else{

                //JsonArray response2 = new JsonParser().parse(new InputStreamReader(urlConnection.getErrorStream())).getAsJsonArray();
                //JSONObject response2 = new JsonParser().parse(new InputStreamReader(urlConnection.getErrorStream())).getAsJsonObject();


                 /*if (response2 instanceof JsonObject) {
                    JsonObject  responseJsonObject = new JsonParser().parse(new InputStreamReader(urlConnection.getErrorStream())).getAsJsonObject();
                    return responseJsonObject.toString();
                }else if (responseArray instanceof JsonArray) {
                    JsonArray  responseArrayObject =  new JsonParser().parse(new InputStreamReader(urlConnection.getErrorStream())).getAsJsonArray();
                    return responseArrayObject.toString();
                }*/
                /*
                Log.e("URL", "https://medico.digitalcoaster.mx/api/admin/api/paciente");


            }*/
        }catch (Exception e){
            e.printStackTrace();
        }






        /*client.post(getActivity(), "https://medico.digitalcoaster.mx/api/admin/api/paciente", entity, "application/json", new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                Log.e("ResponseService", response.toString());

                try {
                    respuestaJSON = new JSONObject(response.toString());
                    Boolean isSucceded = respuestaJSON.getBoolean("success");
                    if(isSucceded){

                        JSONObject user = respuestaJSON.getJSONObject("user");
                        String idUser = user.getString("id");

                        Log.e("IDUser", idUser);

                        db = getActivity().openOrCreateDatabase(DataBaseDB.DB_NAME, Context.MODE_PRIVATE ,null);

                        try {

                            ContentValues values = new ContentValues();
                            values.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_ID,idUser);
                            values.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_NOMBRE,nombre);
                            values.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_CURP,curp);

                            db.insert(DataBaseDB.TABLE_NAME_PACIENTES_SINCRONIZAR_HISTORIC, null, values);

                            progress.dismiss();

                        } catch (Exception ex) {
                            Log.e("Error", ex.toString());
                        } finally {
                            db.close();
                        }


                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            public void onFailure(Throwable error, String content) {
                progress.dismiss();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                progress.dismiss();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                progress.dismiss();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                progress.dismiss();
            }

        });*/


    }

    public void sendDataHistoric(final String id, String no_expediente, String hemo, final String peso, String estatura, String tension, String frecuencia_car, String frecuencia_resp,
                                 String talla,String pulso, String glucemia, String temp, String notas_enf, String ant_personales, String ant_patologicos, String ant_obstericos, String padecimiento_actual,
                                 String sistemas_generales, String respiratorio, String cardio, String digestivo, String urinario, String repro, String hemolin, String endo, String s_nervioso, String esqueletico, String piel,
                                 String habitus, String cabeza, String cuello, String torax, String abdomen, String gine, String extremidades, String c_vertebral, String neuro, String genitales,
                                 String notas_doc, String plan, String impresion_diag, String tratamiento) throws JSONException, ParseException {


        progress.show();


        if(no_expediente == null || no_expediente.equals("") ){
            no_expediente = "";
        }


        JSONObject jsonParams = new JSONObject();
        jsonParams.put("id_usuario",id);
        jsonParams.put("no_expediente",no_expediente);
        jsonParams.put("6",hemo);
        jsonParams.put("7",peso);
        jsonParams.put("8",estatura);
        jsonParams.put("tensión",tension);
        jsonParams.put("10",frecuencia_car);
        jsonParams.put("11",frecuencia_resp);
        jsonParams.put("12",talla);
        jsonParams.put("13",pulso);
        jsonParams.put("14",glucemia);
        jsonParams.put("15",temp);
        jsonParams.put("notas_enf",notas_enf);
        jsonParams.put("ant_personales","");
        jsonParams.put("ant_patologicos",ant_patologicos);
        jsonParams.put("ant_obstericos",ant_obstericos);
        jsonParams.put("19",padecimiento_actual);
        jsonParams.put("27",sistemas_generales);
        jsonParams.put("28",respiratorio);
        jsonParams.put("29",cardio);
        jsonParams.put("30",digestivo);
        jsonParams.put("32",urinario);
        jsonParams.put("33",hemolin);
        jsonParams.put("34",endo);
        jsonParams.put("36",s_nervioso);
        jsonParams.put("37",esqueletico);
        jsonParams.put("38",piel);
        jsonParams.put("39",habitus);
        jsonParams.put("40",cabeza);
        jsonParams.put("41",cuello);
        jsonParams.put("42",torax);
        jsonParams.put("43",abdomen);
        jsonParams.put("44",gine);
        jsonParams.put("45",extremidades);
        jsonParams.put("46",c_vertebral);
        jsonParams.put("neuro",neuro);
        jsonParams.put("48",genitales);
        jsonParams.put("49",notas_doc);
        jsonParams.put("plan",plan);
        jsonParams.put("51",impresion_diag);
        jsonParams.put("52",tratamiento);
        jsonParams.put("no_visita","1");





        Log.d("JSON EMV", jsonParams.toString());



        try {
            progress.show();
            URL url = new URL("https://medico.digitalcoaster.mx/api/admin/api/pacienteResultados");
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();

            urlConnection.setConnectTimeout(10000);
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            urlConnection.setRequestProperty("Content-type", "application/json");


            //urlConnection.setSSLSocketFactory(getCertificates().getSocketFactory());

            DataOutputStream wr = new DataOutputStream(urlConnection.getOutputStream());

            wr.write(jsonParams.toString().getBytes());
            wr.flush();
            wr.close();

            int responseCode = urlConnection.getResponseCode();

            if(responseCode == 200) {

                BufferedReader in = new BufferedReader(
                        new InputStreamReader(urlConnection.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                Log.e("URL", "https://medico.digitalcoaster.mx/api/admin/api/pacienteResultados");
                Log.e("cadenaRespuesta", response.toString());




                progress.dismiss();
            }else{



                Log.e("URL", "https://medico.digitalcoaster.mx/api/admin/api/pacienteResultados");


            }
        }catch (Exception e){
            e.printStackTrace();
        }





    }





    public String connectPostWithCookies(String url1, String params){
        try {
            progress.show();
            URL url = new URL(url1);
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();

            urlConnection.setConnectTimeout(10000);
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            urlConnection.setRequestProperty("Content-type", "application/json");


            //urlConnection.setSSLSocketFactory(getCertificates().getSocketFactory());

            DataOutputStream wr = new DataOutputStream(urlConnection.getOutputStream());

            wr.write(params.getBytes());
            wr.flush();
            wr.close();

            int responseCode = urlConnection.getResponseCode();

            if(responseCode == 200) {

                BufferedReader in = new BufferedReader(
                        new InputStreamReader(urlConnection.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                Log.e("URL", url1);
                Log.e("cadenaRespuesta", response.toString());

                respuestaJSON = new JSONObject(response.toString());
                Boolean isSucceded = respuestaJSON.getBoolean("success");
                if (isSucceded) {

                    JSONObject user = respuestaJSON.getJSONObject("user");
                    String idUser = user.getString("id");

                    Log.e("IDUser", idUser);

                    db = getActivity().openOrCreateDatabase(DataBaseDB.DB_NAME, Context.MODE_PRIVATE, null);

                    try {

                        ContentValues values = new ContentValues();
                        values.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_ID, idUser);
                        //values.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_NOMBRE, nombre);
                        //values.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_CURP, curp);

                        db.insert(DataBaseDB.TABLE_NAME_PACIENTES_SINCRONIZAR_HISTORIC, null, values);

                        progress.dismiss();
                    }catch(Exception e){

                    }
                }


                        progress.dismiss();
                return response.toString();
            }else{

                //JsonArray response2 = new JsonParser().parse(new InputStreamReader(urlConnection.getErrorStream())).getAsJsonArray();
                //JSONObject response2 = new JsonParser().parse(new InputStreamReader(urlConnection.getErrorStream())).getAsJsonObject();


                 /*if (response2 instanceof JsonObject) {
                    JsonObject  responseJsonObject = new JsonParser().parse(new InputStreamReader(urlConnection.getErrorStream())).getAsJsonObject();
                    return responseJsonObject.toString();
                }else if (responseArray instanceof JsonArray) {
                    JsonArray  responseArrayObject =  new JsonParser().parse(new InputStreamReader(urlConnection.getErrorStream())).getAsJsonArray();
                    return responseArrayObject.toString();
                }*/

                    Log.e("URL", url1);


                return "false";
            }
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }



    /*public void getPostal() {
        try {

            String IPCodigos = "https://medico.digitalcoaster.mx/api/admin/api/codigospostales";
            System.out.println(IPCodigos);
            url = new URL(IPCodigos);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Linux; Android 1.5; es-ES) Ejemplo HTTP");

            "{\"curp\":\""+curp+"\"," +
                    "\"apellidoPaterno\":\""+a_pa+"\"," +
                    "\"apellidoMaterno\":" + a_ma + "," +
                    "\"nombre\":\""+nombre+"\"," +
                    "\"fechadeNacimiento\":"+fechaNac+"," +
                    "\"estadodeNacimiento\":\""+estadoNac+"\"," +
                    "\"sexo\":\""+sexo+"\"," +
                    "\"nacionalidadOrigen\":\""+nac+"\"," +
                    "\"estadoResidencia\":\""+estadoRes+"\"," +
                    "\"municipio\":\""+municipio+"\"," +
                    "\"cp\":\""+cp+"\"," +
                    "\"pob_vul\":\""+poblacion+"\"," +
                    "\"colonia\":"+colonia+"," +
                    "\"nombreCalle\":"+nombreCalle+"," +
                    "\"estadoCivil\":"+edo_civil+"," +
                    "\"ocupacion\":"+ocupacion+"," +
                    "\"derechoHabiencia\":\""+derecho+"\"," +
                    "\"telFijo\":\""+telFijo+"\"," +
                    "\"telCelular\":\""+telCel+"\"," +
                    "\"correoElectronico\":\""+correo+"\"," +
                    "}";


            Uri.Builder builder = new Uri.Builder()
                    .appendQueryParameter("curp", curp)
                    .appendQueryParameter("apellidoPaterno", paramValue2)
                    .appendQueryParameter("nombre", paramValue3);
                    .appendQueryParameter("nombre", paramValue3);
                    .appendQueryParameter("nombre", paramValue3);
                    .appendQueryParameter("nombre", paramValue3);
                    .appendQueryParameter("nombre", paramValue3);
                    .appendQueryParameter("nombre", paramValue3);
                    .appendQueryParameter("nombre", paramValue3);
                    .appendQueryParameter("nombre", paramValue3);
                    .appendQueryParameter("nombre", paramValue3);
                    .appendQueryParameter("nombre", paramValue3);
                    .appendQueryParameter("nombre", paramValue3);
                    .appendQueryParameter("nombre", paramValue3);
                    .appendQueryParameter("nombre", paramValue3);
                    .appendQueryParameter("nombre", paramValue3);







            String query = builder.build().getEncodedQuery();

            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(query);
            writer.flush();
            writer.close();
            os.close();

            conn.connect();

            int respuesta = conn.getResponseCode();
            StringBuilder result = new StringBuilder();

            if (respuesta == HttpURLConnection.HTTP_OK) {

                InputStream in = new BufferedInputStream(conn.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line); // Pasar todas las entradas al StringBuilder
                }

                /*respuestaJSON = new JSONObject(result.toString());
                JSONArray parentesco = respuestaJSON.getJSONArray("codigospostales");

                String codigo_postal;
                String colonia;
                String municipio;
                String estado;

                db = getActivity().openOrCreateDatabase(DataBaseDB.DB_NAME, Context.MODE_PRIVATE, null);
                for (int i = 0; i < parentesco.length(); i++) {

                    codigo_postal = parentesco.getJSONObject(i).getString("CodigoPostal");
                    colonia = parentesco.getJSONObject(i).getString("Colonia");
                    municipio = parentesco.getJSONObject(i).getString("Municipio");
                    estado = parentesco.getJSONObject(i).getString("Estado");


                    System.out.println("CODIGO_POSTAL: " + codigo_postal);
                    System.out.println("COLONIA: " + colonia);
                    System.out.println("MUNICIPIO: " + municipio);
                    System.out.println("ESTADO: " + estado);

                    /*------------------------- Revisar si existe ------------------------*/
                    /*c = db.rawQuery("SELECT " + DataBaseDB.CODIGO_POSTAL +
                            " FROM " + DataBaseDB.TABLE_NAME_CODIGOS_POSTALES +
                            " WHERE " + DataBaseDB.CODIGO_POSTAL + "='" + codigo_postal + "'", null);
                    try {
                        if (c.moveToFirst()) {
                            System.out.print("Codigo existente: ");
                            ContentValues update = new ContentValues();

                            update.put(DataBaseDB.CODIGO_POSTAL, codigo_postal);
                            update.put(DataBaseDB.COLONIA, colonia);
                            update.put(DataBaseDB.MUNICIPIO, municipio);
                            update.put(DataBaseDB.ESTADO, estado);

                            db.update(DataBaseDB.TABLE_NAME_CODIGOS_POSTALES, update, DataBaseDB.CODIGO_POSTAL + "='" + codigo_postal + "'", null);
                            System.out.println("Codigo actualizado correctamente");

                        } else {
                            ContentValues values = new ContentValues();

                            values.put(DataBaseDB.CODIGO_POSTAL, codigo_postal);
                            values.put(DataBaseDB.COLONIA, colonia);
                            values.put(DataBaseDB.MUNICIPIO, municipio);
                            values.put(DataBaseDB.ESTADO, estado);

                            db.insert(DataBaseDB.TABLE_NAME_CODIGOS_POSTALES, null, values);
                            System.out.println("Codigo postal insertado correctamente");
                        }
                        c.close();
                    } catch (SQLException ex) {
                        System.out.println("Error al insertar codigo postal: " + ex);
                    }
                }
                db.close();*/

           // }


        /*} catch (IOException e) {
        } catch (JSONException e) {
        }
    }*/

    public void enableStrictMode() {
        StrictMode.setThreadPolicy(
                new StrictMode.ThreadPolicy.Builder()
                        .detectDiskReads()
                        .detectDiskWrites()
                        .detectNetwork()
                        .penaltyLog()
                        .build());
        StrictMode.setVmPolicy(
                new StrictMode.VmPolicy.Builder()
                        .detectLeakedSqlLiteObjects()
                        .penaltyLog()
                        .build());
    }


    private void viewAlertSincronizar() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(false);
        final LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.alert_sincronizar, null);


        final TextView sincronizar_generales = view.findViewById(R.id.btn_edit);
        final TextView sincronizar_historic = view.findViewById(R.id.btn_edit2);
        final TextView sincronizar_visitas = view.findViewById(R.id.btn_edit3);


        final ImageButton btn_generales = view.findViewById(R.id.btn_generales);
        final ImageButton btn_historic = view.findViewById(R.id.btn_historic);
        final ImageButton btn_visitas = view.findViewById(R.id.btn_visitas);


        String countGenerales= String.valueOf(getCountDatos("Generales"));
        String countHistoric= String.valueOf(getCountDatos("Historic"));
        String countVisitas= String.valueOf(getCountDatos("Visitas"));

        sincronizar_generales.setText(countGenerales + "/" + countGenerales);
        sincronizar_historic.setText(countHistoric + "/" + countHistoric);
        sincronizar_visitas.setText(countVisitas + "/" + countVisitas);



        btn_generales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progress.show();

                db = getActivity().openOrCreateDatabase(DataBaseDB.DB_NAME, Context.MODE_PRIVATE ,null);

                try {
                    c = db.rawQuery("SELECT * FROM " + DataBaseDB.TABLE_NAME_PACIENTES_SINCRONIZAR, null);
                    if (c.moveToFirst()) {
                        do {

                            sendDataGenerales(c.getString(2),c.getString(4),c.getString(5),c.getString(1),c.getString(6),c.getString(7),c.getString(8),
                                    c.getString(9),c.getString(10), c.getString(11),c.getString(21),c.getString(22),c.getString(12),c.getString(3),
                                    c.getString(13), c.getString(14),c.getString(15),c.getString(17),c.getString(18),c.getString(19));

                        }while (c.moveToNext());
                    } else {
                        Toast.makeText(getActivity(),"No hay pacientes a sincronizar",Toast.LENGTH_LONG).show();
                        progress.dismiss();
                        System.out.println("No existen PACIENTES");
                    }
                    c.close();
                } catch (Exception ex) {
                    Log.e("Error", ex.toString());
                } finally {

                    db.close();


                }

            }
        });


        btn_historic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                db = getActivity().openOrCreateDatabase(DataBaseDB.DB_NAME, Context.MODE_PRIVATE ,null);

                try {
                    c = db.rawQuery("SELECT * FROM " + DataBaseDB.TABLE_NAME_PACIENTES_SINCRONIZAR_HISTORIC+ " WHERE "+ DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_ID +
                            " != ''", null);
                    if (c.moveToFirst()) {
                        do {

                            sendDataHistoric(c.getString(53),c.getString(4),c.getString(7),c.getString(8),c.getString(9),c.getString(10),c.getString(11),
                                    c.getString(12),c.getString(13), c.getString(14),c.getString(15),c.getString(16),c.getString(48),"",
                                    c.getString(24), c.getString(26),c.getString(23),c.getString(28),c.getString(29),c.getString(30),c.getString(31),c.getString(32),c.getString(33),c.getString(34),
                                    c.getString(35),c.getString(36),c.getString(37),c.getString(38),c.getString(39),c.getString(40),c.getString(41),c.getString(54),c.getString(42),c.getString(43),c.getString(44),
                                    c.getString(45),c.getString(46),c.getString(47),c.getString(49),c.getString(50),c.getString(51),c.getString(52));






                        }while (c.moveToNext());
                    } else {
                        Toast.makeText(getActivity(),"No hay pacientes a sincronizar",Toast.LENGTH_LONG).show();
                        progress.dismiss();
                        System.out.println("No existen PACIENTES");
                    }
                    c.close();
                } catch (Exception ex) {
                    Log.e("Error", ex.toString());
                } finally {

                    db.close();

                }

            }
        });

        btn_visitas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



            }
        });


        builder.setView(view);
        builder.setCancelable(true);
        builder.show();


    }

    public int getCountDatos(String datosCount){

        db = getActivity().openOrCreateDatabase(DataBaseDB.DB_NAME, Context.MODE_PRIVATE ,null);
        String tableName = null;
        if(datosCount.equals("Generales")){
            tableName = DataBaseDB.TABLE_NAME_PACIENTES_SINCRONIZAR;
        }else if(datosCount.equals("Historic")){
            tableName = DataBaseDB.TABLE_NAME_PACIENTES_SINCRONIZAR_HISTORIC;

        }else if(datosCount.equals("Visitas")){
            tableName = DataBaseDB.TABLE_NAME_PACIENTES_VISITAS;
        }

        try {
            c = db.rawQuery("SELECT * FROM " + tableName, null);
            if (c.moveToFirst()) {
                return c.getCount();
            } else {
                return 0;
            }
        } catch (Exception ex) {
            Log.e("Error", ex.toString());
        } finally {

            db.close();


        }
        return 0;
    }



}
