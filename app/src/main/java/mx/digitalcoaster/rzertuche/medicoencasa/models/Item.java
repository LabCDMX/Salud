package mx.digitalcoaster.rzertuche.medicoencasa.models;

public class Item {

    /*---------------------------------- Datos de lista de clientes ------------------------------*/
    private String nombre;
    private String curp;
    private String direccion;
    private String status;
    private Boolean makeHojaDiaria;
    private Boolean checkHojaDiaria;


    /*--------------------------------- Datos de la lista de agendados ---------------------------*/


    /**----------------------------- Contructor para inflar los clientes -------------------------*/
    public Item(String nombre, String curp, String direccion){
        this.nombre = nombre;
        this.curp = curp;
        this.direccion = direccion;
    }

    public Item(String nombre, String curp, String direccion, String status, Boolean makeHojaDiaria, Boolean checkHojaDiaria){
        this.nombre = nombre;
        this.curp = curp;
        this.direccion = direccion;
        this.status = status;
        this.makeHojaDiaria = makeHojaDiaria;
        this.checkHojaDiaria = checkHojaDiaria;
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

}
