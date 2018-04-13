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
 * @author Leonardo
 */
public class DispatcherImpl implements Dispatcher {
    static Logger log = Logger.getLogger(Dispatcher.class.getName());

    final ExecutorService executorService;
    final PriorityBlockingQueue<Empleado> colaEmpleados;

    /**
     * Genera el Dispatcher con una cola de empleados indicado por parametros y una capacidad para manejar
     * el numero de llamadas simultaneas enviado por parametro
     * @param empleados
     * @param cantidadLlamadasSimultaneas
     */
    public DispatcherImpl(List<Empleado> empleados, int cantidadLlamadasSimultaneas) {
        this.executorService = Executors.newFixedThreadPool(cantidadLlamadasSimultaneas);
        this.colaEmpleados = new PriorityBlockingQueue<>(empleados);
    }

    @Override
    public void dispatchCall(Llamada llamada) {
        this.executorService.execute(()->{
            try {
                Empleado empleado = colaEmpleados.take();
                llamada.setEmpleadoAsignado(empleado);
                log.info("Llamada " + llamada.getId() + " Atendida por el empleado: " + empleado.toString());
                empleado.constestarLlamada(llamada);
                log.info("Llamada " + llamada.getId() + " terminada, duracion: " + llamada.getDuracion() + " segundos," + " el empleado " + empleado.toString() + " se desocupo");
                colaEmpleados.put(empleado);
            } catch (InterruptedException e) {
                log.info("Se ha interrumpido la llamada" + llamada.getId());
            }
        });

    }

    /**
     * Verifica si el ExecutorService sigue trabajando
     * @return booleano indicando true si el executorService no ha terminado, false si ya termino.
     */
    public Boolean isRunning(){
        return !this.executorService.isTerminated();
    }

    /**
     * Metodo encargado de apagar el Executorservice
     */
    public void killExecutor(){
        this.executorService.shutdown();
        try {
            if (!this.executorService.awaitTermination(800, TimeUnit.MILLISECONDS)) {
                this.executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            this.executorService.shutdownNow();
        }
    }


}
