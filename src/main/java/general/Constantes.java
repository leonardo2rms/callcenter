package general;

/**
 * Clase que contiene las constantes de la aplicacion
 *
 * @author Leonardo
 */
public class Constantes {
    /* Maximo de llamadas concurrentes para el dispatcher*/
    public static final int MAXIMO_LLAMADAS_SIMULTANEAS = 10;
    /* Maximo de llamadas concurrentes para el producer*/
    public static final int MAXIMO_LLAMADAS_SIMULTANEAS_PRODUCTOR = 1;
    /* Total de llamadas que creara el ProductorLlamada*/
    public static final int TOTAL_LLAMADAS_PRODUCER = 100;
    /* Cantidad de empleados de tipo OPERADOR*/
    public static final int TOTAL_OPERADORES = 7;
    /* Cantidad de empleados de tipo SUPERVISOR*/
    public static final int TOTAL_SUPERVISORES = 1;
    /* Cantidad de empleados de tipo DIRECTOR*/
    public static final int TOTAL_DIRECTORES = 1;
    /* Duracion minima de cada llamada */
    public static final int DURACION_MINIMA_LLAMADA = 5;
    /* Duracion maxima de cada llamada */
    public static final int DURACION_MAXIMA_LLAMADA = 10;
    /* Tiempo que se esperara por el executorService a que termine sus tareas antes de apagarlo */
    public static final int TIEMPO_ESPERA_KILL_EXECUTOR = 30;

}
