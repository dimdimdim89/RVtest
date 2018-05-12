package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import test.Constants;
import test.db.dao.AccountDao;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Arrays;

public class MainController extends Controller {

    @Inject
    @Named(value = Constants.ACCOUNT_DAO)
    private AccountDao accountDao;

    public Result index() {
        return ok(Arrays.toString(accountDao.getAll().toArray()));
    }

}
