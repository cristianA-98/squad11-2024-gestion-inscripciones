package com.squad11.squad.controllers.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api/v1/demo/")
public class DemoController {

    @GetMapping("saludo")
    public String saludo(){
        return "hola";
    }
}
