package com.haimian.bl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class Login {

    @RequestMapping("/login")
    public String login() {
        return "/login";
    }

    @RequestMapping(value = "/message")
    public String goMessage(HttpServletRequest request) {
//        String id_card = request.getParameter("id_card");
        return "/message";
    }

    @RequestMapping(value = "/god")
    public String goGod() {
        return "/setUserScore";
    }
}
