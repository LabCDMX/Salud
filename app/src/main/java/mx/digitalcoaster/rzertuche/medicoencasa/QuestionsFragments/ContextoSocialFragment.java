package mx.digitalcoaster.rzertuche.medicoencasa.QuestionsFragments;

import android.content.Context;
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
    ImageButton btnedit,btnedit1,btnedit3,btnedit4,next;




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
        btnedit3 = (ImageButton) getActivity().findViewById(R.id.btn_edit4);
        btnedit4 = (ImageButton) getActivity().findViewById(R.id.btn_edit5);

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
        String listElectroAux= borrarRepetidos(listElectro).toString().replace("[","").replace("]","").replace(",","\n");



        for (String object: listElectro) {
            cadena+= "-" + object + "\n";
        }


        Log.e("CADENA",cadena);
        estado.setText(cadena);
        municipio.setText(sharedPreferences.getStringData("Alimentos"));

        lockEditText();

        btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nombre.setText("");
                nombre.setEnabled(true);
                nombre.requestFocus();
                InputMethodManager imm = (InputMethodManager) // con esto abres el teclado despues de ubicar el foco en tu editText
                        getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(nombre, InputMethodManager.SHOW_IMPLICIT);
            }
        });
        nombre.setOnKeyListener(new View.OnKeyListener()
        {
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                if (event.getAction() == KeyEvent.ACTION_DOWN)
                {
                    switch (keyCode)
                    {
                        case KeyEvent.KEYCODE_DPAD_CENTER:
                        case KeyEvent.KEYCODE_ENTER:
                            Log.e("DETECTAR ENTER", "Works super cute");
                            nombre.setEnabled(false);
                            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);

                            return true;
                        default:
                            break;
                    }
                }
                return false;
            }
        });


        btnedit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apellidoP.setText("");
                apellidoP.setEnabled(true);
                apellidoP.requestFocus();
                InputMethodManager imm = (InputMethodManager) // con esto abres el teclado despues de ubicar el foco en tu editText
                        getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(apellidoP, InputMethodManager.SHOW_IMPLICIT);
            }
        });
        apellidoP.setOnKeyListener(new View.OnKeyListener()
        {
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                if (event.getAction() == KeyEvent.ACTION_DOWN)
                {
                    switch (keyCode)
                    {
                        case KeyEvent.KEYCODE_DPAD_CENTER:
                        case KeyEvent.KEYCODE_ENTER:
                            Log.e("DETECTAR ENTER", "Works super cute");
                            apellidoP.setEnabled(false);
                            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);

                            return true;
                        default:
                            break;
                    }
                }
                return false;
            }
        });



        btnedit3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                estado.setText("");
                estado.setEnabled(true);
                estado.requestFocus();
                InputMethodManager imm = (InputMethodManager) // con esto abres el teclado despues de ubicar el foco en tu editText
                        getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(estado, InputMethodManager.SHOW_IMPLICIT);
            }
        });
        estado.setOnKeyListener(new View.OnKeyListener()
        {
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                if (event.getAction() == KeyEvent.ACTION_DOWN)
                {
                    switch (keyCode)
                    {
                        case KeyEvent.KEYCODE_DPAD_CENTER:
                        case KeyEvent.KEYCODE_ENTER:
                            Log.e("DETECTAR ENTER", "Works super cute");
                            estado.setEnabled(false);
                            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);

                            return true;
                        default:
                            break;
                    }
                }
                return false;
            }
        });


        btnedit4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                municipio.setText("");
                municipio.setEnabled(true);
                municipio.requestFocus();
                InputMethodManager imm = (InputMethodManager) // con esto abres el teclado despues de ubicar el foco en tu editText
                        getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(municipio, InputMethodManager.SHOW_IMPLICIT);
            }
        });
        municipio.setOnKeyListener(new View.OnKeyListener()
        {
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                if (event.getAction() == KeyEvent.ACTION_DOWN)
                {
                    switch (keyCode)
                    {
                        case KeyEvent.KEYCODE_DPAD_CENTER:
                        case KeyEvent.KEYCODE_ENTER:
                            Log.e("DETECTAR ENTER", "Works super cute");
                            municipio.setEnabled(false);
                            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);

                            return true;
                        default:
                            break;
                    }
                }
                return false;
            }
        });


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((MainActivity)getActivity()).stopCronometro();
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

    public List<String> borrarRepetidos(List<String> arraycar){
        Set<String> hs = new HashSet<>();
        hs.addAll(arraycar);
        arraycar.clear();
        arraycar.addAll(hs);

        return arraycar;

    }

    public void blockListeners(){
        inicio.setEnabled(false);
        registros.setEnabled(false);
        seguimiento.setEnabled(false);
        sincronizacion.setEnabled(false);
    }
}
