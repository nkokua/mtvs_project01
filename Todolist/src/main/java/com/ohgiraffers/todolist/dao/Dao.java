package com.ohgiraffers.todolist.dao;

import com.ohgiraffers.todolist.model.Tag;

import java.sql.Connection;
import java.util.List;
import java.util.Objects;

public class Dao{
    protected final Connection connection;
    public Dao(Connection connection) {
        this.connection = connection;
    }
}
