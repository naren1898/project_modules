package com.dev.UserService.Service;

import com.dev.UserService.Models.Role;
import com.dev.UserService.Models.User;
import com.dev.UserService.Repository.*;
import java.util.Set;

import com.dev.UserService.Repository.RoleRepository;
import com.dev.UserService.Repository.UserRepository;
import com.dev.UserService.dto.Userdto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserService {
private UserRepository userRepository;
private RoleRepository roleRepository;
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository=userRepository;
        this.roleRepository=roleRepository;
    }

    public Userdto getUserDetails(Long Id){
        Optional<User> userOptional = userRepository.findById(Id);
       if(userOptional.isEmpty()){
           return null;
       }
       return Userdto.from(userOptional.get());
    }
    public Userdto setUserRoles(Long userId, List<Long> roleIds){
        Optional<User> optionalUser = userRepository.findById(userId);
        Set<Role> roles = roleRepository.findAllByIdIn(roleIds);
        if(optionalUser.isEmpty()){
            return null;
        }
        User user = optionalUser.get();
        user.setRoles(roles);
        User saveduser= userRepository.save(user);
        return Userdto.from(saveduser);
    }
}
