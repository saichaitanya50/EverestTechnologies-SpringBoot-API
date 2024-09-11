package com.pro.EverestTechnologies.controller;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String sayHello(HttpServletRequest request) {
        return "Hello Chaitanya!" + request.getSession().getId();
    }

}
