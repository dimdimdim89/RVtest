package test.db.service.impl;

import io.ebean.TxScope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import test.Constants;
import test.db.dao.AccountDao;
import test.db.entity.AbstractEntity;
import test.db.entity.Account;
import test.db.entity.dto.Transfer;
import test.db.service.AbstractService;
import test.db.service.AccountService;
import test.db.service.validation.Validation;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static io.ebean.annotation.TxIsolation.READ_COMMITED;

@Singleton
public class AccountServiceImpl extends AbstractService<Account> implements AccountService {
    private static final Logger log = LoggerFactory.getLogger(AccountServiceImpl.class);

    private List<Validation<Transfer<Account>>> validationList;

    @Inject
    public AccountServiceImpl(AccountDao dao) {
        super(dao);
    }

    @Override
    public boolean transfer(Long fromAccountId, Long toAccountId, BigDecimal amount) {
        return dao.server().
                executeCall(TxScope.requiresNew().setIsolation(READ_COMMITED), () -> {
                    Transfer<Account> transfer = new Transfer<>(getById(fromAccountId), getById(toAccountId), amount);
                    for (Validation<Transfer<Account>> v : validationList) {
                        if (!v.validate(transfer)) {
                            log.error("Error while validating makeTransfer - {}", v.getValidationMessage());
                            return false;
                        }
                    }
                    return makeTransfer(transfer.from, transfer.to, transfer.amount);
                });
    }

    private boolean makeTransfer(Account fromAccount, Account toAccount, BigDecimal amount) {
        return dao.server().
                executeCall(TxScope.required().setIsolation(READ_COMMITED), () -> {
                    fromAccount.setMoneySum(fromAccount.getMoneySum().subtract(amount));
                    toAccount.setMoneySum(toAccount.getMoneySum().add(amount));
                    Stream.of(fromAccount, toAccount).
                            sorted(Comparator.comparing(AbstractEntity::getId)).
                            forEach(this::update);
                    return true;
                });
    }

    @SuppressWarnings("unchecked")
    @Inject
    public void setValidationList(@Named(value = Constants.VALIDATION) List validationList) {
        this.validationList = validationList;
    }
}
