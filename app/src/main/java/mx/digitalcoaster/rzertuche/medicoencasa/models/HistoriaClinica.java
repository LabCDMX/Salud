package mx.digitalcoaster.rzertuche.medicoencasa.models;

import java.util.Date;

import io.realm.RealmObject;

/**
 * Created by zertuche on 4/2/18.
 */

public class HistoriaClinica extends RealmObject{

    public String userUUID;
    public Date fecha;

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

    public String getEnfermedades_cardio() {
        return enfermedades_cardio;
    }

    public void setEnfermedades_cardio(String enfermedades_cardio) {
        this.enfermedades_cardio = enfermedades_cardio;
    }

    public String getEnfermedades_hta() {
        return enfermedades_hta;
    }

    public void setEnfermedades_hta(String enfermedades_hta) {
        this.enfermedades_hta = enfermedades_hta;
    }

    public String getEnfermedades_diabetes() {
        return enfermedades_diabetes;
    }

    public void setEnfermedades_diabetes(String enfermedades_diabetes) {
        this.enfermedades_diabetes = enfermedades_diabetes;
    }

    public String getEnfermedades_dislipidemias() {
        return enfermedades_dislipidemias;
    }

    public void setEnfermedades_dislipidemias(String enfermedades_dislipidemias) {
        this.enfermedades_dislipidemias = enfermedades_dislipidemias;
    }

    public String getEnfermedades_obesidad() {
        return enfermedades_obesidad;
    }

    public void setEnfermedades_obesidad(String enfermedades_obesidad) {
        this.enfermedades_obesidad = enfermedades_obesidad;
    }

    public String getEnfermedades_cerebrovascular() {
        return enfermedades_cerebrovascular;
    }

    public void setEnfermedades_cerebrovascular(String enfermedades_cerebrovascular) {
        this.enfermedades_cerebrovascular = enfermedades_cerebrovascular;
    }

    public String getCerebrovascular() {
        return cerebrovascular;
    }

    public void setCerebrovascular(String cerebrovascular) {
        this.cerebrovascular = cerebrovascular;
    }

    public String getSobrepeso() {
        return sobrepeso;
    }

    public void setSobrepeso(String sobrepeso) {
        this.sobrepeso = sobrepeso;
    }

    public String getVih() {
        return vih;
    }

    public void setVih(String vih) {
        this.vih = vih;
    }

    public String getEnfermedades_cardiovascules() {
        return enfermedades_cardiovascules;
    }

    public void setEnfermedades_cardiovascules(String enfermedades_cardiovascules) {
        this.enfermedades_cardiovascules = enfermedades_cardiovascules;
    }

    public String getTabaquismo() {
        return tabaquismo;
    }

    public void setTabaquismo(String tabaquismo) {
        this.tabaquismo = tabaquismo;
    }

    public String getTuberculosis() {
        return tuberculosis;
    }

    public void setTuberculosis(String tuberculosis) {
        this.tuberculosis = tuberculosis;
    }

    public String getSedentarismo() {
        return sedentarismo;
    }

    public void setSedentarismo(String sedentarismo) {
        this.sedentarismo = sedentarismo;
    }

    public String getAlcoholismo() {
        return alcoholismo;
    }

    public void setAlcoholismo(String alcoholismo) {
        this.alcoholismo = alcoholismo;
    }

    public String getPost_menopausia() {
        return post_menopausia;
    }

    public void setPost_menopausia(String post_menopausia) {
        this.post_menopausia = post_menopausia;
    }

    public String getHemotipo() {
        return hemotipo;
    }

    public void setHemotipo(String hemotipo) {
        this.hemotipo = hemotipo;
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

    public String getRespiratorio() {
        return respiratorio;
    }

    public void setRespiratorio(String respiratorio) {
        this.respiratorio = respiratorio;
    }

    public String getCardiovascular() {
        return cardiovascular;
    }

    public void setCardiovascular(String cardiovascular) {
        this.cardiovascular = cardiovascular;
    }

    public String getDigestivo() {
        return digestivo;
    }

    public void setDigestivo(String digestivo) {
        this.digestivo = digestivo;
    }

    public String getUrinario() {
        return urinario;
    }

    public void setUrinario(String urinario) {
        this.urinario = urinario;
    }

    public String getReproductor() {
        return reproductor;
    }

    public void setReproductor(String reproductor) {
        this.reproductor = reproductor;
    }

    public String getHemolinfatico() {
        return hemolinfatico;
    }

    public void setHemolinfatico(String hemolinfatico) {
        this.hemolinfatico = hemolinfatico;
    }

    public String getEndocrino() {
        return endocrino;
    }

    public void setEndocrino(String endocrino) {
        this.endocrino = endocrino;
    }

    public String getSistema_nervioso() {
        return sistema_nervioso;
    }

    public void setSistema_nervioso(String sistema_nervioso) {
        this.sistema_nervioso = sistema_nervioso;
    }

    public String getMusculo_esqueletico() {
        return musculo_esqueletico;
    }

    public void setMusculo_esqueletico(String musculo_esqueletico) {
        this.musculo_esqueletico = musculo_esqueletico;
    }

    public String getPiel_y_anexos() {
        return piel_y_anexos;
    }

    public void setPiel_y_anexos(String piel_y_anexos) {
        this.piel_y_anexos = piel_y_anexos;
    }

    public String getHabitus_exterior() {
        return habitus_exterior;
    }

    public void setHabitus_exterior(String habitus_exterior) {
        this.habitus_exterior = habitus_exterior;
    }

    public String getCabeza() {
        return cabeza;
    }

    public void setCabeza(String cabeza) {
        this.cabeza = cabeza;
    }

    public String getCuello() {
        return cuello;
    }

    public void setCuello(String cuello) {
        this.cuello = cuello;
    }

    public String getTorax() {
        return torax;
    }

    public void setTorax(String torax) {
        this.torax = torax;
    }

    public String getAbdomen() {
        return abdomen;
    }

    public void setAbdomen(String abdomen) {
        this.abdomen = abdomen;
    }

    public String getExploracion_ginecologica() {
        return exploracion_ginecologica;
    }

    public void setExploracion_ginecologica(String exploracion_ginecologica) {
        this.exploracion_ginecologica = exploracion_ginecologica;
    }

    public String getExtremidades() {
        return extremidades;
    }

    public void setExtremidades(String extremidades) {
        this.extremidades = extremidades;
    }

    public String getColumna_vertebral() {
        return columna_vertebral;
    }

    public void setColumna_vertebral(String columna_vertebral) {
        this.columna_vertebral = columna_vertebral;
    }

    public String getExploracion_neurologica() {
        return exploracion_neurologica;
    }

    public void setExploracion_neurologica(String exploracion_neurologica) {
        this.exploracion_neurologica = exploracion_neurologica;
    }

    public String getGenitales() {
        return genitales;
    }

    public void setGenitales(String genitales) {
        this.genitales = genitales;
    }

    //Antecedentes
    public String enfermedades_cardio;
    public String enfermedades_hta;
    public String enfermedades_diabetes;
    public String enfermedades_dislipidemias;
    public String enfermedades_obesidad;
    public String enfermedades_cerebrovascular;
    public String cerebrovascular;
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
