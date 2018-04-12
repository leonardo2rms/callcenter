import model.Empleado;
import model.Llamada;
import model.TipoEmpleadoEnum;
import org.apache.log4j.Logger;
import service.ConsumidorLlamada;
import service.ProductorLlamada;

import java.util.concurrent.*;

/**
 * Clase principal de la aplicacion, contiene el metodo main
 */
public class App {

    static Logger log = Logger.getLogger(App.class.getName());

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        ProductorLlamada producer = new ProductorLlamada(20, 5, 100);
        PriorityBlockingQueue<Empleado> colaEmpleados = new PriorityBlockingQueue<>();
        BlockingQueue<Llamada> llamadasEnEspera = new LinkedBlockingQueue<>(100);

        for (int i = 0; i < 5; i++) {
            colaEmpleados.put(new Empleado(TipoEmpleadoEnum.OPERATOR));
        }
        for (int i = 0; i < 2; i++) {
            colaEmpleados.put(new Empleado(TipoEmpleadoEnum.SUPERVISOR));
        }
        log.info("Agregue todos los empleados");
        colaEmpleados.put(new Empleado(TipoEmpleadoEnum.DIRECTOR));

        executorService.execute(producer);
        ConsumidorLlamada consumer = new ConsumidorLlamada(producer.getLlamadas(), producer.getLlamadasEnEspera(), colaEmpleados);


        for (int i = 0; i < 100; i++) {
            executorService.execute(consumer);

        }
        executorService.shutdown();

    }
}
