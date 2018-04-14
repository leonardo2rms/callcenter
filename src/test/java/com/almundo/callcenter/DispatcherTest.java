package com.almundo.callcenter;

import general.Constantes;
import model.Empleado;
import model.Llamada;
import model.TipoEmpleadoEnum;
import org.junit.Before;
import org.junit.Test;
import service.Dispatcher;
import service.impl.DispatcherImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Clase Test del Dispatcher
 */
public class DispatcherTest {

    private Dispatcher dispatcher;
    private List<Empleado> empleados;

    @Before
    public void setUp() {
        this.empleados = Empleado.generarEmpleadosPorTipo(TipoEmpleadoEnum.OPERADOR, Constantes.TOTAL_OPERADORES);
        this.empleados.addAll(Empleado.generarEmpleadosPorTipo(TipoEmpleadoEnum.SUPERVISOR, Constantes.TOTAL_SUPERVISORES));
        this.empleados.addAll(Empleado.generarEmpleadosPorTipo(TipoEmpleadoEnum.DIRECTOR, Constantes.TOTAL_DIRECTORES));

        this.dispatcher = new DispatcherImpl(this.empleados, Constantes.MAXIMO_LLAMADAS_SIMULTANEAS);
    }

    /**
     * Test de 10 llamadas concurrentes solicitado en el enunciado
     */
    @Test
    public void shouldMakeTenCalls() {
        Collection<Llamada> llamadas = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            llamadas.add(new Llamada());
        }
        llamadas.parallelStream()
                .forEach(dispatcher::dispatchCall);

        this.dispatcher.killExecutor(20);
    }


}
