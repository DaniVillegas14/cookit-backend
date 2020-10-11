package ar.edu.unq.cookitbackend.dto.response;

import lombok.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class RecipeResponseDto {
    private Long id;
    private int comensales;
    private String description;
    private LocalDateTime created_at;
    private int time;
    private String name;
    private String imageUrl;
    private CommentResponseDto lastComment;
    private Integer commentsSize;
    private List<IngredientResponseDto> ingredients;
    private List<StepResponseDto> steps;
    private LittleUserResponseDto user;
}
