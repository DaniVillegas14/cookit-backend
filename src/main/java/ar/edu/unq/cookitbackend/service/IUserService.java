package ar.edu.unq.cookitbackend.service;

import ar.edu.unq.cookitbackend.dto.response.UserResponseDto;

public interface IUserService {
    UserResponseDto getUserByToken(String token);

    void addRecipeToFavorites(Long userId, Long favoriteRecipeDto);

    void removeRecipeToFavorites(Long userId, Long favoriteId);
}
