package com.ohgiraffers.todolist.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.ohgiraffers.todolist.model.Tag;
import com.ohgiraffers.todolist.model.TagTodo;
import com.ohgiraffers.todolist.util.QueryUtil;

public class TagDao extends Dao {

    public TagDao(Connection connection) {
        super(connection);
    }
// 태그부여
    public List<TagTodo> getTodoByTagId(int tagId, String xmlQry) throws SQLException {
        List<TagTodo> tagtodo = new ArrayList<>();
        String query = QueryUtil.getQuery(xmlQry); // XML에서 쿼리로
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {

            try (ResultSet rs = pstmt.executeQuery()) {

                pstmt.setInt(1, tagId);
                while (rs.next()) {
                    new TagTodo(
                          rs.getString("Todo"),
//                        라벨명 Todo -> todo(할일)
                            rs.getString("태그명"));
//                        라벨명 태그명 -> tag_name(태그이름)
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return tagtodo;
        }
    }
// 모든 태그목록 조회
    public List<Tag> getAllTag(String xmlQry) {
        List<Tag> tag = new ArrayList<>();
        String query = QueryUtil.getQuery(xmlQry); // XML에서 쿼리로
        try (PreparedStatement pstmt = connection.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                tag.add(new Tag(
                        rs.getInt("tag_id"),
                        rs.getString("tag_name")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tag;
    }
    //같은이름의 태그존재조회. 수정요망
    public boolean existsTag(String tagName) throws SQLException {
        String query = QueryUtil.getQuery("existsTag");
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, tagName);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        }
    }

    public boolean createTag(Tag tag, String addTag) throws SQLException {
        if (existsTag(addTag)) {
            return false;
        }
        String query = QueryUtil.getQuery("createTag");
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, addTag);
            return pstmt.executeUpdate() > 0;
        }
    }

    public Tag getTagById(int tagId) throws SQLException {
        String query = QueryUtil.getQuery("getTagById");
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, tagId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Tag(rs.getInt("id"), rs.getString("name"));
                }
            }
        }
        return null;
    }

    public boolean deleteTag(int tagId) throws SQLException {
        String query = QueryUtil.getQuery("deleteTag");
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, tagId);
            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean updateTag(Tag tag) throws SQLException {
        String query = QueryUtil.getQuery("updateTag");
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, tag.getTagName());
            pstmt.setInt(2, tag.getTagId());
            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean deleteTagtodo(int tagId) {
        String query = QueryUtil.getQuery("deleteTagtodoByTag");
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, tagId);
            int affectedRows = pstmt.executeUpdate(); // 삭제된 행의 수를 반환
            // 삭제 성공시 affectedRows > 0, 존재하지 않는 경우 0 반환
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }
}



