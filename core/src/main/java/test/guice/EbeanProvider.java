package test.guice;

import com.google.inject.Provider;
import io.ebean.Ebean;
import io.ebean.EbeanServer;

public class EbeanProvider implements Provider<EbeanServer> {
    @Override
    public EbeanServer get() {
        return Ebean.getDefaultServer();
    }
}
