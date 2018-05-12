package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import test.Constants;
import test.db.entity.Account;
import test.db.service.AccountService;

import javax.inject.Inject;
import javax.inject.Named;
import java.math.BigDecimal;
import java.util.Arrays;

public class MainController extends Controller {

    @Inject
    @Named(value = Constants.ACCOUNT_SERVICE)
    private AccountService accountService;

    public Result index() {
        return ok(Arrays.toString(accountService.getAll().stream().map(Account::getUser).toArray()));
    }

    public Result transfer(Long fromAccountId, Long toAccountId, Double amount) {
        return ok(String.valueOf(accountService.transfer(fromAccountId, toAccountId, new BigDecimal(amount))));
    }

}
