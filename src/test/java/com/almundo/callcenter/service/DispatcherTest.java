package com.almundo.callcenter.service;

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
import java.util.stream.IntStream;

/**
 * Clase Test del Dispatcher
 *
 * @author Leonardo
 */
public class DispatcherTest {

    private Dispatcher dispatcher;

    @Before
    public void setUp() {
        //Iniciar el generador de id en 0
        Empleado.count.set(0);
        Llamada.count.set(0);

        List<Empleado> empleados = Empleado.generarEmpleadosPorTipo(TipoEmpleadoEnum.OPERADOR, 5);
        empleados.addAll(Empleado.generarEmpleadosPorTipo(TipoEmpleadoEnum.SUPERVISOR, 2));
        empleados.addAll(Empleado.generarEmpleadosPorTipo(TipoEmpleadoEnum.DIRECTOR, 1));
        this.dispatcher = new DispatcherImpl(empleados, 10);
    }

    /**
     * Test de 10 llamadas concurrentes solicitado en el enunciado
     */
    @Test
    public void shouldMakeTenSimultaneousCalls() {
        Collection<Llamada> llamadas = new ArrayList<>();
        IntStream.range(0, 10).forEach(i -> llamadas.add(new Llamada()));
        llamadas.parallelStream()
                .forEach(dispatcher::dispatchCall);
        this.dispatcher.killExecutor(20);
    }


}
