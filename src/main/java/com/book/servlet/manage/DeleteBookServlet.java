package com.book.servlet.manage;

import com.book.service.BorrowService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/deleteBook")
public class DeleteBookServlet extends HttpServlet {
    BorrowService service;

    @Override
    public void init() throws ServletException {
        service = new com.book.service.impl.BorrowServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int bid =Integer.parseInt(req.getParameter("bid"));
        service.deleteBook(bid);
        resp.sendRedirect("books");
    }
}
