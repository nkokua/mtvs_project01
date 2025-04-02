package com.ohgiraffers.todolist;

import com.ohgiraffers.todolist.config.JDBCConnection;
import com.ohgiraffers.todolist.view.ShowMain;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

class ViewTest {
    ShowMain view;
    Connection con;
    @BeforeEach void setUp() throws SQLException {
        con = JDBCConnection.getConnection();
        view = new ShowMain(con);
    }
    @Test
    void run(){
        try {
            Assertions.assertTrue(view.run());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @AfterEach
    void tearDown() {
        view = null;
        JDBCConnection.close();
    }
}