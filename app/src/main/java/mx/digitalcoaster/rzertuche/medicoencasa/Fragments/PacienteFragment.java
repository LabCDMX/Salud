package mx.digitalcoaster.rzertuche.medicoencasa.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;
import mx.digitalcoaster.rzertuche.medicoencasa.R;
import mx.digitalcoaster.rzertuche.medicoencasa.models.User;
import mx.digitalcoaster.rzertuche.medicoencasa.models.Visita;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PacienteFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class PacienteFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    public String userID = "";
    public User user = new User();
    public RealmResults visitas;

    private OnFragmentInteractionListener mListener;

    public PacienteFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        Log.d("User", "USERUUID:"+userID);


        return inflater.inflate(R.layout.fragment_paciente, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        Realm realm = Realm.getDefaultInstance();
        try {
            user = realm.where(User.class).equalTo("userUUID", userID).findFirst();
            // do something with the person ...
            TextView name = (TextView) view.findViewById(R.id.textView7);
            name.setText(user.getNombre()+" "+user.getApellido_Paterno()+" "+user.getApellido_Materno());

            visitas = realm.where(Visita.class).equalTo("userUUID",""+user.getUserUUID()).sort("fecha", Sort.DESCENDING).findAll();

            if (visitas.size() > 0){

                Visita visita = (Visita) visitas.get(0);

                TextView nota = (TextView) view.findViewById(R.id.textView8);
                nota.setText(visita.getNota());

                TextView tratamineto = (TextView) view.findViewById(R.id.textView12);
                tratamineto.setText(visita.getTratamiento());

                LinearLayout visitasLayout = (LinearLayout) view.findViewById(R.id.visitasLayout);

                for( int i = 1; i < visitas.size(); i++ )
                        {
                            Visita visitaLoop = (Visita) visitas.get(i);

                            LinearLayout horizontal = new LinearLayout(getActivity());
                            horizontal.setOrientation(LinearLayout.HORIZONTAL);

                            LinearLayout vertical = new LinearLayout(getActivity());
                            vertical.setOrientation(LinearLayout.VERTICAL);

                            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                            layoutParams.setMargins(0, 0, 0, 10);
                            vertical.setLayoutParams(layoutParams);

                            TextView textView = new TextView(getActivity());

                            SimpleDateFormat simpleDate =  new SimpleDateFormat("dd/MM/yyyy");
                            String strDt = simpleDate.format(visitaLoop.fecha);



                            textView.setText("Visita "+(visitas.size()-(i))+" - "+strDt);
                            textView.setTextSize(21);


                            TextView textView2 = new TextView(getActivity());
                            textView2.setText("Diagnóstico: "+visitaLoop.getNota());
                            textView2.setTextSize(14);

                            TextView textView3 = new TextView(getActivity());
                            textView3.setText("Tratamiento: "+visitaLoop.getTratamiento());
                            textView3.setTextSize(14);

                            vertical.addView(textView);
                            vertical.addView(textView2);
                            vertical.addView(textView3);
                            horizontal.addView(vertical);
                            visitasLayout.addView(horizontal);
                        }
            }


        } finally {
            realm.close();
        }

    }


    public void onButtonPressed(Uri uri) {
        callParentMethod();
    }


    public void callParentMethod(){
        getActivity().onBackPressed();
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
