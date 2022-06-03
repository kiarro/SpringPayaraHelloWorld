package com.example.spring.core.test;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.example.spring.core.InvalidValueException;

public class Obj1 {
    private Long a;
    private String txt;
    
    private Long id;

    @JsonCreator
    public Obj1(Long a, String txt) throws InvalidValueException{
        if (a<0) {
            throw new InvalidValueException("a < 0");
        }
        this.a = a;
        this.txt = txt;
        this.id = 50l;
    }


    public Obj1(Long a, String txt, Long id) throws InvalidValueException{
        this.a = a;
        this.txt = txt;
        this.id = id;
    }

    public Long getA() {
        return a;
    }

    public void setA(Long a) {
        this.a = a;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    } 

    public Long getId(){
        return id;
    }

}
