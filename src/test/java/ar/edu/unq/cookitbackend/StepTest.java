package ar.edu.unq.cookitbackend;

import ar.edu.unq.cookitbackend.model.Recipe;
import ar.edu.unq.cookitbackend.model.Step;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

public class StepTest {

    @Test
    public void testGivenAStepWithDescriptionWhenRecieveGetDescriptionThenGiveHisDescription() {
        Step step = Step.builder()
                .description("Colocar harina")
                .build();

        Assert.assertEquals("Colocar harina", step.getDescription());
    }

    @Test
    public void testGivenAStepWithRecipeWhenRecieveGetRecipeThenGiveHisRecipe() {
        Recipe recipe = mock(Recipe.class);

        Step step = Step.builder()
                .recipe(recipe)
                .build();

        Assert.assertEquals(recipe, step.getRecipe());
    }
}
