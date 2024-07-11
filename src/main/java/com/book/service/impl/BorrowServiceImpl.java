package com.book.service.impl;

import com.book.dao.BookDAO;
import com.book.dao.StudentDAO;
import com.book.entity.Book;
import com.book.entity.Borrow;
import com.book.entity.Student;
import com.book.service.BorrowService;

import java.util.*;
import java.util.stream.Collectors;

public class BorrowServiceImpl implements BorrowService {
    private BookDAO bookDAO = new BookDAO();
    private StudentDAO studentDAO = new StudentDAO();

    //返回借出去的书的信息
    @Override
    public List<Borrow> getBorrowList() {
        return bookDAO.getBorrowList();
    }

    //还书
    @Override
    public void returnBorrow(String id) {
        bookDAO.deleteBorrow(id);
    }

    //返回可以借出去的书
    @Override
    public List<Book> getActiveBookList() {
        Set<Integer> borrowedBookIds = this.getBorrowList().stream()
                .map(Borrow::getBook_id)
                .collect(Collectors.toSet());

        return bookDAO.getBookList().stream()
                .filter(book -> !borrowedBookIds.contains(book.getBid()))
                .collect(Collectors.toList());
    }

    //返回学生信息
    @Override
    public List<Student> getStudentList() {
        return studentDAO.getStudent();
    }

    //添加借阅信息
    @Override
    public void addBorrow(int sid, int bid) {
        bookDAO.insertBorrow(sid, bid);
    }

    //返回所有书籍的信息，包含借阅与否
    @Override
    public Map<Book, Boolean> getAllBook() {
        Set<Integer> borrowedBookIds = this.getBorrowList().stream()
                .map(Borrow::getBook_id)
                .collect(Collectors.toSet());

        Map<Book, Boolean> bookMap = new LinkedHashMap<>();
        bookDAO.getBookList().forEach(book ->
                bookMap.put(book, borrowedBookIds.contains(book.getBid())));

        return bookMap;
    }

    @Override
    public void deleteBook(int bid) {
        bookDAO.deleteBook(bid);
    }

    @Override
    public void InsertBook(Book book) {
        bookDAO.insertBook(book.getTitle(), book.getDesc(), book.getPrice());
    }
}
