import general.Constantes;
import model.Empleado;
import model.TipoEmpleadoEnum;
import org.apache.log4j.Logger;
import service.ProductorLlamada;
import service.impl.DispatcherImpl;

import java.util.List;

/**
 * Clase principal de la aplicacion, contiene el metodo main
 */
public class App {

    private static Logger log = Logger.getLogger(App.class.getName());

    public static void main(String[] args) {

        /* Generar todos los empleados y guardarlos en una lista*/
        List<Empleado> empleados = Empleado.generarEmpleadosPorTipo(TipoEmpleadoEnum.OPERADOR, Constantes.TOTAL_OPERADORES);
        empleados.addAll(Empleado.generarEmpleadosPorTipo(TipoEmpleadoEnum.SUPERVISOR, Constantes.TOTAL_SUPERVISORES));
        empleados.addAll(Empleado.generarEmpleadosPorTipo(TipoEmpleadoEnum.DIRECTOR, Constantes.TOTAL_DIRECTORES));

        /* Inicializar el Dispatcher*/
        DispatcherImpl dispatcher = new DispatcherImpl(empleados, Constantes.MAXIMO_LLAMADAS_SIMULTANEAS);

        /* Inicializar el ProductorLlamada */
        ProductorLlamada producer = new ProductorLlamada(Constantes.TOTAL_LLAMADAS_PRODUCER, Constantes.MAXIMO_LLAMADAS_SIMULTANEAS, dispatcher);

        /* Comenzar a producir llamadas */
        producer.run();

        /* Detener el Executor service del ProductorLlamada */
        producer.killExecutor(Constantes.TIEMPO_ESPERA_KILL_EXECUTOR);

        /* Detener el Executor service del Dispatcher */
        dispatcher.killExecutor(Constantes.TIEMPO_ESPERA_KILL_EXECUTOR);


    }
}
