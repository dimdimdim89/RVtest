package test.db.dao;

import io.ebean.EbeanServer;

import java.util.List;

public class AbstractDao<T> implements CommonDao<T> {
    protected EbeanServer ebeanServer;
    private Class<T> clazz;

    public AbstractDao(Class<T> clazz, EbeanServer ebeanServer) {
        this.clazz = clazz;
        this.ebeanServer = ebeanServer;
    }

    public T getById(Long id) {
        return ebeanServer.find(clazz).where().eq("id", id).findOne();
    }

    public List<T> getAll() {
        return ebeanServer.find(clazz).findList();
    }

    public void save(T entity) {
        ebeanServer.save(entity);
    }

}
