package com.ohgiraffers.todolist.dao;

import com.ohgiraffers.todolist.model.Todolist;
import com.ohgiraffers.todolist.model.User;
import com.ohgiraffers.todolist.util.QueryUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class TodolistDao extends Dao{
    public TodolistDao(Connection connection) {
        super(connection);
    }
    public boolean addTodolist(Todolist todo, String xmlqry) {
        String query = QueryUtil.getQuery(xmlqry); // XML에서 쿼리 로드

        try (PreparedStatement ptmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ptmt.setString(1,todo.getTodo());
            ptmt.setInt(2,todo.getMemberId());
            ptmt.setDate(3,todo.getCreationDate());
            ptmt.setString(4,"N");
            int affectedRows = ptmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
