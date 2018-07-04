package mx.digitalcoaster.rzertuche.medicoencasa.QuestionsFragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Range;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity;
import mx.digitalcoaster.rzertuche.medicoencasa.R;
import mx.digitalcoaster.rzertuche.medicoencasa.Utils.SharedPreferences;

import static mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity.inicio;
import static mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity.registros;
import static mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity.seguimiento;
import static mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity.sincronizacion;


public class QuestionsExploracion extends Fragment {


    Double imcAux = 0.0;
    private EditText imc,estatura,peso;
    private OnFragmentInteractionListener mListener;

    SharedPreferences sharedPreferences;
    private CheckBox estufa,refrigerador,lavadora,telefono,horno,televisor;
    private ImageButton next;
    public static List<String> listElectro = new ArrayList<String>();

    private LinearLayout questions,questions3,questions4;
    private TextView questions2;
    private int count = 0;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.dialog_question_exploracion, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        blockListeners();

        sharedPreferences = SharedPreferences.getInstance();
        String name = sharedPreferences.getStringData("nameHistoric");

        TextView title2 = (TextView) getActivity().findViewById(R.id.title2);
        title2.setText(name);


        estatura = (EditText) getActivity().findViewById(R.id.answer2);
        estatura.addTextChangedListener(imcWatcher);

        imc = (EditText) getActivity().findViewById(R.id.answer3);

        peso = (EditText) getActivity().findViewById(R.id.answer);

        next = (ImageButton) getActivity().findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sharedPreferences.setStringData("Peso", peso.getText().toString());
                sharedPreferences.setStringData("Estatura", estatura.getText().toString());

                ((MainActivity)getActivity()).historiaClinicaFragment();

            }
        });

    }


    private final TextWatcher imcWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            imc.setVisibility(View.VISIBLE);
            Double pesoAux = 0.0;
            Double estaturaAux = 0.0;

            pesoAux = Double.valueOf(peso.getText().toString());
            estaturaAux = Double.valueOf(estatura.getText().toString());
            imcAux = (pesoAux / (estaturaAux * estaturaAux));

        }

        public void afterTextChanged(Editable s) {
            if (s.length() == 0) {
                imc.setVisibility(View.GONE);
            } else{
                DecimalFormat df = new DecimalFormat("#.00");
                Double imcAuxiliar = Double.valueOf(df.format(imcAux));

                Log.e("prueba de double" , String.valueOf(imcAuxiliar));

                if(imcAuxiliar<18.5){
                    imc.setText(df.format(imcAux) + " Peso Insuficiente");
                }else if(imcAuxiliar >= 18.5 && imcAuxiliar <= 24.9){
                    imc.setText(df.format(imcAux) + " Peso Normal");
                }else if(imcAuxiliar >= 25 && imcAuxiliar <= 26.9){
                    imc.setText(df.format(imcAux) + " Sobrepeso Grado I");
                }else if(imcAuxiliar >= 27 && imcAuxiliar <= 29.9){
                    imc.setText(df.format(imcAux) + " Sobrepeso Grado II");
                }else if(imcAuxiliar >= 30 && imcAuxiliar <= 34.9){
                    imc.setText(df.format(imcAux) + " Obesidad Tipo I");
                }else if(imcAuxiliar >= 35 && imcAuxiliar <= 34.9){
                    imc.setText(df.format(imcAux) + " Obesidad Tipo II");
                }

            }
        }
    };



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
}
