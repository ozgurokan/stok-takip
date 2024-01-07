package com.ozgurokanozdal.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    private Connection connection = null;

    public Connection connectDB(){
        try{
            this.connection = DriverManager.getConnection(DBConfig.url,DBConfig.username,DBConfig.password);

        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }

        return this.connection;
    }

    public static Connection getInstance(){
        DBConnector db = new DBConnector();
        return db.connectDB();
    }

}
