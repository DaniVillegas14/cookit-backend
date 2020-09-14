package ar.edu.unq.cookitbackend.utils;

import ar.edu.unq.cookitbackend.dto.request.IngredientDto;
import ar.edu.unq.cookitbackend.dto.request.RecipeDto;
import ar.edu.unq.cookitbackend.dto.request.UserRequestDto;
import ar.edu.unq.cookitbackend.dto.response.RecipeResponseDto;
import ar.edu.unq.cookitbackend.model.Ingredient;
import ar.edu.unq.cookitbackend.model.Recipe;
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
        return Recipe.builder()
                .comensales(recipeDto.getComensales())
                .description(recipeDto.getDescription())
                .imageUrl(recipeDto.getImage_url())
                .name(recipeDto.getName())
                .time(recipeDto.getTime())
                .ingredients(toIngredients(recipeDto.getIngredients()))
                .build();
    }

    public static Ingredient toIngredient(IngredientDto ingredientDto) {
        return Ingredient.builder()
                .name(ingredientDto.getName())
                .quantity_weight(ingredientDto.getQuantity_weight())
                .build();
    }

    public static List<Ingredient> toIngredients (List<IngredientDto> ingredientDtos) {
        return ingredientDtos
                .stream()
                .map(Converter::toIngredient)
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
