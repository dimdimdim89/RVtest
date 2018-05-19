package test.db.service;

import test.db.entity.Account;
import test.db.entity.dto.Transfer;

import java.math.BigDecimal;

public interface AccountService extends CommonService<Account> {
    boolean transfer(Long fromAccountId, Long toAccountId, BigDecimal amount);
}
