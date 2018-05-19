package test.db.service.validation;

import test.db.entity.Account;
import test.db.entity.dto.Transfer;

import java.math.BigDecimal;

import static java.util.Objects.isNull;

public class TransferValidation implements Validation<Transfer<Account>> {
    private String message;

    @Override
    public boolean validate(Transfer<Account> accountTransfer) {
        if (accountTransfer.amount.compareTo(BigDecimal.valueOf(0)) < 0) {
            message = "Less then zero amount";
            return false;
        }
        if (isNull(accountTransfer.from) || isNull(accountTransfer.to)) {
            message = "Null account";
            return false;
        }
        if (accountTransfer.from.getMoneySum().compareTo(accountTransfer.amount) < 0) {
            message = "Insufficient funds";
            return false;
        }
        return true;
    }

    @Override
    public String getValidationMessage() {
        return message;
    }
}
