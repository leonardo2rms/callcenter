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
}
