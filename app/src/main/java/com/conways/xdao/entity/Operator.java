package com.conways.xdao.entity;

/**
 * Created by John on 2016/9/22.
 */
public class Operator {

    private int id;
    private String name;


    public Operator(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Operator(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
