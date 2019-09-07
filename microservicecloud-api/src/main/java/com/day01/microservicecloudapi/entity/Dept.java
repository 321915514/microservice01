package com.day01.microservicecloudapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
@Data
@NoArgsConstructor
@Accessors(chain=true)
@SuppressWarnings("serial")
public class Dept implements Serializable {
    private Integer deptno;
    private String  dname;
    private String  db_source;

    public Dept(String dname) {
        this.dname = dname;
    }
}
