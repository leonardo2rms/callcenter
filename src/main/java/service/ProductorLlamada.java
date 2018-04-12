package service;

import model.Llamada;
import org.apache.log4j.Logger;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ProductorLlamada implements Runnable {
    private static Logger log = Logger.getLogger(ProductorLlamada.class.getName());

    private BlockingQueue<Llamada> llamadas;
    private final int totalLlamadas;

    public ProductorLlamada(int totalLlamadas, int cantidadLlamadasSimultaneas) {
        this.totalLlamadas = totalLlamadas;
        llamadas = new LinkedBlockingQueue<>(cantidadLlamadasSimultaneas);
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= totalLlamadas; i++) {
                    llamadas.put(new Llamada(i));
                    log.info("Se agrego la llamada " + i + " a la lista de llamadas");
            }
        } catch (InterruptedException e) {
            log.error("Error en: " + Thread.currentThread().getName() + ": " + e.toString());
        }
    }

    public BlockingQueue<Llamada> getLlamadas() {
        return llamadas;
    }

}
