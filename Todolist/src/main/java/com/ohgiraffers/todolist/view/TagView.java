package com.ohgiraffers.todolist.view;

import com.ohgiraffers.todolist.model.Tag;
import com.ohgiraffers.todolist.model.TagTodo;
import com.ohgiraffers.todolist.model.Todolist;
import com.ohgiraffers.todolist.service.TagService;
import com.ohgiraffers.todolist.service.TodolistService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
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

    private void createTag() {

        System.out.print("태그이름: ");
        String tagName = scanner.nextLine();


        Tag tag = new Tag(tagName);
        try {
            boolean success = tagService.createTag(tag);
            if (success) {
                System.out.println("사용자가 성공적으로 등록되었습니다.");
            } else {
                System.out.println("사용자 등록에 실패하였습니다.");
            }
        } catch (SQLException e) {
            System.out.println("사용자 등록 중 오류가 발생했습니다.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

    }

    private void updateTag() {
        System.out.print("태그id: ");
        int tagId = scanner.nextInt();

        System.out.print("태그이름: ");
        String tagName = scanner.nextLine();
        Tag tag = new Tag(tagId,tagName);
        try {
            boolean success = tagService.updateTag(tag);
            if (success) {
                System.out.println("사용자가 성공적으로 등록되었습니다.");
            } else {
                System.out.println("사용자 등록에 실패하였습니다.");
            }
        } catch (SQLException e) {
            System.out.println("사용자 등록 중 오류가 발생했습니다.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
    private void deleteTag() {
        System.out.print("삭제할 태그 ID를 입력하세요: ");
        int tagId = scanner.nextInt();
        scanner.nextLine(); // 개행 문자 처리
        try {
            boolean success = tagService.deleteTag(tagId);
            if (success) {
                System.out.println("사용자가 성공적으로 삭제되었습니다.");
            } else {
                System.out.println("사용자 삭제에 실패하였습니다.");
            }
        } catch (SQLException e) {
            System.out.println("사용자 삭제 중 오류가 발생했습니다.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
    private void readAllTag() {
        try{
            List<TagTodo> tags = tagService.getAllTag();
            if (tags.isEmpty()) {
                System.out.println("📌 조회된 Todolist가 없습니다..");
            } else {
                System.out.println("\n📌 Todolist 목록:");
                for (TagTodo tag : tags) {
                    System.out.println(tags);
                }
            }
        }catch (SQLException e){
            System.out.println("t o d o l i s t 조 회 오 류 발 생");
        }
    }
}
