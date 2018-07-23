package mx.digitalcoaster.rzertuche.medicoencasa.models;

public class Item {

    /*---------------------------------- Datos de lista de clientes ------------------------------*/
    private String nombre;
    private String curp;
    private String direccion;
    private String status;
    private Boolean makeHojaDiaria;
    private Boolean checkHojaDiaria;
    private String numero_visita;
    private String expediente;



    /*--------------------------------- Datos de la lista de agendados ---------------------------*/


    /**----------------------------- Contructor para inflar los clientes -------------------------*/
    public Item(String nombre, String curp, String direccion){
        this.nombre = nombre;
        this.curp = curp;
        this.direccion = direccion;
    }

    public Item(String nombre, String curp, String numero_visita, String direccion, String status, Boolean makeHojaDiaria, Boolean checkHojaDiaria, String expediente){
        this.nombre = nombre;
        this.curp = curp;
        this.numero_visita = numero_visita;
        this.direccion = direccion;
        this.status = status;
        this.makeHojaDiaria = makeHojaDiaria;
        this.checkHojaDiaria = checkHojaDiaria;
        this.expediente = expediente;

    }


    public String getNombre() {
        return nombre;
    }
    public String getCurp() {
        return curp;
    }
    public String getDireccion() {
        return direccion;
    }
    public String getStatus() {
        return status;
    }
    public Boolean getMakeHojaDiaria() {
        return makeHojaDiaria;
    }
    public Boolean getCheckHojaDiaria() {
        return checkHojaDiaria;
    }
    public String getNumero_visita() {
        return numero_visita;
    }
    public String getExpediente() {
        return expediente;
    }

}
