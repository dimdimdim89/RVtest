package test.db.dao.impl;

import io.ebean.EbeanServer;
import test.db.dao.AbstractDao;
import test.db.dao.AccountDao;
import test.db.entity.Account;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AccountDaoImpl extends AbstractDao<Account> implements AccountDao {

    @Inject
    public AccountDaoImpl(EbeanServer ebeanServer) {
        super(Account.class, ebeanServer);
    }
}
