/**
 * Clase que representa a un empleado del tipo Operador
 *
 * @author leonardo
 */
public class Operador extends Empleado {

    public Operador(String nombre) {
        super(nombre);
        setTipoEmpleado(TipoEmpleadoEnum.OPERATOR);
    }

}
