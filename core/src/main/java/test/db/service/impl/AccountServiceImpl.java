package test.db.service.impl;

import io.ebean.TxScope;
import io.ebean.annotation.TxIsolation;
import test.db.dao.AccountDao;
import test.db.entity.AbstractEntity;
import test.db.entity.Account;
import test.db.service.AbstractService;
import test.db.service.AccountService;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.stream.Stream;

import static java.util.Objects.isNull;

@Singleton
public class AccountServiceImpl extends AbstractService<Account> implements AccountService {

    @Inject
    public AccountServiceImpl(AccountDao dao) {
        super(dao);
    }

    @Override
    public boolean transfer(Long fromAccountId, Long toAccountId, BigDecimal amount) {
        return dao.server().
                executeCall(TxScope.requiresNew().setIsolation(TxIsolation.READ_COMMITED), () -> {
                    Account fromAccount = getById(fromAccountId);
                    Account toAccount = getById(toAccountId);
                    if (isNull(fromAccount) || isNull(toAccount)) {
                        throw new RuntimeException("Null Account");
                    }
                    if (fromAccount.getMoneySum().compareTo(amount) >= 0) {
                        fromAccount.setMoneySum(fromAccount.getMoneySum().subtract(amount));
                        toAccount.setMoneySum(toAccount.getMoneySum().add(amount));
                    } else {
                        return false;
                    }
                    Stream.of(fromAccount, toAccount).
                            sorted(Comparator.comparing(AbstractEntity::getId)).
                            forEach(this::update);
                    return true;
                });
    }
}
