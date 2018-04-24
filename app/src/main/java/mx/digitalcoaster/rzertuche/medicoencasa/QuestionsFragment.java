package mx.digitalcoaster.rzertuche.medicoencasa;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import mx.digitalcoaster.rzertuche.medicoencasa.models.Contexto;
import mx.digitalcoaster.rzertuche.medicoencasa.models.HistoriaClinica;
import mx.digitalcoaster.rzertuche.medicoencasa.models.Question;
import mx.digitalcoaster.rzertuche.medicoencasa.models.User;


public class QuestionsFragment extends Fragment {

    Question currentQuestion;

    TextView question;
    TextView title;
    TextView category;
    RadioGroup multiples1;
    RadioGroup multiples2;
    RadioButton multiple1;
    RadioButton multiple2;
    RadioButton multiple3;
    RadioButton multiple4;
    RadioButton multiple5;
    RadioButton multiple6;
    RadioButton multiple7;
    RadioButton multiple8;
    EditText answer;

    LinearLayout multiple;
    LinearLayout open;
    LinearLayout finish;
    LinearLayout review;

    ImageButton next;
    ImageView imageLogo;
    ImageView imageIcon2;

    String checking = "personales";

    User user;
    Contexto contexto;
    HistoriaClinica historiaClinica;
    String random_uuid = "";

    public void setPersonales(RealmResults<Question> questions) {
        this.personales = questions;
    }
    RealmResults<Question> personales;

    public void setDomiciliarios(RealmResults<Question> questions) {
        this.domiciliarios = questions;
    }
    RealmResults<Question> domiciliarios;

    public void setSociales(RealmResults<Question> questions) {
        this.sociales = questions;
    }
    RealmResults<Question> sociales;

    public void setCondiciones(RealmResults<Question> questions) {
        this.condiciones = questions;
    }
    RealmResults<Question> condiciones;

    public void setAlimentacion(RealmResults<Question> questions) {
        this.alimentacion = questions;
    }
    RealmResults<Question> alimentacion;

    public void setAntecedentesFamiliares(RealmResults<Question> questions) {
        this.antecedentesFamiliares = questions;
    }
    RealmResults<Question> antecedentesFamiliares;

    public void setAntecedentes(RealmResults<Question> questions) {
        this.antecedentes = questions;
    }
    RealmResults<Question> antecedentes;

    public void setInterrogatorio(RealmResults<Question> questions) {
        this.interrogatorio = questions;
    }
    RealmResults<Question> interrogatorio;

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    ArrayList<Question> questions;

    Activity activity;
    View container;

    int indexCurrentQuestion = 0;



    private OnFragmentInteractionListener mListener;

    public QuestionsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Realm realm = Realm.getDefaultInstance();

        RealmQuery<Question> query = realm.where(Question.class);
        RealmResults<Question> questions = query.findAll();

        if(questions.size() == 0){
            Log.d("Realm", "We DO NOT have questions saved");
            ArrayList<String> personales = new ArrayList<String>();
            personales.add("CURP");
            personales.add("Apellido Paterno");
            personales.add("Apellido Materno");
            personales.add("Nombre");
            personales.add("Fecha de Nacimiento");
            personales.add("Estado de Nacimiento");
            personales.add("Sexo");
            personales.add("Nacionalidad de Origen");

            for(int i = 0; i<personales.size(); i++){
                realm.beginTransaction();
                final Question question = realm.createObject(Question.class);
                question.setCategory("personales");
                question.setOrder(i);
                question.setQuestion(personales.get(i));
                question.setMultiple(false);
                realm.commitTransaction();
            }

            ArrayList<String> domiciliarios = new ArrayList<String>();
            domiciliarios.add("Estado");
            domiciliarios.add("Municipio");
            domiciliarios.add("Localidad (Colonia)");
            domiciliarios.add("Estado Civil");
            domiciliarios.add("Ocupación");
            domiciliarios.add("Derechohabiente");
            domiciliarios.add("Telefono fijo");
            domiciliarios.add("Telefono celular");
            domiciliarios.add("Correo electronico");

            for(int i = 0; i<domiciliarios.size(); i++){
                realm.beginTransaction();
                final Question question = realm.createObject(Question.class);
                question.setCategory("domiciliarios");
                question.setOrder(i);
                question.setQuestion(domiciliarios.get(i));
                question.setMultiple(false);
                if(domiciliarios.get(i).equals("Estado Civil")){
                    question.setMultiple(true);
                    question.setMultiple1("soltero");
                    question.setMultiple2("casado");
                    question.setMultiple3("divorciado");
                    question.setMultiple4("viudo");
                }
                if(domiciliarios.get(i).equals("Ocupación")){
                    question.setMultiple(true);
                    question.setMultiple1("empleado");
                    question.setMultiple2("profesionista");
                    question.setMultiple3("estudiante");
                    question.setMultiple4("ninguno");
                }
                if(domiciliarios.get(i).equals("Derechohabiente")){
                    question.setMultiple(true);
                    question.setMultiple1("si");
                    question.setMultiple2("no");
                }
                realm.commitTransaction();
            }

            ArrayList<String> social = new ArrayList<String>();
            social.add("Escolaridad máxima");
            social.add("Tiempo de escuela/trabajo");
            social.add("Tiempo de transporte");
            social.add("Tiempo de recreación");
            social.add("Realiza alguna actividad física");
            social.add("Nivel de estrés actual");
            social.add("Condición fisica actual");
            social.add("Horas de sueño al día");
            social.add("Status económico");
            social.add("Ingreso mensual");
            social.add("Egreso mensual");

            for(int i = 0; i<social.size(); i++){
                realm.beginTransaction();
                final Question question = realm.createObject(Question.class);
                question.setCategory("sociales");
                question.setOrder(i);
                question.setQuestion(social.get(i));
                question.setMultiple(false);
                if(social.get(i).equals("Escolaridad máxima")){
                    question.setMultiple(true);
                    question.setMultiple1("primaria");
                    question.setMultiple2("secundaria");
                    question.setMultiple3("preparatoria");
                    question.setMultiple4("licenciatura");
                    question.setMultiple5("posgrado");
                    question.setMultiple6("sin escolaridad");
                }
                if(social.get(i).equals("Tiempo de escuela/trabajo")){
                    question.setMultiple(true);
                    question.setMultiple1("menos de 1hrs");
                    question.setMultiple2("6 y 9hrs");
                    question.setMultiple3("9 y 12hrs");
                    question.setMultiple4("más de 12hrs");
                }
                if(social.get(i).equals("Tiempo de transporte")){
                    question.setMultiple(true);
                    question.setMultiple1("menos de 1hra");
                    question.setMultiple2("1 y 3hrs");
                    question.setMultiple3("3 y 6hrs");
                }
                if(social.get(i).equals("Tiempo de recreación")){
                    question.setMultiple(true);
                    question.setMultiple1("menos de 1hra");
                    question.setMultiple2("1 y 3hrs");
                    question.setMultiple3("3 y 6hrs");
                }
                if(social.get(i).equals("Realiza alguna actividad física")){
                    question.setMultiple(true);
                    question.setMultiple1("si");
                    question.setMultiple2("no");
                }
                if(social.get(i).equals("Nivel de estrés actual")){
                    question.setMultiple(true);
                    question.setMultiple1("muy bajo");
                    question.setMultiple2("bajo");
                    question.setMultiple3("normal");
                    question.setMultiple4("alto");
                    question.setMultiple5("muy alto");
                    question.setMultiple6("no sé");
                    question.setMultiple7("no responder");
                }
                if(social.get(i).equals("Condición fisica actual")){
                    question.setMultiple(true);
                    question.setMultiple1("muy bajo");
                    question.setMultiple2("bajo");
                    question.setMultiple3("normal");
                    question.setMultiple4("alto");
                    question.setMultiple5("muy alto");
                    question.setMultiple6("no sé");
                    question.setMultiple7("no responder");
                }
                if(social.get(i).equals("Horas de sueño al día")){
                    question.setMultiple(true);
                    question.setMultiple1("menos de 6hrs");
                    question.setMultiple2("6 a 8hrs");
                    question.setMultiple3("más de 8hrs");
                }
                if(social.get(i).equals("Status económico")){
                    question.setMultiple(true);
                    question.setMultiple1("Baja");
                    question.setMultiple2("Media-Baja");
                    question.setMultiple3("Media");
                    question.setMultiple4("Media-Alta");
                    question.setMultiple5("Alta");
                }
                realm.commitTransaction();
            }

            ArrayList<String> condiciones = new ArrayList<String>();
            condiciones.add("Equipos electrodomésticos");
            condiciones.add("Características de vivienda");
            condiciones.add("Piso");
            condiciones.add("Techo");
            condiciones.add("Número de habitaciones");
            condiciones.add("Servicios públicos");
            condiciones.add("Biomasa");
            condiciones.add("Zoonosis");

            for(int i = 0; i<condiciones.size(); i++){
                realm.beginTransaction();
                final Question question = realm.createObject(Question.class);
                question.setCategory("condiciones");
                question.setOrder(i);
                question.setQuestion(condiciones.get(i));
                question.setMultiple(false);
                if(condiciones.get(i).equals("Características de vivienda")){
                    question.setMultiple(true);
                    question.setMultiple1("departamento");
                    question.setMultiple2("casa");
                    question.setMultiple3("duplex");
                    question.setMultiple4("multifamiliar");
                    question.setMultiple5("otro");
                }
                if(condiciones.get(i).equals("Piso")){
                    question.setMultiple(true);
                    question.setMultiple1("losa");
                    question.setMultiple2("cemento");
                    question.setMultiple3("tierra");
                    question.setMultiple4("otro");
                }
                if(condiciones.get(i).equals("Techo")){
                    question.setMultiple(true);
                    question.setMultiple1("abesto");
                    question.setMultiple2("cemento");
                    question.setMultiple3("paja");
                    question.setMultiple4("otra");
                }
                if(condiciones.get(i).equals("Número de habitaciones")){
                    question.setMultiple(true);
                    question.setMultiple1("1 a 4");
                    question.setMultiple2("5 a 7");
                    question.setMultiple3("7 a 10");
                    question.setMultiple4("más de 10");
                }
                if(condiciones.get(i).equals("Servicios públicos")){
                    question.setMultiple(true);
                    question.setMultiple1("agua");
                    question.setMultiple2("drenaje");
                    question.setMultiple3("recolección basura");
                }
                if(condiciones.get(i).equals("Biomasa")){
                    question.setMultiple(true);
                    question.setMultiple1("gas");
                    question.setMultiple2("calefacción");
                    question.setMultiple3("leña");
                    question.setMultiple4("carbón");
                    question.setMultiple5("otro");
                }
                if(condiciones.get(i).equals("Zoonosis")){
                    question.setMultiple(true);
                    question.setMultiple1("si");
                    question.setMultiple2("no");
                }
                realm.commitTransaction();
            }

            ArrayList<String> alimentacion = new ArrayList<String>();
            alimentacion.add("Comidas");
            alimentacion.add("Tipo de Alimentos");

            for(int i = 0; i<alimentacion.size(); i++){
                realm.beginTransaction();
                final Question question = realm.createObject(Question.class);
                question.setCategory("alimentacion");
                question.setOrder(i);
                question.setQuestion(alimentacion.get(i));
                question.setMultiple(false);
                if(alimentacion.get(i).equals("Comidas")){
                    question.setMultiple(true);
                    question.setMultiple1("Menos de 2");
                    question.setMultiple2("2 a 3");
                    question.setMultiple3("Más de 3");
                }
                realm.commitTransaction();
            }

            ArrayList<String> familiares = new ArrayList<String>();
            familiares.add("Enfermedades Cardiovasculares");
            familiares.add("HTA");
            familiares.add("Diabetes");
            familiares.add("Dislipidemias");
            familiares.add("Obesidad");
            familiares.add("Enfermedades Cerebrovascular");

            for(int i = 0; i<familiares.size(); i++){
                realm.beginTransaction();
                final Question question = realm.createObject(Question.class);
                question.setCategory("familiares");
                question.setOrder(i);
                question.setQuestion(familiares.get(i));
                question.setMultiple(true);
                question.setMultiple1("Abuelos");
                question.setMultiple2("Padres");
                question.setMultiple3("Tios");
                question.setMultiple4("Hermanos");
                question.setMultiple5("Ninguno");
                realm.commitTransaction();
            }

            ArrayList<String> antecedentes = new ArrayList<String>();
            antecedentes.add("Enfermedades Cerebrovascular");
            antecedentes.add("Sobrepeso");
            antecedentes.add("VIH");
            antecedentes.add("Enfermedades Cardiovascules");
            antecedentes.add("Tabaquismo");
            antecedentes.add("Tuberculosis");
            antecedentes.add("Sedentarismo");
            antecedentes.add("Alcoholismo");
            antecedentes.add("Post Menopausia");

            for(int i = 0; i<antecedentes.size(); i++){
                realm.beginTransaction();
                final Question question = realm.createObject(Question.class);
                question.setCategory("antecedentes");
                question.setOrder(i);
                question.setQuestion(antecedentes.get(i));
                question.setMultiple(true);
                question.setMultiple1("sí");
                question.setMultiple2("no");
                realm.commitTransaction();
            }

            ArrayList<String> sistemas = new ArrayList<String>();
            sistemas.add("Respiratorio");
            sistemas.add("Cardiovascular");
            sistemas.add("Digestivo");
            sistemas.add("Urinario");
            sistemas.add("Reproductor");
            sistemas.add("Hemolinfático");
            sistemas.add("Endocrino");
            sistemas.add("Sistema Nervioso");
            sistemas.add("Músculo esquelético");
            sistemas.add("Piel y anexos");

            for(int i = 0; i<sistemas.size(); i++){
                realm.beginTransaction();
                final Question question = realm.createObject(Question.class);
                question.setCategory("sistemas");
                question.setOrder(i);
                question.setQuestion(sistemas.get(i));
                question.setMultiple(false);
                realm.commitTransaction();
            }



        } else {
            Log.d("Realm", "We have questions saved");
        }

        RealmResults<Question> personales = realm.where(Question.class).equalTo("category","personales").findAll();
        setPersonales(personales);
        setQuestions(new ArrayList(personales));

        RealmResults<Question> domiciliarios = realm.where(Question.class).equalTo("category","domiciliarios").findAll();
        setDomiciliarios(domiciliarios);

        RealmResults<Question> sociales = realm.where(Question.class).equalTo("category","sociales").findAll();
        setSociales(sociales);

        RealmResults<Question> condiciones = realm.where(Question.class).equalTo("category","condiciones").findAll();
        setCondiciones(condiciones);

        RealmResults<Question> alimentacion = realm.where(Question.class).equalTo("category","alimentacion").findAll();
        setAlimentacion(alimentacion);

        RealmResults<Question> familiareas = realm.where(Question.class).equalTo("category","familiares").findAll();
        setAntecedentesFamiliares(familiareas);

        RealmResults<Question> antecendentes = realm.where(Question.class).equalTo("category","antecedentes").findAll();
        setAntecedentes(antecendentes);

        RealmResults<Question> sistemas = realm.where(Question.class).equalTo("category","sistemas").findAll();
        setInterrogatorio(sistemas);

        random_uuid = UUID.randomUUID().toString();

        user = new User();
        user.setUserUUID(random_uuid);
        user.fecha = new Date();
        contexto = new Contexto();
        contexto.setUserUUID(random_uuid);
        historiaClinica = new HistoriaClinica();
        historiaClinica.setUserUUID(random_uuid);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.dialog_question, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        this.activity = getActivity();
        this.container = view;

        open = (LinearLayout) view.findViewById(R.id.open);
        multiple = (LinearLayout) view.findViewById(R.id.multiple);
        finish = (LinearLayout) view.findViewById(R.id.finishLayout);
        review = (LinearLayout) view.findViewById(R.id.ReviewLayout);


        question = (TextView) view.findViewById(R.id.question);
        title = (TextView) view.findViewById(R.id.title);
        answer = (EditText) view.findViewById(R.id.answer);
        category = (TextView) view.findViewById(R.id.category);
        multiple1 = (RadioButton) view.findViewById(R.id.multiple1);
        multiple2 = (RadioButton) view.findViewById(R.id.multiple2);
        multiple3 = (RadioButton) view.findViewById(R.id.multiple3);
        multiple4 = (RadioButton) view.findViewById(R.id.multiple4);
        multiple5 = (RadioButton) view.findViewById(R.id.multiple5);
        multiple6 = (RadioButton) view.findViewById(R.id.multiple6);
        multiple7 = (RadioButton) view.findViewById(R.id.multiple7);
        multiple8 = (RadioButton) view.findViewById(R.id.multiple8);

        multiples1 = (RadioGroup) view.findViewById(R.id.multiples1);
        multiples2 = (RadioGroup) view.findViewById(R.id.multiples2);
        multiples1.clearCheck(); // this is so we can start fresh, with no selection on both RadioGroups
        multiples2.clearCheck();
        multiples1.setOnCheckedChangeListener(listener1);
        multiples2.setOnCheckedChangeListener(listener2);

        loadQuestion();

        next = (ImageButton) view.findViewById(R.id.next);
        next.setOnClickListener(nextOnClickListener);

        imageLogo = (ImageView) view.findViewById(R.id.imageView8);
        imageIcon2 = (ImageView) view.findViewById(R.id.icon2);

        category.setText("Personales");
    }

    private RadioGroup.OnCheckedChangeListener listener1 = new RadioGroup.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId != -1) {
                multiples2.setOnCheckedChangeListener(null); // remove the listener before clearing so we don't throw that stackoverflow exception(like Vladimir Volodin pointed out)
                multiples2.clearCheck(); // clear the second RadioGroup!
                multiples2.setOnCheckedChangeListener(listener2); //reset the listener
            }
        }
    };

    private RadioGroup.OnCheckedChangeListener listener2 = new RadioGroup.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId != -1) {
                multiples1.setOnCheckedChangeListener(null); // remove the listener before clearing so we don't throw that stackoverflow exception(like Vladimir Volodin pointed out)
                multiples1.clearCheck(); // clear the second RadioGroup!
                multiples1.setOnCheckedChangeListener(listener1); //reset the listener
            }
        }
    };

    private View.OnClickListener nextOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if(currentQuestion.getMultiple()){
                int chkId1 = multiples1.getCheckedRadioButtonId();
                int chkId2 = multiples2.getCheckedRadioButtonId();
                int realCheck = chkId1 == -1 ? chkId2 : chkId1;
                RadioButton selectedRadioButton = (RadioButton) container.findViewById(realCheck);
                if(selectedRadioButton == null){
                    Toast.makeText(activity,
                            "Por favor selecciona una opción", Toast.LENGTH_SHORT).show();
                } else {
                    //Toast.makeText(activity,selectedRadioButton.getText(), Toast.LENGTH_SHORT).show();

                    if (checking == "domiciliarios"){
                        if (indexCurrentQuestion==3){user.setEstado_civil(selectedRadioButton.getText().toString());}
                        if (indexCurrentQuestion==4){user.setOcupacion(selectedRadioButton.getText().toString());}
                        if (indexCurrentQuestion==5){user.setDerechohabiente(selectedRadioButton.getText().toString());}
                    }
                    if (checking == "sociales"){
                        if (indexCurrentQuestion==0){contexto.setEscolaridad_maxima(selectedRadioButton.getText().toString());}
                        if (indexCurrentQuestion==1){contexto.setTiempo_de_escuela_trabajo(selectedRadioButton.getText().toString());}
                        if (indexCurrentQuestion==2){contexto.setTiempo_de_transporte(selectedRadioButton.getText().toString());}
                        if (indexCurrentQuestion==3){contexto.setTiempo_de_recreacion(selectedRadioButton.getText().toString());}
                        if (indexCurrentQuestion==4){contexto.setRealiza_alguna_actividad_fisica(selectedRadioButton.getText().toString());}
                        if (indexCurrentQuestion==5){contexto.setNivel_de_estres_actual(selectedRadioButton.getText().toString());}
                        if (indexCurrentQuestion==6){contexto.setCondicion_fisica_actual(selectedRadioButton.getText().toString());}
                        if (indexCurrentQuestion==7){contexto.setHoras_de_sueno_al_dia(selectedRadioButton.getText().toString());}
                        if (indexCurrentQuestion==8){contexto.setStatus_economico(selectedRadioButton.getText().toString());}
                    }
                    if (checking == "condiciones"){
                        if (indexCurrentQuestion==1){contexto.setCaracteristicas_de_vivienda(selectedRadioButton.getText().toString());}
                        if (indexCurrentQuestion==2){contexto.setPiso(selectedRadioButton.getText().toString());}
                        if (indexCurrentQuestion==3){contexto.setTecho(selectedRadioButton.getText().toString());}
                        if (indexCurrentQuestion==4){contexto.setNumero_de_habitaciones(selectedRadioButton.getText().toString());}
                        if (indexCurrentQuestion==5){contexto.setServicios_publicos(selectedRadioButton.getText().toString());}
                        if (indexCurrentQuestion==6){contexto.setBiomasa(selectedRadioButton.getText().toString());}
                        if (indexCurrentQuestion==7){contexto.setZoonosis(selectedRadioButton.getText().toString());}
                    }
                    if (checking == "alimentacion"){
                        if (indexCurrentQuestion==0){contexto.setComidas(selectedRadioButton.getText().toString());}
                    }
                    if (checking == "familiares"){
                        if (indexCurrentQuestion==0){historiaClinica.setEnfermedades_cardio(selectedRadioButton.getText().toString());}
                        if (indexCurrentQuestion==1){historiaClinica.setEnfermedades_hta(selectedRadioButton.getText().toString());}
                        if (indexCurrentQuestion==2){historiaClinica.setEnfermedades_diabetes(selectedRadioButton.getText().toString());}
                        if (indexCurrentQuestion==3){historiaClinica.setEnfermedades_dislipidemias(selectedRadioButton.getText().toString());}
                        if (indexCurrentQuestion==4){historiaClinica.setEnfermedades_obesidad(selectedRadioButton.getText().toString());}
                        if (indexCurrentQuestion==5){historiaClinica.setEnfermedades_cerebrovascular(selectedRadioButton.getText().toString());}
                    }
                    if (checking == "antecedentes"){
                        if (indexCurrentQuestion==0){historiaClinica.setCerebrovascular(selectedRadioButton.getText().toString());}
                        if (indexCurrentQuestion==1){historiaClinica.setSobrepeso(selectedRadioButton.getText().toString());}
                        if (indexCurrentQuestion==2){historiaClinica.setVih(selectedRadioButton.getText().toString());}
                        if (indexCurrentQuestion==3){historiaClinica.setEnfermedades_cardiovascules(selectedRadioButton.getText().toString());}
                        if (indexCurrentQuestion==4){historiaClinica.setTabaquismo(selectedRadioButton.getText().toString());}
                        if (indexCurrentQuestion==5){historiaClinica.setTuberculosis(selectedRadioButton.getText().toString());}
                        if (indexCurrentQuestion==6){historiaClinica.setSedentarismo(selectedRadioButton.getText().toString());}
                        if (indexCurrentQuestion==7){historiaClinica.setAlcoholismo(selectedRadioButton.getText().toString());}
                        if (indexCurrentQuestion==8){historiaClinica.setPost_menopausia(selectedRadioButton.getText().toString());}
                    }

                    indexCurrentQuestion++;
                    if (indexCurrentQuestion < questions.size()){
                        loadQuestion();
                    } else {
                        if (checking.equals("personales")){
                            checking = "domiciliarios";
                            category.setText("Domiciliarios");
                            title.setText(checking.toUpperCase());
                            imageLogo.setImageResource(R.drawable.icon_domiciliarios);
                            setQuestions(new ArrayList(domiciliarios));
                            indexCurrentQuestion = 0;
                            loadQuestion();
                        } else if (checking.equals("domiciliarios")){
                            checking = "sociales";
                            category.setText("Antecedentes no Patológicos");
                            imageIcon2.setImageResource(R.drawable.number_two_pink);
                            title.setText(checking.toUpperCase());
                            imageLogo.setImageResource(R.drawable.icon_interrogatorio);
                            setQuestions(new ArrayList(sociales));
                            indexCurrentQuestion = 0;
                            loadQuestion();
                        } else if (checking.equals("sociales")){
                            checking = "condiciones";
                            category.setText("Condiciones y Servicios del Hogar");
                            title.setText(checking.toUpperCase());
                            imageLogo.setImageResource(R.drawable.icon_condiciones);
                            setQuestions(new ArrayList(condiciones));
                            indexCurrentQuestion = 0;
                            loadQuestion();
                        } else if (checking.equals("condiciones")){
                            checking = "alimentación";
                            category.setText("Alimentación");
                            title.setText(checking.toUpperCase());
                            imageLogo.setImageResource(R.drawable.icon_alimentacion);
                            setQuestions(new ArrayList(alimentacion));
                            indexCurrentQuestion = 0;
                            loadQuestion();
                        } else if (checking.equals("alimentación")){
                            checking = "familiares";
                            category.setText("Antecedentes (familiares)");
                            title.setText(checking.toUpperCase());
                            imageLogo.setImageResource(R.drawable.icon_antecedentes);
                            setQuestions(new ArrayList(antecedentesFamiliares));
                            indexCurrentQuestion = 0;
                            loadQuestion();
                        } else if (checking.equals("familiares")){
                            checking = "antecedentes";
                            category.setText("Antecedentes (personales)");
                            title.setText(checking.toUpperCase());
                            imageLogo.setImageResource(R.drawable.icon_antecedentes);
                            setQuestions(new ArrayList(antecedentes));
                            indexCurrentQuestion = 0;
                            loadQuestion();
                        } else if (checking.equals("antecedentes")) {
                            checking = "sistemas";
                            category.setText("Interrogatorio por Sistemas");
                            title.setText(checking.toUpperCase());
                            imageLogo.setImageResource(R.drawable.icon_interrogatorio);
                            setQuestions(new ArrayList(interrogatorio));
                            indexCurrentQuestion = 0;
                            loadQuestion();
                        } else{


                            Realm realm = Realm.getDefaultInstance();

                            realm.executeTransaction(new Realm.Transaction() {
                                @Override
                                public void execute(Realm realm) {
                                    realm.copyToRealmOrUpdate(user);
                                }
                            });

                            realm.executeTransaction(new Realm.Transaction() {
                                @Override
                                public void execute(Realm realm) {
                                    realm.copyToRealmOrUpdate(contexto);
                                }
                            });

//                            String[] textArray = {"One", "Two", "Three", "Four"};
//                            for( int i = 0; i < textArray.length; i++ )
//                            {
//                                TextView textView = new TextView(getActivity());
//                                textView.setText(textArray[i]);
//                                review.addView(textView);
//                            }

                            finish.setVisibility(View.VISIBLE);

                            //Toast.makeText(activity,"No more", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            } else {

                if (checking.equals("personales")){
                    if (indexCurrentQuestion==0){user.setcURP(answer.getText().toString());}
                    if (indexCurrentQuestion==1){user.setApellido_Paterno(answer.getText().toString());}
                    if (indexCurrentQuestion==2){user.setApellido_Materno(answer.getText().toString());}
                    if (indexCurrentQuestion==3){user.setNombre(answer.getText().toString());}
                    if (indexCurrentQuestion==4){user.setFecha_de_Nacimiento(answer.getText().toString());}
                    if (indexCurrentQuestion==5){user.setEstado_de_Nacimiento(answer.getText().toString());}
                    if (indexCurrentQuestion==6){user.setSexo(answer.getText().toString());}
                    if (indexCurrentQuestion==7){user.setNacionalidad_de_Origen(answer.getText().toString());}
                }
                if (checking.equals("domiciliarios")){
                    if (indexCurrentQuestion==0){user.setEstado(answer.getText().toString());}
                    if (indexCurrentQuestion==1){user.setMunicipio(answer.getText().toString());}
                    if (indexCurrentQuestion==2){user.setLocalidad_colonia(answer.getText().toString());}
                    if (indexCurrentQuestion==6){user.setTelefono_fijo(answer.getText().toString());}
                    if (indexCurrentQuestion==7){user.setTelefono_celular(answer.getText().toString());}
                }
                if(checking.equals("sociales")){
                    if (indexCurrentQuestion==9){contexto.setIngreso_mensual(answer.getText().toString());}
                    if (indexCurrentQuestion==10){contexto.setEgreso_mensual(answer.getText().toString());}
                }
                if (checking.equals("condiciones")){
                    if (indexCurrentQuestion==0){contexto.setEquipos_electrodomesticos(answer.getText().toString());}
                }
                if (checking.equals("alimentacion")){
                    if (indexCurrentQuestion==0){contexto.setTipo_de_alimentos(answer.getText().toString());}
                }
                if (checking == "sistemas"){
                    if (indexCurrentQuestion==0){historiaClinica.setRespiratorio(answer.getText().toString());}
                    if (indexCurrentQuestion==1){historiaClinica.setCardiovascular(answer.getText().toString());}
                    if (indexCurrentQuestion==2){historiaClinica.setDigestivo(answer.getText().toString());}
                    if (indexCurrentQuestion==3){historiaClinica.setUrinario(answer.getText().toString());}
                    if (indexCurrentQuestion==4){historiaClinica.setReproductor(answer.getText().toString());}
                    if (indexCurrentQuestion==5){historiaClinica.setHemolinfatico(answer.getText().toString());}
                    if (indexCurrentQuestion==6){historiaClinica.setEndocrino(answer.getText().toString());}
                    if (indexCurrentQuestion==7){historiaClinica.setSistema_nervioso(answer.getText().toString());}
                    if (indexCurrentQuestion==8){historiaClinica.setMusculo_esqueletico(answer.getText().toString());}
                    if (indexCurrentQuestion==9){historiaClinica.setPiel_y_anexos(answer.getText().toString());}
                }

                indexCurrentQuestion++;
                if (indexCurrentQuestion < questions.size()){
                    loadQuestion();
                } else {
                    if (checking.equals("personales")){
                        checking = "domiciliarios";
                        category.setText("Domiciliarios");
                        title.setText(checking.toUpperCase());
                        imageLogo.setImageResource(R.drawable.icon_domiciliarios);
                        setQuestions(new ArrayList(domiciliarios));
                        indexCurrentQuestion = 0;
                        loadQuestion();
                    } else if (checking.equals("domiciliarios")){
                        checking = "sociales";
                        imageLogo.setImageResource(R.drawable.icon_interrogatorio);
                        category.setText("Antecedentes no Patológicos");
                        title.setText(checking.toUpperCase());
                        imageIcon2.setImageResource(R.drawable.number_two_pink);
                        imageLogo.setImageResource(R.drawable.icon_interrogatorio);
                        setQuestions(new ArrayList(sociales));
                        indexCurrentQuestion = 0;
                        loadQuestion();
                    } else if (checking.equals("sociales")){
                        checking = "condiciones";
                        category.setText("Condiciones y Servicios del Hogar");
                        title.setText(checking.toUpperCase());
                        imageLogo.setImageResource(R.drawable.icon_condiciones);
                        setQuestions(new ArrayList(condiciones));
                        indexCurrentQuestion = 0;
                        loadQuestion();
                    } else if (checking.equals("condiciones")){
                        checking = "alimentación";
                        category.setText("Alimentación");
                        title.setText(checking.toUpperCase());
                        imageLogo.setImageResource(R.drawable.icon_alimentacion);
                        setQuestions(new ArrayList(alimentacion));
                        indexCurrentQuestion = 0;
                        loadQuestion();
                    }  else if (checking.equals("alimentación")){
                        checking = "familiares";
                        category.setText("Antecedentes (familiares)");
                        title.setText(checking.toUpperCase());
                        imageLogo.setImageResource(R.drawable.icon_antecedentes);
                        setQuestions(new ArrayList(antecedentesFamiliares));
                        indexCurrentQuestion = 0;
                        loadQuestion();
                    } else if (checking.equals("familiares")){
                        checking = "antecedentes";
                        category.setText("Antecedentes (personales)");
                        title.setText(checking.toUpperCase());
                        imageLogo.setImageResource(R.drawable.icon_antecedentes);
                        setQuestions(new ArrayList(antecedentes));
                        indexCurrentQuestion = 0;
                        loadQuestion();
                    } else if (checking.equals("antecedentes")) {
                        checking = "sistemas";
                        category.setText("Interrogatorio por Sistemas");
                        title.setText(checking.toUpperCase());
                        imageLogo.setImageResource(R.drawable.icon_interrogatorio);
                        setQuestions(new ArrayList(interrogatorio));
                        indexCurrentQuestion = 0;
                        loadQuestion();
                    } else{

                        Realm realm = Realm.getDefaultInstance();

                        realm.executeTransaction(new Realm.Transaction() {
                            @Override
                            public void execute(Realm realm) {
                                realm.copyToRealmOrUpdate(user);
                            }
                        });

                        realm.executeTransaction(new Realm.Transaction() {
                            @Override
                            public void execute(Realm realm) {
                                realm.copyToRealmOrUpdate(contexto);
                            }
                        });

//
//                        for( int i = 0; i < personales.size(); i++ )
//                        {
//                            LinearLayout horizontal = new LinearLayout(getActivity());
//                            horizontal.setOrientation(LinearLayout.HORIZONTAL);
//
//                            LinearLayout vertical = new LinearLayout(getActivity());
//                            vertical.setOrientation(LinearLayout.VERTICAL);
//
//                            TextView textView = new TextView(getActivity());
//                            textView.setText(personales.get(i).getQuestion());
//
//                            EditText textView2 = new EditText(getActivity());
//                            textView2.setText(user.getcURP());
//
//                            vertical.addView(textView);
//                            vertical.addView(textView2);
//                            horizontal.addView(vertical);
//                            review.addView(horizontal);
//                        }

                        finish.setVisibility(View.VISIBLE);
                        //Toast.makeText(activity, "No more", Toast.LENGTH_SHORT).show();
                    }
                }
            }


        }
    };

    public void loadQuestion(){
        currentQuestion = questions.get(indexCurrentQuestion);

        question.setText((indexCurrentQuestion+1)+".- "+currentQuestion.getQuestion());
        if (currentQuestion.getMultiple()){
            multiple1.setChecked(false);
            multiple1.setText(currentQuestion.getMultiple1());
            multiple2.setChecked(false);
            multiple2.setText(currentQuestion.getMultiple2());
            multiple3.setChecked(false);
            multiple3.setText(currentQuestion.getMultiple3());
            multiple4.setChecked(false);
            multiple4.setText(currentQuestion.getMultiple4());
            multiple5.setChecked(false);
            multiple5.setText(currentQuestion.getMultiple5());
            multiple6.setChecked(false);
            multiple6.setText(currentQuestion.getMultiple6());
            multiple7.setChecked(false);
            multiple7.setText(currentQuestion.getMultiple7());
            multiple8.setChecked(false);
            multiple8.setText(currentQuestion.getMultiple8());
            if(currentQuestion.getMultiple1() == null){multiple1.setVisibility(View.GONE);}else{multiple1.setVisibility(View.VISIBLE);}
            if(currentQuestion.getMultiple2() == null){multiple2.setVisibility(View.GONE);}else{multiple2.setVisibility(View.VISIBLE);}
            if(currentQuestion.getMultiple3() == null){multiple3.setVisibility(View.GONE);}else{multiple3.setVisibility(View.VISIBLE);}
            if(currentQuestion.getMultiple4() == null){multiple4.setVisibility(View.GONE);}else{multiple4.setVisibility(View.VISIBLE);}
            if(currentQuestion.getMultiple5() == null){multiple5.setVisibility(View.GONE);}else{multiple5.setVisibility(View.VISIBLE);}
            if(currentQuestion.getMultiple6() == null){multiple6.setVisibility(View.GONE);}else{multiple6.setVisibility(View.VISIBLE);}
            if(currentQuestion.getMultiple7() == null){multiple7.setVisibility(View.GONE);}else{multiple7.setVisibility(View.VISIBLE);}
            if(currentQuestion.getMultiple8() == null){multiple8.setVisibility(View.GONE);}else{multiple8.setVisibility(View.VISIBLE);}

            multiple.setVisibility(View.VISIBLE);
            open.setVisibility(View.GONE);
        } else {
            answer.setText("");
            multiple.setVisibility(View.GONE);
            open.setVisibility(View.VISIBLE);
        }
    }

    public void saveGenerales(){

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
