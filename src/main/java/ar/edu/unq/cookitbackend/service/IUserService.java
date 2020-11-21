package ar.edu.unq.cookitbackend.service;

import ar.edu.unq.cookitbackend.dto.request.EditUserRequestDto;
import ar.edu.unq.cookitbackend.dto.request.UserRequestDto;
import ar.edu.unq.cookitbackend.dto.response.UserResponseDto;
import ar.edu.unq.cookitbackend.exception.CreateDocumentationException;
import ar.edu.unq.cookitbackend.exception.NotFoundException;
import ar.edu.unq.cookitbackend.model.Category;

import java.util.List;
import ar.edu.unq.cookitbackend.exception.PasswordIncorrectException;

import java.io.IOException;

public interface IUserService {
    UserResponseDto getUserByToken(String token);

    void addRecipeToFavorites(Long userId, Long favoriteRecipeDto);

    void removeRecipeToFavorites(Long userId, Long favoriteId);

    void followUser(Long userId, Long userFollowId) throws NotFoundException;

    void unfollowUser(Long userId, Long userFollowId) throws NotFoundException;

    void addNewCategory(Long userId, String name) throws  NotFoundException;

    List<Category> getCategories(Long userId) throws NotFoundException;

    void addRecipeToCategory(Long userId, Long idCategory, Long idRecipe) throws NotFoundException;

    Category getFavoritesByCategory(Long userId, Long idCategory) throws NotFoundException;

    void editUser(EditUserRequestDto request) throws NotFoundException, PasswordIncorrectException, IOException, CreateDocumentationException;
}
