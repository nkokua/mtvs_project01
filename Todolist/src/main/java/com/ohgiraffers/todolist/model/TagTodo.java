package com.ohgiraffers.todolist.model;
// tagtodoDto로 대체!!
import java.sql.Date;
// 태그 투두를 둘다 담는 경우
public class TagTodo {
    private int tagId;
    private int todoId;
    private String todo;
    private String tagName;
    private int memberId;
    private Date creationDate;
    private Date completionDate;
    private char isCompleted;

    public TagTodo(int tagId, int todoId, String todo, String tagName, int memberId, Date creationDate, Date completionDate, char isCompleted) {
        this.tagId = tagId;
        this.todoId = todoId;
        this.todo = todo;
        this.tagName = tagName;
        this.memberId = memberId;
        this.creationDate = creationDate;
        this.completionDate = completionDate;
        this.isCompleted = isCompleted;
    }
    public TagTodo(int todoID, String todo, String tagName, Date creationDate, Date completionDate, char isCompleted) {
        this.todoId = todoId;
        this.todo = todo;
        this.tagName = tagName;
        this.creationDate = creationDate;
        this.completionDate = completionDate;
        this.isCompleted = isCompleted;
    }

    public TagTodo(String todo, String tagName) {
        this.todo = todo;
        this.tagName = tagName;
    }

    public TagTodo(int tagId, String tagName) {
        this.tagId = tagId;
        this.tagName = tagName;
    }


    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public int getTodoId() {
        return todoId;
    }

    public void setTodoId(int todoId) {
        this.todoId = todoId;
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
