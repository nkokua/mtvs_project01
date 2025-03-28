package com.ohgiraffers.todolist.service;

import com.ohgiraffers.todolist.dao.TagDao;
import com.ohgiraffers.todolist.dao.TagTodoDao;
import com.ohgiraffers.todolist.dao.TodolistDao;
import com.ohgiraffers.todolist.dao.UserDao;
import com.ohgiraffers.todolist.model.TagTodo;
import com.ohgiraffers.todolist.model.Todolist;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TodolistService {
    private TodolistDao todoDao;
    private TagTodoDao tagtodoDao;
    private final Connection connection;
    public TodolistService(Connection con) {
        this.connection = con;
        this.todoDao = new TodolistDao(con);
    }
    public boolean addTodo(Todolist todo) {
        if (todo == null || todo.getTodo().isEmpty() ) {
            return false;
        }
        return todoDao.addTodolist(todo,"addTodo");  // userDao는 사용자 데이터를 저장하는 역할
    }

    public List<TagTodo> getAllTodolist() throws SQLException{
        List<TagTodo> tagtodos = tagtodoDao.getAllTagTodo("getTodoByTagId");
        return tagtodos;
    };
}
