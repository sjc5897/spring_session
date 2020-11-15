package com.example.spring_session.controller;

import com.example.spring_session.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AdminController {
    @Autowired
    AuthenticationService service;

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String getAdminPage(HttpServletRequest request, Model model){
        //Authenticate via session
        HttpSession session = request.getSession();
        String ret = service.verfySession(session,"admin");
        if(ret.length() > 0){
            return ret;
        }

        List<String> users = service.getAllUsers();
        model.addAttribute("users",users);
        return "admin";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.POST)
    public String submitAdmin(@RequestParam("email") String email, @RequestParam("role") String role,
                              @RequestParam("password") String password, HttpServletRequest request, Model model){
        //Authenticate via session
        HttpSession session = request.getSession();
        String ret = service.verfySession(session,"admin");
        if(ret.length() > 0){
            return ret;
        }

        service.addUser(email,role,password);
        return "redirect:/admin";
    }
}
