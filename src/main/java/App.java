import model.Empleado;
import model.TipoEmpleadoEnum;
import org.apache.log4j.Logger;
import service.ProductorLlamada;
import service.impl.DispatcherImpl;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Clase principal de la aplicacion, contiene el metodo main
 */
public class App {

    private static Logger log = Logger.getLogger(App.class.getName());

    private static final int MAXIMO_LLAMADAS_SIMULTANEAS = 10;
    private static final int TOTAL_LLAMADAS_PRODUCER = 20;

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        /* Generar todos los empleados y guardarlos en una lista*/
        List<Empleado> empleados = Empleado.generarEmpleadosPorTipo(TipoEmpleadoEnum.OPERATOR, 5);
        empleados.addAll(Empleado.generarEmpleadosPorTipo(TipoEmpleadoEnum.SUPERVISOR, 2));
        empleados.addAll(Empleado.generarEmpleadosPorTipo(TipoEmpleadoEnum.DIRECTOR, 1));

        DispatcherImpl dispatcher = new DispatcherImpl(empleados, MAXIMO_LLAMADAS_SIMULTANEAS);
        ProductorLlamada producer = new ProductorLlamada(TOTAL_LLAMADAS_PRODUCER, MAXIMO_LLAMADAS_SIMULTANEAS, dispatcher);
        producer.run();

        executorService.shutdown();

    }
}
