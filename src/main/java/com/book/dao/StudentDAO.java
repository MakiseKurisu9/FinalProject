package com.book.dao;

import com.book.entity.Student;
import com.book.utils.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    public List<Student> getStudent() {
        List<Student> studentList = new ArrayList<>();
        String sql = "SELECT * FROM student";

        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Student student = new Student();
                student.setSid(rs.getInt("sid"));
                student.setName(rs.getString("name"));
                student.setGrade(rs.getInt("grade"));
                student.setSex(rs.getString("sex"));
                studentList.add(student);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return studentList;
    }
}
