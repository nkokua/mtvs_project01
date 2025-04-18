package com.ohgiraffers.todolist.service;

import com.ohgiraffers.todolist.dao.UserDao;
import com.ohgiraffers.todolist.model.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static com.ohgiraffers.todolist.Application.log;

public class UserService {
    private final Connection connection;
    private UserDao userDao;  // UserDao 클래스가 데이터베이스와의 상호작용을 담당

    // 생성자
    public UserService(Connection con) {
        this.connection = con;
        this.userDao = new UserDao(con);
    }


    /**
     *
     * 입력 데이터를 userDao.addUser로 넘겨줍니다.
     * 트랜잭션으로 관리.
     * */
    public boolean registerUser(User user) throws SQLException {
        connection.setAutoCommit(false);
        if (user == null || user.getNickname().isEmpty() || user.getPassword().isEmpty()) {
            System.out.println("입력값이 비어있습니다!");
            return false;
        }
        if (userDao.existsUserByEmail(user.getEmail())) {
            throw new IllegalArgumentException("이미 존재하는 유저입니다.");
        }
        boolean result = userDao.addUser(user,"addUser");
        if (result) {
            connection.commit();
            return result;
        }else{
            connection.rollback();
            return result;
        }
    }

    public int loginUser(User user) {
        if (user == null || user.getEmail().isEmpty() || user.getPassword().isEmpty()) {
            System.out.println("입력값이 비어있습니다!");
            return -1;
        }
        // 이메일 존재여부 확인후 비밀번호 일치여부 확인.
        if(userDao.existsUserByEmail(user.getEmail())) {
            if(userDao.getUser(user.getEmail()).getPassword().equals(user.getPassword())){
                System.out.println("환영합니다.");
                return userDao.getUser(user.getEmail()).getuserId();
            }
        };
        System.out.println("로그인 실패입니다.");
        return -1;
    }

    public List<User> getAllUser() throws SQLException {
        List<User> users = userDao.getAllUser();
        if(users==null){
            log.error("조회목록이 없거나 오류발생");
            return null;
        }
        return users;
    };

}

