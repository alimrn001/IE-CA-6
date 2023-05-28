package com.baloot.baloot.Repository;

import org.apache.commons.dbcp.BasicDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionPool {

    private static BasicDataSource ds = new BasicDataSource();

    static {
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        // remote database
        ds.setUrl("jdbc:mysql://localhost:3306/balootDB?useUnicode=true&characterEncoding=UTF-8");
        ds.setUsername("root");
        ds.setPassword("sqlPass80%");
        ds.setMinIdle(1);
        ds.setMaxIdle(2000);
        ds.setMaxOpenPreparedStatements(2000);
    }

    public static Connection getConnection() throws SQLException {
        try {
            return ds.getConnection();
        }
        catch (Exception e){
            ds.setPassword("sqlPass80%"); // ?!?!
            return ds.getConnection();
        }
    }

    private ConnectionPool() {
    }
}
