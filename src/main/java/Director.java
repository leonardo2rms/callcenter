/**
 * Clase que representa a un empleado del tipo Director
 *
 * @author leonardo
 */
public class Director extends Empleado {

    public Director(String nombre) {
        super(nombre);
        setTipoEmpleado(TipoEmpleadoEnum.DIRECTOR);
    }

}
