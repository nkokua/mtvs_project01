package com.ohgiraffers.todolist.dao;
import com.ohgiraffers.todolist.model.Tag;
import com.ohgiraffers.todolist.model.TagTodo;
import com.ohgiraffers.todolist.util.QueryUtil;
import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TagTodoDao extends Dao{
    public TagTodoDao(Connection connection) {
        super(connection);
    }
    public boolean createTagTodo(int tag_id,int todo_id, String xmlQry) {
        String query = QueryUtil.getQuery(xmlQry);
        try(PreparedStatement ptmt = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
        ){
            ptmt.setInt(1,tag_id);
            ptmt.setInt(2,todo_id);
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<TagTodo> getAllTagTodo(int tagId, int todoId) {
        List<TagTodo> tagTodos = new ArrayList<>();
        String query = QueryUtil.getQuery("getAllTagTodo");
        try (PreparedStatement ptmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            try(ResultSet rs = ptmt.executeQuery()) {
                while (rs.next()) {
                    tagTodos.add(new TagTodo(
                            rs.getInt("tagId"),
                            rs.getInt("TodoID")
                    ));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tagTodos;
    }

    public boolean existsTagTodo(int tagId,int todoId){
        String query = QueryUtil.getQuery("getExistsTagTodo");
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1,tagId);
            pstmt.setInt(2,todoId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0; // todoId 존재하면 반환
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
