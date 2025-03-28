package com.ohgiraffers.todolist.view;

import com.ohgiraffers.todolist.model.Tag;
import com.ohgiraffers.todolist.model.Todolist;
import com.ohgiraffers.todolist.service.TagService;
import com.ohgiraffers.todolist.service.TodolistService;

import java.sql.Connection;
import java.util.Scanner;

public class TagView {
    private Scanner scanner ;
    private String inputValue;
    TagService tagService;
    Tag tag;
    public TagView(Connection con) {
        tagService = new TagService(con);
        scanner = new Scanner(System.in);
    }

    public void showMenu() {
        while (true) {
            System.out.println("Tag 페이지입니다 원하시는 기능을 입력해주세요!!");
            System.out.println("1.생성\n");
            System.out.println("2.수정\n");
            System.out.println("3.삭제\n");
            System.out.println("4.태그 전체 조회\n");
            System.out.println("5.Todolist 페이지로");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    createTag();
                    break;
                case 2:
                    updateTag();
                    break;
                case 3:
                    deleteTag();
                    break;
                case 4:
                    readAllTag();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("잘못된 값이 입력되었습니다.");
            }


        }
    }
}
