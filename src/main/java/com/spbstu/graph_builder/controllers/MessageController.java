package com.spbstu.graph_builder.controllers;

import com.spbstu.graph_builder.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.Date;

@Controller
public class MessageController {

    private static final int SLEEP_MS = 128;
    private final SimpMessagingTemplate simpMessagingTemplate;
    @Autowired
    public MessageController(SimpMessagingTemplate simpMessagingTemplate){
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @MessageMapping("/newUser/{id}")
    public void addUser(@DestinationVariable String id) throws Exception {
        Thread.sleep(SLEEP_MS); // simulated delay
        var newUser = new User(id);
        User.CONNECTED_USERS.add(newUser);
        System.out.printf("[%s][%s] CONNECTED\n", new Date(), newUser.getId());
    }

    @MessageMapping("/removeUser/{id}")
    public void removeUser(@DestinationVariable String id) throws Exception {
        Thread.sleep(SLEEP_MS); // simulated delay
        User.CONNECTED_USERS.removeIf(u -> u.getId().equals(id));
        System.out.printf("[%s][%s] DISCONNECTED\n", new Date(), id);
    }

    @MessageMapping("/calculate")
    public Point calculate(CalculationRequest request) throws Exception {
        Thread.sleep(SLEEP_MS);

        FunctionType functionType = Enum.valueOf(FunctionType.class, request.getFunctionType());
        var xCoordinate = request.getXCoordinate();

        var point = Calculator.calculate(functionType, xCoordinate);
        var userId = request.getUserId();

        System.out.printf("[%s][%s] GOT: {x: %f}\n", new Date(), userId, xCoordinate);
        System.out.printf("[%s][%s] CALCULATED: {x: %f, y: %f}\n", new Date(), userId, point.getXCoordinate(), point.getYCoordinate());
        simpMessagingTemplate.convertAndSendToUser(userId,"/calculation", point);
        return point;
    }
}
