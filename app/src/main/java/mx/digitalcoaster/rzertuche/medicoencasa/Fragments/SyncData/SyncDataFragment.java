package mx.digitalcoaster.rzertuche.medicoencasa.Fragments.SyncData;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import mx.digitalcoaster.rzertuche.medicoencasa.DataBase.DataBaseDB;
import mx.digitalcoaster.rzertuche.medicoencasa.DataBase.DataBaseHelper;
import mx.digitalcoaster.rzertuche.medicoencasa.R;

public class SyncDataFragment extends Fragment implements View.OnClickListener {
    //Add VIEW...
    View view;

    //Add CONST data
    public static String TAG = "SyncDataFragment";

    Button  buttonBack;

    ImageButton btnGenerales,
                btnHistoric,
                btnVisitas;

    //COUNTING AND SHOW DATA SYNC
    TextView tvCountGeneralData,
             tvCountHistory,
             tvCountVisit;

    private SQLiteDatabase db = null;      // Objeto para utilizar la base de datos
    private DataBaseHelper sqliteHelper;   // Objeto para abrir la base de Datos
    private Cursor c = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.sync_data_fragment,container,false);

        btnGenerales =  view.findViewById(R.id.btn_generales);
        btnHistoric = view.findViewById(R.id.btn_historic);
        btnVisitas = view.findViewById(R.id.btn_visitas);
        buttonBack = view.findViewById(R.id.but_return);

        btnGenerales.setOnClickListener(this);
        btnHistoric.setOnClickListener(this);
        btnVisitas.setOnClickListener(this);
        buttonBack.setOnClickListener(this);

        //Add func count sync data...
        showCountDataSync(view);

        return view;
    }


    private void showCountDataSync(View view){

        tvCountGeneralData = view.findViewById(R.id.text_count_general_data);
        tvCountHistory = view.findViewById(R.id.text_count_history);
        tvCountVisit = view.findViewById(R.id.text_count_visit_medic);

        String countGenerales = String.valueOf(getCountData("Generales"));
        String countHistoric  = String.valueOf(getCountData("Historic"));
        String countVisitas   = String.valueOf(getCountData("Visitas"));

        tvCountGeneralData.setText(countGenerales + "/" + countGenerales);
        tvCountHistory.setText(countHistoric + "/" + countHistoric);
        tvCountVisit.setText(countVisitas + "/" + countVisitas);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btn_generales:

                break;

            case R.id.btn_historic:

                break;

            case R.id.btn_visitas:

                break;

            case R.id.but_return:
                getFragmentManager().popBackStack();
                break;
        }
    }

    public int getCountData(String datosCount){

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
            c.close();
            db.close();
        }

        return 0;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        getFragmentManager().beginTransaction().remove(this).commit();
    }

}
