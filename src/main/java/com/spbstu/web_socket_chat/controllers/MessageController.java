package com.spbstu.web_socket_chat.controllers;

import com.spbstu.web_socket_chat.entities.Message;
import com.spbstu.web_socket_chat.entities.Name;
import com.spbstu.web_socket_chat.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.List;

import static com.spbstu.web_socket_chat.entities.Message.MESSAGE_COUNTER;

@Controller
@RequiredArgsConstructor
public class MessageController {

    private static final int SLEEP_MS = 300;
    @Autowired
    private final SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/newUser")
    @SendTo("/topic/users")
    public List<User> addUser(User newUser) throws Exception {
        Thread.sleep(SLEEP_MS); // simulated delay
        User.CONNECTED_USERS.add(newUser);
        System.out.println("Connected:" + newUser.getName() + "{" + newUser.getId() + "}");
        return User.CONNECTED_USERS;
    }

    @MessageMapping("/removeUser/{id}")
    @SendTo("/topic/users")
    public List<User> removeUser(@DestinationVariable String id) throws Exception {
        Thread.sleep(SLEEP_MS); // simulated delay
        User.CONNECTED_USERS.removeIf(u -> u.getId().equals(id));
        System.out.println("Disconnected user: " + id);
        return User.CONNECTED_USERS;
    }

    @MessageMapping("/changeName/{id}")
    @SendTo("/topic/users")
    public List<User> changeName(@DestinationVariable String id, Name newName) throws Exception {
        Thread.sleep(SLEEP_MS); // simulated delay
        User.CONNECTED_USERS.forEach(us -> {
            if(us.getId().equals(id)){
                us.setName(newName.toString());
                System.out.println("Set user " + id + "name to " + newName);
            }
        });

        return User.CONNECTED_USERS;
    }

    @MessageMapping("/sendMessage")
    @SendTo("/topic/public/chat")
    public Message sendMessage(Message message) throws Exception {
        MESSAGE_COUNTER++;
        System.out.println("Total messages: " + MESSAGE_COUNTER);
        return message;
    }

    @MessageMapping("/sendPrivateMessage")
    public Message addUser(@Payload Message chatMessage) {
        MESSAGE_COUNTER++;
        System.out.println("Total messages: " + MESSAGE_COUNTER);
        System.out.println(chatMessage.getSender());
        System.out.println(chatMessage.getRecipient());
        System.out.println(chatMessage.getContent());
        simpMessagingTemplate.convertAndSendToUser(chatMessage.getRecipient(),"/private", chatMessage);
        return chatMessage;
    }
}
