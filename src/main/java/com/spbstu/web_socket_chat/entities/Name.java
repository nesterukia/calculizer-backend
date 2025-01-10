package com.spbstu.web_socket_chat.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Name {
    String name;

    @Override
    public String toString() {
        return name;
    }
}
