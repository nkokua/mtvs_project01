package com.ohgiraffers.todolist.service;

import com.ohgiraffers.todolist.dao.TagDao;
import com.ohgiraffers.todolist.dao.TagTodoDao;
import com.ohgiraffers.todolist.dao.TodolistDao;
import com.ohgiraffers.todolist.dao.UserDao;
import com.ohgiraffers.todolist.model.TagTodo;
import com.ohgiraffers.todolist.model.Todolist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TodolistService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private TodolistDao todoDao;
    private TagDao tagDao;
    private final Connection connection;


    public TodolistService(Connection con) {
        this.connection = con;
        this.todoDao = new TodolistDao(con);
        this.tagDao = new TagDao(con);
    }
    public boolean addTodo(Todolist todo) throws SQLException{
        if (todo == null || todo.getTodo().isEmpty() ) {
            return false;
        }
        return todoDao.addTodolist(todo,"addTodo");
    }

    public List<TagTodo> getAllTodolist() throws SQLException{
        List<TagTodo> tagtodos = todoDao.getAllTodolist("getAllData");
        if(tagtodos==null){
            log.error("조회목록이 없거나 오류발생");
            return null;
        }
        return tagtodos;
    };

    public boolean deleteTodo(int todoId) throws SQLException{
        Todolist todo = todoDao.getTodoById(todoId);
        if (todo == null) {
            throw new IllegalArgumentException("삭제할 todo를 찾을 수 없습니다.");
        }
        return todoDao.deleteTodo(todoId);
    }

    public boolean updateTodo(Todolist todolist) throws SQLException {
        // 1️⃣ 기존 사용자 존재 여부 확인
        Todolist existsTodolist = todoDao.getTodoById(todolist.getTodo_id());

        if (existsTodolist == null) {
            throw new IllegalArgumentException("수정할 Todo를 찾을 수 없습니다.");
        }
        // 3️⃣ 업데이트 수행
        boolean result = todoDao.updateTodo(todolist);
        if (!result) {
            throw new SQLException("수정하는 과정에서 오류가 발생되었습니다.");
        }
        return result;
    }

    public boolean updateCompletionTodo(int todoId) throws SQLException {
        Todolist todo = todoDao.getTodoById(todoId);
        if (todo == null) {
            throw new IllegalArgumentException("대상 todo를 찾을 수 없습니다.");
        }
        boolean result = false;
        if(todo.getIsCompleted() == 'Y'){
            result = todoDao.updateCompletionTodo(todoId,'N');
            if (!result) {
                throw new SQLException("Y-N 과정에서 오류가 발생되었습니다.");
            }
        }else if(todo.getIsCompleted() == 'N'){
            result = todoDao.updateCompletionTodo(todoId,'Y');
            if (!result) {
                throw new SQLException("N-Y 과정에서 오류가 발생되었습니다.");
            }
        }
        return result;
    }


}
