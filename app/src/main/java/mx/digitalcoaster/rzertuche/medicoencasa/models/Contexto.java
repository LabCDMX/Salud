package mx.digitalcoaster.rzertuche.medicoencasa.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by zertuche on 4/2/18.
 */

public class Contexto extends RealmObject{

    @PrimaryKey
    public String userUUID;

    public String getUserUUID() {
        return userUUID;
    }

    public void setUserUUID(String userUUID) {
        this.userUUID = userUUID;
    }

    public String getEscolaridad_maxima() {
        return escolaridad_maxima;
    }

    public void setEscolaridad_maxima(String escolaridad_maxima) {
        this.escolaridad_maxima = escolaridad_maxima;
    }

    public String getTiempo_de_escuela_trabajo() {
        return tiempo_de_escuela_trabajo;
    }

    public void setTiempo_de_escuela_trabajo(String tiempo_de_escuela_trabajo) {
        this.tiempo_de_escuela_trabajo = tiempo_de_escuela_trabajo;
    }

    public String getTiempo_de_transporte() {
        return tiempo_de_transporte;
    }

    public void setTiempo_de_transporte(String tiempo_de_transporte) {
        this.tiempo_de_transporte = tiempo_de_transporte;
    }

    public String getTiempo_de_recreacion() {
        return tiempo_de_recreacion;
    }

    public void setTiempo_de_recreacion(String tiempo_de_recreacion) {
        this.tiempo_de_recreacion = tiempo_de_recreacion;
    }

    public String getRealiza_alguna_actividad_fisica() {
        return realiza_alguna_actividad_fisica;
    }

    public void setRealiza_alguna_actividad_fisica(String realiza_alguna_actividad_fisica) {
        this.realiza_alguna_actividad_fisica = realiza_alguna_actividad_fisica;
    }

    public String getNivel_de_estres_actual() {
        return nivel_de_estres_actual;
    }

    public void setNivel_de_estres_actual(String nivel_de_estres_actual) {
        this.nivel_de_estres_actual = nivel_de_estres_actual;
    }

    public String getCondicion_fisica_actual() {
        return condicion_fisica_actual;
    }

    public void setCondicion_fisica_actual(String condicion_fisica_actual) {
        this.condicion_fisica_actual = condicion_fisica_actual;
    }

    public String getHoras_de_sueno_al_dia() {
        return horas_de_sueno_al_dia;
    }

    public void setHoras_de_sueno_al_dia(String horas_de_sueno_al_dia) {
        this.horas_de_sueno_al_dia = horas_de_sueno_al_dia;
    }

    public String getStatus_economico() {
        return status_economico;
    }

    public void setStatus_economico(String status_economico) {
        this.status_economico = status_economico;
    }

    public String getIngreso_mensual() {
        return ingreso_mensual;
    }

    public void setIngreso_mensual(String ingreso_mensual) {
        this.ingreso_mensual = ingreso_mensual;
    }

    public String getEgreso_mensual() {
        return egreso_mensual;
    }

    public void setEgreso_mensual(String egreso_mensual) {
        this.egreso_mensual = egreso_mensual;
    }

    public String getEquipos_electrodomesticos() {
        return equipos_electrodomesticos;
    }

    public void setEquipos_electrodomesticos(String equipos_electrodomesticos) {
        this.equipos_electrodomesticos = equipos_electrodomesticos;
    }

    public String getCaracteristicas_de_vivienda() {
        return caracteristicas_de_vivienda;
    }

    public void setCaracteristicas_de_vivienda(String caracteristicas_de_vivienda) {
        this.caracteristicas_de_vivienda = caracteristicas_de_vivienda;
    }

    public String getPiso() {
        return piso;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public String getTecho() {
        return techo;
    }

    public void setTecho(String techo) {
        this.techo = techo;
    }

    public String getNumero_de_habitaciones() {
        return numero_de_habitaciones;
    }

    public void setNumero_de_habitaciones(String numero_de_habitaciones) {
        this.numero_de_habitaciones = numero_de_habitaciones;
    }

    public String getServicios_publicos() {
        return servicios_publicos;
    }

    public void setServicios_publicos(String servicios_publicos) {
        this.servicios_publicos = servicios_publicos;
    }

    public String getBiomasa() {
        return biomasa;
    }

    public void setBiomasa(String biomasa) {
        this.biomasa = biomasa;
    }

    public String getZoonosis() {
        return zoonosis;
    }

    public void setZoonosis(String zoonosis) {
        this.zoonosis = zoonosis;
    }

    public String getComidas() {
        return comidas;
    }

    public void setComidas(String comidas) {
        this.comidas = comidas;
    }

    public String getTipo_de_alimentos() {
        return tipo_de_alimentos;
    }

    public void setTipo_de_alimentos(String tipo_de_alimentos) {
        this.tipo_de_alimentos = tipo_de_alimentos;
    }

    //Contexto
    public String escolaridad_maxima;
    public String tiempo_de_escuela_trabajo;
    public String tiempo_de_transporte;
    public String tiempo_de_recreacion;
    public String realiza_alguna_actividad_fisica;
    public String nivel_de_estres_actual;
    public String condicion_fisica_actual;
    public String horas_de_sueno_al_dia;
    public String status_economico;
    public String ingreso_mensual;
    public String egreso_mensual;

    //Hogar
    public String equipos_electrodomesticos;
    public String caracteristicas_de_vivienda;
    public String piso;
    public String techo;
    public String numero_de_habitaciones;
    public String servicios_publicos;
    public String biomasa;
    public String zoonosis;

    //Alimentacion
    public String comidas;
    public String tipo_de_alimentos;

}
