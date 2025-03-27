package com.ohgiraffers.todolist.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.ohgiraffers.todolist.model.Tag;
import com.ohgiraffers.todolist.model.TagTodo;
import com.ohgiraffers.todolist.util.QueryUtil;

public class TagDao extends Dao  {

    public TagDao(Connection connection) {
        super(connection);
    }
    public List<TagTodo> getTodoByTagId(String xmlQry) {
        List<TagTodo> tagtodo = new ArrayList<>();
        String query = QueryUtil.getQuery(xmlQry); // XML에서 쿼리로
        try (PreparedStatement pstmt = connection.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()){
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

    public List<Tag> getAllData(String xmlQry) {
        List<Tag> tag = new ArrayList<>();
        String query = QueryUtil.getQuery(xmlQry); // XML에서 쿼리로
        try (PreparedStatement pstmt = connection.prepareStatement(query);
           ResultSet rs = pstmt.executeQuery()){
            while (rs.next()) {
                tag.add(new Tag(
                        rs.getString("tag_name"),
                        rs.getInt("todo_id")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tag;
    }

}
