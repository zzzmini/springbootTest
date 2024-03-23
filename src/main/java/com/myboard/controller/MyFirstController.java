package com.myboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyFirstController {
    @GetMapping("/hi")
    public String hi(){
        return "greetings";
    }

    @GetMapping("/test")
    public String test(Model model){
        model.addAttribute("name", "김형민");
        return "test";
    }
}
