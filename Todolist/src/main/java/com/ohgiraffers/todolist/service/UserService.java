package com.ohgiraffers.todolist.service;

import com.ohgiraffers.todolist.dao.UserDao;
import com.ohgiraffers.todolist.model.User;

import java.sql.Connection;

public class UserService {
    private final Connection connection;
    private UserDao userDao;  // UserDao 클래스가 데이터베이스와의 상호작용을 담당

    // 생성자
    public UserService(Connection con) {
        this.connection = con;
        this.userDao = new UserDao(con);
    }


    // 사용자 등록
    public boolean registerUser(User user) {
        if (user == null || user.getNickname().isEmpty() || user.getPassword().isEmpty()) {
            System.out.println("입력값이 비어있습니다!");
            return false;
        }
        if (userDao.existsUser(user.getEmail())) {
            throw new IllegalArgumentException("이미 존재하는 태그입니다.");
        }
        return userDao.addUser(user,"addUser");
    }

    public boolean loginUser(User user) {
        if (user == null || user.getNickname().isEmpty() || user.getPassword().isEmpty()) {
            System.out.println("입력값이 비어있습니다!");
            return false;
        }
        // 이메일 존재여부 확인후 비밀번호 존재여부 확인.
        if(user.getEmail().equals(userDao.getUser().getEmail())){
            if(userDao.getUser().getPassword().equals(user.getPassword())){
                return true;
            }
        };
        return false;
    }

}

