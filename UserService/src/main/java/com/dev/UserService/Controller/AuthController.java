package com.dev.UserService.Controller;

import com.dev.UserService.Models.SessionStatus;
import com.dev.UserService.Service.AuthService;
import com.dev.UserService.dto.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<Userdto> login(@RequestBody LoginRequestDto loginRequestDto){
        return authService.login(loginRequestDto.getEmail(),loginRequestDto.getPassword());
    }
    @PostMapping("/logout/{id}")
    public ResponseEntity<Void> logout(@PathVariable("id") Long UserId, @RequestHeader("token") String token){
        return authService.logout(UserId,token);
    }
    @PostMapping("/signup")
    public ResponseEntity<Userdto> signup(@RequestBody SignUpRequestDto signUpRequestDto){
        Userdto userdto = authService.signup(signUpRequestDto.getEmail(), signUpRequestDto.getPassword());
        return new ResponseEntity<>(userdto, HttpStatus.OK);
    }
    @PostMapping("/validate")
    public ResponseEntity<SessionStatus> validate(@RequestBody ValidateTokenRequestDto validateTokenRequestDto){
        SessionStatus sessionStatus = authService.validate(validateTokenRequestDto.getToken(), validateTokenRequestDto.getId());
        return new ResponseEntity<>(sessionStatus,HttpStatus.OK);
    }
}
