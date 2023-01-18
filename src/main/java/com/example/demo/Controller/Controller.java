package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Value("${spring.application.name}")
    private String name;

    @GetMapping("/name")
    public String getNameApplication() {
        return name;
    }

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello World";
    }
}