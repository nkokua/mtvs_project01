package com.ohgiraffers.todolist;

import com.ohgiraffers.todolist.config.JDBCConnection;
import com.ohgiraffers.todolist.service.TodolistService;
import com.ohgiraffers.todolist.view.ShowMain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class Application {
    public static final Logger log = LoggerFactory.getLogger(TodolistService.class);

    public static void main(String[] args) {

        try (Connection con = JDBCConnection.getConnection()){
            ShowMain view = new ShowMain(con);
            view.run();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



        JDBCConnection.close();
    }


}
