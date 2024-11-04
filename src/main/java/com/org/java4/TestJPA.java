package com.org.java4;

import com.org.java4.entities.UserManager;

public class TestJPA {
    public static void main(String[] args) {
        UserManager userManager = new UserManager();
        userManager.findAll();
    }
}
