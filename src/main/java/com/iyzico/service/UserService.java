package com.iyzico.service;

import com.iyzico.dto.LoginDto;
import com.iyzico.entity.User;

import java.util.List;

/**
 * Created by hikuley on 27/08/16.
 */


public interface UserService {

    User create(User user);

    List<User> listAll();

    User findById(Long id);

    void delete(Long id);

    User update(User user);

    User login(LoginDto loginDto);

    boolean logout();

    boolean hasLogin();

    User getLoginUser();
}
