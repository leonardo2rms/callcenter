package service.impl;

import model.Empleado;
import model.Llamada;
import org.apache.log4j.Logger;
import service.Dispatcher;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;

/**
 *
 * Clase encargada de despachar las llamadas entrantes
 */
public class DispatcherImpl implements Dispatcher {
    static Logger log = Logger.getLogger(Dispatcher.class.getName());

    final ExecutorService executorService;
    final PriorityBlockingQueue<Empleado> colaEmpleados;


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
                log.info("Llamada " + llamada.getId() + " terminada, duracion: " + llamada.getDuracion() + " segundos" + " el empleado " + empleado.toString() + " se desocupo");
                colaEmpleados.put(empleado);
            } catch (InterruptedException e) {
                log.info("Se ha interrumpido la llamada" + llamada.getId());
            }
        });
    }


}
