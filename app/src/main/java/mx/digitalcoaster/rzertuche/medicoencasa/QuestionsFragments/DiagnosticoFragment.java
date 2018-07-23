package mx.digitalcoaster.rzertuche.medicoencasa.QuestionsFragments;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.ObjectStreamException;

import mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity;
import mx.digitalcoaster.rzertuche.medicoencasa.DataBase.DataBaseDB;
import mx.digitalcoaster.rzertuche.medicoencasa.DataBase.DataBaseHelper;
import mx.digitalcoaster.rzertuche.medicoencasa.R;
import mx.digitalcoaster.rzertuche.medicoencasa.Utils.SharedPreferences;

import static mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity.inicio;
import static mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity.registros;
import static mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity.seguimiento;
import static mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity.sincronizacion;
import static mx.digitalcoaster.rzertuche.medicoencasa.Fragments.PacientesFragment.isSinExp;

;


public class DiagnosticoFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    SharedPreferences sharedPreferences;

    LinearLayout sano,obeso,sobrepeso;
    ImageButton next;

    EditText et_tratamiento, et_tratamiento1, et_tratamiento2 , et_diagnostico,et_diagnostico1,et_diagnostico2;
    TextView uno,dos,tres;

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
        return inflater.inflate(R.layout.fragment_diagnostico, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        //Obtencion de datos del sharedPreferences
        sharedPreferences = SharedPreferences.getInstance();


        next = (ImageButton) getActivity().findViewById(R.id.next);

        sano = (LinearLayout) getActivity().findViewById(R.id.layout_sano);
        sobrepeso = (LinearLayout) getActivity().findViewById(R.id.layout_sobrepeso);
        obeso = (LinearLayout) getActivity().findViewById(R.id.layout_obeso);

        et_tratamiento = (EditText) getActivity().findViewById(R.id.et_tratamiento);
        et_tratamiento1 = (EditText) getActivity().findViewById(R.id.et_tratamiento1);
        et_tratamiento2 = (EditText) getActivity().findViewById(R.id.et_tratamiento2);

        et_diagnostico = (EditText) getActivity().findViewById(R.id.et_diagnostico);
        et_diagnostico1 = (EditText) getActivity().findViewById(R.id.et_diagnostico1);
        et_diagnostico2 = (EditText) getActivity().findViewById(R.id.et_diagnostico2);

        uno = (EditText) getActivity().findViewById(R.id.uno);
        dos = (EditText) getActivity().findViewById(R.id.dos);
        tres = (EditText) getActivity().findViewById(R.id.tres);


        if(isSinExp) {
            getDatosSinExp();
            String name = sharedPreferences.getStringData("nameItem");
            et_tratamiento.setText(sharedPreferences.getStringData("Tratamiento"+name));
            et_tratamiento1.setVisibility(View.GONE);
            et_tratamiento2.setVisibility(View.GONE);
            hideNumbers();

            et_diagnostico.setText(sharedPreferences.getStringData("Diagnostico"+name));
            et_diagnostico1.setVisibility(View.GONE);
            et_diagnostico2.setVisibility(View.GONE);
            hideNumbers();

        }





        sano.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {

                sobrepeso.setBackgroundColor(Color.TRANSPARENT);
                obeso.setBackgroundColor(Color.TRANSPARENT);


                sano.setBackgroundColor(getResources().getColor(R.color.colorSano));
                sharedPreferences.setStringData("ImageItem","Sano");

            }
        });

        sobrepeso.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                sano.setBackgroundColor(Color.TRANSPARENT);
                obeso.setBackgroundColor(Color.TRANSPARENT);


                sobrepeso.setBackgroundColor(getResources().getColor(R.color.colorAmbar));
                sharedPreferences.setStringData("ImageItem","Sobrepeso");

            }
        });

        obeso.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                sano.setBackgroundColor(Color.TRANSPARENT);
                sobrepeso.setBackgroundColor(Color.TRANSPARENT);


                obeso.setBackgroundColor(getResources().getColor(R.color.colorRojo));
                sharedPreferences.setStringData("ImageItem","Obeso");

            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sharedPreferences.setStringData("Diagnostico1", et_diagnostico.getText().toString());
                sharedPreferences.setStringData("Diagnostico2", et_diagnostico1.getText().toString());
                sharedPreferences.setStringData("Diagnostico3", et_diagnostico2.getText().toString());


                sharedPreferences.setStringData("Tratamiento1", et_tratamiento.getText().toString());
                sharedPreferences.setStringData("Tratamiento2", et_tratamiento1.getText().toString());
                sharedPreferences.setStringData("Tratamiento3", et_tratamiento2.getText().toString());


                ((MainActivity)getActivity()).fragmentTarjetaPaciente();


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


    public void getDatosSinExp(){

        db = getActivity().openOrCreateDatabase(DataBaseDB.DB_NAME, Context.MODE_PRIVATE, null);
        try {
            c = db.rawQuery("SELECT * FROM " + DataBaseDB.TABLE_NAME_PACIENTES_SIN_EXPEDIENTE + " WHERE "
                    + DataBaseDB.PACIENTES_EXPEDIENTE_NOMBRE + " = '" + sharedPreferences.getStringData("nameItem") + "' AND "
                    + DataBaseDB.PACIENTES_EXPEDIENTE_CURP + " = '" + sharedPreferences.getStringData("curpItem")+"'", null);
            if (c.moveToFirst()) {
                do {
                    String name = sharedPreferences.getStringData("nameItem");


                    //sharedPreferences.setStringData("Elaboro"+name,c.getString(5));
                    //sharedPreferences.setStringData("Siguiente Visita"+name,c.getString(6));
                    sharedPreferences.setStringData("Diagnostico"+name,c.getString(51));
                    sharedPreferences.setStringData("Tratamiento"+name,c.getString(52));


                }while (c.moveToNext());
            } else {
                System.out.println("No existen PACIENTES");
            }
            c.close();
        } catch (Exception ex) {
            Log.e("Error", ex.toString());
        } finally {
            db.close();
        }


    }

    public void hideNumbers(){
        uno.setVisibility(View.GONE);
        dos.setVisibility(View.GONE);
        tres.setVisibility(View.GONE);
    }

}
