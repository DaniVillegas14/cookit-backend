package ar.edu.unq.cookitbackend.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class RecipeDto {
    private int comensales;
    private String description;
    private int time;
    private String name;
    private String image_url;
    private List<IngredientDto> ingredients;
}
