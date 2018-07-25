package mx.digitalcoaster.rzertuche.medicoencasa.Fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity;
import mx.digitalcoaster.rzertuche.medicoencasa.DataBase.DataBaseDB;
import mx.digitalcoaster.rzertuche.medicoencasa.DataBase.DataBaseHelper;
import mx.digitalcoaster.rzertuche.medicoencasa.R;
import mx.digitalcoaster.rzertuche.medicoencasa.Utils.SharedPreferences;
import mx.digitalcoaster.rzertuche.medicoencasa.models.Item;
import mx.digitalcoaster.rzertuche.medicoencasa.models.ItemVisita;
import mx.digitalcoaster.rzertuche.medicoencasa.models.ItemVisitaAdapter;
import mx.digitalcoaster.rzertuche.medicoencasa.models.VisitasAdapter;



public class VisitasFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public static Context appContext;

    public GridView lista;
    private List<ItemVisita> items = null;

    private SQLiteDatabase db = null;      // Objeto para utilizar la base de datos
    private DataBaseHelper sqliteHelper;   // Objeto para abrir la base de Datos
    private Cursor c = null;

    private TextView title;
    public static Boolean isSeguimiento=false;
    SharedPreferences sharedPreferences;
    public static String nombrePatient;
    private TextView nombre, diagnostico, tratamiento,expediente;
    ImageView status;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_visita_paciente, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sharedPreferences = SharedPreferences.getInstance();
        nombrePatient = sharedPreferences.getStringData("nameSeguimiento");

        status = (ImageView) getActivity().findViewById(R.id.status);

        nombre = (TextView) getActivity().findViewById(R.id.tvNombreItem);
        diagnostico = (TextView) getActivity().findViewById(R.id.textViewDiagnostico);
        tratamiento = (TextView) getActivity().findViewById(R.id.textViewTratamiento);
        expediente = (TextView) getActivity().findViewById(R.id.expediente);





        String statusImage = sharedPreferences.getStringData("ImageItem");

        nombre.setText(sharedPreferences.getStringData("Nombre"));
        diagnostico.setText(sharedPreferences.getStringData("Diagnostico"));
        tratamiento.setText(sharedPreferences.getStringData("Tratamiento"));
        expediente.setText("No. Expediente \n" + sharedPreferences.getStringData("Expediente"));


        if(statusImage.equals("Sano")){
            status.setBackground(getResources().getDrawable(R.drawable.status_green));

        }else if(statusImage.equals("Sobrepeso")){
            status.setBackground(getResources().getDrawable(R.drawable.status_ambar));


        }else if(statusImage.equals("Obeso")){
            status.setBackground(getResources().getDrawable(R.drawable.status_red));


        }

        ImageButton next = (ImageButton) getActivity().findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isSeguimiento = true;
                ((MainActivity)getActivity()).questionExploracion();
            }
        });


        lista = (GridView) getActivity().findViewById(R.id.gridview);
        items = new ArrayList<>();
        getProductos();
        lista.setAdapter(new ItemVisitaAdapter(getActivity().getApplicationContext(), items));
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View v, int position, long arg3) {

                viewAlertDialog();

            }
        });

    }




    private void getProductos() {
        db = getActivity().openOrCreateDatabase(DataBaseDB.DB_NAME, Context.MODE_PRIVATE, null);
        try {
            c = db.rawQuery("SELECT * FROM " + DataBaseDB.TABLE_NAME_PACIENTES_SEGUIMIENTO + " WHERE " +
                    DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_NOMBRE + " ='"+nombrePatient+"'", null);
            if (c.moveToFirst()) {
                do {

                    sharedPreferences.setStringData("Diagnostico",c.getString(2));
                    sharedPreferences.setStringData("Tratamiento",c.getString(3));
                    sharedPreferences.setStringData("Nombre",c.getString(1));
                    sharedPreferences.setStringData("ImageItem",c.getString(8));



                    items.add(new ItemVisita(c.getString(1), c.getString(2), c.getString(3), c.getString(4), c.getString(5), c.getString(6), c.getString(7)));
                }while (c.moveToNext());
            } else {
                System.out.println("No existen Visitas");
            }
            c.close();
        } catch (Exception ex) {
            Log.e("Error", ex.toString());
        } finally {
            db.close();
        }
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




    private void viewAlertDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(false);
        final LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.alert_visita, null);

        db = getActivity().openOrCreateDatabase(DataBaseDB.DB_NAME, android.content.Context.MODE_PRIVATE, null);
        //-------------------------- Obtener información del cliente ---------------------
        try {
            c = db.rawQuery("SELECT * FROM " + DataBaseDB.TABLE_NAME_PACIENTES_SEGUIMIENTO + " WHERE " +
                    DataBaseDB.PACIENTES_VISITA_SEGUIMIENTO_NOMBRE + " ='"+nombrePatient+"'", null);


            if (c.moveToFirst()) {

                ((TextView) view.findViewById(R.id.textViewPeso)).setText(c.getString(15));
                ((TextView) view.findViewById(R.id.textViewEstatura)).setText(c.getString(16));
                ((TextView) view.findViewById(R.id.textViewHemotipo)).setText(c.getString(14));
                ((TextView) view.findViewById(R.id.textViewPulso)).setText(c.getString(21));
                ((TextView) view.findViewById(R.id.textViewTensionArterial)).setText(c.getString(17));
                ((TextView) view.findViewById(R.id.textViewFrecuenciaCar)).setText(c.getString(18));
                ((TextView) view.findViewById(R.id.textViewFrecuenciaRes)).setText(c.getString(19));
                ((TextView) view.findViewById(R.id.textViewGlucemia)).setText(c.getString(22));
                ((TextView) view.findViewById(R.id.textViewTemperatura)).setText(c.getString(23));
                ((TextView) view.findViewById(R.id.etNotasMedicas)).setText(c.getString(9));
                ((TextView) view.findViewById(R.id.etSubjetivo)).setText(c.getString(10));
                ((TextView) view.findViewById(R.id.etDiagnostico)).setText(c.getString(3));
                ((TextView) view.findViewById(R.id.etTratamiento)).setText(c.getString(4));
                ((TextView) view.findViewById(R.id.edSiguienteVisita)).setText(c.getString(5));


            } else {
                System.out.println("No existe información del cliente");
            }
            c.close();
        } catch (Exception ex) {
            System.out.println("Exception: " + ex);
        }



        builder.setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });

        builder.setView(view);
        builder.show();
    }
}
