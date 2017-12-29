package org.audt4j.demo.hibernate.web.controller;

import org.audt4j.demo.hibernate.model.User;
import org.audt4j.demo.hibernate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AuditDemoController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/testAddUser", method = RequestMethod.GET)
    public String testWithoutAnnotations() {

        userService.saveUser(new User("aa", "bb"));
        return "redirect:/demo";
    }

}
