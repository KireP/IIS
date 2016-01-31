package com.hello.controller;

import com.hello.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/greeting", method = RequestMethod.GET)
    public ModelAndView greeting(@RequestParam(value = "name", required = false, defaultValue = "World") String name) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("greetings");
        modelAndView.addObject("name", name);
        return modelAndView;
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ModelAndView getUser() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("userDetails");
        modelAndView.addObject("user", userService.findUser(1));
        return modelAndView;
    }
}
