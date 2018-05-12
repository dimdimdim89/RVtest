package test.db.dao.impl;

import io.ebean.EbeanServer;
import io.ebean.TxScope;
import io.ebean.annotation.TxIsolation;
import test.db.dao.AbstractDao;
import test.db.dao.UserDao;
import test.db.entity.User;

import java.util.List;

public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    public UserDaoImpl(Class<User> clazz, EbeanServer ebeanServer) {
        super(clazz, ebeanServer);
    }

}
