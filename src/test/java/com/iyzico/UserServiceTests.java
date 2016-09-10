package com.iyzico;

import com.iyzico.entity.User;
import com.iyzico.service.UserService;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationMain.class)
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserServiceTests {


    @Autowired
    UserService userService;


    User testUser;
    static Long testId;


    @BeforeClass
    public static void userServiceInitial() {
        // Before Test initializing

    }

    @Before
    public void userServiceBefore() {
        testUser = new User();
        testUser.setName("Halil İbrahim");
        testUser.setSurname("Küley");
        testUser.setCellPhone("05419485599");
        testUser.setEmail("halil.ibrahimkuley@gmail.com");
        testUser.setUsername("hikuley");
        testUser.setPassword("1234225");
    }

    @Test
    public void test1_create() {
        User savedUser = userService.create(testUser);
        Assert.assertNotNull("Could not record", savedUser);
        testId = savedUser.getId();
    }

    @Test
    public void test2_listAll() {
        Iterable<User> users = userService.listAll();
        Assert.assertNotNull("Not found user list", users);
    }


    @Test
    public void test3_findById() {
        User user = userService.findById(testId);
        Assert.assertNotNull("Not found user", user);
    }

    @Test
    public void test4_update() {

        User savedUser = userService.findById(testId);
        savedUser.setEmail("test@test.com"); // set field

        User user = userService.update(savedUser);
        Assert.assertNotNull("Not update user", user);
    }

    @Test
    public void test5_delete() {
        userService.delete(testId);
    }

}
