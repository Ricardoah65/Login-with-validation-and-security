package com.project.loginVaS.service;

import com.project.loginVaS.model.User;

import java.util.List;

public interface IUserService {

    public void create(User user);
    public List<User> readAllUser();
    public User readForId(Long id);
    public void delete(Long id);
    public User updateForId(Long id);
    public User update(User user);
}
