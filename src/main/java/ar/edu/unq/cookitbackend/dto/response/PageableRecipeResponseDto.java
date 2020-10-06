package ar.edu.unq.cookitbackend.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter @Builder
public class PageableRecipeResponseDto {

    private Long id;
    private String name;
    private String description;
    private String imageUrl;
    private LocalDateTime created_at;
    private LittleUserResponseDto user;
}
