package mx.digitalcoaster.rzertuche.medicoencasa.Fragments;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;

import javax.net.ssl.HttpsURLConnection;

import io.realm.Realm;
import io.realm.RealmResults;
import mx.digitalcoaster.rzertuche.medicoencasa.DataBase.DataBaseDB;
import mx.digitalcoaster.rzertuche.medicoencasa.DataBase.DataBaseHelper;
import mx.digitalcoaster.rzertuche.medicoencasa.R;
import mx.digitalcoaster.rzertuche.medicoencasa.Utils.SharedPreferences;
import mx.digitalcoaster.rzertuche.medicoencasa.api.ApiInterface;
import mx.digitalcoaster.rzertuche.medicoencasa.api.MedicalService;
import mx.digitalcoaster.rzertuche.medicoencasa.models.Item;
import mx.digitalcoaster.rzertuche.medicoencasa.models.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SincronizacionFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SincronizacionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SincronizacionFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TextView total;
    public TextView verde;
    public TextView amarillo;
    public TextView rojo;
    public int totalPatient;
    private ImageButton sincronizar;
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
    static ProgressDialog mProgressDialog;


    private OnFragmentInteractionListener mListener;



    public SincronizacionFragment() {
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
    public static SincronizacionFragment newInstance(String param1, String param2) {
        SincronizacionFragment fragment = new SincronizacionFragment();
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
        return inflater.inflate(R.layout.fragment_registros, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String totalPatients = String.valueOf(getTotalDB());

        total = view.findViewById(R.id.total);
        verde = view.findViewById(R.id.verde);
        amarillo =  view.findViewById(R.id.amarillo);
        rojo = view.findViewById(R.id.rojo);
        sincronizar = view.findViewById(R.id.imageButton6);
        progress = new ProgressDialog(getActivity());
        progress.setMessage("Sincronizando datos...");
        progress.setIndeterminate(false);
        progress.setCancelable(false);


        total.setText(totalPatients);
        sincronizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                viewAlertSincronizar();

            }
        });


    }



    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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

    private int getTotalDB() {
        int totalAux = 0;

        db = getActivity().openOrCreateDatabase(DataBaseDB.DB_NAME, Context.MODE_PRIVATE, null);
        try {
            c = db.rawQuery("SELECT * FROM " + DataBaseDB.TABLE_NAME_PACIENTES, null);
            totalAux = c.getCount();
            c.close();

            return totalAux;

        } catch (Exception ex) {
            Log.e("Error", ex.toString());
        } finally {
            db.close();
        }

        return totalAux;
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

                db = getActivity().openOrCreateDatabase(DataBaseDB.DB_NAME, Context.MODE_PRIVATE ,null);
                showActivityIndicator("Aviso","Sincronizando datos");

                try {
                    c = db.rawQuery("SELECT * FROM " + DataBaseDB.TABLE_NAME_PACIENTES_SINCRONIZAR, null);
                    if (c.moveToFirst()) {
                        do {
                            sendDataGenerales(c.getString(2),c.getString(4),c.getString(5),c.getString(1),c.getString(6),c.getString(7),c.getString(8),
                                    c.getString(9),c.getString(10), c.getString(11),c.getString(21),c.getString(22),c.getString(12),c.getString(3),
                                    c.getString(13), c.getString(14),c.getString(15),c.getString(17),c.getString(18),c.getString(19));

                        }while (c.moveToNext());
                        hideActivityIndicator();
                    } else {
                        Toast.makeText(getActivity(),"No hay pacientes a sincronizar",Toast.LENGTH_LONG).show();
                        hideActivityIndicator();
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


    public void sendDataGenerales(final String curp, String a_pa, String a_ma, final String nombre, String fechaNac, String estadoNac, String sexo, String nac, String estadoRes,
                                  String municipio, String cp, String poblacion, String colonia, String nombreCalle, String edo_civil, String ocupacion, String derecho,
                                  String telFijo, String telCel, String correo) throws JSONException, ParseException {



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

                    Log.e("URL", "https://medico.digitalcoaster.mx/api/admin/api/paciente");
                    Log.e("cadenaRespuesta ", response.body().toString());

                    JsonObject responsePaciente = new JsonObject();
                    responsePaciente = response.body();
                    Boolean isSucceded = responsePaciente.getAsJsonObject("success").getAsBoolean();


                    if (isSucceded) {

                        JsonObject user = responsePaciente.getAsJsonObject("user");
                        String idUser = user.getAsJsonObject("id").getAsString();

                        Log.e("IDUser", idUser);

                        db = getActivity().openOrCreateDatabase(DataBaseDB.DB_NAME, Context.MODE_PRIVATE, null);

                        try {

                            ContentValues updates = new ContentValues();
                            updates.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_ID, idUser);
                            updates.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_NOMBRE, nombre);
                            updates.put(DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_CURP, curp);

                            db.update(DataBaseDB.TABLE_NAME_PACIENTES_SINCRONIZAR_HISTORIC, updates, DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_NOMBRE + "='" + nombre +
                                    "' AND " + DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_CURP+ "='"+curp+"'", null);

                            db.delete(DataBaseDB.TABLE_NAME_PACIENTES_SINCRONIZAR,DataBaseDB.PACIENTES_SINCRONIZAR_CURP + "=? AND "+ DataBaseDB.PACIENTES_SINCRONIZAR_NOMBRE + "=?",new String[]{curp,nombre});

                        }catch(Exception e){
                            e.printStackTrace();
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
            hideActivityIndicator();

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




        if(no_expediente == null || no_expediente.equals("") ){
            no_expediente = "";
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
        jsonParams.addProperty("ant_personales","");
        jsonParams.addProperty("ant_patologicos",ant_patologicos);
        jsonParams.addProperty("ant_obstericos",ant_obstericos);
        jsonParams.addProperty("19",padecimiento_actual);
        jsonParams.addProperty("27",sistemas_generales);
        jsonParams.addProperty("28",respiratorio);
        jsonParams.addProperty("29",cardio);
        jsonParams.addProperty("30",digestivo);
        jsonParams.addProperty("32",urinario);
        jsonParams.addProperty("33",hemolin);
        jsonParams.addProperty("34",endo);
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





        Log.d("JSON EMV", jsonParams.toString());



        try {
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
                hideActivityIndicator();


                }else{



                Log.e("URL", "https://medico.digitalcoaster.mx/api/admin/api/pacienteResultados");


            }
        }catch (Exception e){
            e.printStackTrace();
        }





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

    public void showActivityIndicator(String strTitle, String strMessage) {
        try {
            if (mProgressDialog != null) {
                hideActivityIndicator();
                mProgressDialog.setCancelable(false);
            }
            if (getActivity() != null) {
                mProgressDialog = ProgressDialog.show(getActivity(), strTitle,
                        strMessage, true);
                mProgressDialog.setCancelable(false);
            }


        }catch (Exception ex){
            ex.printStackTrace();
        }
    }


    public static void hideActivityIndicator() {
        if(mProgressDialog != null){
            mProgressDialog.dismiss();
        }
    }


}
