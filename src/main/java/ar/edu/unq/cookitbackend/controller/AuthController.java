package ar.edu.unq.cookitbackend.controller;

import ar.edu.unq.cookitbackend.dto.request.LoginRequestDto;
import ar.edu.unq.cookitbackend.dto.request.UserRequestDto;
import ar.edu.unq.cookitbackend.exception.NotFoundException;
import ar.edu.unq.cookitbackend.service.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private IAuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRequestDto request) throws NotFoundException {
        authService.register(request);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(
            @RequestHeader (value = "token") String token,
            @RequestBody LoginRequestDto request) {
        authService.login(token, request);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }
}
