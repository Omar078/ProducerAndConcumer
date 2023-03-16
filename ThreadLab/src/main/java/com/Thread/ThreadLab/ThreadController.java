package com.Thread.ThreadLab;


import com.Thread.ThreadLab.Service.ThreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;

@CrossOrigin
@RestController
public class ThreadController {

    @Autowired
    private ThreadService threadService = new ThreadService();

    @PostMapping("Start/{nq}/{nm}")
    public void start(@RequestBody String[] adjList, @PathVariable int nq, @PathVariable int nm){
        threadService.startSimulation(adjList, nq, nm);
    }
    @GetMapping("Update")
    public LinkedList<String> update(){
        return threadService.sendUpdate();
    }

    @PostMapping("addProducts/{n}")
    public void addProducts(@PathVariable int n){
        threadService.addProduct(n);
    }
    @PostMapping("restart")
    public void restart(){
        threadService.restart();
    }
}
