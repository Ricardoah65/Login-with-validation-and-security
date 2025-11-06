package com.project.loginVaS.controller;

import com.project.loginVaS.dto.AuthRequest;
import com.project.loginVaS.model.User;
import com.project.loginVaS.repository.IUserRepository;
import com.project.loginVaS.util.JwtUtil;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private IUserRepository userRepo;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        try {
            //autenticar el usuario
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(),
                            authRequest.getPassword()));

            String token = jwtUtil.generationToken(authRequest.getEmail());

            return ResponseEntity.ok("Bearer  " + token);

        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body("Credencial invalida");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (userRepo.findByEmail(user.getEmail().isPresent())) {
            return ResponseEntity.badRequest().body("Ya existe usuario con este email");
        }
        userRepo.save(user);
        return ResponseEntity.ok("Agregado");
    }
}