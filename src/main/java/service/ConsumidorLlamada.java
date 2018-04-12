package service;

import model.Empleado;
import model.Llamada;
import org.apache.log4j.Logger;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class ConsumidorLlamada implements Runnable{
    static Logger log = Logger.getLogger(ConsumidorLlamada.class.getName());

    BlockingQueue<Llamada> llamadas;
    BlockingQueue<Llamada> llamadasEnEspera;
    PriorityBlockingQueue<Empleado> colaEmpleados;

    public ConsumidorLlamada(BlockingQueue<Llamada> llamadas, BlockingQueue<Llamada> llamadasEnEspera, PriorityBlockingQueue<Empleado> colaEmpleados) {
        this.llamadas = llamadas;
        this.llamadasEnEspera = llamadasEnEspera;
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
                Thread.sleep(getCallTime()*1000);
                log.info("Llamada " + llamada.getId() + " terminada, el empleado " + empleado.toString() + " se desocupo");
                colaEmpleados.put(empleado);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    private Integer getCallTime(){
        return new Random().nextInt((10 - 5) + 1) + 5;
    }
}
