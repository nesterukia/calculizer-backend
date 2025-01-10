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
public class Message {
    public static int MESSAGE_COUNTER = 0;
    private String sender;
    private String recipient;
    private String content;
}
