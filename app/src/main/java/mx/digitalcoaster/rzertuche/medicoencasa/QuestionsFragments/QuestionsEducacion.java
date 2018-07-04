package mx.digitalcoaster.rzertuche.medicoencasa.QuestionsFragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity;
import mx.digitalcoaster.rzertuche.medicoencasa.Utils.SharedPreferences;
import mx.digitalcoaster.rzertuche.medicoencasa.R;

import static mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity.inicio;
import static mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity.registros;
import static mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity.seguimiento;
import static mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity.sincronizacion;


public class QuestionsEducacion extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    SharedPreferences sharedPreferences;
    private RadioButton primaria,secundaria,preparatoria,licenciatura,posgrado,sinEsc;
    private RadioGroup radioEsc,radioTiempo,radioTransporte, radioRecre,radioEstres;
    private ImageButton next;
    private LinearLayout questions,questions2,questions3,questions4,questions5;
    private int count = 0;





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
        return inflater.inflate(R.layout.dialog_question_contexto, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        blockListeners();


        primaria = (RadioButton) getActivity().findViewById(R.id.primaria_rb);
        secundaria = (RadioButton) getActivity().findViewById(R.id.secundaria_rb);
        preparatoria = (RadioButton) getActivity().findViewById(R.id.media_rb);
        licenciatura = (RadioButton) getActivity().findViewById(R.id.licenciatura_rb);
        posgrado = (RadioButton) getActivity().findViewById(R.id.posgrado_rb);
        sinEsc = (RadioButton) getActivity().findViewById(R.id.sin_esc_rb);

        radioEsc = (RadioGroup) getActivity().findViewById(R.id.radioEsc);
        radioTiempo = (RadioGroup) getActivity().findViewById(R.id.radioTiempo);

        radioRecre = (RadioGroup) getActivity().findViewById(R.id.radioRecre);
        radioEstres = (RadioGroup) getActivity().findViewById(R.id.radioEstres);
        radioTransporte = (RadioGroup) getActivity().findViewById(R.id.radioTrans);



        next = (ImageButton) getActivity().findViewById(R.id.next);


        sharedPreferences = SharedPreferences.getInstance();

        questions = (LinearLayout) getActivity().findViewById(R.id.questions);
        questions2 = (LinearLayout) getActivity().findViewById(R.id.questions2);
        questions3 = (LinearLayout) getActivity().findViewById(R.id.questions3);
        questions4 = (LinearLayout) getActivity().findViewById(R.id.questions4);
        questions5 = (LinearLayout) getActivity().findViewById(R.id.questions5);


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                count++;

                if(count == 1){
                    questions.setVisibility(View.GONE);
                    questions2.setVisibility(View.VISIBLE);
                }

                if(count == 2){
                    questions2.setVisibility(View.GONE);
                    questions3.setVisibility(View.VISIBLE);
                }

                if(count == 3){
                    questions3.setVisibility(View.GONE);
                    questions4.setVisibility(View.VISIBLE);
                }

                if(count == 4){
                    questions4.setVisibility(View.GONE);
                    questions5.setVisibility(View.VISIBLE);
                }

                if(count == 5){
                    ((MainActivity)getActivity()).fragmentContextElectro();

                }
            }
        });

        radioEsc.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                if (checkedId == R.id.primaria_rb){
                    sharedPreferences.setStringData("Escolaridad","Primaria");

                }else if (checkedId == R.id.secundaria_rb){
                    sharedPreferences.setStringData("Escolaridad","Secundaria");

                }else if (checkedId == R.id.media_rb){
                    sharedPreferences.setStringData("Escolaridad","Media Superior");

                }else if (checkedId == R.id.licenciatura_rb){
                    sharedPreferences.setStringData("Escolaridad","Licenciatura");

                }else if (checkedId == R.id.posgrado_rb){
                    sharedPreferences.setStringData("Escolaridad","Posgrado");

                }else if (checkedId == R.id.sin_esc_rb){
                    sharedPreferences.setStringData("Escolaridad","Sin escolaridad");

                }

            }

        });


        radioTiempo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                if (checkedId == R.id.hora1){
                    sharedPreferences.setStringData("TiempoEscuela","Menos de 6 hrs");

                }else if (checkedId == R.id.hora2){
                    sharedPreferences.setStringData("TiempoEscuela","6hrs a 9hrs");

                }else if (checkedId == R.id.hora3){
                    sharedPreferences.setStringData("TiempoEscuela","9hrs a 12hrs");

                }else if (checkedId == R.id.hora4){
                    sharedPreferences.setStringData("TiempoEscuela","Mas de 12 hrs");

                }

            }

        });



        radioTransporte.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                if (checkedId == R.id.transporte1){
                    sharedPreferences.setStringData("Transporte","Menos de 1 hora");

                }else if (checkedId == R.id.transporte2){
                    sharedPreferences.setStringData("Transporte","1 hora a 3 horas");

                }else if (checkedId == R.id.transporte3){
                    sharedPreferences.setStringData("Transporte","Más de 3 horas");

                }
            }

        });


        radioRecre.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                if (checkedId == R.id.recre){
                    sharedPreferences.setStringData("Recreacion","Menos de 1 hora");

                }else if (checkedId == R.id.recre1){
                    sharedPreferences.setStringData("Recreacion","1 hora a 2 horas");

                }else if (checkedId == R.id.recre2){
                    sharedPreferences.setStringData("Recreacion","Más de 2 horas");

                }else if (checkedId == R.id.recre3){
                    sharedPreferences.setStringData("Recreacion","No tengo actividad recreativa");

                }
            }

        });

        radioEstres.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                if (checkedId == R.id.estres){
                    sharedPreferences.setStringData("Estres","Muy bajo");

                }else if (checkedId == R.id.estres1){
                    sharedPreferences.setStringData("Estres","Bajo");

                }else if (checkedId == R.id.estres2){
                    sharedPreferences.setStringData("Estres","Normal");

                }else if (checkedId == R.id.estres3){
                    sharedPreferences.setStringData("Estres","Alto");

                }else if (checkedId == R.id.estres4){
                    sharedPreferences.setStringData("Estres","Muy alto");

                }else if (checkedId == R.id.estres5){
                    sharedPreferences.setStringData("Estres","No se");

                }else if (checkedId == R.id.estres6){
                    sharedPreferences.setStringData("Estres","Prefiero no responder");

                }
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
