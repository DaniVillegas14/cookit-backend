package ar.edu.unq.cookitbackend.service;

import ar.edu.unq.cookitbackend.dto.request.EditUserRequestDto;
import ar.edu.unq.cookitbackend.dto.request.UserRequestDto;
import ar.edu.unq.cookitbackend.dto.response.UserResponseDto;
import ar.edu.unq.cookitbackend.exception.NotFoundException;
import ar.edu.unq.cookitbackend.exception.PasswordIncorrectException;

public interface IUserService {
    UserResponseDto getUserByToken(String token);

    void addRecipeToFavorites(Long userId, Long favoriteRecipeDto);

    void removeRecipeToFavorites(Long userId, Long favoriteId);

    void followUser(Long userId, Long userFollowId) throws NotFoundException;

    void unfollowUser(Long userId, Long userFollowId) throws NotFoundException;

    void editUser(EditUserRequestDto request) throws NotFoundException, PasswordIncorrectException;
}
