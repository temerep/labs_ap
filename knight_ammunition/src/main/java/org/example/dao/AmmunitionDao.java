package org.example.dao;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.example.entity.Entity;

import java.util.List;

public class AmmunitionDao<T extends Entity> extends AbstractDao<T> {
    private static final Logger LOGGER = LoggerFactory.getLogger(AmmunitionDao.class);

    public AmmunitionDao() {
    }

    public AmmunitionDao(List<T> storage) {
        super(storage);
    }

    @Override
    public List<T> findAll() throws Exception {
        return super.findAll();
    }

    @Override
    public T findEntityById(int id) throws Exception {
        return super.findEntityById(id);
    }

    @Override
    public boolean delete(T t) throws Exception {
        return super.delete(t);
    }

    @Override
    public boolean delete(int id) throws Exception {
        return super.delete(id);
    }

    @Override
    public boolean create(T t) throws Exception {
        return super.create(t);
    }

}
