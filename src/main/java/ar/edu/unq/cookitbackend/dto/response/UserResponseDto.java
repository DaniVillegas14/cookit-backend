package ar.edu.unq.cookitbackend.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter @Setter @Builder
public class UserResponseDto {
    private Long id;
    private String email;
    private String imageUrl;
    private String name;
    private String lastname;
}
