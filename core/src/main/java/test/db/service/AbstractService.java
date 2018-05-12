package test.db.service;

import test.db.dao.CommonDao;

import java.util.List;

public class AbstractService<T> implements CommonService<T> {
    protected CommonDao<T> abstractDao;

    public AbstractService(CommonDao<T> abstractDao) {
        this.abstractDao = abstractDao;
    }

    @Override
    public T getById(Long id) {
        return abstractDao.getById(id);
    }

    @Override
    public List<T> getAll() {
        return abstractDao.getAll();
    }

    @Override
    public void save(T entity) {
        abstractDao.save(entity);
    }

    @Override
    public void update(T entity) {
        abstractDao.update(entity);
    }
}
