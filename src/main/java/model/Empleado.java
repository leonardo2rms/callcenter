package model;

import general.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Clase que representa al empleado del call center
 *
 * @author leonardo
 */
public class Empleado implements Comparable<Empleado> {

    public static final AtomicInteger count = new AtomicInteger(0);

    private int id;
    private TipoEmpleadoEnum tipoEmpleado;

    /**
     * Constructor con el nombre del empleado
     *
     * @param tipoEmpleado el tipo de empleado - Operador, Supervisor o Director.
     */
    public Empleado(TipoEmpleadoEnum tipoEmpleado) {
        this.tipoEmpleado = tipoEmpleado;
        this.id = count.incrementAndGet();
    }

    public int getId() {
        return id;
    }

    public TipoEmpleadoEnum getTipoEmpleado() {
        return tipoEmpleado;
    }

    public void setTipoEmpleado(TipoEmpleadoEnum tipoEmpleado) {
        this.tipoEmpleado = tipoEmpleado;
    }

    @Override
    public String toString() {
        return id + " - " + tipoEmpleado.getDescripcion();
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

    /**
     * Metodo encargado de generar una cantidad de empleados por tipo
     *
     * @param tipoEmpleado el tipo de empleado
     * @param cantidad     la cantidad que se quieren generar
     * @return una lista de empleados del tipo pasado por parametros
     */
    public static List<Empleado> generarEmpleadosPorTipo(TipoEmpleadoEnum tipoEmpleado, int cantidad) {
        List<Empleado> empleados = new ArrayList<>();
        for (int i = 0; i < cantidad; i++) {
            empleados.add(new Empleado(tipoEmpleado));
        }
        return empleados;
    }

    /**
     * Se contesta la llamada y se le asigna el tiempo de duracion
     *
     * @param llamada la llamada a ser atendida
     * @throws InterruptedException si se inturrumpe antes de terminar la llamada
     */
    public void constestarLlamada(Llamada llamada) throws InterruptedException {
        llamada.setDuracion(Util.getTiempoLlamada());
        TimeUnit.SECONDS.sleep(llamada.getDuracion());
    }



}
