package test.guice;

import com.google.inject.AbstractModule;
import io.ebean.EbeanServer;

public class EbeanModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(EbeanServer.class).toProvider(EbeanProvider.class).asEagerSingleton();
    }
}
