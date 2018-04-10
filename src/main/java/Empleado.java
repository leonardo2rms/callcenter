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
}
