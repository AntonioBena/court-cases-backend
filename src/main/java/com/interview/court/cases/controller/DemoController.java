package com.interview.court.cases.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @GetMapping("/da-vidim")
    public String demo() {
        return "Hello World";
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/lock/user")
    public String user() {
        return "Hello World user";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/lock/admin")
    public String admin(){
        return "Hello World admin";
    }
}
