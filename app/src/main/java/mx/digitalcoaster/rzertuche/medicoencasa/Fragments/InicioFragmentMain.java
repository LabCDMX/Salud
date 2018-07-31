package mx.digitalcoaster.rzertuche.medicoencasa.Fragments;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.conn.ConnectTimeoutException;
import cz.msebera.android.httpclient.entity.StringEntity;
import io.realm.internal.android.JsonUtils;
import mx.digitalcoaster.rzertuche.medicoencasa.DataBase.DataBaseDB;
import mx.digitalcoaster.rzertuche.medicoencasa.DataBase.DataBaseHelper;
import mx.digitalcoaster.rzertuche.medicoencasa.Utils.SharedPreferences;
import mx.digitalcoaster.rzertuche.medicoencasa.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link InicioFragmentMain.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link InicioFragmentMain#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InicioFragmentMain extends Fragment {
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

                db = getActivity().openOrCreateDatabase(DataBaseDB.DB_NAME, Context.MODE_PRIVATE ,null);

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
                }


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


    public void sendData(final String curp, String a_pa, String a_ma, final String nombre, String fechaNac, String estadoNac, String sexo, String nac, String estadoRes,
                         String municipio, String cp, String poblacion, String colonia, String nombreCalle, String edo_civil, String ocupacion, String derecho,
                         String telFijo, String telCel, String correo){

        progress.show();
        final AsyncHttpClient client = new AsyncHttpClient();
        HttpEntity entity;

       if(sexo.equals("Masculino")){
           sexo = String.valueOf(0);
       }else{
           sexo = String.valueOf(1);
       }

        String json = "" +
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

                "}";


        entity = new StringEntity(json, "UTF-8");
        Log.d("JSON EMV", json);
        client.post(getActivity(), "https://medico.digitalcoaster.mx/api/admin/api/paciente", entity, "application/json", new JsonHttpResponseHandler() {
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

        });







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


}
