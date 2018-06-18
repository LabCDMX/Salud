package mx.digitalcoaster.rzertuche.medicoencasa.QuestionsFragments;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity;
import mx.digitalcoaster.rzertuche.medicoencasa.DataBase.DataBaseDB;
import mx.digitalcoaster.rzertuche.medicoencasa.DataBase.DataBaseHelper;
import mx.digitalcoaster.rzertuche.medicoencasa.R;
import mx.digitalcoaster.rzertuche.medicoencasa.Utils.SharedPreferences;

import static mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity.inicio;
import static mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity.registros;
import static mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity.seguimiento;
import static mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity.sincronizacion;

;


public class TarjetaPacienteFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    SharedPreferences sharedPreferences;

    TextView tvNombreItem, tvCurpItem, tvDireccionItem;
    ImageButton next;
    ImageView status;

    private SQLiteDatabase db = null;      // Objeto para utilizar la base de datos
    private DataBaseHelper sqliteHelper;   // Objeto para abrir la base de Datos
    private Cursor c = null;



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
        return inflater.inflate(R.layout.fragment_tarjeta_paciente, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        blockListeners();

        //Obtencion de datos del sharedPreferences
        sharedPreferences = SharedPreferences.getInstance();

        tvNombreItem = (TextView) getActivity().findViewById(R.id.tvNombreItem);
        tvCurpItem = (TextView) getActivity().findViewById(R.id.tvCurpItem);
        tvDireccionItem = (TextView) getActivity().findViewById(R.id.tvDireccionItem);


        status = (ImageView) getActivity().findViewById(R.id.status);


        tvNombreItem.setText(sharedPreferences.getStringData("nameHistoric"));
        tvCurpItem.setText(sharedPreferences.getStringData("curpHistoric"));
        tvDireccionItem.setText(sharedPreferences.getStringData("direccionHistoric"));

        String statusImage = sharedPreferences.getStringData("ImageItem");

        if(statusImage.equals("Sano")){
            status.setBackground(getActivity().getDrawable(R.drawable.status_green));

        }else if(statusImage.equals("Sobrepeso")){
            status.setBackground(getActivity().getDrawable(R.drawable.status_ambar));


        }else if(statusImage.equals("Obeso")){
            status.setBackground(getActivity().getDrawable(R.drawable.status_red));


        }

        ImageButton next = (ImageButton) getActivity().findViewById(R.id.next);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                deleteUser(sharedPreferences.getStringData("curpHistoric"));

                ((MainActivity)getActivity()).succededClinica();


            }
        });

    }


    public void blockListeners(){
        inicio.setEnabled(false);
        registros.setEnabled(false);
        seguimiento.setEnabled(false);
        sincronizacion.setEnabled(false);
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

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void deleteUser(String CURP){

        db = getActivity().openOrCreateDatabase(DataBaseDB.DB_NAME, Context.MODE_PRIVATE ,null);

        /*------------------------- Revisar si existe ------------------------*/
        c = db.rawQuery("SELECT * FROM " + DataBaseDB.TABLE_NAME_PACIENTES_VISITAS, null);
        try {
            if(c.moveToFirst()) {
                ContentValues values = new ContentValues();
                values.put(DataBaseDB.PACIENTES_VISITA_NOMBRE,sharedPreferences.getStringData("nameHistoric"));
                values.put(DataBaseDB.PACIENTES_VISITA_CURP, sharedPreferences.getStringData("curpHistoric"));
                values.put(DataBaseDB.PACIENTES_VISITA_DIRECCION, sharedPreferences.getStringData("direccionHistoric"));
                values.put(DataBaseDB.PACIENTES_VISITA_STATUS, sharedPreferences.getStringData("ImageItem"));


                db.insert(DataBaseDB.TABLE_NAME_PACIENTES_VISITAS, null, values);
                System.out.println("Visita  insertada correctamente");
            }
            else {
                ContentValues values = new ContentValues();
                values.put(DataBaseDB.PACIENTES_VISITA_NOMBRE,sharedPreferences.getStringData("nameHistoric"));
                values.put(DataBaseDB.PACIENTES_VISITA_CURP, sharedPreferences.getStringData("curpHistoric"));
                values.put(DataBaseDB.PACIENTES_VISITA_DIRECCION, sharedPreferences.getStringData("direccionHistoric"));
                values.put(DataBaseDB.PACIENTES_VISITA_STATUS, sharedPreferences.getStringData("ImageItem"));


                db.insert(DataBaseDB.TABLE_NAME_PACIENTES_VISITAS, null, values);
                System.out.println("Visita  insertada correctamente");

            }
            c.close();
        } catch (SQLException ex) {
            System.out.println("Error al insertar productos: " + ex);
        }

        db.delete(DataBaseDB.TABLE_NAME_PACIENTES,DataBaseDB.PACIENTES_CURP + "=?",new String[]{CURP});


        db.close();
    }

}
