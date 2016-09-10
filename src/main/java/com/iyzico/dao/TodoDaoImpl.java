package com.iyzico.dao;

import com.iyzico.entity.Todo;
import com.iyzico.entity.User;
import com.iyzico.entity.enums.TodoStatus;
import com.iyzico.utility.GenericDaoImpl;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by hikuley on 27/08/16.
 */

@Repository
@Transactional
@Qualifier(value = "todoDao")
public class TodoDaoImpl extends GenericDaoImpl<Todo> implements TodoDao {

    @Override
    public List<Todo> getListByUser(User user, TodoStatus todoStatus) {
        Criteria criteria = this.getSession().createCriteria(Todo.class);
        criteria.add(Restrictions.eq("user", user));
        criteria.add(Restrictions.eq("status", todoStatus));
        return criteria.list();
    }
}
