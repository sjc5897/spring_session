package com.example.spring_session.controller;

import com.example.spring_session.service.AuthenticationService;
import com.example.spring_session.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

@Controller
public class WorkerController {
    @Autowired
    TaskService taskService;
    @Autowired
    AuthenticationService authenticationService;

    @RequestMapping(value="/worker", method = RequestMethod.GET)
    public String getUserPage(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        String ret = authenticationService.verfySession(session,"worker");
        if(ret.length() > 0){
            return ret;
        }
        model.addAttribute("task",taskService.getUserTask((String)session.getAttribute("uid")));
        return "worker";
    }

    @RequestMapping(value="/worker/{task}", method = RequestMethod.GET)
    public String completeTask(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        String ret = authenticationService.verfySession(session,"worker");
        if(ret.length() > 0){
            return ret;
        }
        taskService.completeTask((String)session.getAttribute("uid"));
        return "redirect:/worker";
    }

}
