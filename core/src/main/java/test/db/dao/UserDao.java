package test.db.dao;

import test.db.entity.User;

public interface UserDao extends CommonDao<User> {
    User getByEmail(String email);
}
