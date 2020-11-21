package ar.edu.unq.cookitbackend.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class EditRecipeRequestDto {
    private Long id;
    private int comensales;
    private String description;
    private int time;
    private String name;
    private String image_url;
    private List<IngredientDto> ingredients;
    private List<StepDto> steps;
    private Long userId;
}
