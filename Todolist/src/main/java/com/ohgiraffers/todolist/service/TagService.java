package com.ohgiraffers.todolist.service;

import com.ohgiraffers.todolist.dao.TagDao;
import com.ohgiraffers.todolist.dao.TagTodoDao;
import com.ohgiraffers.todolist.dao.TodolistDao;
import com.ohgiraffers.todolist.model.Tag;
import com.ohgiraffers.todolist.model.TagTodo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TagService {
    private TagDao tagDao;
    private TagTodoDao tagTodoDao;
    private final Connection con;

    public TagService(Connection con) {
        this.con = con;
        this.tagDao = new TagDao(con);
        this.tagTodoDao = new TagTodoDao(con);

    }

    public List<Tag> getAllTag() throws SQLException {
        return tagDao.getAllTag("getAllTag");
    }

    public List<TagTodo> getTodoByTagId(int tagId) throws SQLException {
        return tagDao.getTodoByTagId(tagId, "getTodoByTagId");
    }

    public boolean createTag(Tag tag) throws SQLException {
        // 중복 확인
        if (tagDao.existsTag(tag.getTagName())) {
            throw new IllegalArgumentException("이미 존재하는 태그입니다.");
        }
        // 중복이 아니면 추가 로직 수행 (예: DB에 삽입)
        return tagDao.createTag(tag, "addTag");

    }

    public boolean deleteTag(int tagId) throws SQLException {
        con.setAutoCommit(false);

        if (tagDao.getTagById(tagId) == null) {
            throw new IllegalArgumentException("삭제할 태그를 찾을 수 없습니다.");
        }
        if(tagTodoDao.existsTagTodoByTagId(tagId)){
            tagDao.deleteTagtodo(tagId);
        //실패시 실패반환
        }
        boolean result = tagDao.deleteTag(tagId);
        if (result) {
            con.commit();
        }else{
            con.rollback();
        }
        return result;
    }

    public boolean updateTag(Tag tag) throws SQLException {
        if (tagDao.getTagById(tag.getTagId()) == null) {
            throw new IllegalArgumentException("업데이트할 태그를 찾을 수 없습니다.");
        }
        return tagDao.updateTag(tag);
    }
}