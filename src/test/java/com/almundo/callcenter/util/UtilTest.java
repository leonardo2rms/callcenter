package com.almundo.callcenter.util;

import general.Util;
import org.junit.Assert;
import org.junit.Test;

import java.util.stream.IntStream;

import static org.junit.Assert.assertTrue;

/**
 * Test de la clase Util
 */
public class UtilTest {

    @Test
    public void shouldGenerateAThreeSecondsTimeCall() {
        int tiempoLlamada = Util.getTiempoLlamada(3, 3);
        Assert.assertEquals(3, tiempoLlamada);
    }

    @Test
    public void testRandomBetwenValues() {
        int tiempoMinimo= 5;
        int tiempoMaximo= 10;
        IntStream.range(0,5).forEach(i -> {
            int tiempoLlamada = Util.getTiempoLlamada(tiempoMinimo, tiempoMaximo);
            assertTrue(tiempoLlamada >= tiempoMinimo && tiempoLlamada <= tiempoMaximo);
        });
    }


}
