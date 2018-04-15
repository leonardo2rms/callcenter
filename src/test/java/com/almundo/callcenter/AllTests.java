package com.almundo.callcenter;

import com.almundo.callcenter.model.EmpleadoTest;
import com.almundo.callcenter.model.LlamadaTest;
import com.almundo.callcenter.service.DispatcherTest;
import com.almundo.callcenter.util.UtilTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Clase que engloba los tests de la aplicacion
 * @author Leonardo
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({
        EmpleadoTest.class,
        LlamadaTest.class,
        DispatcherTest.class,
        UtilTest.class})

public class AllTests {

}
