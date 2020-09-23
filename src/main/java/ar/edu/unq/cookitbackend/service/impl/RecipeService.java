package ar.edu.unq.cookitbackend.service.impl;

import ar.edu.unq.cookitbackend.dto.request.RecipeDto;
import ar.edu.unq.cookitbackend.dto.response.RecipeResponseDto;
import ar.edu.unq.cookitbackend.exception.NotFoundException;
import ar.edu.unq.cookitbackend.model.Recipe;
import ar.edu.unq.cookitbackend.persistence.RecipeRepository;
import ar.edu.unq.cookitbackend.service.IRecipes;
import ar.edu.unq.cookitbackend.utils.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RecipeService implements IRecipes {

    @Autowired
    RecipeRepository recipeRepository;

    @Override
    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    @Override
    public Recipe createRecipe(RecipeDto recipeDto) {
        return recipeRepository.save(Converter.toRecipe(recipeDto));
    }

    @Override
    public List<RecipeResponseDto> getRecipesByQuery(String query) throws NotFoundException {
        List<Recipe> recipes = recipeRepository.findRecipesByQuery(query);

        if (recipes.isEmpty()) {
            throw new NotFoundException("No se encontraron resultados para " + query);
        }

        return Converter.toListRecipeResponseDto(recipes);
    }

    @Override
    public Recipe getRecipe(Long id) {
        return recipeRepository.findById(id).orElse(new Recipe());
    }
}
