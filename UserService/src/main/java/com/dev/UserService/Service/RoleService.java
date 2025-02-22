package com.dev.UserService.Service;

import com.dev.UserService.Models.Role;
import com.dev.UserService.Repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    public Role createRole(String name){
        Role role = new Role();
        role.setRole(name);
        return roleRepository.save(role);
    }
}
