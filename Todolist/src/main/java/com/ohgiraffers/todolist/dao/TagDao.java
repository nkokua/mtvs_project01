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
        String sql = "SELECT COUNT(*) FROM Tags WHERE name = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
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
        String sql = "INSERT INTO Tags (name) VALUES (?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, addTag);
            return pstmt.executeUpdate() > 0;
        }
    }

    public Tag getTagById(int tagId) throws SQLException {
        String sql = "SELECT * FROM Tags WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
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
        String sql = "DELETE FROM Tags WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, tagId);
            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean updateTag(Tag tag) throws SQLException {
        String sql = "UPDATE Tags SET name = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, tag.getTagName());
            pstmt.setInt(2, tag.getTagId());
            return pstmt.executeUpdate() > 0;
        }
    }
}



