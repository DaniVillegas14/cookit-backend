package ar.edu.unq.cookitbackend.controller;

import ar.edu.unq.cookitbackend.dto.request.UserRequestDto;
import ar.edu.unq.cookitbackend.dto.response.UserResponseDto;
import ar.edu.unq.cookitbackend.exception.NotFoundException;
import ar.edu.unq.cookitbackend.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping
    public ResponseEntity<UserResponseDto> getUserByToken(@RequestHeader("Authorization") String token) {
        UserResponseDto response = userService.getUserByToken(token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<String> editUser(@RequestBody UserRequestDto request) throws NotFoundException {
        userService.editUser(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/follow")
    public ResponseEntity<String> unfollowUser(@RequestBody HashMap<String, Long> data) throws NotFoundException {
        userService.unfollowUser(data.get("userId"), data.get("userUnfollowId"));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{userId}/favorites/{recipeId}")
    public ResponseEntity<String> addRecipeToFavorites(@PathVariable("userId") Long userId,
                                                       @PathVariable("recipeId") Long recipeId) {
        userService.addRecipeToFavorites(userId, recipeId);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @DeleteMapping("/{userId}/favorites/{favoriteId}")
    public ResponseEntity<String> removeRecipeToFavorites(@PathVariable("userId") Long userId,
                                                          @PathVariable("favoriteId") Long favoriteId) {
        userService.removeRecipeToFavorites(userId, favoriteId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{userId}/follow/{userFollowId}")
    public ResponseEntity<String> addUserToFollow(@PathVariable("userId") Long userId,
                                                  @PathVariable("userFollowId") Long userFollowId) throws NotFoundException {
        userService.followUser(userId, userFollowId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
