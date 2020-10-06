package ar.edu.unq.cookitbackend.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class IngredientResponseDto {
    private String quantity_weight;
    private String name;
}
