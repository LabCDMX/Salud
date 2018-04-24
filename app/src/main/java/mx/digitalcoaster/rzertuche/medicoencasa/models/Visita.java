package mx.digitalcoaster.rzertuche.medicoencasa.models;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by zertuche on 4/2/18.
 */

public class Visita extends RealmObject{

    //General
    @PrimaryKey
    public String visitUUID;
    public String userUUID;
    public Date fecha;

    public String peso;
    public String estatura;
    public String tension_arterial;
    public String frecuencia_cardiaca;
    public String frecuencia_respiratoria;
    public String talla;
    public String pulso;
    public String glucemia;
    public String nota;
    public String tratamiento;

    public String getUserUUID() {
        return userUUID;
    }

    public void setUserUUID(String userUUID) {
        this.userUUID = userUUID;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getEstatura() {
        return estatura;
    }

    public void setEstatura(String estatura) {
        this.estatura = estatura;
    }

    public String getTension_arterial() {
        return tension_arterial;
    }

    public void setTension_arterial(String tension_arterial) {
        this.tension_arterial = tension_arterial;
    }

    public String getFrecuencia_cardiaca() {
        return frecuencia_cardiaca;
    }

    public void setFrecuencia_cardiaca(String frecuencia_cardiaca) {
        this.frecuencia_cardiaca = frecuencia_cardiaca;
    }

    public String getFrecuencia_respiratoria() {
        return frecuencia_respiratoria;
    }

    public void setFrecuencia_respiratoria(String frecuencia_respiratoria) {
        this.frecuencia_respiratoria = frecuencia_respiratoria;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public String getPulso() {
        return pulso;
    }

    public void setPulso(String pulso) {
        this.pulso = pulso;
    }

    public String getGlucemia() {
        return glucemia;
    }

    public void setGlucemia(String glucemia) {
        this.glucemia = glucemia;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }
}
