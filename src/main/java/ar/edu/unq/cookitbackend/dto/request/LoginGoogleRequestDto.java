package ar.edu.unq.cookitbackend.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class LoginGoogleRequestDto {
    private String email;
    private String name;
    private String lastname;
    private String imageUrl;
}
