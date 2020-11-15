package com.example.spring_session.controller;

import com.example.spring_session.service.AuthenticationService;
import com.example.spring_session.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ManagerController {
    @Autowired
    TaskService taskService;
    @Autowired
    AuthenticationService authenticationService;

    @RequestMapping(value="/manager", method = RequestMethod.GET)
    public String getManager(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        String ret = authenticationService.verfySession(session,"manager");
        if(ret.length() > 0){
            return ret;
        }
        List<String> allWorkers = authenticationService.getAllWorkers();
        Map<String, String> tasks = taskService.getAllTasks();

        allWorkers.removeAll(tasks.keySet());
        if(allWorkers.size()>0){
            model.addAttribute("aval_workers", allWorkers);
        }
        if(tasks.size()>0){
            model.addAttribute("tasks", tasks);
        }
        return "manager";
    }

    @RequestMapping(value="/manager", method = RequestMethod.POST)
    public String giveTask(@RequestParam("email") String email, @RequestParam("task") String task,
                           HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        String ret = authenticationService.verfySession(session,"manager");
        if(ret.length() > 0){
            return ret;
        }

        taskService.addTask(email,task);
        return "redirect:/manager";

    }

}
