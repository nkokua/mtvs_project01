package com.ohgiraffers.todolist.view;

import com.ohgiraffers.todolist.config.JDBCConnection;
import com.ohgiraffers.todolist.dao.UserDao;
import com.ohgiraffers.todolist.model.User;
import com.ohgiraffers.todolist.service.UserService;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class RegisterView extends View {
    Scanner input = new Scanner(System.in);
    private String nickname,email,password,inputValue;
    private User user;
    UserService userService;
    public RegisterView(Connection con) {
        super(con);
    }

    public boolean run(){
            while(true){
                System.out.println("회원가입페이지 , 이메일을 입력해주세요");
                    nickname = input.nextLine();
                System.out.println("회원가입페이지 , 이메일을 입력해주세요");
                    email = input.nextLine();
                System.out.println("회원가입페이지 , 비밀번호를 입력해주세요");
                    password = input.nextLine();
                System.out.println("회원가입페이지 , 가입하시겠습니까? y/n");
                    inputValue = input.nextLine();
                    while(true){
                        if(inputValue.equals("y")){
                            /*서비스로 전달*/
                            user= new User(nickname,email,password);
                            userService = new UserService(new UserDao(con));
                            userService.registerUser(user);
                            return true;
                        }else if(inputValue.equals("n")){

                            return true;
                        }else {
                            System.out.println("제대로된 값을 입력해주세요.");
                            break;
                        }
                    }

                }
            }
    }



