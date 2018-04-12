import model.Empleado;
import model.TipoEmpleadoEnum;
import org.apache.log4j.Logger;
import service.ConsumidorLlamada;
import service.ProductorLlamada;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Clase principal de la aplicacion, contiene el metodo main
 */
public class App {

    private static Logger log = Logger.getLogger(App.class.getName());

    private static final int MAXIMO_LLAMADAS_SIMULTANEAS = 10;
    private static final int TOTAL_LLAMADAS_PRODUCER = 200;

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        ProductorLlamada producer = new ProductorLlamada(TOTAL_LLAMADAS_PRODUCER, MAXIMO_LLAMADAS_SIMULTANEAS);
        PriorityBlockingQueue<Empleado> colaEmpleados = new PriorityBlockingQueue<>();

        /* Llenar la cola de empleados*/
        for (int i = 0; i < 5; i++) {
            colaEmpleados.put(new Empleado(TipoEmpleadoEnum.OPERATOR));
        }
        for (int i = 0; i < 2; i++) {
            colaEmpleados.put(new Empleado(TipoEmpleadoEnum.SUPERVISOR));
        }
        colaEmpleados.put(new Empleado(TipoEmpleadoEnum.DIRECTOR));
        log.info("Se agregaron todos los empleados a la cola");

        executorService.execute(producer);
        ConsumidorLlamada consumer = new ConsumidorLlamada(producer.getLlamadas(), colaEmpleados);

        /* usar el Executor service para hacer el numero de llamadas simultaneas*/
        for (int i = 0; i < MAXIMO_LLAMADAS_SIMULTANEAS; i++) {
            executorService.execute(consumer);
        }
        executorService.shutdown();

    }
}
