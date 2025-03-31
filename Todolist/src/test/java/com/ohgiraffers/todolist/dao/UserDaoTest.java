package com.ohgiraffers.todolist.dao;

import com.ohgiraffers.todolist.config.JDBCConnection;
import com.ohgiraffers.todolist.model.User;
import com.ohgiraffers.todolist.util.QueryUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {

    private Connection connection;
    private UserDao dao;
    private User user,user2;
    List<User> users;
    @BeforeEach
    void setUp() {
        try{
            connection = JDBCConnection.getConnection();
            dao = new UserDao(connection);
            List<User> users = new ArrayList<>();
        }catch (SQLException e) {
            Assertions.fail("데이터 베이스 연결 실패");
        }
    }
    @Test
    void getAllUser() {
        users = dao.getAllUser();
        Assertions.assertTrue(dao.getAllUser().size()>0);
        users.forEach(user -> System.out.println(user.getNickname()+" "+user.getEmail()+"\n"));
    }
    @Test
    void addUser() {
        //실행
        Assertions.assertTrue(dao.addUser(user,"addUser"));
        Assertions.assertTrue(dao.addUser(user2,"addUser"));
    }

    @AfterEach
    void tearDown() {
        try {
            connection.close(); // 연결 닫기
            System.out.println("🔄 테스트 데이터 롤백 완료");
        } catch (SQLException e) {
            Assertions.fail("❌ 트랜잭션 롤백 실패: " + e.getMessage());
        }
    }


}