package ar.edu.unq.cookitbackend.controller;

import ar.edu.unq.cookitbackend.dto.request.LoginGoogleRequestDto;
import ar.edu.unq.cookitbackend.dto.request.LoginRequestDto;
import ar.edu.unq.cookitbackend.dto.request.UserRequestDto;
import ar.edu.unq.cookitbackend.dto.response.JwtResponse;
import ar.edu.unq.cookitbackend.exception.CreateDocumentationException;
import ar.edu.unq.cookitbackend.exception.EmailExistException;
import ar.edu.unq.cookitbackend.exception.LoginException;
import ar.edu.unq.cookitbackend.exception.NotFoundException;
import ar.edu.unq.cookitbackend.service.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private IAuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRequestDto request) throws NotFoundException, EmailExistException, IOException, CreateDocumentationException {
        authService.register(request);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    @PostMapping("/login/social")
    public ResponseEntity<JwtResponse> loginSocial(@RequestBody LoginGoogleRequestDto request) throws IOException, CreateDocumentationException {
        JwtResponse response = authService.loginSocial(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginRequestDto request) throws NotFoundException, LoginException {
        JwtResponse response = authService.login(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
