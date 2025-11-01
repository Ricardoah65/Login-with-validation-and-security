package com.project.loginVaS.controller;

import com.project.loginVaS.model.User;
import com.project.loginVaS.service.UserService;
import jakarta.validation.Valid;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userServ;

    @GetMapping("/create")
    public ResponseEntity<String> create(@Valid @RequestBody User user){
        userServ.create(user);
        return ResponseEntity.ok("Registrado correctamente");
    }
}
