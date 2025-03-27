package com.ohgiraffers.todolist.model;

import java.sql.Date;

public class Todolist {
    private Integer todo_id;
    private String todo;
    private int memberId;
    private Date creationDate;
    private Date completionDate;
    private char isCompleted;

    public Integer getTodo_id() {
        return todo_id;
    }

    @Override
    public String toString() {
        return "Todolist{" +
                "todo_id=" + todo_id +
                ", todo='" + todo + '\'' +
                ", memberId=" + memberId +
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

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
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
