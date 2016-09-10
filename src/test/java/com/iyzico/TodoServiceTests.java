package com.iyzico;

import com.iyzico.entity.Todo;
import com.iyzico.entity.enums.TodoStatus;
import com.iyzico.service.TodoService;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Calendar;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationMain.class)
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TodoServiceTests {


    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    TodoService todoService;

    Todo testTodo;
    static Long testId;


    @BeforeClass
    public static void userServiceInitial() {
        // Before Test initializing

    }

    @Before
    public void userServiceBefore() {
        testTodo = new Todo();
        testTodo.setName("Arayüz hazırlanması");
        testTodo.setContent("arayüz hazırlanıp içerik girişi yapılacaktır.");
        testTodo.setStatus(TodoStatus.TODO);
        testTodo.setDescription("arayüz açıklaması");
        testTodo.setDeadline(Calendar.getInstance());
        testTodo.setUser(null);
    }

    @Test
    public void test1_create() {
        Todo savedTodo = todoService.create(testTodo);
        Assert.assertNotNull("Could not record", savedTodo);
        testId = savedTodo.getId();
    }


    @Test
    public void test2_listAll() {
        Iterable<Todo> todos = todoService.listAll();
        Assert.assertNotNull("Not found user list", todos);
    }


    @Test
    public void test3_findById() {
        Todo todo = todoService.findById(testId);
        Assert.assertNotNull("Not found user", todo);
    }


    @Test
    public void test4_update() {
        Todo savedTodo = todoService.findById(testId);
        savedTodo.setContent("Test");

        Todo updatedTodo = todoService.update(savedTodo);
        Assert.assertNotNull("Not update todo", updatedTodo);
    }

    @Test
    public void test5_delete() {
        todoService.delete(testId);
    }

}
