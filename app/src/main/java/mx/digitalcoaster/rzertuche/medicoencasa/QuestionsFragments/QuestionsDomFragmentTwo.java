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
import android.widget.RelativeLayout;
import android.widget.Toast;

import mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity;
import mx.digitalcoaster.rzertuche.medicoencasa.R;
import mx.digitalcoaster.rzertuche.medicoencasa.Utils.SharedPreferences;

import static mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity.inicio;
import static mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity.registros;
import static mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity.seguimiento;
import static mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity.sincronizacion;


public class QuestionsDomFragmentTwo extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;


    SharedPreferences sharedPreferences;
    private RadioButton seguro,seguro2;
    private RadioGroup radioEsc,radioAct,radioSeguro;
    private ImageButton next;
    private RelativeLayout relativeSeguro;
    private LinearLayout questions,questions2,questions3;
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
        return inflater.inflate(R.layout.dialog_question_domiciliarios1, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        blockListeners();

        seguro = (RadioButton) getActivity().findViewById(R.id.seguro);
        seguro2 = (RadioButton) getActivity().findViewById(R.id.seguro2);

        radioEsc = (RadioGroup) getActivity().findViewById(R.id.radioEsc);
        radioAct = (RadioGroup) getActivity().findViewById(R.id.radioAct);
        radioSeguro = (RadioGroup) getActivity().findViewById(R.id.radioSeguro);



        next = (ImageButton) getActivity().findViewById(R.id.next);

        relativeSeguro = (RelativeLayout) getActivity().findViewById(R.id.linearYes);
        questions = (LinearLayout) getActivity().findViewById(R.id.questions);
        questions2 = (LinearLayout) getActivity().findViewById(R.id.questions2);
        questions3 = (LinearLayout) getActivity().findViewById(R.id.questions3);




        sharedPreferences = SharedPreferences.getInstance();




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
                    count=0;
                    ((MainActivity)getActivity()).questionDomThree();

                }



            }
        });



        radioEsc.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                if (checkedId == R.id.estado_civil){
                    sharedPreferences.setStringData("EstadoCiv","Soltero");
                }else if (checkedId == R.id.estado_civil2){
                    sharedPreferences.setStringData("EstadoCiv","Casado");
                }else if (checkedId == R.id.estado_civil3){
                    sharedPreferences.setStringData("EstadoCiv","Divorciado");
                }else if (checkedId == R.id.estado_civil4){
                    sharedPreferences.setStringData("EstadoCiv","Viudo");
                }

            }

        });


        radioAct.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                if (checkedId == R.id.actividad){
                    sharedPreferences.setStringData("Ocupacion","Empleado");
                }else if (checkedId == R.id.actividad2){
                    sharedPreferences.setStringData("Ocupacion","Profesionista");
                }else if (checkedId == R.id.actividad3){
                    sharedPreferences.setStringData("Ocupacion","Estudiante");
                }

            }

        });

        radioAct.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                if (checkedId == R.id.actividad){
                    sharedPreferences.setStringData("Ocupacion","Empleado");
                }else if (checkedId == R.id.actividad2){
                    sharedPreferences.setStringData("Ocupacion","Profesionista");
                }else if (checkedId == R.id.actividad3){
                    sharedPreferences.setStringData("Ocupacion","Estudiante");
                }

            }

        });

        radioSeguro.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                if (checkedId == R.id.seguro){
                    sharedPreferences.setStringData("Derechohabiente","SI");
                    relativeSeguro.setVisibility(View.VISIBLE);
                }else if (checkedId == R.id.seguro2){
                    sharedPreferences.setStringData("Derechohabiente","NO");
                    relativeSeguro.setVisibility(View.GONE);
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
