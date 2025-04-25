package com.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

/// Удаление студента

public class JDBCDelete {

    static final String DB_URL = "jdbc:mysql://localhost:3306/test_db";
    static final String USER = "root";
    static final String PASS = "root";

    public static void main(String[] args) {

        Connection conn = null;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the surname of your student"); // Вводим имя студента которого надо удалить
        String surname = scanner.nextLine();

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            PreparedStatement statement = conn.prepareStatement(
                    "DELETE FROM students WHERE surname = ?");

            statement.setString(1, surname);

            int delete = statement.executeUpdate();
            System.out.println(delete + " студент удален");
            statement.close();

        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
