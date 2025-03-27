package com.ohgiraffers.todolist;

import com.ohgiraffers.todolist.config.JDBCConnection;
import com.ohgiraffers.todolist.view.MainView;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

class ViewTest {
    MainView view;
    Connection con;
    @BeforeEach void setUp() throws SQLException {
        con = JDBCConnection.getConnection();
        view = new MainView(con);
    }
    @Test
    void run(){
        Assertions.assertTrue(view.run());
    }
    @AfterEach
    void tearDown() {
        view = null;
        JDBCConnection.close();
    }
}