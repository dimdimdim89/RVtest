package test.guice;

import com.google.inject.AbstractModule;
import test.db.dao.AccountDao;
import test.db.dao.UserDao;
import test.db.dao.impl.AccountDaoImpl;
import test.db.dao.impl.UserDaoImpl;

public class DAOModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(AccountDao.class).to(AccountDaoImpl.class);
        bind(UserDao.class).to(UserDaoImpl.class);
    }
}
