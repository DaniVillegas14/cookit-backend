package ar.edu.unq.cookitbackend;

import ar.edu.unq.cookitbackend.model.Ingredient;
import ar.edu.unq.cookitbackend.model.Recipe;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

public class IngredientTest {

    @Test
    public void testGivenAIngredientWithNameWhenRecieveGetNameThenGiveHisName() {
        Ingredient ingredient = Ingredient.builder()
                .name("Chocolate")
                .build();

        Assert.assertEquals("Chocolate", ingredient.getName());
    }

    @Test
    public void testGivenAIngredientWithQuantityWeightWhenRecieveGetQuantityWeightThenGiveHisQuantityWeight() {
        Ingredient ingredient = Ingredient.builder()
                .quantity_weight("200gr")
                .build();

        Assert.assertEquals("200gr", ingredient.getQuantity_weight());
    }

    @Test
    public void testGivenAIngredientWithRecipeWhenRecieveGetRecipeThenGiveHisRecipe() {
        Recipe recipe = mock(Recipe.class);

        Ingredient ingredient = Ingredient.builder()
                .recipe(recipe)
                .build();

        Assert.assertEquals(recipe, ingredient.getRecipe());
    }

}
