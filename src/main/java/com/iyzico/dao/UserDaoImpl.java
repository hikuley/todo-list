package com.iyzico.dao;

import com.iyzico.entity.User;
import com.iyzico.utility.GenericDaoImpl;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by hikuley on 27/08/16.
 */

@Repository
@Transactional
@Qualifier(value = "userDado")
public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao {

    @Override
    public User hasUser(String username, String password) {
        Criteria criteria = this.getSession().createCriteria(User.class);
        criteria.add(Restrictions.eq("username", username));
        criteria.add(Restrictions.eq("password", password));
        return (User) criteria.uniqueResult();
    }
}
