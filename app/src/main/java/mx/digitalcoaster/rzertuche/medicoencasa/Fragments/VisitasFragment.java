package mx.digitalcoaster.rzertuche.medicoencasa.Fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
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
    public static String numeroVisita;
    public static String curpPatient;

    private TextView nombre, diagnostico, tratamiento,expediente;
    ImageView status;
    ImageButton datosGenerales, historiaClinica;



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
        numeroVisita = sharedPreferences.getStringData("numero_visita");
        curpPatient = sharedPreferences.getStringData("curpSeguimiento");

        status = (ImageView) getActivity().findViewById(R.id.status);
        nombre = (TextView) getActivity().findViewById(R.id.tvNombreItem);
        diagnostico = (TextView) getActivity().findViewById(R.id.textViewDiagnostico);
        tratamiento = (TextView) getActivity().findViewById(R.id.textViewTratamiento);
        expediente = (TextView) getActivity().findViewById(R.id.expediente);

        datosGenerales = (ImageButton) getActivity().findViewById(R.id.datos_generales);
        historiaClinica = (ImageButton) getActivity().findViewById(R.id.historia_clinica);

        lista = (GridView) getActivity().findViewById(R.id.gridview);
        items = new ArrayList<>();
        getProductos();
        Log.e("SUPERMEGAVISITA",sharedPreferences.getStringData("numero_visita"));
        lista.setAdapter(new ItemVisitaAdapter(getActivity().getApplicationContext(), items));
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View v, int position, long arg3) {
                ItemVisita selectedUser = items.get(position);
                String numero_visita = selectedUser.getNumero_visita();
                viewAlertDialog(numero_visita);

            }
        });


        String statusImage = sharedPreferences.getStringData("ImageStatus");
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

        datosGenerales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                viewAlertDialogDatos();


            }
        });

        historiaClinica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               viewAlertDialogHistoria();


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
                    sharedPreferences.setStringData("Nombre",c.getString(1));
                    sharedPreferences.setStringData("numero_visita",c.getString(6));



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


    private void viewAlertDialog(String numeroVisita) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(false);
        final LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.alert_visita, null);

        db = getActivity().openOrCreateDatabase(DataBaseDB.DB_NAME, android.content.Context.MODE_PRIVATE, null);
        //-------------------------- Obtener información del cliente ---------------------
        try {
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


    private void viewAlertDialogDatos() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(false);
        final LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.alert_datos_generales, null);

        db = getActivity().openOrCreateDatabase(DataBaseDB.DB_NAME, android.content.Context.MODE_PRIVATE, null);
        //-------------------------- Obtener información del cliente ---------------------
        try {
            c = db.rawQuery("SELECT * FROM " + DataBaseDB.TABLE_NAME_PACIENTES_SINCRONIZAR + " WHERE " +
                    DataBaseDB.PACIENTES_SINCRONIZAR_NOMBRE + " ='"+nombrePatient+"' AND "+
                    DataBaseDB.PACIENTES_SINCRONIZAR_CURP+ " = '"+curpPatient+"'", null);


            if (c.moveToFirst()) {

                ((TextView) view.findViewById(R.id.textViewCURP)).setText(c.getString(2));
                ((TextView) view.findViewById(R.id.textViewNombre)).setText(c.getString(1));
                ((TextView) view.findViewById(R.id.textViewApellidoP)).setText(c.getString(4));
                ((TextView) view.findViewById(R.id.textViewApellidoM)).setText(c.getString(5));
                ((TextView) view.findViewById(R.id.textViewEdad)).setText(c.getString(24));
                ((TextView) view.findViewById(R.id.textViewFechaNac)).setText(c.getString(6));
                ((TextView) view.findViewById(R.id.textViewEstadoNac)).setText(c.getString(7));
                ((TextView) view.findViewById(R.id.textViewSexo)).setText(c.getString(8));
                ((TextView) view.findViewById(R.id.textViewNacOrigen)).setText(c.getString(9));

                //Domiciliarios
                ((TextView) view.findViewById(R.id.textViewCP)).setText(c.getString(21));
                ((TextView) view.findViewById(R.id.textViewEstado)).setText(c.getString(10));
                ((TextView) view.findViewById(R.id.textViewMunicipio)).setText(c.getString(11));
                ((TextView) view.findViewById(R.id.textViewLocalidad)).setText(c.getString(12));
                ((TextView) view.findViewById(R.id.textViewEstadoCivil)).setText(c.getString(13));
                ((TextView) view.findViewById(R.id.textViewOcupacion)).setText(c.getString(14));
                ((TextView) view.findViewById(R.id.textViewTelefonoFijo)).setText(c.getString(17));
                ((TextView) view.findViewById(R.id.textViewTelefonoCel)).setText(c.getString(18));
                ((TextView) view.findViewById(R.id.textViewEmail)).setText(c.getString(19));


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


    private void viewAlertDialogHistoria() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(false);
        final LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.alert_historia_clinica, null);

        db = getActivity().openOrCreateDatabase(DataBaseDB.DB_NAME, android.content.Context.MODE_PRIVATE, null);
        //-------------------------- Obtener información del cliente ---------------------
        try {
            c = db.rawQuery("SELECT * FROM " + DataBaseDB.TABLE_NAME_PACIENTES_SINCRONIZAR_HISTORIC + " WHERE " +
                    DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_NOMBRE + " ='"+nombrePatient+"' AND "+
                    DataBaseDB.PACIENTES_SINCRONIZAR_HISTORIC_CURP+ " = '"+curpPatient+"'", null);


            if (c.moveToFirst()) {

                ((TextView) view.findViewById(R.id.textViewCardioVas)).setText(c.getString(17));
                ((TextView) view.findViewById(R.id.textViewHTA)).setText(c.getString(18));
                ((TextView) view.findViewById(R.id.textViewDiabetes)).setText(c.getString(19));
                ((TextView) view.findViewById(R.id.textViewDisli)).setText(c.getString(20));
                ((TextView) view.findViewById(R.id.textViewObesidad)).setText(c.getString(21));
                ((TextView) view.findViewById(R.id.textViewCerebroVas)).setText(c.getString(22));
                ((TextView) view.findViewById(R.id.tvCerebro)).setText(c.getString(23));
                //LA OTRA PARTE DEL MODAL
                ((TextView) view.findViewById(R.id.textViewRespiratorio)).setText(c.getString(29));
                ((TextView) view.findViewById(R.id.textViewCardio)).setText(c.getString(30));
                ((TextView) view.findViewById(R.id.textViewDigestivo)).setText(c.getString(31));
                ((TextView) view.findViewById(R.id.textViewUrinario)).setText(c.getString(32));
                ((TextView) view.findViewById(R.id.textViewReproductor)).setText(c.getString(33));
                ((TextView) view.findViewById(R.id.textViewHemolinfatico)).setText(c.getString(34));
                ((TextView) view.findViewById(R.id.textViewEndocrino)).setText(c.getString(35));
                ((TextView) view.findViewById(R.id.textViewNervioso)).setText(c.getString(36));
                ((TextView) view.findViewById(R.id.textViewPiel)).setText(c.getString(38));
                ((TextView) view.findViewById(R.id.textViewHabitus)).setText(c.getString(39));
                ((TextView) view.findViewById(R.id.textViewCabeza)).setText(c.getString(40));
                ((TextView) view.findViewById(R.id.textViewCuello)).setText(c.getString(41));
                ((TextView) view.findViewById(R.id.textViewTorax)).setText("");
                ((TextView) view.findViewById(R.id.textViewAbdomen)).setText(c.getString(42));
                ((TextView) view.findViewById(R.id.textViewExploracionGinec)).setText(c.getString(43));
                ((TextView) view.findViewById(R.id.textViewExtremidades)).setText(c.getString(44));
                ((TextView) view.findViewById(R.id.textViewColumna)).setText(c.getString(45));
                ((TextView) view.findViewById(R.id.textViewNeurologica)).setText(c.getString(46));
                ((TextView) view.findViewById(R.id.textViewGenitales)).setText(c.getString(47));

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
