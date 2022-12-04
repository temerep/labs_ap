package org.example.dao;


import org.example.entity.Ammunition;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DBManager {
    private static DBManager instance;
    private static final AmmunitionDao ammunitionDao;


    private static final Connection connection;

    static {
        ammunitionDao = new AmmunitionDao();
        try {
            connection = ConnectionManager.createConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ammunitionDao.setConnection(connection);

    }


    public static synchronized DBManager getInstance() {
        if (instance == null) {
            instance = new DBManager();
        }
        return instance;
    }

    public static void close(Statement statement) {
        ammunitionDao.close(statement);
    }

    public static void setConnection(Connection connection) {
        ammunitionDao.setConnection(connection);
    }

    public static List<Ammunition> findAll() throws Exception {
        return ammunitionDao.findAll();
    }

    public static Ammunition findEntityById(int id) throws Exception {
        return ammunitionDao.findEntityById(id);
    }

    public static boolean delete(Ammunition t) throws Exception {
        return ammunitionDao.delete(t);
    }

    public static boolean delete(int id) throws Exception {
        return ammunitionDao.delete(id);
    }

    public static boolean create(Ammunition t) throws Exception {
        return ammunitionDao.create(t);
    }
}
