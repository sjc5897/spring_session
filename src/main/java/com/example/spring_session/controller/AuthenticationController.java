package com.example.spring_session.controller;

import com.example.spring_session.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class AuthenticationController {
    @Autowired
    AuthenticationService service;

    @RequestMapping(value = {"/","/login"}, method = RequestMethod.GET)
    public String getLogin(){
        return "login";
    }
    @RequestMapping(value={"/","/login"}, method = RequestMethod.POST)
    public String login(@RequestParam("username")String email, @RequestParam("password") String password,
                        HttpServletRequest request, HttpSession session, Model model){
        String role;
        if(service.autheticated(email,password)){
            role = service.getRole(email);
        }
        else{
            model.addAttribute("error","Login Failed");
            return "login";
        }
        //Configure Session

            if (!session.isNew()) {
                session.invalidate();
            }
        HttpSession newSession = request.getSession();
        newSession.setAttribute("uid",email);
        newSession.setAttribute("role",role);
        newSession.setMaxInactiveInterval(60);

        return "redirect:/" + role;

    }
    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        if(!session.isNew()){
            session.invalidate();
        }
        return "redirect:/login";
    }
}
