package com.almundo.callcenter.model;

import model.Llamada;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Test de la clase Llamada
 */
public class LlamadaTest {
    @Test
    public void shouldInstantiateOneObjectWithAutogeneratedId(){
        Llamada llamada = new Llamada();
        Assert.assertEquals(1, llamada.getId());
    }

    @Test
    public void shouldInstantiateTenObjectsWithAutogeneratedId(){
        List<Llamada> llamadas = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            llamadas.add(new Llamada());
        }
        Assert.assertEquals(10, llamadas.size());
        Assert.assertEquals(10, llamadas.get(9).getId());
    }

}
