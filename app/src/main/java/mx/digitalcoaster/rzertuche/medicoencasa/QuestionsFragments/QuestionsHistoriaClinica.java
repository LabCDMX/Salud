package mx.digitalcoaster.rzertuche.medicoencasa.QuestionsFragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity;
import mx.digitalcoaster.rzertuche.medicoencasa.R;
import mx.digitalcoaster.rzertuche.medicoencasa.Utils.SharedPreferences;

import static mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity.inicio;
import static mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity.registros;
import static mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity.seguimiento;
import static mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity.sincronizacion;


public class QuestionsHistoriaClinica extends Fragment {



    private OnFragmentInteractionListener mListener;

    SharedPreferences sharedPreferences;
    private CheckBox estufa,refrigerador,lavadora,telefono,horno,televisor;
    private ImageButton next;
    public static List<String> listElectro = new ArrayList<String>();

    private TextView question2,question;
    private int count = 0;





    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.dialog_question_historia_clinica, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        blockListeners();

        question2 = getActivity().findViewById(R.id.question2);
        question = getActivity().findViewById(R.id.question);


        next = (ImageButton) getActivity().findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;



                if(count == 1){
                    question.setText(getActivity().getResources().getString(R.string.interrogatorio_title1));
                    question2.setText(getActivity().getResources().getString(R.string.interrogatorio1));
                }

                if(count == 2){
                    question.setText(getActivity().getResources().getString(R.string.interrogatorio_title2));
                    question2.setText(getActivity().getResources().getString(R.string.interrogatorio2));
                }

                if(count == 3){
                    question.setText(getActivity().getResources().getString(R.string.interrogatorio_title3));
                    question2.setText(getActivity().getResources().getString(R.string.interrogatorio3));
                }

                if(count == 4){
                    question.setText(getActivity().getResources().getString(R.string.interrogatorio_title4));
                    question2.setText(getActivity().getResources().getString(R.string.interrogatorio4));
                }
                if(count == 5){
                    question.setText(getActivity().getResources().getString(R.string.interrogatorio_title5));
                    question2.setText(getActivity().getResources().getString(R.string.interrogatorio5));
                }
                if(count == 6){
                    question.setText(getActivity().getResources().getString(R.string.interrogatorio_title6));
                    question2.setText(getActivity().getResources().getString(R.string.interrogatorio6));
                }
                if(count == 7){
                    question.setText(getActivity().getResources().getString(R.string.interrogatorio_title7));
                    question2.setText(getActivity().getResources().getString(R.string.interrogatorio7));
                }
                if(count == 8){
                    question.setText(getActivity().getResources().getString(R.string.interrogatorio_title8));
                    question2.setText(getActivity().getResources().getString(R.string.interrogatorio8));
                }
                if(count == 9){
                    question.setText(getActivity().getResources().getString(R.string.interrogatorio_title9));
                    question2.setText(getActivity().getResources().getString(R.string.interrogatorio9));
                }
                if(count == 10){
                    count=0;
                    ((MainActivity)getActivity()).questionExploracion();

                }


            }
        });





        sharedPreferences = SharedPreferences.getInstance();

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
