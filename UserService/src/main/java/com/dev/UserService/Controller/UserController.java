package com.dev.UserService.Controller;

import com.dev.UserService.Service.UserService;
import com.dev.UserService.dto.SetUserRolesRequestDto;
import com.dev.UserService.dto.Userdto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.dev.UserService.dto.*;
@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<Userdto> getUserDetails(@PathVariable("id") Long UserId){
        Userdto userdto = userService.getUserDetails(UserId);
        return new ResponseEntity<>(userdto, HttpStatus.OK);
    }
    @PostMapping("/{id}/roles")
    public ResponseEntity<Userdto> setUserRoles(@PathVariable("id") Long UserId, @RequestBody SetUserRolesRequestDto request){
        System.out.println(request.getRoleIds());
     Userdto userdto =userService.setUserRoles(UserId,request.getRoleIds());
     return new ResponseEntity<>(userdto,HttpStatus.OK);
    }
}
