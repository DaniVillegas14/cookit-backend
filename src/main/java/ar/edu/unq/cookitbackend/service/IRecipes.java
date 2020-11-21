package ar.edu.unq.cookitbackend.service;

import ar.edu.unq.cookitbackend.dto.request.CommentRequestDto;
import ar.edu.unq.cookitbackend.dto.request.RecipeDto;
import ar.edu.unq.cookitbackend.dto.response.CommentResponseDto;
import ar.edu.unq.cookitbackend.dto.response.PageableCommentResponseDto;
import ar.edu.unq.cookitbackend.dto.response.PageableRecipeResponseDto;
import ar.edu.unq.cookitbackend.dto.response.RecipeResponseDto;
import ar.edu.unq.cookitbackend.exception.CreateDocumentationException;
import ar.edu.unq.cookitbackend.exception.NotFoundException;
import ar.edu.unq.cookitbackend.model.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface IRecipes {
    Page<PageableRecipeResponseDto> getAllRecipes(Optional<String> search, Pageable pageable);
    RecipeDto createRecipe(RecipeDto recipeDto) throws IOException, CreateDocumentationException;
    RecipeResponseDto getRecipe(Long id) throws NotFoundException;
    CommentResponseDto createComment(CommentRequestDto request) throws NotFoundException;
    Page<CommentResponseDto> getCommentsByIdRecipe(Long id, Pageable pageable) throws NotFoundException;
    void deleteRecipeById(Long id) throws NotFoundException;
    Page<PageableRecipeResponseDto> findFollowersRecipes(Long userId, Pageable pageable) throws NotFoundException;
}
