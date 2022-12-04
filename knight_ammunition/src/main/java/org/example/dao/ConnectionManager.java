package org.example.dao;


import org.example.constants.Constants;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {
    //private static final Properties properties = new Properties();
    private static final String DATABASE_URL;

    static {
//        try {
//            properties.load(new FileReader(Constants.SETTINGS_FILE));
////            String driverName = (String) properties.get("db.driver");
////            Class.forName(driverName);
//        } catch (IOException e) {
//            e.printStackTrace(); // fatal exception
//        }

//        DATABASE_URL = (String) properties.get("connection.url");
        DATABASE_URL = "jdbc:mysql://" + Constants.dbHost + ":" + Constants.dbPort + "/" + Constants.dbName;
    }

    private ConnectionManager() {
    }

    public static Connection createConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, Constants.dbUser, Constants.dbPass);
    }
}