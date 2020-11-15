package com.example.spring_session.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TaskService {
    private static HashMap<String,String> dummy_tasks = new HashMap<>();
    static{
        dummy_tasks.put("worker@worker.com","Clean Bathroom");
        dummy_tasks.put("worker1@worker.com","Lunch Break");
        dummy_tasks.put("worker2@worker.com","Replace Light in Office 12");
    }

    public Map<String,String> getAllTasks(){
        return dummy_tasks;
    }
    public String getUserTask(String email){
        try{
            return dummy_tasks.get(email);
        }
        catch (Exception ex){
            return null;
        }
    }

    public boolean addTask(String email, String task){
        if(dummy_tasks.containsKey(email)){
            return false;
        }
        else{
            dummy_tasks.put(email,task);
            return true;
        }
    }

    public boolean completeTask(String email){
        dummy_tasks.remove(email);
        return true;
    }
}
