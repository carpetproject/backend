package com.example.carpet.Services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ConnectionServiceImpl implements ConnectionService {

    @Value("${spring.datasource.url}")
    private String conString;

    @Value("${spring.datasource.username}")
    private String dbUser;

    @Value("${spring.datasource.password}")
    private String dbPassword;

    @Override
    public Connection GetConnection() throws SQLException {

        return DriverManager.getConnection(conString, dbUser, dbPassword);
    }

}
