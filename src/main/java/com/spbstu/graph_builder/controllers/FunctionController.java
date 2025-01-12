package com.spbstu.graph_builder.controllers;

import com.spbstu.graph_builder.entities.FunctionType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class FunctionController {

    @GetMapping("/functions")
    List<String> getFunctions() {
        List<String> functions = Stream.of(FunctionType.values())
                .map(FunctionType::name)
                .collect(Collectors.toList());
        System.out.printf("[%s] Sent functions list: %s\n", new Date(), functions);
        return functions;
    }
}
