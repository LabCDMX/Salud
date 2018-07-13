package mx.digitalcoaster.rzertuche.medicoencasa.QuestionsFragments;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

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


public class QuestionCuidador extends Fragment {




    LinearLayout open,open2, open3;


    ImageButton next;

    Activity activity;
    View container;
    int count = 0;

    TextView answer4;

    private Spinner sp;

    private SimpleDateFormat dateFormatter;
    private DatePickerDialog datePickerDialog;





    private QuestionsFragment.OnFragmentInteractionListener mListener;
    SharedPreferences sharedPreferences;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.dialog_datos_cuidador, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        this.activity = getActivity();
        this.container = view;


        open = (LinearLayout) view.findViewById(R.id.open);
        open2 = (LinearLayout) view.findViewById(R.id.open2);
        open3 = (LinearLayout) view.findViewById(R.id.open3);




        sharedPreferences = SharedPreferences.getInstance();
        sp = getActivity().findViewById(R.id.answer6);



        answer4 = (TextView) getActivity().findViewById(R.id.answer4);
        answer4.setOnClickListener(new View.OnClickListener() {
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
                                answer4.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.getDatePicker().setSpinnersShown(true);
                datePickerDialog.setCancelable(false);
                datePickerDialog.show();


            }
        });






        ImageButton next = (ImageButton) view.findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                count ++;

                if(count == 1){
                    open.setVisibility(View.GONE);
                    open2.setVisibility(View.VISIBLE);
                }else if(count == 2){
                    open2.setVisibility(View.GONE);
                    open3.setVisibility(View.VISIBLE);

                }else if(count == 3){

                    ((MainActivity)getActivity()).candidatoPrograma();
                }


                /*String nombre = answer.getText().toString();
                String apeP = answer2.getText().toString();
                String apeM = answer3.getText().toString();


                if(checkName(nombre)){
                    sharedPreferences.setStringData("Estado",nombre);
                    if(checkAP(apeP)){
                        sharedPreferences.setStringData("Municipio",apeP);
                        if(checkAM(apeM)){
                            sharedPreferences.setStringData("Localidad",apeM);
                            sharedPreferences.setStringData("Direccion", nombre + " " +  apeP + " " + apeM);

                            ((MainActivity)getActivity()).questionDomTwo();
                        }else{
                            answer3.setError("Campo faltante");
                            answer3.requestFocus();
                            InputMethodManager imm = (InputMethodManager) // con esto abres el teclado despues de ubicar el foco en tu editText
                                    getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.showSoftInput(answer3, InputMethodManager.SHOW_IMPLICIT);
                        }
                    }else{
                        answer2.setError("Campo faltante");
                        answer2.requestFocus();
                        InputMethodManager imm = (InputMethodManager) // con esto abres el teclado despues de ubicar el foco en tu editText
                                getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.showSoftInput(answer2, InputMethodManager.SHOW_IMPLICIT);

                    }
                }else{

                    answer.setError("Campo faltante");
                    answer.requestFocus();
                    InputMethodManager imm = (InputMethodManager) // con esto abres el teclado despues de ubicar el foco en tu editText
                            getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(answer, InputMethodManager.SHOW_IMPLICIT);

                } */




            }
        });

        getSpinnerOptions();



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




    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof QuestionsFragment.OnFragmentInteractionListener) {
            mListener = (QuestionsFragment.OnFragmentInteractionListener) context;
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
