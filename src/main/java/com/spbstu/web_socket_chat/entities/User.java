package com.spbstu.web_socket_chat.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    public static final ArrayList<User> CONNECTED_USERS = new ArrayList<>();
    private String id;
    private String name;
}
