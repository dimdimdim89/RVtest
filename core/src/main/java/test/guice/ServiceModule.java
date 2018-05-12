package test.guice;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import test.Constants;
import test.db.service.AccountService;
import test.db.service.impl.AccountServiceImpl;

public class ServiceModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(AccountService.class).annotatedWith(Names.named(Constants.ACCOUNT_SERVICE)).to(AccountServiceImpl.class);
    }
}
