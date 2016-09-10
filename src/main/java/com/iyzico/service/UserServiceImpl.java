package com.iyzico.service;

import com.iyzico.dao.UserDao;
import com.iyzico.dto.LoginDto;
import com.iyzico.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by hikuley on 27/08/16.
 */

@Service
@Qualifier(value = "userService")
public class UserServiceImpl implements UserService {

    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private HttpServletRequest currentRequest;


    @Autowired
    private UserDao userDao;


    @Override
    public List<User> listAll() {
        return userDao.listAll();

    }

    @Override
    public User create(User user) {
        User savedUser = userDao.create(user);
        return savedUser;
    }

    @Override
    public User findById(Long id) {
        return userDao.findById(id);
    }

    @Override
    public void delete(Long id) {
        userDao.delete(id);
    }

    @Override
    public User update(User user) {
        return userDao.update(user);
    }

    @Override
    public User login(LoginDto loginDto) {
        User user = userDao.hasUser(loginDto.getUsername(), loginDto.getPassword());
        if (user == null)
            return null;
        log.debug("User login start {}", user);

        HttpSession session = currentRequest.getSession();
        session.setAttribute("user", user);
        return user;
    }

    @Override
    public boolean logout() {
        log.debug("User logut start ");

        HttpSession session = currentRequest.getSession(false);
        if (session != null) {
            session.invalidate();
        } else
            return false;
        return true;
    }

    @Override
    public boolean hasLogin() {
        log.debug("User has login.");

        HttpSession session = currentRequest.getSession(false);
        try {
            User user = (User) session.getAttribute("user");
            if (user != null)
                return true;
            else
                return false;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public User getLoginUser() {
        log.debug(" user get login.");

        HttpSession session = currentRequest.getSession(false);
        try {
            User user = (User) session.getAttribute("user");
            if (user != null)
                return user;
            else
                return null;
        } catch (Exception e) {
            return null;
        }
    }

}
