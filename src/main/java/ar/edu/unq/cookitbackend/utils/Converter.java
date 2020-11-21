package ar.edu.unq.cookitbackend.utils;

import ar.edu.unq.cookitbackend.dto.response.CommentResponseDto;
import ar.edu.unq.cookitbackend.dto.response.RecipeResponseDto;
import ar.edu.unq.cookitbackend.dto.response.UserCommentResponseDto;
import ar.edu.unq.cookitbackend.dto.request.IngredientDto;
import ar.edu.unq.cookitbackend.dto.request.RecipeDto;
import ar.edu.unq.cookitbackend.dto.request.StepDto;
import ar.edu.unq.cookitbackend.dto.request.UserRequestDto;
import ar.edu.unq.cookitbackend.dto.response.*;
import ar.edu.unq.cookitbackend.model.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class Converter<R extends BaseEntity, P> {

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

    public static PageableRecipeResponseDto toPageableRecipeDto(Recipe recipe) {
        return PageableRecipeResponseDto.builder()
                .id(recipe.getId())
                .created_at(recipe.getCreated_at())
                .description(recipe.getDescription())
                .imageUrl(recipe.getImageUrl())
                .name(recipe.getName())
                .user(convertUserToLittleUserDto(recipe.getUser()))
                .build();
    }

    public static LittleUserResponseDto convertUserToLittleUserDto(User user) {
        return LittleUserResponseDto.builder()
                .id(user.getId())
                .imageUrl(user.getImageUrl())
                .lastname(user.getLastname())
                .name(user.getName())
                .build();
    }

    public static RecipeResponseDto toRecipeResponseDto(Recipe recipe) {
        return RecipeResponseDto.builder()
                .id(recipe.getId())
                .created_at(recipe.getCreated_at())
                .comensales(recipe.getComensales())
                .description(recipe.getDescription())
                .imageUrl(recipe.getImageUrl())
                .comensales(recipe.getComensales())
                .time(recipe.getTime())
                .commentsSize(recipe.getComments().size())
                .created_at(recipe.getCreated_at())
                .name(recipe.getName())
                .ingredients(toIngredientsResponseDto(recipe.getIngredients()))
                .steps(toStepsResponseDto(recipe.getSteps()))
                .user(convertUserToLittleUserDto(recipe.getUser()))
                .build();
    }

    public static List<CommentResponseDto> toCommentsResponseDto(List<Comment> comments) {
        return comments.stream().map(Converter::toCommentResponseDto).collect(Collectors.toList());
    }

    public static CommentResponseDto toCommentResponseDto(Comment comment) {
        return CommentResponseDto.builder()
                .id(comment.getId())
                .message(comment.getMessage())
                .created_at(comment.getCreated_at())
                .owner(toUserCommentResponseDto(comment.getOwner()))
                .build();
    }

    public static UserCommentResponseDto toUserCommentResponseDto(User user) {
        return UserCommentResponseDto.builder()
                .idUser(user.getId())
                .name(user.getName())
                .lastname(user.getLastname())
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

    public static UserResponseDto toUserResponseDto(User user, List<Recipe> recipes) {
        return UserResponseDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .lastname(user.getLastname())
                .imageUrl(user.getImageUrl())
                .isGoogleAccount(user.getIsGoogleAccount())
                .favorites(user.getFavorite_recipes().stream().map(Converter::toRecipeResponseDto).collect(Collectors.toList()))
                .recipes(recipes.stream().map(Converter::toRecipeResponseDto).collect(Collectors.toList()))
                .followeds(user.getFollows().stream().map(Converter::convertUserToLittleUserDto).collect(Collectors.toList()))
                .followers(user.getFollowers().stream().map(Converter::convertUserToLittleUserDto).collect(Collectors.toList()))
                .build();
    }

    public static List<IngredientResponseDto> toIngredientsResponseDto(List<Ingredient> ingredients) {
        return ingredients
                .stream()
                .map(Converter::toIngredientResponseDto)
                .collect(Collectors.toList());
    }

    public static IngredientResponseDto toIngredientResponseDto(Ingredient ingredient) {
        return IngredientResponseDto.builder()
                .name(ingredient.getName())
                .quantity_weight(ingredient.getQuantity_weight())
                .build();
    }

    public static List<StepResponseDto> toStepsResponseDto(List<Step> steps) {
        return steps
                .stream()
                .map(Converter::toStepResponseDto)
                .collect(Collectors.toList());
    }

    public static StepResponseDto toStepResponseDto(Step step) {
        return StepResponseDto.builder()
                .description(step.getDescription())
                .build();
    }

    public static List<CategoryResponseDto> toCategoriesResponseDto(List<Category> categories) {
        return categories.stream().map(Converter::toCategoryResponseDto).collect(Collectors.toList());
    }

    public static CategoryResponseDto toCategoryResponseDto(Category category) {
        return CategoryResponseDto.builder()
                .id(category.getId())
                .name(category.getName())
                .favorites(category.getFavoriteRecipeList().stream().map(Converter::toRecipeResponseDto).collect(Collectors.toList()))
                .build();
    }

    public static List<RecipeResponseDto> toRecipesResponseDto(List<Recipe> recipes) {
        return recipes.stream().map(Converter::toRecipeResponseDto).collect(Collectors.toList());
    }
}
