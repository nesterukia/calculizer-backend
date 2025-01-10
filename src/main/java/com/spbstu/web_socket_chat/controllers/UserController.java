package com.spbstu.web_socket_chat.controllers;


import com.spbstu.web_socket_chat.entities.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {
    @GetMapping
    public List<User> getAllUsers(){
        return User.CONNECTED_USERS;
    }
}
