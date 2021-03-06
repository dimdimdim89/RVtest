package test.db.dao;

import io.ebean.EbeanServer;

import java.util.List;

public interface CommonDao<T> {
    T getById(Long id);

    List<T> getAll();

    void save(T entity);

    void update(T entity);

    EbeanServer server();
}
