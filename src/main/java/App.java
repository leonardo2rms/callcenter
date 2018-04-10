import model.Operador;
import org.apache.log4j.Logger;

/**
 * Clase principal de la aplicacion, contiene el metodo main
 */
public class App {

    static Logger log = Logger.getLogger(App.class.getName());

    public static void main(String[] args) {
        Operador operador = new Operador("mark");
        log.info(operador.toString());

    }
}
