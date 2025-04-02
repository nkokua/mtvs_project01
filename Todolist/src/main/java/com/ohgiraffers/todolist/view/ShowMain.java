package com.ohgiraffers.todolist.view;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class ShowMain {
    Scanner input ;
    private String inputValue;
    private UserView userView ;
    private TodolistView todolistView;
    public ShowMain(Connection con) {
      userView = new UserView(con);
      input = new Scanner(System.in);
      todolistView = new TodolistView(con);
    }

    public boolean run() throws SQLException {
         while(true){
             System.out.println("1 : 회원가입 ");
             System.out.println("\n2 : 로그인 ");
             System.out.println("\n3 : 종료 ");
             System.out.println("\n4 : 모든유저 조회 ");
             System.out.println("\n원하시는 기능을 입력해주세요 : ");

             inputValue = input.nextLine();
             switch(inputValue){
                 case "1":
                     if(userView.createUser()){
                         System.out.println("회원가입 성공");
                     }else{
                         System.out.println("회원가입 실패");
                     }
                     break;
                 case "2":
                     if(userView.loginUser()){
                         todolistView.showMenu();
                     }
                     break;
                 case "3":
                     return true;
                 case "4":
                     userView.getAllUser();
                     break;
                 default:
                     System.out.println("잘못된 값을 입력 하셨습니다 ");
             }
         }
    }



}
