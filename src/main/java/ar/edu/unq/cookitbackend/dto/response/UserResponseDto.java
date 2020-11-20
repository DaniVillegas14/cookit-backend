package ar.edu.unq.cookitbackend.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Getter @Setter @Builder
public class UserResponseDto {
    private Long id;
    private String email;
    private String imageUrl;
    private String name;
    private String lastname;
    private Boolean isGoogleAccount;
    private List<RecipeResponseDto> favorites;
    private List<RecipeResponseDto> recipes;
    private List<LittleUserResponseDto> followeds;
    private List<LittleUserResponseDto> followers;
}
