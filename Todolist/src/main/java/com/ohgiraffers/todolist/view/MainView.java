package com.ohgiraffers.todolist.view;

import java.sql.Connection;
import java.util.Scanner;

public class MainView extends View {
    Scanner input = new Scanner(System.in);
    private String inputValue;
    private RegisterView registerView = new RegisterView(super.con);

    public MainView(Connection con) {
        super(con);
    }

    public boolean run(){
         while(true){

             System.out.println("1 : 회원가입 ");
             System.out.println("\n2 : 로그인 ");
             System.out.println("\n3 : 종료 ");
             System.out.println("\n원하시는 기능을 입력해주세요 : ");
             inputValue = input.nextLine();
             switch(inputValue){
                 case "1":
                     registerView.run();
                     break;
                 case "2":
                     System.out.println("로그인페이지 , 아이디를 입력해주세요");
                     break;
                 case "3":

                     return true;
                 default:
             }
         }
    }



}
