package com.ohgiraffers.todolist.service;

import com.ohgiraffers.todolist.dao.TagDao;
import com.ohgiraffers.todolist.dao.TagTodoDao;
import com.ohgiraffers.todolist.model.TagTodo;

import java.sql.Connection;
import java.sql.SQLException;

public class TagTodoService {
    private TagTodoDao tagTodoDao;
    private final Connection con;
    public TagTodoService(Connection con) {
        this.con = con;
        this.tagTodoDao = new TagTodoDao(con);
    }

    public boolean createTagTodo(int tagId,int todoId) throws SQLException {
        // 중복 확인
            if (tagTodoDao.existsTagTodo(tagId,todoId)) {
                throw new IllegalArgumentException("이미 부여된 태그입니다.");
            }
        // 중복이 아니면 추가 로직 수행 (예: DB에 삽입)
            return tagTodoDao.createTagTodo(tagId,todoId,"addTagTodo");
        }

}


