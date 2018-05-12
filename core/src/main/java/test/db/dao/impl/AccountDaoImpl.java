package test.db.dao.impl;

import io.ebean.EbeanServer;
import io.ebean.TxScope;
import io.ebean.annotation.TxIsolation;
import test.db.dao.AbstractDao;
import test.db.dao.AccountDao;
import test.db.entity.Account;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.math.BigDecimal;

import static java.util.Objects.isNull;

@Singleton
public class AccountDaoImpl extends AbstractDao<Account> implements AccountDao {

    @Inject
    public AccountDaoImpl(EbeanServer ebeanServer) {
        super(Account.class, ebeanServer);
    }

    @Override
    public boolean transfer(Long fromAccountId, Long toAccountId, BigDecimal amount) {
        return ebeanServer.
                executeCall(TxScope.requiresNew().setIsolation(TxIsolation.READ_COMMITED), () -> {
                    Account fromAccount = ebeanServer.find(Account.class).where().eq("id", fromAccountId).findOne();
                    Account toAccount = ebeanServer.find(Account.class).where().eq("id", toAccountId).findOne();
                    if (isNull(fromAccount) || isNull(toAccount)) {
                        throw new RuntimeException("Null Account"); //NullAccountException?
                    }
                    if (fromAccount.getMoneySum().compareTo(amount) >= 0) {
                        fromAccount.setMoneySum(fromAccount.getMoneySum().subtract(amount));
                        toAccount.setMoneySum(toAccount.getMoneySum().add(amount));
                    }
                    ebeanServer.update(fromAccount);
                    ebeanServer.update(toAccount);
                    return true;
                });
    }
}
