package com.ohgiraffers.todolist.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueryUtilTest {

    @Test
    void getQuery() {
        String query = QueryUtil.getQuery("getAllUser"); // XML에서 쿼리 로드
        Assertions.assertEquals("SELECT u.nickname AS \"nickname\", u.email AS \"email\"\n" +
                "        FROM user u", query);
    }
}