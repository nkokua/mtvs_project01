package com.ohgiraffers.todolist.dao;
import com.ohgiraffers.todolist.util.QueryUtil;
import java.sql.Connection;
import java.sql.*;

public class TagTodoDao extends Dao{
    public TagTodoDao(Connection connection) {
        super(connection);
    }
    public boolean addData(int tag_id,int todo_id, String xmlQry) {
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
}
