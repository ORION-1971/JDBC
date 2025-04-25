package com.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

/// Ввод данных по студенту

public class JDBCInsert {
    static final String DB_URL = "jdbc:mysql://localhost:3306/test_db";
    static final String USER = "root";
    static final String PASS = "root";

    public static void main(String[] args) {

        Connection conn = null;
        Scanner sc = new Scanner(System.in);
        System.out.println("Введи имя студента");
        String name = sc.nextLine();
        System.out.println("Введи фамилию студента");
        String surname = sc.nextLine();
        System.out.println("Средняя оценка (5,5)");  // Вводить через запятую
        double avgGrade = sc.nextDouble();

        Student student = new Student(name, surname, avgGrade);

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            PreparedStatement statement = conn.prepareStatement(
                    "INSERT INTO students(name, surname, avg_grade) VALUES (?,?,?)");

            statement.setString(1, student.getName());
            statement.setString(2, student.getSurname());
            statement.setDouble(3, student.getAvgGrade());

            statement.executeUpdate();         // Изменение данных
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
