package com.ebac.modulo59.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {
    public MySQLConnection(){

    }

    public Connection getConnection(String url, String user , String password) throws SQLException {
        return DriverManager.getConnection(url , user , password);
    }
}
