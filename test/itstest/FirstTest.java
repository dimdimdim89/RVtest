package itstest;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.Before;
import test.guice.DAOModule;
import test.guice.EbeanModule;
import test.guice.ServiceModule;

public class FirstTest {
    private Injector injector;

    @Before
    public void setUp() {
        injector = Guice.createInjector(new EbeanModule(), new ServiceModule(), new DAOModule());
    }
}
