package ar.edu.unq.cookitbackend.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class RecipeResponseDto {

    private String name;
    private String description;
    private String imageUrl;

}
