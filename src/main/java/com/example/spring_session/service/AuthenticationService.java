package com.example.spring_session.service;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class AuthenticationService {
    private static HashMap<String,String> dummy_udb = new HashMap<>();
    static{
        dummy_udb.put("admin@admin.com","qwerty");
        dummy_udb.put("worker@worker.com","qwerty");
        dummy_udb.put("manager@manager.com","qwerty");
        dummy_udb.put("worker2@worker.com","qwerty");
    }

    private static HashMap<String,String> dummy_rdb = new HashMap<>();
    static{
        dummy_rdb.put("admin@admin.com","admin");
        dummy_rdb.put("worker@worker.com","worker");
        dummy_rdb.put("manager@manager.com","manager");
        dummy_rdb.put("worker2@worker.com","worker");
    }

    public boolean autheticated(String user, String password){
        if(dummy_udb.containsKey(user)){
            return dummy_udb.get(user).equals(password);
        }
        return false;
    }

    public String getRole(String user){
        return dummy_rdb.get(user);
    }

    public List<String> getAllUsers(){
        List<String> users = new ArrayList<>();
        for(String user : dummy_udb.keySet()){
            users.add(user);
        }
        return users;
    }

    public List<String> getAllWorkers(){
        List<String> Workers = new ArrayList<>();
        for(String user : dummy_udb.keySet()){
            if(dummy_rdb.get(user).equals("worker")){
                Workers.add(user);
            }
        }
        return Workers;
    }

    public void addUser(String email, String role, String password){
        dummy_udb.put(email,password);
        dummy_rdb.put(email,role);
    }

    public String verfySession(HttpSession session, String v_role){
        try{

            if(session.isNew()){
                return "redirect:/login";
            }
            String role = (String) session.getAttribute("role");
            if(!role.equals(v_role)){
                return "redirect:/" + role;
            }
        }catch (Exception ex){
            return "redirect:/login";
        }
        return "";
    }
}
