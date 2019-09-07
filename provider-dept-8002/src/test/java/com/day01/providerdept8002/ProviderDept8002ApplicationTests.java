package com.day01.providerdept8002;

import com.day01.providerdept8002.dao.DeptDao;
import com.day01.providerdept8002.service.DeptService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProviderDept8002ApplicationTests {
    @Autowired
    private DeptService deptService;

    @Test
    public void contextLoads() {
        System.out.println(deptService.list());
    }

}
