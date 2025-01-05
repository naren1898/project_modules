package com.dev.UserService.dto;

import com.dev.UserService.Models.Role;
import com.dev.UserService.Models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
public class Userdto {
    private String email;
    private Set<Role> roles =new HashSet<>();

    public static Userdto from(User user){
        Userdto userdto =new Userdto();
        userdto.setEmail(user.getEmail());
        userdto.setRoles(user.getRoles());
        return userdto;
    }

}
