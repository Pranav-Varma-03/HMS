package com.example.hms4;

import java.sql.Connection;
import java.sql.DriverManager;

public class dbConnection {
    public Connection getconnection(){
        Connection conn;
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hmsdb_1","root","MysqlML@323");
            return conn;
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }
}
