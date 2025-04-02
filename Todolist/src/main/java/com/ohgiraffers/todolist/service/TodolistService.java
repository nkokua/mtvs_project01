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
    private TagTodoDao tagTodoDao;
    private TodolistDao todoDao;
    private TagDao tagDao;
    private final Connection connection;

    public TodolistService(Connection con,int userId) {
        this.connection = con;
        this.todoDao = new TodolistDao(con,userId);
        this.tagDao = new TagDao(con,userId);
        this.tagTodoDao = new TagTodoDao(con);

    }


    public boolean addTodo(Todolist todo) throws SQLException{
//        공백방지
        if (todo == null || todo.getTodo().isEmpty() ) {
            System.out.println("공백값입니다.");
            return false;
        }
//        중복방지
        if (todoDao.existsTodo(todo.getTodo())){
            System.out.println("중복값입니다.");
            return false;
        }

        return todoDao.addTodolist(todo,"addTodo");
    }

    public List<TagTodo> getAllTodolist() throws SQLException{
        List<TagTodo> tagtodos = todoDao.getAllTodolist("getAllTodolist");
        if(tagtodos==null){
            System.out.println("조회목록이 없거나 오류발생");
            return null;
        }
        return tagtodos;
    };

    public boolean deleteTodo(int todoId) throws SQLException{

        Todolist todo = todoDao.getTodoById(todoId);
        if (todo == null) {
            throw new IllegalArgumentException("삭제할 todo를 찾을 수 없습니다.");
        }
        if(tagTodoDao.existsTagTodoByTodoId(todoId)){
            todoDao.deleteTagtodo(todoId);
            //실패시 실패반환
        }
        return todoDao.deleteTodo(todoId);

    }


    /** TODO를 수정합니다
     * */
    public boolean updateTodo(Todolist todolist) throws SQLException {
        // 1️⃣ todo 존재 여부 확인
        Todolist existsTodolist = todoDao.getTodoById(todolist.getTodo_id());

        if (existsTodolist == null) {
            throw new IllegalArgumentException("수정할 Todo를 찾을 수 없습니다.");
        }
        if (todolist.getTodo()==null){
            throw new IllegalArgumentException("Todo가 공백입니다.");

        }
        // 3️⃣ 업데이트 수행
        boolean result = todoDao.updateTodo(todolist);
        if (!result) {
            throw new SQLException("수정하는 과정에서 오류가 발생되었습니다.");
        }
        return result;
    }



    /**
     * 완료여부를 Y면 N
     * N이면 Y로 바꿔줍니다.
     * */
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
            result = todoDao.deleteCompletionDate(todoId);
            if (!result) {
                throw new SQLException("CompletionDate 초기화 과정에서 오류발생하였습니다.");
            }
        }else if(todo.getIsCompleted() == 'N'){
            result = todoDao.updateCompletionTodo(todoId,'Y');
            if (!result) {
                throw new SQLException("N-Y 과정에서 오류가 발생되었습니다.");
            }
            result = todoDao.updateCompletionDate(todoId);
            if (!result) {
                throw new SQLException("completionDate 갱신 과정에서 오류가 발생되었습니다.");
            }
        }
        return result;
    }


    public boolean existsUsersTodoId(int todoId) {
        return todoDao.existsTodoIdByUserId(todoId);
    }
}
