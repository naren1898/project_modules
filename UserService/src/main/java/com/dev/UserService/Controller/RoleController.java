package com.dev.UserService.Controller;

import com.dev.UserService.Models.Role;
import com.dev.UserService.Service.RoleService;
import com.dev.UserService.dto.createRoleRequestDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles")
public class RoleController {
    private RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }
    @PostMapping
    public ResponseEntity<Role> createrole(@RequestBody createRoleRequestDto request){
           Role role = roleService.createRole(request.getName());
           return new ResponseEntity<>(role, HttpStatus.OK);
    }
}
