package test.guice;

import com.google.inject.Provider;
import test.db.entity.Account;
import test.db.entity.dto.Transfer;
import test.db.service.validation.TransferValidation;
import test.db.service.validation.Validation;

import java.util.List;

import static java.util.Arrays.asList;

public class ValidationProvider implements Provider<List<Validation<Transfer<Account>>>> {

    @Override
    public List<Validation<Transfer<Account>>> get() {
        Validation<Transfer<Account>> validation = new TransferValidation();
        return asList(validation);
    }
}
