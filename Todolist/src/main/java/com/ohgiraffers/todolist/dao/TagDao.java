package com.ohgiraffers.todolist.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.ohgiraffers.todolist.model.Tag;
import com.ohgiraffers.todolist.util.QueryUtil;

public class TagDao {
    private final Connection connection;

    public TagDao(Connection connection) {
        this.connection = connection;
    }

    public List<Tag> getAllUsers(String query) {
        List<Tag> tag = new ArrayList<>();
        String tmpqry = QueryUtil.getQuery(query); // XML에서 쿼리 로드

        try (PreparedStatement pstmt = connection.prepareStatement(tmpqry);
           ResultSet rs = pstmt.executeQuery(tmpqry)){
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
