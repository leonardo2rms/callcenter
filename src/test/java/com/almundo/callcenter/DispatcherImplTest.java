package com.almundo.callcenter;

import general.Constantes;
import model.Empleado;
import model.TipoEmpleadoEnum;
import org.junit.Before;
import org.junit.Test;
import service.ProductorLlamada;
import service.impl.DispatcherImpl;

import java.util.List;

public class DispatcherImplTest {

    private ProductorLlamada producer;
    private DispatcherImpl dispatcher;
    private List<Empleado> empleados;

    @Before
    public void setUp() {
        this.empleados = Empleado.generarEmpleadosPorTipo(TipoEmpleadoEnum.OPERADOR, Constantes.TOTAL_OPERADORES);
        this.empleados.addAll(Empleado.generarEmpleadosPorTipo(TipoEmpleadoEnum.SUPERVISOR, Constantes.TOTAL_SUPERVISORES));
        this.empleados.addAll(Empleado.generarEmpleadosPorTipo(TipoEmpleadoEnum.DIRECTOR, Constantes.TOTAL_DIRECTORES));

        this.dispatcher = new DispatcherImpl(this.empleados, Constantes.MAXIMO_LLAMADAS_SIMULTANEAS);
        this.producer = new ProductorLlamada(Constantes.TOTAL_LLAMADAS_PRODUCER, Constantes.MAXIMO_LLAMADAS_SIMULTANEAS, this.dispatcher);
    }

    @Test
    public void shouldMakeOneHundredCallsWithTenSimultaneoslyCalls() {

        this.producer = new ProductorLlamada(100, 10, this.dispatcher);
        this.producer.run();
    }

}
