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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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
        accountService.transfer(firstUser.getAccounts().get(0).getId(), secondUser.getAccounts().get(0).getId(), BigDecimal.ONE);
        firstUser = userDao.getByEmail("test11@gmail.com");
        secondUser = userDao.getByEmail("test22@gmail.com");

        assertEquals(firstUser.getAccounts().get(0).getMoneySum(), BigDecimal.TEN.subtract(new BigDecimal(1)).setScale(1));
        assertEquals(secondUser.getAccounts().get(0).getMoneySum(), BigDecimal.TEN.add(new BigDecimal(1)).setScale(1));
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

        assertEquals(firstUser.getAccounts().get(0).getMoneySum(), BigDecimal.TEN.setScale(1));
        assertEquals(secondUser.getAccounts().get(0).getMoneySum(), BigDecimal.TEN.setScale(1));
    }

    @AfterClass
    public static void setDown(){
        stop(app);
    }
}
