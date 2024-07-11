package com.book.service.impl;

import com.book.dao.UserDAO;
import com.book.entity.User;
import jakarta.servlet.http.HttpSession;

public class UserServiceImpl implements com.book.service.UserService {
    private UserDAO userDAO = new UserDAO();

    @Override
    public boolean auth(String username, String password, HttpSession session) {
        User user = userDAO.getUser(username, password);
        if (user == null) return false;
        session.setAttribute("user", user);
        return true;
    }
}
