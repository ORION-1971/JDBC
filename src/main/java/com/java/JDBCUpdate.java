package com.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

/// Обновить оценку у студента указанного в консоли

public class JDBCUpdate {

    static final String DB_URL = "jdbc:mysql://localhost:3306/test_db";
    static final String USER = "root";
    static final String PASS = "root";

    public static void main(String[] args) {

        Connection conn = null;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the surname of your student"); // Вводим имя студента кому хотим поменять оценку
        String surname = scanner.nextLine();
        System.out.println("Enter the grade (5,5)"); // Вводим оценку
        double avgGrade = scanner.nextDouble();

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            PreparedStatement statement = conn.prepareStatement(
                    "UPDATE students SET avg_grade = ? WHERE surname = ?");

            statement.setDouble(1, avgGrade);
            statement.setString(2, surname);

            statement.executeUpdate();

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
