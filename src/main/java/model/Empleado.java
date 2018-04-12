package model;

import java.util.Objects;

/**
 * Clase que representa al empleado del call center
 *
 * @author leonardo
 */
public class Empleado implements Comparable<Empleado>{

    private String nombre;
    private TipoEmpleadoEnum tipoEmpleado;

    /**
     * Constructor con el nombre del empleado
     *
     * @param tipoEmpleado el tipo de empleado - Operador, Supervisor o Director.
     */
    public Empleado(TipoEmpleadoEnum tipoEmpleado) {
        this.tipoEmpleado = tipoEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoEmpleadoEnum getTipoEmpleado() {
        return tipoEmpleado;
    }

    public void setTipoEmpleado(TipoEmpleadoEnum tipoEmpleado) {
        this.tipoEmpleado = tipoEmpleado;
    }

    @Override
    public String toString() {
        return nombre + "-" + tipoEmpleado.getDescripcion();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Empleado empleado = (Empleado) o;
        return tipoEmpleado == empleado.tipoEmpleado;
    }

    @Override
    public int hashCode() {

        return Objects.hash(tipoEmpleado);
    }

    @Override
    public int compareTo(Empleado o) {
        return this.tipoEmpleado.compareTo(o.tipoEmpleado);
    }
}
