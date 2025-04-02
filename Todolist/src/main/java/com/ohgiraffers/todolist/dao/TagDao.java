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

    public List<TagTodo> getTodoByTagId(int tagId, String xmlQry) throws SQLException {
        List<TagTodo> tagTodos = new ArrayList<>();
        String query = QueryUtil.getQuery(xmlQry); // XML에서 쿼리로
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, tagId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
//                                      todo_id AS "TodoID",
//                                tl.todo AS "Todo",
//                                t.tag_name AS "태그명",
//                                tl.creation_date AS "생성일",
//                                tl.completion_date "완료일",
//                                tl.iscompleted AS "완료 여부"
                    tagTodos.add(new TagTodo(
                            rs.getInt("TagId"),
                            rs.getInt("TodoID"),
                            rs.getString("Todo"),
                            rs.getString("태그명"),
                            rs.getDate("생성일"),
                            rs.getDate("완료일"),
                            rs.getString("완료 여부").charAt(0)
                    ));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return tagTodos;
        }
    }


    /*
    *         String query = QueryUtil.getQuery(xmlqry); // XML에서 쿼리 로드
        List<TagTodo> tagTodos = new ArrayList<>();
        try (PreparedStatement ptmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            try(ResultSet rs = ptmt.executeQuery()) {
                    while (rs.next()) {
//                                      todo_id AS "TodoID",
//                                tl.todo AS "Todo",
//                                t.tag_name AS "태그명",
//                                tl.creation_date AS "생성일",
//                                tl.completion_date "완료일",
//                                tl.iscompleted AS "완료 여부"
                                tagTodos.add(new TagTodo(
                                        rs.getInt("TagId"),
                                rs.getInt("TodoID"),
                                rs.getString("Todo"),
                                rs.getString("태그명"),
                                rs.getDate("생성일"),
                                rs.getDate("완료일"),
                                rs.getString("완료 여부").charAt(0)
                                ));
                    }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tagTodos;
    *
    * */
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

    public boolean createTag(Tag tag) throws SQLException {
        if (existsTag(tag.getTagName())) {
            return false;
        }
        String query = QueryUtil.getQuery("createTag");
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, tag.getTagName());
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



