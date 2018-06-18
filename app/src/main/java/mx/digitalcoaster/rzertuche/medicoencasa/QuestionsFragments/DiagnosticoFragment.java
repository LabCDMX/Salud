package mx.digitalcoaster.rzertuche.medicoencasa.QuestionsFragments;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity;
import mx.digitalcoaster.rzertuche.medicoencasa.R;
import mx.digitalcoaster.rzertuche.medicoencasa.Utils.SharedPreferences;

import static mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity.inicio;
import static mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity.registros;
import static mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity.seguimiento;
import static mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity.sincronizacion;

;


public class DiagnosticoFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    SharedPreferences sharedPreferences;

    ImageButton next,sano,obeso,sobrepeso;



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
        return inflater.inflate(R.layout.fragment_diagnostico, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        blockListeners();


        next = (ImageButton) getActivity().findViewById(R.id.next);

        sano = (ImageButton) getActivity().findViewById(R.id.sano);
        sobrepeso = (ImageButton) getActivity().findViewById(R.id.sobrepeso);
        obeso = (ImageButton) getActivity().findViewById(R.id.obeso);




        //Obtencion de datos del sharedPreferences
        sharedPreferences = SharedPreferences.getInstance();

        sano.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                sobrepeso.setImageDrawable(getActivity().getDrawable(R.drawable.status_ambar));
                obeso.setImageDrawable(getActivity().getDrawable(R.drawable.status_red));

                sano.setImageDrawable(getActivity().getDrawable(R.drawable.seleccionar));
                sharedPreferences.setStringData("ImageItem","Sano");

            }
        });

        sobrepeso.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                sano.setImageDrawable(getActivity().getDrawable(R.drawable.status_green));
                obeso.setImageDrawable(getActivity().getDrawable(R.drawable.status_red));

                sobrepeso.setImageDrawable(getActivity().getDrawable(R.drawable.seleccionar));
                sharedPreferences.setStringData("ImageItem","Sobrepeso");

            }
        });

        obeso.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                sano.setImageDrawable(getActivity().getDrawable(R.drawable.status_green));
                sobrepeso.setImageDrawable(getActivity().getDrawable(R.drawable.status_ambar));

                obeso.setImageDrawable(getActivity().getDrawable(R.drawable.seleccionar));
                sharedPreferences.setStringData("ImageItem","Obeso");

            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((MainActivity)getActivity()).fragmentTarjetaPaciente();


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

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}
