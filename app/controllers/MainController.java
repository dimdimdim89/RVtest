package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import test.db.service.AccountService;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.Arrays;

public class MainController extends Controller {

    @Inject
    private AccountService accountService;

    public Result index() {
        return ok(Arrays.toString(accountService.getAll().toArray()));
    }

    public Result transfer(Long fromAccountId, Long toAccountId, Double amount) {
        return ok(String.valueOf(accountService.transfer(fromAccountId, toAccountId, new BigDecimal(amount))));
    }

}
