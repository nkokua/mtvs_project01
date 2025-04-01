package com.ohgiraffers.todolist.model;

public class Tag {
    private String tagName;
    private int tagId;

    public Tag(int tagId,String tagName) {
        this.tagName = tagName;
        this.tagId = tagId;
    }

    public Tag(String tagName) {
        this.tagName = tagName;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "tagName='" + tagName + '\'' +
                ", tagId=" + tagId +
                '}';
    }
}
