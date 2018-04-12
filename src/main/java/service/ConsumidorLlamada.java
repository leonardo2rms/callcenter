package service;

import model.Empleado;
import model.Llamada;
import org.apache.log4j.Logger;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

public class ConsumidorLlamada implements Runnable {
    static Logger log = Logger.getLogger(ConsumidorLlamada.class.getName());

    private BlockingQueue<Llamada> llamadas;
    private PriorityBlockingQueue<Empleado> colaEmpleados;

    public ConsumidorLlamada(BlockingQueue<Llamada> llamadas, PriorityBlockingQueue<Empleado> colaEmpleados) {
        this.llamadas = llamadas;
        this.colaEmpleados = colaEmpleados;
    }

    @Override
    public void run() {
        try {
            while (true) {
                //TODO falta el poison pill
                Llamada llamada = llamadas.take();
                Empleado empleado = colaEmpleados.take();
                llamada.setEmpleadoAsignado(empleado);
                log.info("Llamada " + llamada.getId() + " Atendida por el empleado: " + empleado.toString());
                TimeUnit.SECONDS.sleep(getCallTime());
                log.info("Llamada " + llamada.getId() + " terminada, el empleado " + empleado.toString() + " se desocupo");
                colaEmpleados.put(empleado);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private Integer getCallTime() {
        return new Random().nextInt((10 - 5) + 1) + 5;
    }
}
