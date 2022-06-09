package com.example.spring.core.test;

public class Inner {
    private Long a;
    private String txt;

    public Inner(){
        this(0l, "");
    }

    public Inner(Long a, String txt) {
        this.a = a;
        this.txt = txt;
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
}
