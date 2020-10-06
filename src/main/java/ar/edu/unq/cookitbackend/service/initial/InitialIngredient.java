package ar.edu.unq.cookitbackend.service.initial;

import ar.edu.unq.cookitbackend.model.Ingredient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class InitialIngredient {
    public List<Ingredient> createIngredients() {
        return new ArrayList<>(Arrays.asList(
                Ingredient.builder()
                        .name("Agua")
                        .quantity_weight("200ml")
                        .build(),
                Ingredient.builder()
                        .name("Harina 000")
                        .quantity_weight("1/2 kilo")
                        .build(),
                Ingredient.builder()
                        .name("Azucar")
                        .quantity_weight("3 cucharadas")
                        .build(),
                Ingredient.builder()
                        .name("Tomates")
                        .quantity_weight("4")
                        .build(),
                Ingredient.builder()
                        .name("Queso a eleccion")
                        .quantity_weight("200 gramos")
                        .build()

        ));
    }
}
