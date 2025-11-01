package com.project.loginVaS.service;

import com.project.loginVaS.model.User;
import com.project.loginVaS.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService{

    @Autowired
    private IUserRepository userRepo;

    @Override
    public void create(User user) {
        userRepo.save(user);
    }

    @Override
    public List<User> readAllUser() {
        return List.of();
    }

    @Override
    public User readForId(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public User updateForId(Long id) {
        return null;
    }

    @Override
    public User update(User user) {
        return null;
    }
}
