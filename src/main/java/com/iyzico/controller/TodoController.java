package com.iyzico.controller;

import com.iyzico.dto.Response;
import com.iyzico.dto.TodoDto;
import com.iyzico.dto.TodoStatusDto;
import com.iyzico.entity.Todo;
import com.iyzico.service.TodoService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by hikuley on 28/08/16.
 */

@RestController
@RequestMapping(value = "/todo")
public class TodoController {

    private final Logger log = LoggerFactory.getLogger(TodoController.class);

    @Autowired
    protected TodoService todoService;

    @Autowired
    protected ModelMapper modelMapper;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    private Response create(@RequestBody @Valid TodoDto todoDto, BindingResult bindingResult) {
        log.debug("REST request to create todo : {}", todoDto);

        Response response = new Response();
        if (!bindingResult.hasErrors()) {
            Todo todo = modelMapper.map(todoDto, Todo.class);
            Todo savedTodo = todoService.create(todo);
            response.setData(savedTodo);
            response.setStatus(HttpStatus.OK);
            response.setMessage("Successfully created todo");
            return response;
        } else {
            response.setFieldErrors(bindingResult.getFieldErrors());
            response.setStatus(HttpStatus.BAD_REQUEST);
            response.setMessage("Can not created todo");
            return response;
        }
    }

    @RequestMapping(value = "/updateStatus", method = RequestMethod.POST)
    public Response updateStatus(@RequestBody TodoStatusDto todoStatusDto) {
        log.debug("REST request tos change status  : {}", todoStatusDto);

        todoService.todoStatusUpdate(todoStatusDto);
        Response response = new Response();
        response.setStatus(HttpStatus.OK);
        response.setMessage("Updated successfully");
        return response;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public Response deleteTodo(@PathVariable(value = "id") Long id) {
        log.debug("REST request to delete todo  id:", id);

        todoService.delete(id);
        Response response = new Response();
        response.setStatus(HttpStatus.OK);
        response.setMessage("Deleted successfully");
        return response;
    }


    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    private Response update(@RequestBody @Valid TodoDto todoDto, BindingResult bindingResult) {
        log.debug("REST request to update todo : {}", todoDto);

        Response response = new Response();
        if (!bindingResult.hasErrors()) {
            Todo todo = modelMapper.map(todoDto, Todo.class);
            Todo savedTodo = todoService.update(todo);
            response.setData(savedTodo);
            response.setStatus(HttpStatus.OK);
            response.setMessage("Successfully update todo");
            return response;
        } else {
            response.setFieldErrors(bindingResult.getFieldErrors());
            response.setStatus(HttpStatus.BAD_REQUEST);
            response.setMessage("Can not update todo");
            return response;
        }
    }

    @RequestMapping(value = "/read", method = RequestMethod.GET)
    public Iterable<Todo> readTodo(@PathVariable(value = "id") Long id) {
        log.debug("REST request to read one todo by id: {}", id);

        Response response = new Response();
        if (id != null) {
            Todo todo = todoService.findById(id);
            TodoDto todoDto = modelMapper.map(todo, TodoDto.class);

            response.setStatus(HttpStatus.OK);
            response.setData(todoDto);
            response.setMessage("Read todo");

        } else {
            response.setStatus(HttpStatus.BAD_REQUEST);
            response.setMessage("Require Todo ID");
        }
        return todoService.listAll();
    }


}
