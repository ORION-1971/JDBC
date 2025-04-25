package com.java;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/// Вывод всех студентов у кого оценка выше указанного в консоли

public class JDBCSelect {
    static final String DB_URL = "jdbc:mysql://localhost:3306/test_db";
    static final String USER = "root";
    static final String PASS = "root";

    public static void main(String[] args) {

        Connection conn = null;
        Scanner sc = new Scanner(System.in);
        System.out.println("Показать студентов у кого оценка выше (5,5)");  // Вводить через запятую
        double avgGrade = sc.nextDouble();

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            PreparedStatement statement = conn.prepareStatement(
                    "SELECT * FROM students WHERE avg_grade > ?");

            statement.setDouble(1, avgGrade);
            ResultSet resultSet = statement.executeQuery();

            List<Student> studentList = new ArrayList<>();
            while (resultSet.next()) {
                Student student = new Student();

                student.setId(resultSet.getLong("id"));
                student.setName(resultSet.getString("name"));
                student.setSurname(resultSet.getString("surname"));
                student.setAvgGrade(resultSet.getDouble("avg_grade"));
                studentList.add(student);
            }
            for (Student student : studentList) {
                System.out.println(student);
            }

            resultSet.close();
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
