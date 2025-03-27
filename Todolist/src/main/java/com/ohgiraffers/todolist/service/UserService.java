package com.ohgiraffers.todolist.service;

import com.ohgiraffers.todolist.dao.UserDao;
import com.ohgiraffers.todolist.model.User;

public class UserService {

    private UserDao userDao;  // UserDao 클래스가 데이터베이스와의 상호작용을 담당

    // 생성자
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    // 사용자 등록
    public boolean registerUser(User user) {
        if (user == null || user.getNickname().isEmpty() || user.getPassword().isEmpty()) {
            return false;
        }
        return userDao.addUser(user,"addUser");  // userDao는 사용자 데이터를 저장하는 역할
    }

    // 사용자 조회
    public User getUserById(int userId) {
        return userDao.findUserById(userId);
    }

    // 사용자 정보 수정
    public boolean updateUser(User user) {
        return userDao.updateUser(user);  // 수정된 사용자 정보를 DB에 업데이트
    }

    // 사용자 삭제
    public boolean deleteUser(int userId) {
        return userDao.deleteUser(userId);
    }
}

