package general;

import java.util.Random;

/**
 * Clase con codigo util para toda la aplicacion
 *
 * @author Leonardo
 */
public class Util {
    /**
     * Obtiene un numero random que representa la duracion de la llamada, obtiene de las constantes de la aplicacion la
     * duracion minima y la maxima
     *
     * @return duracion de la llamada
     */
    public static Integer getTiempoLlamada() {
        return new Random().nextInt((Constantes.DURACION_MAXIMA_LLAMADA - Constantes.DURACION_MINIMA_LLAMADA) + 1) + Constantes.DURACION_MINIMA_LLAMADA;
    }
}
