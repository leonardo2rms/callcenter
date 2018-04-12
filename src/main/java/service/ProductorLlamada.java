package service;

import model.Llamada;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ProductorLlamada implements Runnable {
    private BlockingQueue<Llamada> llamadas;
    private BlockingQueue<Llamada> llamadasEnEspera;
    private final int totalLlamadas;

    public ProductorLlamada(int totalLlamadas, int cantidadLlamadasSimultaneas, int maximoLlamadasEnEspera) {
        this.totalLlamadas = totalLlamadas;
        llamadas = new LinkedBlockingQueue<>(cantidadLlamadasSimultaneas);
        llamadasEnEspera = new LinkedBlockingQueue<>(maximoLlamadasEnEspera);
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= totalLlamadas; i++) {

                System.out.println("Produciendo llamada " + i);
                llamadas.put(new Llamada(i));
            }
            //TODO falta el posion pill
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public BlockingQueue<Llamada> getLlamadasEnEspera() {
        return llamadasEnEspera;
    }

    public BlockingQueue<Llamada> getLlamadas() {
        return llamadas;
    }

}
