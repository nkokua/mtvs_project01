package com.ohgiraffers.todolist.view;

import com.ohgiraffers.todolist.model.Tag;
import com.ohgiraffers.todolist.model.TagTodo;
import com.ohgiraffers.todolist.model.Todolist;
import com.ohgiraffers.todolist.model.User;
import com.ohgiraffers.todolist.service.TodolistService;
import com.ohgiraffers.todolist.service.UserService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class TodolistView {
    private Scanner scanner ;
    private String inputValue;
    TodolistService todolistService;
    Todolist todo;
    private TagView tagView;

    public TodolistView(Connection con) {
        todolistService = new TodolistService(con);
        scanner = new Scanner(System.in);
        tagView = new TagView(con);
    }

    public void showMenu() {
        while(true){
            System.out.println("Todolist 페이지입니다 원하시는 기능을 입력해주세요!!");
            System.out.println("1.생성\n");
            System.out.println("2.수정\n");
            System.out.println("3.삭제\n");
            System.out.println("4.전체 조회\n");
            System.out.println("5.특정 태그 리스트 조회");
            System.out.println("6.태그 페이지로 이동");
            System.out.println("7.메인페이지로");


            int choice = scanner.nextInt();
            switch(choice){
                case 1:
                    createTodo();
                    break;
                case 2:
                    updateTodo();
                    break;
                case 3:
                    deleteTodo();
                    break;
                case 4:
                    readAllTodolist();
                    break;
                case 5:
//                    getAllTodoById();
                    break;
                case 6:
                    tagView.showMenu();
                    break;
                case 7:
                    break;
                default:
                    System.out.println("잘못된 값이 입력되었습니다.");
            }


        }
    }

    private void readAllTodolist() {
        try{
            List<TagTodo> tagtodos = todolistService.getAllTodolist();
            if (tagtodos.isEmpty()) {
                System.out.println("📌 조회된 강의가 없습니다.");
            } else {
                System.out.println("\n📌 강의 목록:");
                for (TagTodo tagtodo : tagtodos) {
                    System.out.println(tagtodo);
                }
            }
        }catch (SQLException e){
            System.out.println("t o d o l i s t 조 회 오 류 발 생");
        }
    }

    private void createTodo() {

    }


    private void updateTodo(){

    }

    private void deleteTodo(){

    }


}
