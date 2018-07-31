package mx.digitalcoaster.rzertuche.medicoencasa.QuestionsFragments;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;

import org.json.JSONException;

import mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity;
import mx.digitalcoaster.rzertuche.medicoencasa.DataBase.DataBaseDB;
import mx.digitalcoaster.rzertuche.medicoencasa.DataBase.DataBaseHelper;
import mx.digitalcoaster.rzertuche.medicoencasa.Utils.SharedPreferences;
import mx.digitalcoaster.rzertuche.medicoencasa.R;

import static mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity.inicio;
import static mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity.registros;
import static mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity.seguimiento;
import static mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity.sincronizacion;


public class DatosGeneralesFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    SharedPreferences sharedPreferences;

    EditText nombre, apellidoP, apellidoM, estado, municipio, localidad, fechaNac, estadoNac,sexo,nac,estadociv,ocupacion,telFijo,telCelular,email,curp;
    ImageButton btnedit16, btnedit,btnedit2,btnedit3,btnedit4,btnedit5,btnedit6,btnedit7, btnedit8,next,back;
    ImageButton btnedit9, btnedit10,btnedit11,btnedit12,btnedit13,btnedit14,btnedit15;

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
        return inflater.inflate(R.layout.fragment_datos_generales, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        nombre = (EditText) getActivity().findViewById(R.id.textViewNombre);
        apellidoP = (EditText) getActivity().findViewById(R.id.textViewApellidoP);
        apellidoM = (EditText) getActivity().findViewById(R.id.textViewApellidoM);
        estado = (EditText) getActivity().findViewById(R.id.textViewEstado);
        municipio = (EditText) getActivity().findViewById(R.id.textViewMunicipio);
        localidad = (EditText) getActivity().findViewById(R.id.textViewLocalidad);
        fechaNac = (EditText) getActivity().findViewById(R.id.textViewFechaNac);
        estadoNac = (EditText) getActivity().findViewById(R.id.textViewEstadoNac);
        sexo = (EditText) getActivity().findViewById(R.id.textViewSexo);
        nac = (EditText) getActivity().findViewById(R.id.textViewNacOrigen);
        estadociv = (EditText) getActivity().findViewById(R.id.textViewEstadoCivil);
        ocupacion = (EditText) getActivity().findViewById(R.id.textViewOcupacion);
        telFijo = (EditText) getActivity().findViewById(R.id.textViewTelefonoFijo);
        telCelular = (EditText) getActivity().findViewById(R.id.textViewTelefonoCel);
        email = (EditText) getActivity().findViewById(R.id.textViewEmail);
        curp = (EditText) getActivity().findViewById(R.id.textViewCURP);



        //Personales editText
        btnedit = (ImageButton) getActivity().findViewById(R.id.btn_edit16);
        btnedit2 = (ImageButton) getActivity().findViewById(R.id.btn_edit);
        btnedit3 = (ImageButton) getActivity().findViewById(R.id.btn_edit2);
        btnedit4 = (ImageButton) getActivity().findViewById(R.id.btn_edit3);
        btnedit5 = (ImageButton) getActivity().findViewById(R.id.btn_edit7);
        btnedit6 = (ImageButton) getActivity().findViewById(R.id.btn_edit8);
        btnedit7 = (ImageButton) getActivity().findViewById(R.id.btn_edit9);
        btnedit8 = (ImageButton) getActivity().findViewById(R.id.btn_edit10);


        //Domiciliarios
        btnedit9 = (ImageButton) getActivity().findViewById(R.id.btn_edit4);
        btnedit10 = (ImageButton) getActivity().findViewById(R.id.btn_edit5);
        btnedit11 = (ImageButton) getActivity().findViewById(R.id.btn_edit6);
        btnedit12 = (ImageButton) getActivity().findViewById(R.id.btn_edit11);
        btnedit13 = (ImageButton) getActivity().findViewById(R.id.btn_edit12);
        btnedit14 = (ImageButton) getActivity().findViewById(R.id.btn_edit13);
        btnedit15 = (ImageButton) getActivity().findViewById(R.id.btn_edit14);
        btnedit16 = (ImageButton) getActivity().findViewById(R.id.btn_edit15);



        next = (ImageButton) getActivity().findViewById(R.id.next);
        back = (ImageButton) getActivity().findViewById(R.id.back);


        nombre.setSingleLine(true);

        //Obtencion de datos del sharedPreferences
        sharedPreferences = SharedPreferences.getInstance();

        curp.setText(sharedPreferences.getStringData("CURP"));

        nombre.setText(sharedPreferences.getStringData("NombrePatient"));
        apellidoP.setText(sharedPreferences.getStringData("ApellidoP"));
        apellidoM.setText(sharedPreferences.getStringData("ApellidoM"));

        estado.setText(sharedPreferences.getStringData("Estado"));
        municipio.setText(sharedPreferences.getStringData("Municipio"));
        localidad.setText(sharedPreferences.getStringData("Localidad"));

        fechaNac.setText(sharedPreferences.getStringData("FechaNac"));
        estadoNac.setText(sharedPreferences.getStringData("EstadoNac"));
        sexo.setText(sharedPreferences.getStringData("Sexo"));

        nac.setText(sharedPreferences.getStringData("Nac"));
        estadociv.setText(sharedPreferences.getStringData("EstadoCiv"));
        ocupacion.setText(sharedPreferences.getStringData("Ocupacion"));

        telFijo.setText(sharedPreferences.getStringData("TelFijo"));
        telCelular.setText(sharedPreferences.getStringData("TelCel"));
        email.setText(sharedPreferences.getStringData("Email"));


        btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnEditar(curp);
            }
        });

        btnedit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnEditar(nombre);
            }
        });

        btnedit3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnEditar(apellidoP);
            }
        });

        btnedit4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnEditar(apellidoM);
            }
        });


        btnedit5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnEditar(fechaNac);
            }
        });

        btnedit6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnEditar(estadoNac);
            }
        });

        btnedit7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnEditar(sexo);
            }
        });

        btnedit8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnEditar(nac);
            }
        });



        btnedit9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnEditar(estado);
            }
        });
        btnedit10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnEditar(municipio);
            }
        });
        btnedit11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnEditar(localidad);
            }
        });
        btnedit12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnEditar(estadociv);
            }
        });

        btnedit13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnEditar(ocupacion);
            }
        });

        btnedit14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnEditar(telFijo);
            }
        });
        btnedit15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnEditar(telCelular);
            }
        });
        btnedit16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnEditar(email);
            }
        });




        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                db = getActivity().openOrCreateDatabase(DataBaseDB.DB_NAME, Context.MODE_PRIVATE ,null);

                String nameComplet = sharedPreferences.getStringData("NombrePatient") + " " +
                        " " + sharedPreferences.getStringData("ApellidoP")  +
                        " " + sharedPreferences.getStringData("ApellidoM");
                String curp = sharedPreferences.getStringData("CURP");

                String direccion = sharedPreferences.getStringData("Direccion");




                        /*------------------------- Revisar si existe ------------------------*/
                c = db.rawQuery("SELECT * FROM " + DataBaseDB.TABLE_NAME_PACIENTES, null);
                try {
                    if(c.moveToFirst()) {
                        ContentValues values = new ContentValues();


                        values.put(DataBaseDB.PACIENTES_CURP, curp);
                        values.put(DataBaseDB.PACIENTES_AP_PATERNO, sharedPreferences.getStringData("ApellidoP"));
                        values.put(DataBaseDB.PACIENTES_AP_MATERNO, sharedPreferences.getStringData("ApellidoM"));
                        values.put(DataBaseDB.PACIENTES_NOMBRE, sharedPreferences.getStringData("NombrePatient"));
                        values.put(DataBaseDB.PACIENTES_FECHA_NACIMIENTO, sharedPreferences.getStringData("FechaNac"));
                        values.put(DataBaseDB.PACIENTES_ESTADO_NACIMIENTO, sharedPreferences.getStringData("EstadoNac"));
                        values.put(DataBaseDB.PACIENTES_SEXO, sharedPreferences.getStringData("Sexo"));
                        values.put(DataBaseDB.PACIENTES_CALLE, sharedPreferences.getStringData("Calle")+sharedPreferences.getStringData("No Ext.")+sharedPreferences.getStringData("No int"));
                        values.put(DataBaseDB.PACIENTES_NACIONALIDAD, sharedPreferences.getStringData("Nac"));
                        values.put(DataBaseDB.PACIENTES_ESTADO, sharedPreferences.getStringData("Estado"));
                        values.put(DataBaseDB.PACIENTES_MUNICIPIO, sharedPreferences.getStringData("Municipio"));
                        values.put(DataBaseDB.PACIENTES_POBLACION, sharedPreferences.getStringData("Poblacion"));
                        values.put(DataBaseDB.PACIENTES_COLONIA, sharedPreferences.getStringData("Localidad"));
                        values.put(DataBaseDB.PACIENTES_ESTADO_CIVIL, sharedPreferences.getStringData("EstadoCiv"));
                        values.put(DataBaseDB.PACIENTES_OCUPACION, sharedPreferences.getStringData("Ocupacion"));
                        values.put(DataBaseDB.PACIENTES_DERECHO, sharedPreferences.getStringData("DerechoHabi"));
                        values.put(DataBaseDB.PACIENTES_TEL_FIJO, sharedPreferences.getStringData("TelFijo"));
                        values.put(DataBaseDB.PACIENTES_CEL, sharedPreferences.getStringData("TelCel"));
                        values.put(DataBaseDB.PACIENTES_EMAIL, sharedPreferences.getStringData("Email"));


                        db.insert(DataBaseDB.TABLE_NAME_PACIENTES, null, values);
                        System.out.println("Productos insertados correctamente");
                    }
                    else {
                        ContentValues values = new ContentValues();



                        values.put(DataBaseDB.PACIENTES_CURP, curp);
                        values.put(DataBaseDB.PACIENTES_AP_PATERNO, sharedPreferences.getStringData("ApellidoP"));
                        values.put(DataBaseDB.PACIENTES_AP_MATERNO, sharedPreferences.getStringData("ApellidoM"));
                        values.put(DataBaseDB.PACIENTES_NOMBRE, sharedPreferences.getStringData("NombrePatient"));
                        values.put(DataBaseDB.PACIENTES_FECHA_NACIMIENTO, sharedPreferences.getStringData("FechaNac"));
                        values.put(DataBaseDB.PACIENTES_ESTADO_NACIMIENTO, sharedPreferences.getStringData("EstadoNac"));
                        values.put(DataBaseDB.PACIENTES_SEXO, sharedPreferences.getStringData("Sexo"));
                        values.put(DataBaseDB.PACIENTES_CALLE, sharedPreferences.getStringData("Calle")+sharedPreferences.getStringData("No Ext.")+sharedPreferences.getStringData("No int"));
                        values.put(DataBaseDB.PACIENTES_NACIONALIDAD, sharedPreferences.getStringData("Nac"));
                        values.put(DataBaseDB.PACIENTES_ESTADO, sharedPreferences.getStringData("Estado"));
                        values.put(DataBaseDB.PACIENTES_MUNICIPIO, sharedPreferences.getStringData("Municipio"));
                        values.put(DataBaseDB.PACIENTES_POBLACION, sharedPreferences.getStringData("Poblacion"));
                        values.put(DataBaseDB.PACIENTES_COLONIA, sharedPreferences.getStringData("Localidad"));
                        values.put(DataBaseDB.PACIENTES_ESTADO_CIVIL, sharedPreferences.getStringData("EstadoCiv"));
                        values.put(DataBaseDB.PACIENTES_OCUPACION, sharedPreferences.getStringData("Ocupacion"));
                        values.put(DataBaseDB.PACIENTES_DERECHO, sharedPreferences.getStringData("DerechoHabi"));
                        values.put(DataBaseDB.PACIENTES_TEL_FIJO, sharedPreferences.getStringData("TelFijo"));
                        values.put(DataBaseDB.PACIENTES_CEL, sharedPreferences.getStringData("TelCel"));
                        values.put(DataBaseDB.PACIENTES_EMAIL, sharedPreferences.getStringData("Email"));



                        db.insert(DataBaseDB.TABLE_NAME_PACIENTES, null, values);
                        System.out.println("Productos insertados correctamente");

                    }
                    c.close();
                } catch (SQLException ex) {
                    System.out.println("Error al insertar productos: " + ex);
                }



                /*------------------------- Revisar si existe ------------------------*/
                c = db.rawQuery("SELECT * FROM " + DataBaseDB.TABLE_NAME_PACIENTES_SINCRONIZAR, null);
                try {
                    if(c.moveToFirst()) {
                        ContentValues values = new ContentValues();


                        values.put(DataBaseDB.PACIENTES_SINCRONIZAR_CURP, curp);
                        values.put(DataBaseDB.PACIENTES_SINCRONIZAR_AP_PATERNO, sharedPreferences.getStringData("ApellidoP"));
                        values.put(DataBaseDB.PACIENTES_SINCRONIZAR_AP_MATERNO, sharedPreferences.getStringData("ApellidoM"));
                        values.put(DataBaseDB.PACIENTES_SINCRONIZAR_NOMBRE, sharedPreferences.getStringData("NombrePatient"));
                        values.put(DataBaseDB.PACIENTES_SINCRONIZAR_FECHA_NACIMIENTO, sharedPreferences.getStringData("FechaNac"));
                        values.put(DataBaseDB.PACIENTES_SINCRONIZAR_ESTADO_NACIMIENTO, sharedPreferences.getStringData("EstadoNac"));
                        values.put(DataBaseDB.PACIENTES_SINCRONIZAR_SEXO, sharedPreferences.getStringData("Sexo"));
                        values.put(DataBaseDB.PACIENTES_SINCRONIZAR_CALLE, sharedPreferences.getStringData("Calle")+sharedPreferences.getStringData("No Ext.")+sharedPreferences.getStringData("No int"));
                        values.put(DataBaseDB.PACIENTES_SINCRONIZAR_NACIONALIDAD, sharedPreferences.getStringData("Nac"));
                        values.put(DataBaseDB.PACIENTES_SINCRONIZAR_ESTADO, sharedPreferences.getStringData("Estado"));
                        values.put(DataBaseDB.PACIENTES_SINCRONIZAR_MUNICIPIO, sharedPreferences.getStringData("Municipio"));
                        values.put(DataBaseDB.PACIENTES_SINCRONIZAR_POBLACION, sharedPreferences.getStringData("Poblacion"));
                        values.put(DataBaseDB.PACIENTES_SINCRONIZAR_COLONIA, sharedPreferences.getStringData("Localidad"));
                        values.put(DataBaseDB.PACIENTES_SINCRONIZAR_ESTADO_CIVIL, sharedPreferences.getStringData("EstadoCiv"));
                        values.put(DataBaseDB.PACIENTES_SINCRONIZAR_OCUPACION, sharedPreferences.getStringData("Ocupacion"));
                        values.put(DataBaseDB.PACIENTES_SINCRONIZAR_DERECHO, sharedPreferences.getStringData("DerechoHabi"));
                        values.put(DataBaseDB.PACIENTES_SINCRONIZAR_TEL_FIJO, sharedPreferences.getStringData("TelFijo"));
                        values.put(DataBaseDB.PACIENTES_SINCRONIZAR_CEL, sharedPreferences.getStringData("TelCel"));
                        values.put(DataBaseDB.PACIENTES_SINCRONIZAR_EMAIL, sharedPreferences.getStringData("Email"));
                        values.put(DataBaseDB.PACIENTES_SINCRONIZAR_EDAD, sharedPreferences.getStringData("Edad"));
                        values.put(DataBaseDB.PACIENTES_SINCRONIZAR_CODIGO, sharedPreferences.getStringData("CP"));



                        db.insert(DataBaseDB.TABLE_NAME_PACIENTES_SINCRONIZAR, null, values);
                        System.out.println("Productos insertados correctamente");
                    }
                    else {
                        ContentValues values = new ContentValues();



                        values.put(DataBaseDB.PACIENTES_SINCRONIZAR_CURP, curp);
                        values.put(DataBaseDB.PACIENTES_SINCRONIZAR_AP_PATERNO, sharedPreferences.getStringData("ApellidoP"));
                        values.put(DataBaseDB.PACIENTES_SINCRONIZAR_AP_MATERNO, sharedPreferences.getStringData("ApellidoM"));
                        values.put(DataBaseDB.PACIENTES_SINCRONIZAR_NOMBRE, sharedPreferences.getStringData("NombrePatient"));
                        values.put(DataBaseDB.PACIENTES_SINCRONIZAR_FECHA_NACIMIENTO, sharedPreferences.getStringData("FechaNac"));
                        values.put(DataBaseDB.PACIENTES_SINCRONIZAR_ESTADO_NACIMIENTO, sharedPreferences.getStringData("EstadoNac"));
                        values.put(DataBaseDB.PACIENTES_SINCRONIZAR_SEXO, sharedPreferences.getStringData("Sexo"));
                        values.put(DataBaseDB.PACIENTES_SINCRONIZAR_CALLE, sharedPreferences.getStringData("Calle")+sharedPreferences.getStringData("No Ext.")+sharedPreferences.getStringData("No int"));
                        values.put(DataBaseDB.PACIENTES_SINCRONIZAR_NACIONALIDAD, sharedPreferences.getStringData("Nac"));
                        values.put(DataBaseDB.PACIENTES_SINCRONIZAR_ESTADO, sharedPreferences.getStringData("Estado"));
                        values.put(DataBaseDB.PACIENTES_SINCRONIZAR_MUNICIPIO, sharedPreferences.getStringData("Municipio"));
                        values.put(DataBaseDB.PACIENTES_SINCRONIZAR_POBLACION, sharedPreferences.getStringData("Poblacion"));
                        values.put(DataBaseDB.PACIENTES_SINCRONIZAR_COLONIA, sharedPreferences.getStringData("Localidad"));
                        values.put(DataBaseDB.PACIENTES_SINCRONIZAR_ESTADO_CIVIL, sharedPreferences.getStringData("EstadoCiv"));
                        values.put(DataBaseDB.PACIENTES_SINCRONIZAR_OCUPACION, sharedPreferences.getStringData("Ocupacion"));
                        values.put(DataBaseDB.PACIENTES_SINCRONIZAR_DERECHO, sharedPreferences.getStringData("DerechoHabi"));
                        values.put(DataBaseDB.PACIENTES_SINCRONIZAR_TEL_FIJO, sharedPreferences.getStringData("TelFijo"));
                        values.put(DataBaseDB.PACIENTES_SINCRONIZAR_CEL, sharedPreferences.getStringData("TelCel"));
                        values.put(DataBaseDB.PACIENTES_SINCRONIZAR_EMAIL, sharedPreferences.getStringData("Email"));
                        values.put(DataBaseDB.PACIENTES_SINCRONIZAR_EDAD, sharedPreferences.getStringData("Edad"));
                        values.put(DataBaseDB.PACIENTES_SINCRONIZAR_CODIGO, sharedPreferences.getStringData("CP"));


                        db.insert(DataBaseDB.TABLE_NAME_PACIENTES_SINCRONIZAR, null, values);

                        System.out.println("Productos insertados correctamente");

                    }
                    c.close();
                } catch (SQLException ex) {
                    System.out.println("Error al insertar productos: " + ex);
                }

                db.close();






                ((MainActivity)getActivity()).fragmentQuestionsEsc();


            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ((MainActivity)getActivity()).questionDomThree();

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


    public void btnEditar(final EditText nombre){

        nombre.setText("");
        nombre.setEnabled(true);
        nombre.requestFocus();
        InputMethodManager imm = (InputMethodManager) // con esto abres el teclado despues de ubicar el foco en tu editText
                getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(nombre, InputMethodManager.SHOW_IMPLICIT);

    }

}
