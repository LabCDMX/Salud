package mx.digitalcoaster.rzertuche.medicoencasa.Fragments;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;
import mx.digitalcoaster.rzertuche.medicoencasa.Utils.SharedPreferences;


import mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity;
import mx.digitalcoaster.rzertuche.medicoencasa.DataBase.DataBaseDB;
import mx.digitalcoaster.rzertuche.medicoencasa.R;
import mx.digitalcoaster.rzertuche.medicoencasa.models.Item;
import mx.digitalcoaster.rzertuche.medicoencasa.models.ItemAdapter;
import mx.digitalcoaster.rzertuche.medicoencasa.models.VisitasAdapter;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RegistroFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RegistroFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegistroFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public static Context appContext;

    public GridView lista;
    private List<Item> items = null;

    private SQLiteDatabase db = null;   // Objeto para usar la base de datos local
    private Cursor c = null;            // Objeto para hacer consultas a la base de datos

    SharedPreferences sharedPreferences;

    public GridView lista2;
    private List<Item> items2 = null;


    public RegistroFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static RegistroFragment newInstance(String param1, String param2) {
        RegistroFragment fragment = new RegistroFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

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
        return inflater.inflate(R.layout.fragment_registro, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sharedPreferences = SharedPreferences.getInstance();




        lista2 = (GridView) getActivity().findViewById(R.id.gridview);
        items2 = new ArrayList<>();


        getPacientes();
        lista.setAdapter(new VisitasAdapter(getActivity().getApplicationContext(), items2));


        sharedPreferences = SharedPreferences.getInstance();


        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View v, int position, long arg3) {
                Item selectedUser = items.get(position);
                String nameUser = selectedUser.getNombre();
                sharedPreferences.setStringData("nameSeguimiento", nameUser);
                Log.e("TOUCHME",nameUser);
                ((MainActivity)getActivity()).visitasFragment(nameUser);

            }
        });





        GridView gridView = (GridView) view.findViewById(R.id.gridusers);

        items = new ArrayList<>();
        getProductos();
        gridView.setAdapter(new ItemAdapter(getActivity().getApplicationContext(), items));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View v, int position, long arg3) {
                Item selectedUser = items.get(position);
                Log.d("User", "USERUUID:"+selectedUser.getNombre());
                sharedPreferences.setStringData("nameHistoric", selectedUser.getNombre());
                sharedPreferences.setStringData("curpHistoric", selectedUser.getCurp());
                sharedPreferences.setStringData("direccionHistoric", selectedUser.getDireccion());

                ((MainActivity)getActivity()).questionExploracion();

            }
        });
    }

    private void getProductos() {

        db = getActivity().openOrCreateDatabase(DataBaseDB.DB_NAME, android.content.Context.MODE_PRIVATE, null);
        try {
            c = db.rawQuery("SELECT * FROM " + DataBaseDB.TABLE_NAME_PACIENTES, null);
            if (c.moveToFirst()) {
                do {
                    items.add(new Item(c.getString(1), c.getString(2), c.getString(3)));
                }while (c.moveToNext());
            } else {
                System.out.println("No existen PACIENTES");
            }
            c.close();
        } catch (Exception ex) {
            Log.e("Error", ex.toString());
        } finally {
            db.close();
        }
    }


    private void getPacientes() {

        db = getActivity().openOrCreateDatabase(DataBaseDB.DB_NAME, Context.MODE_PRIVATE, null);
        try {
            c = db.rawQuery("SELECT * FROM " + DataBaseDB.TABLE_NAME_PACIENTES_VISITAS, null);
            if (c.moveToFirst()) {
                do {
                    items2.add(new Item(c.getString(1), c.getString(2), c.getString(3), c.getString(5)));
                }while (c.moveToNext());
            } else {
                System.out.println("No existen PACIENTES");
            }
            c.close();
        } catch (Exception ex) {
            Log.e("Error", ex.toString());
        } finally {
            db.close();
        }
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
