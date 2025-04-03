package com.ohgiraffers.todolist.dao;

import com.ohgiraffers.todolist.model.TagTodo;
import com.ohgiraffers.todolist.model.Todolist;

import com.ohgiraffers.todolist.util.QueryUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TodolistDao extends Dao{

    private final int userId;
    public TodolistDao(Connection connection,int userId) {
        super(connection);
        this.userId = userId;
    }
    public boolean addTodolist(Todolist todo, String xmlqry) {
        String query = QueryUtil.getQuery(xmlqry); // XML에서 쿼리 로드

        try (PreparedStatement ptmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ptmt.setString(1,todo.getTodo());
            ptmt.setInt(2,userId);
            ptmt.setString(3,"N");
            int affectedRows = ptmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
//    todolist에서 todo와 iscompleted 불러옴
public Todolist getTodoById(int todoId) throws SQLException {
        String query = QueryUtil.getQuery("getTodoById");
        Todolist todolist = null;
        try (PreparedStatement ptmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ptmt.setInt(1, todoId);
            try (ResultSet rs = ptmt.executeQuery()) {
                if (rs.next()) {
    //                        todo_id AS "TodoID",
    //                                tl.todo AS "Todo",
    //                                tl.iscompleted AS "완료 여부"
                    todolist = new Todolist(
                            rs.getString("Todo"),
                            rs.getString("완료 여부").charAt(0)
                    );
                }
            }
        }
    return todolist;
}
    public boolean deleteTodo(int todoId) throws SQLException {
        String query = QueryUtil.getQuery("deleteTodo");
        try (PreparedStatement pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, todoId);
            return pstmt.executeUpdate() > 0; // 삭제된 행이 있으면 true 반환
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public boolean deleteTagtodo(int todoId) {
        String query = QueryUtil.getQuery("deleteTagtodoByTodo");
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, todoId);
            int affectedRows = pstmt.executeUpdate(); // 삭제된 행의 수를 반환
            // 삭제 성공시 affectedRows > 0, 존재하지 않는 경우 0 반환
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


//투두리스트 업데이트 todo내용을.
    public boolean updateTodo(Todolist todo) {
        String query = QueryUtil.getQuery("updateTodo"); // XML에서 쿼리 로드
        try (PreparedStatement ptmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ptmt.setString(1,todo.getTodo());
            ptmt.setInt(2,todo.getTodo_id());
            int affectedRows = ptmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
// Y->N (완료일 해제) N->Y (완료일 생성)
    public boolean updateCompletionTodo(int todoId, char isCompleted) {
        String query = QueryUtil.getQuery("updateCompletionTodo"); // XML에서 쿼리 로드
        try (PreparedStatement ptmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ptmt.setString(1, String.valueOf(isCompleted));
            ptmt.setInt(2,todoId);
            int affectedRows = ptmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean updateCompletionDate(int todoId){
        String query = QueryUtil.getQuery("updateCompletionDate"); // XML에서 쿼리 로드
        try (PreparedStatement ptmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ptmt.setInt(1,todoId);
            int affectedRows = ptmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean deleteCompletionDate(int todoId){
        String query = QueryUtil.getQuery("nullCompletionDate");
        try (PreparedStatement ptmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ptmt.setInt(1,todoId);
            int affectedRows = ptmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
//투두리스트태그붙여서모든것을 조회하는 함수
    public List<TagTodo> getAllTodolist(String xmlqry) {
        String query = QueryUtil.getQuery(xmlqry); // XML에서 쿼리 로드
        List<TagTodo> tagTodos = new ArrayList<>();
        try (PreparedStatement ptmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ptmt.setInt(1,userId);
            try(ResultSet rs = ptmt.executeQuery()) {
                    while (rs.next()) {
//                                      todo_id AS "TodoID",
//                                tl.todo AS "Todo",
//                                t.tag_name AS "태그명",
//                                tl.creation_date AS "생성일",
//                                tl.completion_date "완료일",
//                                tl.iscompleted AS "완료 여부"
                                tagTodos.add(new TagTodo(
                                        rs.getString("TagId"),
                                rs.getInt("TodoId"),
                                rs.getString("Todo"),
                                rs.getString("태그명"),
                                rs.getDate("생성일"),
                                rs.getDate("완료일"),
                                rs.getString("완료 여부").charAt(0)
                                ));
                    }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tagTodos;
    }

// Todoname존재하는지확인.
    public boolean existsTodo(String todo) {
        String query = QueryUtil.getQuery("existsTodoName");
        try(PreparedStatement ptmt = connection.prepareStatement(query)) {
            ptmt.setString(1,todo);
            ptmt.setInt(2,userId);
            try(ResultSet rs = ptmt.executeQuery()) {
                if (rs.next()) {
//                    * 첫 컬럼 (id)
                    return rs.getInt(1) > 0 ;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    public boolean existsTodoIdByUserId(int todoId){
        String query = QueryUtil.getQuery("existsTodoIdByUserId");

        try(PreparedStatement ptmt = connection.prepareStatement(query)){
            ptmt.setInt(1,userId);
            ptmt.setInt(2,todoId);
            try(ResultSet rs = ptmt.executeQuery()) {
                if (rs.next()) {
//                   id가 존재하는 지 확인.
                    return rs.getInt(1) > 0 ;
                }

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

}
