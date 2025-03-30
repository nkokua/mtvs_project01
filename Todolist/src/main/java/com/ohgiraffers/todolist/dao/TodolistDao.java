package com.ohgiraffers.todolist.dao;

import com.ohgiraffers.todolist.model.TagTodo;
import com.ohgiraffers.todolist.model.Todolist;

import com.ohgiraffers.todolist.util.QueryUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public List<TagTodo> getAllTodolist(String getAllData) {
        String query = QueryUtil.getQuery(xmlqry); // XML에서 쿼리 로드
        List<TagTodo> tagTodos = new ArrayList<>();
        try (PreparedStatement ptmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            try(ResultSet rs = ptmt.executeQuery()) {
                    while (rs.next()) {
//                        todo_id AS "TodoID",
//                                tl.todo AS "Todo",
//                                t.tag_name AS "태그명",
//                                tl.creation_date AS "생성일",
//                                tl.completion_date "완료일",
//                                tl.iscompleted AS "완료 여부"
                                tagTodos.add(new TagTodo(
                                rs.getInt("TodoID"),
                                rs.getString("Todo"),
                                rs.getString("태그명"),
                                rs.getDate("생성일"),
                                rs.getDate("완료일"),
                                rs.getString("완료 여부")
                                ));
                    }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tagTodos;
    }
}
