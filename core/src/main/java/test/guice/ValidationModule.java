package test.guice;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import test.Constants;
import test.db.entity.Account;
import test.db.entity.dto.Transfer;
import test.db.service.validation.Validation;

import java.util.List;

public class ValidationModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(List.class).annotatedWith(Names.named(Constants.VALIDATION)).toProvider(ValidationProvider.class);
    }
}
