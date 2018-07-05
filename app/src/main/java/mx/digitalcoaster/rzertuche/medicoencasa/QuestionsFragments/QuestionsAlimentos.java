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
import android.widget.TextView;
import android.widget.Toast;

import mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity;
import mx.digitalcoaster.rzertuche.medicoencasa.R;
import mx.digitalcoaster.rzertuche.medicoencasa.Utils.SharedPreferences;

import static mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity.inicio;
import static mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity.registros;
import static mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity.seguimiento;
import static mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity.sincronizacion;


public class QuestionsAlimentos extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    SharedPreferences sharedPreferences;
    private RadioButton alimentos1,alimentos2,alimentos3;
    private RadioGroup radioEsc, radioAli;
    private ImageButton next;
    private TextView changeTitle;
    private LinearLayout tipoAlimentos, open;
    private Boolean isFruit = false;
    private Boolean isVerdura = false;
    private Boolean isLegu = false;
    private Boolean isCere = false;
    private Boolean isChatarra = false;
    private Boolean isPan = false;
    private Boolean isCarne = false;

    int count=0;



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
        return inflater.inflate(R.layout.dialog_question_alimentacion, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        alimentos1 = (RadioButton) getActivity().findViewById(R.id.alimentos1);
        alimentos2 = (RadioButton) getActivity().findViewById(R.id.alimentos2);
        alimentos3= (RadioButton) getActivity().findViewById(R.id.alimentos3);


        changeTitle = (TextView) getActivity().findViewById(R.id.question2);
        tipoAlimentos = (LinearLayout) getActivity().findViewById(R.id.alimentos);
        open = (LinearLayout) getActivity().findViewById(R.id.open);


        radioEsc = (RadioGroup) getActivity().findViewById(R.id.radioEsc);
        radioAli = (RadioGroup) getActivity().findViewById(R.id.radioAli);


        next = (ImageButton) getActivity().findViewById(R.id.next);


        sharedPreferences = SharedPreferences.getInstance();




        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                if((alimentos1.isChecked() || alimentos2.isChecked() || alimentos3.isChecked()) && count == 1){
                    Log.e("VALUES RADIO", sharedPreferences.getStringData("Alimentos"));
                }else if(count == 2){
                    isVerdura = true;
                    open.setVisibility(View.GONE);
                    tipoAlimentos.setVisibility(View.VISIBLE);
                }else if(count == 3){
                    isVerdura = false;
                    isFruit = true;

                    radioAli.clearCheck();
                    changeTitle.setText("Frutas");

                }else if(count == 4){
                    isFruit = false;
                    isLegu = true;
                    radioAli.clearCheck();
                    changeTitle.setText("Leguminosas");

                }else if(count == 5){
                    isLegu = false;
                    isCere = true;
                    radioAli.clearCheck();
                    changeTitle.setText("Cereales/Tuberculos");

                }else if(count == 6){
                    isCere=false;
                    isPan = true;
                    radioAli.clearCheck();
                    changeTitle.setText("Pan/Tortilla");

                }else if(count == 7){
                    isPan = false;
                    isChatarra = true;
                    radioAli.clearCheck();
                    changeTitle.setText("Chatarra");

                }else if(count == 8){
                    radioAli.clearCheck();
                    changeTitle.setText("Agua");

                }else if(count == 9){
                    isChatarra=false;
                    isCarne = true;
                    radioAli.clearCheck();
                    changeTitle.setText("Carne");

                }else if(count == 10){
                    radioAli.clearCheck();
                    changeTitle.setText("Pollo");

                }else if(count == 11){
                    radioAli.clearCheck();
                    changeTitle.setText("Pescado");

                }else if(count == 12){
                    radioAli.clearCheck();
                    changeTitle.setText("Embutidos");

                }else if(count == 13){
                    radioAli.clearCheck();
                    count = 0;
                    ((MainActivity)getActivity()).fragmentQuestionsContexto();
                }






            }
        });

        radioEsc.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                if (checkedId == R.id.alimentos1){

                    sharedPreferences.setStringData("Alimentos","Menos de 3");
                    Log.e("RadioGroup","Primaria");

                }else if (checkedId == R.id.alimentos2){

                    sharedPreferences.setStringData("Alimentos","3 a 5");
                    Log.e("RadioGroup","Sec");

                }else if (checkedId == R.id.alimentos3){

                    sharedPreferences.setStringData("Alimentos","Más de 5");
                    Log.e("RadioGroup","prepa");

                }

            }

        });

        radioAli.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                if (checkedId == R.id.tipoAli){

                    if(isVerdura){
                        sharedPreferences.setStringData("Verduras","Ninguna");
                    }else if(isFruit){
                        sharedPreferences.setStringData("Fruta","Ninguna");
                    }else if(isLegu){
                        sharedPreferences.setStringData("Leguminosa","Ninguna");
                    }else if(isCarne){
                        sharedPreferences.setStringData("Carne","Ninguna");
                    }else if(isPan){
                        sharedPreferences.setStringData("Pan","Ninguna");
                    }else if(isCere){
                        sharedPreferences.setStringData("Cereal","Ninguna");
                    }else if(isChatarra){
                        sharedPreferences.setStringData("Chatarra","Ninguna");
                    }


                }else if (checkedId == R.id.tipoAli2){

                    if(isVerdura){
                        sharedPreferences.setStringData("Verduras","1 a 5");
                    }else if(isFruit){
                        sharedPreferences.setStringData("Fruta","1 a 5");
                    }else if(isLegu){
                        sharedPreferences.setStringData("Leguminosa","1 a 5");
                    }else if(isCarne){
                        sharedPreferences.setStringData("Carne","1 a 5");
                    }else if(isPan){
                        sharedPreferences.setStringData("Pan","1 a 5");
                    }else if(isCere){
                        sharedPreferences.setStringData("Cereal","1 a 5");
                    }else if(isChatarra){
                        sharedPreferences.setStringData("Chatarra","1 a 5");
                    }

                }else if (checkedId == R.id.tipoAli3){

                    if(isVerdura){
                        sharedPreferences.setStringData("Verduras","5 a 10");
                    }else if(isFruit){
                        sharedPreferences.setStringData("Fruta","5 a 10");
                    }else if(isLegu){
                        sharedPreferences.setStringData("Leguminosa","5 a 10");
                    }else if(isCarne){
                        sharedPreferences.setStringData("Carne","5 a 10");
                    }else if(isPan){
                        sharedPreferences.setStringData("Pan","5 a 10");
                    }else if(isCere){
                        sharedPreferences.setStringData("Cereal","5 a 10");
                    }else if(isChatarra){
                        sharedPreferences.setStringData("Chatarra","5 a 10");
                    }

                }else if (checkedId == R.id.tipoAli4){

                    if(isVerdura){
                        sharedPreferences.setStringData("Verduras","10 a 15");
                    }else if(isFruit){
                        sharedPreferences.setStringData("Fruta","10 a 15");
                    }else if(isLegu){
                        sharedPreferences.setStringData("Leguminosa","10 a 15");
                    }else if(isCarne){
                        sharedPreferences.setStringData("Carne","10 a 15");
                    }else if(isPan){
                        sharedPreferences.setStringData("Pan","10 a 15");
                    }else if(isCere){
                        sharedPreferences.setStringData("Cereal","10 a 15");
                    }else if(isChatarra){
                        sharedPreferences.setStringData("Chatarra","10 a 15");
                    }

                }else if (checkedId == R.id.tipoAli5){

                    if(isVerdura){
                        sharedPreferences.setStringData("Verduras","15 a 20");
                    }else if(isFruit){
                        sharedPreferences.setStringData("Fruta","15 a 20");
                    }else if(isLegu){
                        sharedPreferences.setStringData("Leguminosa","15 a 20");
                    }else if(isCarne){
                        sharedPreferences.setStringData("Carne","15 a 20");
                    }else if(isPan){
                        sharedPreferences.setStringData("Pan","15 a 20");
                    }else if(isCere){
                        sharedPreferences.setStringData("Cereal","15 a 20");
                    }else if(isChatarra){
                        sharedPreferences.setStringData("Chatarra","15 a 20");
                    }

                }else if (checkedId == R.id.tipoAli6){

                    if(isVerdura){
                        sharedPreferences.setStringData("Verduras","Más de 20");
                    }else if(isFruit){
                        sharedPreferences.setStringData("Fruta","Más de 20");
                    }else if(isLegu){
                        sharedPreferences.setStringData("Leguminosa","Más de 20");
                    }else if(isCarne){
                        sharedPreferences.setStringData("Carne","Más de 20");
                    }else if(isPan){
                        sharedPreferences.setStringData("Pan","Más de 20");
                    }else if(isCere){
                        sharedPreferences.setStringData("Cereal","Más de 20");
                    }else if(isChatarra){
                        sharedPreferences.setStringData("Chatarra","Más de 20");
                    }

                }

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


    public void blockListeners(){
        inicio.setEnabled(false);
        registros.setEnabled(false);
        seguimiento.setEnabled(false);
        sincronizacion.setEnabled(false);
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
