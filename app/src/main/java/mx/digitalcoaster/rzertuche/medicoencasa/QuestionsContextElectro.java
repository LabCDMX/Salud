package mx.digitalcoaster.rzertuche.medicoencasa;

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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class QuestionsContextElectro extends Fragment {



    private OnFragmentInteractionListener mListener;

    PaymentServicesSharedPreferences sharedPreferences;
    private CheckBox estufa,refrigerador,lavadora,telefono,horno,televisor;
    private ImageButton next;
    public static List<String> listElectro = new ArrayList<String>();




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


        estufa = (CheckBox) getActivity().findViewById(R.id.estufa);
        lavadora = (CheckBox) getActivity().findViewById(R.id.lavadora);
        refrigerador = (CheckBox) getActivity().findViewById(R.id.refrigerador);
        telefono = (CheckBox) getActivity().findViewById(R.id.telefono);
        televisor = (CheckBox) getActivity().findViewById(R.id.tele);
        horno = (CheckBox) getActivity().findViewById(R.id.horno);







        next = (ImageButton) getActivity().findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                    ((MainActivity)getActivity()).activityRegistros();
                }else{
                    Toast.makeText(getActivity(),"Selecciona una opcion antes de continuar",Toast.LENGTH_SHORT).show();
                }

            }
        });





        sharedPreferences = PaymentServicesSharedPreferences.getInstance();

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
