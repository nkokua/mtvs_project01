package com.ohgiraffers.todolist.view;

import com.ohgiraffers.todolist.model.User;
import com.ohgiraffers.todolist.service.UserService;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class UserView {
    private final Scanner input ;
    private final UserService userService;

    private String nickname,email,password,inputValue;
    private User user;
    public UserView(Connection con) {
        input = new Scanner(System.in);
        this.userService = new UserService(con);
    }



    public boolean createUser() throws SQLException {
       while(true){
            System.out.println("회원가입페이지 , 닉네임을 입력해주세요");
            nickname = input.nextLine();
            System.out.println("회원가입페이지 , 이메일을 입력해주세요");
            email = input.nextLine();
            boolean isValid = email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
            if(!isValid){
                System.out.println("이메일 형식이 아닙니다.");
                System.out.println("가입 취소");
                return false;
            }
            System.out.println("회원가입페이지 , 비밀번호를 입력해주세요");
            password = input.nextLine();
            System.out.println("회원가입페이지 , 가입하시겠습니까? y/n");
            inputValue = input.nextLine();

            if(inputValue.equals("y")){
                /*서비스로 전달*/
                user = new User(nickname,email,password);
                return userService.registerUser(user);
            }else if(inputValue.equals("n")){
                System.out.println("가입 취소");
                return false;
            }else {
                    System.out.println("제대로된 값을 입력해주세요.");
            }


       }
    }

    public int loginUser() {
        while(true){
            System.out.println("로그인 페이지 , 이메일을 입력해주세요");
            email = input.nextLine();
            System.out.println("로그인 페이지 , 비밀번호를 입력해주세요");
            password = input.nextLine();
            System.out.println("로그인 페이지 , 로그인 하시겠습니까? y/n");
            inputValue = input.nextLine();

            if(inputValue.equals("y")){
                /*서비스로 전달*/
                user= new User(email,password);
                return userService.loginUser(user);
            }else if(inputValue.equals("n")){
                System.out.println("로그인 실패");
                return -1;
            }else {
                System.out.println("제대로된 값을 입력해주세요.");
            }

        }
    }

    public void getAllUser() {
        try{
            List<User> users = userService.getAllUser();
            if (users.isEmpty()) {
                System.out.println("등록된 사용자가 없습니다.");
            } else {
                System.out.println("\n===== 전체 사용자 목록 =====");
                System.out.println("닉네임 \t"+"이메일");
                users.forEach(user -> System.out.println(user.getNickname()+" "+user.getEmail()+"\n"));
            }
        } catch (SQLException e) {
            System.out.println("사용자 목록조회 중 오류 발생.");
        }
    }
}

