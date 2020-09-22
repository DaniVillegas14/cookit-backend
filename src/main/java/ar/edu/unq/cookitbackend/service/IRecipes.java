package ar.edu.unq.cookitbackend.service;

import ar.edu.unq.cookitbackend.dto.request.RecipeDto;
import ar.edu.unq.cookitbackend.dto.response.RecipeResponseDto;
import ar.edu.unq.cookitbackend.exception.NotFoundException;
import ar.edu.unq.cookitbackend.model.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IRecipes {
    Page<Recipe> getAllRecipes(String search, Pageable pageable);
    Recipe createRecipe(RecipeDto recipeDto);
    List<RecipeResponseDto> getRecipesByQuery(String query) throws NotFoundException;
    Recipe getRecipe(Long id);
}
