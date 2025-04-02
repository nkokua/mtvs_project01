package com.ohgiraffers.todolist.dao;

import com.ohgiraffers.todolist.model.User;
import com.ohgiraffers.todolist.util.QueryUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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



    public boolean existsUserByEmail(String email) {
        String query = QueryUtil.getQuery("getExists");
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1,email);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0; // user 존재하면 반환
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<User> getAllUser() {
        List<User> users = new ArrayList<>();
        String query = QueryUtil.getQuery("getAllUser"); // XML에서 쿼리 로드
        try (PreparedStatement ptmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            try(ResultSet rs = ptmt.executeQuery()) {
                while (rs.next()) {
                    users.add(new User(
                            rs.getString("nickname"),
                            rs.getString("email"),
                            rs.getInt("userId")
                    ));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public User getUser(String email) {
        User user = null;
        String query = QueryUtil.getQuery("getUserByEmail");
        try (PreparedStatement ptmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ptmt.setString(1,email);
            try(ResultSet rs = ptmt.executeQuery()) {
                if (rs.next()) {
                    user= new User(
                            rs.getInt("userId"),
                            rs.getString("email"),
                            rs.getString("password")
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
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
