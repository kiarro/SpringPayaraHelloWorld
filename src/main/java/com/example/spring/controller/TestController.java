package com.example.spring.controller;

import java.time.ZonedDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.core.Coordinates;
import com.example.spring.core.Dragon;
import com.example.spring.core.DragonCave;
import com.example.spring.core.DragonType;
import com.example.spring.core.test.Obj1;
import com.example.spring.core.test.Outer;
import com.example.spring.service.DragonAutofill;

@RestController
@RequestMapping("/test")
public class TestController {
    @PostMapping("/randomDragons")
    public Integer generateRandomDragons(@RequestParam(name = "amount", defaultValue = "1") Integer amount){
        for (int i=0; i<amount; i++){
            DragonAutofill.addRandomDragon();
        }
        return amount;
    }

    @GetMapping
    public String getTestMessage(){
        return "This is test message from test";
    }
    @PostMapping("/coord")
    public ResponseEntity<Coordinates> postCoord(@RequestBody Coordinates coord){
        System.out.println(coord);
        return ResponseEntity.ok(coord);
    }
    @PostMapping("/complex")
    public Outer postComplex(@RequestBody Outer outer){
        System.out.println(outer);
        return outer;
    }
    @PostMapping("/obj1")
    public Obj1 postObj1(@RequestBody Obj1 obj){
        System.out.println(obj);
        return obj;
    }

    @PostMapping("/enum")
    public DragonType postEnum(@RequestBody DragonType t){
        System.out.println(t);
        return t;
    }

    @PostMapping("/cave")
    public DragonCave postCave(@RequestBody DragonCave t){
        System.out.println(t);
        return t;
    }

    @PostMapping("/dragon")
    public Dragon postDragon(@RequestBody Dragon t){
        t.setCreationDate(ZonedDateTime.now());
        System.out.println(t);
        return t;
    }

    @PostMapping("/drag")
    public ResponseEntity<Coordinates> postDragon1(@RequestBody Dragon dragon) {
        System.out.println(dragon);
        return ResponseEntity.ok(dragon.getCoordinates());
    }
}
