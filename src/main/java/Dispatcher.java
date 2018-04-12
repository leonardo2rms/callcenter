import model.Llamada;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class Dispatcher {
    /*    Numero de llamas a ser atendidas al mismo tiempo   */
    private static final int MAXIMO_LLAMADAS_SIMULTANEAS = 10;

    private ExecutorService executor;

    Llamada llamada;

    Queue<Llamada> llamadasEnEspera = new ConcurrentLinkedQueue<Llamada>();

    LinkedBlockingQueue<Llamada> colaLlamadasOperadores;

    LinkedBlockingQueue<Llamada> colaLlamadasDirectores;

    LinkedBlockingQueue<Llamada> colaLlamadasSupervisores;


    /* Numero de empleados por defecto */
    int totalOperadores = 5;
    int totalSupervisores = 2;
    int totalDirectores = 1;

    /**
     * Constructor por defecto utilizando el numero de empleados y el numero de llamadas simultaneas por default
     */
    Dispatcher() {
        colaLlamadasOperadores = new LinkedBlockingQueue<Llamada>(totalOperadores);
        colaLlamadasSupervisores = new LinkedBlockingQueue<Llamada>(totalSupervisores);
        colaLlamadasDirectores = new LinkedBlockingQueue<Llamada>(totalDirectores);
        executor = Executors.newFixedThreadPool(MAXIMO_LLAMADAS_SIMULTANEAS);
    }

    /**
     * Constructor indicando el numero de empleados que atenderan la llamada
     * @param totalOperadores la cantidad de operadores que atenderan llamadas
     * @param totalSupervisores la cantidad de supervisores que atenderan llamadas
     * @param totalDirectores la cantidad de directores que atenderan llamadas
     */
    Dispatcher(int totalOperadores, int totalSupervisores, int totalDirectores) {
        colaLlamadasOperadores = new LinkedBlockingQueue<Llamada>(totalOperadores);
        colaLlamadasSupervisores = new LinkedBlockingQueue<Llamada>(totalSupervisores);
        colaLlamadasDirectores = new LinkedBlockingQueue<Llamada>(totalDirectores);
        executor = Executors.newFixedThreadPool(MAXIMO_LLAMADAS_SIMULTANEAS);
    }

    /**
     * Constructor indicando el numero de empleados que atenderan la llamada y cuantas llamadas
     * se pueden atender al mismo tiempo
     * @param totalOperadores la cantidad de operadores que atenderan llamadas
     * @param totalSupervisores la cantidad de supervisores que atenderan llamadas
     * @param totalDirectores la cantidad de directores que atenderan llamadas
     * @param maximoLlamadasSimultaneas
     */
    Dispatcher(int totalOperadores, int totalSupervisores, int totalDirectores, int maximoLlamadasSimultaneas) {
        colaLlamadasOperadores = new LinkedBlockingQueue<Llamada>(totalOperadores);
        colaLlamadasSupervisores = new LinkedBlockingQueue<Llamada>(totalSupervisores);
        colaLlamadasDirectores = new LinkedBlockingQueue<Llamada>(totalDirectores);
        executor = Executors.newFixedThreadPool(maximoLlamadasSimultaneas);
    }

    /**
     * Este metodo asigna las llamadas a los empleados disponibles
     * primero asigna las llamadas a los operadores, si estos estan disponibles, sino
     * asigna las llamadas a los supervisores, si estos no estan disponibles, entonces la llamada
     * es asignada a los directores, en caso que estos tambien esten ocupados, la llamada debe ser puesta
     * en espera a que alguno de los empleados se desocupe.
     * (llamadas).
     *
     * @param llamada la llamada que se quiere despachar
     */
    public void dispatchCall(Llamada llamada) {
        //TODO do some magic in here

    }


}
