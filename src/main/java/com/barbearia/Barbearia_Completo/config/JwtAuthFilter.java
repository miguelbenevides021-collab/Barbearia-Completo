package com.barbearia.Barbearia_Completo.config;

import com.barbearia.Barbearia_Completo.service.JwtService;
import com.barbearia.Barbearia_Completo.repository.UsersRepository;
import com.barbearia.Barbearia_Completo.model.UsuarioModel;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UsersRepository usuarioRepository;

    public JwtAuthFilter(
            JwtService jwtService,
            UsersRepository usuarioRepository
    ) {
        this.jwtService = jwtService;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain
    ) throws ServletException, IOException {

        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {

            String token = header.substring(7);

            try {
                // Extrai o email do token
                String email = jwtService.extractEmail(token);

                // Busca o usuário no banco
                UsuarioModel usuario = usuarioRepository.findByEmail(email)
                        .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

                // Recupera a role do usuário
                String role = usuario.getRole().name();

                // Converte a role para uma autoridade do Spring Security
                var authorities = List.of(
                        new SimpleGrantedAuthority("ROLE_" + role)
                );

                // Cria a autenticação com email e permissões
                var auth = new UsernamePasswordAuthenticationToken(
                        email,
                        null,
                        authorities
                );

                // Salva a autenticação no contexto
                SecurityContextHolder.getContext().setAuthentication(auth);

            } catch (JwtException | IllegalArgumentException e) {
                SecurityContextHolder.clearContext();
            }
        }

        chain.doFilter(request, response);
    }
}