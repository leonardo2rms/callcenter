package service;

import model.Llamada;
import org.apache.log4j.Logger;
import service.impl.DispatcherImpl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Clase encargada de producir llamadas
 *
 * @author Leonardo
 */
public class ProductorLlamada {
    private static Logger log = Logger.getLogger(ProductorLlamada.class.getName());
    private final int totalLlamadasParaProducir;
    private final ExecutorService executorService;
    private final DispatcherImpl dispatcher;

    /**
     * Constructor del productor de llamadas, crea la cantidad de llamadas enviada por parametro, crea un executorService
     * con la cantidad de threads que se le indique en totalLlamadasSimultaneas
     *
     * @param totalLlamadasParaProducir el total de llamadas que producira
     * @param totalLlamadasSimultaneas el total de llamadas que se atienden al mismo tiempo
     * @param dispatcher objeto encargado de despachar las llamadas
     */
    public ProductorLlamada(int totalLlamadasParaProducir, int totalLlamadasSimultaneas, DispatcherImpl dispatcher) {
        this.totalLlamadasParaProducir = totalLlamadasParaProducir;
        this.executorService = Executors.newFixedThreadPool(totalLlamadasSimultaneas);
        this.dispatcher = dispatcher;
    }

    /**
     * Utiliza el executorService para hacer la cantidad de llamadas indicadas en el constructor de la clase
     */
    public void run() {
        executorService.execute(() -> {
            for (int i = 0; i < this.totalLlamadasParaProducir; i++) {
                dispatcher.dispatchCall(new Llamada());
            }
        });
    }

}
