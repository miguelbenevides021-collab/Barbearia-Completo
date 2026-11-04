package com.barbearia.Barbearia_Completo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @GetMapping("/ola")
    @PreAuthorize("hasRole('ADMIN')")
    public String test(){
        return "Ola, apenas admin";
    }
}
