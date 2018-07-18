package mx.digitalcoaster.rzertuche.medicoencasa.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import mx.digitalcoaster.rzertuche.medicoencasa.R;

import static mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity.inicio;
import static mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity.notListerners;
import static mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity.registros;
import static mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity.seguimiento;
import static mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity.sincronizacion;
import static mx.digitalcoaster.rzertuche.medicoencasa.Fragments.PacientesFragment.isSinExp;


public class SuccededHistoriaFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private LinearLayout loader, finish;
    private TextView fase, textoFase;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_succeded_historia_clinica, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        restartListeners();

        final LinearLayout loader = (LinearLayout) getActivity().findViewById(R.id.loader);
        final LinearLayout finish = (LinearLayout) getActivity().findViewById(R.id.completed);
        textoFase = getActivity().findViewById(R.id.textoFase);
        fase = getActivity().findViewById(R.id.fase);



        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                finish.setVisibility(View.VISIBLE);
                loader.setVisibility(View.GONE);


            }
        }, 3000);

        loader.setVisibility(View.VISIBLE);

        if(isSinExp){
            isSinExp = false;
            fase.setText("EXPEDIENTE ASIGNADO");
            textoFase.setText("PACIENTE SELLADO");
        }



    }



    public void restartListeners(){
        notListerners=false;
        inicio.setEnabled(true);
        registros.setEnabled(true);
        seguimiento.setEnabled(true);
        sincronizacion.setEnabled(true);
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
