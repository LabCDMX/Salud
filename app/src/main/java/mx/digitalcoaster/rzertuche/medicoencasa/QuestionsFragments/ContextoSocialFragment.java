package mx.digitalcoaster.rzertuche.medicoencasa.QuestionsFragments;

import android.content.Context;
import android.database.Cursor;
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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity;
import mx.digitalcoaster.rzertuche.medicoencasa.DataBase.DataBaseHelper;
import mx.digitalcoaster.rzertuche.medicoencasa.R;
import mx.digitalcoaster.rzertuche.medicoencasa.Utils.SharedPreferences;

import static mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity.inicio;
import static mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity.registros;
import static mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity.seguimiento;
import static mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity.sincronizacion;
import static mx.digitalcoaster.rzertuche.medicoencasa.QuestionsFragments.QuestionsContextElectro.listElectro;


public class ContextoSocialFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    SharedPreferences sharedPreferences;

    EditText nombre, apellidoP, estado, municipio, transporte, recreacion, estres, caracteristicas, piso, techo,verduras, fruta, leguminosa,carne,cereal,pan,chatarra;
    ImageButton next;

    ImageButton btnedit,btnedit1,btnedit2,btnedit3,btnedit4,btnedit5,btnedit6,btnedit7,btnedit8,btnedit9,btnedit10,btnedit11,btnedit12,btnedit13,btnedit14,btnedit15,btnedit16,btnedit17;
    ImageButton btnedit18,btnedit19,btnedit20;


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
        return inflater.inflate(R.layout.fragment_contexto_social, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        nombre = (EditText) getActivity().findViewById(R.id.textViewNombre);
        apellidoP = (EditText) getActivity().findViewById(R.id.textViewApellidoP);
        estado = (EditText) getActivity().findViewById(R.id.textViewEstado);
        municipio = (EditText) getActivity().findViewById(R.id.textCantidades);
        transporte = (EditText) getActivity().findViewById(R.id.textViewTransporte);
        recreacion = (EditText) getActivity().findViewById(R.id.textViewRecreacion);
        estres = (EditText) getActivity().findViewById(R.id.textViewEstres);
        caracteristicas = (EditText) getActivity().findViewById(R.id.textViewCaracteristicas);
        piso = (EditText) getActivity().findViewById(R.id.textViewPiso);
        techo = (EditText) getActivity().findViewById(R.id.textViewTecho);
        verduras = (EditText) getActivity().findViewById(R.id.textViewVerduras);
        fruta = (EditText) getActivity().findViewById(R.id.textViewFruta);
        leguminosa = (EditText) getActivity().findViewById(R.id.textViewLeguminosas);
        carne = (EditText) getActivity().findViewById(R.id.textViewCarne);
        cereal = (EditText) getActivity().findViewById(R.id.textViewCereales);
        chatarra = (EditText) getActivity().findViewById(R.id.textViewChatarra);
        pan = (EditText) getActivity().findViewById(R.id.textViewPan);


        btnedit = (ImageButton) getActivity().findViewById(R.id.btn_edit);
        btnedit1 = (ImageButton) getActivity().findViewById(R.id.btn_edit2);
        btnedit2 = (ImageButton) getActivity().findViewById(R.id.btn_edit3);
        btnedit3 = (ImageButton) getActivity().findViewById(R.id.btn_edit8);
        btnedit4 = (ImageButton) getActivity().findViewById(R.id.btn_edit9);
        btnedit5 = (ImageButton) getActivity().findViewById(R.id.btn_edit4);
        btnedit6 = (ImageButton) getActivity().findViewById(R.id.btn_edit10);
        btnedit7 = (ImageButton) getActivity().findViewById(R.id.btn_edit11);
        btnedit8 = (ImageButton) getActivity().findViewById(R.id.btn_edit12);
        btnedit9 = (ImageButton) getActivity().findViewById(R.id.btn_edit5);
        btnedit10 = (ImageButton) getActivity().findViewById(R.id.btn_edit13);
        btnedit11 = (ImageButton) getActivity().findViewById(R.id.btn_edit14);
        btnedit12 = (ImageButton) getActivity().findViewById(R.id.btn_edit15);
        btnedit13 = (ImageButton) getActivity().findViewById(R.id.btn_edit16);
        btnedit14 = (ImageButton) getActivity().findViewById(R.id.btn_edit17);
        btnedit15 = (ImageButton) getActivity().findViewById(R.id.btn_edit18);
        btnedit16 = (ImageButton) getActivity().findViewById(R.id.btn_edit19);


        next = (ImageButton) getActivity().findViewById(R.id.next);

        nombre.setSingleLine(true);

        //Obtencion de datos del sharedPreferences
        sharedPreferences = SharedPreferences.getInstance();

        nombre.setText(sharedPreferences.getStringData("Escolaridad"));
        apellidoP.setText(sharedPreferences.getStringData("TiempoEscuela"));
        transporte.setText(sharedPreferences.getStringData("Transporte"));
        recreacion.setText(sharedPreferences.getStringData("Recreacion"));
        estres.setText(sharedPreferences.getStringData("Estres"));
        caracteristicas.setText(sharedPreferences.getStringData("Caracteristicas"));
        piso.setText(sharedPreferences.getStringData("Piso"));
        techo.setText(sharedPreferences.getStringData("Techo"));
        verduras.setText(sharedPreferences.getStringData("Verduras"));
        fruta.setText(sharedPreferences.getStringData("Fruta"));
        leguminosa.setText(sharedPreferences.getStringData("Leguminosa"));
        pan.setText(sharedPreferences.getStringData("Pan"));
        carne.setText(sharedPreferences.getStringData("Carne"));
        chatarra.setText(sharedPreferences.getStringData("Chatarra"));
        cereal.setText(sharedPreferences.getStringData("Cereal"));



        String cadena = new String();
        listElectro = quitarRepedidos(listElectro);
        for (String object: listElectro) {
            cadena+= "-" + object + "\n";
        }


        estado.setText(cadena);
        municipio.setText(sharedPreferences.getStringData("Alimentos"));

        lockEditText();

        btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnEditar(nombre);
            }
        });
        btnedit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnEditar(apellidoP);
            }
        });
        btnedit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnEditar(transporte);

            }
        });
        btnedit3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnEditar(recreacion);

            }
        });
        btnedit4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnEditar(estres);
            }
        });
        btnedit5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnEditar(estado);
            }
        });
        btnedit6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnEditar(caracteristicas);

            }
        });
        btnedit7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnEditar(piso);

            }
        });
        btnedit8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnEditar(techo);
            }
        });
        btnedit9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnEditar(municipio);
            }
        });
        btnedit10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnEditar(verduras);

            }
        });
        btnedit11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnEditar(fruta);

            }
        });
        btnedit12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnEditar(leguminosa);
            }
        });
        btnedit13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnEditar(cereal);
            }
        });
        btnedit14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnEditar(pan);

            }
        });
        btnedit15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnEditar(chatarra);

            }
        });
        btnedit16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnEditar(carne);
            }
        });





        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((MainActivity)getActivity()).stopCronometro();
                sharedPreferences.clearPreferences();
                ((MainActivity)getActivity()).fragmentSucceded();
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

    public void lockEditText(){
        nombre.setEnabled(false);
        apellidoP.setEnabled(false);
        estado.setEnabled(false);
        municipio.setEnabled(false);
    }

    public void focusViews(){
        nombre.setEnabled(true);
        apellidoP.setEnabled(true);
        estado.setEnabled(true);
        municipio.setEnabled(true);
    }


    public void blockListeners(){
        inicio.setEnabled(false);
        registros.setEnabled(false);
        seguimiento.setEnabled(false);
        sincronizacion.setEnabled(false);
    }

    public void btnEditar(final EditText nombre){

        nombre.setText("");
        nombre.setEnabled(true);
        nombre.requestFocus();
        InputMethodManager imm = (InputMethodManager) // con esto abres el teclado despues de ubicar el foco en tu editText
                getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(nombre, InputMethodManager.SHOW_IMPLICIT);

        final InputMethodManager hideKeyboard = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        hideKeyboard.hideSoftInputFromWindow(getView().getWindowToken(), 0);

    }

    public List<String> quitarRepedidos(List<String> repeditos){
        Set<String> hs = new HashSet<>();
        hs.addAll(repeditos);
        repeditos.clear();
        repeditos.addAll(hs);

        return repeditos;
    }




}
