package com.ozgurokanozdal.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    private Connection connection;
    public Connection connectDB(){
        try{
            if(connection == null){
                this.connection = DriverManager.getConnection(DBConfig.url,DBConfig.username,DBConfig.password);
            }
            return connection;
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }
    public static Connection getInstance(){
        DBConnector db = new DBConnector();
        return db.connectDB();
    }
}
