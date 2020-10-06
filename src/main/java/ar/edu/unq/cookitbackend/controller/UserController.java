package ar.edu.unq.cookitbackend.controller;

import ar.edu.unq.cookitbackend.dto.response.UserResponseDto;
import ar.edu.unq.cookitbackend.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping
    public ResponseEntity<UserResponseDto> getUserByToken(@RequestHeader ("Authorization") String token) {
        UserResponseDto response = userService.getUserByToken(token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
