package com.ohgiraffers.todolist.view;

import com.ohgiraffers.todolist.model.Tag;
import com.ohgiraffers.todolist.model.TagTodo;
import com.ohgiraffers.todolist.model.Todolist;
import com.ohgiraffers.todolist.service.TagService;
import com.ohgiraffers.todolist.service.TagTodoService;
import com.ohgiraffers.todolist.service.TodolistService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class TagView {
    private Scanner scanner ;
    private String inputValue;
    TagService tagService;
    TagTodoService tagTodoService;
    Tag tag;

    public TagView(Connection con,int userId) {
        tagService = new TagService(con,userId);
        tagTodoService = new TagTodoService(con);
        scanner = new Scanner(System.in);
    }


    /** 정수만을 받게합니다. (예외처리)
     * @return Integer.parseInt(scanner.nextLine());
     * */
    private int getIntInput() {
        String input = scanner.nextLine();
        int result = 0;
        while (true) {
            try {
                result = Integer.parseInt(input); // nextLine()으로 입력 받고 변환
                return result;
            } catch (NumberFormatException e) {
                System.out.println("⚠ 올바른 숫자를 입력하세요.");
            }
        }
    }

    public void showMenu() {
        while (true) {
            System.out.println("Tag 페이지입니다 원하시는 기능을 입력해주세요!!");
            System.out.println("1.생성\n");
            System.out.println("2.수정\n");
            System.out.println("3.삭제\n");
            System.out.println("4.태그 전체 조회\n");
            System.out.println("5.Todolist 페이지로\n");
            System.out.println("6.태그 부여\n");
            System.out.println("7.특정 태그 조회\n");

            int choice = getIntInput();

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
                    return;
                case 6:
                    giveTagTodo();
                    break;
                case 7:
                    getTodoByTagId();
                    break;
                default:
                    System.out.println("잘못된 값이 입력되었습니다.");
            }


        }
    }

    private void giveTagTodo() {
        System.out.println("부여할 태그ID");
        int tagId = getIntInput();
        System.out.println("부여할 todoID");
        int todoId = getIntInput();
        try {
            boolean success = tagTodoService.createTagTodo(tagId,todoId);
            if (success) {
                System.out.println("태그가 성공적으로 부여되었습니다.");
            } else {
                System.out.println("태그 부여에 실패하였습니다.");
            }
        } catch (SQLException e) {
            System.out.println("태그 부여 중 오류가 발생했습니다.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void createTag() {
        System.out.print("태그이름: ");
        String tagName = scanner.nextLine();
        Tag tag = new Tag(tagName);
        try {
            boolean success = tagService.createTag(tag);
            if (success) {
                System.out.println("태그가 성공적으로 등록되었습니다.");
            } else {
                System.out.println("태그 등록에 실패하였습니다.");
            }
        } catch (SQLException e) {
            System.out.println("태그 등록 중 오류가 발생했습니다.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

    }

    private void updateTag() {
        System.out.print("수정할 태그 id: ");
        int tagId = getIntInput();
        System.out.print("태그이름: ");
        String tagName = scanner.nextLine();

        Tag tag = new Tag(tagId,tagName);
        try {
            boolean success = tagService.updateTag(tag);
            if (success) {
                System.out.println("태그가 성공적으로 수정되었습니다.");
            } else {
                System.out.println("태그 업데이트에 실패하였습니다.");
            }
        } catch (SQLException e) {
            System.out.println("태그 등록 중 오류가 발생했습니다.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
    private void deleteTag() {
        System.out.print("삭제할 태그 ID를 입력하세요: ");
        int tagId = getIntInput();
        
        try {
            boolean success = tagService.deleteTag(tagId);
            if (success) {
                System.out.println("태그가 성공적으로 삭제되었습니다.");
            } else {
                System.out.println("태그 삭제에 실패하였습니다.");
            }
        } catch (SQLException e) {
            System.out.println("태그 삭제 중 오류가 발생했습니다.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
    private void readAllTag() {
        try{
            List<Tag> tags = tagService.getAllTag();
            if (tags.isEmpty()) {
                System.out.println("📌 조회된 태그가 없습니다..");
            } else {
                System.out.println("\n📌 태그 목록:");
                for (Tag tag : tags) {
                    System.out.println(tag);
                }
            }
        }catch (SQLException e){
            System.out.println("todolist조회오류발생");
        }
    }
    private void getTodoByTagId() {
        try{
            System.out.print("검색할 태그 ID를 입력하세요: ");
            int tagId = getIntInput();

            List<TagTodo> tagtodos = tagService.getTodoByTagId(tagId);
            if (tagtodos.isEmpty()) {
                System.out.println("📌 조회된 Todolist가 없습니다..");
            } else {
                System.out.println("\n📌 Todolist 목록:");
                for (TagTodo tagtodo : tagtodos) {
                    System.out.println(tagtodo);
                }
            }
        }catch (SQLException e){
            System.out.println("todolist조회오류발생");
        }
    }
}
