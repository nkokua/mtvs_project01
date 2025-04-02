package com.ohgiraffers.todolist.model;

import java.sql.Date;

public class Todolist {
    private int todo_id;
    private String todo;
    private int userId;
    private Date creationDate;
    private Date completionDate;
    private char isCompleted;

    public Todolist(String todo) {
        this.todo = todo;
    }

    public Todolist(String todo, char isCompleted) {
        this.todo = todo;
        this.isCompleted = isCompleted;
    }

    public Todolist(String todo, int todoId) {
        this.todo = todo;
        this.todo_id = todoId;
    }

    public Todolist(int userId) {
        this.userId = userId;
    }


    public int getTodo_id() {
        return todo_id;
    }

    @Override
    public String toString() {
        return "Todolist{" +
                "todo_id=" + todo_id +
                ", todo='" + todo + '\'' +
                ", userId=" + userId +
                ", creationDate=" + creationDate +
                ", completionDate=" + completionDate +
                ", isCompleted=" + isCompleted +
                '}';
    }

    public void setTodo_id(Integer todo_id) {
        this.todo_id = todo_id;
    }

    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public int getuserId() {
        return userId;
    }

    public void setuserId(int userId) {
        this.userId = userId;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(Date completionDate) {
        this.completionDate = completionDate;
    }

    public char getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(char isCompleted) {
        this.isCompleted = isCompleted;
    }
}
