package com.ohgiraffers.todolist.view;

import com.ohgiraffers.todolist.model.Tag;
import com.ohgiraffers.todolist.model.TagTodo;
import com.ohgiraffers.todolist.model.Todolist;
import com.ohgiraffers.todolist.model.User;
import com.ohgiraffers.todolist.service.TagService;
import com.ohgiraffers.todolist.service.TodolistService;
import com.ohgiraffers.todolist.service.UserService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class TodolistView {
    private final Scanner scanner ;
    TodolistService todolistService;
    TagService tagService;
    private final TagView tagView;

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
    public TodolistView(Connection con) {
        todolistService = new TodolistService(con);
        tagService = new TagService(con);
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
            System.out.println("5.태그 페이지로 이동\n");
            System.out.println("7.메인페이지로\n:");
            System.out.println("8.완료 체크하기\n:");


            int choice = getIntInput();
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
                    getAllTodo();
                    break;
                case 5:
                    tagView.showMenu();
                    break;
                case 7:
                    return;
                case 8:
                    checkCompletionTodo();
                    break;
                default:
                    System.out.println("잘못된 케이스입니다.");
            }


        }
    }


    private void createTodo() {
        System.out.print("등록할 Todo를 입력하세요: ");
        String todo = scanner.nextLine();
        Todolist todolist = new Todolist(todo);

        try {
            boolean success = todolistService.addTodo(todolist);
            if (success) {
                System.out.println("Todolist 등록 성공하였습니다.");
            } else {
                System.out.println("Todolist 등록에 실패하였습니다.");
            }
        } catch (SQLException e) {
            System.out.println("Todolist 등록 중 오류가 발생했습니다.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }


    private void updateTodo(){
        System.out.print("수정할 Todo ID를 입력하세요: ");
        int todoId = getIntInput();

        System.out.print("새로운 Todo: ");
        String todo = scanner.nextLine();
        Todolist todolist = new Todolist(todo,todoId);


        try {
            boolean success = todolistService.updateTodo(todolist);
            if (success) {
                System.out.println("Todolist 수정 성공하였습니다.");
            } else {
                System.out.println("Todolist 수정에 실패하였습니다.");
            }
        } catch (SQLException e) {
            System.out.println("Todolist 수정 중 오류가 발생했습니다.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void deleteTodo(){
        System.out.print("삭제할 Todo ID를 입력하세요: ");
        int todoId = getIntInput();

        try {
            boolean success = todolistService.deleteTodo(todoId);
            if (success) {
                System.out.println("Todo가 성공적으로 삭제되었습니다.");
            } else {
                System.out.println("Todo 삭제에 실패하였습니다.");
            }
        } catch (SQLException e) {
            System.out.println("Todo 삭제 중 오류가 발생했습니다.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }


    private void getAllTodo() {

        try{
            List<TagTodo> tagtodos = todolistService.getAllTodolist();
            int prevId = 0;
            if (tagtodos.isEmpty()) {
                System.out.println("📌 조회된 Todolist가 없습니다..");
            } else {
                System.out.println("\n📌 Todolist 목록:");
//                사이즈 하나일경우
                if(tagtodos.size()==1){
                    System.out.println(tagtodos.get(0).toString());
                }
                for (int i = 1; i<tagtodos.size(); i++ ) {
                    if(prevId != tagtodos.get(i).getTodoId()){
                        //현재꺼 이전꺼랑 다르면 이전꺼출력
                        System.out.println(tagtodos.get(i-1).toString());
//                        마지막 회차처리
                        if(i==tagtodos.size()-1){
                            System.out.println(tagtodos.get(i).toString());
                        }
                    }else{
                        // 이전꺼랑 현재 todo id같으면 **이전꺼 태그 이름, 아이디 ***현재 인덱스 리스트에 추가!
                        tagtodos.get(i).addTagIds(tagtodos.get(i-1).getTagId());
                        tagtodos.get(i).addTagNames(tagtodos.get(i-1).getTagName());
//                       * 마지막회차 에서 이전꺼랑 태그id가같으면 마지막께 합쳐지므로 그러기전에 출력.
                        if(i==tagtodos.size()-1){
                            System.out.println(tagtodos.get(i).toString());
                        }
                    }
                    prevId = tagtodos.get(i).getTodoId();
                }
            }
        }catch (SQLException e){
            System.out.println("todolist 조회오류발생");
        }
    }

    private void checkCompletionTodo(){
        System.out.print("완료여부를 체크할 id를 입력해주세요");
        int todoId = getIntInput();

        try {
            boolean success = todolistService.updateCompletionTodo(todoId);
            if (success) {
                System.out.println("todolist 체크 완료!");
            } else {
                System.out.println("완료여부 체크 실패하였습니다.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }







}
