package com.ohgiraffers.todolist.model;

public class Tag {
    private String tagName;
    private int todoId;

    public Tag(String tagName, int todoId) {
        this.tagName = tagName;
        this.todoId = todoId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public Integer getTodoId() {
        return todoId;
    }

    public void setTodoId(int todoId) {
        this.todoId = todoId;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "tagName='" + tagName + '\'' +
                ", todoId=" + todoId +
                '}';
    }
}
