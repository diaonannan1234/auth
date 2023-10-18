package org.example.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskWeb {



    @Autowired private TaskRepos taskRepos;

    @GetMapping()
    public List<TaskEntity> getAll(String sub,String obj,String act){

//        p, alice, /task, get
//        p, bob, /task, post
//        p, lisi, /task,delete
//        p, bob, /task,put


        return taskRepos.findAll();
    }
}
