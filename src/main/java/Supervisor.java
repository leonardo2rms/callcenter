/**
 * Clase que representa a un empleado del tipo Supervisor
 *
 * @author leonardo
 */
public class Supervisor extends Empleado {

    public Supervisor(String nombre) {
        super(nombre);
        setTipoEmpleado(TipoEmpleadoEnum.SUPERVISOR);
    }

}
