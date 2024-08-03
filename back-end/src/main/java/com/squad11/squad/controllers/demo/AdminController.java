package com.squad11.squad.controllers.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api/v1/admin/")
public class AdminController {

    @GetMapping("")
    public String admin() {
        return "admin";
    }

}
