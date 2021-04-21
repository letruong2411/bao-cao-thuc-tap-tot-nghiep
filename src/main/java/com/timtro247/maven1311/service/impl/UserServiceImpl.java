package com.timtro247.maven1311.service.impl;

import com.timtro247.maven1311.model.Pagination;
import com.timtro247.maven1311.model.Response;
import com.timtro247.maven1311.model.Users;
import com.timtro247.maven1311.model.security.Roles;
import com.timtro247.maven1311.model.security.UserRoles;
import com.timtro247.maven1311.repository.RoleRepository;
import com.timtro247.maven1311.repository.UserRepository;
import com.timtro247.maven1311.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public Users save(Users user) {
        Roles role = roleRepository.findByName("USER");
        Set<UserRoles> userRoles = new HashSet<>();
        userRoles.add(new UserRoles(user, role));
        user.getUserRoles().addAll(userRoles);
        String encryptedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        userRepository.save(user);
        return user;
    }

    @Override
    public Response<Users> findUsersList(int page, int pageSize, String searchData) {
        Pageable pageable = getPagination(page, pageSize);
        Page<Users> usersPage = userRepository.findAllByFullNameContainingOrUsernameContainingOrIdentityCardContainingOrAddressContaining(pageable, searchData, searchData, searchData, searchData);
        return new Response<>(usersPage.getContent(), new Pagination(page, usersPage.getTotalPages()));
    }

    @Override
    public Users findById(Long id) {
        return userRepository.findByIdAndDeletedAtNull(id);
    }

    @Override
    public Users findByUsername(String username) {
        return userRepository.findByUsernameAndDeletedAtNull(username);
    }

    @Override
    public Users findByPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumberAndDeletedAtNull(phoneNumber);
    }

    @Override
    public boolean update(Users user) {
        Users oldUser = userRepository.findByIdAndDeletedAtNull(user.getId());
        oldUser.setFullName(user.getFullName());
        oldUser.setPhoneNumber(user.getPhoneNumber());
        oldUser.setAddress(user.getAddress());
        oldUser.setIdentityCard(user.getIdentityCard());
        oldUser.setGender(user.getGender());
        if (!user.getPassword().matches("\\s*")) {
            String encryptedPassword = bCryptPasswordEncoder.encode(user.getPassword());
            oldUser.setPassword(encryptedPassword);
        }
        LocalDateTime oldUpdatedAt = oldUser.getUpdatedAt();
        userRepository.save(oldUser);
        if (oldUpdatedAt.equals(oldUser.getUpdatedAt())) {
            return false;
        }
        return true;
    }

    @Override
    public void delete(Long id) {
        Users user = userRepository.findByIdAndDeletedAtNull(id);
        if(user == null) return;
        user.setDeletedAt(LocalDateTime.now());
        userRepository.save(user);
    }

    @Override
    public void activeUser(Long id) {
        Users user = userRepository.findById(id).get();
        user.setDeletedAt(null);
        userRepository.save(user);
    }

    public Pageable getPagination(int page, int pageSize) {
        return PageRequest.of((page > 0) ? --page : 0, pageSize);
    }
}
