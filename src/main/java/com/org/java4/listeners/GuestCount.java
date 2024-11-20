package com.org.java4.listeners;

import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class GuestCount implements ServletContextListener, HttpSessionListener {
    private static int count = 0;
    private static final String COUNT_FILE = "count.txt";

    @Override
    public void contextInitialized(jakarta.servlet.ServletContextEvent sce) {
        try {
            java.io.File file = new java.io.File(sce.getServletContext().getRealPath(COUNT_FILE));
            if (file.exists()) {
                java.util.Scanner scanner = new java.util.Scanner(file);
                if (scanner.hasNextInt()) {
                    count = scanner.nextInt();
                }
                scanner.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        count++;
        se.getSession().setAttribute("guestCount", count);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        try {
            java.io.FileWriter fw = new java.io.FileWriter(se.getSession().getServletContext().getRealPath(COUNT_FILE));
            fw.write(String.valueOf(count));
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
