package itstest;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import play.Application;
import play.mvc.Http;
import play.mvc.Result;
import play.test.Helpers;
import test.db.dao.UserDao;
import test.db.entity.Account;
import test.db.entity.User;
import test.db.service.AccountService;

import java.math.BigDecimal;

import static java.math.BigDecimal.ROUND_HALF_DOWN;
import static org.junit.Assert.*;
import static play.test.Helpers.*;

public class FirstTest {
    private static Application app;

    @BeforeClass
    public static void setUp() {
        app = fakeApplication(inMemoryDatabase());
        UserDao userDao = app.injector().instanceOf(UserDao.class);
        User user = new User();
        user.setEmail("test11@gmail.com");
        Account account = new Account();
        account.setMoneySum(BigDecimal.TEN);
        account.setUser(user);
        user.getAccounts().add(account);
        userDao.save(user);

        User user2 = new User();
        user2.setEmail("test22@gmail.com");
        Account account2 = new Account();
        account2.setMoneySum(BigDecimal.TEN);
        account2.setUser(user);
        user2.getAccounts().add(account2);
        userDao.save(user2);
    }

    @Test
    public void test1() {
        UserDao userDao = app.injector().instanceOf(UserDao.class);
        assertNotNull(userDao.getByEmail("test11@gmail.com"));
        assertNotNull(userDao.getByEmail("test22@gmail.com"));
    }

    @Test
    public void test2() {
        UserDao userDao = app.injector().instanceOf(UserDao.class);
        AccountService accountService = app.injector().instanceOf(AccountService.class);
        User firstUser = userDao.getByEmail("test11@gmail.com");
        User secondUser = userDao.getByEmail("test22@gmail.com");
        accountService.transfer(firstUser.getAccounts().get(0).getId(), secondUser.getAccounts().get(0).getId(), BigDecimal.ONE.setScale(2, ROUND_HALF_DOWN));
        firstUser = userDao.getByEmail("test11@gmail.com");
        secondUser = userDao.getByEmail("test22@gmail.com");

        assertEquals(firstUser.getAccounts().get(0).getMoneySum(), BigDecimal.TEN.subtract(new BigDecimal(1)).setScale(2, ROUND_HALF_DOWN));
        assertEquals(secondUser.getAccounts().get(0).getMoneySum(), BigDecimal.TEN.add(new BigDecimal(1)).setScale(2, ROUND_HALF_DOWN));
    }

    @Test
    public void test3() {
        UserDao userDao = app.injector().instanceOf(UserDao.class);
        User firstUser = userDao.getByEmail("test11@gmail.com");
        User secondUser = userDao.getByEmail("test22@gmail.com");


        Http.RequestBuilder request = Helpers.fakeRequest()
                .method(PUT)
                .uri("/transfer/" + secondUser.getAccounts().get(0).getId() + "/" + firstUser.getAccounts().get(0).getId() + "/" + "1");
        Result result = route(app, request);
        assertEquals(OK, result.status());

        firstUser = userDao.getByEmail("test11@gmail.com");
        secondUser = userDao.getByEmail("test22@gmail.com");

        assertEquals(firstUser.getAccounts().get(0).getMoneySum(), BigDecimal.TEN.setScale(2, ROUND_HALF_DOWN));
        assertEquals(secondUser.getAccounts().get(0).getMoneySum(), BigDecimal.TEN.setScale(2, ROUND_HALF_DOWN));
    }

    @Test
    public void test4() {
        AccountService accountService = app.injector().instanceOf(AccountService.class);
        UserDao userDao = app.injector().instanceOf(UserDao.class);
        User firstUser = userDao.getByEmail("test11@gmail.com");
        BigDecimal before1 = firstUser.getAccounts().get(0).getMoneySum();
        User secondUser = userDao.getByEmail("test22@gmail.com");
        BigDecimal before2 = secondUser.getAccounts().get(0).getMoneySum();
        boolean result = accountService.transfer(firstUser.getAccounts().get(0).getId(), secondUser.getAccounts().get(0).getId(), new BigDecimal("-1").setScale(2, ROUND_HALF_DOWN));
        assertFalse(result);
        firstUser = userDao.getByEmail("test11@gmail.com");
        BigDecimal after1 = firstUser.getAccounts().get(0).getMoneySum();
        secondUser = userDao.getByEmail("test22@gmail.com");
        BigDecimal after2 = secondUser.getAccounts().get(0).getMoneySum();
        assertEquals(before1, after1);
        assertEquals(before2, after2);
    }
    @AfterClass
    public static void setDown() {
        stop(app);
    }
}
