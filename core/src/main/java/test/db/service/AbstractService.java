package test.db.service;

import test.db.dao.CommonDao;

import java.util.List;

public class AbstractService<T> implements CommonService<T> {
    protected CommonDao<T> dao;

    public AbstractService(CommonDao<T> dao) {
        this.dao = dao;
    }

    @Override
    public T getById(Long id) {
        return dao.getById(id);
    }

    @Override
    public List<T> getAll() {
        return dao.getAll();
    }

    @Override
    public void save(T entity) {
        dao.save(entity);
    }

    @Override
    public void update(T entity) {
        dao.update(entity);
    }
}
