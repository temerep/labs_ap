package org.example.dao;

import org.example.entity.Entity;

import java.util.List;

public interface IDao<T extends Entity> {
    List<T> findAll() throws Exception;
    T findEntityById(int id) throws Exception;
    boolean delete(T t) throws Exception;
    boolean delete(int id) throws Exception;
    boolean create(T t) throws Exception;

}
