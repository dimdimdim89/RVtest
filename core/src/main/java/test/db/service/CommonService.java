package test.db.service;

import java.util.List;

public interface CommonService<T> {
    T getById(Long id);

    List<T> getAll();

    void save(T entity);

    void update(T entity);
}
