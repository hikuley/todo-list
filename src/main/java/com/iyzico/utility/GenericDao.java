package com.iyzico.utility;

import com.iyzico.entity.BaseEntity;

import java.util.List;

/**
 * Created by hikuley on 28/08/16.
 */

public interface GenericDao<T extends BaseEntity> {

    T create(T t);

    void delete(Long id, Class aClass);

    void delete(Long id);

    T update(T t);

    T findById(Long id, Class aClass);

    T findById(Long id);

    List<T> listWithPagination(Integer start, Integer limit, Class clazz);

    List<T> listAll(Class clazz);

    List<T> listWithPagination(Integer start, Integer limit);

    List<T> listAll();

    Long recordSize();

    Long recordSize(Class aClass);

}
