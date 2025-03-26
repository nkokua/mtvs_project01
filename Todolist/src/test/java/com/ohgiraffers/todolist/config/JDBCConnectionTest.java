package com.ohgiraffers.todolist.config;

import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class JDBCConnectionTest {

    @BeforeAll
    static void setUp() {
        System.out.println("테스트 시작");
    }
    @Test
    void testGetConnection() throws SQLException {
        Connection connection = JDBCConnection.getConnection();
        Assertions.assertNotNull(connection,"연결생성실패");
        Assertions.assertFalse(connection.isClosed(),"커넥션이 닫혀있음");
    }
    @AfterAll
    static void tearDown() {
        JDBCConnection.close();
    }
}