package com.day01.microservicecloudconfigdeptclient8001.service;

import com.day01.microservicecloudapi.entity.Dept;

import java.util.List;


public interface DeptService {
    public boolean add(Dept dept);
    public Dept get(Long id);
    public List<Dept> list();

}
