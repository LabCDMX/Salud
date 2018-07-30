package mx.digitalcoaster.rzertuche.medicoencasa.QuestionsFragments;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity;
import mx.digitalcoaster.rzertuche.medicoencasa.R;
import mx.digitalcoaster.rzertuche.medicoencasa.Utils.SharedPreferences;
import mx.digitalcoaster.rzertuche.medicoencasa.models.Contexto;
import mx.digitalcoaster.rzertuche.medicoencasa.models.HistoriaClinica;
import mx.digitalcoaster.rzertuche.medicoencasa.models.Question;
import mx.digitalcoaster.rzertuche.medicoencasa.models.User;

import static mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity.inicio;
import static mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity.registros;
import static mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity.seguimiento;
import static mx.digitalcoaster.rzertuche.medicoencasa.Activitys.MainActivity.sincronizacion;


public class QuestionsFragmentTwo extends Fragment {


    TextView category;
    EditText answer,answer2,answer3;

    Activity activity;
    View container;

    private SimpleDateFormat dateFormatter;
    private DatePickerDialog datePickerDialog;






    private OnFragmentInteractionListener mListener;
    SharedPreferences sharedPreferences;
    private String date = "";
    private TextView text_date;
    private  Spinner sp;
    private EditText edad, estadoNacimiento, nacionalidad;

    RelativeLayout open, open1;

    int count = 0;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.dialog_question1, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        this.activity = getActivity();
        this.container = view;



        category = (TextView) view.findViewById(R.id.category);
        category.setText("Personales");

        edad = view.findViewById(R.id.answer6);



        open = view.findViewById(R.id.open);
        open1 = view.findViewById(R.id.open1);

        sp= (Spinner) getActivity().findViewById(R.id.answer2);

        estadoNacimiento = view.findViewById(R.id.answer);
        nacionalidad = view.findViewById(R.id.answer3);


        sharedPreferences = SharedPreferences.getInstance();



        final TextView date = (TextView) getActivity().findViewById(R.id.answer4);


        ImageButton next = (ImageButton) view.findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                count ++;


                if(count == 1){

                    String sexo = sp.getSelectedItem().toString();
                    sharedPreferences.setStringData("Sexo",sexo);
                    sharedPreferences.setStringData("FechaNac",date.getText().toString());
                    sharedPreferences.setStringData("Edad",edad.getText().toString());

                    open.setVisibility(View.GONE);
                    open1.setVisibility(View.VISIBLE);

                }

                if(count == 2){
                    sharedPreferences.setStringData("EstadoNac",estadoNacimiento.getText().toString());
                    sharedPreferences.setStringData("Nac",nacionalidad.getText().toString());

                    ((MainActivity)getActivity()).questionDomTwo();

                }



            }

        });

        final ImageButton calendario = (ImageButton) getActivity().findViewById(R.id.calendar);
        calendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                dateFormatter = new SimpleDateFormat("dd/MM/yyyy");

                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                date .setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);

                                edad.setText(String.valueOf(calcularEdad(year,monthOfYear,dayOfMonth)) + " años");
                                edad.setEnabled(false);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.getDatePicker().setSpinnersShown(true);
                datePickerDialog.setCancelable(false);
                datePickerDialog.show();


            }
        });



        getSpinnerOptions();



    }


    private int calcularEdad(int year, int month, int day) {
        Calendar today = Calendar.getInstance();

        int diff_year = today.get(Calendar.YEAR) -  year;
        int diff_month = today.get(Calendar.MONTH) - month;
        int diff_day = today.get(Calendar.DAY_OF_MONTH) - day;

        //Si está en ese año pero todavía no los ha cumplido
        if (diff_month < 0 || (diff_month == 0 && diff_day < 0)) {
            diff_year = diff_year - 1; //no aparecían los dos guiones del postincremento :|
        }

        return diff_year;
    }

    public void getSpinnerOptions(){
        ArrayList<String> category = new ArrayList<String>();

        category.add("Selecciona uno");
        category.add("HOMBRE");
        category.add("MUJER");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.spinner_item, category);
        dataAdapter.setDropDownViewResource(R.layout.spinner_item);
        sp.setAdapter(dataAdapter);
        dataAdapter.notifyDataSetChanged();

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
