package ar.edu.unq.cookitbackend.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class LittleUserResponseDto {
    private Long id;
    private String name;
    private String lastname;
    private String imageUrl;
}
