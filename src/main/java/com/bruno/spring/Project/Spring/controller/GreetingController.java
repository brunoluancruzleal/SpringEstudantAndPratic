package com.bruno.spring.Project.Spring.controller;


import com.bruno.spring.Project.Spring.model.Greeting;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

@RestController

public class GreetingController {

    private static final String templates = "Hello, %s!";
    private final AtomicLong couter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(
            @RequestParam(value = "name", defaultValue = "Word")
            String name){
        return new Greeting(couter.incrementAndGet(),String.format(templates,name));
    }
}
