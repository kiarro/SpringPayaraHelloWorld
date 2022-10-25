package com.itmoclouddev.labf.controller;

import java.time.ZonedDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itmoclouddev.labf.service.DragonAutofill;


@RestController
@RequestMapping("/test")
public class TestController {
    
    @GetMapping()
    public String getTestMessage(){
        return "This is test message from test";
    }

    @PostMapping("/autofill")
    public String autofillDragon(){
        DragonAutofill.addRandomDragon();
        return "good";
    }
}
