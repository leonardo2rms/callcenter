package service.impl;

import model.Empleado;
import model.Llamada;
import org.apache.log4j.Logger;
import service.Dispatcher;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Clase encargada de despachar las llamadas entrantes
 *
 * @author Leonardo
 */
public class DispatcherImpl implements Dispatcher {
    private static Logger log = Logger.getLogger(Dispatcher.class.getName());

    private final ExecutorService executorService;
    private final PriorityBlockingQueue<Empleado> colaEmpleados;

    /**
     * Genera el Dispatcher con una cola de empleados indicado por parametros y una capacidad para manejar
     * el numero de llamadas simultaneas enviado por parametro
     *
     * @param empleados                   lista de los empleados que atenderan las llamadas
     * @param cantidadLlamadasSimultaneas cantidad de llamadas que seran atendidas concurrentemente
     */
    public DispatcherImpl(List<Empleado> empleados, int cantidadLlamadasSimultaneas) {
        this.executorService = Executors.newFixedThreadPool(cantidadLlamadasSimultaneas);
        this.colaEmpleados = new PriorityBlockingQueue<>(empleados);
    }

    @Override
    public void dispatchCall(Llamada llamada) {
        this.executorService.execute(() -> {
            try {
                Empleado empleado = colaEmpleados.take();
                log.info("Llamada " + llamada.getId() + " recibida, en breve sera atendida.");
                llamada.setEmpleadoAsignado(empleado);
                log.info("Llamada " + llamada.getId() + " Atendida por el empleado: " + empleado.toString());
                empleado.constestarLlamada(llamada);
                log.info("Llamada " + llamada.getId() + " terminada, duracion: " + llamada.getDuracion() + " segundos," + " el empleado " + llamada.getEmpleadoAsignado().toString() + " se desocupo");
                colaEmpleados.put(empleado);
            } catch (InterruptedException e) {
                log.error("Se ha interrumpido la llamada: " + llamada.getId());
            }
        });
    }

    @Override
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
