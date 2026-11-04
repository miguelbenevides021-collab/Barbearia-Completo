package com.barbearia.Barbearia_Completo.controller;

import com.barbearia.Barbearia_Completo.dto.AuthResponse;
import com.barbearia.Barbearia_Completo.dto.LoginRequest;
import com.barbearia.Barbearia_Completo.dto.RegisterRequest;
import com.barbearia.Barbearia_Completo.dto.UsuarioResponse;
import com.barbearia.Barbearia_Completo.service.UsuariosService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private UsuariosService service;

    public AuthController(UsuariosService usuariosService){
        this.service = usuariosService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioResponse register(@Valid @RequestBody RegisterRequest request){
        return service.register(request);

    }

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody LoginRequest request){
       return service.login(request);
    }

}
