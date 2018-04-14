package com.almundo.callcenter.model;

import model.Empleado;
import model.TipoEmpleadoEnum;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Test de la clase Empleado
 */
public class EmpleadoTest {

    @Before
    public void setUp() {
        //Iniciar el generador de id en 0
        Empleado.count.set(0);
    }

    @Test
    public void shouldCreateASupervisor(){
        Empleado empleado= new Empleado(TipoEmpleadoEnum.SUPERVISOR);
        Assert.assertEquals(TipoEmpleadoEnum.SUPERVISOR, empleado.getTipoEmpleado());
    }

    @Test
    public void shouldCreateAnOperator(){
        Empleado empleado= new Empleado(TipoEmpleadoEnum.OPERADOR);
        Assert.assertEquals(TipoEmpleadoEnum.OPERADOR, empleado.getTipoEmpleado());
    }

    @Test
    public void shouldCreateADirector(){
        Empleado empleado= new Empleado(TipoEmpleadoEnum.DIRECTOR);
        Assert.assertEquals(TipoEmpleadoEnum.DIRECTOR, empleado.getTipoEmpleado());
    }

    @Test
    public void shouldCreateMultipleEmployees(){
        List<Empleado> empleados;
        empleados = Empleado.generarEmpleadosPorTipo(TipoEmpleadoEnum.OPERADOR, 3);
        empleados.addAll(Empleado.generarEmpleadosPorTipo(TipoEmpleadoEnum.SUPERVISOR, 2));
        empleados.addAll(Empleado.generarEmpleadosPorTipo(TipoEmpleadoEnum.DIRECTOR, 1));

        Assert.assertEquals(6, empleados.size());
        Assert.assertEquals(6, empleados.get(5).getId());
    }

    @Test
    public void testEquals(){
        Empleado empleado= new Empleado(TipoEmpleadoEnum.DIRECTOR);
        Empleado empleado2= new Empleado(TipoEmpleadoEnum.DIRECTOR);
        Assert.assertEquals(empleado,empleado2);
    }
}
