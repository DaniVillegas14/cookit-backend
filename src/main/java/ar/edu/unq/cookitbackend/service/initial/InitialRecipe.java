package ar.edu.unq.cookitbackend.service.initial;

import ar.edu.unq.cookitbackend.model.*;
import ar.edu.unq.cookitbackend.persistence.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class InitialRecipe {
    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    private InitialIngredient initialIngredient;

    @Autowired
    private InitialComment initialComment;

    @Autowired
    private InitialStep initialStep;

    public void createRecipes(User user) {
        this.generateRecipes().forEach(recipe -> {
            List<Ingredient> ingredients = initialIngredient.createIngredients();
            List<Step> steps = initialStep.createSteps();
            List<Comment> comments = initialComment.createComments();
            recipe.setIngredients(ingredients);
            recipe.setSteps(steps);
            recipe.setComments(comments);
            steps.forEach(step -> step.setRecipe(recipe));
            ingredients.forEach(ingredient -> ingredient.setRecipe(recipe));
            comments.forEach(comment -> comment.setRecipe(recipe));
            user.addRecipe(recipe);
            recipe.setUser(user);
            recipeRepository.save(recipe);
        });
    }

    public List<Recipe> generateRecipes() {
        return new ArrayList<>(Arrays.asList(
                Recipe.builder()
                        .description("Para aprovechar las frutillas de estación. En este caso la hice doble porque somos muchos, pero se puede hacer la mitad y sale una tarta redonda convencional.")
                        .name("Tarta de frutillas")
                        .time(30)
                        .imageUrl("https://img-global.cpcdn.com/recipes/424565f2d5a5103c/751x532cq70/tarta-de-frutillas-foto-principal.jpg")
                        .build(),
                Recipe.builder()
                        .description("Ideal para una comida rica y liviana. Ideal para una de estas noches frías que aun tenemos, ya que todo se hace al horno.")
                        .name("Salmon rosado con budin de verduras")
                        .time(30)
                        .imageUrl("https://img-global.cpcdn.com/recipes/7da4e1e662bcf5b4/751x532cq70/salmon-rosado-con-budin-de-verduras-foto-principal.jpg")
                        .build(),
                Recipe.builder()
                        .description("Oreo , dulce de leche, crema chica, Azúcar, Leche, Esencia de vainilla, Papel film(opcional).")
                        .name("Postre oreo")
                        .time(30)
                        .imageUrl("https://img-global.cpcdn.com/recipes/9ec5cf84d2cb7938/751x532cq70/postre-oreo-foto-principal.jpg")
                        .build(),
                Recipe.builder()
                        .description("Sin descripcion")
                        .name("Chipa")
                        .time(30)
                        .imageUrl("https://img-global.cpcdn.com/recipes/63c7a764bd6bb505/751x532cq70/chipa-foto-principal.jpg")
                        .build(),
                Recipe.builder()
                        .description("Riquisimos muffins para salvar la tarde")
                        .name("Muffins de chocolate")
                        .time(30)
                        .imageUrl("https://img-global.cpcdn.com/recipes/419fd48c1ae27237/751x532cq70/muffins-de-chocolate-%F0%9F%A7%A1espectaculares%F0%9F%A7%A1-foto-principal.jpg")
                        .build(),
                Recipe.builder()
                        .description("Me sobró arroz y me propuse hacer algo diferente para aprovecharlo con lo que tenía en casa, animarte a probar con otras verduras te puede llevar a encontrar un sabor nuevo.")
                        .name("Muffins de arroz y zanahoria")
                        .time(30)
                        .imageUrl("https://img-global.cpcdn.com/recipes/927af10d5064ca75/751x532cq70/muffins-de-arroz-y-zanahoria-foto-principal.jpg")
                        .build(),
                Recipe.builder()
                        .description("Un clásico de clasicos pero sin aceite. Tortilla de papas.")
                        .name("Tortilla de papas")
                        .time(30)
                        .imageUrl("https://img-global.cpcdn.com/recipes/0b69e91cbcc99a6a/751x532cq70/tortilla-de-papas-saludable-foto-principal.jpg")
                        .build(),
                Recipe.builder()
                        .description("Ideales para acompañar un rico té")
                        .name("Scons de naranja")
                        .time(30)
                        .imageUrl("https://img-global.cpcdn.com/recipes/33420f716e2a7f6c/751x532cq70/scons-de-naranja-%F0%9F%8D%8A%E2%99%A5%EF%B8%8F-foto-principal.jpg")
                        .build(),
                Recipe.builder()
                        .description("Las tradicionales de siempre!!")
                        .name("Empanadas de carne")
                        .time(30)
                        .imageUrl("https://img-global.cpcdn.com/recipes/20e7b8db1cc404b2/751x532cq70/empanadas-de-carne-fritas-foto-principal.jpg")
                        .build(),
                Recipe.builder()
                        .description("Unas ricas y rápidas galletitas Sin Tacc , para disfrutar con toda la familia.")
                        .name("Galletitas sin tacc")
                        .time(30)
                        .imageUrl("https://img-global.cpcdn.com/recipes/2eb84764b7679ddc/751x532cq70/galletitas-sin-tacc-foto-principal.jpg")
                        .build()
        ));
    }
}
