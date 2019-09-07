package com.day01.providerdept8003.Controller;

import com.day01.microservicecloudapi.entity.Dept;
import com.day01.providerdept8003.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeptController {
    @Autowired
    private DeptService service;
    @Autowired
    private DiscoveryClient discoveryClient;


    @RequestMapping(value="/dept/add",method= RequestMethod.POST)
    public boolean add(@RequestBody Dept dept) {
        return service.add(dept);
    }

    @RequestMapping(value="/dept/get/{id}",method=RequestMethod.GET)
    public Dept get(@PathVariable("id") Long id) {
        return service.get(id);
    }

    @RequestMapping(value="/dept/list",method=RequestMethod.GET)
    public List<Dept> list() {
        return service.list();
    }
    @GetMapping("/dept/discovery")
    public Object discovery(){
        List<String> list=discoveryClient.getServices();
        System.out.println("---------------"+list);
        return this.discoveryClient;
    }

}
