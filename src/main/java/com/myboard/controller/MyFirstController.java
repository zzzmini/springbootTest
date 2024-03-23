package com.myboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyFirstController {
    @GetMapping("/hi")
    public String hi(){
        return "greetings";
    }

    @GetMapping("/test")
    public String test(){
        return "test";
    }
}
