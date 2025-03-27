package com.ohgiraffers.todolist.model;

public class TagTodo {
    private int tagId;
    private int todoId;
    private String todo;
    private String tagName;

    public TagTodo(String todo, String tagName) {
        this.todo = todo;
        this.tagName = tagName;
    }

    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public void setTodoId(int todoId) {
        this.todoId = todoId;
    }

    public int getTagId() {
        return tagId;
    }

    public int getTodoId() {
        return todoId;
    }
}
