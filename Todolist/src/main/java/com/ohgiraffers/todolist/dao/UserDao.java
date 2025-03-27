package com.ohgiraffers.todolist.dao;

import com.ohgiraffers.todolist.model.User;
import com.ohgiraffers.todolist.util.QueryUtil;

import java.sql.*;

public class UserDao extends Dao {
    public UserDao(Connection connection) {
        super(connection);
    }


    public boolean addUser(User user,String xmlqry) {
        String query = QueryUtil.getQuery(xmlqry); // XML에서 쿼리 로드

        try (PreparedStatement ptmt = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS)) {
            ptmt.setString(1,user.getEmail());
            ptmt.setString(2,user.getNickname());
            ptmt.setString(3,user.getPassword());

            int affectedRows = ptmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteUser(User user,String xmlqry) {
        String query = QueryUtil.getQuery(xmlqry); // XML에서 쿼리 로드
        try (PreparedStatement ptmt = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS)) {
            ptmt.setString(1,user.getEmail());
            ptmt.setString(2,user.getNickname());
            ptmt.setString(3,user.getPassword());

            int affectedRows = ptmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return false;
    }



}
