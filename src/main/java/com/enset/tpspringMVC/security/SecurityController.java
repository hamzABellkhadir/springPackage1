package com.enset.tpspringMVC.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecurityController {
    @RequestMapping("/login")
    public String login(){

        System.out.println("********************************login worck");
        return "login";
    }


    @GetMapping(path="/")
    public String home() {
        return "redirect:/index";
    }
}
