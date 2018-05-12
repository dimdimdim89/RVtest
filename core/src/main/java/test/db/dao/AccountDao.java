package test.db.dao;

import test.db.entity.Account;

import java.math.BigDecimal;

public interface AccountDao extends CommonDao<Account> {
    boolean transfer(Long fromAccountId, Long toAccountId, BigDecimal amount);
}
