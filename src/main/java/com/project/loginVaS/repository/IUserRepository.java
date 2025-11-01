package com.project.loginVaS.repository;

import com.project.loginVaS.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User,Long> {
    //Buscar un usuario por el email
    Optional<User> findByEmail(String email);
}
