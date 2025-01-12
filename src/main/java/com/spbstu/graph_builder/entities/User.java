package com.spbstu.graph_builder.entities;

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
}
