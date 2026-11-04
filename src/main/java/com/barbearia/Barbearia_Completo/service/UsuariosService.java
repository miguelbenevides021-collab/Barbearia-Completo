package com.barbearia.Barbearia_Completo.service;

import com.barbearia.Barbearia_Completo.dto.AuthResponse;
import com.barbearia.Barbearia_Completo.dto.LoginRequest;
import com.barbearia.Barbearia_Completo.dto.RegisterRequest;
import com.barbearia.Barbearia_Completo.dto.UsuarioResponse;
import com.barbearia.Barbearia_Completo.model.UsuarioModel;
import com.barbearia.Barbearia_Completo.repository.UsersRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UsuariosService {
    private final UsersRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UsuariosService(UsersRepository repository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public UsuarioResponse register(RegisterRequest request){
        String email =  normalizeEmail(request.getEmail());

       if(repository.existsByEmail(email)){
           throw new ResponseStatusException(HttpStatus.CONFLICT, "Email já Cadastrado");
       }

        UsuarioModel user = new UsuarioModel();
        user.setEmail(email);
        user.setName(request.getName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(UsuarioModel.Role.CLIENT);

        UsuarioModel saved = repository.save(user);
        return new UsuarioResponse(saved.getId(), saved.getEmail(), saved.getName());
    }

    public AuthResponse login(LoginRequest request){
        String email = normalizeEmail(request.getEmail());

        UsuarioModel user = repository.findByEmail(email).orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciais invalidas"));

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciais invalidas");
        }

        String token = jwtService.generateToken(user.getEmail(), user.getRole().name());

        UsuarioResponse usuarioResponse = new UsuarioResponse(
                user.getId(),
                user.getEmail(),
                user.getName()
        );

        return new AuthResponse(token, usuarioResponse);
    }

    private String normalizeEmail(String email) {
        return email.trim().toLowerCase();
    }
}
