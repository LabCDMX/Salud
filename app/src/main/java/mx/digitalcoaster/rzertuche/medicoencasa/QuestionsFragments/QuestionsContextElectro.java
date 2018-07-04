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
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity;
import mx.digitalcoaster.rzertuche.medicoencasa.Utils.SharedPreferences;
import mx.digitalcoaster.rzertuche.medicoencasa.R;

import static mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity.inicio;
import static mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity.registros;
import static mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity.seguimiento;
import static mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity.sincronizacion;


public class QuestionsContextElectro extends Fragment {



    private OnFragmentInteractionListener mListener;

    SharedPreferences sharedPreferences;
    private CheckBox estufa,refrigerador,lavadora,telefono,horno,televisor;
    private ImageButton next;
    public static List<String> listElectro = new ArrayList<String>();
    private LinearLayout questions,questions2,questions3,questions4;
    private int count = 0;
    private RadioGroup radioVivienda,radioPiso, radioTecho;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.dialog_question_electro, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        blockListeners();

        estufa = (CheckBox) getActivity().findViewById(R.id.estufa);
        lavadora = (CheckBox) getActivity().findViewById(R.id.lavadora);
        refrigerador = (CheckBox) getActivity().findViewById(R.id.refrigerador);
        telefono = (CheckBox) getActivity().findViewById(R.id.telefono);
        televisor = (CheckBox) getActivity().findViewById(R.id.tele);
        horno = (CheckBox) getActivity().findViewById(R.id.horno);


        questions = (LinearLayout) getActivity().findViewById(R.id.questions);
        questions2 = (LinearLayout) getActivity().findViewById(R.id.questions2);
        questions3 = (LinearLayout) getActivity().findViewById(R.id.questions3);
        questions4 = (LinearLayout) getActivity().findViewById(R.id.questions4);

        radioVivienda = (RadioGroup) getActivity().findViewById(R.id.radioEsc);
        radioPiso = (RadioGroup) getActivity().findViewById(R.id.radioPiso);
        radioTecho = (RadioGroup) getActivity().findViewById(R.id.radioTecho);






        next = (ImageButton) getActivity().findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;



                if(estufa.isChecked() || refrigerador.isChecked() || lavadora.isChecked() || telefono.isChecked() || horno.isChecked() || televisor.isChecked() ){
                    if(estufa.isChecked()){
                        listElectro.add("Estufa");
                    }
                    if(refrigerador.isChecked()){
                        listElectro.add("Refrigerador");
                    }
                    if(lavadora.isChecked()){
                        listElectro.add("Lavadora");
                    }
                    if(telefono.isChecked()){
                        listElectro.add("Telefono");
                    }
                    if(horno.isChecked()){
                        listElectro.add("Horno");
                    }
                    if(televisor.isChecked()){
                        listElectro.add("Televisor");
                    }

                    Log.e("VALUES LIST", listElectro.toString());
                }


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
                    count=0;
                    ((MainActivity)getActivity()).fragmentQuestionsAlimentos();

                }

            }
        });





        sharedPreferences = SharedPreferences.getInstance();




        radioVivienda.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                if (checkedId == R.id.departamento){
                    sharedPreferences.setStringData("Caracteristicas","Departamento");
                }else if (checkedId == R.id.casa){
                    sharedPreferences.setStringData("Caracteristicas","Casa");
                }else if (checkedId == R.id.duplex){
                    sharedPreferences.setStringData("Caracteristicas","Duplex");
                }else if (checkedId == R.id.multifamiliar){
                    sharedPreferences.setStringData("Caracteristicas","MultiFamiliar");
                }

            }

        });


        radioPiso.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                if (checkedId == R.id.piso){
                    sharedPreferences.setStringData("Piso","Loza");
                }else if (checkedId == R.id.piso2){
                    sharedPreferences.setStringData("Piso","Cemento");
                }else if (checkedId == R.id.piso3){
                    sharedPreferences.setStringData("Piso","Tierra");
                }

            }

        });

        radioTecho.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                if (checkedId == R.id.techo){
                    sharedPreferences.setStringData("Techo","Asbesto");
                }else if (checkedId == R.id.techo2){
                    sharedPreferences.setStringData("Techo","Cemento");
                }else if (checkedId == R.id.techo3){
                    sharedPreferences.setStringData("Techo","Paja");
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
