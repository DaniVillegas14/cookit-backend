package ar.edu.unq.cookitbackend.service;

import ar.edu.unq.cookitbackend.dto.request.RecipeDto;
import ar.edu.unq.cookitbackend.model.Recipe;

import java.util.List;

public interface IRecipes {
    List<Recipe> getAllRecipes();
    Recipe createRecipe(RecipeDto recipeDto);
}
