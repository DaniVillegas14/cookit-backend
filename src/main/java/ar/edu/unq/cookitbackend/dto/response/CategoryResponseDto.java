package ar.edu.unq.cookitbackend.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class CategoryResponseDto {
    private Long id;
    private String name;
    private List<RecipeResponseDto> favorites;
}
