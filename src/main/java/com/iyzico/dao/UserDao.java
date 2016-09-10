package com.iyzico.dao;

import com.iyzico.entity.User;
import com.iyzico.utility.GenericDao;

/**
 * Created by hikuley on 27/08/16.
 */

public interface UserDao extends GenericDao<User> {

    User hasUser(String username, String password);


}
