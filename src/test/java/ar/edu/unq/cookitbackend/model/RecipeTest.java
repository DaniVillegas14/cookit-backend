package ar.edu.unq.cookitbackend.model;

import ar.edu.unq.cookitbackend.model.Ingredient;
import ar.edu.unq.cookitbackend.model.Recipe;
import ar.edu.unq.cookitbackend.model.Step;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;

public class RecipeTest {

    @Test
    public void testGivenARecipeWithNameWhenRecieveGetNameThenGiveHisName() {
        Recipe recipe = Recipe.builder()
                .name("Muffin magico")
                .build();

        Assert.assertEquals("Muffin magico", recipe.getName());
    }

    @Test
    public void testGivenARecipeWithDescriptionWhenRecieveGetDescriptionThenGiveHisDescription() {
        Recipe recipe = Recipe.builder()
                .description("El mejor postre")
                .build();

        Assert.assertEquals("El mejor postre", recipe.getDescription());
    }

    @Test
    public void testGivenARecipeWithImageUrlWhenRecieveGetImageUrlThenGiveHisImageUrl() {
        Recipe recipe = Recipe.builder()
                .imageUrl("http://www.elmuffinmagico.com")
                .build();

        Assert.assertEquals("http://www.elmuffinmagico.com", recipe.getImageUrl());
    }

    @Test
    public void testGivenARecipeWithComensalesWhenRecieveGetComensalesThenGiveHisComensales() {
        Recipe recipe = Recipe.builder()
                .comensales(4)
                .build();

        Assert.assertEquals(4, recipe.getComensales());
    }

    @Test
    public void testGivenARecipeWithTimeWhenRecieveGetTimeThenGiveHisTime() {
        Recipe recipe = Recipe.builder()
                .time(6)
                .build();

        Assert.assertEquals(6, recipe.getTime());
    }

    @Test
    public void testGivenARecipeWithIngredientsWhenRecieveSizeGetIngredientsThenGiveTheSizeFromIngredients() {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(mock(Ingredient.class));

        Recipe recipe = Recipe.builder()
                .ingredients(ingredients)
                .build();

        Assert.assertEquals(1, recipe.getIngredients().size());
    }

    @Test
    public void testGivenARecipeWithStepsWhenRecieveSizeGetStepsThenGiveTheSizeFromSteps() {
        List<Step> steps = new ArrayList<>();
        steps.add(mock(Step.class));

        Recipe recipe = Recipe.builder()
                .steps(steps)
                .build();

        Assert.assertEquals(1, recipe.getSteps().size());
    }
}
