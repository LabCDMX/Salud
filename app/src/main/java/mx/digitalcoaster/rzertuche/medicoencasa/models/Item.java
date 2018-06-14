package mx.digitalcoaster.rzertuche.medicoencasa.models;

public class Item {

    /*---------------------------------- Datos de lista de clientes ------------------------------*/
    private String nombre;
    private String curp;
    private String direccion;


    /*--------------------------------- Datos de la lista de agendados ---------------------------*/


    /**----------------------------- Contructor para inflar los clientes -------------------------*/
    public Item(String nombre, String curp, String direccion){
        this.nombre = nombre;
        this.curp = curp;
        this.direccion = direccion;


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



}
