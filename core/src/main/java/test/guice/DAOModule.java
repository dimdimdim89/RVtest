package test.guice;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import test.Constants;
import test.db.dao.AccountDao;
import test.db.dao.impl.AccountDaoImpl;

public class DAOModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(AccountDao.class).annotatedWith(Names.named(Constants.ACCOUNT_DAO)).to(AccountDaoImpl.class);
    }
}
