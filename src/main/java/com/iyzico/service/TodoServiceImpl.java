package com.iyzico.service;

import com.iyzico.dao.TodoDao;
import com.iyzico.dto.TodoStatusDto;
import com.iyzico.entity.Todo;
import com.iyzico.entity.User;
import com.iyzico.entity.enums.TodoStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hikuley on 28/08/16.
 */


@Service
@Qualifier(value = "todoService")
public class TodoServiceImpl implements TodoService {

    private final Logger log = LoggerFactory.getLogger(TodoServiceImpl.class);

    @Autowired
    private TodoDao todoDao;

    @Autowired
    private UserService userService;

    @Override
    public List<Todo> listAll() {
        log.debug("all list todo");

        List<Todo> todoList = todoDao.listAll();
        return todoList;
    }

    @Override
    public Todo create(Todo todo) {
        log.debug("todo service running. created new todo");

        todo.setStatus(TodoStatus.TODO);
        todo.setUser(userService.getLoginUser());
        return todoDao.create(todo);
    }

    @Override
    public Todo findById(Long id) {
        log.debug("service running. find todo by id");

        return todoDao.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("service running. deleted todo by id");

        todoDao.delete(id);
    }

    @Override
    public Todo update(Todo todo) {
        log.debug("service running. updated todo");

        return todoDao.update(todo);
    }

    @Override
    public List<Todo> getListTodoByUser(TodoStatus todoStatus) {
        log.debug("service running. read todo by status");

        User loginUser = userService.getLoginUser();
        List<Todo> listTodo = todoDao.getListByUser(loginUser, todoStatus);
        return listTodo;
    }

    @Override
    public void todoStatusUpdate(TodoStatusDto todoStatusDto) {
        log.debug("service running. update todo by status and id");

        Todo todo = todoDao.findById(todoStatusDto.getId());
        todo.setStatus(todoStatusDto.getStatus());
        todoDao.update(todo);
    }


}
