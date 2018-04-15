package com.almundo.callcenter.model;

import model.Empleado;
import model.TipoEmpleadoEnum;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Test de la clase Empleado
 *
 * @author Leonardo
 */
public class EmpleadoTest {

    @Before
    public void setUp() {
        //Iniciar el generador de id en 0
        Empleado.count.set(0);
    }

    @Test
    public void shouldCreateASupervisor() {
        Empleado empleado = new Empleado(TipoEmpleadoEnum.SUPERVISOR);
        Assert.assertEquals(TipoEmpleadoEnum.SUPERVISOR, empleado.getTipoEmpleado());
    }

    @Test
    public void shouldCreateAnOperator() {
        Empleado empleado = new Empleado(TipoEmpleadoEnum.OPERADOR);
        Assert.assertEquals(TipoEmpleadoEnum.OPERADOR, empleado.getTipoEmpleado());
    }

    @Test
    public void shouldCreateADirector() {
        Empleado empleado = new Empleado(TipoEmpleadoEnum.DIRECTOR);
        Assert.assertEquals(TipoEmpleadoEnum.DIRECTOR, empleado.getTipoEmpleado());
    }

    @Test
    public void shouldCreateMultipleEmployees() {
        List<Empleado> empleados = getEmpleados();
        Assert.assertEquals(6, empleados.size());
        Assert.assertEquals(6, empleados.get(5).getId());
    }


    @Test
    public void testEquals() {
        Empleado empleado = new Empleado(TipoEmpleadoEnum.DIRECTOR);
        Empleado empleado2 = new Empleado(TipoEmpleadoEnum.DIRECTOR);
        Assert.assertEquals(empleado, empleado2);
    }


    @Test
    public void testEmployeePriorityQueue() {
        try {
            PriorityBlockingQueue<Empleado> empleados = new PriorityBlockingQueue<>(getEmpleados());
            //El primer empleado en obtener debe ser un operador
            Empleado operador = empleados.take();
            Assert.assertEquals(TipoEmpleadoEnum.OPERADOR, operador.getTipoEmpleado());
            //Tomamos 2 empleados mas que sabemos que seran operadores
            empleados.take();
            empleados.take();
            //El siguiente en tomar deberia ser Supervisor
            Empleado supervisor = empleados.take();
            Assert.assertEquals(TipoEmpleadoEnum.SUPERVISOR, supervisor.getTipoEmpleado());
            //Tomamos un empleado mas que sabemos que sera supervisor
            empleados.take();
            //El siguiente en tomar deberia ser Director
            Empleado director = empleados.take();
            Assert.assertEquals(TipoEmpleadoEnum.DIRECTOR, director.getTipoEmpleado());
        } catch (InterruptedException e) {
            //Hacemos que falle el Test en caso de recibir una InterruptedException
            Assert.assertTrue(false);
        }
    }

    private List<Empleado> getEmpleados() {
        List<Empleado> empleados = Empleado.generarEmpleadosPorTipo(TipoEmpleadoEnum.OPERADOR, 3);
        empleados.addAll(Empleado.generarEmpleadosPorTipo(TipoEmpleadoEnum.SUPERVISOR, 2));
        empleados.addAll(Empleado.generarEmpleadosPorTipo(TipoEmpleadoEnum.DIRECTOR, 1));
        return empleados;
    }
}
