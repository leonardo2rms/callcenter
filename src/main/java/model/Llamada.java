package model;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Clase que representa una llamada telefonica
 *
 * @author leonardo
 */
public class Llamada {
    public static final AtomicInteger count = new AtomicInteger(0);

    private int id;
    private Empleado empleadoAsignado;
    private int duracion;
    /**
     * Constructor que crea un objeto un con id autoincrementado
     */
    public Llamada() {
        this.id = count.incrementAndGet();
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public int getId() {
        return id;
    }

    public Empleado getEmpleadoAsignado() {
        return empleadoAsignado;
    }

    public void setEmpleadoAsignado(Empleado empleadoAsignado) {
        this.empleadoAsignado = empleadoAsignado;
    }

}
