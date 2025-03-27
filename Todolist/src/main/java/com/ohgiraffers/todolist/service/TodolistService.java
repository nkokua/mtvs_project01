package com.ohgiraffers.todolist.service;

import com.ohgiraffers.todolist.dao.TodolistDao;
import com.ohgiraffers.todolist.dao.UserDao;
import com.ohgiraffers.todolist.model.Todolist;
import com.ohgiraffers.todolist.model.User;

public class TodolistService {
    private TodolistDao todoDao;
    public boolean addTodo(Todolist todo) {
        if (todo == null || todo.getTodo().isEmpty() ) {
            return false;
        }
        return todoDao.addTodolist(todo,"addTodo");  // userDao는 사용자 데이터를 저장하는 역할
    }
}
