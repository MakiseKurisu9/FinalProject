package com.book.dao;

import com.book.entity.Book;
import com.book.entity.Borrow;
import com.book.utils.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {

    public List<Borrow> getBorrowList() {
        List<Borrow> borrowList = new ArrayList<>();
        String sql = "SELECT * FROM borrow, student, book WHERE borrow.bid = book.bid AND borrow.sid = student.sid";

        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Borrow borrow = new Borrow();
                borrow.setId(rs.getString("id"));
                borrow.setBook_id(rs.getInt("bid"));
                borrow.setBook_name(rs.getString("title"));
                borrow.setTime(rs.getTimestamp("time"));
                borrow.setStudent_name(rs.getString("name"));
                borrow.setStudent_id(rs.getInt("sid"));
                borrowList.add(borrow);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return borrowList;
    }

    public void deleteBorrow(String id) {
        String sql = "DELETE FROM borrow WHERE id = ?";

        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Book> getBookList() {
        List<Book> bookList = new ArrayList<>();
        String sql = "SELECT * FROM book";

        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Book book = new Book();
                book.setBid(rs.getInt("bid"));
                book.setTitle(rs.getString("title"));
                book.setDesc(rs.getString("desc"));
                book.setPrice(rs.getDouble("price"));
                bookList.add(book);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookList;
    }

    public void insertBook(String title, String desc, double price) {
        String sql = "INSERT INTO book(title, `desc`, price) VALUES(?, ?, ?)";

        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, title);
            ps.setString(2, desc);
            ps.setDouble(3, price);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertBorrow(int sid, int bid) {
        String sql = "INSERT INTO borrow(sid, bid, time) VALUES(?, ?, NOW())";

        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, sid);
            ps.setInt(2, bid);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteBook(int bid) {
        String sql = "DELETE FROM book WHERE bid = ?";

        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, bid);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
