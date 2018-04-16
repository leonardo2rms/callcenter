package service.impl;

import general.Constantes;
import model.Llamada;
import service.Dispatcher;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * Clase encargada de producir llamadas
 *
 * @author Leonardo
 */
public class ProductorLlamada {
    private final int totalLlamadasParaProducir;
    private final ExecutorService executorService;
    private Dispatcher dispatcher;

    /**
     * Constructor del productor de llamadas, crea la cantidad de llamadas enviada por parametro, crea un executorService
     * con la cantidad de threads que se le indique en totalLlamadasSimultaneas
     *
     * @param totalLlamadasParaProducir el total de llamadas que producira
     * @param totalLlamadasSimultaneas  el total de llamadas que se atienden al mismo tiempo
     * @param dispatcher                objeto encargado de despachar las llamadas
     */
    public ProductorLlamada(int totalLlamadasParaProducir, int totalLlamadasSimultaneas, Dispatcher dispatcher) {
        this.totalLlamadasParaProducir = totalLlamadasParaProducir;
        this.executorService = Executors.newFixedThreadPool(totalLlamadasSimultaneas);
        this.dispatcher = dispatcher;
    }

    /**
     * Utiliza el executorService para hacer la cantidad de llamadas indicadas en el constructor de la clase.
     */
    public void run() {
        obtenerLlamadas(totalLlamadasParaProducir).forEach(llamada -> {
            try {
                /*Se agrego este delay para que se presenciara el uso del priorityBlockingQueue de los empleados.
                  en el log no se veia que las llamadas estaban siendo atendidas por los operadores, luego los
                  supervisores y luego los directores, como se recibian tan rapidamente no se apreciaba el orden. */
                TimeUnit.MILLISECONDS.sleep(Constantes.DELAY_LLAMADA_PRODUCTOR);
                this.executorService.execute(() -> dispatcher.dispatchCall(llamada));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Crea la cantidad de llamadas indicadas por parametro y retorna una coleccion con ellas
     *
     * @param totalLlamadasParaProducir la cantidad de llamadas a producir
     * @return una coleccion de Llamada
     */
    private Collection<Llamada> obtenerLlamadas(int totalLlamadasParaProducir) {
        Collection<Llamada> llamadas = new ArrayList<>();
        IntStream.range(0, totalLlamadasParaProducir).forEach(i -> {
            llamadas.add(new Llamada());
        });
        return llamadas;
    }

    /**
     * Metodo encargado de apagar el Executorservice
     *
     * @param awaitTermination la cantidad de segundos que se esperara por el executor service a que finalice sus tareas.
     */
    public void killExecutor(Integer awaitTermination) {
        this.executorService.shutdown();
        try {
            if (!this.executorService.awaitTermination(awaitTermination, TimeUnit.SECONDS)) {
                this.executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            this.executorService.shutdownNow();
        }
    }

}
