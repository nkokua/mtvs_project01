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
    private final Connection con;
    public TagService(Connection con) {
        this.con = con;
        this.tagDao = new TagDao(con);
    }
    public List<Tag> getAllTag() throws SQLException{
        return tagDao.getAllData("");
    }
    public List<TagTodo> getTodoByTagId(int tagId) throws SQLException{
        return tagDao.getTodoByTagId(tagId,"getTodoByTagId");
    }
    public boolean createTag(Tag tag) throws SQLException {
        return true;
    }

    public boolean deleteTag(int tagId) throws SQLException {
        return true;
    }
    public boolean updateTag(Tag tag) throws SQLException {
        return true;
    }

}
