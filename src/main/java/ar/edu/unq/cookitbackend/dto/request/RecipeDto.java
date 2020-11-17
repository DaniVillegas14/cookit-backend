package ar.edu.unq.cookitbackend.dto.request;

import lombok.*;

import java.util.List;

@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class RecipeDto {
    private int comensales;
    private String description;
    private int time;
    private String name;
    private String image_url;
    private List<IngredientDto> ingredients;
    private List<StepDto> steps;
    private Long userId;
}
