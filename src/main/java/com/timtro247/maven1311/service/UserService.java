package com.timtro247.maven1311.service;

import com.timtro247.maven1311.model.Response;
import com.timtro247.maven1311.model.Users;

import java.util.List;

public interface UserService {

    Users save(Users user);
    Response<Users> findUsersList(int page, int pageSize, String searchData);
    Users findById(Long id);
    Users findByUsername(String username);
    Users findByPhoneNumber(String phoneNumber);
    boolean update(Users user);
    void delete(Long id);
    void activeUser(Long id);
}
