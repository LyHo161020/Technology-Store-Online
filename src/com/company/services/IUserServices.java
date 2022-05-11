package com.company.services;

import com.company.model.User;

import java.util.ArrayList;

public interface IUserServices {
    ArrayList<User> getUsers();

    User loginAdmin(String username, String password);

    void addUser(User newUser);

    void update(String value, String options, int id);

    boolean exist(int id);

    boolean checkEmailExist(String email);

    boolean checkPhoneExist(String phone);

    boolean checkUserNameExist(String userName);

    User getUserById(int id);

    User getUserByUserName(String userName);
}
