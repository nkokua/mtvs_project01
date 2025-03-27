package com.ohgiraffers.todolist.view;

import java.sql.Connection;
import java.util.Scanner;

public abstract class View {
    protected Connection con;
    public View(Connection con) {
        this.con = con;
    }
    public abstract boolean run();
}
