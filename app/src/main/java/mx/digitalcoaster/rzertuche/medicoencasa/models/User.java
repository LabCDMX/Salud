package mx.digitalcoaster.rzertuche.medicoencasa.models;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by zertuche on 4/2/18.
 */

public class User extends RealmObject{

    //General
    @PrimaryKey
    public String userUUID;
    public Date fecha;

    public String cURP;

    public String apellido_Paterno;
    public String apellido_Materno;
    public String nombre;
    public String fecha_de_Nacimiento;
    public String estado_de_Nacimiento;
    public String sexo;
    public String nacionalidad_de_Origen;

    //Domicilio
    public String estado;
    public String municipio;
    public String localidad_colonia;
    public String estado_civil;
    public String ocupacion;
    public String derechohabiente;
    public String telefono_fijo;
    public String telefono_celular;
    public String correo_electronico;

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

    public String getcURP() {
        return cURP;
    }

    public void setcURP(String cURP) {
        this.cURP = cURP;
    }

    public String getApellido_Paterno() {
        return apellido_Paterno;
    }

    public void setApellido_Paterno(String apellido_Paterno) {
        this.apellido_Paterno = apellido_Paterno;
    }

    public String getApellido_Materno() {
        return apellido_Materno;
    }

    public void setApellido_Materno(String apellido_Materno) {
        this.apellido_Materno = apellido_Materno;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha_de_Nacimiento() {
        return fecha_de_Nacimiento;
    }

    public void setFecha_de_Nacimiento(String fecha_de_Nacimiento) {
        this.fecha_de_Nacimiento = fecha_de_Nacimiento;
    }

    public String getEstado_de_Nacimiento() {
        return estado_de_Nacimiento;
    }

    public void setEstado_de_Nacimiento(String estado_de_Nacimiento) {
        this.estado_de_Nacimiento = estado_de_Nacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getNacionalidad_de_Origen() {
        return nacionalidad_de_Origen;
    }

    public void setNacionalidad_de_Origen(String nacionalidad_de_Origen) {
        this.nacionalidad_de_Origen = nacionalidad_de_Origen;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getLocalidad_colonia() {
        return localidad_colonia;
    }

    public void setLocalidad_colonia(String localidad_colonia) {
        this.localidad_colonia = localidad_colonia;
    }

    public String getEstado_civil() {
        return estado_civil;
    }

    public void setEstado_civil(String estado_civil) {
        this.estado_civil = estado_civil;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    public String getDerechohabiente() {
        return derechohabiente;
    }

    public void setDerechohabiente(String derechohabiente) {
        this.derechohabiente = derechohabiente;
    }

    public String getTelefono_fijo() {
        return telefono_fijo;
    }

    public void setTelefono_fijo(String telefono_fijo) {
        this.telefono_fijo = telefono_fijo;
    }

    public String getTelefono_celular() {
        return telefono_celular;
    }

    public void setTelefono_celular(String telefono_celular) {
        this.telefono_celular = telefono_celular;
    }

    public String getCorreo_electronico() {
        return correo_electronico;
    }

    public void setCorreo_electronico(String correo_electronico) {
        this.correo_electronico = correo_electronico;
    }
}
