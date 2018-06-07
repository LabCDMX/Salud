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

import mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity;
import mx.digitalcoaster.rzertuche.medicoencasa.Utils.SharedPreferences;
import mx.digitalcoaster.rzertuche.medicoencasa.R;


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

    EditText nombre, apellidoP, apellidoM, estado, municipio, localidad;
    ImageButton btnedit,btnedit1,btnedit2,btnedit3,btnedit4,btnedit5,next ;


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

        btnedit = (ImageButton) getActivity().findViewById(R.id.btn_edit);
        btnedit1 = (ImageButton) getActivity().findViewById(R.id.btn_edit2);
        btnedit2 = (ImageButton) getActivity().findViewById(R.id.btn_edit3);
        btnedit3 = (ImageButton) getActivity().findViewById(R.id.btn_edit4);
        btnedit4 = (ImageButton) getActivity().findViewById(R.id.btn_edit5);
        btnedit5 = (ImageButton) getActivity().findViewById(R.id.btn_edit6);

        next = (ImageButton) getActivity().findViewById(R.id.next);






        nombre.setSingleLine(true);

        //Obtencion de datos del sharedPreferences
        sharedPreferences = SharedPreferences.getInstance();

        nombre.setText(sharedPreferences.getStringData("NombrePatient"));
        apellidoP.setText(sharedPreferences.getStringData("ApellidoP"));
        apellidoM.setText(sharedPreferences.getStringData("ApellidoM"));

        estado.setText(sharedPreferences.getStringData("Estado"));
        municipio.setText(sharedPreferences.getStringData("Municipio"));
        localidad.setText(sharedPreferences.getStringData("Localidad"));

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


        btnedit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apellidoM.setText("");
                apellidoM.setEnabled(true);
                apellidoM.requestFocus();
                InputMethodManager imm = (InputMethodManager) // con esto abres el teclado despues de ubicar el foco en tu editText
                        getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(apellidoM, InputMethodManager.SHOW_IMPLICIT);
            }
        });
        apellidoM.setOnKeyListener(new View.OnKeyListener()
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
                            apellidoM.setEnabled(false);
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

        btnedit5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                localidad.setText("");
                localidad.setEnabled(true);
                localidad.requestFocus();
                InputMethodManager imm = (InputMethodManager) // con esto abres el teclado despues de ubicar el foco en tu editText
                        getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(localidad, InputMethodManager.SHOW_IMPLICIT);
            }
        });
        localidad.setOnKeyListener(new View.OnKeyListener()
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
                            localidad.setEnabled(false);
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
                ((MainActivity)getActivity()).fragmentQuestionsEsc();
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
        apellidoM.setEnabled(false);
        estado.setEnabled(false);
        municipio.setEnabled(false);
        localidad.setEnabled(false);
    }

    public void focusViews(){
        nombre.setEnabled(true);
        apellidoP.setEnabled(true);
        apellidoM.setEnabled(true);
        estado.setEnabled(true);
        municipio.setEnabled(true);
        localidad.setEnabled(true);
    }
}
