package com.org.java4;

import com.org.java4.entities.UserManager;
import com.org.java4.model.User;

import java.util.List;

public class TestJPA {
    public static void main(String[] args) {
        UserManager userManager = new UserManager();
//        userManager.findAll();
//        userManager.create();
//        userManager.update();
//        userManager.findById("U01");
//        userManager.deleteById();
//        userManager.findWhere();
        List<User> users = userManager.findWithPage(0, 3);
        if (users != null) {
            for (User user : users) {
                System.out.println(user.getFullname() + " - " + user.getEmail());
            }
        } else {
            System.out.println("Không có user nào được tìm thấy.");
        }
    }
}
