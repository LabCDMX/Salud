package mx.digitalcoaster.rzertuche.medicoencasa.models;

public class ItemVisita {

    /*---------------------------------- Datos de lista de clientes ------------------------------*/
    private String nombre;
    private String diagnostico;
    private String tratamiento;
    private String fecha_visita;
    private String numero_visita;


    /*--------------------------------- Datos de la lista de agendados ---------------------------*/


    /**----------------------------- Contructor para inflar los clientes -------------------------*/
    public ItemVisita(String nombre, String diagnostico, String tratamiento, String fecha_visita, String numero_visita){
        this.nombre = nombre;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
        this.fecha_visita = fecha_visita;
        this.numero_visita = numero_visita;


    }


    public String getNombre() {
        return nombre;
    }
    public String getDiagnostico() {
        return diagnostico;
    }
    public String getTratamiento() {
        return tratamiento;
    }
    public String getFecha_visita() {
        return fecha_visita;
    }
    public String getNumero_visita() {
        return numero_visita;
    }


}
