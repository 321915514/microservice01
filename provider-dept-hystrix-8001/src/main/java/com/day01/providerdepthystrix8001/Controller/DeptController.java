package com.day01.providerdepthystrix8001.Controller;

import com.day01.microservicecloudapi.entity.Dept;
import com.day01.providerdepthystrix8001.service.DeptService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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
    @HystrixCommand(fallbackMethod = "processHystrix_Get")
    public Dept get(@PathVariable("id") Long id) {
        Dept dept=service.get(id);
        if (dept==null){
            throw new RuntimeException("该ID："+id+"没有没有对应的信息");
        }else {
            return dept;
        }
    }
    public Dept processHystrix_Get(@PathVariable("id")Long id){
        return new Dept().setDeptno(id)
                .setDname("该ID："+id+"没有没有对应的信息,null--@HystrixCommand")
                .setDb_source("no this database in MySQL");
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
