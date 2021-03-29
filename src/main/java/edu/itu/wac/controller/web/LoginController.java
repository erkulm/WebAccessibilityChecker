package edu.itu.wac.controller.web;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @RequestMapping("/login")
    @ResponseBody
    public ModelAndView serveLoginPage() {
        ModelAndView model = new ModelAndView();
        model.setViewName("login");
        return model;
    }

    @PostMapping("/login_perform")
    @ResponseBody
    public ModelAndView login() {
        ModelAndView model = new ModelAndView();
        model.setViewName("index");
        return model;
    }

}
