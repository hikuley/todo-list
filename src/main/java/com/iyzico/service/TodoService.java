package com.iyzico.service;

import com.iyzico.dto.TodoStatusDto;
import com.iyzico.entity.Todo;
import com.iyzico.entity.enums.TodoStatus;

import java.util.List;

/**
 * Created by hikuley on 28/08/16.
 */
public interface TodoService {

    Todo create(Todo todo);

    List<Todo> listAll();

    Todo findById(Long id);

    void delete(Long id);

    Todo update(Todo t);

    List<Todo> getListTodoByUser(TodoStatus todoStatus);

    void todoStatusUpdate(TodoStatusDto todoStatusDto);

}
