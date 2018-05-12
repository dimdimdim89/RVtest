package test.db.dao;

import java.util.List;

public interface CommonDao<T> {
    T getById(Long id);

    List<T> getAll();

    void save(T entity);
}
