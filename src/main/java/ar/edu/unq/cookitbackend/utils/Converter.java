package ar.edu.unq.cookitbackend.utils;

import ar.edu.unq.cookitbackend.dto.request.IngredientDto;
import ar.edu.unq.cookitbackend.dto.request.RecipeDto;
import ar.edu.unq.cookitbackend.dto.request.StepDto;
import ar.edu.unq.cookitbackend.dto.request.UserRequestDto;
import ar.edu.unq.cookitbackend.dto.response.RecipeResponseDto;
import ar.edu.unq.cookitbackend.model.Ingredient;
import ar.edu.unq.cookitbackend.model.Recipe;
import ar.edu.unq.cookitbackend.model.Step;
import ar.edu.unq.cookitbackend.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Converter {

    public static User toUser(UserRequestDto request) {
        return User.builder()
                .email(request.getEmail())
                .name(request.getName())
                .lastname(request.getLastname())
                .imageUrl(request.getImageUrl())
                .build();
    }

    public static Recipe toRecipe(RecipeDto recipeDto) {
        Recipe recipe = Recipe.builder()
                .comensales(recipeDto.getComensales())
                .description(recipeDto.getDescription())
                .imageUrl(recipeDto.getImage_url())
                .name(recipeDto.getName())
                .time(recipeDto.getTime())
                .build();

        recipe.setIngredients(toIngredients(recipeDto.getIngredients(), recipe));
        recipe.setSteps(toSteps(recipeDto.getSteps(), recipe));
        return recipe;
    }

    public static Step toStep(StepDto stepDto, Recipe recipe) {
        return Step.builder().recipe(recipe).description(stepDto.getDescription()).build();
    }

    public static List<Step> toSteps(List<StepDto> stepDtos, Recipe recipe) {
        return stepDtos.stream().map(stepDto -> toStep(stepDto, recipe)).collect(Collectors.toList());
    }

    public static Ingredient toIngredient(IngredientDto ingredientDto, Recipe recipe) {
        return Ingredient.builder()
                .name(ingredientDto.getName())
                .recipe(recipe)
                .quantity_weight(ingredientDto.getQuantity_weight())
                .build();
    }

    public static List<Ingredient> toIngredients (List<IngredientDto> ingredientDtos, Recipe recipe) {
        return ingredientDtos
                .stream()
                .map(ingredientDto -> toIngredient(ingredientDto, recipe))
                .collect(Collectors.toList());
    }

    public static List<RecipeResponseDto> toListRecipeResponseDto(List<Recipe> recipes) {
        List<RecipeResponseDto> result = new ArrayList<>();

        recipes.forEach(recipe -> result.add(Converter.toRecipeResponseDto(recipe)));

        return result;
    }

    private static RecipeResponseDto toRecipeResponseDto(Recipe recipe) {
        return RecipeResponseDto.builder()
                .name(recipe.getName())
                .imageUrl(recipe.getImageUrl())
                .build();
    }
}
