package com.barbearia.Barbearia_Completo.dto;


public class AuthResponse {
    private final String token;
    private final UsuarioResponse user;

    public AuthResponse(String token, UsuarioResponse user) {
        this.token = token;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public UsuarioResponse getUser() {
        return user;
    }
}
