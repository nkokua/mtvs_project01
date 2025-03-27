package com.ohgiraffers.todolist;

import com.ohgiraffers.todolist.config.JDBCConnection;
import com.ohgiraffers.todolist.view.MainView;

import java.sql.Connection;
import java.sql.SQLException;

public class Application {
    public static void main(String[] args) {
        final Connection con;
        try {
            con= JDBCConnection.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("원하시는 기능을 입력해주세요");
        MainView view = new MainView(con);
        view.run();
        JDBCConnection.close();
    }
}
