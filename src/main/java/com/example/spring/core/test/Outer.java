package com.example.spring.core.test;

import com.example.spring.core.DragonType;

public class Outer {
    private String name;
    private Inner inner;
    private DragonType type;

    public Outer(){
        // this("", new Inner(), DragonType.FIRE);
    }

    public Outer(String name, Inner inner, DragonType type) {
        this.name = name;
        this.inner = inner;
        this.type = type;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Inner getInner() {
        return inner;
    }
    public void setInner(Inner inner) {
        this.inner = inner;
    }

    public DragonType getType() {
        return type;
    }

    public void setType(DragonType type) {
        this.type = type;
    }
    
}
