package mx.digitalcoaster.rzertuche.medicoencasa.models;

import java.util.Date;

import io.realm.RealmObject;

/**
 * Created by zertuche on 4/2/18.
 */

public class HistoriaClinica extends RealmObject{

    public String userUUID;
    public Date fecha;

    //Antecedentes
    public String enfermedades_abuelos;
    public String enfermedades_padres;
    public String enfermedades_tios;
    public String enfermedades_hermanos;
    public String enfermedades_ninguno;
    public String enfermedades_cerebrovascular;
    public String sobrepeso;
    public String vih;
    public String enfermedades_cardiovascules;
    public String tabaquismo;
    public String tuberculosis;
    public String sedentarismo;
    public String alcoholismo;
    public String post_menopausia;
    //Exploracion
    public String hemotipo;
    public String peso;
    public String estatura;
    public String tension_arterial;
    public String frecuencia_cardiaca;
    public String frecuencia_respiratoria;
    public String talla;
    public String pulso;
    //Sistema
    public String respiratorio;
    public String cardiovascular;
    public String digestivo;
    public String urinario;
    public String reproductor;
    public String hemolinfatico;
    public String endocrino;
    public String sistema_nervioso;
    public String musculo_esqueletico;
    public String piel_y_anexos;
    public String habitus_exterior;
    public String cabeza;
    public String cuello;
    public String torax;
    public String abdomen;
    public String exploracion_ginecologica;
    public String extremidades;
    public String columna_vertebral;
    public String exploracion_neurologica;
    public String genitales;

}
