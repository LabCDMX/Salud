package mx.digitalcoaster.rzertuche.medicoencasa.models;

public class ItemVisita {

    /*---------------------------------- Datos de lista de clientes ------------------------------*/
    private String nombre;
    private String diagnostico;
    private String tratamiento;
    private String fecha_visita;
    private String numero_visita;
    private String curp_visita;
    private String no_expediente;



    /*--------------------------------- Datos de la lista de agendados ---------------------------*/


    /**----------------------------- Contructor para inflar los clientes -------------------------*/
    public ItemVisita(String nombre, String curp_visita, String diagnostico, String tratamiento, String fecha_visita, String numero_visita, String no_expediente){
        this.nombre = nombre;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
        this.fecha_visita = fecha_visita;
        this.numero_visita = numero_visita;
        this.curp_visita = curp_visita;
        this.no_expediente = no_expediente;


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
    public String getCurp_visita() {
        return curp_visita;
    }
    public String getNo_expediente() {
        return no_expediente;
    }





}
