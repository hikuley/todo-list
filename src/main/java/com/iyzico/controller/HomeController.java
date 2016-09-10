package com.iyzico.controller;

import com.iyzico.entity.Todo;
import com.iyzico.entity.User;
import com.iyzico.entity.enums.TodoStatus;
import com.iyzico.service.TodoService;
import com.iyzico.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by hikuley on 27/08/16.
 */

@Controller
public class HomeController {

    @Autowired
    UserService userService;

    @Autowired
    TodoService todoService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        if (userService.hasLogin()) {
            List<Todo> listTodo = todoService.getListTodoByUser(TodoStatus.TODO);
            List<Todo> listInprogress = todoService.getListTodoByUser(TodoStatus.INPROGRESS);
            List<Todo> listTest = todoService.getListTodoByUser(TodoStatus.TEST);
            List<Todo> listDone = todoService.getListTodoByUser(TodoStatus.DONE);
            model.addAttribute("todoList", listTodo);
            model.addAttribute("inprogressList", listInprogress);
            model.addAttribute("testList", listTest);
            model.addAttribute("doneList", listDone);
            return "index";
        } else {
            return "redirect:" + "/login";
        }
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("siteTitle", "Todo List");
        // handle request
        return "login";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String profile(Model model) {
        User loginUser = userService.getLoginUser();
        model.addAttribute("user", loginUser);
        // handle request
        return "profile";
    }


}
