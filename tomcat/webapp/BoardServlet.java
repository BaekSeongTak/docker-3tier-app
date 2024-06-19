package com.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@WebServlet("/api/board")
public class BoardServlet extends HttpServlet {

    private static final String DB_URL = "jdbc:mysql://mysql:3306/webapp_db";
    private static final String DB_USER = "webapp_user";
    private static final String DB_PASSWORD = "webapp_password";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String gender = request.getParameter("gender");
        String event = request.getParameter("event");
        String consent = request.getParameter("consent");

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "INSERT INTO board (username, phone, email, gender, event, consent) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, phone);
            statement.setString(3, email);
            statement.setString(4, gender);
            statement.setString(5, event);
            statement.setString(6, consent);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("/");
    }
}

