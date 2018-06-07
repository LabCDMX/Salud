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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity;
import mx.digitalcoaster.rzertuche.medicoencasa.Utils.SharedPreferences;
import mx.digitalcoaster.rzertuche.medicoencasa.R;


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
    private RadioGroup radioEsc;
    private ImageButton next;



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


        primaria = (RadioButton) getActivity().findViewById(R.id.primaria_rb);
        secundaria = (RadioButton) getActivity().findViewById(R.id.secundaria_rb);
        preparatoria = (RadioButton) getActivity().findViewById(R.id.media_rb);
        licenciatura = (RadioButton) getActivity().findViewById(R.id.licenciatura_rb);
        posgrado = (RadioButton) getActivity().findViewById(R.id.posgrado_rb);
        sinEsc = (RadioButton) getActivity().findViewById(R.id.sin_esc_rb);

        radioEsc = (RadioGroup) getActivity().findViewById(R.id.radioEsc);

        next = (ImageButton) getActivity().findViewById(R.id.next);


        sharedPreferences = SharedPreferences.getInstance();




        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(primaria.isChecked() || secundaria.isChecked() || preparatoria.isChecked() || licenciatura.isChecked() || posgrado.isChecked() || sinEsc.isChecked() ){
                    Log.e("VALUES RADIO", sharedPreferences.getStringData("Escolaridad"));
                    ((MainActivity)getActivity()).fragmentContextElectro();
                }else{
                    Toast.makeText(getActivity(),"Selecciona una opcion antes de continuar",Toast.LENGTH_SHORT).show();
                }

            }
        });

        radioEsc.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                if (checkedId == R.id.primaria_rb){

                    sharedPreferences.setStringData("Escolaridad","Primaria");
                    Log.e("RadioGroup","Primaria");


                }else if (checkedId == R.id.secundaria_rb){

                    sharedPreferences.setStringData("Escolaridad","Secundaria");
                    Log.e("RadioGroup","Sec");

                }else if (checkedId == R.id.media_rb){

                    sharedPreferences.setStringData("Escolaridad","Media Superior");
                    Log.e("RadioGroup","prepa");

                }else if (checkedId == R.id.licenciatura_rb){

                    sharedPreferences.setStringData("Escolaridad","Licenciatura");
                    Log.e("RadioGroup","lic");

                }else if (checkedId == R.id.posgrado_rb){

                    sharedPreferences.setStringData("Escolaridad","Posgrado");
                    Log.e("RadioGroup","posgra");

                }else if (checkedId == R.id.sin_esc_rb){

                    sharedPreferences.setStringData("Escolaridad","Sin escolaridad");
                    Log.e("RadioGroup","sinexc");

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
