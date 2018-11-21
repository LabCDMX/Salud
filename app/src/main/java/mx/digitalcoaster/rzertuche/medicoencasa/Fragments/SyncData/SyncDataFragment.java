package mx.digitalcoaster.rzertuche.medicoencasa.Fragments.SyncData;

import android.app.ProgressDialog;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import mx.digitalcoaster.rzertuche.medicoencasa.DataBase.SyncDataDB;
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

    SyncDataDB mSyncDataDB;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSyncDataDB = new SyncDataDB();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.sync_data_fragment,container,false);

        //Data.. :3
        mSyncDataDB.addBaseActivity(getActivity(),view);

        btnGenerales =  view.findViewById(R.id.btn_generales);
        btnHistoric = view.findViewById(R.id.btn_historic);
        btnVisitas = view.findViewById(R.id.btn_visitas);
        buttonBack = view.findViewById(R.id.but_return);

        btnGenerales.setOnClickListener(this);
        btnHistoric.setOnClickListener(this);
        btnVisitas.setOnClickListener(this);
        buttonBack.setOnClickListener(this);

        //Add func count sync data...
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btn_generales:
                    mSyncDataDB.initSyncGeneralesData();
                break;

            case R.id.btn_historic:
                    mSyncDataDB.initSyncHistoric();
                break;

            case R.id.btn_visitas:
                    mSyncDataDB.initSyncVisitas();
                break;

            case R.id.but_return:
                getFragmentManager().popBackStack();
                break;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        getFragmentManager().beginTransaction().remove(this).commit();
    }

}
