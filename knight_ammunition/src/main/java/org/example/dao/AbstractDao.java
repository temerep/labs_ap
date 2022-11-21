package org.example.dao;

import org.example.entity.Entity;


import java.util.ArrayList;
import java.util.List;

public class AbstractDao<T extends Entity> implements IDao<T> {
    List<T> storage;

    public AbstractDao(List<T> storage) {
        this.storage = storage;
    }

    public AbstractDao() {
        storage = new ArrayList<>();
    }

    @Override
    public List<T> findAll() throws Exception {
        return new ArrayList<>(storage);
    }

    @Override
    public T findEntityById(int id) throws Exception {
        return storage.get(id);
    }

    @Override
    public boolean delete(T t) throws Exception {
        return storage.remove(t);
    }

    @Override
    public boolean delete(int id) throws Exception {
        storage.remove(id);
        return true;
    }

    @Override
    public boolean create(T t) throws Exception {
        t.setId(storage.size());
        return storage.add(t);
    }
}
