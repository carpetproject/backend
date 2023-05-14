package com.example.carpet.Services;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionService {

    public Connection GetConnection() throws SQLException;
}
