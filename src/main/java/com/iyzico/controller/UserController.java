package com.iyzico.controller;

import com.iyzico.dto.LoginDto;
import com.iyzico.dto.Response;
import com.iyzico.dto.UserDto;
import com.iyzico.entity.User;
import com.iyzico.service.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by hikuley on 27/08/16.
 */

@RestController
@RequestMapping(value = "/user")
public class UserController {


    private final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    protected ModelMapper modelMapper;

    @Autowired
    protected UserService userService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Response signup(@RequestBody @Valid UserDto userDto, BindingResult bindingResult) {
        log.debug("REST request to save User : {}", userDto);

        Response response = new Response();
        if (!bindingResult.hasErrors()) {
            User user = modelMapper.map(userDto, User.class);
            User savedUser = userService.create(user);
            response.setData(savedUser);
            response.setStatus(HttpStatus.OK);
            response.setMessage("Successfully register");
            return response;
        } else {
            response.setFieldErrors(bindingResult.getFieldErrors());
            response.setStatus(HttpStatus.BAD_REQUEST);
            return response;
        }
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Response login(@RequestBody @Valid LoginDto loginDto, BindingResult bindingResult) {
        log.debug("REST request to login User : {}", loginDto);

        Response response = new Response();
        if (!bindingResult.hasErrors()) {
            User singnUser = userService.login(loginDto);
            if (singnUser != null) {
                response.setStatus(HttpStatus.OK);
                response.setData(singnUser);
                response.setMessage("Successfully login");
            } else {
                response.setStatus(HttpStatus.FOUND);
                response.setMessage("Failure login, not found user and password");
            }
        } else {
            response.setFieldErrors(bindingResult.getFieldErrors());
            response.setStatus(HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public Response logout() {
        log.debug("REST request to login User");

        Response response = new Response();
        if (userService.logout()) {
            response.setStatus(HttpStatus.OK);
            response.setMessage("successfully logout.");
        } else {
            response.setStatus(HttpStatus.FOUND);
            response.setMessage("Çıkış başarısız");
        }
        return response;
    }

    @RequestMapping(value = "/hasLogin", method = RequestMethod.GET)
    public Response hasLogin() {
        log.debug("REST request to has session");

        Response response = new Response();
        response.setStatus(HttpStatus.OK);
        if (userService.hasLogin())
            response.setMessage("Session ok and available");
        else
            response.setMessage("not found session");
        return response;
    }
}