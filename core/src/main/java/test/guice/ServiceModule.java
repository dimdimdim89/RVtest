package test.guice;

import com.google.inject.AbstractModule;
import test.db.service.AccountService;
import test.db.service.impl.AccountServiceImpl;

public class ServiceModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(AccountService.class).to(AccountServiceImpl.class);
    }
}
