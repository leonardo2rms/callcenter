package model;

/**
 * Clase que representa una llamada telefonica
 * @author leonardo
 */
public class Llamada{
    private int id;
    private Empleado empleadoAsignado;

    public Llamada(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Empleado getEmpleadoAsignado() {
        return empleadoAsignado;
    }

    public void setEmpleadoAsignado(Empleado empleadoAsignado) {
        this.empleadoAsignado = empleadoAsignado;
    }

}
