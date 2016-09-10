package com.iyzico.dao;

import com.iyzico.entity.Todo;
import com.iyzico.entity.User;
import com.iyzico.entity.enums.TodoStatus;
import com.iyzico.utility.GenericDao;

import java.util.List;

/**
 * Created by hikuley on 27/08/16.
 */

public interface TodoDao extends GenericDao<Todo> {

    List<Todo> getListByUser(User user, TodoStatus todoStatus);
}
