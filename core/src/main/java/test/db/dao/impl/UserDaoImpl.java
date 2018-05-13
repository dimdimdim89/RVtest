package test.db.dao.impl;

import io.ebean.EbeanServer;
import test.db.dao.AbstractDao;
import test.db.dao.UserDao;
import test.db.entity.User;

import javax.inject.Inject;

public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    @Inject
    public UserDaoImpl(EbeanServer ebeanServer) {
        super(User.class, ebeanServer);
    }

    @Override
    public User getByEmail(String email) {
        return server().find(User.class).where().eq("email", email).findOne();
    }
}
