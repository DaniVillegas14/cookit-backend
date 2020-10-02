package ar.edu.unq.cookitbackend.dto.response;

import ar.edu.unq.cookitbackend.model.Ingredient;
import ar.edu.unq.cookitbackend.model.Step;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class RecipeResponseDto {

    private String name;
    private String description;
    private String imageUrl;
    private int comensales;
    private int time;
    private LocalDateTime created_at;
    private List<Ingredient> ingredients;
    private List<Step> steps;
    private List<CommentResponseDto> comments;
}
