package ar.edu.unq.cookitbackend.utils;

import ar.edu.unq.cookitbackend.dto.request.*;
import ar.edu.unq.cookitbackend.dto.response.CommentResponseDto;
import ar.edu.unq.cookitbackend.dto.response.RecipeResponseDto;
import ar.edu.unq.cookitbackend.dto.response.UserCommentResponseDto;
import ar.edu.unq.cookitbackend.model.*;

import java.time.LocalDateTime;
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

    public static RecipeResponseDto toRecipeResponseDto(Recipe recipe) {
        return RecipeResponseDto.builder()
                .name(recipe.getName())
                .description(recipe.getDescription())
                .imageUrl(recipe.getImageUrl())
                .comensales(recipe.getComensales())
                .time(recipe.getTime())
                .created_at(recipe.getCreated_at())
                .ingredients(recipe.getIngredients())
                .steps(recipe.getSteps())
                .comments(toCommentResponseDto(recipe.getComments()))
                .build();
    }

    private static List<CommentResponseDto> toCommentResponseDto(List<Comment> comments) {
        return comments.stream().map(Converter::toCommentDto).collect(Collectors.toList());
    }

    private static CommentResponseDto toCommentDto(Comment comment) {
        return CommentResponseDto.builder()
                .message(comment.getMessage())
                .created_at(comment.getCreated_at())
                .owner(toUserCommentResponseDto(comment.getOwner()))
                .build();
    }

    private static UserCommentResponseDto toUserCommentResponseDto(User user) {
        return UserCommentResponseDto.builder()
                .name(user.getName())
                .imageUrl(user.getImageUrl())
                .build();
    }

    public static Comment toComment(String message, Recipe recipe, User owner) {
        return Comment.builder()
                .message(message)
                .created_at(LocalDateTime.now())
                .recipe(recipe)
                .owner(owner)
                .build();
    }
}
