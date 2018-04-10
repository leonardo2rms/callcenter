package model;

import java.util.Objects;

/**
 * Clase que representa al empleado del call center
 * @author leonardo
 */
public class Empleado {

    private String nombre;
    private TipoEmpleadoEnum tipoEmpleado;

    /**
     * Constructor con el nombre del empleado
     * @param nombre el nombre del empleado
     */
    public Empleado(String nombre) {
        this.nombre = nombre;
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
        return "Empleado{" +
                "nombre='" + nombre + '\'' +
                ", tipoEmpleado=" + tipoEmpleado +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Empleado empleado = (Empleado) o;
        return Objects.equals(nombre, empleado.nombre) &&
                tipoEmpleado == empleado.tipoEmpleado;
    }

    @Override
    public int hashCode() {

        return Objects.hash(nombre, tipoEmpleado);
    }
}
