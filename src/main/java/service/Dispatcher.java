package service;

import model.Llamada;

/**
 * Interfaz para el despacho de las llamadas
 *
 * @author leonardo
 */
public interface Dispatcher {
    /**
     * Metodo encargado de despachar una llamada
     *
     * @param llamada la llamada a ser despachada
     */
    void dispatchCall(Llamada llamada);

    /**
     * Metodo encargado de apagar el Executorservice
     *
     * @param awaitTermination la cantidad de segundos que se esperara por el executor service a que finalice sus tareas.
     */
    void killExecutor(Integer awaitTermination);
}
